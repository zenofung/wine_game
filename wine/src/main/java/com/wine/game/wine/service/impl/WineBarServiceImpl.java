package com.wine.game.wine.service.impl;

import com.wine.game.wine.entity.UserEntity;
import com.wine.game.wine.entity.WineAttentionEntity;
import com.wine.game.wine.service.UserService;
import com.wine.game.wine.service.WineAttentionService;
import com.wine.game.wine.service.WineService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
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
import org.springframework.util.StringUtils;

import javax.annotation.Resource;


@Service("wineBarService")
public class WineBarServiceImpl extends ServiceImpl<WineBarDao, WineBarEntity> implements WineBarService {

    @Resource
    private WineService wineService;
    @Resource
    private WineAttentionService wineAttentionService;
    @Resource
    private WineBarDao wineBarDao;
    @Resource
    private UserService userService;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        QueryWrapper<WineBarEntity> wineBarEntityQueryWrapper = new QueryWrapper<>();
        if (!StringUtils.isEmpty(params.get("distanceUm"))){
            wineBarEntityQueryWrapper.having("distance_um <="+params.get("distanceUm").toString());
        }
        if (!StringUtils.isEmpty(params.get("juli"))){
            wineBarEntityQueryWrapper.orderByAsc("distance_um");
        }
        if (!StringUtils.isEmpty(params.get("haoping"))){
            wineBarEntityQueryWrapper.orderByDesc("bar_favorability");
        }

        if (!StringUtils.isEmpty(params.get("xiaoliang"))){
            wineBarEntityQueryWrapper.orderByDesc("bar_sales");
        }

        UserEntity userId = userService.getById(params.get("userId").toString());
        if (StringUtils.isEmpty(userId)){
            throw new RuntimeException("没有用户");
        }
        //mybatis-plus 自定义分页
        IPage<WineBarEntity> page=wineBarDao.selectBarPage2(new Query<WineBarEntity>().getPage(params),wineBarEntityQueryWrapper,userId.getUserLat(),userId.getUserLong());
        page.getRecords().stream().forEach(m -> {
            m.setDistanceUm(m.getDistanceUm()/1000);
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

    @Override
    public List<WineBarEntity> queryPageBar(Map<String, Object> params) {
        UserEntity userId = userService.getById(params.get("userId").toString());
        if (StringUtils.isEmpty(userId)){
            throw new RuntimeException("没有用户");
        }
        QueryWrapper<WineBarEntity> wineBarEntityQueryWrapper = new QueryWrapper<>();
        wineBarEntityQueryWrapper.having("distance_um <="+params.get("juli").toString());
        List<WineBarEntity> list=  wineBarDao.selectListBar(wineBarEntityQueryWrapper,userId.getUserLat(),userId.getUserLong());
        return list;
    }


    @Override
    public WineBarEntity queryPageBar2(Map<String, Object> params, Integer barId) {
        UserEntity userId = userService.getById(params.get("userId").toString());
        if (StringUtils.isEmpty(userId)){
            throw new RuntimeException("没有用户");
        }
        QueryWrapper<WineBarEntity> wineBarEntityQueryWrapper = new QueryWrapper<>();
        wineBarEntityQueryWrapper.eq("id",barId);
        WineBarEntity list=  wineBarDao.selectListBar(wineBarEntityQueryWrapper,userId.getUserLat(),userId.getUserLong()).get(0);
        return list;

    }

}