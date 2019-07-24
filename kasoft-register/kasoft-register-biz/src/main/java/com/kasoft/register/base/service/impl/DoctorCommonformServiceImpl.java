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
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kasoft.register.base.dao.MongoAgreementDaoTemplate;
import com.kasoft.register.base.dto.DoctorCommonformDTO;
import com.kasoft.register.base.service.DoctorCommonformService;
import com.kasoft.register.base.service.DoctorPeopleinfoService;
import com.kasoft.register.base.entity.DoctorCommonform;
import com.kasoft.register.base.mapper.DoctorCommonformMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 通用随访
 *
 * @author charlie
 * @date 2019-06-15 11:40:08
 */
@Service
public class DoctorCommonformServiceImpl extends ServiceImpl<DoctorCommonformMapper, DoctorCommonform> implements DoctorCommonformService {
	@Autowired
	private DoctorPeopleinfoService peopleinfoService;
	@Autowired
	private MongoAgreementDaoTemplate mongoAgreementDaoTemplate;
	@Override
	public boolean save(DoctorCommonformDTO entity) {
		DoctorCommonform bean = new DoctorCommonform();
		BeanUtils.copyProperties(entity, bean);
		boolean flag = super.save(entity);
		//更新随访时间通用随访不更新随访时间
//		peopleinfoService.updateServceTimeById(entity.getPeopleId(), entity.getFollowTime(), entity.getNextFollowTime());
		//更新随访次数
		if(StrUtil.isNotEmpty(entity.getAgreementId()) && StrUtil.isNotEmpty(entity.getPackageId()) &&
				StrUtil.isNotEmpty(entity.getProjectId()) && ObjectUtil.isNotNull(entity.getNowServiceFrequency())) {
			mongoAgreementDaoTemplate.updateFormId(bean.getAgreementId(), bean.getPackageId(), bean.getProjectId(),
					entity.getNowServiceFrequency(), bean.getCommonFormId(), bean.getFollowTime());
		}
		return flag;
	}

	@Override
	public boolean updateById(DoctorCommonformDTO entity) {
		DoctorCommonform bean = new DoctorCommonform();
		BeanUtils.copyProperties(entity, bean);
		//更新随访时间
		peopleinfoService.updateServceTimeById(entity.getPeopleId(), entity.getFollowTime(), entity.getNextFollowTime());
		return super.updateById(entity);
	}
}
