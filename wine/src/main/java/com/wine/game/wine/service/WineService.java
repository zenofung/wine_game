package com.wine.game.wine.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wine.game.wine.vo.UserVo;
import com.zenofung.common.utils.PageUtils;
import com.wine.game.wine.entity.WineEntity;

import java.util.List;
import java.util.Map;

/**
 * 
 *
 * @author zenofung
 * @email zenofung@qq.com
 * @date 2022-03-08 11:46:32
 */
public interface WineService extends IService<WineEntity> {

    PageUtils queryPage(Map<String, Object> params);

    PageUtils queryPage(Map<String, Object> params, String id);

    WineEntity getByIdAll(String wineId);

    List<UserVo> getBarById(Integer id);
}

