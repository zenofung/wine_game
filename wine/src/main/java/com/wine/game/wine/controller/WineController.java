package com.wine.game.wine.controller;

import java.util.Arrays;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wine.game.wine.entity.WineEntity;
import com.wine.game.wine.service.WineService;
import com.zenofung.common.utils.PageUtils;
import com.zenofung.common.utils.R;



/**
 * 
 *
 * @author zenofung
 * @email zenofung@qq.com
 * @date 2022-03-08 11:46:32
 */
@RestController
@RequestMapping("wine/wine")
public class WineController {
    @Autowired
    private WineService wineService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    //@RequiresPermissions("wine:wine:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = wineService.queryPage(params);

        return R.ok().put("page", page);
    }
    /**
     * 列表
     */
    @RequestMapping("/list/{id}")
    //@RequiresPermissions("wine:wine:list")
    public R list(@RequestParam Map<String, Object> params,@PathVariable String id){
        PageUtils page = wineService.queryPage(params,id);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    //@RequiresPermissions("wine:wine:info")
    public R info(@PathVariable("id") String id){
		WineEntity wine = wineService.getById(id);

        return R.ok().put("wine", wine);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("wine:wine:save")
    public R save(@RequestBody WineEntity wine){
		wineService.save(wine);

        return R.ok().put("wine",wine);
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("wine:wine:update")
    public R update(@RequestBody WineEntity wine){
		wineService.updateById(wine);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("${moduleNamez}:wine:delete")
    public R delete(@RequestBody String[] ids){
		wineService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
