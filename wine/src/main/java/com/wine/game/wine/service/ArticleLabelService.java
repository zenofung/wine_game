package com.wine.game.wine.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zenofung.common.utils.PageUtils;
import com.wine.game.wine.entity.ArticleLabelEntity;

import java.util.List;
import java.util.Map;

/**
 * 标签关联文章
 *
 * @author zenofung
 * @email zenofung@qq.com
 * @date 2022-03-11 16:57:36
 */
public interface ArticleLabelService extends IService<ArticleLabelEntity> {

    PageUtils queryPage(Map<String, Object> params);

    List<ArticleLabelEntity> listByArticleId(Integer articleId);

}

