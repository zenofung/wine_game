package com.wine.game.wine.dao;

import com.wine.game.wine.entity.ArticleLabelEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 标签关联文章
 * 
 * @author zenofung
 * @email zenofung@qq.com
 * @date 2022-03-11 16:57:36
 */
@Mapper
public interface ArticleLabelDao extends BaseMapper<ArticleLabelEntity> {
	
}
