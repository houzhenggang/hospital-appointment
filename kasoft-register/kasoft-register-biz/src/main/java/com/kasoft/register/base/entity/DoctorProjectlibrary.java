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
 * 项目库
 *
 * @author charlie
 * @date 2019-05-07 15:21:57
 */
@Data
@TableName("twtsoft_doctor_projectlibrary")
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "项目库")
public class DoctorProjectlibrary extends Model<DoctorProjectlibrary> {
private static final long serialVersionUID = 1L;

    /**
   * 主键
   */
	@TableId(value = "id", type = IdType.UUID)
	@ApiModelProperty(value = "主键")
	private String id;
    /**
   * 父级项目编号id
   */
	@ApiModelProperty(value = "父级项目编号id 必要")
	@NotBlank(message = "父级项目编号id不能为空")
	private String parentId;
    /**
   * 中心编码
   */
	@ApiModelProperty(value = "中心编码")
    private String centerCode;
    /**
   * 物价编码
   */
	@ApiModelProperty(value = "物价编码")
    private String priceCode;
    /**
   * 项目编号
   */
	@ApiModelProperty(value = "项目编号 重要")
	private String projectCode;
    /**
   * 项目名称
   */
	@ApiModelProperty(value = "项目名称 重要")
	@NotBlank(message = "项目名称不能为空")
    private String projectName;
    /**
   * 缩略图
   */
	@ApiModelProperty(value = "缩略图")
    private String thumbnail;
    /**
   * 宣传图
   */
	@ApiModelProperty(value = "宣传图")
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
   * 服务对象人群类别
   */
	@ApiModelProperty(value = "服务对象人群类别")
    private String peopleTypeList;
    /**
   * 项目类型 1: 公卫服务 2: 医疗服务
   */
	@ApiModelProperty(value = "项目类型 1: 公卫服务 2: 医疗服务", hidden = true)
    private String projectType;
    /**
   * 项目内容
   */
	@ApiModelProperty(value = "项目内容")
    private String projectContent;
    /**
   * 项目除外内容
   */
	@ApiModelProperty(value = "项目除外内容")
    private String projectNoContent;
    /**
   * 项目备注
   */
	@ApiModelProperty(value = "项目备注")
    private String projectRemark;
    /**
   * 价格总额，执行价格
   */
	@ApiModelProperty(value = "价格总额，执行价格")
    private BigDecimal projectPrice;
    /**
   * 调整价格
   */
	@ApiModelProperty(value = "调整价格", hidden = true)
    private BigDecimal adjustPrice;
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
   * 建议服务频率
   */
	@ApiModelProperty(value = "建议服务频率")
    private Integer serviceFrequency;
    /**
   * 服务单位
   */
	@ApiModelProperty(value = "服务单位")
    private String serviceUnit;
    /**
   * 业务关联
   */
	@ApiModelProperty(value = "业务关联")
    private String businessContext;
    /**
   * 适用范围 1：国 2：省 3：市 4：区 5：机构
   */
	@ApiModelProperty(value = "适用范围 1：国 2：省 3：市 4：区 5：机构", hidden = true)
    private String projectScope;
    /**
   * 服务地区
   */
	@ApiModelProperty(value = "服务地区")
    private String projectArea;
    /**
   * 服务机构
   */
	@ApiModelProperty(value = "服务机构 重要")
    private String projectHospital;
    /**
   * 项目排序
   */
	@ApiModelProperty(value = "项目排序 重要")
    private Integer sort;
    /**
   * 状态 0停用 1启用
   */
	@ApiModelProperty(value = "状态 0停用 1启用")
    private String projectState;
    /**
   * 是否有子节点 0：无 1：有
   */
	@ApiModelProperty(value = "是否有子节点 0：无 1：有 重要")
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
