package com.wine.game.wine.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zenofung.common.utils.PageUtils;
import com.wine.game.wine.entity.ImMessageEntity;

import java.util.Map;

/**
 * 
 *
 * @author zenofung
 * @email zenofung@qq.com
 * @date 2022-05-11 11:45:43
 */
public interface ImMessageService extends IService<ImMessageEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

