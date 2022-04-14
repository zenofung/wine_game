package com.wine.game.wine.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wine.game.wine.vo.SearchVo;
import com.zenofung.common.utils.PageUtils;
import com.wine.game.wine.entity.SearchEntity;

import java.util.List;
import java.util.Map;

/**
 * 
 *
 * @author zenofung
 * @email zenofung@qq.com
 * @date 2022-04-03 20:21:42
 */
public interface SearchService extends IService<SearchEntity> {

    PageUtils queryPage(Map<String, Object> params);

    List<String> getListKeyWord();

    SearchVo getListSearch(String key);
}

