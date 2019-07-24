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
 * 医院
 *
 * @author charlie
 * @date 2019-04-30 17:09:57
 */
@Data
@TableName("twtsoft_doctor_hospital")
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "医院")
public class DoctorHospital extends Model<DoctorHospital> {
private static final long serialVersionUID = 1L;

    /**
   * 主键
   */
	@TableId(value = "id", type = IdType.UUID)
	@ApiModelProperty(value = "主键")
	private String id;
    /**
   * 医疗机构名称
   */
	@ApiModelProperty(value = "医疗机构名称")
	@NotBlank(message = "医院名称不能为空")
    private String name;
    /**
   * 医院图片
   */
	@ApiModelProperty(value = "医院图片")
    private String hospitalImage;
    /**
   * 医疗机构等级
   */
	@ApiModelProperty(value = "医疗机构等级")
    private String hospitalLevel;
    /**
   * 介绍
   */
	@ApiModelProperty(value = "介绍")
    private String introduced;
    /**
   * 地址
   */
	@ApiModelProperty(value = "地址")
    private String address;
    /**
   * 总机电话
   */
	@ApiModelProperty(value = "总机电话")
    private String phone;
    /**
   * 机构联系人
   */
	@ApiModelProperty(value = "机构联系人")
    private String contactName;
    /**
   * 联系人电话
   */
	@ApiModelProperty(value = "联系人电话")
    private String contactPhone;
    /**
   * 所属地区
   */
	@ApiModelProperty(value = "所属地区")
	@NotBlank(message = "所属地区不能为空")
	private String areaId;
    /**
   * 是否合作0: 是 1：否
   */
	@ApiModelProperty(value = "是否合作0: 是 1：否")
    private String cooperation;
    /**
   * 是否可建档0: 是 1：否
   */
	@ApiModelProperty(value = "是否可建档0: 是 1：否")
    private String archives;
    /**
   * 经度
   */
	@ApiModelProperty(value = "经度")
    private String lon;
    /**
   * 纬度
   */
	@ApiModelProperty(value = "纬度")
    private String lat;
    /**
   * 是否推送短信0: 是 1：否
   */
	@ApiModelProperty(value = "是否推送短信0: 是 1：否")
    private String msg;
    /**
   * 是否有专案 0：无 1：有
   */
	@ApiModelProperty(value = "是否有专案 0：无 1：有")
    private String project;
    /**
   * 医院服务电话
   */
	@ApiModelProperty(value = "医院服务电话")
    private String hospitalTel;
    /**
   * 关联医院编号
   */
	@ApiModelProperty(value = "关联医院编号")
    private String associated;
	/**
	 * 上级医院编号
	 */
	@ApiModelProperty(value = "上级医院编号")
	private String parentId;
	/**
	 * 部门ID
	 */
	private Integer deptId;
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
