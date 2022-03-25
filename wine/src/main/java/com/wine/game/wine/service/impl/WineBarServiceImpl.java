package com.wine.game.wine.service.impl;

import com.wine.game.wine.entity.WineAttentionEntity;
import com.wine.game.wine.service.WineAttentionService;
import com.wine.game.wine.service.WineService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zenofung.common.utils.PageUtils;
import com.zenofung.common.utils.Query;

import com.wine.game.wine.dao.WineBarDao;
import com.wine.game.wine.entity.WineBarEntity;
import com.wine.game.wine.service.WineBarService;

import javax.annotation.Resource;


@Service("wineBarService")
public class WineBarServiceImpl extends ServiceImpl<WineBarDao, WineBarEntity> implements WineBarService {

    @Resource
    private WineService wineService;
    @Resource
    private WineAttentionService wineAttentionService;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<WineBarEntity> page = this.page(
                new Query<WineBarEntity>().getPage(params),
                new QueryWrapper<WineBarEntity>()
        );
        page.getRecords().stream().forEach(m -> {
            //查询组局人
            m.setWineEntityList(wineService.getBarById(m.getId()));
            //查询是否关注
            List<WineAttentionEntity> list = wineAttentionService.list(new QueryWrapper<WineAttentionEntity>()
                    .eq("user_id", params.get("userId"))
                    .eq("wine_bar", m.getId()));
            m.setWineBarAttention(list.size()>0?true:false);
        });


        return new PageUtils(page);
    }

}