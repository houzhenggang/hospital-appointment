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
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kasoft.register.base.dto.DoctorPeopleinfoDTO;
import com.kasoft.register.base.service.DeviceImuserService;
import com.kasoft.register.base.service.DoctorDoctorinfoService;
import com.kasoft.register.base.service.DoctorPeopleinfoService;
import com.kasoft.register.base.utils.EdConstants;
import com.kasoft.register.base.entity.DeviceImuser;
import com.kasoft.register.base.entity.DoctorDoctorinfo;
import com.kasoft.register.base.mapper.DeviceImuserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * IM用户管理
 *
 * @author charlie
 * @date 2019-07-21 14:02:04
 */
@Service
public class DeviceImuserServiceImpl extends ServiceImpl<DeviceImuserMapper, DeviceImuser> implements DeviceImuserService {
	@Autowired
	private DoctorPeopleinfoService doctorPeopleinfoService;
	@Autowired
	private DoctorDoctorinfoService doctorDoctorinfoService;
	@Override
	public boolean save(DeviceImuser deviceImuser) {
		completeData(deviceImuser);
		return super.save(deviceImuser);
	}

	@Override
	public boolean updateById(DeviceImuser deviceImuser) {
		completeData(deviceImuser);
		return super.updateById(deviceImuser);
	}

	/**
	 * 补全数据
	 * @param deviceImuser 对象
	 */
	private void completeData(DeviceImuser deviceImuser) {
		if(ObjectUtil.isNotNull(deviceImuser.getUserType())) {
			if(EdConstants.UserType.PEOPLE.equals(deviceImuser.getUserType())) {
				//补全用户数据
				DoctorPeopleinfoDTO detailByUserId = doctorPeopleinfoService.getDetailByUserId(deviceImuser.getUserId());
				if(detailByUserId != null) {
					deviceImuser.setAreaId(detailByUserId.getAreaId());
					deviceImuser.setHospitalId(detailByUserId.getHospitalId());
					deviceImuser.setPeopleId(detailByUserId.getPeopleId());
					deviceImuser.setName(detailByUserId.getName());
				}
			} else {
				DoctorDoctorinfo doctorbyUserId = doctorDoctorinfoService.getByUserId(deviceImuser.getUserId());
				if(doctorbyUserId != null) {
					deviceImuser.setAreaId(doctorbyUserId.getAreaId());
					deviceImuser.setHospitalId(doctorbyUserId.getHospitalId());
					deviceImuser.setDoctorId(doctorbyUserId.getId());
					deviceImuser.setName(doctorbyUserId.getName());
				}
			}
		}
	}

}
