package com.kasoft.register.base.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * 项目库树dto
 * @author charlie
 */
@Data
@ApiModel(description = "项目库树dto")
public class ProjectlibraryTree extends EdTreeNode {

	/**
	 * 项目编号
	 */
	@ApiModelProperty(value = "项目编号")
	private String projectCode;
	/**
	 * 项目名称
	 */
	@ApiModelProperty(value = "项目名称")
	@NotBlank(message = "项目名称不能为空")
	private String projectName;
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
	 * 项目内容
	 */
	@ApiModelProperty(value = "项目内容")
	private String projectContent;
	/**
	 * 项目备注
	 */
	@ApiModelProperty(value = "项目备注")
	private String projectRemark;
	/**
	 * 服务地区
	 */
	@ApiModelProperty(value = "服务地区")
	private String projectArea;
	/**
	 * 服务机构
	 */
	@ApiModelProperty(value = "服务机构")
	private String projectHospital;
	/**
	 * 项目排序
	 */
	@ApiModelProperty(value = "项目排序")
	private Integer sort;
	/**
	 * 是否有子节点 0：无 1：有
	 */
	@ApiModelProperty(value = "是否有子节点 0：无 1：有")
	private String hasChildren;
}
