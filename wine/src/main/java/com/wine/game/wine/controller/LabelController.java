package com.wine.game.wine.controller;

import java.util.Arrays;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wine.game.wine.entity.LabelEntity;
import com.wine.game.wine.service.LabelService;
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
@RequestMapping("wine/label")
public class LabelController {
    @Autowired
    private LabelService labelService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    //@RequiresPermissions("wine:label:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = labelService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    //@RequiresPermissions("wine:label:info")
    public R info(@PathVariable("id") Integer id){
		LabelEntity label = labelService.getById(id);

        return R.ok().put("label", label);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("wine:label:save")
    public R save(@RequestBody LabelEntity label){
		labelService.save(label);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("wine:label:update")
    public R update(@RequestBody LabelEntity label){
		labelService.updateById(label);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("${moduleNamez}:label:delete")
    public R delete(@RequestBody Integer[] ids){
		labelService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
