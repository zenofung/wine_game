package com.wine.game.wine.controller;

import java.util.Arrays;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wine.game.wine.entity.WineBarEntity;
import com.wine.game.wine.service.WineBarService;
import com.zenofung.common.utils.PageUtils;
import com.zenofung.common.utils.R;



/**
 * 酒吧
 *
 * @author zenofung
 * @email zenofung@qq.com
 * @date 2022-03-25 16:57:04
 */
@RestController
@RequestMapping("wine/winebar")
public class WineBarController {
    @Autowired
    private WineBarService wineBarService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    //@RequiresPermissions("wine:winebar:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = wineBarService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    //@RequiresPermissions("wine:winebar:info")
    public R info(@PathVariable("id") Integer id){
		WineBarEntity wineBar = wineBarService.getById(id);

        return R.ok().put("wineBar", wineBar);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("wine:winebar:save")
    public R save(@RequestBody WineBarEntity wineBar){
		wineBarService.save(wineBar);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("wine:winebar:update")
    public R update(@RequestBody WineBarEntity wineBar){
		wineBarService.updateById(wineBar);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("${moduleNamez}:winebar:delete")
    public R delete(@RequestBody Integer[] ids){
		wineBarService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
