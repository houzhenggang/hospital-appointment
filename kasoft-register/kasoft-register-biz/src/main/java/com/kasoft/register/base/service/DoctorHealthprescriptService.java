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
import com.kasoft.register.base.entity.DoctorHealthprescript;

/**
 * 健康处方
 *
 * @author charlie
 * @date 2019-07-11 17:10:18
 */
public interface DoctorHealthprescriptService extends IService<DoctorHealthprescript> {
	/**
	 * 更新数据
	 * @param doctorHealthprescript
	 * @return
	 */
	@Override
	boolean updateById(DoctorHealthprescript doctorHealthprescript);

	/**
	 * 保存数据
	 * @param doctorHealthprescript
	 * @return
	 */
	@Override
	boolean save(DoctorHealthprescript doctorHealthprescript);
}
