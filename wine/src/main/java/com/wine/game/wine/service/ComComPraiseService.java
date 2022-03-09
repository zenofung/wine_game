package com.wine.game.wine.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wine.game.wine.entity.ComComEntity;
import com.zenofung.common.utils.PageUtils;
import com.wine.game.wine.entity.ComComPraiseEntity;

import java.util.Map;

/**
 * 
 *
 * @author zenofung
 * @email zenofung@qq.com
 * @date 2022-03-09 17:35:50
 */
public interface ComComPraiseService extends IService<ComComPraiseEntity> {

    PageUtils queryPage(Map<String, Object> params);

    void removeByUserId(ComComPraiseEntity comComPraiseEntity);

}

