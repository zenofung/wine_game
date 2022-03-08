package com.wine.game.wine.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zenofung.common.utils.PageUtils;
import com.zenofung.common.utils.Query;

import com.wine.game.wine.dao.AttentionCommentDao;
import com.wine.game.wine.entity.AttentionCommentEntity;
import com.wine.game.wine.service.AttentionCommentService;


@Service("attentionCommentService")
public class AttentionCommentServiceImpl extends ServiceImpl<AttentionCommentDao, AttentionCommentEntity> implements AttentionCommentService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<AttentionCommentEntity> page = this.page(
                new Query<AttentionCommentEntity>().getPage(params),
                new QueryWrapper<AttentionCommentEntity>()
        );

        return new PageUtils(page);
    }

}