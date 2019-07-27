package com.kasoft.register.base.utils;

import com.kasoft.register.base.api.dto.EdTreeNode;
import lombok.experimental.UtilityClass;

import java.util.ArrayList;
import java.util.List;

/**
 * 构建树工具
 * @author charlie
 */
@UtilityClass
public class EdTreeUtils {
	/**
	 * 使用递归方法建树
	 *
	 * @param edTreeNodes
	 * @return
	 */
	public <T extends EdTreeNode> List<T> buildByRecursive(List<T> edTreeNodes, String root) {
		List<T> trees = new ArrayList<T>();
		for (T edTreeNode : edTreeNodes) {
			if (root.equals(edTreeNode.getParentId())) {
				trees.add(findChildren(edTreeNode, edTreeNodes));
			}
		}
		return trees;
	}

	/**
	 * 递归查找子节点
	 *
	 * @param edTreeNodes
	 * @return
	 */
	public <T extends EdTreeNode> T findChildren(T edTreeNode, List<T> edTreeNodes) {
		for (T it : edTreeNodes) {
			if (edTreeNode.getId().equals(it.getParentId())) {
				if (edTreeNode.getChildren() == null) {
					edTreeNode.setChildren(new ArrayList<>());
				}
				edTreeNode.add(findChildren(it, edTreeNodes));
			}
		}
		return edTreeNode;
	}

}
