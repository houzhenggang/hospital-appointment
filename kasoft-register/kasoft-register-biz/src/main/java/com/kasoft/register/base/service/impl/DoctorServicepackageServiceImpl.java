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
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kasoft.register.base.dto.DoctorServicepackageDTO;
import com.kasoft.register.base.dto.ServicepackageTree;
import com.kasoft.register.base.service.DoctorHospitalService;
import com.kasoft.register.base.service.DoctorProjectlibraryService;
import com.kasoft.register.base.service.DoctorServicepackageService;
import com.kasoft.register.base.service.DoctorServicepackageitemService;
import com.kasoft.register.base.utils.EdConstants;
import com.kasoft.register.base.utils.EdTreeUtils;
import com.kasoft.register.base.entity.DoctorHospital;
import com.kasoft.register.base.entity.DoctorProjectlibrary;
import com.kasoft.register.base.entity.DoctorServicepackage;
import com.kasoft.register.base.entity.DoctorServicepackageitem;
import com.kasoft.register.base.mapper.DoctorServicepackageMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * 服务套餐包
 *
 * @author charlie
 * @date 2019-05-07 15:56:24
 */
@Service
public class DoctorServicepackageServiceImpl extends ServiceImpl<DoctorServicepackageMapper, DoctorServicepackage> implements DoctorServicepackageService {
	@Autowired
	private DoctorHospitalService doctorHospitalService;
	@Autowired
	private DoctorServicepackageitemService servicepackageitemService;
	@Autowired
	private DoctorProjectlibraryService doctorProjectlibraryService;
	@Override
	public List<ServicepackageTree> getServicepackageTree(String hospitalId, String type, String serviceType) {
		List<ServicepackageTree> servicepackageTreeList = new ArrayList<>();
		DoctorServicepackage servicepackage = new DoctorServicepackage();
		if(!StrUtil.isEmpty(type)) {
			servicepackage.setHasChildren(type);
		}
		servicepackage.setHospitalId(hospitalId);
		List<DoctorServicepackage> doctoServicepackageList = this.list(Wrappers.query(servicepackage).orderByAsc("sort"));
		for(DoctorServicepackage item : doctoServicepackageList) {
			//如果服务类型不为空并且是叶子节点
			if(StrUtil.isNotEmpty(serviceType) && EdConstants.HaveNo.NO.equals(item.getHasChildren())) {
				//如果类型不一致就进行下一次循环
				if(!serviceType.equals(item.getServiceType())) {
					continue;
				}
			}
			ServicepackageTree tree = new ServicepackageTree();
			tree.setParentId(item.getParentId());
			BeanUtils.copyProperties(item, tree);
			servicepackageTreeList.add(tree);
		}
		List<ServicepackageTree> servicepackageTrees = EdTreeUtils.buildByRecursive(servicepackageTreeList, EdConstants.TREE_ROOT_ID);
		return servicepackageTrees;
	}

	@Override
	public IPage<DoctorServicepackage> page(IPage<DoctorServicepackage> page, DoctorServicepackageDTO doctorServicepackageDTO) {
		DoctorServicepackage doctorServicepackage = new DoctorServicepackage();
		BeanUtils.copyProperties(doctorServicepackageDTO, doctorServicepackage);
		String likePackageLabel = doctorServicepackage.getPackageLabel();
		doctorServicepackage.setPackageLabel(likePackageLabel);
		QueryWrapper<DoctorServicepackage> queryWrapper = Wrappers.query(doctorServicepackage);
		if(!StrUtil.isEmpty(likePackageLabel)) {
			queryWrapper.and(wrapper -> wrapper.like("package_label", likePackageLabel));
		}
		queryWrapper.orderByAsc("sort");
		return super.page(page, queryWrapper);
 	}

	@Override
	public DoctorServicepackageDTO getById(String id) {
		DoctorServicepackageDTO servicepackageDTO = new DoctorServicepackageDTO();
		//查询套餐
		DoctorServicepackage servicepackage = super.getById(id);
		//查询套餐项
		List<DoctorServicepackageitem> listByServicepackageId = servicepackageitemService.getListByServicepackageId(id);
		if(ObjectUtil.isNotNull(servicepackage)) {
			BeanUtils.copyProperties(servicepackage, servicepackageDTO);
		}
		//id转换成套餐项id
		listByServicepackageId.forEach(item -> {
			item.setId(item.getProjectId());
		});
		servicepackageDTO.setDoctorServicepackageitemList(listByServicepackageId);
		return servicepackageDTO;
	}

