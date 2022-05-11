package com.wine.game.wine.nettywebsocket.common;


/**
 * @description: #　　　Codes are far away from bugs with the animal protecting
 * #　　　　　　　        神兽保佑,代码无bug
 * @author: zeno fung
 * @create: 2022-05-10 17:18
 */
public enum MessageEnum {

    QUIT("0","quit"),
    SENDMESSAGE_GROUP("3","sendMessage_group"),
    SENDMESSAGE_SINGLE("2","sendMessage_single"),
    LOGIN("1","login"),
    HEARTBEAT("0","heardbeat");



    private String state;
    private String info;

    private MessageEnum(String state,String info){
        this.info=info;
        this.state=state;
    }

    public String getState(){
        return state;
    }
    public String getInfo(){
        return info;
    }


}
