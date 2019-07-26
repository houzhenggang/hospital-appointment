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
 * 科室字典
 *
 * @author charlie
 * @date 2019-04-30 17:10:43
 */
@Data
@TableName("kasoft_doctor_departmentdictionary")
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "科室")
public class DoctorDepartmentdictionary extends Model<DoctorDepartmentdictionary> {
private static final long serialVersionUID = 1L;

    /**
   * 主键
   */
	@TableId(value = "department_id", type = IdType.UUID)
	@ApiModelProperty(value = "主键")
	private String departmentId;
    /**
   * 科室代码
   */
	@ApiModelProperty(value = "科室代码")
    private String departmentCode;
    /**
   * 科室名称
   */
	@ApiModelProperty(value = "科室名称")
	@NotBlank(message = "名称不能为空")
    private String name;
    /**
   * 科室图片
   */
	@ApiModelProperty(value = "科室图片")
    private String departmentImage;
    /**
   * 介绍
   */
	@ApiModelProperty(value = "介绍")
    private String introduced;
    /**
   * 科室联系人
   */
	@ApiModelProperty(value = "科室联系人")
    private String contactName;
    /**
   * 联系人电话
   */
	@ApiModelProperty(value = "联系人电话")
    private String contactPhone;
    /**
   * 地区编号
   */
	@ApiModelProperty(value = "地区编号")
    private String areaId;
    /**
   * 所属医疗机构
   */
	@ApiModelProperty(value = "所属医疗机构")
	@NotBlank(message = "所属医疗机构不能为空")
	private String hospitalId;
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
