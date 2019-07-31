package com.kasoft.register.base.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kasoft.register.base.api.entity.DoctorApplyorder;
import com.kasoft.register.base.api.entity.DoctorInspectresource;
import com.kasoft.register.base.mapper.DoctorApplyorderMapper;
import com.kasoft.register.base.service.DoctorApplyorderService;
import com.kasoft.register.base.service.DoctorInspectresourceService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 预约订单
 *
 * @author kylin
 * @date 2019-07-27 10:32:12
 */
@Service
@AllArgsConstructor
public class DoctorApplyorderServiceImpl extends ServiceImpl<DoctorApplyorderMapper, DoctorApplyorder> implements DoctorApplyorderService {

	private final DoctorApplyorderService doctorApplyorderService;

	private final DoctorInspectresourceService doctorInspectresourceService;

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void addApplyorder(DoctorApplyorder doctorApplyorder) {
		doctorApplyorderService.save(doctorApplyorder);
		DoctorInspectresource upInspectresource = new DoctorInspectresource();
//		upInspectresource
//		doctorInspectresourceService.updateById();
	}
}
