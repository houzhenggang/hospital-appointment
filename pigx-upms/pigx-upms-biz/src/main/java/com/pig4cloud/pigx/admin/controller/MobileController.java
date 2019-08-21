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

package com.pig4cloud.pigx.admin.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.pig4cloud.pigx.admin.api.entity.SysUser;
import com.pig4cloud.pigx.admin.service.MobileService;
import com.pig4cloud.pigx.admin.service.SysUserService;
import com.pig4cloud.pigx.common.core.util.R;
import com.pig4cloud.pigx.common.security.annotation.Inner;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lengleng
 * @date 2018/11/14
 * <p>
 * 手机验证码
 */
@RestController
@AllArgsConstructor
@RequestMapping("/mobile")
@Api(value = "mobile", tags = "手机管理模块")
public class MobileController {
	private final MobileService mobileService;

	private final SysUserService sysUserService;

	@Inner(value = false)
	@GetMapping("/register/{mobile}")
	public R sendRegisterSmsCode(@PathVariable String mobile) {
		return mobileService.sendRegisterSmsCode(mobile);
	}

	@Inner(value = false)
	@GetMapping("/login/{mobile}")
	public R sendLoginSmsCode(@PathVariable String mobile) {
		return mobileService.sendLoginSmsCode(mobile);
	}

	@Inner(value = false)
	@GetMapping("/valid/sms")
	public R validSmsCode() {
		return R.ok(null, "验证成功!");
	}

	@Inner(value = false)
	@GetMapping("/valid/{phone}")
	public R validPhone(@PathVariable String phone) {
		int count = sysUserService.count(new QueryWrapper<SysUser>()
				.eq("phone", phone)
		);
		if (count > 0) {
			return R.failed(null, "手机号已注册!");
		}
		return R.ok(null, "手机号可用!");
	}
}
