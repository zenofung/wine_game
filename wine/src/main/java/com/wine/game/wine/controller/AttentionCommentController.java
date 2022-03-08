package com.wine.game.wine.controller;

import java.util.Arrays;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wine.game.wine.entity.AttentionCommentEntity;
import com.wine.game.wine.service.AttentionCommentService;
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
@RequestMapping("wine/attentioncomment")
public class AttentionCommentController {
    @Autowired
    private AttentionCommentService attentionCommentService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    //@RequiresPermissions("wine:attentioncomment:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = attentionCommentService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    //@RequiresPermissions("wine:attentioncomment:info")
    public R info(@PathVariable("id") Integer id){
		AttentionCommentEntity attentionComment = attentionCommentService.getById(id);

        return R.ok().put("attentionComment", attentionComment);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("wine:attentioncomment:save")
    public R save(@RequestBody AttentionCommentEntity attentionComment){
		attentionCommentService.save(attentionComment);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("wine:attentioncomment:update")
    public R update(@RequestBody AttentionCommentEntity attentionComment){
		attentionCommentService.updateById(attentionComment);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("${moduleNamez}:attentioncomment:delete")
    public R delete(@RequestBody Integer[] ids){
		attentionCommentService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
