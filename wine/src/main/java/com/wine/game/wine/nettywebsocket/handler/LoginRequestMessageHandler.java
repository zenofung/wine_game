package com.wine.game.wine.nettywebsocket.handler;

import com.mysql.cj.xdevapi.SessionFactory;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import org.springframework.stereotype.Component;

/**
 * @description: 
 
 #　　　Codes are far away from bugs with the animal protecting　　　
 #　　　　　　　        神兽保佑,代码无bug　
 
 * @author: zeno fung
 *
 * @create: 2022-05-10 13:57
 */
@Component
@ChannelHandler.Sharable
public class LoginRequestMessageHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {
    // @Override
    // protected void channelRead0(ChannelHandlerContext ctx, LoginRequestMessage msg) throws Exception {
    //     String username = msg.getUsername();
    //     String password = msg.getPassword();
    //     boolean login = UserServiceFactory.getUserService().login(username, password);
    //     LoginResponseMessage message;
    //     if (login) {
    //         SessionFactory.getSession().bind(ctx.channel(), username);
    //         message = new LoginResponseMessage(true, "登录成功");
    //     } else {
    //         message = new LoginResponseMessage(false, "用户名或密码不正确");
    //     }
    //     ctx.writeAndFlush(message);
    // }

    /**
     * <strong>Please keep in mind that this method will be renamed to
     * {@code messageReceived(ChannelHandlerContext, I)} in 5.0.</strong>
     * <p>
     * Is called for each message of type {@link I}.
     *
     * @param ctx the {@link ChannelHandlerContext} which this {@link SimpleChannelInboundHandler}
     *            belongs to
     * @param msg the message to handle
     * @throws Exception is thrown if an error occurred
     */
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, TextWebSocketFrame msg) throws Exception {

    }
}
