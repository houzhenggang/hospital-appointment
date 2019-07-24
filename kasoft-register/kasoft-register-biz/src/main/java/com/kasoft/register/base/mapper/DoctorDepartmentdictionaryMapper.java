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
import com.kasoft.register.base.entity.DoctorDepartmentdictionary;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 科室字典
 *
 * @author charlie
 * @date 2019-04-30 17:10:43
 */
@Mapper
public interface DoctorDepartmentdictionaryMapper extends BaseMapper<DoctorDepartmentdictionary> {
	/**
	 * 根据医院编号获取科室列表
	 * @param hospitalId
	 * @return
	 */
	List<DoctorDepartmentdictionary> getDepartmentDictByHospital(@Param("hospitalId") String hospitalId);
}
