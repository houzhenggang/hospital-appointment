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

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kasoft.register.base.dao.MongoCountryArchiveDao;
import com.kasoft.register.base.dao.MongoProjectDiabetesDao;
import com.kasoft.register.base.dao.MongoProjectHypertensionDao;
import com.kasoft.register.base.dao.MongoProjectOldPeopleDao;
import com.kasoft.register.base.dto.DoctorPeopleinfoDTO;
import com.kasoft.register.base.entity.*;
import com.kasoft.register.base.service.DoctorHospitalService;
import com.kasoft.register.base.service.DoctorPeopleinfoService;
import com.kasoft.register.base.utils.EdConstants;
import com.pig4cloud.pigx.admin.api.dto.UserDTO;
import com.pig4cloud.pigx.admin.api.entity.SysUser;
import com.pig4cloud.pigx.admin.api.feign.RemoteSysPublicParamService;
import com.pig4cloud.pigx.admin.api.feign.RemoteUserService;
import com.pig4cloud.pigx.common.core.util.R;
import com.twtsoft.health.base.entity.*;
import com.kasoft.register.base.mapper.DoctorPeopleinfoMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * 居民基本信息表
 *
 * @author charlie
 * @date 2019-05-07 15:21:30
 */
@Service
@Slf4j
public class DoctorPeopleinfoServiceImpl extends ServiceImpl<DoctorPeopleinfoMapper, DoctorPeopleinfo> implements DoctorPeopleinfoService {
	@Autowired
	private MongoCountryArchiveDao mongoCountryArchiveDao;
	@Autowired
	private MongoProjectHypertensionDao mongoProjectHypertensionDao;
	@Autowired
	private MongoProjectDiabetesDao mongoProjectDiabetesDao;
	@Autowired
	private MongoProjectOldPeopleDao mongoProjectOldPeopleDao;
	@Autowired
	private DoctorHospitalService doctorHospitalService;
	@Autowired
	private RemoteUserService remoteUserService;
	@Autowired
	private RemoteSysPublicParamService remoteSysPublicParamService;

	@Override
	public IPage page(Page page, DoctorPeopleinfo peopleinfo, boolean aboutForm) {
		List<DoctorPeopleinfoDTO> peopleinfoDTOList = new ArrayList<>();
		QueryWrapper wrapper = Wrappers.query(peopleinfo).orderByDesc("update_time");
		if(aboutForm) {
			//如果身份证和姓名的查询条件都为空。根据下次随访时间查询
			if(StrUtil.isEmpty(peopleinfo.getIdCard()) && StrUtil.isEmpty(peopleinfo.getName())) {
				wrapper = Wrappers.query(peopleinfo).between("next_service_time",
						new Date(DateUtil.lastMonth().getTime()), new Date(DateUtil.nextMonth().getTime()))
						.orderByDesc("update_time");
			}
		}
		IPage pageData = super.page(page, wrapper);
		pageData.getRecords().forEach(item -> {
			DoctorPeopleinfo info = (DoctorPeopleinfo)item;
			DoctorPeopleinfoDTO peopleinfoDTO = new DoctorPeopleinfoDTO();
			BeanUtils.copyProperties(info, peopleinfoDTO);
			if(!StrUtil.isEmpty(info.getPeopleTypeList())) {
				peopleinfoDTO.setPeopleTypeListArray(info.getPeopleTypeList().split(","));
			}
			peopleinfoDTOList.add(peopleinfoDTO);
		});
		pageData.setRecords(peopleinfoDTOList);
		return pageData;
	}

	@Override
	public String save(DoctorPeopleinfoDTO peopleinfoDTO) {
		//补全地区
		DoctorHospital hospital = doctorHospitalService.getById(peopleinfoDTO.getHospitalId());
		if(hospital != null) {
			peopleinfoDTO.setAreaId(hospital.getAreaId());
		}
		DoctorPeopleinfo doctorPeopleinfo = new DoctorPeopleinfo();
		MongoCountryArchive countryArchive = new MongoCountryArchive();
		BeanUtils.copyProperties(peopleinfoDTO, doctorPeopleinfo);
		if(peopleinfoDTO.getPeopleTypeListArray() != null) {
			doctorPeopleinfo.setPeopleTypeList(ArrayUtil.join(peopleinfoDTO.getPeopleTypeListArray(), ","));
		}
		DoctorPeopleinfo findTemp = new DoctorPeopleinfo();
		findTemp.setUserId(peopleinfoDTO.getUserId());
		List<DoctorPeopleinfo> peopleinfoList = super.list(Wrappers.query(findTemp));
		//用户已经绑定档案，只能绑定一份
		if(peopleinfoList.size() > 0) {
			log.warn("用户已经绑定档案，只能绑定一份" + doctorPeopleinfo.getApplicationId() + " " + doctorPeopleinfo.getUserId());
			doctorPeopleinfo.setUserId(null);
		}

		//保存mysql
		super.save(doctorPeopleinfo);
		BeanUtils.copyProperties(peopleinfoDTO, countryArchive);
		countryArchive.setPeopleId(doctorPeopleinfo.getId());
		//保存mongo
		mongoCountryArchiveDao.save(countryArchive);
		return doctorPeopleinfo.getId();
	}

