package com.wine.game.wine.controller;

import java.util.Arrays;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wine.game.wine.entity.ImMessageEntity;
import com.wine.game.wine.service.ImMessageService;
import com.zenofung.common.utils.PageUtils;
import com.zenofung.common.utils.R;



/**
 * 
 *
 * @author zenofung
 * @email zenofung@qq.com
 * @date 2022-05-11 11:45:43
 */
@RestController
@RequestMapping("wine/immessage")
public class ImMessageController {
    @Autowired
    private ImMessageService imMessageService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    //@RequiresPermissions("wine:immessage:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = imMessageService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{magId}")
    //@RequiresPermissions("wine:immessage:info")
    public R info(@PathVariable("magId") String magId){
		ImMessageEntity imMessage = imMessageService.getById(magId);

        return R.ok().put("imMessage", imMessage);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("wine:immessage:save")
    public R save(@RequestBody ImMessageEntity imMessage){
		imMessageService.save(imMessage);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("wine:immessage:update")
    public R update(@RequestBody ImMessageEntity imMessage){
		imMessageService.updateById(imMessage);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("${moduleNamez}:immessage:delete")
    public R delete(@RequestBody String[] magIds){
		imMessageService.removeByIds(Arrays.asList(magIds));

        return R.ok();
    }

}
