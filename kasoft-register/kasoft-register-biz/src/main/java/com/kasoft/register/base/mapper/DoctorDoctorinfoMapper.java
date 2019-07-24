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
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kasoft.register.base.vo.DoctorDoctorinfoVO;
import com.kasoft.register.base.entity.DoctorDoctorinfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 医生信息表
 *
 * @author charlie
 * @date 2019-04-30 17:10:49
 */
@Mapper
public interface DoctorDoctorinfoMapper extends BaseMapper<DoctorDoctorinfo> {
	/**
	 * 根据医院查询医生字典
	 * @param hospitalId 医院编号
	 * @return
	 */
	List<DoctorDoctorinfo> getDoctorDictByHospital(@Param("hospitalId") String hospitalId);

	/**
	 * 根据团队查询医生字典
	 * @param teamId 团队编号
	 * @return
	 */
	List<DoctorDoctorinfo> getDoctorDictByTeam(@Param("teamId") String teamId);

	/**
	 * 分页查询医生信息
	 * @param page 分页对象
	 * @param doctorinfo 医生对象
	 * @return
	 */
	IPage<List<DoctorDoctorinfoVO>> getDoctorinfoPage(Page page, @Param("query") DoctorDoctorinfo doctorinfo);

}
