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
import com.kasoft.register.base.service.DeviceBloodsugarService;
import com.kasoft.register.base.service.DoctorHospitalService;
import com.kasoft.register.base.service.DoctorPeopleinfoService;
import com.kasoft.register.base.utils.PhysicalIndicatorsUtils;
import com.kasoft.register.base.entity.DeviceBloodsugar;
import com.kasoft.register.base.entity.DoctorHospital;
import com.kasoft.register.base.mapper.DeviceBloodsugarMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 血糖管理
 *
 * @author charlie
 * @date 2019-07-11 16:35:16
 */
@Service
public class DeviceBloodsugarServiceImpl extends ServiceImpl<DeviceBloodsugarMapper, DeviceBloodsugar> implements DeviceBloodsugarService {
	@Autowired
	private DoctorHospitalService doctorHospitalService;
	@Autowired
	private DoctorPeopleinfoService doctorPeopleinfoService;
	@Override
	public boolean save(DeviceBloodsugar deviceBloodsugar) {
		completeData(deviceBloodsugar);
		return super.save(deviceBloodsugar);
	}

	@Override
	public boolean updateById(DeviceBloodsugar deviceBloodsugar) {
		completeData(deviceBloodsugar);
		return super.updateById(deviceBloodsugar);
	}
	/**
	 * 补全数据
	 * @param deviceBloodsugar 对象
	 */
	private void completeData(DeviceBloodsugar deviceBloodsugar) {
		if(StrUtil.isNotEmpty(deviceBloodsugar.getHospitalId())) {
			//补全地区数据
			DoctorHospital hospital = doctorHospitalService.getById(deviceBloodsugar.getHospitalId());
			if (hospital != null) {
				deviceBloodsugar.setAreaId(hospital.getAreaId());
			}
		}
		if(ObjectUtil.isNotNull(deviceBloodsugar.getUserId())) {
			//补全用户数据
			DoctorPeopleinfoDTO detailByUserId = doctorPeopleinfoService.getDetailByUserId(deviceBloodsugar.getUserId());
			if(detailByUserId != null) {
				deviceBloodsugar.setPeopleId(detailByUserId.getPeopleId());
				deviceBloodsugar.setIdCard(detailByUserId.getIdCard());
				deviceBloodsugar.setName(detailByUserId.getName());
			}
		}
		if(StrUtil.isNotEmpty(deviceBloodsugar.getGlu()) && StrUtil.isNotEmpty(deviceBloodsugar.getGluType())) {
			//补全状态数据
			String exceptionType = PhysicalIndicatorsUtils.judgeBloodSugar(deviceBloodsugar.getGlu(), deviceBloodsugar.getGluType());
			deviceBloodsugar.setExceptionType(exceptionType);
		}
	}
}
