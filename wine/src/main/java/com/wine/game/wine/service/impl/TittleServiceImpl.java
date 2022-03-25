package com.wine.game.wine.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zenofung.common.utils.PageUtils;
import com.zenofung.common.utils.Query;

import com.wine.game.wine.dao.TittleDao;
import com.wine.game.wine.entity.TittleEntity;
import com.wine.game.wine.service.TittleService;


@Service("tittleService")
public class TittleServiceImpl extends ServiceImpl<TittleDao, TittleEntity> implements TittleService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<TittleEntity> page = this.page(
                new Query<TittleEntity>().getPage(params),
                new QueryWrapper<TittleEntity>()
        );

        return new PageUtils(page);
    }

}