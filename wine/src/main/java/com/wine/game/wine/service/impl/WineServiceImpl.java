package com.wine.game.wine.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zenofung.common.utils.PageUtils;
import com.zenofung.common.utils.Query;

import com.wine.game.wine.dao.WineDao;
import com.wine.game.wine.entity.WineEntity;
import com.wine.game.wine.service.WineService;
import org.springframework.util.StringUtils;


@Service("wineService")
public class WineServiceImpl extends ServiceImpl<WineDao, WineEntity> implements WineService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<WineEntity> page = this.page(
                new Query<WineEntity>().getPage(params),
                new QueryWrapper<WineEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public PageUtils queryPage(Map<String, Object> params, String id) {
        QueryWrapper<WineEntity> wineEntityQueryWrapper = new QueryWrapper<>();
        if (!StringUtils.isEmpty(id)){
            wineEntityQueryWrapper.eq("id",id);
        }
        IPage<WineEntity> page = this.page(new Query<WineEntity>().getPage(params),
                wineEntityQueryWrapper);
        return new PageUtils(page);


    }

}