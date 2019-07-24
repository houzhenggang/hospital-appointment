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
 * 服务团队
 *
 * @author charlie
 * @date 2019-04-30 17:10:16
 */
@Data
@TableName("twtsoft_doctor_serviceteam")
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "服务团队")
public class DoctorServiceteam extends Model<DoctorServiceteam> {
private static final long serialVersionUID = 1L;

    /**
   * 主键
   */
	@TableId(value = "id", type = IdType.UUID)
	@ApiModelProperty(value = "主键")
	private String id;
    /**
   * 团队编号
   */
	@ApiModelProperty(value = "团队编号")
    private String teamCode;
    /**
   * 团队头像
   */
	@ApiModelProperty(value = "团队头像")
    private String headImg;
    /**
   * 团队名称
   */
	@ApiModelProperty(value = "团队名称")
	@NotBlank(message = "团队名称不能为空")
    private String name;
    /**
   * 团队口号
   */
	@ApiModelProperty(value = "团队口号")
    private String slogan;
    /**
   * 第一联系人
   */
	@ApiModelProperty(value = "第一联系人")
    private String contactOne;
    /**
   * 第二联系人
   */
	@ApiModelProperty(value = "第二联系人")
    private String contactTwo;
    /**
   * 队长
   */
	@ApiModelProperty(value = "队长")
    private String teamLeader;
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
