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
import com.kasoft.register.base.entity.DoctorPeopleinfo;
import com.pig4cloud.pigx.admin.api.dto.UserDTO;
import com.pig4cloud.pigx.common.core.util.R;

/**
 * 居民基本信息表
 *
 * @author charlie
 * @date 2019-07-26 09:58:13
 */
public interface DoctorPeopleinfoService extends IService<DoctorPeopleinfo> {

	/**
	 * 注册
	 * @param userDTO 用户信息
	 * @return
	 */
	R<Boolean> register(UserDTO userDTO);

	/**
	 * 用户名是否存在
	 * @param userName 用户名
	 * @return
	 */
	boolean hasUserName(String userName);
}
