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

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

/**
 * 居民签约协议明细
 *
 * @author charlie
 * @date 2019-05-21 21:54:31
 */
@Data
@TableName("twtsoft_doctor_peopleservicepackage")
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "签约协议套餐明细")
public class DoctorPeopleservicepackage extends Model<DoctorPeopleservicepackage> {
private static final long serialVersionUID = 1L;

    /**
   * 主键
   */
	@TableId(value = "id", type = IdType.UUID)
	@ApiModelProperty(value = "主键")
	private String id;
    /**
   * 居民编号
   */
	@ApiModelProperty(value = "居民编号")
    private String peopleId;
    /**
   * 居民身份证
   */
	@ApiModelProperty(value = "居民身份证")
	@NotBlank(message = "身份证不能为空")
    private String idCard;
    /**
   * 套餐ID
   */
	@ApiModelProperty(value = "套餐ID")
	private String packageId;
    /**
   * 套餐编码
   */
	@ApiModelProperty(value = "套餐编号")
    private String packageCode;
    /**
   * 套餐名称
   */
	@ApiModelProperty(value = "套餐名称")
    private String packageLabel;
    /**
   * 协议类型 1: 基础 2：个性化
   */
	@ApiModelProperty(value = "协议类型 1: 基础 2：个性化")
    private String serviceType;
    /**
   * 项目ID
   */
	@ApiModelProperty(value = "项目ID")
    private String projectId;
    /**
   * 项目编号
   */
	@ApiModelProperty(value = "项目编号")
    private String projectCode;
    /**
   * 项目名称
   */
	@ApiModelProperty(value = "项目名称")
    private String projectName;
    /**
   * 建议服务频率
   */
	@ApiModelProperty(value = "建议服务频率")
    private Integer serviceFrequency;
    /**
   * 业务关联
   */
	@ApiModelProperty(value = "业务关联")
    private String businessContext;
    /**
   * 签约类型 1：项目 2：套餐
   */
	@ApiModelProperty(value = "签约类型 1：项目 2：套餐")
    private Integer signingType;
    /**
   * 协议主键
   */
	@ApiModelProperty(value = "协议主键")
    private String agreementId;
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
	@ApiModelProperty(hidden=true)
	private String delFlag;
	/**
	 * 租户
	 */
	@ApiModelProperty(hidden=true)
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
