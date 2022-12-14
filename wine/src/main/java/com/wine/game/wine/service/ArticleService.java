package com.wine.game.wine.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wine.game.wine.entity.CommentEntity;
import com.zenofung.common.utils.PageUtils;
import com.wine.game.wine.entity.ArticleEntity;

import java.util.Map;

/**
 * 
 *
 * @author zenofung
 * @email zenofung@qq.com
 * @date 2022-03-08 11:46:32
 */
public interface ArticleService extends IService<ArticleEntity> {

    PageUtils queryPage(Map<String, Object> params);

    ArticleEntity getByIdAndContent(String id,String userId);

    CommentEntity getByIdCom(String id, String userId);
}

