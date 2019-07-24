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

package com.kasoft.register.base.controller;

import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.google.common.collect.Lists;
import com.kasoft.register.base.dto.DeviceImuserDTO;
import com.kasoft.register.base.init.TimRunner;
import com.pig4cloud.pigx.common.core.util.R;
import com.pig4cloud.pigx.common.log.annotation.SysLog;
import com.kasoft.register.base.entity.DeviceImuser;
import com.kasoft.register.base.service.DeviceImuserService;
import com.wakeup.qcloud.QCloudException;
import com.wakeup.qcloud.domain.AddFriendItemDO;
import com.wakeup.qcloud.request.AccountImportRequest;
import com.wakeup.qcloud.request.SnsFriendAddRequest;
import com.wakeup.qcloud.request.SnsFriendDeleteRequest;
import com.wakeup.qcloud.request.SnsFriendGetAllRequest;
import com.wakeup.qcloud.response.QCloudIMResponse;
import com.wakeup.qcloud.response.SnsFriendGetAllResponse;
import com.wakeup.qcloud.response.SnsFriendResponse;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.security.access.prepost.PreAuthorize;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


/**
 * IM用户管理
 *
 * @author charlie
 * @date 2019-07-21 14:02:04
 */
@RestController
@AllArgsConstructor
@RequestMapping("/deviceimuser" )
@Slf4j
@Api(value = "deviceimuser", tags = "基础-IM管理")
public class DeviceImuserController {

    private final  DeviceImuserService deviceImuserService;

    /**
     * 分页查询
     * @param page 分页对象
     * @param deviceImuser IM用户管理
     * @return
     */
    @GetMapping("/page" )
    public R getDeviceImuserPage(Page page, DeviceImuser deviceImuser) {
        return new R<>(deviceImuserService.page(page, Wrappers.query(deviceImuser)));
    }


    /**
     * 通过id查询IM用户管理
     * @param userId id
     * @return R
     */
    @GetMapping("/{userId}" )
    public R getById(@PathVariable("userId" ) String userId) throws Exception {
		DeviceImuser deviceImuser = deviceImuserService.getById(userId);
		if(ObjectUtil.isNotNull(deviceImuser)) {
			DeviceImuserDTO deviceImuserDTO = new DeviceImuserDTO();
			BeanUtils.copyProperties(deviceImuser, deviceImuserDTO);
			deviceImuserDTO.setUserSigInfo(TimRunner.getQcloudClient().getUserSigInfo(deviceImuser.getImuserId()));
			return new R<>(deviceImuserDTO);
		}
		return new R<>(deviceImuser);
    }

    /**
     * 新增IM用户管理
     * @param deviceImuser IM用户管理
     * @return R
     */
    @SysLog("新增IM用户管理" )
    @PostMapping
    @PreAuthorize("@pms.hasPermission('base_deviceimuser_add')" )
    public R save(@RequestBody DeviceImuser deviceImuser) {
		deviceImuser.clearNoUseDTO();
		if(StrUtil.isEmpty(deviceImuser.getImuserId())) {
			deviceImuser.setImuserId(IdUtil.simpleUUID());
		}
		createTencentAccount(deviceImuser.getImuserId(), deviceImuser.getNick(), deviceImuser.getHeadImg());
		return new R<>(deviceImuserService.save(deviceImuser));
    }

    /**
     * 修改IM用户管理
     * @param deviceImuser IM用户管理
     * @return R
     */
    @SysLog("修改IM用户管理" )
    @PutMapping
    @PreAuthorize("@pms.hasPermission('base_deviceimuser_edit')" )
    public R updateById(@RequestBody DeviceImuser deviceImuser) {
		deviceImuser.clearNoUseDTO();
		createTencentAccount(deviceImuser.getImuserId(), deviceImuser.getNick(), deviceImuser.getHeadImg());
		return new R<>(deviceImuserService.updateById(deviceImuser));
    }

    /**
     * 通过id删除IM用户管理
     * @param userId id
     * @return R
     */
    @SysLog("删除IM用户管理" )
    @DeleteMapping("/{userId}" )
    @PreAuthorize("@pms.hasPermission('base_deviceimuser_del')" )
    public R removeById(@PathVariable String userId) {
        return new R<>(deviceImuserService.removeById(userId));
    }

	/**
	 * 创建腾讯云通信账号
	 * @param:  @param identifier 账号
	 * @param:  @param nick 昵称
	 * @param:  @param faceUrl 头像
	 * @author: xiongjisheng
	 * @date: 2019年6月21日 上午10:44:05
	 */
	private Boolean createTencentAccount(String imuserId, String nick, String headImg) {
		try {
			AccountImportRequest accountImportRequest = new AccountImportRequest();
			accountImportRequest.setIdentifier(imuserId);
			accountImportRequest.setNick(nick);
			accountImportRequest.setFaceUrl(headImg);
			QCloudIMResponse qCloudIMResponse = TimRunner.getQcloudClient().execute(accountImportRequest);
			if(!qCloudIMResponse.isSuccess()) {
				throw new QCloudException(qCloudIMResponse.getErrorCode() + qCloudIMResponse.getErrorInfo());
			} else {
				return true;
			}
		} catch (QCloudException e) {
			log.error("创建云通信账号失败", e);
			throw new RuntimeException("创建云通信账号失败");
		}
	}

