package com.wine.game.wine.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wine.game.wine.vo.UserVo;
import com.zenofung.common.utils.PageUtils;
import com.wine.game.wine.entity.UserRankingEntity;

import java.util.List;
import java.util.Map;

/**
 * 用户活跃排行
 *
 * @author zenofung
 * @email zenofung@qq.com
 * @date 2022-03-25 16:57:04
 */
public interface UserRankingService extends IService<UserRankingEntity> {

    PageUtils queryPage(Map<String, Object> params);

    List<UserRankingEntity> getListRanking(String type);

    UserRankingEntity listMeRanking(String userId);
}

