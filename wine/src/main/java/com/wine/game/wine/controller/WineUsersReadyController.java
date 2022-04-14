package com.wine.game.wine.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wine.game.wine.entity.WineUsersReadyEntity;
import com.wine.game.wine.service.WineUsersReadyService;
import com.zenofung.common.utils.PageUtils;
import com.zenofung.common.utils.R;



/**
 * 
 *
 * @author zenofung
 * @email zenofung@qq.com
 * @date 2022-04-03 22:10:27
 */
@RestController
@RequestMapping("wine/wineusersready")
public class WineUsersReadyController {
    @Autowired
    private WineUsersReadyService wineUsersReadyService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    //@RequiresPermissions("wine:wineusersready:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = wineUsersReadyService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    //@RequiresPermissions("wine:wineusersready:info")
    public R info(@PathVariable("id") Integer id){
		WineUsersReadyEntity wineUsersReady = wineUsersReadyService.getById(id);

        return R.ok().put("wineUsersReady", wineUsersReady);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("wine:wineusersready:save")
    public R save(@RequestBody WineUsersReadyEntity wineUsersReady){
        List<WineUsersReadyEntity> list = wineUsersReadyService.list(new QueryWrapper<WineUsersReadyEntity>().eq("wine_id", wineUsersReady.getWineId())
                .eq("user_id", wineUsersReady.getUserId()));
        if (list.size()<=0){
            wineUsersReadyService.save(wineUsersReady);
        }

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("wine:wineusersready:update")
    public R update(@RequestBody WineUsersReadyEntity wineUsersReady){
		wineUsersReadyService.updateById(wineUsersReady);

        return R.ok();
    }

    @RequestMapping("/deleteByUser")
    //@RequiresPermissions("wine:wineusersready:update")
    public R deleteByUser(@RequestBody WineUsersReadyEntity wineUsersReady){
        List<WineUsersReadyEntity> list = wineUsersReadyService.list(new QueryWrapper<WineUsersReadyEntity>().eq("wine_id", wineUsersReady.getWineId())
                .eq("user_id", wineUsersReady.getUserId()));
        list.stream().forEach(m->{
            wineUsersReadyService.removeById(m.getId());
        });
        return R.ok();
    }


    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("${moduleNamez}:wineusersready:delete")
    public R delete(@RequestBody Integer[] ids){
		wineUsersReadyService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
