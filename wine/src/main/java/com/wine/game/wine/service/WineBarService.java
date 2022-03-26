package com.wine.game.wine.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zenofung.common.utils.PageUtils;
import com.wine.game.wine.entity.WineBarEntity;

import java.util.List;
import java.util.Map;

/**
 * 酒吧
 *
 * @author zenofung
 * @email zenofung@qq.com
 * @date 2022-03-25 16:57:04
 */
public interface WineBarService extends IService<WineBarEntity> {

    PageUtils queryPage(Map<String, Object> params);

    List<WineBarEntity> queryPageBar(Map<String, Object> params);


    WineBarEntity queryPageBar2(Map<String, Object> params,Integer barId);

}

