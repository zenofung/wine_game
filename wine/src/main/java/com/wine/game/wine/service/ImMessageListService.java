package com.wine.game.wine.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zenofung.common.utils.PageUtils;
import com.wine.game.wine.entity.ImMessageListEntity;

import java.util.Map;

/**
 * 
 *
 * @author zenofung
 * @email zenofung@qq.com
 * @date 2022-05-11 11:45:43
 */
public interface ImMessageListService extends IService<ImMessageListEntity> {

    PageUtils queryPage(Map<String, Object> params);

    String getFriendList(String imList);


    ImMessageListEntity getByIdAndUserVo(Integer id, String userId);
}

