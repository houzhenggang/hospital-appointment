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
package com.kasoft.register.base.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kasoft.register.base.service.DoctorHospitalService;
import com.kasoft.register.base.utils.EdConstants;
import com.kasoft.register.base.entity.DoctorHospital;
import com.kasoft.register.base.mapper.DoctorHospitalMapper;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 医院
 *
 * @author charlie
 * @date 2019-04-30 17:09:57
 */
@Service
public class DoctorHospitalServiceImpl extends ServiceImpl<DoctorHospitalMapper, DoctorHospital> implements DoctorHospitalService {

	@Override
	public List<DoctorHospital> getHospitalByName(String name) {
		return this.baseMapper.getHospitalByName(name);
	}

	@Override
	@Cacheable(value = EdConstants.ED_HOSPITAL_DETAILS, key = "#id", unless = "#result == null ")
	public DoctorHospital getById(String id) {
		return super.getById(id);
	}
}
