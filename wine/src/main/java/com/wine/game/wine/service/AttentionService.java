package com.wine.game.wine.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zenofung.common.utils.PageUtils;
import com.wine.game.wine.entity.AttentionEntity;

import java.util.Map;

/**
 * 
 *
 * @author zenofung
 * @email zenofung@qq.com
 * @date 2022-03-08 11:46:32
 */
public interface AttentionService extends IService<AttentionEntity> {

    PageUtils queryPage(Map<String, Object> params);

    AttentionEntity saveAttention(AttentionEntity attention);


}

