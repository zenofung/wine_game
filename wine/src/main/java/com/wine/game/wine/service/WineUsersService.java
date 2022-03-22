package com.wine.game.wine.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wine.game.wine.vo.UserVo;
import com.zenofung.common.utils.PageUtils;
import com.wine.game.wine.entity.WineUsersEntity;

import java.util.List;
import java.util.Map;

/**
 * 
 *
 * @author zenofung
 * @email zenofung@qq.com
 * @date 2022-03-17 17:20:49
 */
public interface WineUsersService extends IService<WineUsersEntity> {

    PageUtils queryPage(Map<String, Object> params);

    List<WineUsersEntity> getListByWine(String wineId);
    List<UserVo> getListByUser(String wineId);

    boolean WineUserStatus(WineUsersEntity wineUsers);

}

