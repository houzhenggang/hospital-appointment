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
import com.kasoft.register.base.service.DoctorDrugremindingService;
import com.kasoft.register.base.service.DoctorHospitalService;
import com.kasoft.register.base.entity.DoctorDrugreminding;
import com.kasoft.register.base.entity.DoctorHospital;
import com.kasoft.register.base.mapper.DoctorDrugremindingMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 用药管理
 *
 * @author charlie
 * @date 2019-07-11 16:35:08
 */
@Service
public class DoctorDrugremindingServiceImpl extends ServiceImpl<DoctorDrugremindingMapper, DoctorDrugreminding> implements DoctorDrugremindingService {
	@Autowired
	private DoctorHospitalService doctorHospitalService;

	@Override
	public boolean save(DoctorDrugreminding doctorHealthprescript) {
		if(StrUtil.isNotEmpty(doctorHealthprescript.getHospitalId())) {
			//补全地区数据
			DoctorHospital hospital = doctorHospitalService.getById(doctorHealthprescript.getHospitalId());
			if (hospital != null) {
				doctorHealthprescript.setAreaId(hospital.getAreaId());
			}
		}
		return super.save(doctorHealthprescript);
	}

	@Override
	public boolean updateById(DoctorDrugreminding doctorHealthprescript) {
		if(StrUtil.isNotEmpty(doctorHealthprescript.getHospitalId())) {
			//补全地区数据
			DoctorHospital hospital = doctorHospitalService.getById(doctorHealthprescript.getHospitalId());
			if (hospital != null) {
				doctorHealthprescript.setAreaId(hospital.getAreaId());
			}
		}
		return super.updateById(doctorHealthprescript);
	}
}
