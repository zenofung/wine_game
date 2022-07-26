package com.wine.game.wine.controller;

import com.wine.game.wine.common.Constants;
import com.wine.game.wine.common.ServletUtils;
import com.wine.game.wine.entity.UserEntity;
import com.wine.game.wine.security.LoginUser;
import com.wine.game.wine.security.service.SysLoginService;
import com.wine.game.wine.security.service.TokenService;
import com.zenofung.common.utils.R;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @description: 
 
 #　　　Codes are far away from bugs with the animal protecting　　　
 #　　　　　　　        神兽保佑,代码无bug　
 
 * @author: zeno fung
 *
 * @create: 2022-05-18 14:46
 */
@RestController
@RequestMapping("wine/")
@Slf4j
public class LoginController {

    @Autowired
    private SysLoginService sysLoginService;
    @Autowired
    private TokenService tokenService;

    @PostMapping("login")
    public R login(@RequestBody UserEntity userEntity)
    {
        // 生成令牌
        String token = sysLoginService.login(userEntity.getUserPhone(),userEntity.getOpenId());
        return R.ok().put("token",token);
    }

    /**
     * 获取用户信息
     *
     * @return 用户信息
     */
    @GetMapping("getInfo")
    public R getInfo()
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        return R.ok().put("user", loginUser);
    }
    /**
     * 获取用户信息
     *
     * @return 用户信息
     */
    @GetMapping("good")
    public String good()
    {
       return "good";
    }

    @GetMapping("infiniteLoop2")
    public void infiniteLoop2(){
        log.info("进入");
        //死锁
        try {
            Thread.sleep(100000000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @GetMapping("infiniteLoop3")
    public void infiniteLoop3(){
        log.info("进入3");
        new Thread(()->{
            try {
                Thread.sleep(500000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }

    @GetMapping("infiniteLoop")
    public void infiniteLoop(){
        log.info("进入");
        //死锁
        new Thread(new Thread1()).start();
        new Thread(new Thread2()).start();
        //堆溢出
        // List<byte[]> list = new  ArrayList<byte[]>();
        // for(int i =0;i<1024000;i++) {
        //     list.add(new byte[1024*1024]);
        // }
      // List list=new ArrayList();
      // Thread thread=new Thread(new MyThread01());
      // thread.setName("zeno_Thread");
      // thread.start();
      // new Thread(()->{

      // }).start();

    }
    private static Integer resource1 = 1 ;
    private static Integer resource2 = 2 ;
    static class Thread1 implements Runnable{
        @Override
        public void run() {
            synchronized (resource1){
                try {
                    System.out.println(getClass().getName()+" obtains the lock of resource1!");
                    Thread.sleep(500);
                    synchronized (resource2) {
                        System.out.println(getClass().getName()+" obtains the lock of resource2!");
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    static class Thread2 implements Runnable{
        @Override
        public void run() {
            synchronized (resource2){
                try {
                    System.out.println(getClass().getName()+" obtains the lock of resource2!");
                    Thread.sleep(500);
                    synchronized (resource1) {
                        System.out.println(getClass().getName()+" obtains the lock of resource1!");
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }


}