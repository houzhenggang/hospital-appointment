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

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kasoft.register.base.dto.DoctorPeopleinfoDTO;
import com.kasoft.register.base.service.DeviceBloodpressureService;
import com.kasoft.register.base.service.DoctorHospitalService;
import com.kasoft.register.base.service.DoctorPeopleinfoService;
import com.kasoft.register.base.utils.EdConstants;
import com.kasoft.register.base.utils.PhysicalIndicatorsUtils;
import com.kasoft.register.base.entity.DeviceBloodpressure;
import com.kasoft.register.base.entity.DoctorHospital;
import com.kasoft.register.base.mapper.DeviceBloodpressureMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 血压管理
 *
 * @author charlie
 * @date 2019-07-11 16:35:20
 */
@Service
public class DeviceBloodpressureServiceImpl extends ServiceImpl<DeviceBloodpressureMapper, DeviceBloodpressure> implements DeviceBloodpressureService {
	@Autowired
	private DoctorHospitalService doctorHospitalService;
	@Autowired
	private DoctorPeopleinfoService doctorPeopleinfoService;

	@Override
	public boolean save(DeviceBloodpressure deviceBloodpressure) {
		completeData(deviceBloodpressure);
		return super.save(deviceBloodpressure);
	}

	@Override
	public boolean updateById(DeviceBloodpressure deviceBloodpressure) {
		completeData(deviceBloodpressure);
		return super.updateById(deviceBloodpressure);
	}

	/**
	 * 补全数据
	 * @param deviceBloodpressure 对象
	 */
	private void completeData(DeviceBloodpressure deviceBloodpressure) {
		if(StrUtil.isNotEmpty(deviceBloodpressure.getHospitalId())) {
			//补全地区数据
			DoctorHospital hospital = doctorHospitalService.getById(deviceBloodpressure.getHospitalId());
			if (hospital != null) {
				deviceBloodpressure.setAreaId(hospital.getAreaId());
			}
		}
		if(ObjectUtil.isNotNull(deviceBloodpressure.getUserId())) {
			//补全用户数据
			DoctorPeopleinfoDTO detailByUserId = doctorPeopleinfoService.getDetailByUserId(deviceBloodpressure.getUserId());
			if(detailByUserId != null) {
				deviceBloodpressure.setPeopleId(detailByUserId.getPeopleId());
				deviceBloodpressure.setIdCard(detailByUserId.getIdCard());
				deviceBloodpressure.setName(detailByUserId.getName());
			}
		}
		if(ObjectUtil.isNotNull(deviceBloodpressure.getSbp())) {
			//补全状态数据
			String exceptionType = PhysicalIndicatorsUtils.judgeBloodPressure(deviceBloodpressure.getSbp(), EdConstants.SBP.key);
			deviceBloodpressure.setSbpExceptionType(exceptionType);
		}
		if(ObjectUtil.isNotNull(deviceBloodpressure.getDbp())) {
			//补全状态数据
			String exceptionType = PhysicalIndicatorsUtils.judgeBloodPressure(deviceBloodpressure.getDbp(), EdConstants.DBP.key);
			deviceBloodpressure.setDbpExceptionType(exceptionType);
		}
		if(ObjectUtil.isNotNull(deviceBloodpressure.getHr())) {
			//补全状态数据
			String exceptionType = PhysicalIndicatorsUtils.judgePulse(deviceBloodpressure.getHr());
			deviceBloodpressure.setHrExceptionType(exceptionType);
		}
	}
}
