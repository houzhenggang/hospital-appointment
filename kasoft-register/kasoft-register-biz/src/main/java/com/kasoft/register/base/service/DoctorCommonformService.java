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
import com.kasoft.register.base.dto.DoctorCommonformDTO;
import com.kasoft.register.base.entity.DoctorCommonform;

/**
 * 通用随访
 *
 * @author charlie
 * @date 2019-06-15 11:40:08
 */
public interface DoctorCommonformService extends IService<DoctorCommonform> {
	/**
	 * 保存根据编号保存通用随访对象
	 * @param entity
	 * @return
	 */
	boolean save(DoctorCommonformDTO entity);

	/**
	 * 根据编号更新通用随访对象
	 * @param entity
	 * @return
	 */
	boolean updateById(DoctorCommonformDTO entity);
}
