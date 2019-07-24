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
import com.kasoft.register.base.dto.DeviceApplicationDTO;
import com.kasoft.register.base.entity.DeviceApplication;

/**
 * 居民自助建档签约
 *
 * @author charlie
 * @date 2019-05-14 10:55:40
 */
public interface DeviceApplicationService extends IService<DeviceApplication> {

	/**
	 * 根据编号查询自助建档签约dto对象
	 * @param id
	 * @return
	 */
	DeviceApplicationDTO getById(String id);

	/**
	 * 保存自助建档签约dto对象
	 * @param entity
	 * @return
	 */
	boolean save(DeviceApplicationDTO entity);

	/**
	 * 根据编号更新自助建档签约dto对象
	 * @param entity
	 * @return
	 */
	boolean updateById(DeviceApplicationDTO entity);

}
