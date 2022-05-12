package com.wine.game.wine.dao;

import com.wine.game.wine.entity.ImMessageEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 
 * 
 * @author zenofung
 * @email zenofung@qq.com
 * @date 2022-05-11 11:45:43
 */
@Mapper
public interface ImMessageDao extends BaseMapper<ImMessageEntity> {

    ImMessageEntity getOneByListId(@Param("id") Integer id);
}
