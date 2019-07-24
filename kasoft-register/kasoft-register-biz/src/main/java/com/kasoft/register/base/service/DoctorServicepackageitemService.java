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

package com.kasoft.register.base.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.kasoft.register.base.entity.DoctorServicepackageitem;

import java.util.List;

/**
 * 服务包项目
 *
 * @author charlie
 * @date 2019-05-21 21:55:26
 */
public interface DoctorServicepackageitemService extends IService<DoctorServicepackageitem> {
	/**
	 * 根据套餐包id删除套餐项
	 * @param id 套餐包id
	 * @return
	 */
	boolean removeByServicepackageId(String id);

	/**
	 * 根据套餐编号获取套餐项列表
	 * @param id 套餐编号
	 * @return 套餐项列表
	 */
	List<DoctorServicepackageitem> getListByServicepackageId(String id);
}
