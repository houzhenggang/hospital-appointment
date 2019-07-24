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
import com.kasoft.register.base.service.DoctorDoctorinfoteamService;
import com.kasoft.register.base.service.DoctorHospitalService;
import com.kasoft.register.base.service.DoctorServiceteamService;
import com.kasoft.register.base.entity.DoctorHospital;
import com.kasoft.register.base.entity.DoctorServiceteam;
import com.kasoft.register.base.mapper.DoctorServiceteamMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 服务团队
 *
 * @author charlie
 * @date 2019-04-30 17:10:16
 */
@Service
public class DoctorServiceteamServiceImpl extends ServiceImpl<DoctorServiceteamMapper, DoctorServiceteam> implements DoctorServiceteamService {
	@Autowired
	private DoctorHospitalService doctorHospitalService;
	@Autowired
	private DoctorDoctorinfoteamService doctorinfoteamService;
	/**
	 * 保存 带依赖
	 * @param entity
	 * @return
	 */
	@Override
	public boolean save(DoctorServiceteam entity) {
		DoctorHospital hospital = doctorHospitalService.getById(entity.getHospitalId());
		if(hospital != null) {
			entity.setAreaId(hospital.getAreaId());
		}
		return super.save(entity);
	}

	/**
	 * 根据id更新 带依赖
	 * @param entity
	 * @return
	 */
	@Override
	public boolean updateById(DoctorServiceteam entity) {
		DoctorHospital hospital = doctorHospitalService.getById(entity.getHospitalId());
		if(hospital != null) {
			entity.setAreaId(hospital.getAreaId());
		}
		return super.updateById(entity);
	}

	@Override
	public List<DoctorServiceteam> getServiceteamDictByHospital(String hospitalId) {
		return this.baseMapper.getServiceteamDictByHospital(hospitalId);
	}

	@Override
	public boolean removeById(String id) {
		//先删除团队成员
		doctorinfoteamService.removeByTeamId(id);
		//再删除团队
		return super.removeById(id);
	}
}
