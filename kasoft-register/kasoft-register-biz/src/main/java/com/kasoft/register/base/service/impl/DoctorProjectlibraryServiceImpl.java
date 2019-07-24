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
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kasoft.register.base.dto.DoctorProjectlibraryDTO;
import com.kasoft.register.base.dto.ProjectlibraryTree;
import com.kasoft.register.base.service.DoctorHospitalService;
import com.kasoft.register.base.service.DoctorProjectlibraryService;
import com.kasoft.register.base.utils.EdConstants;
import com.kasoft.register.base.utils.EdTreeUtils;
import com.kasoft.register.base.entity.DoctorHospital;
import com.kasoft.register.base.entity.DoctorProjectlibrary;
import com.kasoft.register.base.mapper.DoctorProjectlibraryMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 项目库
 *
 * @author charlie
 * @date 2019-05-07 15:21:57
 */
@Service
public class DoctorProjectlibraryServiceImpl extends ServiceImpl<DoctorProjectlibraryMapper, DoctorProjectlibrary> implements DoctorProjectlibraryService {
	@Autowired
	private DoctorHospitalService doctorHospitalService;

	@Override
	public List<ProjectlibraryTree> getProjectlibraryTree(String hospitalId, String type) {
		List<ProjectlibraryTree> projectlibraryTreeList = new ArrayList<>();
		DoctorProjectlibrary projectlibrary = new DoctorProjectlibrary();
		if(!StrUtil.isEmpty(type)) {
			projectlibrary.setHasChildren(type);
		}
		projectlibrary.setProjectHospital(hospitalId);
		List<DoctorProjectlibrary> doctorProjectlibraryList = this.list(Wrappers.query(projectlibrary).orderByAsc("sort"));
		doctorProjectlibraryList.forEach(item -> {
			ProjectlibraryTree tree = new ProjectlibraryTree();
			BeanUtils.copyProperties(item, tree);
			projectlibraryTreeList.add(tree);
		});
		List<ProjectlibraryTree> projectlibraryTrees = EdTreeUtils.buildByRecursive(projectlibraryTreeList, EdConstants.TREE_ROOT_ID);
		return projectlibraryTrees;
	}

	@Override
	public IPage<DoctorProjectlibrary> page(IPage<DoctorProjectlibrary> page, DoctorProjectlibraryDTO doctorProjectlibraryDTO) {
		DoctorProjectlibrary doctorProjectlibrary = new DoctorProjectlibrary();
		BeanUtils.copyProperties(doctorProjectlibraryDTO, doctorProjectlibrary);
		//找出模糊查找项
		String likeProjectName = doctorProjectlibrary.getProjectName();
		doctorProjectlibrary.setProjectName(null);
		QueryWrapper<DoctorProjectlibrary> queryWrapper = Wrappers.query(doctorProjectlibrary);
		if(!StrUtil.isEmpty(likeProjectName)) {
			queryWrapper.and(wrapper -> wrapper.like("project_name", likeProjectName));
		}
		queryWrapper.orderByAsc("sort");
		return super.page(page, queryWrapper);
	}

	/**
	 * 保存 带依赖
	 * @param entity
	 * @return
	 */
	@Override
	public boolean save(DoctorProjectlibrary entity) {
		DoctorHospital hospital = doctorHospitalService.getById(entity.getProjectHospital());
		if(hospital != null) {
			entity.setProjectArea(hospital.getAreaId());
		}
		return super.save(entity);
	}

	/**
	 * 根据id更新 带依赖
	 * @param entity
	 * @return
	 */
	@Override
	public boolean updateById(DoctorProjectlibrary entity) {
		DoctorHospital hospital = doctorHospitalService.getById(entity.getProjectHospital());
		if(hospital != null) {
			entity.setProjectArea(hospital.getAreaId());
		}
		return super.updateById(entity);
	}
}
