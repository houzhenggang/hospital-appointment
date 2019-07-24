package com.kasoft.register.base.entity;

import lombok.Data;

/**
 * 档案name time 实体bean
 *
 * @author charlie
 * @date 2019-05-07 15:43:03
 */
@Data
public class ArchiveNameTimeBean {
	/**
	 * 序号
	 */
	private Integer index;
	/**
	 * 名称 或 原因
	 */
	private String name;
	/**
	 * 时间
	 */
	private String time;
}
