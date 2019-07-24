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
import com.kasoft.register.base.service.DoctorDoctorinfoteamService;
import com.kasoft.register.base.entity.DoctorDoctorinfoteam;
import com.kasoft.register.base.mapper.DoctorDoctorinfoteamMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 医生团队表
 *
 * @author charlie
 * @date 2019-04-30 17:10:54
 */
@Service
public class DoctorDoctorinfoteamServiceImpl extends ServiceImpl<DoctorDoctorinfoteamMapper, DoctorDoctorinfoteam> implements DoctorDoctorinfoteamService {

	@Override
	public boolean removeByTeamId(String id) {
		//查询出所有的套餐项目
		DoctorDoctorinfoteam doctorDoctorinfoteam = new DoctorDoctorinfoteam();
		doctorDoctorinfoteam.setTeamId(id);
		List<DoctorDoctorinfoteam> doctorDoctorinfoteamList = list(Wrappers.query(doctorDoctorinfoteam));
		//循环出id
		List<String> idList = new ArrayList<>();
		doctorDoctorinfoteamList.forEach(item -> {
			idList.add(item.getId());
		});
		//批量删除id
		if(CollUtil.isNotEmpty(idList)) {
			return super.removeByIds(idList);
		}
		return true;
	}

	@Override
	public boolean saveRemoveList(String teamId, List<String> doctorIdList) {
		//查询出所有的成员
		DoctorDoctorinfoteam doctorDoctorinfoteam = new DoctorDoctorinfoteam();
		doctorDoctorinfoteam.setTeamId(teamId);
		List<DoctorDoctorinfoteam> doctorDoctorinfoteamList = list(Wrappers.query(doctorDoctorinfoteam));
		List<String> nowDocIdList = new ArrayList<>();
		List<String> delIdList = new ArrayList<>();
		List<DoctorDoctorinfoteam> addList = new ArrayList<>();
		//查找删除数据
		doctorDoctorinfoteamList.forEach(item -> {
			nowDocIdList.add(item.getDoctorId());
			if(!doctorIdList.contains(item.getDoctorId())) {
				delIdList.add(item.getId());
			}
		});
		//查找添加数据
		doctorIdList.forEach(item -> {
			if(!nowDocIdList.contains(item)) {
				DoctorDoctorinfoteam doctorDoctorinfoteam1 = new DoctorDoctorinfoteam();
				doctorDoctorinfoteam1.setTeamId(teamId);
				doctorDoctorinfoteam1.setDoctorId(item);
				addList.add(doctorDoctorinfoteam1);
			}
		});
		//删除数据
		if(CollUtil.isNotEmpty(delIdList)) {
			super.removeByIds(delIdList);
		}
		//添加数据
		if(CollUtil.isNotEmpty(addList)) {
			super.saveBatch(addList);
		}
		return true;
	}
}
