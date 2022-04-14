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
 * @date 2022-04-03 20:21:42
 */
@Data
@TableName("w_search")
public class SearchEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Integer id;
	/**
	 * 用户id
	 */
	private String userId;
	/**
	 * 1用户 2酒馆 3关键词 4酒局
	 */
	private Integer type;
	/**
	 * 显示内容
	 */
	private String title;
	/**
	 * 
	 */
	private Integer status;
	/**
	 * 
	 */
	private Date createTime;

}
