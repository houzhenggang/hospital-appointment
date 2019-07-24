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
 * 居民自助建档签约
 *
 * @author charlie
 * @date 2019-05-14 10:55:40
 */
@Data
@TableName("twtsoft_device_application")
@EqualsAndHashCode(callSuper = true)
@ApiModel(description ="自助建档签约")
public class DeviceApplication extends Model<DeviceApplication> {
private static final long serialVersionUID = 1L;

    /**
   * 主键
   */
	@TableId(value = "id", type = IdType.UUID)
	@ApiModelProperty(value = "主键")
	private String id;
    /**
   * 居民身份证
   */
	@ApiModelProperty(value = "身份证号")
	@NotBlank(message = "身份证号不能为空")
    private String idCard;
	/**
	 * 身份证正面照片
	 */
	@ApiModelProperty(value = "身份证正面照片")
	private String idCardFront;
    /**
	 * 姓名
	 */
	@ApiModelProperty(value = "姓名")
	@NotBlank(message = "姓名不能为空")
	private String name;
	/**
	 * 手机号
	 */
	@ApiModelProperty(value = "手机号")
	private String phone;
    /**
   * 性别  1：男  2：女
   */
    @ApiModelProperty(value = "性别  1：男  2：女 3：未说明")
	private String sex;
    /**
   * 出生日期
   */
    @ApiModelProperty(value = "出生日期")
	private String birthDate;
    /**
   * 民族-字典
   */
    @ApiModelProperty(value = "民族-字典")
	private String national;
	/**
	 * 工作单位
	 */
	@ApiModelProperty(value = "工作单位")
    private String workUnit;
    /**
   * 医院
   */
    @ApiModelProperty(value = "医院")
	private String hospitalId;
    /**
   * 团队
   */
    @ApiModelProperty(value = "团队")
	private String teamId;
    /**
   * 责任医生
   */
    @ApiModelProperty(value = "责任医生")
	private String doctorId;
    /**
   * 所选签约服务ID ,号隔开列表
   */
    @ApiModelProperty(value = "所选签约服务ID,号隔开列表", hidden = true)
	private String serviceId;
    /**
   * 所选套餐名称
   */
    @ApiModelProperty(value = "所选套餐名称,号隔开列表", hidden = true)
	private String serviceName;
    /**
   * 协议类型 1: 基础 2：个性化
   */
    @ApiModelProperty(value = "协议类型 1: 基础 2：个性化")
	private String serviceType;
    /**
   * 头像
   */
    @ApiModelProperty(value = "头像")
	private String headImg;
    /**
   * 处理状态 0 未处理 1已处理
   */
    @ApiModelProperty(value = "处理状态 0 未处理 1已处理")
	private String status;
	/**
	 * 关联用户编号
	 */
	@ApiModelProperty(value = "关联用户编号")
	private Integer userId;
	/**
	 * 来源 1: 手机 2：签约机
	 */
	@ApiModelProperty(value = "数据来源")
	private String source;
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
