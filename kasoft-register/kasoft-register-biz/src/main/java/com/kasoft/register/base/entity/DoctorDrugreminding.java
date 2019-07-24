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
 * 用药管理
 *
 * @author charlie
 * @date 2019-07-11 16:35:08
 */
@Data
@TableName("twtsoft_doctor_drugreminding")
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "用药管理")
public class DoctorDrugreminding extends Model<DoctorDrugreminding> {
private static final long serialVersionUID = 1L;

    /**
   * 主键
   */
	@TableId(value = "drugreminding_id", type = IdType.UUID)
	@ApiModelProperty(value = "主键")
    private String drugremindingId;
    /**
   * 用户编号
   */
	@ApiModelProperty(value = "用户编号档案")
	private String peopleId;
	/**
	 * 姓名
	 */
	@ApiModelProperty(value = "姓名")
	private String name;
	/**
	 * 身份证
	 */
	@ApiModelProperty(value = "身份证")
	private String idCard;
    /**
   * 医生编号
   */
	@ApiModelProperty(value = "医生编号")
	private String doctorId;
    /**
   * 通用名称
   */
	@ApiModelProperty(value = "通用名称")
	private String commonName;
    /**
   * 药品名称
   */
	@ApiModelProperty(value = "药品名称")
	private String tradeName;
    /**
   * 用法用量
   */
	@ApiModelProperty(value = "用法用量")
	private String dosage;
    /**
   * 适应症
   */
	@ApiModelProperty(value = "适应症")
	private String indications;
    /**
   * 不良反应
   */
	@ApiModelProperty(value = "不良反应")
	private String adverse;
    /**
   * 禁忌
   */
	@ApiModelProperty(value = "禁忌")
	private String contraindications;
    /**
   * 注意事项
   */
	@ApiModelProperty(value = "注意事项")
	private String attentions;
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
