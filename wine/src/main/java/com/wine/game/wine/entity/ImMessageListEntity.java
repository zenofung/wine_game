package com.wine.game.wine.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;

import lombok.Builder;
import lombok.Data;

/**
 * 
 * 
 * @author zenofung
 * @email zenofung@qq.com
 * @date 2022-05-11 11:45:43
 */
@Data
@TableName("w_im_message_list")
public class ImMessageListEntity implements Serializable {
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
	private String friendId;
	/**
	 * 是否删除
	 */
	private Integer status;
	/**
	 * 是否顶置
	 */
	private Integer height;
	/**
	 * 0 不在线   1在线
	 */
	private Integer onLine;
	/**
	 * 
	 */
	private Date updateTime;
	/**
	 * 
	 */
	private Date createTime;

}
