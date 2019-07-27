package com.kasoft.register.base.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.kasoft.register.base.api.entity.DoctorDepartmentdictionary;

import java.util.List;

/**
 * 科室字典
 *
 * @author charlie
 * @date 2019-04-30 17:10:43
 */
public interface DoctorDepartmentdictionaryService extends IService<DoctorDepartmentdictionary> {
	/**
	 * 根据医院编号获取科室字典
	 * @param hospitalId 医院编号
	 * @return
	 */
	List<DoctorDepartmentdictionary> getDepartmentDictByHospital(String hospitalId);
}
