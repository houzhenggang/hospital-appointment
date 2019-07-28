package com.kasoft.register.base.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kasoft.register.base.api.entity.DoctorPeopleinfo;
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
		remoteUserService.save(userDTO);
		SysUser sysUser = remoteUserService.user(userDTO.getUsername()).getData();
		DoctorPeopleinfo peopleinfo = new DoctorPeopleinfo();
		peopleinfo.setUserId(sysUser.getUserId());
		peopleinfo.setName(userDTO.getName());
		peopleinfo.setSex(userDTO.getSex());
		this.save(peopleinfo);
		return R.ok(true, "注册成功!");
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
