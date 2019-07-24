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

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kasoft.register.base.service.DoctorHealthprescriptService;
import com.kasoft.register.base.service.DoctorHospitalService;
import com.kasoft.register.base.entity.DoctorHealthprescript;
import com.kasoft.register.base.entity.DoctorHospital;
import com.kasoft.register.base.mapper.DoctorHealthprescriptMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 健康处方
 *
 * @author charlie
 * @date 2019-07-11 17:10:18
 */
@Service
public class DoctorHealthprescriptServiceImpl extends ServiceImpl<DoctorHealthprescriptMapper, DoctorHealthprescript> implements DoctorHealthprescriptService {
	@Autowired
	private DoctorHospitalService doctorHospitalService;

	@Override
	public boolean save(DoctorHealthprescript doctorDrugreminding) {
		if(StrUtil.isNotEmpty(doctorDrugreminding.getHospitalId())) {
			//补全地区数据
			DoctorHospital hospital = doctorHospitalService.getById(doctorDrugreminding.getHospitalId());
			if (hospital != null) {
				doctorDrugreminding.setAreaId(hospital.getAreaId());
			}
		}
		return super.save(doctorDrugreminding);
	}

	@Override
	public boolean updateById(DoctorHealthprescript doctorDrugreminding) {
		if(StrUtil.isNotEmpty(doctorDrugreminding.getHospitalId())) {
			//补全地区数据
			DoctorHospital hospital = doctorHospitalService.getById(doctorDrugreminding.getHospitalId());
			if (hospital != null) {
				doctorDrugreminding.setAreaId(hospital.getAreaId());
			}
		}
		return super.updateById(doctorDrugreminding);
	}
}
