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

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kasoft.register.base.dao.MongoAgreementDao;
import com.kasoft.register.base.dto.DeviceApplicationDTO;
import com.kasoft.register.base.dto.DoctorAgreementDTO;
import com.kasoft.register.base.entity.*;
import com.kasoft.register.base.service.*;
import com.kasoft.register.base.utils.EdConstants;
import com.twtsoft.health.base.entity.*;
import com.kasoft.register.base.mapper.DoctorAgreementMapper;
import com.twtsoft.health.base.service.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * 居民签约协议
 *
 * @author charlie
 * @date 2019-05-07 15:43:03
 */
@Service
@Slf4j
public class DoctorAgreementServiceImpl extends ServiceImpl<DoctorAgreementMapper, DoctorAgreement> implements DoctorAgreementService {
	@Autowired
	private DoctorHospitalService doctorHospitalService;
	@Autowired
	private DoctorPeopleservicepackageService doctorPeopleservicepackageService;
	@Autowired
	private DoctorServicepackageService doctorServicepackageService;
	@Autowired
	private DoctorPeopleinfoService doctorPeopleinfoService;
	@Autowired
	private DeviceApplicationService deviceApplicationService;
	@Autowired
	private DoctorServicepackageitemService servicepackageitemService;
	@Autowired
	private MongoAgreementDao mongoAgreementDao;

	@Override
	public IPage<DoctorAgreement> pageByUserId(IPage<DoctorAgreement> page, Integer userId) {
		DoctorPeopleinfo peopleinfo = new DoctorPeopleinfo();
		peopleinfo.setUserId(userId);
		List<DoctorPeopleinfo> peopleinfoList = doctorPeopleinfoService.list(Wrappers.query(peopleinfo));
		//只有用户是1个档案才正常
		if(peopleinfoList.size() == 1) {
			DoctorAgreement agreement = new DoctorAgreement();
			agreement.setPeopleId(peopleinfoList.get(0).getId());
			return super.page(page, Wrappers.query(agreement));
		}
		return null;
	}

	@Override
	public boolean saveRemoveItemList(DoctorAgreementDTO doctorAgreementDTO) {
		//补全数据
		DoctorHospital hospital = doctorHospitalService.getById(doctorAgreementDTO.getHospitalId());
		if(hospital != null) {
			doctorAgreementDTO.setAreaId(hospital.getAreaId());
		}
		//如果新增，就提前设置id
		if(StrUtil.isEmpty(doctorAgreementDTO.getId())) {
			doctorAgreementDTO.setId(IdUtil.simpleUUID());
		}

		//查询出所有的签约项
		DoctorPeopleservicepackage peopleservicepackage = new DoctorPeopleservicepackage();
		peopleservicepackage.setAgreementId(doctorAgreementDTO.getId());
		List<String> nowIdList = new ArrayList<>();
		List<String> reqIdList = new ArrayList<>();
		List<String> delIdList = new ArrayList<>();
		List<String> addList = new ArrayList<>();
		if(CollUtil.isNotEmpty(doctorAgreementDTO.getDoctorServicepackageList())) {
			List<DoctorPeopleservicepackage> peopleservicepackageList = doctorPeopleservicepackageService.list(Wrappers.query(peopleservicepackage));
			//查找现有项目ID列表
			peopleservicepackageList.forEach(item -> {
				nowIdList.add(item.getPackageId());
			});
			//查找添加项目库关联数据 保存和修改时传过来的id是项目id
			doctorAgreementDTO.getDoctorServicepackageList().forEach(item -> {
				reqIdList.add(item.getId());
				if (!nowIdList.contains(item.getId())) {
					addList.add(item.getId());
				}
			});
			//查找待删除ID列表
			peopleservicepackageList.forEach(item -> {
				if (!reqIdList.contains(item.getPackageId())) {
					delIdList.add(item.getId());
				}
			});

			//删除项目库关联数据
			if (CollUtil.isNotEmpty(delIdList)) {
				doctorPeopleservicepackageService.removeByIds(delIdList);
			}
			//添加项目库关联数据
			if (CollUtil.isNotEmpty(addList)) {
				Collection<DoctorServicepackage> doctorServicepackageList = doctorServicepackageService.listByIds(addList);
				List<DoctorPeopleservicepackage> servicepackageitemListTemp = new ArrayList<>();
				doctorServicepackageList.forEach(item -> {
					DoctorPeopleservicepackage doctorPeopleservicepackage = new DoctorPeopleservicepackage();
					BeanUtils.copyProperties(item, doctorPeopleservicepackage);
					BeanUtils.copyProperties(doctorAgreementDTO, doctorPeopleservicepackage);
					doctorPeopleservicepackage.setId(IdUtil.simpleUUID());
					doctorPeopleservicepackage.setPackageId(item.getId());
					doctorPeopleservicepackage.setAgreementId(doctorAgreementDTO.getId());
					doctorPeopleservicepackage.setTenantId(null);
					doctorPeopleservicepackage.setCreateTime(null);
					doctorPeopleservicepackage.setUpdateTime(null);
					servicepackageitemListTemp.add(doctorPeopleservicepackage);
				});
				doctorPeopleservicepackageService.saveBatch(servicepackageitemListTemp);
			}
		}
		DoctorAgreement doctorAgreement = new DoctorAgreement();
		BeanUtils.copyProperties(doctorAgreementDTO, doctorAgreement);
		boolean flag = super.saveOrUpdate(doctorAgreement);
		//存储到mongo
		MongoAgreement mongoAgreement  = mongoAgreementDao.findById(doctorAgreement.getId()).orElse(new MongoAgreement());
		BeanUtil.copyProperties(doctorAgreement, mongoAgreement, CopyOptions.create().setIgnoreNullValue(true).setIgnoreError(true));
		mongoAgreement.setAgreementId(doctorAgreement.getId());
		//mongo 中的套餐明细
		Map<String, MongoAgreementServicepackage> mongoAgreementServicepackageMap = mongoAgreement.getAgreementServicepackageMap();
		if(mongoAgreementServicepackageMap == null) {
			mongoAgreementServicepackageMap = new LinkedHashMap<>();
			mongoAgreement.setAgreementServicepackageMap(mongoAgreementServicepackageMap);
		}
		//查询所有的套餐包 mysql中的套餐明细
		List<DoctorPeopleservicepackage> peopleservicepackageList = doctorPeopleservicepackageService.list(Wrappers.query(peopleservicepackage));
		//如果mongo中有更新，没有添加，多余的删除
		Map<String , DoctorPeopleservicepackage> peopleservicepackageMap = new LinkedHashMap<>();
		peopleservicepackageList.forEach(item -> {
			peopleservicepackageMap.put(item.getPackageId(), item);
		});
		//2个id的差集
		Collection<String> disList = CollUtil.disjunction(mongoAgreementServicepackageMap.keySet(), peopleservicepackageMap.keySet());
		for(String item : disList) {

			if(peopleservicepackageMap.containsKey(item)) {
				//如果在mysql中存在,就组装好，添加到mongo中
				MongoAgreementServicepackage mongoAgreementServicepackage = new MongoAgreementServicepackage();
				BeanUtil.copyProperties(peopleservicepackageMap.get(item), mongoAgreementServicepackage, CopyOptions.create().setIgnoreNullValue(true).setIgnoreError(true));
				//添加套餐明细也就是套餐项
				DoctorServicepackageitem servicepackageitem = new DoctorServicepackageitem();
				servicepackageitem.setPackageId(item);
				List<DoctorServicepackageitem> servicepackageitemList = servicepackageitemService.list(Wrappers.query(servicepackageitem));
				Map<String, MongoAgreementProject> agreementProjectMap = new LinkedHashMap<>();
				servicepackageitemList.forEach(packageItem -> {
					MongoAgreementProject mongoAgreementProject = new MongoAgreementProject();
					BeanUtil.copyProperties(packageItem, mongoAgreementProject, CopyOptions.create().setIgnoreNullValue(true).setIgnoreError(true));
					agreementProjectMap.put(mongoAgreementProject.getProjectId(), mongoAgreementProject);
				});
				mongoAgreementServicepackage.setAgreementProjectMap(agreementProjectMap);
				mongoAgreementServicepackageMap.put(mongoAgreementServicepackage.getPackageId(), mongoAgreementServicepackage);
			} else {
				//如果在mysql中不存在，只在mongo中存在，需要删除mongo中的数据
				mongoAgreementServicepackageMap.remove(item);
			}
		}
		mongoAgreementDao.save(mongoAgreement);
		return flag;
	}

