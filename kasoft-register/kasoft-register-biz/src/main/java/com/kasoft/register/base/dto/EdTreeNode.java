package com.kasoft.register.base.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * ed树的Dto
 *
 * @author charlie
 */
@Data
@ApiModel(description = "树的Dto")
public class EdTreeNode implements Serializable {
	@ApiModelProperty(value = "项目编号id")
	protected String id;
	@ApiModelProperty(value = "父级项目编号id")
	protected String parentId;
	@ApiModelProperty(value = "子级项目列表")
	protected List<EdTreeNode> children = new ArrayList<EdTreeNode>();

	/**
	 * 添加子节点
	 * @param node
	 */
	public void add(EdTreeNode node) {
		children.add(node);
	}
}
