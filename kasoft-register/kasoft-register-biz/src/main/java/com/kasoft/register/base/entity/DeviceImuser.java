/*
 *    Copyright (c) 2018-2025, lengleng All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice,
 * this list of conditions and the following disclaimer.
 * Redistributions in binary form must reproduce the above copyright
 * notice, this list of conditions and the following disclaimer in the
 * documentation and/or other materials provided with the distribution.
 * Neither the name of the pig4cloud.com developer nor the names of its
 * contributors may be used to endorse or promote products derived from
 * this software without specific prior written permission.
 * Author: lengleng (wangiegie@gmail.com)
 */

package com.kasoft.register.base.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

/**
 * IM用户管理
 *
 * @author charlie
 * @date 2019-07-21 14:02:04
 */
@Data
@TableName("twtsoft_device_imuser")
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "IM用户")
public class DeviceImuser extends Model<DeviceImuser> {
private static final long serialVersionUID = 1L;

	/**
	 * 用户编号个人
	 */
	@TableId(value = "user_id", type = IdType.INPUT)
	@ApiModelProperty(value = "主键-用户编号个人")
	private Integer userId;

	/**
	 * 腾讯IM编号
	 */
	@ApiModelProperty(value = "腾讯IM编号")
    private String imuserId;

    /**
   * 姓名
   */
	@ApiModelProperty(value = "姓名")
	private String name;
    /**
   * 昵称
   */
	@ApiModelProperty(value = "昵称")
	private String nick;
    /**
   * 头像
   */
	@ApiModelProperty(value = "头像")
	private String headImg;
    /**
   * 用户编号档案
   */
	@ApiModelProperty(value = "用户编号档案")
	private String peopleId;
    /**
   * 医生编号
   */
	@ApiModelProperty(value = "医生编号")
	private String doctorId;
    /**
   * 医院编号
   */
	@ApiModelProperty(value = "医院编号")
	private String hospitalId;
    /**
   * 地区编号
   */
	@ApiModelProperty(value = "地区编号")
	private String areaId;
    /**
   * 用户类型  0：居民  1：医生
   */
	@ApiModelProperty(value = "用户类型", notes = "0：居民  1：医生")
	private String userType;
	/**
	 * 创建时间
	 */
	@ApiModelProperty(value = "创建时间")
	private LocalDateTime createTime;
	/**
	 * 修改时间
	 */
	@ApiModelProperty(value = "修改时间")
	private LocalDateTime updateTime;
	/**
	 * 是否删除  -1：已删除  0：正常
	 */
	@TableLogic
	@ApiModelProperty(hidden = true)
	private String delFlag;
	/**
	 * 租户
	 */
	@ApiModelProperty(hidden = true)
	private Integer tenantId;

	/**
	 * 清理不使用的DTO参数
	 */
	public void clearNoUseDTO() {
		createTime = null;
		updateTime = null;
		delFlag = null;
		tenantId = null;
	}
}
