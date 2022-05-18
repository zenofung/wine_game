package com.wine.game.wine.security.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.thoughtworks.xstream.core.BaseException;
import com.wine.game.wine.common.StringUtils;
import com.wine.game.wine.entity.UserEntity;
import com.wine.game.wine.security.LoginUser;
import com.wine.game.wine.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.naming.Name;
import java.util.ArrayList;
import java.util.List;

/**
 * 用户验证处理
 *
 * @author ruoyi
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private static final Logger log = LoggerFactory.getLogger(UserDetailsServiceImpl.class);

    @Autowired
    private UserService userService;

    @Autowired
    private SysPermissionService permissionService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = userService.list(new QueryWrapper<UserEntity>().eq("user_phone", username)).get(0);
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        if (org.springframework.util.StringUtils.isEmpty(user.getTourist())) {
            authorities.add(new SimpleGrantedAuthority(user.getTourist().toString()));
        }
        // return createLoginUser(user);
        return new User(user.getUserPhone(), user.getOpenId(),authorities );
    }

    public UserDetails createLoginUser(UserEntity user) {
        return new LoginUser(user, permissionService.getMenuPermission(user));
    }
}
