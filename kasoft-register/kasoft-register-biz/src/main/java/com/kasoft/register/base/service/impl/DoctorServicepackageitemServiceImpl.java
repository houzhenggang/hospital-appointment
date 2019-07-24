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
import com.kasoft.register.base.service.DoctorServicepackageitemService;
import com.kasoft.register.base.entity.DoctorServicepackageitem;
import com.kasoft.register.base.mapper.DoctorServicepackageitemMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 服务包项目
 *
 * @author charlie
 * @date 2019-05-21 21:55:26
 */
@Service
public class DoctorServicepackageitemServiceImpl extends ServiceImpl<DoctorServicepackageitemMapper, DoctorServicepackageitem> implements DoctorServicepackageitemService {

	@Override
	public boolean removeByServicepackageId(String id) {
		//查询出所有的套餐项目
		DoctorServicepackageitem servicepackageitem = new DoctorServicepackageitem();
		servicepackageitem.setPackageId(id);
		List<DoctorServicepackageitem> servicepackageitemList = list(Wrappers.query(servicepackageitem));
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
	public List<DoctorServicepackageitem> getListByServicepackageId(String id) {
		//查询出所有的套餐项目
		DoctorServicepackageitem servicepackageitem = new DoctorServicepackageitem();
		servicepackageitem.setPackageId(id);
		return  list(Wrappers.query(servicepackageitem));
	}
}
