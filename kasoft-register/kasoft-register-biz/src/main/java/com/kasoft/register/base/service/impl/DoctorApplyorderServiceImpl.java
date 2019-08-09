package com.kasoft.register.base.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kasoft.register.base.api.entity.DoctorApplyorder;
import com.kasoft.register.base.api.entity.DoctorInspectresource;
import com.kasoft.register.base.api.entity.DoctorPeopleinfo;
import com.kasoft.register.base.mapper.DoctorApplyorderMapper;
import com.kasoft.register.base.mapper.DoctorPeopleinfoMapper;
import com.kasoft.register.base.service.DoctorApplyorderService;
import com.kasoft.register.base.service.DoctorInspectresourceService;
import com.kasoft.register.base.utils.KrbConstants;
import com.kasoft.register.base.utils.SmsUtils;
import com.pig4cloud.pigx.common.core.exception.CheckedException;
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

	private final DoctorInspectresourceService doctorInspectresourceService;

	private final DoctorPeopleinfoMapper doctorPeopleinfoMapper;

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void addApplyorder(DoctorApplyorder doctorApplyorder) {
		DoctorPeopleinfo quPeopleinfo = doctorPeopleinfoMapper.selectById(doctorApplyorder.getPeopleId());
		if (quPeopleinfo == null) {
			throw new CheckedException("患者信息不存在!");
		}
		DoctorInspectresource quInspectresource = doctorInspectresourceService.getById(doctorApplyorder.getInspResourceId());
		if (quInspectresource == null) {
			throw new CheckedException("检查资源不存在!");
		}
		if (quInspectresource.getQuantity() == 0) {
			throw new CheckedException("该检查资源已被预约完!");
		}
		doctorApplyorder.setInspItemDate(quInspectresource.getInspItemDate());
		doctorApplyorder.setPeriod(quInspectresource.getPeriod());
		this.save(doctorApplyorder);
		DoctorInspectresource upInspectresource = new DoctorInspectresource();
		upInspectresource.setInspResourceId(doctorApplyorder.getInspResourceId());
		upInspectresource.setQuantity(quInspectresource.getQuantity() - 1);
		doctorInspectresourceService.updateById(upInspectresource);
		//发送短信提示
		if (quPeopleinfo.getPhone() != null){
			String mobile = quPeopleinfo.getPhone();
			String name = quPeopleinfo.getName();
			String time = doctorApplyorder.getApplyTime();
			String hospital = doctorApplyorder.getHospitalName();
			SmsUtils.sendApplySuccessSms(mobile, name, time, hospital);
		}
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void updateApplyOrder(DoctorApplyorder doctorApplyorder) {
		this.updateById(doctorApplyorder);
		if (doctorApplyorder.getOrderState() != null &&
				StrUtil.equals(doctorApplyorder.getOrderState(), KrbConstants.OrderState.HAD_CANCEL)) {
			DoctorApplyorder quApplyorder = this.getById(doctorApplyorder.getApplyOrderId());
			DoctorInspectresource quInspectresource = doctorInspectresourceService.getById(quApplyorder.getInspResourceId());
			DoctorInspectresource upInspectresource = new DoctorInspectresource();
			upInspectresource.setInspResourceId(quApplyorder.getInspResourceId());
			upInspectresource.setQuantity(quInspectresource.getQuantity() + 1);
			doctorInspectresourceService.updateById(upInspectresource);
		}
	}

}
