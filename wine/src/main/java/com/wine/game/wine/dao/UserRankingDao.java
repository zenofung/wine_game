package com.wine.game.wine.dao;

import com.wine.game.wine.entity.UserRankingEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 用户活跃排行
 * 
 * @author zenofung
 * @email zenofung@qq.com
 * @date 2022-03-25 16:57:04
 */
@Mapper
public interface UserRankingDao extends BaseMapper<UserRankingEntity> {

    String getMeRanking(String userId);

    String getMeRankingSocial(String userId);

}
