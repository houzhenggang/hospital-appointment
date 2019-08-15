package com.kasoft.register.base.task;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.kasoft.register.base.api.entity.DoctorApplyorder;
import com.kasoft.register.base.mapper.DoctorApplyorderMapper;
import com.kasoft.register.base.utils.SmsUtils;
import com.yunpian.sdk.YunpianClient;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

/**
 * <p>
 * 预约订单定时任务
 * </p>
 *
 * @author kylin
 * @since 2019/8/15 15:20
 */
@Slf4j
@Component("applyOrderTask")
@AllArgsConstructor
public class ApplyOrderTask {

	private final DoctorApplyorderMapper doctorApplyorderMapper;

	private final YunpianClient yunpianClient;

	@Scheduled(cron = "0 0 * * * ?")
	public void run(){
		Date curr = new Date();
		List<DoctorApplyorder> list = doctorApplyorderMapper.selectList(new QueryWrapper<DoctorApplyorder>()
				.eq("del_flag", 0)
				.eq("order_state", 10)
				.le("end_time", curr)
		);
		if (!list.isEmpty()) {
			list.forEach( applyOrder -> {
				//修改订单状态
				applyOrder.setOrderState("30");
				doctorApplyorderMapper.updateById(applyOrder);
				//发送短信提醒
				String detail = applyOrder.getApplyTime() + applyOrder.getHospitalName() + applyOrder.getInspItemName();
				SmsUtils.sendSms(yunpianClient, applyOrder.getPeoplePhone(), "您好，您之前预约的"+ detail + "服务已过期，请注意查看相关预约订单。");
			});
		}
	}

}
