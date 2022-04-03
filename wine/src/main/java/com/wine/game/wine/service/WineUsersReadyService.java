package com.wine.game.wine.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zenofung.common.utils.PageUtils;
import com.wine.game.wine.entity.WineUsersReadyEntity;

import java.util.Map;

/**
 * 
 *
 * @author zenofung
 * @email zenofung@qq.com
 * @date 2022-04-03 22:10:27
 */
public interface WineUsersReadyService extends IService<WineUsersReadyEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

