package com.wine.game.wine.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zenofung.common.utils.PageUtils;
import com.zenofung.common.utils.Query;

import com.wine.game.wine.dao.WineUsersReadyDao;
import com.wine.game.wine.entity.WineUsersReadyEntity;
import com.wine.game.wine.service.WineUsersReadyService;


@Service("wineUsersReadyService")
public class WineUsersReadyServiceImpl extends ServiceImpl<WineUsersReadyDao, WineUsersReadyEntity> implements WineUsersReadyService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<WineUsersReadyEntity> page = this.page(
                new Query<WineUsersReadyEntity>().getPage(params),
                new QueryWrapper<WineUsersReadyEntity>()
        );

        return new PageUtils(page);
    }

}