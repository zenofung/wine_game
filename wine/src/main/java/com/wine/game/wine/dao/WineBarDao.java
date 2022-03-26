package com.wine.game.wine.dao;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.wine.game.wine.entity.WineBarEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.List;

/**
 * 酒吧
 * 
 * @author zenofung
 * @email zenofung@qq.com
 * @date 2022-03-25 16:57:04
 */
@Mapper
public interface WineBarDao extends BaseMapper<WineBarEntity> {

    IPage<WineBarEntity> selectBarPage(IPage<?> page,  @Param(Constants.WRAPPER) Wrapper<WineBarEntity> wineBarEntityQueryWrapper);

    IPage<WineBarEntity> selectBarPage2(IPage<?> page, @Param(Constants.WRAPPER) Wrapper<WineBarEntity> wineBarEntityQueryWrapper, @Param("userLat") BigDecimal userLat, @Param("userLong")BigDecimal userLong);


    List<WineBarEntity> selectListBar( @Param(Constants.WRAPPER) Wrapper<WineBarEntity> wineBarEntityQueryWrapper, @Param("userLat") BigDecimal userLat, @Param("userLong")BigDecimal userLong);
}
