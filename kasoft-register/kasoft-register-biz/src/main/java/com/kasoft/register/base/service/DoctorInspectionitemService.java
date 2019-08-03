package com.kasoft.register.base.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.kasoft.register.base.api.entity.DoctorInspectionitem;

import java.util.List;

/**
 * 检查项目
 *
 * @author kylin
 * @date 2019-07-26 21:33:19
 */
public interface DoctorInspectionitemService extends IService<DoctorInspectionitem> {

	/**
	 * 查询热门检查项目
	 * @return List
	 */
	List<DoctorInspectionitem> queryHotInspitem();
}
