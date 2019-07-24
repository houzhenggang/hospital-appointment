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
 * 问题反馈
 *
 * @author charlie
 * @date 2019-04-30 17:10:08
 */
@Data
@TableName("twtsoft_doctor_problemfeedback")
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "问题反馈")
public class DoctorProblemfeedback extends Model<DoctorProblemfeedback> {
private static final long serialVersionUID = 1L;

    /**
   * 主键
   */
	@TableId(value = "id", type = IdType.UUID)
	@ApiModelProperty(value = "主键")
	private String id;
    /**
   * 反馈类别  1：软件问题  2：投诉
   */
	@ApiModelProperty(value = "反馈类别  1：软件问题  2：投诉")
    private String feedbackType;
    /**
   * 反馈内容
   */
	@ApiModelProperty(value = "反馈内容")
	@NotBlank(message = "内容不能为空")
    private String feedbackContent;
    /**
   * 反馈时间
   */
	@ApiModelProperty(value = "反馈时间")
    private LocalDateTime feedbackTime;
    /**
   * 反馈人
   */
	@ApiModelProperty(value = "反馈人编号")
    private String personId;
	/**
	 * 反馈人
	 */
	@ApiModelProperty(value = "反馈人姓名")
	private String personName;
    /**
   * 处理结果
   */
	@ApiModelProperty(value = "处理结果")
    private String handle;
    /**
   * 处理人
   */
	@ApiModelProperty(value = "处理人编号")
    private String handleId;
	/**
	 * 处理人
	 */
	@ApiModelProperty(value = "处理人姓名")
	private String handleName;
    /**
   * 处理时间
   */
	@ApiModelProperty(value = "处理时间")
    private LocalDateTime handleTime;
    /**
   * 处理状态 0：未处理，1：已处理
   */
	@ApiModelProperty(value = "处理状态 0：未处理，1：已处理")
    private String feedbackState;
    /**
   * 软件名称 医生APP 居民APP
   */
	@ApiModelProperty(value = "软件名称-字典SOFTNAME")
    private String softName;
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
