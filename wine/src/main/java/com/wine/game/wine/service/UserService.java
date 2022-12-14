package com.wine.game.wine.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wine.game.wine.security.LoginUser;
import com.wine.game.wine.vo.UserVo;
import com.zenofung.common.utils.PageUtils;
import com.wine.game.wine.entity.UserEntity;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * 
 *
 * @author zenofung
 * @email zenofung@qq.com
 * @date 2022-03-08 11:46:32
 */
public interface UserService extends IService<UserEntity> {

    PageUtils queryPage(Map<String, Object> params);

    UserVo getByIdUserVo(String unId);

    UserEntity saveUser(UserEntity user, HttpServletRequest httpServletRequest);

    LoginUser getLoginUser(String username);
}

