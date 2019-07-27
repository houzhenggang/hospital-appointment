package com.kasoft.register.base.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kasoft.register.base.service.DoctorDoctorinfoService;
import com.kasoft.register.base.service.DoctorHospitalService;
import com.kasoft.register.base.api.entity.DoctorDoctorinfo;
import com.kasoft.register.base.api.entity.DoctorHospital;
import com.kasoft.register.base.mapper.DoctorDoctorinfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 医生信息表
 *
 * @author charlie
 * @date 2019-04-30 17:10:49
 */
@Service
public class DoctorDoctorinfoServiceImpl extends ServiceImpl<DoctorDoctorinfoMapper, DoctorDoctorinfo> implements DoctorDoctorinfoService {

	@Autowired
	private DoctorHospitalService doctorHospitalService;

	@Override
	public List<DoctorDoctorinfo> getDoctorDictByHospital(String hospitalId) {
		return this.baseMapper.getDoctorDictByHospital(hospitalId);
	}

	@Override
	public List<DoctorDoctorinfo> getDoctorDictByTeam(String teamId) {
		return this.baseMapper.getDoctorDictByTeam(teamId);
	}

	@Override
	public IPage page(Page page, DoctorDoctorinfo doctorDoctorinfo) {
		return this.baseMapper.getDoctorinfoPage(page, doctorDoctorinfo);
	}

	@Override
	public DoctorDoctorinfo getByUserId(Integer userId) {
		DoctorDoctorinfo doctorDoctorinfo = new DoctorDoctorinfo();
		doctorDoctorinfo.setUserId(userId);
		List<DoctorDoctorinfo> doctorDoctorinfoList = this.list(Wrappers.query(doctorDoctorinfo));
		if(doctorDoctorinfoList.size() > 0) {
			return  doctorDoctorinfoList.get(0);
		}
		return null;
	}

	/**
	 * 保存 带依赖
	 * @param entity
	 * @return
	 */
	@Override
	public boolean save(DoctorDoctorinfo entity) {
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
	public boolean updateById(DoctorDoctorinfo entity) {
		DoctorHospital hospital = doctorHospitalService.getById(entity.getHospitalId());
		if(hospital != null) {
			entity.setAreaId(hospital.getAreaId());
		}
		return super.updateById(entity);
	}
}
