package com.wine.game.wine.entity;

import com.baomidou.mybatisplus.annotation.IdType;
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
@TableName("w_user")
public class UserEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId(type = IdType.ASSIGN_ID)
	private String id;
	/**
	 * 
	 */
	private String openId;
	/**
	 * 
	 */
	private String userNikename;
	/**
	 * 
	 */
	private String userProtrait;
	/**
	 * 
	 */
	private String userSex;
	/**
	 * 
	 */
	private String userName;
	/**
	 * 
	 */
	private String userPhone;
	/**
	 * 
	 */
	private String userAddress;
	/**
	 * 
	 */
	private String lastIp;
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


}
