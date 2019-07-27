package com.kasoft.register.base.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kasoft.register.base.service.DoctorHospitalService;
import com.kasoft.register.base.utils.KrbConstants;
import com.kasoft.register.base.entity.DoctorHospital;
import com.kasoft.register.base.mapper.DoctorHospitalMapper;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 医院
 *
 * @author charlie
 * @date 2019-04-30 17:09:57
 */
@Service
public class DoctorHospitalServiceImpl extends ServiceImpl<DoctorHospitalMapper, DoctorHospital> implements DoctorHospitalService {

	@Override
	public List<DoctorHospital> getHospitalByName(String name) {
		return this.baseMapper.getHospitalByName(name);
	}

	@Override
	@Cacheable(value = KrbConstants.ED_HOSPITAL_DETAILS, key = "#id", unless = "#result == null ")
	public DoctorHospital getById(String id) {
		return super.getById(id);
	}
}
