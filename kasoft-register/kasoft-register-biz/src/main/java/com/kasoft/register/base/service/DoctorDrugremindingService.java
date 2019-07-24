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
import com.kasoft.register.base.entity.DoctorDrugreminding;

/**
 * 用药管理
 *
 * @author charlie
 * @date 2019-07-11 16:35:08
 */
public interface DoctorDrugremindingService extends IService<DoctorDrugreminding> {
	/**
	 * 更新数据
	 * @param doctorDrugreminding
	 * @return
	 */
	@Override
	boolean updateById(DoctorDrugreminding doctorDrugreminding);

	/**
	 * 保存数据
	 * @param doctorDrugreminding
	 * @return
	 */
	@Override
	boolean save(DoctorDrugreminding doctorDrugreminding);
}
