package com.wine.game.wine.dao;

import com.wine.game.wine.entity.SearchEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 
 * 
 * @author zenofung
 * @email zenofung@qq.com
 * @date 2022-04-03 20:21:42
 */
@Mapper
public interface SearchDao extends BaseMapper<SearchEntity> {

    List<String> getListKeyWord();

}
