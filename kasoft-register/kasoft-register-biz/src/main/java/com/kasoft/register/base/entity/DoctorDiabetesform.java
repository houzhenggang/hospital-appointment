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
 * 糖尿病随访
 *
 * @author charlie
 * @date 2019-06-08 10:40:08
 */
@Data
@TableName("twtsoft_doctor_diabetesform")
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "糖尿病随访")
public class DoctorDiabetesform extends Model<DoctorDiabetesform> {
private static final long serialVersionUID = 1L;

    /**
   * 主键
   */
	@TableId(value = "diabetes_form_id", type = IdType.UUID)
	@ApiModelProperty(value = "主键")
	private String diabetesFormId;
    /**
   * 居民编号
   */
	@ApiModelProperty(value = "居民编号")
	private String peopleId;
    /**
   * 身份证编号
   */
	@ApiModelProperty(value = "身份证编号")
	private String idCard;
    /**
   * 姓名
   */
	@ApiModelProperty(value = "姓名")
	private String name;
    /**
   * 本次随访时间
   */
	@ApiModelProperty(value = "本次随访时间")
	private LocalDateTime followTime;
    /**
   * 本次随访方式
   */
	@ApiModelProperty(value = "本次随访方式")
	private String followUpWay;
    /**
   * 症状列表
   */
	@ApiModelProperty(value = "症状列表", hidden = true)
	private String symptoms;
    /**
   * 其他症状列表
   */
	@ApiModelProperty(value = "其他症状列表", hidden = true)
	private String otherSymptoms;
    /**
   * 血压-收缩压
   */
	@ApiModelProperty(value = "血压-收缩压")
	private Integer systolic;
    /**
   * 血压-舒张压
   */
	@ApiModelProperty(value = "血压-舒张压")
	private Integer diastolic;
    /**
   * 心率
   */
	@ApiModelProperty(value = "心率")
	private String heartRate;
    /**
   * 空腹血糖
   */
	@ApiModelProperty(value = "空腹血糖")
	private String fastingPlasmaGlucose;
    /**
   * 随机血糖
   */
	@ApiModelProperty(value = "随机血糖")
	private String randomBloodSugar;
    /**
   * 餐后血糖
   */
	@ApiModelProperty(value = "餐后血糖")
	private String postprandialBloodSugar;
    /**
   * 糖化血红蛋白
   */
	@ApiModelProperty(value = "糖化血红蛋白")
	private String glycosylatedHemoglobin;
    /**
   * 糖化血红蛋白检查时间
   */
	@ApiModelProperty(value = "糖化血红蛋白检查时间")
	private String checkTime;
    /**
   * 身高
   */
	@ApiModelProperty(value = "身高")
	private Double height;
    /**
   * 体重
   */
	@ApiModelProperty(value = "体重")
	private Double weight;
    /**
   * 体质指数
   */
	@ApiModelProperty(value = "体质指数")
	private Double bmi;
    /**
   * BMI分析
   */
	@ApiModelProperty(value = "BMI分析")
	private String bmiAnalysis;
    /**
   * 足背动脉
   */
	@ApiModelProperty(value = "足背动脉")
	private String dorsalArteryOfFoot;
    /**
   * 其他体征列表
   */
	@ApiModelProperty(value = "其他体征列表", hidden = true)
	private String otherSigns;
    /**
   * 服药依从性
   */
	@ApiModelProperty(value = "服药依从性")
	private String medicationAdherence;
    /**
   * 药物不良反应
   */
	@ApiModelProperty(value = "药物不良反应")
	private String adverseDrugReactions;
    /**
   * 不良反应描述
   */
	@ApiModelProperty(value = "不良反应描述")
	private String responseDescribe;
    /**
   * 低血糖反应
   */
	@ApiModelProperty(value = "低血糖反应")
	private String lowBloodGlucoseReaction;
    /**
   * 此次随访分类
   */
	@ApiModelProperty(value = "此次随访分类")
	private String followUpType;
    /**
   * 下次随访日期
   */
	@ApiModelProperty(value = "下次随访日期")
	private LocalDateTime nextFollowTime;
    /**
   * 健康处方
   */
	@ApiModelProperty(value = "健康处方")
	private String prescriptionText;
    /**
   * 医院编号
   */
	@ApiModelProperty(value = "医院编号")
	private String hospitalId;
    /**
   * 团队编号
   */
	@ApiModelProperty(value = "团队编号")
	private String teamId;
    /**
   * 随访医生
   */
	@ApiModelProperty(value = "随访医生")
	private String doctorId;
	/**
	 * 签约编号
	 */
	@ApiModelProperty(value = "签约编号")
	private String agreementId;
	/**
	 * 套餐编号
	 */
	@ApiModelProperty(value = "套餐编号")
	private String packageId;
	/**
	 * 服务项目编号
	 */
	@ApiModelProperty(value = "服务项目编号")
	private String projectId;
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
