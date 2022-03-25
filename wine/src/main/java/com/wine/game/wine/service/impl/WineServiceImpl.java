package com.wine.game.wine.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.wine.game.wine.entity.UserEntity;
import com.wine.game.wine.entity.WineUsersEntity;
import com.wine.game.wine.service.UserService;
import com.wine.game.wine.service.WineBarService;
import com.wine.game.wine.service.WineUsersService;
import com.wine.game.wine.vo.UserVo;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
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
    @Autowired
    private WineBarService wineBarService;

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
            m.setUserVo(userService.getByIdUserVo(m.getUnId()));
            m.setUserEntityList(wineUsersService.getListByUser(m.getId()));
            if (!StringUtils.isEmpty(params.get("userId"))){
                WineUsersEntity wineUsersEntity =new WineUsersEntity();
                wineUsersEntity.setUserId(params.get("userId").toString());
                wineUsersEntity.setWineId(m.getId());
                m.setWineUserStatus(wineUsersService.WineUserStatus(wineUsersEntity));
            }
            m.setWineBarEntity(wineBarService.getById(m.getWineBarId()));

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

    @Override
    public WineEntity getByIdAll(String wineId) {
        WineEntity byId = this.getById(wineId);
        if (StringUtils.isEmpty(byId)){
            return null;
        }
        byId.setUserEntityList(wineUsersService.getListByUser(wineId));
        return byId;
    }

    @Override
    public List<UserVo> getBarById(Integer id) {
        List<WineEntity> wine_bar_id = this.list(new QueryWrapper<WineEntity>().eq("wine_bar_id", id));
        List<UserVo> list=new ArrayList<>();
        wine_bar_id.stream().forEach(m->{
            UserVo userVo=new UserVo();
            UserEntity byId = userService.getById(m.getUnId());
            BeanUtil.copyProperties(byId,userVo);
            list.add(userVo);
        });
        return list;
    }

}