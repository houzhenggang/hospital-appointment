/*
 *    Copyright (c) 2018-2025, lengleng All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice,
 * this list of conditions and the following disclaimer.
 * Redistributions in binary form must reproduce the above copyright
 * notice, this list of conditions and the following disclaimer in the
 * documentation and/or other materials provided with the distribution.
 * Neither the name of the pig4cloud.com developer nor the names of its
 * contributors may be used to endorse or promote products derived from
 * this software without specific prior written permission.
 * Author: lengleng (wangiegie@gmail.com)
 */
package com.kasoft.register.base.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kasoft.register.base.entity.DoctorPeopleinfo;
import com.kasoft.register.base.mapper.DoctorPeopleinfoMapper;
import com.kasoft.register.base.service.DoctorPeopleinfoService;
import com.kasoft.register.base.utils.KrbConstants;
import com.pig4cloud.pigx.admin.api.dto.UserDTO;
import com.pig4cloud.pigx.admin.api.entity.SysUser;
import com.pig4cloud.pigx.admin.api.feign.RemoteSysPublicParamService;
import com.pig4cloud.pigx.admin.api.feign.RemoteUserService;
import com.pig4cloud.pigx.common.core.util.R;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 居民基本信息表
 *
 * @author charlie
 * @date 2019-07-26 09:58:13
 */
@Service
@AllArgsConstructor
public class DoctorPeopleinfoServiceImpl extends ServiceImpl<DoctorPeopleinfoMapper, DoctorPeopleinfo> implements DoctorPeopleinfoService {
	private final RemoteUserService remoteUserService;
	private final RemoteSysPublicParamService remoteSysPublicParamService;
	@Override
	public R<Boolean> register(UserDTO userDTO) {
		//添加默认角色
		String role = remoteSysPublicParamService.publicKey(KrbConstants.ROLE_DEFAULT_USER).getData();
		if(StrUtil.isNotEmpty(role)) {
			List<Integer> roleList = new ArrayList<>();
			roleList.add(Integer.parseInt(role));
			userDTO.setRole(roleList);
		}
		if(hasUserName(userDTO.getUsername())) {
			return R.failed(false, "用户名已经存在");
		}
		return remoteUserService.save(userDTO);
	}

	@Override
	public boolean hasUserName(String userName) {
		if(StrUtil.isNotEmpty(userName) && remoteUserService.user(userName).getData() instanceof SysUser) {
			return true;
		} else {
			return false;
		}
	}
}
