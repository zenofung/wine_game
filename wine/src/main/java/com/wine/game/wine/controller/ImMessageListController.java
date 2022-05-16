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

import com.wine.game.wine.entity.ImMessageListEntity;
import com.wine.game.wine.service.ImMessageListService;
import com.zenofung.common.utils.PageUtils;
import com.zenofung.common.utils.R;


/**
 * @author zenofung
 * @email zenofung@qq.com
 * @date 2022-05-11 11:45:43
 */
@RestController
@RequestMapping("wine/immessagelist")
public class ImMessageListController {
    @Autowired
    private ImMessageListService imMessageListService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    //@RequiresPermissions("wine:immessagelist:list")
    public R list(@RequestParam Map<String, Object> params) {
        PageUtils page = imMessageListService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    //@RequiresPermissions("wine:immessagelist:info")
    public R info(@PathVariable("id") String id, String userId) {
        ImMessageListEntity imMessageList = imMessageListService.getByIdAndUserVo(id, userId);

        return R.ok().put("imMessageList", imMessageList);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("wine:immessagelist:save")
    public R save(@RequestBody ImMessageListEntity imMessageList) {
        if (imMessageList.getFriendId().equals(imMessageList.getUserId())){
            throw  new RuntimeException("自己无法建立链接");
        }
        List<ImMessageListEntity> list = imMessageListService.list(new QueryWrapper<ImMessageListEntity>().eq("user_id", imMessageList.getUserId()).eq("friend_id", imMessageList.getFriendId()).or().eq("user_id", imMessageList.getFriendId()).eq("friend_id", imMessageList.getUserId()));
        if (list.size()<=0){
            imMessageListService.save(imMessageList);
            return R.ok().put("imMessageList",imMessageList);
        }else {
            return R.ok().put("imMessageList",list.get(0));
        }



    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("wine:immessagelist:update")
    public R update(@RequestBody ImMessageListEntity imMessageList) {
        imMessageListService.updateById(imMessageList);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("${moduleNamez}:immessagelist:delete")
    public R delete(@RequestBody Integer[] ids) {
        imMessageListService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
