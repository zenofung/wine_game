package com.wine.game.wine.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
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
	private String content;
	/**
	 * 
	 */
	private String praise;
	/**
	 * 
	 */
	private Integer status;
	/**
	 * 
	 */
	private Date createTime;

}
