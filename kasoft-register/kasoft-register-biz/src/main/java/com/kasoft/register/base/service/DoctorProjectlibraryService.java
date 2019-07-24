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
import com.kasoft.register.base.dto.DoctorProjectlibraryDTO;
import com.kasoft.register.base.dto.ProjectlibraryTree;
import com.kasoft.register.base.entity.DoctorProjectlibrary;

import java.util.List;

/**
 * 项目库
 *
 * @author charlie
 * @date 2019-05-07 15:21:57
 */
public interface DoctorProjectlibraryService extends IService<DoctorProjectlibrary> {
	/**
	 * 获取树状项目库
	 * 默认选择当前医院和默认的项目
	 * @param hospitalId 医院编号
	 * @param type 类型 为空是查询全部树，1是查非叶子节点
	 * @return
	 */
	List<ProjectlibraryTree> getProjectlibraryTree(String hospitalId, String type);
	/**
	 * 分页查询 带复杂搜索实现
	 *
	 * @param page                 分页对象
	 * @param doctorProjectlibraryDTO 项目库
	 * @return
	 */
	IPage<DoctorProjectlibrary> page(IPage<DoctorProjectlibrary> page, DoctorProjectlibraryDTO doctorProjectlibraryDTO);
}
