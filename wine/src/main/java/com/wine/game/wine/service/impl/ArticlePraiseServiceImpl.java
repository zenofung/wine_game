package com.wine.game.wine.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zenofung.common.utils.PageUtils;
import com.zenofung.common.utils.Query;

import com.wine.game.wine.dao.ArticlePraiseDao;
import com.wine.game.wine.entity.ArticlePraiseEntity;
import com.wine.game.wine.service.ArticlePraiseService;


@Service("articlePraiseService")
public class ArticlePraiseServiceImpl extends ServiceImpl<ArticlePraiseDao, ArticlePraiseEntity> implements ArticlePraiseService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<ArticlePraiseEntity> page = this.page(
                new Query<ArticlePraiseEntity>().getPage(params),
                new QueryWrapper<ArticlePraiseEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public void removeByUserId(ArticlePraiseEntity articlePraiseEntity) {
        this.remove(new QueryWrapper<ArticlePraiseEntity>()
                .eq("user_id", articlePraiseEntity.getUserId())
                .eq("article_id", articlePraiseEntity.getArticleId()));
    }


}