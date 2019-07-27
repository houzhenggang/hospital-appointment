package com.kasoft.register.base.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kasoft.register.base.service.DoctorDepartmentdictionaryService;
import com.kasoft.register.base.utils.KrbConstants;
import com.kasoft.register.base.entity.DoctorDepartmentdictionary;
import com.kasoft.register.base.entity.DoctorHospital;
import com.kasoft.register.base.mapper.DoctorDepartmentdictionaryMapper;
import com.kasoft.register.base.service.DoctorHospitalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 科室字典
 *
 * @author charlie
 * @date 2019-04-30 17:10:43
 */
@Service
public class DoctorDepartmentdictionaryServiceImpl extends ServiceImpl<DoctorDepartmentdictionaryMapper, DoctorDepartmentdictionary> implements DoctorDepartmentdictionaryService {
	@Autowired
	private DoctorHospitalService doctorHospitalService;
	@Override
	@Cacheable(value = KrbConstants.ED_DEPARTMENT_DETAILS, key = "#hospitalId", unless = "#result == null ")
	public List<DoctorDepartmentdictionary> getDepartmentDictByHospital(String hospitalId) {
		return this.baseMapper.getDepartmentDictByHospital(hospitalId);
	}

	/**
	 * 保存 带依赖
	 * @param entity
	 * @return
	 */
	@Override
	public boolean save(DoctorDepartmentdictionary entity) {
		DoctorHospital hospital = doctorHospitalService.getById(entity.getHospitalId());
		if(hospital != null) {
			entity.setAreaId(hospital.getAreaId());
		}
		return super.save(entity);
	}

	/**
	 * 根据id更新 带依赖
	 * @param entity
	 * @return
	 */
	@Override
	public boolean updateById(DoctorDepartmentdictionary entity) {
		DoctorHospital hospital = doctorHospitalService.getById(entity.getHospitalId());
		if(hospital != null) {
			entity.setAreaId(hospital.getAreaId());
		}
		return super.updateById(entity);
	}
}
