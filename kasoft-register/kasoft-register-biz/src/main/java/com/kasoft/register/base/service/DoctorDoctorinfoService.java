package com.kasoft.register.base.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.kasoft.register.base.entity.DoctorDoctorinfo;

import java.util.List;

/**
 * 医生信息表
 *
 * @author charlie
 * @date 2019-04-30 17:10:49
 */
public interface DoctorDoctorinfoService extends IService<DoctorDoctorinfo> {

	/**
	 * 根据医院查询医生字典
	 * @param hospitalId 医院编号
	 * @return
	 */
	List<DoctorDoctorinfo> getDoctorDictByHospital(String hospitalId);

	/**
	 * 根据团队编号获取意思字典
	 * @param teamId 团队编号
	 * @return
	 */
	List<DoctorDoctorinfo> getDoctorDictByTeam(String teamId);

	/**
	 * 分页查询医生信息
	 * @param page 分页信息
	 * @param doctorDoctorinfo 医生对象
	 * @return
	 */
	IPage page(Page page, DoctorDoctorinfo doctorDoctorinfo);

	/**
	 * 获取用户根据userId
	 * @param userId 用户编号
	 * @return
	 */
	DoctorDoctorinfo getByUserId(Integer userId);
}
