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
 * 酒吧
 * 
 * @author zenofung
 * @email zenofung@qq.com
 * @date 2022-03-25 16:57:04
 */
@Data
@TableName("w_wine_bar")
public class WineBarEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Integer id;
	/**
	 * 酒吧名称
	 */
	private String barTittle;
	/**
	 * 轮播图
	 */
	private String barBanner;
	/**
	 * 清吧
	 */
	private String barType;
	/**
	 * 每人单价
	 */
	private String peopleAveMoney;
	/**
	 * 营业时间
	 */
	private String openingHours;
	/**
	 * 商家电话
	 */
	private String businessPhone;

	/**
	 * 星级
	 */
	private Integer barFavorability;

	/**
	 * 商家位置
	 */
	private String barLocation;
	/**
	 * 商家位置详细地址
	 */
	private String barLocationDetails;
	/**
	 * 商家经纬度
	 */
	private String barLocationLat;
	/**
	 * 
	 */
	private Integer status;
	/**
	 * 
	 */
	private Date updateTime;

	@TableField(exist = false)
	private List<UserVo> wineEntityList;

	@TableField(exist = false)
	private Boolean wineBarAttention;

}
