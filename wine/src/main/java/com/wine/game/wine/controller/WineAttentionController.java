package com.wine.game.wine.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wine.game.wine.entity.WineAttentionEntity;
import com.wine.game.wine.service.WineAttentionService;
import com.zenofung.common.utils.PageUtils;
import com.zenofung.common.utils.R;



/**
 * 关注酒吧
 *
 * @author zenofung
 * @email zenofung@qq.com
 * @date 2022-03-25 16:57:04
 */
@RestController
@RequestMapping("wine/wineattention")
public class WineAttentionController {
    @Autowired
    private WineAttentionService wineAttentionService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    //@RequiresPermissions("wine:wineattention:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = wineAttentionService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    //@RequiresPermissions("wine:wineattention:info")
    public R info(@PathVariable("id") Integer id){
		WineAttentionEntity wineAttention = wineAttentionService.getById(id);

        return R.ok().put("wineAttention", wineAttention);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("wine:wineattention:save")
    public R save(@RequestBody WineAttentionEntity wineAttention){
        List<WineAttentionEntity> list = wineAttentionService.list(new QueryWrapper<WineAttentionEntity>()
                .eq("user_id", wineAttention.getUserId())
                .eq("wine_bar", wineAttention.getWineBar()));
        if (list.size()>0){
            return R.ok();
        }
		wineAttentionService.save(wineAttention);
        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("wine:wineattention:update")
    public R update(@RequestBody WineAttentionEntity wineAttention){
        wineAttentionService.updateById(wineAttention);

        return R.ok();
    }
    /**
     * 取消关注
     */
    @RequestMapping("/deleteByAttention")
    //@RequiresPermissions("wine:wineattention:update")
    public R deleteByAttention(@RequestBody WineAttentionEntity wineAttention){
        List<WineAttentionEntity> list = wineAttentionService.list(new QueryWrapper<WineAttentionEntity>()
                .eq("user_id", wineAttention.getUserId())
                .eq("wine_bar", wineAttention.getWineBar()));
        if (!StringUtils.isEmpty(list)){
            list.forEach(m->{
                wineAttentionService.removeById(m.getId());
            });

        }
        return R.ok();
    }
    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("${moduleNamez}:wineattention:delete")
    public R delete(@RequestBody Integer[] ids){
		wineAttentionService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
