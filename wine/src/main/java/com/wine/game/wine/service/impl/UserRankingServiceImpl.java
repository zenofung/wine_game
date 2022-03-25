package com.wine.game.wine.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zenofung.common.utils.PageUtils;
import com.zenofung.common.utils.Query;

import com.wine.game.wine.dao.UserRankingDao;
import com.wine.game.wine.entity.UserRankingEntity;
import com.wine.game.wine.service.UserRankingService;


@Service("userRankingService")
public class UserRankingServiceImpl extends ServiceImpl<UserRankingDao, UserRankingEntity> implements UserRankingService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<UserRankingEntity> page = this.page(
                new Query<UserRankingEntity>().getPage(params),
                new QueryWrapper<UserRankingEntity>()
        );

        return new PageUtils(page);
    }

}