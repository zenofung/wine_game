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

import com.wine.game.wine.entity.ArticleLabelEntity;
import com.wine.game.wine.service.ArticleLabelService;
import com.zenofung.common.utils.PageUtils;
import com.zenofung.common.utils.R;



/**
 * 标签关联文章
 *
 * @author zenofung
 * @email zenofung@qq.com
 * @date 2022-03-11 16:57:36
 */
@RestController
@RequestMapping("wine/articlelabel")
public class ArticleLabelController {
    @Autowired
    private ArticleLabelService articleLabelService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    //@RequiresPermissions("wine:articlelabel:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = articleLabelService.queryPage(params);

        return R.ok().put("page", page);
    }
    /**
     * 列表
     */
    @RequestMapping("/list/{articleId}")
    //@RequiresPermissions("wine:articlepraise:list")
    public R list(@PathVariable String articleId){
        List<ArticleLabelEntity> articleId1 = articleLabelService.listByArticleId(articleId);
        return R.ok().put("art",articleId1);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    //@RequiresPermissions("wine:articlelabel:info")
    public R info(@PathVariable("id") Integer id){
		ArticleLabelEntity articleLabel = articleLabelService.getById(id);

        return R.ok().put("articleLabel", articleLabel);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("wine:articlelabel:save")
    public R save(@RequestBody ArticleLabelEntity articleLabel){
		articleLabelService.save(articleLabel);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("wine:articlelabel:update")
    public R update(@RequestBody ArticleLabelEntity articleLabel){
		articleLabelService.updateById(articleLabel);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("${moduleNamez}:articlelabel:delete")
    public R delete(@RequestBody Integer[] ids){
		articleLabelService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
