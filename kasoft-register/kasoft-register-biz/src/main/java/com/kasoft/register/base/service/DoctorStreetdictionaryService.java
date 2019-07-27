package com.kasoft.register.base.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.kasoft.register.base.entity.DoctorStreetdictionary;

/**
 * 街道居委会
 *
 * @author charlie
 * @date 2019-04-30 17:10:33
 */
public interface DoctorStreetdictionaryService extends IService<DoctorStreetdictionary> {
	/**
	 * 根据id查询街道居委会
	 * @param id 编号
	 * @return 街道居委会对象
	 */
	DoctorStreetdictionary getById(String id);
}
