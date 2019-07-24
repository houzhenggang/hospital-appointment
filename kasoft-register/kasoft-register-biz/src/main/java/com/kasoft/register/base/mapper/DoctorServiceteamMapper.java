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
package com.kasoft.register.base.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.kasoft.register.base.entity.DoctorServiceteam;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 服务团队
 *
 * @author charlie
 * @date 2019-04-30 17:10:16
 */
@Mapper
public interface DoctorServiceteamMapper extends BaseMapper<DoctorServiceteam> {
	/**
	 * 根据医院查询服务团队字典
	 * @param hospitalId 医院编号
	 * @return
	 */
	List<DoctorServiceteam> getServiceteamDictByHospital(@Param("hospitalId") String hospitalId);
}
