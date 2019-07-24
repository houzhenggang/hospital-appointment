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
 * 资讯
 *
 * @author charlie
 * @date 2019-05-26 14:11:18
 */
@Data
@TableName("twtsoft_doctor_information")
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "资讯")
public class DoctorInformation extends Model<DoctorInformation> {
private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	@TableId(value = "id", type = IdType.UUID)
	@ApiModelProperty(value = "主键")
	private String id;
    /**
   * 标题
   */
	@ApiModelProperty(value = "标题")
	@NotBlank(message = "标题不能为空")
	private String title;
    /**
   * 简述
   */
	@ApiModelProperty(value = "简述")
    private String simple;
    /**
   * 正文
   */
	@ApiModelProperty(value = "正文")
	@NotBlank(message = "正文不能为空")
    private String body;
    /**
   * 标签类型
   */
	@ApiModelProperty(value = "标签类型-字典")
    private String tagType;
    /**
   * 资讯分类
   */
	@ApiModelProperty(value = "资讯分类-字典")
    private String informationType;
    /**
   * 宣传图片
   */
	@ApiModelProperty(value = "宣传图片")
    private String smallImage;
    /**
   * 作者
   */
	@ApiModelProperty(value = "作者")
    private String author;
    /**
   * 阅读次数
   */
	@ApiModelProperty(value = "阅读次数")
    private Integer redNum;
    /**
   * 首页展现 0：是；1：否
   */
	@ApiModelProperty(value = "首页展现 0：是；1：否", hidden = true)
    private String homeDisplay;
    /**
   * 是否公开 0：专属；1：公开
   */
	@ApiModelProperty(value = "是否公开 0：专属；1：公开", hidden = true)
    private String publicPermissions;
    /**
   * 包含医院编号列表
   */
	@ApiModelProperty(value = "包含医院编号列表,号分割", hidden = true)
    private String hospitalId;
    /**
   * 是否展示
   */
	@ApiModelProperty(value = "是否展示 0：是；1：否")
    private String state;
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
