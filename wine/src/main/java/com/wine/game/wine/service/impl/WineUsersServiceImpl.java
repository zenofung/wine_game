package com.wine.game.wine.service.impl;

import com.wine.game.wine.entity.WineUsersReadyEntity;
import com.wine.game.wine.service.UserService;
import com.wine.game.wine.service.WineUsersReadyService;
import com.wine.game.wine.vo.UserVo;
import com.wine.game.wine.vo.WineUserVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zenofung.common.utils.PageUtils;
import com.zenofung.common.utils.Query;

import com.wine.game.wine.dao.WineUsersDao;
import com.wine.game.wine.entity.WineUsersEntity;
import com.wine.game.wine.service.WineUsersService;


@Service("wineUsersService")
public class WineUsersServiceImpl extends ServiceImpl<WineUsersDao, WineUsersEntity> implements WineUsersService {

    @Autowired
    private UserService userService;

    @Autowired
    private WineUsersReadyService wineUsersReadyService;


    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<WineUsersEntity> page = this.page(
                new Query<WineUsersEntity>().getPage(params),
                new QueryWrapper<WineUsersEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public WineUserVo getListByWine(String wineId,String userId) {
        List<WineUsersEntity> wine_id = this.list(new QueryWrapper<WineUsersEntity>().eq("wine_id", wineId));
        WineUserVo wineUserVo=new WineUserVo();

        List<UserVo> collect = wine_id.stream().map(m -> {
            UserVo userVo = new UserVo();
            BeanUtils.copyProperties(userService.getById(m.getUserId()), userVo);
            return userVo;
        }).collect(Collectors.toList());
        wineUserVo.setUserVo(collect);

        List<WineUsersReadyEntity> wine_id1 = this.wineUsersReadyService.list(new QueryWrapper<WineUsersReadyEntity>().eq("wine_id", wineId));
        List<UserVo> collect1 = wine_id1.stream().map(m -> {
            UserVo userVo = new UserVo();
            BeanUtils.copyProperties(userService.getById(m.getUserId()), userVo);
            return userVo;
        }).collect(Collectors.toList());
        List<WineUsersReadyEntity> wine_id2 = this.wineUsersReadyService.list(new QueryWrapper<WineUsersReadyEntity>().eq("wine_id", wineId).eq("user_id",userId));
        wineUserVo.setMeStatus(wine_id2.size() > 0 ? true:false);
        wineUserVo.setUserReady(collect1);
        return wineUserVo;
    }

    @Override
    public List<UserVo> getListByUser(String wineId) {
        List<WineUsersEntity> wine_id = this.list(new QueryWrapper<WineUsersEntity>().eq("wine_id", wineId));
        List list=new ArrayList();
        wine_id.stream().forEach(m->{
            UserVo userVo=new UserVo();
            BeanUtils.copyProperties(userService.getById(m.getUserId()),userVo);
            list.add(userVo);
        });
        return list;
    }

    @Override
    public boolean WineUserStatus(WineUsersEntity wineUsers) {
        List<WineUsersEntity> list = this.list(new QueryWrapper<WineUsersEntity>().eq("wine_id", wineUsers.getWineId()).eq("user_id", wineUsers.getUserId()));
        return list.size()>0? true:false;
    }

}