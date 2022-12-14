package com.wine.game.wine.controller;

import java.util.Arrays;
import java.util.Map;

import com.wine.game.wine.entity.CommentEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wine.game.wine.entity.ArticleEntity;
import com.wine.game.wine.service.ArticleService;
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
@RequestMapping("wine/article")
public class ArticleController {
    @Autowired
    private ArticleService articleService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    //@RequiresPermissions("wine:article:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = articleService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}/{userId}")
    //@RequiresPermissions("wine:article:info")
    public R info(@PathVariable("id") String id,@PathVariable("userId") String userId){
		ArticleEntity article = articleService.getByIdAndContent(id,userId);
        return R.ok().put("article", article);
    }

    /**
     * 信息
     */
    @RequestMapping("/infoCom/{id}/{userId}")
    //@RequiresPermissions("wine:article:info")
    public R infoCom(@PathVariable("id") String id,@PathVariable("userId") String userId){
        CommentEntity article = articleService.getByIdCom(id,userId);
        return R.ok().put("comment", article);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("wine:article:save")
    public R save(@RequestBody ArticleEntity article){
		articleService.save(article);

        return R.ok().put("article",article);
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("wine:article:update")
    public R update(@RequestBody ArticleEntity article){
		articleService.updateById(article);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("${moduleNamez}:article:delete")
    public R delete(@RequestBody String[] ids){
		articleService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
