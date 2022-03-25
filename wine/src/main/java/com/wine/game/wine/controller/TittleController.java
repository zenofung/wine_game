package com.wine.game.wine.controller;

import java.util.Arrays;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wine.game.wine.entity.TittleEntity;
import com.wine.game.wine.service.TittleService;
import com.zenofung.common.utils.PageUtils;
import com.zenofung.common.utils.R;



/**
 * 轮播图
 *
 * @author zenofung
 * @email zenofung@qq.com
 * @date 2022-03-25 16:57:04
 */
@RestController
@RequestMapping("wine/tittle")
public class TittleController {
    @Autowired
    private TittleService tittleService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    //@RequiresPermissions("wine:tittle:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = tittleService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    //@RequiresPermissions("wine:tittle:info")
    public R info(@PathVariable("id") Integer id){
		TittleEntity tittle = tittleService.getById(id);

        return R.ok().put("tittle", tittle);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("wine:tittle:save")
    public R save(@RequestBody TittleEntity tittle){
		tittleService.save(tittle);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("wine:tittle:update")
    public R update(@RequestBody TittleEntity tittle){
		tittleService.updateById(tittle);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("${moduleNamez}:tittle:delete")
    public R delete(@RequestBody Integer[] ids){
		tittleService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
