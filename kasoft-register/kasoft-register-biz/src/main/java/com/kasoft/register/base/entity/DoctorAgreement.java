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
 * 居民签约协议
 *
 * @author charlie
 * @date 2019-05-07 15:43:03
 */
@Data
@TableName("twtsoft_doctor_agreement")
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "居民签约协议")
public class DoctorAgreement extends Model<DoctorAgreement> {
	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	@TableId(value = "id", type = IdType.UUID)
	@ApiModelProperty(value = "主键")
	private String id;
	/**
	 * 协议编号
	 */
	@ApiModelProperty(value = "协议编号")
	private String code;
	/**
	 * 居民编号
	 */
	@ApiModelProperty(value = "居民编号")
	@NotBlank(message = "居民编号不能为空")
	private String peopleId;
	/**
	 * 居民身份证
	 */
	@ApiModelProperty(value = "身份证")
	@NotBlank(message = "身份证不能为空")
	private String idCard;
	/**
	 * 居民姓名
	 */
	@ApiModelProperty(value = "居民姓名")
	private String name;
	/**
	 * 开始时间
	 */
	@ApiModelProperty(value = "开始时间", example = "2019-02-06 10:10:10")
	private LocalDateTime startTime;
	/**
	 * 结束时间
	 */
	@ApiModelProperty(value = "结束时间", example = "2019-05-06 10:10:10")
	private LocalDateTime endTime;
	/**
	 * 协议状态 0：未上传 1：已上传
	 */
	@ApiModelProperty(value = "协议状态 0：未上传 1：已上传")
	private String protocolStatus;
	/**
	 * 协议照片
	 */
	@ApiModelProperty(value = "协议照片")
	private String protocolPhoto;
	/**
	 * 协议上传时间
	 */
	@ApiModelProperty(value = "协议上传时间")
	private LocalDateTime protocolUptime;
	/**
	 * 地区
	 */
	@ApiModelProperty(value = "地区")
	private String areaId;
	/**
	 * 医院
	 */
	@ApiModelProperty(value = "医院")
	@NotBlank(message = "医院不能为空")
	private String hospitalId;
	/**
	 * 团队
	 */
	@ApiModelProperty(value = "团队")
	@NotBlank(message = "团队不能为空")
	private String teamId;
	/**
	 * 责任医生
	 */
	@ApiModelProperty(value = "责任医生")
	@NotBlank(message = "责任医生不能为空")
	private String doctorId;
	/**
	 * 签约人
	 */
	@ApiModelProperty(value = "签约人")
	private String contractorId;
	/**
	 * 医生签名
	 */
	@ApiModelProperty(value = "医生签名")
	private String doctorSign;
	/**
	 * 居民签名
	 */
	@ApiModelProperty(value = "居民签名")
	private String peopleSign;
	/**
	 * 状态 1：正常 2：已续约 3：已解约
	 */
	@ApiModelProperty(value = "状态 1：正常 2：已续约 3：已解约")
	private String status;
	/**
	 * 当前状态 1：生效中 2：未生效 3：已失效
	 */
	@ApiModelProperty(value = "当前状态 1：生效中 2：未生效 3：已失效")
	private String workStatus;
	/**
	 * 协议类型 1: 基础 2：个性化
	 */
	@ApiModelProperty(value = "协议类型 1: 基础 2：个性化")
	private String serviceType;
	/**
	 * 协议文字描述
	 */
	@ApiModelProperty(value = "协议文字描述")
	private String note;
	/**
	 * 自助签约编号
	 */
	@ApiModelProperty(value = "自助签约编号")
	private String applicationId;
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
