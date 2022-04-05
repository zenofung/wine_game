package com.wine.game.wine.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.math.BigDecimal;
import java.io.Serializable;
import java.util.Date;

import com.wine.game.wine.vo.UserVo;
import lombok.Data;

/**
 * 用户活跃排行
 * 
 * @author zenofung
 * @email zenofung@qq.com
 * @date 2022-03-25 16:57:04
 */
@Data
@TableName("w_user_ranking")
public class UserRankingEntity implements Serializable {
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
	private BigDecimal active;
	/**
	 * 
	 */
	private BigDecimal social;
	/**
	 * 
	 */
	private Date updateTime;
	/**
	 * 
	 */
	private Date createTime;

	@TableField(exist = false)
	private UserVo userVo;
	@TableField(exist = false)
	private boolean me;

	@TableField(exist = false)
	private boolean meAttention;

	@TableField(exist = false)
	private String meRankingActive;
	@TableField(exist = false)
	private String meRankingSocial;
}
