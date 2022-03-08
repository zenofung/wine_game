package com.wine.game.wine.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zenofung.common.utils.PageUtils;
import com.zenofung.common.utils.Query;

import com.wine.game.wine.dao.AttentionDao;
import com.wine.game.wine.entity.AttentionEntity;
import com.wine.game.wine.service.AttentionService;


@Service("attentionService")
public class AttentionServiceImpl extends ServiceImpl<AttentionDao, AttentionEntity> implements AttentionService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<AttentionEntity> page = this.page(
                new Query<AttentionEntity>().getPage(params),
                new QueryWrapper<AttentionEntity>()
        );

        return new PageUtils(page);
    }

}