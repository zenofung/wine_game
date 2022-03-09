package com.wine.game.wine.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zenofung.common.utils.PageUtils;
import com.zenofung.common.utils.Query;

import com.wine.game.wine.dao.CommentPraiseDao;
import com.wine.game.wine.entity.CommentPraiseEntity;
import com.wine.game.wine.service.CommentPraiseService;


@Service("commentPraiseService")
public class CommentPraiseServiceImpl extends ServiceImpl<CommentPraiseDao, CommentPraiseEntity> implements CommentPraiseService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<CommentPraiseEntity> page = this.page(
                new Query<CommentPraiseEntity>().getPage(params),
                new QueryWrapper<CommentPraiseEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public void removeByUserId(CommentPraiseEntity commentPraiseEntity) {
        this.remove(new QueryWrapper<CommentPraiseEntity>()
                .eq("user_id",commentPraiseEntity.getUserId())
                .eq("comment_id",commentPraiseEntity.getCommentId()));
    }

}