package com.kasoft.register.base.api.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * 地区树
 */
@Data
public class AreaTree extends EdTreeNode {
	/**
	 * 省市区名称
	 */
	@ApiModelProperty(value = "名称")
	@NotBlank(message = "名称不能为空")
	private String name;
	/**
	 * 类型 1：省 2：市 3：区
	 */
	@ApiModelProperty(value = "类型 1：省 2：市 3：区")
	@NotBlank(message = "类型不能为空")
	private String areaType;
}
