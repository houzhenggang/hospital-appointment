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
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kasoft.register.base.dao.MongoAgreementDaoTemplate;
import com.kasoft.register.base.dto.DoctorDiabetesformDTO;
import com.kasoft.register.base.service.DoctorDiabetesformService;
import com.kasoft.register.base.service.DoctorPeopleinfoService;
import com.kasoft.register.base.entity.DoctorDiabetesform;
import com.kasoft.register.base.mapper.DoctorDiabetesformMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 糖尿病随访
 *
 * @author charlie
 * @date 2019-06-08 10:40:08
 */
@Service
public class DoctorDiabetesformServiceImpl extends ServiceImpl<DoctorDiabetesformMapper, DoctorDiabetesform> implements DoctorDiabetesformService {
	@Autowired
	private DoctorPeopleinfoService peopleinfoService;
	@Autowired
	private MongoAgreementDaoTemplate mongoAgreementDaoTemplate;
	@Override
	public IPage page(Page page, DoctorDiabetesform entity) {
		IPage outPage = super.page(page, Wrappers.query(entity).orderByDesc("update_time"));
		if(CollUtil.isNotEmpty(outPage.getRecords())) {
			List<DoctorDiabetesformDTO> list = new ArrayList<>();
			outPage.getRecords().forEach(item -> {
				DoctorDiabetesformDTO dto = new DoctorDiabetesformDTO();
				BeanUtils.copyProperties(item, dto);
				dto.entityToDTO();
				list.add(dto);
			});
			outPage.setRecords(list);
		}
		return outPage;
	}

	@Override
	public DoctorDiabetesformDTO getById(String id) {
		DoctorDiabetesform bean = super.getById(id);
		if(ObjectUtil.isNotNull(bean)) {
			DoctorDiabetesformDTO dto = new DoctorDiabetesformDTO();
			BeanUtils.copyProperties(bean, dto);
			dto.entityToDTO();
			return dto;
		}
		return null;
	}

	@Override
	public boolean save(DoctorDiabetesformDTO entity) {
		entity.dtoToEntity();
		DoctorDiabetesform bean = new DoctorDiabetesform();
		BeanUtils.copyProperties(entity, bean);
		boolean flag = super.save(bean);
		//更新随访时间
		peopleinfoService.updateServceTimeById(entity.getPeopleId(), entity.getFollowTime(), entity.getNextFollowTime());
		//更新随访次数
		if(StrUtil.isNotEmpty(entity.getAgreementId()) && StrUtil.isNotEmpty(entity.getPackageId()) &&
				StrUtil.isNotEmpty(entity.getProjectId()) && ObjectUtil.isNotNull(entity.getNowServiceFrequency())) {
			mongoAgreementDaoTemplate.updateFormId(bean.getAgreementId(), bean.getPackageId(), bean.getProjectId(),
					entity.getNowServiceFrequency(), bean.getDiabetesFormId(), bean.getFollowTime());
		}
		return flag;
	}

	@Override
	public boolean updateById(DoctorDiabetesformDTO entity) {
		entity.dtoToEntity();
		DoctorDiabetesform bean = new DoctorDiabetesform();
		BeanUtils.copyProperties(entity, bean);
		//更新随访时间
		peopleinfoService.updateServceTimeById(entity.getPeopleId(), entity.getFollowTime(), entity.getNextFollowTime());
		return super.updateById(bean);
	}
}
