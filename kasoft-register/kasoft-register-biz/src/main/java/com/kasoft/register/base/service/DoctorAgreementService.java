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
import com.baomidou.mybatisplus.extension.service.IService;
import com.kasoft.register.base.dto.DoctorAgreementDTO;
import com.kasoft.register.base.entity.DoctorAgreement;

/**
 * 居民签约协议
 *
 * @author charlie
 * @date 2019-05-07 15:43:03
 */
public interface DoctorAgreementService extends IService<DoctorAgreement> {
	/**
	 * 根据用户编号列出签约列表
	 * @param page 分页
	 * @param userId 用户编号
	 * @return
	 */
	IPage<DoctorAgreement> pageByUserId(IPage<DoctorAgreement> page, Integer userId);

	/**
	 * 保存和删除签约项包括签约
	 * @param doctorAgreementDTO 服务包DTO
	 * @return 是否成功
	 */
	boolean saveRemoveItemList(DoctorAgreementDTO doctorAgreementDTO);
	/**
	 * 根据id获取签约信息，包括签约项
	 * @param id 套餐id
	 * @return
	 */
	DoctorAgreementDTO getById(String id);
	/**
	 * 根据编号更新
	 * @param doctorAgreement 签约对象
	 * @return
	 */
	@Override
	boolean updateById(DoctorAgreement doctorAgreement);

	/**
	 * 保存数据
	 * @param doctorAgreement 签约对象
	 * @return
	 */
	@Override
	boolean save(DoctorAgreement doctorAgreement);

	/**
	 * 根据编号删除
	 * @param id 编号
	 * @return
	 */
	boolean removeById(String id);
}
