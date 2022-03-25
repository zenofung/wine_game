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

import com.wine.game.wine.entity.AttentionEntity;
import com.wine.game.wine.service.AttentionService;
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
@RequestMapping("wine/attention")
public class AttentionController {
    @Autowired
    private AttentionService attentionService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    //@RequiresPermissions("wine:attention:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = attentionService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    //@RequiresPermissions("wine:attention:info")
    public R info(@PathVariable("id") Integer id){
		AttentionEntity attention = attentionService.getById(id);

        return R.ok().put("attention", attention);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("wine:attention:save")
    public R save(@RequestBody AttentionEntity attention){
        List<AttentionEntity> list = attentionService.list(new QueryWrapper<AttentionEntity>().eq("me_id", attention.getMeId())
                .eq("follower_id", attention.getFollowerId()));
        if (list.size()>0){
            return R.ok().put("attention",list.get(0));
        }
         attentionService.save(attention);

        return R.ok().put("attention",attention);
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("wine:attention:update")
    public R update(@RequestBody AttentionEntity attention){
		attentionService.updateById(attention);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("${moduleNamez}:attention:delete")
    public R delete(@RequestBody AttentionEntity attention){
//		attentionService.removeByIds(Arrays.asList(ids));
        QueryWrapper<AttentionEntity> eq = new QueryWrapper<AttentionEntity>().eq("me_id", attention.getMeId()).eq("follower_id", attention.getFollowerId());
        List<AttentionEntity> list = attentionService.list(eq);
        list.stream().forEach(m->{
            attentionService.removeById(m);
        });
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete2")
    //@RequiresPermissions("${moduleNamez}:attention:delete")
    public R delete2(@RequestBody Integer[] ids){
        attentionService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
