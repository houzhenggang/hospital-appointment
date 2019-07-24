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

import com.baomidou.mybatisplus.extension.service.IService;
import com.kasoft.register.base.entity.DoctorDoctorinfoteam;

import java.util.List;

/**
 * 医生团队表
 *
 * @author charlie
 * @date 2019-04-30 17:10:54
 */
public interface DoctorDoctorinfoteamService extends IService<DoctorDoctorinfoteam> {

	/**
	 * 根据团队id删除成员
	 * @param id 团队id
	 * @return
	 */
	boolean removeByTeamId(String id);

	/**
	 * 新增某团队医生列表并删除旧的关联
	 * @param teamId 团队编号
	 * @param doctorIdList 医生列表
	 * @return
	 */
	boolean saveRemoveList(String teamId, List<String> doctorIdList);
}