	@Override
	public DoctorAgreementDTO getById(String id) {
		DoctorAgreementDTO doctorAgreementDTO = new DoctorAgreementDTO();
		//查询签约
		DoctorAgreement doctorAgreement = super.getById(id);
		if(ObjectUtil.isNull(doctorAgreement)) {
			log.warn("签约协议数据为空" + id);
			return doctorAgreementDTO;
		}
		//查询签约项（套餐）
		List<DoctorPeopleservicepackage> listByAgreementId = doctorPeopleservicepackageService.getListByAgreementId(id);
		BeanUtils.copyProperties(doctorAgreement, doctorAgreementDTO);
		//id转换成签约项id（套餐id）
		listByAgreementId.forEach(item -> {
			item.setId(item.getPackageId());
		});
		doctorAgreementDTO.setDoctorServicepackageList(listByAgreementId);
		//查询mongo数据
		MongoAgreement mongoAgreement  = mongoAgreementDao.findById(doctorAgreement.getId()).orElse(new MongoAgreement());
		doctorAgreementDTO.setMongoAgreement(mongoAgreement);
		return doctorAgreementDTO;
	}

	@Override
	public boolean updateById(DoctorAgreement doctorAgreement) {
		//补全数据
		DoctorHospital hospital = doctorHospitalService.getById(doctorAgreement.getHospitalId());
		if(hospital != null) {
			doctorAgreement.setAreaId(hospital.getAreaId());
		}
		return super.updateById(doctorAgreement);
	}

	@Override
	public boolean save(DoctorAgreement doctorAgreement) {
		//补全数据
		DoctorHospital hospital = doctorHospitalService.getById(doctorAgreement.getHospitalId());
		if(hospital != null) {
			doctorAgreement.setAreaId(hospital.getAreaId());
		}
		//如果是自助签约修改审核状态
		if(StrUtil.isNotEmpty(doctorAgreement.getApplicationId())) {
			DeviceApplicationDTO deviceApplicationDTO = new DeviceApplicationDTO();
			deviceApplicationDTO.setId(doctorAgreement.getApplicationId());
			deviceApplicationDTO.setStatus(EdConstants.HandleState.YES);
			deviceApplicationService.updateById(deviceApplicationDTO);
		}
		return super.save(doctorAgreement);
	}


	@Override
	public boolean removeById(String id) {
		//删除签约项（套餐）
		doctorPeopleservicepackageService.removeByAgreementId(id);
		//删除套餐
		return super.removeById(id);
	}
}
