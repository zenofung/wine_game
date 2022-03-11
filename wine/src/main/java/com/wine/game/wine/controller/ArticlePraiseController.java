package com.wine.game.wine.controller;

import java.util.Arrays;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wine.game.wine.entity.ArticlePraiseEntity;
import com.wine.game.wine.service.ArticlePraiseService;
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
@RequestMapping("wine/articlepraise")
public class ArticlePraiseController {
    @Autowired
    private ArticlePraiseService articlePraiseService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    //@RequiresPermissions("wine:articlepraise:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = articlePraiseService.queryPage(params);

        return R.ok().put("page", page);
    }




    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    //@RequiresPermissions("wine:articlepraise:info")
    public R info(@PathVariable("id") Integer id){
		ArticlePraiseEntity articlePraise = articlePraiseService.getById(id);

        return R.ok().put("articlePraise", articlePraise);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("wine:articlepraise:save")
    public R save(@RequestBody ArticlePraiseEntity articlePraise){
		articlePraiseService.save(articlePraise);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("wine:articlepraise:update")
    public R update(@RequestBody ArticlePraiseEntity articlePraise){
		articlePraiseService.updateById(articlePraise);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("${moduleNamez}:articlepraise:delete")
    public R delete(@RequestBody ArticlePraiseEntity articlePraiseEntity){
		articlePraiseService.removeByUserId(articlePraiseEntity);

        return R.ok();
    }



}
