package com.wine.game.wine.dao;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wine.game.wine.entity.ArticleEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 
 * 
 * @author zenofung
 * @email zenofung@qq.com
 * @date 2022-03-08 11:46:32
 */
@Mapper
public interface ArticleDao extends BaseMapper<ArticleEntity> {

    IPage<ArticleEntity> findByAttentionID(IPage<?> page,String userId);

}
