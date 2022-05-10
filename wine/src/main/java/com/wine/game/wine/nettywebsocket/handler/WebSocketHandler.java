package com.wine.game.wine.nettywebsocket.handler;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.wine.game.wine.config.NettyConfig;
import com.wine.game.wine.nettywebsocket.NettyServer;
import com.wine.game.wine.nettywebsocket.common.MessageEnum;
import com.wine.game.wine.nettywebsocket.entity.MessageEntity;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.util.AttributeKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @description: 
 
 #　　　Codes are far away from bugs with the animal protecting　　　
 #　　　　　　　        神兽保佑,代码无bug　
 
 * @author: zeno fung
 *
 * @create: 2022-05-09 13:38
 */
@Component
@ChannelHandler.Sharable
public class WebSocketHandler  extends SimpleChannelInboundHandler<TextWebSocketFrame> {



    private static final Logger log = LoggerFactory.getLogger(NettyServer.class);
    /**
     * 一旦连接，第一个被执行
     * @param ctx
     * @throws Exception
     */
    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        log.info("handlerAdded 被调用"+ctx.channel().id().asLongText());
        // 添加到channelGroup 通道组
        NettyConfig.getChannelGroup().add(ctx.channel());
    }

    /**
     * 读取数据
     */
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, TextWebSocketFrame msg) throws Exception {

        log.info("服务器收到消息：{}",msg.text());
        JSONObject jsonObject = JSONUtil.parseObj(msg.text());
        MessageEntity messageEntity = jsonObject.toBean(MessageEntity.class);
        if (messageEntity.getStatus().equals(MessageEnum.HEARTBEAT.getState())){
            // log.info("心跳：{}",msg.text());
        }else if (messageEntity.getStatus().equals(MessageEnum.LOGIN.getState())){
            //TODO 关联channel与用户id， 广播登录状态给好友登录列表，返回好友列表未读信息，

            // 获取用户ID
            String uid = jsonObject.getStr("uid");
            // 将用户ID作为自定义属性加入到channel中，方便随时channel中获取用户ID
            AttributeKey<String> key = AttributeKey.valueOf("userId");
            ctx.channel().attr(key).setIfAbsent(uid);
            // 关联channel
            NettyConfig.getUserChannelMap().put(uid,ctx.channel());
            // 回复消息
            ctx.channel().writeAndFlush(new TextWebSocketFrame("服务器连接成功！"));
        }else if (messageEntity.getStatus().equals(MessageEnum.SENDMESSAGE_SINGLE.getState())){
            // TODO 单聊 如果聊天用户在线， 如果聊天用户不在线
            if (NettyConfig.getUserChannelMap().containsKey(messageEntity.getHid())){
                Channel channel = NettyConfig.getUserChannelMap().get(messageEntity.getHid());
                channel.writeAndFlush(new TextWebSocketFrame(messageEntity.getContent()));
            }
        }else if (messageEntity.getStatus().equals(MessageEnum.SENDMESSAGE_GROUP.getState())){
            // TODO 群聊
            if (NettyConfig.getUserChannelMap().containsKey(messageEntity.getHid())){
                Channel channel = NettyConfig.getUserChannelMap().get(messageEntity.getHid());
                channel.writeAndFlush(new TextWebSocketFrame(messageEntity.getContent()));
            }
        }



    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        log.info("handlerRemoved 被调用"+ctx.channel().id().asLongText());
        // TODO  删除通道   下线通知，广播给好友
        NettyConfig.getChannelGroup().remove(ctx.channel());
        removeUserId(ctx);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        log.info("异常：{}",cause.getMessage());
        // 删除通道
        NettyConfig.getChannelGroup().remove(ctx.channel());
        removeUserId(ctx);
        ctx.close();
    }

    /**
     * 删除用户与channel的对应关系
     * @param ctx
     */
    private void removeUserId(ChannelHandlerContext ctx){
        AttributeKey<String> key = AttributeKey.valueOf("userId");
        String userId = ctx.channel().attr(key).get();
        NettyConfig.getUserChannelMap().remove(userId);
    }
}