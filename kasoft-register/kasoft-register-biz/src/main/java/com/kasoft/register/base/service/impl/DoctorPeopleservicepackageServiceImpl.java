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
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kasoft.register.base.service.DoctorPeopleservicepackageService;
import com.kasoft.register.base.entity.DoctorPeopleservicepackage;
import com.kasoft.register.base.mapper.DoctorPeopleservicepackageMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 居民签约协议明细
 *
 * @author charlie
 * @date 2019-05-21 21:54:31
 */
@Service
public class DoctorPeopleservicepackageServiceImpl extends ServiceImpl<DoctorPeopleservicepackageMapper, DoctorPeopleservicepackage> implements DoctorPeopleservicepackageService {

	@Override
	public boolean removeByAgreementId(String id) {
		//查询出所有的签约项
		DoctorPeopleservicepackage peopleservicepackage = new DoctorPeopleservicepackage();
		peopleservicepackage.setPackageId(id);
		List<DoctorPeopleservicepackage> servicepackageitemList = list(Wrappers.query(peopleservicepackage));
		//循环出id
		List<String> idList = new ArrayList<>();
		servicepackageitemList.forEach(item -> {
			idList.add(item.getId());
		});
		//批量删除id
		if(CollUtil.isNotEmpty(idList)) {
			return super.removeByIds(idList);
		}
		return true;
	}

	@Override
	public List<DoctorPeopleservicepackage> getListByAgreementId(String id) {
		//查询出所有的签约项目
		DoctorPeopleservicepackage servicepackageitem = new DoctorPeopleservicepackage();
		servicepackageitem.setAgreementId(id);
		return  list(Wrappers.query(servicepackageitem));
	}
}
