package com.wine.game.wine.service.impl;

import com.wine.game.wine.common.AvatarHelper;
import com.wine.game.wine.common.IpUtil;
import com.wine.game.wine.common.RandomName;
import com.wine.game.wine.security.LoginUser;
import com.wine.game.wine.vo.UserVo;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zenofung.common.utils.PageUtils;
import com.zenofung.common.utils.Query;

import com.wine.game.wine.dao.UserDao;
import com.wine.game.wine.entity.UserEntity;
import com.wine.game.wine.service.UserService;

import javax.servlet.http.HttpServletRequest;


@Service("userService")
public class UserServiceImpl extends ServiceImpl<UserDao, UserEntity> implements UserService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<UserEntity> page = this.page(
                new Query<UserEntity>().getPage(params),
                new QueryWrapper<UserEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public UserVo getByIdUserVo(String unId) {
        UserEntity byId = this.getById(unId);
        UserVo userVo=new UserVo();
        BeanUtils.copyProperties(byId,userVo);
        return userVo;
    }


    @Override
    public UserEntity saveUser(UserEntity user, HttpServletRequest httpServletRequest) {
        if (StringUtils.isEmpty(user.getUserNikename())){
            String randomJianHan = RandomName.getRandomJianHan(2);
            user.setUserNikename(randomJianHan);
        }
        String ipAddr = IpUtil.getIpAddr(httpServletRequest);
        user.setLastIp(ipAddr);
        if (StringUtils.isEmpty(user.getUserProtrait())){
            try {
                String base64Avatar = AvatarHelper.createBase64Avatar(Math.abs(user.getUserNikename().hashCode()));
                user.setUserProtrait(AvatarHelper.BASE64_PREFIX+base64Avatar);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        this.save(user);
        return user;
    }

    @Override
    public LoginUser getLoginUser(String username) {
        // UserEntity userEntity = this.list(new QueryWrapper<UserEntity>().eq("user_name", username)).get(0);
        return  null;

    }

}