	/**
	 * 腾讯云通信添加好友
	 * @param imuserId 自己账户
	 * @param toAccount 对方账户
	 */
	@ApiOperation(value="腾讯云通信添加好友",notes = "腾讯云通信添加好友")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "imuserId", value = "自己账户", required = true),
			@ApiImplicitParam(name = "toAccount", value = "对方账户", required = true)
	})
	@PostMapping("/friendAdd/{imuserId}")
	public R friendAdd(@PathVariable("imuserId") String imuserId, String toAccount) {
		try {
			//添加好友关系
			List<AddFriendItemDO> addFriendItemList = Lists.newArrayList();
			AddFriendItemDO addFriendItemDO = new AddFriendItemDO();
			addFriendItemDO.setToAccount(imuserId);
			addFriendItemDO.setAddSource("AddSource_Type_APP");
			addFriendItemList.add(addFriendItemDO);

			SnsFriendAddRequest snsFriendAddRequest = new SnsFriendAddRequest();
			snsFriendAddRequest.setFromAccount(toAccount);
			snsFriendAddRequest.setAddFriendItemList(addFriendItemList);
			SnsFriendResponse snsFriendResponse = TimRunner.getQcloudClient().execute(snsFriendAddRequest);
			if(!snsFriendResponse.isSuccess()) {
				throw new QCloudException(snsFriendResponse.getErrorCode() + snsFriendResponse.getErrorInfo());
			} else {
				return new R<>(true);
			}
		} catch (QCloudException e) {
			log.error("腾讯云通信添加好友失败", e);
			throw new RuntimeException("腾讯云通信添加好友失败");
		}
	}

	/**
	 * 腾讯云通信删除好友
	 * @param imuserId 自己账户
	 * @param toAccount 对方账户
	 */
	@ApiOperation(value="腾讯云通信删除好友",notes = "腾讯云通信删除好友")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "imuserId", value = "自己账户", required = true),
			@ApiImplicitParam(name = "toAccount", value = "对方账户", required = true)
	})
	@DeleteMapping("/friendDelete/{imuserId}" )
	public R friendDelete(@PathVariable("imuserId") String imuserId, String toAccount) {
		try {
			//删除好友
			SnsFriendDeleteRequest snsFriendDeleteRequest = new SnsFriendDeleteRequest();
			snsFriendDeleteRequest.setFromAccount(imuserId);
			snsFriendDeleteRequest.setToAccounts(Lists.newArrayList(toAccount));
			snsFriendDeleteRequest.setDeleteType("Delete_Type_Both");
			SnsFriendResponse snsFriendResponse = TimRunner.getQcloudClient().execute(snsFriendDeleteRequest);
			if(!snsFriendResponse.isSuccess()) {
				throw new QCloudException(snsFriendResponse.getErrorCode() + snsFriendResponse.getErrorInfo());
			} else {
				return new R<>(true);
			}
		} catch (QCloudException e) {
			log.error("腾讯云通信删除好友失败", e);
			throw new RuntimeException("腾讯云通信删除好友失败");
		}
	}

	/**
	 * 获取某人全部的通讯录
	 * @param imuserId 自己账户
	 * @throws Exception
	 */
	@ApiOperation(value="获取某人全部的通讯录",notes = "获取某人全部的通讯录")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "imuserId", value = "自己账户", required = true)
	})
	@GetMapping("/getAllSnsFriendRequest/{imuserId}" )
	public R getAllSnsFriendRequest(@PathVariable("imuserId") String imuserId) throws Exception{
		SnsFriendGetAllRequest request = new SnsFriendGetAllRequest();
		request.setFromAccount(imuserId);
		SnsFriendGetAllResponse response = TimRunner.getQcloudClient().execute(request);
		log.debug(JSON.toJSONString(response));
		return new R<>(response);
	}

	/**
	 * 获取某人签名登入信息
	 * @param imuserId 自己账户
	 * @throws Exception
	 */
	@ApiOperation(value="获取某人签名登入信息",notes = "获取某人签名登入信息")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "imuserId", value = "账号", required = true)
	})
	@PostMapping("/getUserSign/{imuserId}")
	public R getUserSign(@PathVariable("imuserId") String imuserId) throws Exception{
		Map<String, String> userSigInfo = TimRunner.getQcloudClient().getUserSigInfo(imuserId);
		log.debug(JSON.toJSONString(userSigInfo));
		return new R<>(userSigInfo);
	}
}
