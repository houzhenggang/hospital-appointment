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
 * 设备软件管理
 *
 * @author charlie
 * @date 2019-04-30 17:10:23
 */
@Data
@TableName("twtsoft_doctor_softmanager")
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "软件管理")
public class DoctorSoftmanager extends Model<DoctorSoftmanager> {
private static final long serialVersionUID = 1L;

    /**
   * 主键
   */
	@TableId(value = "id", type = IdType.UUID)
	@ApiModelProperty(value = "主键")
	private String id;
    /**
   * 软件名称
   */
	@ApiModelProperty(value = "软件名称")
	@NotBlank(message = "软件名称不能为空")
    private String softName;
    /**
   * 软件路径
   */
	@ApiModelProperty(value = "软件路径")
    private String softPath;
    /**
   * 软件更新日志
   */
	@ApiModelProperty(value = "软件更新日志")
    private String softUpdateLog;
    /**
   * 软件版本号
   */
	@ApiModelProperty(value = "软件版本号")
    private Integer version;
    /**
   * MD5签名
   */
	@ApiModelProperty(value = "MD5签名")
    private String md5;
    /**
   * 软件版本名称
   */
	@ApiModelProperty(value = "软件版本名称")
    private String versionName;
    /**
   * 复核（0未复核 1复核）
   */
	@ApiModelProperty(value = "复核（0未复核 1复核）")
    private String review;
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
