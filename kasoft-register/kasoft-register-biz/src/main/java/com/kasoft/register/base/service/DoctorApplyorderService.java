package com.kasoft.register.base.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.kasoft.register.base.api.entity.DoctorApplyorder;

/**
 * 预约订单
 *
 * @author kylin
 * @date 2019-07-27 10:32:12
 */
public interface DoctorApplyorderService extends IService<DoctorApplyorder> {


	void addApplyorder(DoctorApplyorder doctorApplyorder);
}
