package com.wine.game.wine.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zenofung.common.utils.PageUtils;
import com.zenofung.common.utils.Query;

import com.wine.game.wine.dao.WineAttentionDao;
import com.wine.game.wine.entity.WineAttentionEntity;
import com.wine.game.wine.service.WineAttentionService;


@Service("wineAttentionService")
public class WineAttentionServiceImpl extends ServiceImpl<WineAttentionDao, WineAttentionEntity> implements WineAttentionService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<WineAttentionEntity> page = this.page(
                new Query<WineAttentionEntity>().getPage(params),
                new QueryWrapper<WineAttentionEntity>()
        );

        return new PageUtils(page);
    }

}