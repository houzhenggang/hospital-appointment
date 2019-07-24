package com.kasoft.register.base.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 字典对象
 *
 * @author charlie
 * @date 2019-05-07 15:43:03
 */
@Data
public class DictionariesBean implements Serializable {
	/**
	 * 标签
	 */
	private String label;
	/**
	 * 值
	 */
	private String value;
}
