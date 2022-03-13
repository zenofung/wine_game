package com.wine.game.wine.service.impl;

import com.wine.game.wine.service.LabelService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zenofung.common.utils.PageUtils;
import com.zenofung.common.utils.Query;

import com.wine.game.wine.dao.ArticleLabelDao;
import com.wine.game.wine.entity.ArticleLabelEntity;
import com.wine.game.wine.service.ArticleLabelService;

import javax.annotation.Resource;


@Service("articleLabelService")
public class ArticleLabelServiceImpl extends ServiceImpl<ArticleLabelDao, ArticleLabelEntity> implements ArticleLabelService {

    @Resource
    private LabelService labelService;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<ArticleLabelEntity> page = this.page(
                new Query<ArticleLabelEntity>().getPage(params),
                new QueryWrapper<ArticleLabelEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public List<ArticleLabelEntity> listByArticleId(String articleId) {
        List<ArticleLabelEntity> article_id = this.list(new QueryWrapper<ArticleLabelEntity>().eq("article_id", articleId));
        article_id.stream().forEach(m->{
            m.setLabelEntity(labelService.getById(m.getLabelId()));
        });
        return article_id;
    }

}