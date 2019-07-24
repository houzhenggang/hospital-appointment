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
 * 血糖管理
 *
 * @author charlie
 * @date 2019-07-11 16:35:16
 */
@Data
@TableName("twtsoft_device_bloodsugar")
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "血糖管理")
public class DeviceBloodsugar extends Model<DeviceBloodsugar> {
private static final long serialVersionUID = 1L;

    /**
   * 主键
   */
	@TableId(value = "bloodsugar_id", type = IdType.UUID)
	@ApiModelProperty(value = "主键")
    private String bloodsugarId;
	/**
	 * 用户编号个人
	 */
	@ApiModelProperty(value = "用户编号个人")
	private Integer userId;
	/**
	 * 用户编号档案
	 */
	@ApiModelProperty(value = "用户编号档案")
	private String peopleId;
	/**
	 * 身份证
	 */
	@ApiModelProperty(value = "身份证")
	private String idCard;
	/**
	 * 姓名
	 */
	@ApiModelProperty(value = "姓名")
	private String name;
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
   * 血糖值
   */
	@ApiModelProperty(value = "血糖值")
	private String glu;
    /**
   * 0 = 空腹血糖 1 = 餐后1小时血糖 2 = 餐后2小时血糖
   */
	@ApiModelProperty(value = "血糖类型", notes = "0 = 空腹血糖 1 = 餐后1小时血糖 2 = 餐后2小时血糖")
	private String gluType;
    /**
   * 异常状态 字典
   */
	@ApiModelProperty(value = "异常状态 字典")
	private String exceptionType;
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
