package com.wine.game.wine.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zenofung.common.utils.PageUtils;
import com.wine.game.wine.entity.WineAttentionEntity;

import java.util.Map;

/**
 * 关注酒吧
 *
 * @author zenofung
 * @email zenofung@qq.com
 * @date 2022-03-25 16:57:04
 */
public interface WineAttentionService extends IService<WineAttentionEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

