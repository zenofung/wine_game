package com.wine.game.wine.nettywebsocket.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;

/**
 * @description: 
 
 #　　　Codes are far away from bugs with the animal protecting　　　
 #　　　　　　　        神兽保佑,代码无bug　
 
 * @author: zeno fung
 *
 * @create: 2022-05-09 13:40
 */
public class HeartBeatHandler  extends ChannelInboundHandlerAdapter {
    private int lossConnectCount = 0;
    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        if (evt instanceof IdleStateEvent){
            IdleStateEvent event = (IdleStateEvent)evt;
            if (event.state()== IdleState.READER_IDLE){
                lossConnectCount ++;
                if (lossConnectCount > 2){
                    ctx.channel().close();
                }
            }
        }else {
            super.userEventTriggered(ctx,evt);
        }
    }
}