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
 * 居民基本信息表
 *
 * @author charlie
 * @date 2019-05-07 15:21:30
 */
@Data
@TableName("twtsoft_doctor_peopleinfo")
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "档案")
public class DoctorPeopleinfo extends Model<DoctorPeopleinfo> {
private static final long serialVersionUID = 1L;

    /**
   * 主键
   */
	@TableId(value = "id", type = IdType.UUID)
	@ApiModelProperty(value = "主键")
	private String id;
    /**
   * 医保卡号
   */
	@ApiModelProperty(value = "医保卡号")
    private String cardNumber;
    /**
   * 身份证
   */
	@ApiModelProperty(value = "身份证")
	@NotBlank(message = "身份证不能为空")
    private String idCard;
    /**
   * 身份证正面照片
   */
	@ApiModelProperty(value = "身份证正面照片")
    private String idCardFront;
    /**
   * 身份证背面照片
   */
	@ApiModelProperty(value = "身份证背面照片")
    private String idCardBack;
    /**
   * 头像
   */
	@ApiModelProperty(value = "头像")
    private String headImg;
    /**
   * 姓名
   */
	@ApiModelProperty(value = "姓名")
	@NotBlank(message = "姓名不能为空")
	private String name;
    /**
   * 昵称
   */
	@ApiModelProperty(value = "昵称")
    private String nickname;
    /**
   * 联系电话
   */
	@ApiModelProperty(value = "联系电话")
    private String phone;
    /**
   * 固定电话
   */
	@ApiModelProperty(value = "固定电话")
    private String tel;
    /**
   * 性别  1：男  2：女 3：未说明
   */
	@ApiModelProperty(value = "性别  1：男  2：女 3：未说明")
    private String sex;
    /**
   * 出生日期
   */
	@ApiModelProperty(value = "出生日期")
    private String birthDate;
    /**
   * 建档单位-医院
   */
	@ApiModelProperty(value = "建档单位-医院")
    private String createArchiveUnits;
    /**
   * 建档人
   */
	@ApiModelProperty(value = "建档人")
    private String createArchivePeople;
    /**
   * 建档日期
   */
	@ApiModelProperty(value = "建档日期")
    private LocalDateTime createArchiveTime;
    /**
   * 责任医院
   */
	@ApiModelProperty(value = "责任医院")
	@NotBlank(message = "责任医院不能为空")
	private String hospitalId;
    /**
   * 责任团队
   */
	@ApiModelProperty(value = "责任团队")
	private String teamId;
    /**
   * 责任医生
   */
	@ApiModelProperty(value = "责任医生")
    private String doctorId;
    /**
   * 联系人姓名
   */
	@ApiModelProperty(value = "联系人姓名")
    private String linkName;
    /**
   * 联系人电话
   */
	@ApiModelProperty(value = "联系人电话")
    private String linkPhone;
    /**
   * 院内标签
   */
	@ApiModelProperty(value = "院内标签")
    private String hospitaltag;
    /**
   * 人群分类
   */
	@ApiModelProperty(value = "人群分类")
    private String peopleTypeList;
    /**
   * 摘要
   */
	@ApiModelProperty(value = "摘要")
    private String note;
    /**
   * 意向套餐
   */
	@ApiModelProperty(value = "意向套餐", hidden = true)
    private String intentionPackage;
    /**
   * 当前服务套餐
   */
	@ApiModelProperty(value = "当前服务套餐", hidden = true)
    private String servicePackage;
    /**
   * 协议编号
   */
	@ApiModelProperty(value = "协议编号", hidden = true)
    private String agreementCode;
    /**
   * 基础套餐
   */
	@ApiModelProperty(value = "基础套餐", hidden = true)
    private String basisName;
    /**
   * 个性套餐
   */
	@ApiModelProperty(value = "个性套餐", hidden = true)
    private String personalityName;
    /**
   * 服务套餐开始时间
   */
	@ApiModelProperty(value = "服务套餐开始时间", hidden = true)
    private LocalDateTime startServiceTime;
    /**
   * 服务套餐结束时间
   */
	@ApiModelProperty(value = "服务套餐结束时间", hidden = true)
    private LocalDateTime endServiceTime;
    /**
   * 最后一次服务时间
   */
	@ApiModelProperty(value = "最后一次服务时间")
    private LocalDateTime lastServiceTime;
    /**
   * 下次服务时间
   */
	@ApiModelProperty(value = "下次服务时间")
    private LocalDateTime nextServiceTime;
    /**
   * 服务总次数
   */
	@ApiModelProperty(value = "服务总次数", hidden = true)
    private Integer serviceNum;
    /**
   * 协议类型 1: 基础 2：个性化
   */
	@ApiModelProperty(value = "协议类型 1: 基础 2：个性化", hidden = true)
    private String serviceType;
    /**
   * 档案所属地区
   */
	@ApiModelProperty(value = "档案所属地区")
    private String areaId;
    /**
   * 人员状态 1: 在管 2：死亡 3：迁出 4: 失访 5: 拒绝
   */
	@ApiModelProperty(value = "人员状态 1: 在管 2：死亡 3：迁出 4: 失访 5: 拒绝")
    private String peopleState;
    /**
   * 异常状态 1: 正常 2：异常
   */
	@ApiModelProperty(value = "异常状态 1: 正常 2：异常")
    private String abnormalState;
    /**
   * 异常时间
   */
	@ApiModelProperty(value = "异常时间")
    private LocalDateTime abnormalTime;
    /**
   * 申请状态 0: 无 1：有
   */
	@ApiModelProperty(value = "申请状态 0: 无 1：有")
    private String applyState;
    /**
   * 申请时间
   */
	@ApiModelProperty(value = "申请时间")
    private LocalDateTime applyTime;
    /**
   * 家庭编号
   */
    @ApiModelProperty(value = "家庭编号")
    private String familyId;
    /**
   * 第三方关联编号
   */
	@ApiModelProperty(value = "第三方关联编号")
    private String syncId;
    /**
   * 关联用户编号
   */
	@ApiModelProperty(value = "关联用户编号")
    private Integer userId;
	/**
	 * 自助签约编号
	 */
	@ApiModelProperty(value = "自助签约编号")
	private String applicationId;
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