	@Override
	public boolean saveRemoveItemList(DoctorServicepackageDTO entity) {
		//补全数据
		DoctorHospital hospital = doctorHospitalService.getById(entity.getHospitalId());
		if(hospital != null) {
			entity.setAreaId(hospital.getAreaId());
		}
		//如果新增，就提前设置id
		if(StrUtil.isEmpty(entity.getId())) {
			entity.setId(IdUtil.simpleUUID());
		}

		//查询出所有的套餐项目
		DoctorServicepackageitem servicepackageitem = new DoctorServicepackageitem();
		servicepackageitem.setPackageId(entity.getId());
		List<String> nowIdList = new ArrayList<>();
		List<String> reqIdList = new ArrayList<>();
		List<String> delIdList = new ArrayList<>();
		List<String> addList = new ArrayList<>();
		if(CollUtil.isNotEmpty(entity.getDoctorServicepackageitemList())) {
			List<DoctorServicepackageitem> servicepackageitemList = servicepackageitemService.list(Wrappers.query(servicepackageitem));
			//查找现有项目ID列表
			servicepackageitemList.forEach(item -> {
				nowIdList.add(item.getProjectId());
			});
			//查找添加项目库关联数据 保存和修改时传过来的id是项目id
			entity.getDoctorServicepackageitemList().forEach(item -> {
				reqIdList.add(item.getId());
				if (!nowIdList.contains(item.getId())) {
					addList.add(item.getId());
				}
			});
			//查找待删除ID列表
			servicepackageitemList.forEach(item -> {
				if (!reqIdList.contains(item.getProjectId())) {
					delIdList.add(item.getId());
				}
			});

			//删除项目库关联数据
			if (CollUtil.isNotEmpty(delIdList)) {
				servicepackageitemService.removeByIds(delIdList);
			}
			//添加项目库关联数据
			if (CollUtil.isNotEmpty(addList)) {
				Collection<DoctorProjectlibrary> projectlibraryList = doctorProjectlibraryService.listByIds(addList);
				List<DoctorServicepackageitem> servicepackageitemListTemp = new ArrayList<>();
				projectlibraryList.forEach(item -> {
					DoctorServicepackageitem servicepackageitem1 = new DoctorServicepackageitem();
					BeanUtils.copyProperties(item, servicepackageitem1);
					servicepackageitem1.setId(IdUtil.simpleUUID());
					servicepackageitem1.setProjectId(item.getId());
					servicepackageitem1.setPackageId(entity.getId());
					servicepackageitem1.setServiceType(entity.getServiceType());
					servicepackageitem1.setTenantId(null);
					servicepackageitem1.setCreateTime(null);
					servicepackageitem1.setUpdateTime(null);
					servicepackageitemListTemp.add(servicepackageitem1);
				});
				servicepackageitemService.saveBatch(servicepackageitemListTemp);
			}
		}
		DoctorServicepackage doctorServicepackage = new DoctorServicepackage();
		BeanUtils.copyProperties(entity, doctorServicepackage);
		return super.saveOrUpdate(doctorServicepackage);
	}


	@Override
	public boolean save(DoctorServicepackage doctorServicepackage) {
		//补全数据
		DoctorHospital hospital = doctorHospitalService.getById(doctorServicepackage.getHospitalId());
		if(hospital != null) {
			doctorServicepackage.setAreaId(hospital.getAreaId());
		}
		return super.save(doctorServicepackage);
	}

	@Override
	public boolean updateById(DoctorServicepackage doctorServicepackage) {
		//补全数据
		DoctorHospital hospital = doctorHospitalService.getById(doctorServicepackage.getHospitalId());
		if(hospital != null) {
			doctorServicepackage.setAreaId(hospital.getAreaId());
		}
		return super.updateById(doctorServicepackage);
	}

	@Override
	public boolean removeById(String id) {
		//删除套餐项
		servicepackageitemService.removeByServicepackageId(id);
		//删除套餐
		return super.removeById(id);
	}


}