	@Override
	public boolean updateById(DoctorPeopleinfoDTO peopleinfoDTO) {
		//补全地区
		DoctorHospital hospital = doctorHospitalService.getById(peopleinfoDTO.getHospitalId());
		if(hospital != null) {
			peopleinfoDTO.setAreaId(hospital.getAreaId());
		}
		DoctorPeopleinfo doctorPeopleinfo = new DoctorPeopleinfo();
		MongoCountryArchive countryArchive = new MongoCountryArchive();
		Optional<MongoCountryArchive> countryArchiveOptional = mongoCountryArchiveDao.findById(peopleinfoDTO.getId());
		BeanUtils.copyProperties(peopleinfoDTO, doctorPeopleinfo);
		if(peopleinfoDTO.getPeopleTypeListArray() != null) {
			doctorPeopleinfo.setPeopleTypeList(ArrayUtil.join(peopleinfoDTO.getPeopleTypeListArray(), ","));
		}
		//更新mysql
		boolean flag = super.updateById(doctorPeopleinfo);
		if(countryArchiveOptional.isPresent()) {
			countryArchive = countryArchiveOptional.get();
		}
		BeanUtils.copyProperties(peopleinfoDTO, countryArchive);
		countryArchive.setPeopleId(doctorPeopleinfo.getId());
		//更新mongo
		mongoCountryArchiveDao.save(countryArchive);
		return flag;
	}

	@Override
	public boolean updateServceTimeById(String peopleId, LocalDateTime lastServiceTime, LocalDateTime nextServiceTime) {
		DoctorPeopleinfo doctorPeopleinfo = new DoctorPeopleinfo();
		doctorPeopleinfo.setId(peopleId);
		doctorPeopleinfo.setLastServiceTime(lastServiceTime);
		doctorPeopleinfo.setNextServiceTime(nextServiceTime);
		return doctorPeopleinfo.updateById();
	}

	@Override
	public DoctorPeopleinfoDTO getDetailById(String id) {
		DoctorPeopleinfoDTO doctorPeopleinfoDTO = new DoctorPeopleinfoDTO();
		DoctorPeopleinfo doctorPeopleinfo = super.getById(id);
		if(doctorPeopleinfo != null) {
			packagePeopleinfo(doctorPeopleinfoDTO, doctorPeopleinfo);
			return doctorPeopleinfoDTO;
		}
		return null;
	}

	@Override
	public DoctorPeopleinfoDTO getDetailByUserId(Integer userId) {
		DoctorPeopleinfoDTO doctorPeopleinfoDTO = new DoctorPeopleinfoDTO();
		DoctorPeopleinfo peopleinfo = new DoctorPeopleinfo();
		peopleinfo.setUserId(userId);
		List<DoctorPeopleinfo> peopleinfoList = super.list(Wrappers.query(peopleinfo));
		if(peopleinfoList.size() == 1) {
			packagePeopleinfo(doctorPeopleinfoDTO, peopleinfoList.get(0));
			return doctorPeopleinfoDTO;
		} else if(peopleinfoList.size() > 1) {
			log.warn("档案不唯一userId" + userId);
		} else {
			log.info("档案不存在userId" + userId);
		}
		return null;
	}

	@Override
	public DoctorPeopleinfoDTO getDetailByIdcard(String idCard) {
		DoctorPeopleinfoDTO doctorPeopleinfoDTO = new DoctorPeopleinfoDTO();
		DoctorPeopleinfo peopleinfo = new DoctorPeopleinfo();
		peopleinfo.setIdCard(idCard);
		List<DoctorPeopleinfo> peopleinfoList = super.list(Wrappers.query(peopleinfo));
		if(peopleinfoList.size() == 1) {
			packagePeopleinfo(doctorPeopleinfoDTO, peopleinfoList.get(0));
			return doctorPeopleinfoDTO;
		} else if(peopleinfoList.size() > 1) {
			log.warn("档案不唯一idCard" + idCard);
		} else {
			log.info("档案不存在idCard" + idCard);
		}
		return null;
	}

