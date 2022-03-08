package com.wine.game.wine.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zenofung.common.utils.PageUtils;
import com.zenofung.common.utils.Query;

import com.wine.game.wine.dao.ComComDao;
import com.wine.game.wine.entity.ComComEntity;
import com.wine.game.wine.service.ComComService;


@Service("comComService")
public class ComComServiceImpl extends ServiceImpl<ComComDao, ComComEntity> implements ComComService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<ComComEntity> page = this.page(
                new Query<ComComEntity>().getPage(params),
                new QueryWrapper<ComComEntity>()
        );

        return new PageUtils(page);
    }

}