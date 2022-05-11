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
 * @date 2022-05-11 11:45:43
 */
@Data
@TableName("w_im_message")
public class ImMessageEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
    @TableId(type = IdType.ASSIGN_ID)
	private String magId;
	/**
	 * 
	 */
	private String userId;
	/**
	 * 
	 */
	private String targetId;
	/**
	 * 消息内容
	 */
	private String magContent;
	/**
	 * 消息队列id
	 */
	private Integer imMagListId;
	/**
	 * 0 未读，1 已读。
	 */
	private Integer messageStatus;
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
