package com.wine.game.wine.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zenofung.common.utils.PageUtils;
import com.zenofung.common.utils.Query;

import com.wine.game.wine.dao.LabelDao;
import com.wine.game.wine.entity.LabelEntity;
import com.wine.game.wine.service.LabelService;


@Service("labelService")
public class LabelServiceImpl extends ServiceImpl<LabelDao, LabelEntity> implements LabelService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<LabelEntity> page = this.page(
                new Query<LabelEntity>().getPage(params),
                new QueryWrapper<LabelEntity>()
        );

        return new PageUtils(page);
    }

}