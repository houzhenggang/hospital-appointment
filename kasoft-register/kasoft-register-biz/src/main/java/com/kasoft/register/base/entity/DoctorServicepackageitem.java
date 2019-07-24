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
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 服务包项目
 *
 * @author charlie
 * @date 2019-05-21 21:55:26
 */
@Data
@TableName("twtsoft_doctor_servicepackageitem")
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "组织-套餐项目")
public class DoctorServicepackageitem extends Model<DoctorServicepackageitem> {
private static final long serialVersionUID = 1L;

    /**
   * 主键
   */
	@TableId(value = "id", type = IdType.UUID)
	@ApiModelProperty(value = "主键")
	private String id;
    /**
   * 套餐编号
   */
	@ApiModelProperty(value = "套餐编号")
	@NotBlank(message = "套餐编号不能为空")
	private String packageId;
    /**
   * 协议类型 1: 基础 2：个性化
   */
	@ApiModelProperty(value = "协议类型 1: 基础 2：个性化")
    private String serviceType;
	/**
	 * 项目ID
	 */
	@ApiModelProperty(value = "项目ID")
	@NotBlank(message = "项目ID不能为空")
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
   * 项目类型 1: 公卫服务 2: 医疗服务
   */
	@ApiModelProperty(value = "项目类型 1: 公卫服务 2: 医疗服务")
    private String projectType;
    /**
   * 项目内容
   */
	@ApiModelProperty(value = "项目内容")
    private String projectContent;
    /**
   * 项目价格
   */
	@ApiModelProperty(value = "项目价格", hidden = true)
    private BigDecimal adjustPrice;
    /**
   * 执行价格
   */
	@ApiModelProperty(value = "执行价格")
    private BigDecimal projectPrice;
    /**
   * 是否公卫支付 0: 是  1：否
   */
	@ApiModelProperty(value = "是否公卫支付 0: 是  1：否", hidden = true)
    private String publicSecurity;
    /**
   * 公卫支付份额
   */
	@ApiModelProperty(value = "公卫支付份额", hidden = true)
    private BigDecimal publicSecurityPayment;
    /**
   * 非公卫支付份额
   */
	@ApiModelProperty(value = "非公卫支付份额", hidden = true)
    private BigDecimal noPublicSecurityPayment;
    /**
   * 医保支付份额
   */
	@ApiModelProperty(value = "医保支付份额", hidden = true)
    private BigDecimal medicalInsurancePayment;
    /**
   * 个人支付份额
   */
	@ApiModelProperty(value = "个人支付份额", hidden = true)
    private BigDecimal personalPayment;
    /**
   * 建议服务频率
   */
	@ApiModelProperty(value = "建议服务频率")
    private Integer serviceFrequency;
	/**
	 * 服务单位
	 */
	@ApiModelProperty(value = "服务单位")
    private String serviceUnit;
	/**
	 * 业务关联
	 */
	@ApiModelProperty(value = "业务关联")
    private String businessContext;
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
