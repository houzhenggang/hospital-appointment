package com.kasoft.register.base.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 套餐树DTO
 * @author charlie
 */
@Data
@ApiModel(description = "套餐树DTO")
public class ServicepackageTree extends EdTreeNode {
	/**
	 * 套餐编码
	 */
	@ApiModelProperty(value = "套餐编码")
	private String packageCode;
	/**
	 * 套餐标签名称
	 */
	@ApiModelProperty(value = "套餐标签名称")
	private String packageLabel;
	/**
	 * 服务对象
	 */
	@ApiModelProperty(value = "服务对象")
	private String peopleTypeList;
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
	 * 是否有子节点 0：无 1：有
	 */
	@ApiModelProperty(value = "是否有子节点 0：无 1：有")
	private String hasChildren;
}
