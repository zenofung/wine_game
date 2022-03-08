package com.wine.game.wine.controller;

import java.util.Arrays;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wine.game.wine.entity.ComComEntity;
import com.wine.game.wine.service.ComComService;
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
@RequestMapping("wine/comcom")
public class ComComController {
    @Autowired
    private ComComService comComService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    //@RequiresPermissions("wine:comcom:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = comComService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    //@RequiresPermissions("wine:comcom:info")
    public R info(@PathVariable("id") Integer id){
		ComComEntity comCom = comComService.getById(id);

        return R.ok().put("comCom", comCom);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("wine:comcom:save")
    public R save(@RequestBody ComComEntity comCom){
		comComService.save(comCom);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("wine:comcom:update")
    public R update(@RequestBody ComComEntity comCom){
		comComService.updateById(comCom);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("${moduleNamez}:comcom:delete")
    public R delete(@RequestBody Integer[] ids){
		comComService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
