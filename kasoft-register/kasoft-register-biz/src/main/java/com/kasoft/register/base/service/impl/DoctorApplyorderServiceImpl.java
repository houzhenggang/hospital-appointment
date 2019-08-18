package com.kasoft.register.base.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kasoft.register.base.api.entity.DoctorApplyorder;
import com.kasoft.register.base.api.entity.DoctorInspectresource;
import com.kasoft.register.base.api.entity.DoctorPeopleinfo;
import com.kasoft.register.base.config.YunpianPropertiesConfig;
import com.kasoft.register.base.mapper.DoctorApplyorderMapper;
import com.kasoft.register.base.mapper.DoctorPeopleinfoMapper;
import com.kasoft.register.base.service.DoctorApplyorderService;
import com.kasoft.register.base.service.DoctorInspectresourceService;
import com.kasoft.register.base.utils.KrbConstants;
import com.kasoft.register.base.utils.SmsUtils;
import com.pig4cloud.pigx.common.core.exception.CheckedException;
import com.yunpian.sdk.YunpianClient;
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

	private final YunpianClient yunpianClient;

	private final YunpianPropertiesConfig yunpianPropertiesConfig;

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void addApplyorder(DoctorApplyorder doctorApplyorder) {
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
		if (doctorApplyorder.getPeoplePhone() != null){
			String mobile = doctorApplyorder.getPeoplePhone();
			String name = doctorApplyorder.getInspItemName();
			String time = doctorApplyorder.getApplyTime();
			String hospital = doctorApplyorder.getHospitalName();
			String price = doctorApplyorder.getFeeTotal().toString();
			String detail = yunpianPropertiesConfig.getSignature() + "您已成功预约" + hospital + "的" + name + "，收费" + price
					+ "元，请您携带身份证于" + time + "到达" + hospital + "导医台处登记就诊，稍后医院工作人员会电话告知注意事项，请您注意接听。";
			SmsUtils.sendSms(yunpianClient, mobile, detail);
		}
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void updateApplyOrder(DoctorApplyorder doctorApplyorder) {
		//查询预约订单是否存在
		DoctorApplyorder quApplyOrder = this.getById(doctorApplyorder.getApplyOrderId());
		if (quApplyOrder == null) {
			throw new CheckedException("预约订单不存在!");
		}
		this.updateById(doctorApplyorder);
		if(doctorApplyorder.getOrderState() != null){
			String detail = null;
			if (StrUtil.equals(doctorApplyorder.getOrderState(), KrbConstants.OrderState.HAD_CANCEL)) {
				DoctorApplyorder quApplyorder = this.getById(doctorApplyorder.getApplyOrderId());
				DoctorInspectresource quInspectresource = doctorInspectresourceService.getById(quApplyorder.getInspResourceId());
				DoctorInspectresource upInspectresource = new DoctorInspectresource();
				upInspectresource.setInspResourceId(quApplyorder.getInspResourceId());
				upInspectresource.setQuantity(quInspectresource.getQuantity() + 1);
				doctorInspectresourceService.updateById(upInspectresource);
				detail = yunpianPropertiesConfig.getSignature() + "您好，您的预约"+ quApplyOrder.getApplyTime()
						+ quApplyOrder.getHospitalName() + quApplyOrder.getInspItemName()+"服务已成功取消。";
			} else if (StrUtil.equals(doctorApplyorder.getOrderState(), KrbConstants.OrderState.HAD_CHECK)) {
				detail = yunpianPropertiesConfig.getSignature() + "您好，您已按照约定到场履约"+ quApplyOrder.getApplyTime()
						+ quApplyOrder.getHospitalName() + quApplyOrder.getInspItemName()+"服务。";
			}
			SmsUtils.sendSms(yunpianClient, quApplyOrder.getPeoplePhone(), detail);
		}

	}

}
