package com.wine.game.wine.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;

import com.wine.game.wine.vo.UserVo;
import lombok.Data;

/**
 * 
 * 
 * @author zenofung
 * @email zenofung@qq.com
 * @date 2022-04-03 22:10:27
 */
@Data
@TableName("w_wine_users_ready")
public class WineUsersReadyEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Integer id;
	/**
	 * 
	 */
	private String wineId;
	/**
	 * 
	 */
	private String userId;
	/**
	 * 
	 */
	private Integer flag;
	/**
	 * 
	 */
	private Integer status;
	/**
	 * 
	 */
	private Date createTime;
	/**
	 * 
	 */
	private Date updateTime;
	@TableField(exist = false)
	private UserVo userReady;

	@TableField(exist = false)
	private boolean meStatus;
}
