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
import com.kasoft.register.base.dto.AreaTree;
import com.kasoft.register.base.entity.DoctorAreadictionary;

import java.util.List;

/**
 * 地区字典
 *
 * @author charlie
 * @date 2019-04-30 17:10:28
 */
public interface DoctorAreadictionaryService extends IService<DoctorAreadictionary> {
	/**
	 * 根据id查询地区信息
	 * @param id 地区编号
	 * @return 地区对象
	 */
	DoctorAreadictionary getById(String id);

	/**
	 * 获取全部地区树
	 * @return
	 */
	List<AreaTree> allTree();
}
