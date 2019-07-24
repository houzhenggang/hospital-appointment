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
 * 服务套餐包
 *
 * @author charlie
 * @date 2019-05-07 15:56:24
 */
@Data
@TableName("twtsoft_doctor_servicepackage")
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "套餐")
public class DoctorServicepackage extends Model<DoctorServicepackage> {
private static final long serialVersionUID = 1L;

    /**
   * 主键
   */
	@TableId(value = "id", type = IdType.UUID)
	@ApiModelProperty(value = "主键")
	private String id;
    /**
   * 父级套餐主键
   */
	@ApiModelProperty(value = "父级套餐主键")
	@NotBlank(message = "父级套餐主键不能为空")
	private String parentId;
    /**
   * 套餐编码
   */
	@ApiModelProperty(value = "套餐编码")
	private String packageCode;
    /**
   * 套餐等级
   */
	@ApiModelProperty(value = "套餐等级")
    private String packageLevel;
    /**
   * 套餐标签名称
   */
	@ApiModelProperty(value = "套餐标签名称")
	@NotBlank(message = "套餐标签名称不能为空")
	private String packageLabel;
    /**
   * 服务对象
   */
	@ApiModelProperty(value = "服务对象")
    private String peopleTypeList;
    /**
   * 缩略图
   */
	@ApiModelProperty(value = "缩略图")
    private String thumbnail;
    /**
   * 图片
   */
	@ApiModelProperty(value = "图片")
    private String image;
    /**
   * 拼音助记码
   */
	@ApiModelProperty(value = "拼音助记码")
    private String pinyinMnemonicCode;
    /**
   * 五笔助记码
   */
	@ApiModelProperty(value = "五笔助记码")
    private String wubiMnemonicCode;
    /**
   * 套餐说明
   */
	@ApiModelProperty(value = "套餐说明")
    private String instructions;
    /**
   * 服务说明
   */
	@ApiModelProperty(value = "服务说明")
    private String attention;
    /**
   * 套餐参考费用
   */
	@ApiModelProperty(value = "套餐参考费用", hidden = true)
    private BigDecimal referenceCharges;
    /**
   * 套餐费用(元)
   */
	@ApiModelProperty(value = "套餐费用(元)")
    private BigDecimal charges;
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
   * 套餐地区
   */
	@ApiModelProperty(value = "套餐地区")
    private String areaId;
    /**
   * 所属医院
   */
	@ApiModelProperty(value = "所属医院")
    private String hospitalId;
    /**
   * 服务排序
   */
	@ApiModelProperty(value = "服务排序")
    private Integer sort;
    /**
   * 状态 0停用 1启用
   */
	@ApiModelProperty(value = "状态 0停用 1启用")
    private String packageState;
    /**
   * 协议类型 1: 基础 2：个性化
   */
	@ApiModelProperty(value = "协议类型 1: 基础 2：个性化")
    private String serviceType;
    /**
   * 是否有子节点 0：无 1：有
   */
	@ApiModelProperty(value = "是否有子节点 0：无 1：有")
	@NotBlank(message = "是否有子节点不能为空")
	private String hasChildren;
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
