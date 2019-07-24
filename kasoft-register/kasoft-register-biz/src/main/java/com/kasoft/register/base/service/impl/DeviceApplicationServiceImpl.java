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

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kasoft.register.base.dto.DeviceApplicationDTO;
import com.kasoft.register.base.service.DeviceApplicationService;
import com.kasoft.register.base.service.DoctorServicepackageService;
import com.kasoft.register.base.entity.DeviceApplication;
import com.kasoft.register.base.entity.DoctorServicepackage;
import com.kasoft.register.base.mapper.DeviceApplicationMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * 居民自助建档签约
 *
 * @author charlie
 * @date 2019-05-14 10:55:40
 */
@Service
public class DeviceApplicationServiceImpl extends ServiceImpl<DeviceApplicationMapper, DeviceApplication> implements DeviceApplicationService {

	@Autowired
	private DoctorServicepackageService doctorServicepackageService;

	@Override
	public DeviceApplicationDTO getById(String id) {
		DeviceApplicationDTO deviceApplicationDTO = new DeviceApplicationDTO();
		DeviceApplication deviceApplication = super.getById(id);
		if(ObjectUtil.isNotNull(deviceApplication)) {
			BeanUtils.copyProperties(deviceApplication, deviceApplicationDTO);
			//转换list对象
			if(StrUtil.isNotEmpty(deviceApplication.getServiceId())) {
				String[] ids = deviceApplication.getServiceId().split(",");
				Collection<DoctorServicepackage> doctorServicepackageList = doctorServicepackageService.listByIds(CollUtil.newArrayList(ids));
				deviceApplicationDTO.setDoctorServicepackageList(CollUtil.newArrayList(doctorServicepackageList));
			}
		}
		return deviceApplicationDTO;
	}

	@Override
	public boolean save(DeviceApplicationDTO entity) {
		DeviceApplication deviceApplication = new DeviceApplication();
		BeanUtils.copyProperties(entity, deviceApplication);
		//数组组装
		arrayJoin(deviceApplication, entity);
		return super.save(deviceApplication);
	}

	@Override
	public boolean updateById(DeviceApplicationDTO entity) {
		DeviceApplication deviceApplication = new DeviceApplication();
		BeanUtils.copyProperties(entity, deviceApplication);
		//数组组装
		arrayJoin(deviceApplication, entity);
		return super.updateById(deviceApplication);
	}

	/**
	 * 组装数组
	 * @param deviceApplication
	 * @param entity
	 */
	private void arrayJoin(DeviceApplication deviceApplication, DeviceApplicationDTO entity) {
		if(CollUtil.isNotEmpty(entity.getDoctorServicepackageList())) {
			List<String> idList = new ArrayList<>();
			List<String> nameList = new ArrayList<>();
			entity.getDoctorServicepackageList().forEach(item -> {
				idList.add(item.getId());
				nameList.add(item.getPackageLabel());
			});
			deviceApplication.setServiceId(CollUtil.join(idList, ","));
			deviceApplication.setServiceName(CollUtil.join(nameList, ","));
		}
	}
}
