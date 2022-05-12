package com.wine.game.wine.nettywebsocket.handler;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wine.game.wine.config.NettyConfig;
import com.wine.game.wine.entity.ImMessageEntity;
import com.wine.game.wine.entity.ImMessageListEntity;
import com.wine.game.wine.entity.UserEntity;
import com.wine.game.wine.entity.WineUsersEntity;
import com.wine.game.wine.nettywebsocket.NettyServer;
import com.wine.game.wine.nettywebsocket.common.MessageEnum;
import com.wine.game.wine.nettywebsocket.entity.MessageEntity;
import com.wine.game.wine.service.ImMessageListService;
import com.wine.game.wine.service.ImMessageService;
import com.wine.game.wine.service.UserService;
import com.zenofung.common.utils.R;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.util.AttributeKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.List;

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


    @Autowired
    private ImMessageService imMessageService;
    @Autowired
    private ImMessageListService imMessageListService;
    @Autowired
    private UserService userService;


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

        // log.info("服务器收到消息：{}",msg.text());
        JSONObject jsonObject = JSONUtil.parseObj(msg.text());
        MessageEntity messageEntity = jsonObject.toBean(MessageEntity.class);
        if (messageEntity.getStatus().equals(MessageEnum.HEARTBEAT.getState())){
            // log.info("心跳：{}",msg.text());
        }else if (messageEntity.getStatus().equals(MessageEnum.LOGIN.getState())){
            // 关联channel与用户id， 广播登录状态给好友登录列表，返回好友列表未读信息，
            this.login(ctx, msg,messageEntity.getContent(),MessageEnum.LOGIN.getState());
        }else if (messageEntity.getStatus().equals(MessageEnum.SENDMESSAGE_SINGLE.getState())){
            //  单聊 如果聊天用户在线， 如果聊天用户不在线
            this.sendSingle(ctx,messageEntity.getContent());
        }else if (messageEntity.getStatus().equals(MessageEnum.SENDMESSAGE_GROUP.getState())){
            // TODO 群聊
            if (NettyConfig.getUserChannelMap().containsKey(messageEntity.getHid())){
                Channel channel = NettyConfig.getUserChannelMap().get(messageEntity.getHid());
                channel.writeAndFlush(new TextWebSocketFrame(messageEntity.getContent()));
            }
        }



    }

    private void sendSingle(ChannelHandlerContext ctx, String content) {
        ImMessageEntity imMessageEntity = JSONUtil.toBean(content, ImMessageEntity.class);

        //判断对方是否有好友列表，没有新增， 后面再考虑不接收消息
        List<ImMessageListEntity> list = imMessageListService.list(new QueryWrapper<ImMessageListEntity>().eq("user_id", imMessageEntity.getUserId()).eq("friend_id", imMessageEntity.getTargetId()));
        ImMessageListEntity imMessageListEntity;
        if (list.size()<=0){
            imMessageListEntity=new ImMessageListEntity();
            imMessageListEntity.setUserId(imMessageEntity.getTargetId());
            imMessageListEntity.setFriendId(imMessageEntity.getUserId());
            imMessageListEntity.setOnLine(1);
            imMessageListService.save(imMessageListEntity);
        }else {
            imMessageListEntity = list.get(0);
        }

        if (NettyConfig.getUserChannelMap().containsKey(imMessageEntity.getTargetId())){
            //在线直接发送并同步数据库
            Channel channel = NettyConfig.getUserChannelMap().get(imMessageEntity.getTargetId());
            channel.writeAndFlush(new TextWebSocketFrame(R.getJsonR(R.ok().put("content",imMessageEntity))));
            imMessageEntity.setImMagListId(imMessageListEntity.getId());
            imMessageService.save(imMessageEntity);
        }else {
            //不在线同步数据库即可
            imMessageEntity.setImMagListId(imMessageListEntity.getId());
            imMessageService.save(imMessageEntity);
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


    private void login(ChannelHandlerContext ctx, TextWebSocketFrame msg,String jsonObject,String loginStatus) {
        // 获取用户ID
        UserEntity userEntity = JSONUtil.toBean(jsonObject, UserEntity.class);
        if (!StringUtils.isEmpty(userEntity)){
            // 将用户ID作为自定义属性加入到channel中，方便随时channel中获取用户ID
            AttributeKey<String> key = AttributeKey.valueOf("userId");
            ctx.channel().attr(key).setIfAbsent(userEntity.getId());
            // 关联channel
            NettyConfig.getUserChannelMap().put(userEntity.getId(),ctx.channel());
            // 回复消息
            List<ImMessageListEntity> friend = imMessageListService.list(new QueryWrapper<ImMessageListEntity>().eq("friend_id", userEntity.getId()));
            //更改登录状态
            UserEntity userEntity1=new UserEntity();
            userEntity1.setId(userEntity.getId());
            userEntity1.setLoginStatus(Integer.parseInt(loginStatus));
            userService.updateById(userEntity1);
            UserEntity user = userService.getById(userEntity.getId());
            friend.stream().forEach(m->{
               if (NettyConfig.getUserChannelMap().containsKey(m.getUserId())){
                   NettyConfig.getUserChannelMap().get(m.getUserId()).writeAndFlush(new TextWebSocketFrame(R.getJsonR(R.ok(MessageEnum.LOGIN.getState()).put("loginUser",user))));
               }
            });

            ctx.channel().writeAndFlush(new TextWebSocketFrame(R.getJsonR(R.ok())));
        }else {
            ctx.channel().writeAndFlush(new TextWebSocketFrame(R.getJsonR(R.error())));
        }

    }
}