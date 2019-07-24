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
import com.kasoft.register.base.entity.DoctorPeopleservicepackage;

import java.util.List;

/**
 * 居民签约协议明细
 *
 * @author charlie
 * @date 2019-05-21 21:54:31
 */
public interface DoctorPeopleservicepackageService extends IService<DoctorPeopleservicepackage> {
	/**
	 * 根据签约id删除签约项
	 * @param id 签约id
	 * @return
	 */
	boolean removeByAgreementId(String id);

	/**
	 * 根据签约号获取签约项列表
	 * @param id 签约编号
	 * @return 签约项列表
	 */
	List<DoctorPeopleservicepackage> getListByAgreementId(String id);
}
