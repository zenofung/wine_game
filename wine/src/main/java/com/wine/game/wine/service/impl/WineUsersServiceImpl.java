package com.wine.game.wine.service.impl;

import com.wine.game.wine.service.UserService;
import com.wine.game.wine.vo.UserVo;
import org.springframework.beans.BeanUtils;
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

import com.wine.game.wine.dao.WineUsersDao;
import com.wine.game.wine.entity.WineUsersEntity;
import com.wine.game.wine.service.WineUsersService;


@Service("wineUsersService")
public class WineUsersServiceImpl extends ServiceImpl<WineUsersDao, WineUsersEntity> implements WineUsersService {

    @Autowired
    private UserService userService;


    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<WineUsersEntity> page = this.page(
                new Query<WineUsersEntity>().getPage(params),
                new QueryWrapper<WineUsersEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public List<WineUsersEntity> getListByWine(String wineId) {
        List<WineUsersEntity> wine_id = this.list(new QueryWrapper<WineUsersEntity>().eq("wine_id", wineId));
        wine_id.stream().forEach(m->{
            UserVo userVo=new UserVo();
            BeanUtils.copyProperties(userService.getById(m.getUserId()),userVo);
            m.setUserVo(userVo);
        });
        return wine_id;
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