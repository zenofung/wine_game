package com.wine.game.wine.common;


/**
 * @description: #　　　Codes are far away from bugs with the animal protecting
 * #　　　　　　　        神兽保佑,代码无bug
 * @author: zeno fung
 * @create: 2022-05-10 17:18
 */
public enum LogInEnum {

    REPETITION("0","repetition");



    private String state;
    private String info;

    private LogInEnum(String state, String info){
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
