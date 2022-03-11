package com.wine.game.wine.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import lombok.Data;

/**
 * 
 * 
 * @author zenofung
 * @email zenofung@qq.com
 * @date 2022-03-08 11:46:32
 */
@Data
@TableName("w_article")
public class ArticleEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Integer id;
	/**
	 * 
	 */
	private String content;
	/**
	 * 
	 */
	private String images;

	private Integer userId;
	/**
	 * 
	 */
	private String wineId;
	/**
	 * 
	 */
	private Integer praise;
	/**
	 * 
	 */
	private Integer status;
	/**
	 * 
	 */
	private Date updateTime;
	/**
	 * 
	 */
	private Date createTime;

	@TableField(exist = false)
	private List<CommentEntity> commentEntity;

	@TableField(exist = false)
	private UserEntity userEntity;

	@TableField(exist = false)
	private List<ComComEntity> comComEntityList;

	@TableField(exist = false)
	private Integer praises;

	@TableField(exist = false)
	private boolean praiseStatus;

	/**
	 *关注状态
	 */
	@TableField(exist = false)
	private boolean attentionStatus;
	/**
	 * 标签列表
	 */
	@TableField(exist = false)
	private List<ArticleLabelEntity> labelEntities;
	/**
	 * 酒局
	 */
	@TableField(exist = false)
	private WineEntity wineEntity;
	/**
	 * 位置
	 */
	private String articleAddress;



}
