package com.wine.game.wine.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wine.game.wine.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wine.game.wine.entity.WineUsersEntity;
import com.wine.game.wine.service.WineUsersService;
import com.zenofung.common.utils.PageUtils;
import com.zenofung.common.utils.R;



/**
 * 
 *
 * @author zenofung
 * @email zenofung@qq.com
 * @date 2022-03-17 17:20:49
 */
@RestController
@RequestMapping("wine/wineusers")
public class WineUsersController {
    @Autowired
    private WineUsersService wineUsersService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    //@RequiresPermissions("wine:wineusers:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = wineUsersService.queryPage(params);

        return R.ok().put("page", page);
    }

    /**
     * 列表
     */
    @RequestMapping("/listByWine")
    //@RequiresPermissions("wine:wineusers:list")
    public R listByWine(@RequestParam(value = "wineId")  String wineId){
        List<WineUsersEntity> wineId1 = wineUsersService.getListByWine(wineId);
        return R.ok().put("page", wineId1);
    }



    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    //@RequiresPermissions("wine:wineusers:info")
    public R info(@PathVariable("id") Integer id){
		WineUsersEntity wineUsers = wineUsersService.getById(id);

        return R.ok().put("wineUsers", wineUsers);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("wine:wineusers:save")
    public R save(@RequestBody WineUsersEntity wineUsers){
        boolean b = wineUsersService.WineUserStatus(wineUsers);
        if (b){
            return R.error("已加入");
        }
        wineUsersService.save(wineUsers);
        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("wine:wineusers:update")
    public R update(@RequestBody WineUsersEntity wineUsers){
		wineUsersService.updateById(wineUsers);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("${moduleNamez}:wineusers:delete")
    public R delete(@RequestBody Integer[] ids){
		wineUsersService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
