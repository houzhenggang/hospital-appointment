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
import com.kasoft.register.base.dto.DoctorServicepackageDTO;
import com.kasoft.register.base.dto.ServicepackageTree;
import com.kasoft.register.base.entity.DoctorServicepackage;

import java.util.List;

/**
 * 服务套餐包
 *
 * @author charlie
 * @date 2019-05-07 15:56:24
 */
public interface DoctorServicepackageService extends IService<DoctorServicepackage> {
	/**
	 * 返回套餐的树集合
	 *
	 * @param hospitalId 医院编号
	 * @param type       类型 为空是查询全部树，1是查非叶子节点
	 * @param serviceType 服务类型，基础或个性化
	 * @return
	 */
	List<ServicepackageTree> getServicepackageTree(String hospitalId, String type, String serviceType);

	/**
	 * 分页查询 带复杂搜索实现
	 *
	 * @param page                 分页对象
	 * @param doctorServicepackageDTO 服务套餐包
	 * @return
	 */
	IPage<DoctorServicepackage> page(IPage<DoctorServicepackage> page, DoctorServicepackageDTO doctorServicepackageDTO);

	/**
	 * 保存和删除套餐项包括套餐
	 * @param doctorServicepackageDTO 服务包DTO
	 * @return 是否成功
	 */
	boolean saveRemoveItemList(DoctorServicepackageDTO doctorServicepackageDTO);
	/**
	 * 根据id获取相信信息，包括套餐项
	 * @param id 套餐id
	 * @return
	 */
	DoctorServicepackageDTO getById(String id);
	/**
	 * 根据编号更新
	 * @param doctorServicepackage dto对象
	 * @return
	 */
	@Override
	boolean updateById(DoctorServicepackage doctorServicepackage);

	/**
	 * 保存数据
	 * @param doctorServicepackage dto对象
	 * @return
	 */
	@Override
	boolean save(DoctorServicepackage doctorServicepackage);

	/**
	 * 根据编号删除
	 * @param id 编号
	 * @return
	 */
	boolean removeById(String id);

}