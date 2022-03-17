package com.wine.game.wine.service.impl;

import com.wine.game.wine.entity.UserEntity;
import com.wine.game.wine.entity.WineUsersEntity;
import com.wine.game.wine.service.UserService;
import com.wine.game.wine.service.WineUsersService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private WineUsersService wineUsersService;

    @Autowired
    private UserService userService;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<WineEntity> page = this.page(
                new Query<WineEntity>().getPage(params),
                new QueryWrapper<WineEntity>()
        );


        getAllWine(params, page);

        return new PageUtils(page);
    }

    private void getAllWine(Map<String, Object> params, IPage<WineEntity> page) {
        page.getRecords().stream().forEach(m->{
            m.setUserEntity(userService.getById(m.getUnId()));
            m.setUserEntityList(wineUsersService.getListByWine(m.getId()));
            if (!StringUtils.isEmpty(params.get("userId"))){
                WineUsersEntity wineUsersEntity =new WineUsersEntity();
                wineUsersEntity.setUserId(params.get("userId").toString());
                wineUsersEntity.setWineId(m.getId());
                m.setWineUserStatus(wineUsersService.WineUserStatus(wineUsersEntity));
            }
        });
    }

    @Override
    public PageUtils queryPage(Map<String, Object> params, String id) {
        QueryWrapper<WineEntity> wineEntityQueryWrapper = new QueryWrapper<>();
        if (!StringUtils.isEmpty(id)){
            wineEntityQueryWrapper.eq("id",id);
        }
        IPage<WineEntity> page = this.page(new Query<WineEntity>().getPage(params),
                wineEntityQueryWrapper);
        getAllWine(params, page);

        return new PageUtils(page);


    }

}