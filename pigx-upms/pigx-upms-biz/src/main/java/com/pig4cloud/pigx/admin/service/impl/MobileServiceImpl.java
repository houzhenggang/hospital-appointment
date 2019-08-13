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

package com.pig4cloud.pigx.admin.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.RandomUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.pig4cloud.pigx.admin.api.entity.SysUser;
import com.pig4cloud.pigx.admin.mapper.SysUserMapper;
import com.pig4cloud.pigx.admin.service.MobileService;
import com.pig4cloud.pigx.common.core.constant.CommonConstants;
import com.pig4cloud.pigx.common.core.constant.SecurityConstants;
import com.pig4cloud.pigx.common.core.constant.enums.LoginTypeEnum;
import com.pig4cloud.pigx.common.core.util.R;
import com.yunpian.sdk.YunpianClient;
import com.yunpian.sdk.model.Result;
import com.yunpian.sdk.model.SmsSingleSend;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author lengleng
 * @date 2018/11/14
 * <p>
 * 手机登录相关业务实现
 */
@Slf4j
@Service
@AllArgsConstructor
public class MobileServiceImpl implements MobileService {
	private final RedisTemplate redisTemplate;
	private final SysUserMapper userMapper;


	/**
	 * 发送手机验证码
	 * TODO: 调用短信网关发送验证码,测试返回前端
	 *
	 * @param mobile mobile
	 * @return code
	 */
	@Override
	public R<Boolean> sendSmsCode(String mobile) {
		List<SysUser> userList = userMapper.selectList(Wrappers
			.<SysUser>query().lambda()
			.eq(SysUser::getPhone, mobile));

		if (CollUtil.isEmpty(userList)) {
			log.info("手机号未注册:{}", mobile);
			return R.ok(Boolean.FALSE, "手机号未注册");
		}

		Object codeObj = redisTemplate.opsForValue().get(CommonConstants.DEFAULT_CODE_KEY + LoginTypeEnum.SMS.getType() + "@" + mobile);

		if (codeObj != null) {
			log.info("手机号验证码未过期:{}，{}", mobile, codeObj);
			return R.ok(Boolean.FALSE, "验证码发送过频繁");
		}

		String code = RandomUtil.randomNumbers(Integer.parseInt(SecurityConstants.CODE_SIZE));
		YunpianClient clnt = new YunpianClient("7a9a24894961a2377760f79b44bdf7be").init();
		Map<String, String> param = clnt.newParam(2);
		param.put(YunpianClient.MOBILE, mobile);
		param.put(YunpianClient.TEXT, "【南京擎卡医疗】正在进行登录操作，您的验证码是" + code);
		Result<SmsSingleSend> r = clnt.sms().single_send(param);
		if (r.getCode() != 0) {
			return R.ok(Boolean.FALSE, r.getMsg());
		}
		log.debug("手机号生成验证码成功:{},{}", mobile, code);
		redisTemplate.opsForValue().set(
			CommonConstants.DEFAULT_CODE_KEY + LoginTypeEnum.SMS.getType() + "@" + mobile
			, code, SecurityConstants.CODE_TIME, TimeUnit.SECONDS);
		return R.ok(Boolean.TRUE, code);
	}
}
