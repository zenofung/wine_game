package com.wine.game.wine.controller;

import com.wine.game.wine.common.Constants;
import com.wine.game.wine.common.ServletUtils;
import com.wine.game.wine.entity.UserEntity;
import com.wine.game.wine.security.LoginUser;
import com.wine.game.wine.security.service.SysLoginService;
import com.wine.game.wine.security.service.TokenService;
import com.zenofung.common.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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


}