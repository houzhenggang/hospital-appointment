package com.kasoft.register.base.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.kasoft.register.base.entity.DoctorHospital;

import java.util.List;

/**
 * 医院
 *
 * @author charlie
 * @date 2019-04-30 17:09:57
 */
public interface DoctorHospitalService extends IService<DoctorHospital> {
	/**
	 * 根据名称查询医院 模糊查询
	 * @param name 医院名称
	 * @return
	 */
	List<DoctorHospital> getHospitalByName(String name);

	/**
	 * 根据id查询医院
	 * @param id 医院编号
	 * @return
	 */
	DoctorHospital getById(String id);

}
