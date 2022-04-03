package com.wine.game.wine.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.wine.game.wine.vo.SearchVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wine.game.wine.entity.SearchEntity;
import com.wine.game.wine.service.SearchService;
import com.zenofung.common.utils.PageUtils;
import com.zenofung.common.utils.R;



/**
 * 
 *
 * @author zenofung
 * @email zenofung@qq.com
 * @date 2022-04-03 20:21:42
 */
@RestController
@RequestMapping("wine/search")
public class SearchController {
    @Autowired
    private SearchService searchService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    //@RequiresPermissions("wine:search:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = searchService.queryPage(params);

        return R.ok().put("page", page);
    }

    @RequestMapping("/listKeyWord")
    //@RequiresPermissions("wine:search:list")
    public R listKeyWord(@RequestParam Map<String, Object> params){
//        PageUtils page = searchService.queryPage(params);
       List<String> strings= searchService.getListKeyWord();
        return R.ok().put("page", strings);
    }
    //获取酒馆 直接请求酒馆接口


    /**
     * 用户搜索
     */
    @RequestMapping("/listSearch")
    //@RequiresPermissions("wine:search:list")
    public R list(@RequestParam String key){
          SearchVo searchVos= searchService.getListSearch(key);

        return R.ok().put("page", searchVos);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    //@RequiresPermissions("wine:search:info")
    public R info(@PathVariable("id") Integer id){
		SearchEntity search = searchService.getById(id);

        return R.ok().put("search", search);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("wine:search:save")
    public R save(@RequestBody SearchEntity search){
		searchService.save(search);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("wine:search:update")
    public R update(@RequestBody SearchEntity search){
		searchService.updateById(search);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("${moduleNamez}:search:delete")
    public R delete(@RequestBody Integer[] ids){
		searchService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
