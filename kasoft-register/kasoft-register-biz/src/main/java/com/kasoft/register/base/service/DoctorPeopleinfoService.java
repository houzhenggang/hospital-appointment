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

package com.kasoft.register.base.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.kasoft.register.base.dto.DoctorPeopleinfoDTO;
import com.pig4cloud.pigx.admin.api.dto.UserDTO;
import com.pig4cloud.pigx.common.core.util.R;
import com.kasoft.register.base.entity.DoctorPeopleinfo;

import java.time.LocalDateTime;

/**
 * 居民基本信息表
 *
 * @author charlie
 * @date 2019-05-07 15:21:30
 */
public interface DoctorPeopleinfoService extends IService<DoctorPeopleinfo> {
	/**
	 * 分页查询档案信息
	 * @param page 分页对象
	 * @param peopleinfo 居民信息
	 * @param aboutForm 随访相关
	 * @return
	 */
	IPage page(Page page, DoctorPeopleinfo peopleinfo, boolean aboutForm);
	/**
	 * 保存居民档案dto
	 * @param peopleinfoDTO 档案dto
	 * @return
	 */
	String save(DoctorPeopleinfoDTO peopleinfoDTO);

	/**
	 * 根据编号更新数据
	 * @param peopleinfoDTO 档案dto
	 * @return
	 */
	boolean updateById(DoctorPeopleinfoDTO peopleinfoDTO);

	/**
	 * 根据服务时间
	 * @param peopleId 档案主键
	 * @param lastServiceTime 最近一次服务时间
	 * @param nextServiceTime 下一次服务时间
	 * @return
	 */
	boolean updateServceTimeById(String peopleId, LocalDateTime lastServiceTime, LocalDateTime nextServiceTime);

	/**
	 * 根据编号查询档案
	 * @param id 档案编号
	 * @return
	 */
	DoctorPeopleinfoDTO getDetailById(String id);

	/**
	 * 根据用户编号查询档案
	 * @param userId 用户编号
	 * @return 用户详情
	 */
	DoctorPeopleinfoDTO getDetailByUserId(Integer userId);

	/**
	 * 根据身份证号查询档案
	 * @param idCard 身份证号
	 * @return
	 */
	DoctorPeopleinfoDTO getDetailByIdcard(String idCard);


	/**
	 * 根据编号查询高血压专案
	 * @param id 居民编号
	 * @return 高血压专案
	 */
	MongoProjectHypertension getHypertensionById(String id);

	/**
	 * 根据编号更新高血压专案
	 * @param projectHypertension 高血压专案
	 * @return 是否成功
	 */
	boolean updateHypertensionById(MongoProjectHypertension projectHypertension);
	/**
	 * 根据编号查询糖尿病专案
	 * @param id 居民编号
	 * @return 糖尿病专案
	 */
	MongoProjectDiabetes getDiabetesById(String id);
	/**
	 * 根据编号更新糖尿病专案
	 * @param projectDiabetes 糖尿病专案
	 * @return 是否成功
	 */
	boolean updateDiabetesById(MongoProjectDiabetes projectDiabetes);
	/**
	 * 根据编号查询老年人专案
	 * @param id 居民编号
	 * @return 老年人专案
	 */
	MongoProjectOldPeople getOldPeopleById(String id);
	/**
	 * 根据编号更新老年人专案
	 * @param projectOldPeople 老年人专案
	 * @return 是否成功
	 */
	boolean updateOldPeopleById(MongoProjectOldPeople projectOldPeople);

	/**
	 * 注册
	 * @param userDTO 用户信息
	 * @return
	 */
	R<Boolean> register(UserDTO userDTO);

	/**
	 * 用户名是否存在
	 * @param userName 用户名
	 * @return
	 */
	boolean hasUserName(String userName);

}
