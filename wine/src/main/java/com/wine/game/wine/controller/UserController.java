package com.wine.game.wine.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wine.game.wine.common.LogInEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wine.game.wine.entity.UserEntity;
import com.wine.game.wine.service.UserService;
import com.zenofung.common.utils.PageUtils;
import com.zenofung.common.utils.R;

import javax.servlet.http.HttpServletRequest;


/**
 * 
 *
 * @author zenofung
 * @email zenofung@qq.com
 * @date 2022-03-08 11:46:32
 */
@RestController
@RequestMapping("wine/user")
public class UserController {
    @Autowired
    private UserService userService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    //@RequiresPermissions("wine:user:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = userService.queryPage(params);

        return R.ok().put("page", page);
    }




    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    //@RequiresPermissions("wine:user:info")
    public R info(@PathVariable("id") Integer id){
		UserEntity user = userService.getById(id);

        return R.ok().put("user", user);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("wine:user:save")
    public R save(@RequestBody UserEntity user,HttpServletRequest httpServletRequest){
        // wxMaUserServiceImpl.getUserInfo();
        List<UserEntity> userPhone = userService.list(new QueryWrapper<UserEntity>().eq("user_phone", user.getUserPhone()));
        if (userPhone.size()>0){
            return R.ok().put("status", LogInEnum.REPETITION.getState());
        }

        UserEntity userEntity = userService.saveUser(user, httpServletRequest );
        return R.ok().put("user",userEntity);
    }
    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("wine:user:update")
    public R update(@RequestBody UserEntity user){
		userService.updateById(user);
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("${moduleNamez}:user:delete")
    public R delete(@RequestBody String[] ids){
		userService.removeByIds(Arrays.asList(ids));
         return R.ok();
    }

}
