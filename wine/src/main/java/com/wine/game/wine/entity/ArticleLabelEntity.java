package com.wine.game.wine.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 标签关联文章
 * 
 * @author zenofung
 * @email zenofung@qq.com
 * @date 2022-03-11 16:57:36
 */
@Data
@TableName("w_article_label")
public class ArticleLabelEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Integer id;
	/**
	 * 
	 */
	private Integer labelId;
	/**
	 * 
	 */
	private Integer articleId;
	/**
	 * 
	 */
	private Date createTime;

	/**
	 * 查询标签
	 */
	@TableField(exist = false)
	private LabelEntity labelEntity;

}
