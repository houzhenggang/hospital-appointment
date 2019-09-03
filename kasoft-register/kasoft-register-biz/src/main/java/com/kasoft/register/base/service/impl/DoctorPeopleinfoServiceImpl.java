package com.kasoft.register.base.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kasoft.register.base.api.entity.Applyerinfo;
import com.kasoft.register.base.api.entity.DoctorPeopleinfo;
import com.kasoft.register.base.mapper.ApplyerinfoMapper;
import com.kasoft.register.base.mapper.DoctorPeopleinfoMapper;
import com.kasoft.register.base.service.DoctorPeopleinfoService;
import com.kasoft.register.base.utils.KrbConstants;
import com.pig4cloud.pigx.admin.api.dto.UserDTO;
import com.pig4cloud.pigx.admin.api.dto.UserInfo;
import com.pig4cloud.pigx.admin.api.entity.SysUser;
import com.pig4cloud.pigx.admin.api.feign.RemoteSysPublicParamService;
import com.pig4cloud.pigx.admin.api.feign.RemoteUserService;
import com.pig4cloud.pigx.common.core.constant.CommonConstants;
import com.pig4cloud.pigx.common.core.constant.SecurityConstants;
import com.pig4cloud.pigx.common.core.util.R;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

	private final ApplyerinfoMapper applyerinfoMapper;

	@Override
	@Transactional(rollbackFor = Exception.class)
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
		UserInfo quUserInfo = remoteUserService.social("SMS@" + userDTO.getPhone(), SecurityConstants.FROM_IN).getData();
		if (quUserInfo != null){
			return R.failed(false, "手机号重复!");
		}
		remoteUserService.save(userDTO);
		SysUser sysUser = remoteUserService.user(userDTO.getUsername()).getData();
		DoctorPeopleinfo peopleinfo = new DoctorPeopleinfo();
		peopleinfo.setUserId(sysUser.getUserId());
		peopleinfo.setName(userDTO.getName());
		peopleinfo.setSex(userDTO.getSex());
		peopleinfo.setPhone(userDTO.getPhone());
		peopleinfo.setIdCard(userDTO.getIdCard());
		this.save(peopleinfo);
		Applyerinfo inApplyerInfo = new Applyerinfo();
		inApplyerInfo.setUserId(sysUser.getUserId());
		inApplyerInfo.setApplyerName(userDTO.getName());
		inApplyerInfo.setPhone(userDTO.getPhone());
		inApplyerInfo.setSex(userDTO.getSex());
		inApplyerInfo.setIdCard(userDTO.getIdCard());
		inApplyerInfo.setIsSelf(CommonConstants.IS_SELF);
		applyerinfoMapper.insert(inApplyerInfo);
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

	@Override
	@Transactional(rollbackFor = Exception.class)
	public boolean updatePeopeleInfo(DoctorPeopleinfo doctorPeopleinfo) {
		this.updateById(doctorPeopleinfo);
		applyerinfoMapper.update(null, Wrappers.<Applyerinfo>lambdaUpdate()
				.eq(Applyerinfo::getUserId, doctorPeopleinfo.getUserId())
				.eq(Applyerinfo::getIsSelf, CommonConstants.IS_SELF)
				.set(Applyerinfo::getIdCard, doctorPeopleinfo.getIdCard())
				.set(Applyerinfo::getApplyerName, doctorPeopleinfo.getName())
				.set(Applyerinfo::getPhone, doctorPeopleinfo.getPhone())
				.set(Applyerinfo::getSex, doctorPeopleinfo.getSex())
		);
		return Boolean.TRUE;
	}
}
