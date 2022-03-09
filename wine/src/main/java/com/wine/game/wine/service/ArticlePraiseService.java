package com.wine.game.wine.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zenofung.common.utils.PageUtils;
import com.wine.game.wine.entity.ArticlePraiseEntity;

import java.util.Map;

/**
 * 
 *
 * @author zenofung
 * @email zenofung@qq.com
 * @date 2022-03-09 17:35:50
 */
public interface ArticlePraiseService extends IService<ArticlePraiseEntity> {

    PageUtils queryPage(Map<String, Object> params);


    void removeByUserId(ArticlePraiseEntity articlePraiseEntity);

}

