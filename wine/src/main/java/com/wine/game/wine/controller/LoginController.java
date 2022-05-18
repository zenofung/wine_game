package com.wine.game.wine.controller;

import com.wine.game.wine.common.Constants;
import com.wine.game.wine.entity.UserEntity;
import com.wine.game.wine.security.service.SysLoginService;
import com.zenofung.common.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @description: 
 
 #　　　Codes are far away from bugs with the animal protecting　　　
 #　　　　　　　        神兽保佑,代码无bug　
 
 * @author: zeno fung
 *
 * @create: 2022-05-18 14:46
 */
@Controller
public class LoginController {

    @Autowired
    private SysLoginService sysLoginService;
    @PostMapping("/login")
    public R login(@RequestBody UserEntity userEntity)
    {
        // 生成令牌
        String token = sysLoginService.login(userEntity.getUserPhone(),userEntity.getOpenId());
        return R.ok().put("token",token);
    }
}