	/**
	 * 组装居民信息
	 * @param doctorPeopleinfoDTO dto对象
	 * @param doctorPeopleinfo 居民对象
	 */
	private void packagePeopleinfo(DoctorPeopleinfoDTO doctorPeopleinfoDTO, DoctorPeopleinfo doctorPeopleinfo) {
		Optional<MongoCountryArchive> countryArchiveOptional = mongoCountryArchiveDao.findById(doctorPeopleinfo.getId());
		BeanUtils.copyProperties(doctorPeopleinfo, doctorPeopleinfoDTO);
		//id重命名
		doctorPeopleinfoDTO.setPeopleId(doctorPeopleinfo.getId());
		if(countryArchiveOptional.isPresent()) {
			BeanUtils.copyProperties(countryArchiveOptional.get(), doctorPeopleinfoDTO);
		}
	}

	@Override
	public MongoProjectHypertension getHypertensionById(String id) {
		MongoProjectHypertension projectHypertension = new MongoProjectHypertension();
		Optional<MongoProjectHypertension> projectHypertensionOptional = mongoProjectHypertensionDao.findById(id);
		if(projectHypertensionOptional.isPresent()) {
			projectHypertension = projectHypertensionOptional.get();
		}
		projectHypertension.setPeopleId(id);
		return projectHypertension;
	}

	@Override
	public boolean updateHypertensionById(MongoProjectHypertension projectHypertension) {
		MongoProjectHypertension oldProjectHypertension = getHypertensionById(projectHypertension.getPeopleId());
		BeanUtils.copyProperties(projectHypertension, oldProjectHypertension);
		oldProjectHypertension =mongoProjectHypertensionDao.save(oldProjectHypertension);
		return oldProjectHypertension == null ? false : true;
	}

	@Override
	public MongoProjectDiabetes getDiabetesById(String id) {
		MongoProjectDiabetes projectDiabetes = new MongoProjectDiabetes();
		Optional<MongoProjectDiabetes> projectDiabetesOptional = mongoProjectDiabetesDao.findById(id);
		if(projectDiabetesOptional.isPresent()) {
			projectDiabetes = projectDiabetesOptional.get();
		}
		projectDiabetes.setPeopleId(id);
		return projectDiabetes;
	}

	@Override
	public boolean updateDiabetesById(MongoProjectDiabetes projectDiabetes) {
		MongoProjectDiabetes oldProjectDiabetes = getDiabetesById(projectDiabetes.getPeopleId());
		BeanUtils.copyProperties(projectDiabetes, oldProjectDiabetes);
		oldProjectDiabetes = mongoProjectDiabetesDao.save(oldProjectDiabetes);
		return oldProjectDiabetes == null ? false : true;
	}

	@Override
	public MongoProjectOldPeople getOldPeopleById(String id) {
		MongoProjectOldPeople projectOldPeople = new MongoProjectOldPeople();
		Optional<MongoProjectOldPeople> projectOldPeopleDaoById = mongoProjectOldPeopleDao.findById(id);
		if(projectOldPeopleDaoById.isPresent()) {
			projectOldPeople = projectOldPeopleDaoById.get();
		}
		projectOldPeople.setPeopleId(id);
		return projectOldPeople;
	}

	@Override
	public boolean updateOldPeopleById(MongoProjectOldPeople projectOldPeople) {
		MongoProjectOldPeople oldPeopleById = getOldPeopleById(projectOldPeople.getPeopleId());
		BeanUtils.copyProperties(projectOldPeople, oldPeopleById);
		oldPeopleById = mongoProjectOldPeopleDao.save(oldPeopleById);
		return oldPeopleById == null ? false : true;
	}

	@Override
	public R<Boolean> register(UserDTO userDTO) {
		//添加默认角色
		String role = remoteSysPublicParamService.publicKey(EdConstants.ROLE_DEFAULT_USER).getData();
		if(StrUtil.isNotEmpty(role)) {
			List<Integer> roleList = new ArrayList<>();
			roleList.add(Integer.parseInt(role));
			userDTO.setRole(roleList);
		}
		if(hasUserName(userDTO.getUsername())) {
			return new R<>(false, "用户名已经存在");
		}
		return remoteUserService.save(userDTO);
	}

	@Override
	public boolean hasUserName(String userName) {
		if(StrUtil.isNotEmpty(userName) && remoteUserService.user(userName).getData() instanceof SysUser) {
			return true;
		} else {
			return false;
		}

	}
}
