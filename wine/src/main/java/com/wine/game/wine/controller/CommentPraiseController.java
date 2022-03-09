package com.wine.game.wine.controller;

import java.util.Arrays;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wine.game.wine.entity.CommentPraiseEntity;
import com.wine.game.wine.service.CommentPraiseService;
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
@RequestMapping("wine/commentpraise")
public class CommentPraiseController {
    @Autowired
    private CommentPraiseService commentPraiseService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    //@RequiresPermissions("wine:commentpraise:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = commentPraiseService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    //@RequiresPermissions("wine:commentpraise:info")
    public R info(@PathVariable("id") Integer id){
		CommentPraiseEntity commentPraise = commentPraiseService.getById(id);

        return R.ok().put("commentPraise", commentPraise);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("wine:commentpraise:save")
    public R save(@RequestBody CommentPraiseEntity commentPraise){
		commentPraiseService.save(commentPraise);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("wine:commentpraise:update")
    public R update(@RequestBody CommentPraiseEntity commentPraise){
		commentPraiseService.updateById(commentPraise);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("${moduleNamez}:commentpraise:delete")
    public R delete(@RequestBody CommentPraiseEntity commentPraiseEntity){
		commentPraiseService.removeByUserId(commentPraiseEntity);

        return R.ok();
    }

}
