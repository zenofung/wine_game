package com.wine.game.wine.entity;

import com.baomidou.mybatisplus.annotation.IdType;
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
 * @date 2022-03-17 17:20:49
 */
@Data
@TableName("w_wine")
public class WineEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId(type = IdType.ASSIGN_ID)
	private String id;
	/**
	 * 
	 */
	private String unId;
	/**
	 * 主题
	 */
	private String subject;
	/**
	 * 时间
	 */
	private String time;
	/**
	 * 地点
	 */
	private String place;
	/**
	 * 人数
	 */
	private Integer pepleNumber;
	/**
	 * 组局照片
	 */
	private String titleImage;
	/**
	 * 对象男
	 */
	private Integer objectBoy;
	/**
	 * 对象女
	 */
	private Integer objectGirl;
	/**
	 * 对象不分
	 */
	private Integer object;
	/**
	 * 组局类型
	 */
	private String wineType;
	/**
	 * 付款方式
	 */
	private String paymetType;
	/**
	 * 
	 */
	private String wineTableNumber;
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
	private UserEntity userEntity;
	@TableField(exist = false)
	private List<UserVo> userEntityList;
	@TableField(exist = false)
	private boolean wineUserStatus;

}
