package com.kasoft.register.base.service.impl;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import cn.hutool.json.JSONArray;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kasoft.register.base.api.entity.DoctorHospital;
import com.kasoft.register.base.api.entity.DoctorInspectionitem;
import com.kasoft.register.base.api.entity.DoctorInspectresource;
import com.kasoft.register.base.mapper.DoctorHospitalMapper;
import com.kasoft.register.base.mapper.DoctorInspectionitemMapper;
import com.kasoft.register.base.mapper.DoctorInspectresourceMapper;
import com.kasoft.register.base.service.DoctorInspectresourceService;
import com.pig4cloud.pigx.common.core.exception.CheckedException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * 检查资源
 *
 * @author kylin
 * @date 2019-07-27 10:32:30
 */
@Service
@AllArgsConstructor
public class DoctorInspectresourceServiceImpl extends ServiceImpl<DoctorInspectresourceMapper, DoctorInspectresource> implements DoctorInspectresourceService {

	private final DoctorInspectresourceMapper doctorInspectresourceMapper;

	private final DoctorHospitalMapper doctorHospitalMapper;

	@Override
	@Transactional(rollbackFor = Exception.class)
	public boolean saveInspectresourceBatch(List<DoctorInspectresource> inspectresources) {
		inspectresources.forEach( inspectresource -> {
			int count = this.count(new QueryWrapper<DoctorInspectresource>()
					.eq("hospital_id", inspectresource.getHospitalId())
					.eq("insp_item_id", inspectresource.getInspItemId())
					.eq("insp_item_date", inspectresource.getInspItemDate())
					.eq("period", inspectresource.getPeriod())
			);
			if (count > 0) {
				throw new CheckedException("同一医院,同一项目,同一时间段只允许添加一个检查资源!");
			}
			DoctorHospital doctorHospital = doctorHospitalMapper.selectById(inspectresource.getHospitalId());
			inspectresource.setHospitalImage(doctorHospital.getHospitalImage());
		});
		return this.saveBatch(inspectresources);
	}

	@Override
	public List<DoctorInspectresource> getAllItemGroupPage() {
		return doctorInspectresourceMapper.getAllItemGroup();
	}
}
