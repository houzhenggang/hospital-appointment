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
 * 通用随访
 *
 * @author charlie
 * @date 2019-06-15 11:40:08
 */
@Data
@TableName("twtsoft_doctor_commonform")
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "通用随访")
public class DoctorCommonform extends Model<DoctorCommonform> {
private static final long serialVersionUID = 1L;

    /**
   * 主键
   */
    @TableId(value = "common_form_id", type = IdType.UUID)
	@ApiModelProperty(value = "主键")
    private String commonFormId;
    /**
   * 居民编号
   */
	@ApiModelProperty(value = "居民编号")
	private String peopleId;
    /**
   * 身份证编号
   */
	@ApiModelProperty(value = "身份证编号")
	private String idCard;
    /**
   * 姓名
   */
	@ApiModelProperty(value = "姓名")
	private String name;
    /**
   * 本次随访时间
   */
	@ApiModelProperty(value = "本次随访时间")
	private LocalDateTime followTime;
    /**
   * 本次随访方式
   */
	@ApiModelProperty(value = "本次随访方式")
	private String followUpWay;
    /**
   * 此次随访分类
   */
	@ApiModelProperty(value = "此次随访分类")
	private String followUpType;
    /**
   * 下次随访日期
   */
	@ApiModelProperty(value = "下次随访日期")
	private LocalDateTime nextFollowTime;
    /**
   * 健康处方
   */
	@ApiModelProperty(value = "健康处方")
	private String prescriptionText;
    /**
   * 医院编号
   */
	@ApiModelProperty(value = "医院编号")
	private String hospitalId;
    /**
   * 团队编号
   */
	@ApiModelProperty(value = "团队编号")
	private String teamId;
    /**
   * 随访医生
   */
	@ApiModelProperty(value = "随访医生")
	private String doctorId;
	/**
	 * 签约编号
	 */
	@ApiModelProperty(value = "签约编号")
	private String agreementId;
	/**
	 * 套餐编号
	 */
	@ApiModelProperty(value = "套餐编号")
	private String packageId;
    /**
   * 服务项目编号
   */
	@ApiModelProperty(value = "服务项目编号")
	private String projectId;
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
