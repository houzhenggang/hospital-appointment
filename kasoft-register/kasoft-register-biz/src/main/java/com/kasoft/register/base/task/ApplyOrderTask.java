package com.kasoft.register.base.task;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.kasoft.register.base.api.entity.DoctorApplyorder;
import com.kasoft.register.base.mapper.DoctorApplyorderMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

/**
 * @author kylin
 * @create 2019-08-15 10:07
 */
@Slf4j
@Component("applyOrderTask")
@AllArgsConstructor
public class ApplyOrderTask {

	private final DoctorApplyorderMapper doctorApplyorderMapper;

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
			});
		}


	}

}
