package com.wine.game.wine.dao;

import com.wine.game.wine.entity.UserEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 
 * 
 * @author zenofung
 * @email zenofung@qq.com
 * @date 2022-03-08 11:46:32
 */
@Mapper
public interface UserDao extends BaseMapper<UserEntity> {
	
}
