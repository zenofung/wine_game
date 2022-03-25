package com.wine.game.wine.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wine.game.wine.entity.UserRankingEntity;
import com.wine.game.wine.service.UserRankingService;
import com.zenofung.common.utils.PageUtils;
import com.zenofung.common.utils.R;



/**
 * 用户活跃排行
 *
 * @author zenofung
 * @email zenofung@qq.com
 * @date 2022-03-25 16:57:04
 */
@RestController
@RequestMapping("wine/userranking")
public class UserRankingController {
    @Autowired
    private UserRankingService userRankingService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    //@RequiresPermissions("wine:userranking:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = userRankingService.queryPage(params);

        return R.ok().put("page", page);
    }

    /**
     * 排行榜
     */
    @RequestMapping("/listRanking/{type}")
    //@RequiresPermissions("wine:userranking:list")
    public R listRanking(@PathVariable("type") String type){
//        PageUtils page = userRankingService.queryPage(params);
        List<UserRankingEntity> userRankingEntities= userRankingService.getListRanking(type);
        return R.ok().put("page", userRankingEntities);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    //@RequiresPermissions("wine:userranking:info")
    public R info(@PathVariable("id") Integer id){
		UserRankingEntity userRanking = userRankingService.getById(id);

        return R.ok().put("userRanking", userRanking);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("wine:userranking:save")
    public R save(@RequestBody UserRankingEntity userRanking){
		userRankingService.save(userRanking);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("wine:userranking:update")
    public R update(@RequestBody UserRankingEntity userRanking){
		userRankingService.updateById(userRanking);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("${moduleNamez}:userranking:delete")
    public R delete(@RequestBody Integer[] ids){
		userRankingService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
