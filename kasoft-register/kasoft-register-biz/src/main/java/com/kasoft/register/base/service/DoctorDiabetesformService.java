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
import com.kasoft.register.base.dto.DoctorDiabetesformDTO;
import com.kasoft.register.base.entity.DoctorDiabetesform;

/**
 * 糖尿病随访
 *
 * @author charlie
 * @date 2019-06-08 10:40:08
 */
public interface DoctorDiabetesformService extends IService<DoctorDiabetesform> {
	/**
	 * 分页查询糖尿病随访信息
	 * @param page 分页信息
	 * @param entity 糖尿病随访对象
	 * @return
	 */
	IPage page(Page page, DoctorDiabetesform entity);
	/**
	 * 根据编号查询糖尿病随访信息
	 * @param id
	 * @return
	 */
	DoctorDiabetesformDTO getById(String id);

	/**
	 * 根据编号保存糖尿病随访dto对象
	 * @param entity
	 * @return
	 */
	boolean save(DoctorDiabetesformDTO entity);

	/**
	 * 根据编号更新糖尿病随访dto对象
	 * @param entity
	 * @return
	 */
	boolean updateById(DoctorDiabetesformDTO entity);
}
