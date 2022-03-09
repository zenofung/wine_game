package com.wine.game.wine.controller;

import java.util.Arrays;
import java.util.Map;

import com.wine.game.wine.entity.ComComEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wine.game.wine.entity.ComComPraiseEntity;
import com.wine.game.wine.service.ComComPraiseService;
import com.zenofung.common.utils.PageUtils;
import com.zenofung.common.utils.R;



/**
 * 
 *
 * @author zenofung
 * @email zenofung@qq.com
 * @date 2022-03-09 17:35:50
 */
@RestController
@RequestMapping("wine/comcompraise")
public class ComComPraiseController {
    @Autowired
    private ComComPraiseService comComPraiseService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    //@RequiresPermissions("wine:comcompraise:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = comComPraiseService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    //@RequiresPermissions("wine:comcompraise:info")
    public R info(@PathVariable("id") Integer id){
		ComComPraiseEntity comComPraise = comComPraiseService.getById(id);

        return R.ok().put("comComPraise", comComPraise);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("wine:comcompraise:save")
    public R save(@RequestBody ComComPraiseEntity comComPraise){
		comComPraiseService.save(comComPraise);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("wine:comcompraise:update")
    public R update(@RequestBody ComComPraiseEntity comComPraise){
		comComPraiseService.updateById(comComPraise);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("${moduleNamez}:comcompraise:delete")
    public R delete(@RequestBody ComComPraiseEntity comComPraiseEntity){
		comComPraiseService.removeByUserId(comComPraiseEntity);
        return R.ok();
    }



}
