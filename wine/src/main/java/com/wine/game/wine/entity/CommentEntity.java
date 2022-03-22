package com.wine.game.wine.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.wine.game.wine.vo.UserVo;
import lombok.Data;

/**
 * 
 * 
 * @author zenofung
 * @email zenofung@qq.com
 * @date 2022-03-08 11:46:32
 */
@Data
@TableName("w_comment")
public class CommentEntity implements Serializable {
	private static final long serialVersionUID = 1L;


	/**
	 *
	 */
	@TableId
	private Integer id;
	/**
	 *
	 */
	private String userId;
	/**
	 *
	 */
	private String artId;
	/**
	 *
	 */
	private String content;
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
	private Date createTime;

	@TableField(exist = false)
	private List<ComComEntity> comComEntityList;


	@TableField(exist = false)
	private Integer praises;

	@TableField(exist = false)
	private boolean praiseStatus;

	@TableField(exist = false)
	private UserVo userVo;




}
