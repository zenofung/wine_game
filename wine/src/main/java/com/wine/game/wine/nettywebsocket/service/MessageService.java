package com.wine.game.wine.nettywebsocket.service;


/**
 * @description: #　　　Codes are far away from bugs with the animal protecting
 * #　　　　　　　        神兽保佑,代码无bug
 * @author: zeno fung
 * @create: 2022-05-09 15:25
 * <p>
 * 消息接口，分为单聊&群聊
 */

public interface MessageService {

    /**
     * 发送消息
     *
     * @param userId
     * @param msg
     */
    void sendMessage(String userId, String msg);

    /**
     * 接收消息
     *
     * @param msg
     */
    void receiveMessage(String msg);

}
