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
 * 医生信息表
 *
 * @author charlie
 * @date 2019-04-30 17:10:49
 */
@Data
@TableName("kasoft_doctor_doctorinfo")
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "医生")
public class DoctorDoctorinfo extends Model<DoctorDoctorinfo> {
private static final long serialVersionUID = 1L;

    /**
   * 主键
   */
	@TableId(value = "doctor_id", type = IdType.UUID)
	@ApiModelProperty(value = "主键")
	private String doctorId;
    /**
   * 工号
   */
	@ApiModelProperty(value = "工号")
    private String code;
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
   * 性别  1：男  2：女 3: 未说明
   */
	@ApiModelProperty(value = "性别  1：男  2：女 3: 未说明")
    private String sex;
    /**
   * 手机号码
   */
	@ApiModelProperty(value = "手机号码")
    private String phone;
    /**
   * 出生日期
   */
	@ApiModelProperty(value = "出生日期")
    private String birthday;
    /**
   * 身份证
   */
	@ApiModelProperty(value = "身份证")
    private String idCard;
    /**
   * 人员类别  1：医生  2：护士  3：健康专员
   */
	@ApiModelProperty(value = "人员类别  1：医生  2：护士  3：健康专员")
    private String doctorType;
    /**
   * 医生职称
   */
	@ApiModelProperty(value = "医生职称")
    private String doctorJobTitle;
    /**
   * 护士职称
   */
	@ApiModelProperty(value = "护士职称")
    private String nurseJobTitle;
    /**
   * 接受咨询 0：接受 1：不接受
   */
	@ApiModelProperty(value = "接受咨询 0：接受 1：不接受")
    private String receiveCounseling;
    /**
   * 居住地址
   */
	@ApiModelProperty(value = "居住地址")
    private String address;
    /**
   * 个人擅长
   */
	@ApiModelProperty(value = "个人擅长")
    private String goodAt;
    /**
   * 所属地区
   */
	@ApiModelProperty(value = "所属地区")
    private String areaId;
    /**
   * 所属医院
   */
	@ApiModelProperty(value = "所属医院")
	@NotBlank(message = "所属医院不能为空")
	private String hospitalId;
    /**
   * 所属科室
   */
	@ApiModelProperty(value = "所属科室")
    private String departmentId;
    /**
   * 医生签名（url）
   */
	@ApiModelProperty(value = "医生签名（url）")
    private String doctorSign;

	/**
	 * 用户编号
	 */
	@ApiModelProperty(value = "用户编号")
	private Integer userId;

	/**
	 * 组织编号
	 */
	@ApiModelProperty(value = "组织编号")
	private String deptId;
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
