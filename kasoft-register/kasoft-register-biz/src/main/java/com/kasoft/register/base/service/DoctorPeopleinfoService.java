package com.kasoft.register.base.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.kasoft.register.base.api.entity.DoctorPeopleinfo;
import com.pig4cloud.pigx.admin.api.dto.UserDTO;
import com.pig4cloud.pigx.common.core.util.R;

/**
 * 居民基本信息表
 *
 * @author charlie
 * @date 2019-07-26 09:58:13
 */
public interface DoctorPeopleinfoService extends IService<DoctorPeopleinfo> {

	/**
	 * 注册
	 * @param userDTO 用户信息
	 * @return
	 */
	R<Boolean> register(UserDTO userDTO);

	/**
	 * 用户名是否存在
	 * @param userName 用户名
	 * @return
	 */
	boolean hasUserName(String userName);

	boolean updatePeopeleInfo(DoctorPeopleinfo doctorPeopleinfo);
}
