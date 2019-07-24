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
 * 健康处方
 *
 * @author charlie
 * @date 2019-07-11 17:10:18
 */
@Data
@TableName("twtsoft_doctor_healthprescript")
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "健康处方")
public class DoctorHealthprescript extends Model<DoctorHealthprescript> {
private static final long serialVersionUID = 1L;

    /**
   * 主键
   */
	@TableId(value = "healthprescript_id", type = IdType.UUID)
	@ApiModelProperty(value = "主键")
    private String healthprescriptId;
    /**
   * 健康处方类型
   */
	@ApiModelProperty(value = "健康处方类型 字典")
	private String healthprescriptType;
    /**
   * 标题
   */
	@ApiModelProperty(value = "标题")
	private String title;
    /**
   * 健康处方内容
   */
	@ApiModelProperty(value = "健康处方内容")
	private String content;
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
