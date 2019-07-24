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

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kasoft.register.base.dto.DoctorPeopleinfoDTO;
import com.pig4cloud.pigx.admin.api.dto.UserDTO;
import com.pig4cloud.pigx.common.core.util.R;
import com.pig4cloud.pigx.common.log.annotation.SysLog;
import com.kasoft.register.base.entity.DoctorPeopleinfo;
import com.kasoft.register.base.service.DoctorPeopleinfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


/**
 * 居民基本信息表
 *
 * @author charlie
 * @date 2019-05-07 15:21:30
 */
@RestController
@AllArgsConstructor
@RequestMapping("/doctorpeopleinfo" )
@Api(value = "doctorpeopleinfo", tags = "核心业务-档案")
@Slf4j
public class DoctorPeopleinfoController {

    private final  DoctorPeopleinfoService doctorPeopleinfoService;

    /**
     * 分页查询
     * @param page 分页对象
     * @param doctorPeopleinfo 居民基本信息表
     * @return
     */
    @GetMapping("/page" )
    public R getDoctorPeopleinfoPage(Page page, DoctorPeopleinfo doctorPeopleinfo) {
        return new R<>(doctorPeopleinfoService.page(page, doctorPeopleinfo, false));
    }

	/**
	 * 分页查询待随访居民
	 * @param page 分页对象
	 * @param doctorPeopleinfo 居民基本信息表
	 * @return
	 */
	@ApiOperation(value = "分页查询待随访居民", notes = "分页查询待随访居民")
	@GetMapping("/pageform" )
	public R getDoctorPeopleinfoPageForm(Page page, DoctorPeopleinfo doctorPeopleinfo) {
		return new R<>(doctorPeopleinfoService.page(page, doctorPeopleinfo, true));
	}

	/**
	 * 判断用户名是否存在
	 * @param userName 用户名
	 * @return R
	 */
	@ApiOperation(value = "判断用户名是否存在", notes = "判断用户名是否存在", response = Boolean.class)
	@ApiImplicitParams({
			@ApiImplicitParam(name = "userName", value = "用户名")})
	@SysLog("注册居民账号" )
	@GetMapping("/hasUserName/{userName}")
	public R hasUserName(@PathVariable("userName" ) String userName) {
		return new R<>(doctorPeopleinfoService.hasUserName(userName));
	}

	/**
	 * 注册居民账号
	 * @param userDTO 居民账号
	 * @return R
	 */
	@ApiOperation(value = "注册居民账号", notes = "注册居民账号", response = Boolean.class)
	@SysLog("注册居民账号" )
	@PostMapping("/register")
	public R register(@Valid @RequestBody UserDTO userDTO) {
		return new R<>(doctorPeopleinfoService.register(userDTO));
	}

	/**
	 * 通过身份证查询居民基本信息表
	 * @param idCard 身份证
	 * @return R
	 */
	@ApiOperation(value = "通过身份证查询居民基本信息表", notes = "通过身份证查询居民基本信息表", response = DoctorPeopleinfoDTO.class)
	@ApiImplicitParams({
			@ApiImplicitParam(name = "idCard", value = "身份证")})
	@GetMapping("/idcard/{idCard}" )
	public R getByIdcard(@PathVariable("idCard" ) String idCard) {
		return new R<>(doctorPeopleinfoService.getDetailByIdcard(idCard));
	}

    /**
     * 通过用户查询居民基本信息表
     * @param userId 用户id
     * @return R
     */
	@ApiOperation(value = "通过用户查询居民基本信息表", notes = "通过用户查询居民基本信息表", response = DoctorPeopleinfoDTO.class)
	@ApiImplicitParams({
			@ApiImplicitParam(name = "userId", value = "用户ID")})
    @GetMapping("/user/{userId}" )
    public R getByUserId(@PathVariable("userId" ) Integer userId) {
        return new R<>(doctorPeopleinfoService.getDetailByUserId(userId));
    }

	/**
	 * 通过id查询居民基本信息表
	 * @param id id
	 * @return R
	 */
	@GetMapping("/{id}" )
	public R getById(@PathVariable("id" ) String id) {
		return new R<>(doctorPeopleinfoService.getDetailById(id));
	}

    /**
     * 新增居民基本信息表
     * @param doctorPeopleinfo 居民基本信息表
     * @return R
     */
    @SysLog("新增居民基本信息表" )
    @PostMapping
    @PreAuthorize("@pms.hasPermission('base_doctorpeopleinfo_add')" )
    public R save(@Valid @RequestBody DoctorPeopleinfoDTO doctorPeopleinfo) {
		doctorPeopleinfo.clearNoUseDTO();
        return new R<>(doctorPeopleinfoService.save(doctorPeopleinfo));
    }

    /**
     * 修改居民基本信息表
     * @param doctorPeopleinfo 居民基本信息表
     * @return R
     */
    @SysLog("修改居民基本信息表" )
    @PutMapping
    @PreAuthorize("@pms.hasPermission('base_doctorpeopleinfo_edit')" )
    public R updateById(@Valid @RequestBody DoctorPeopleinfoDTO doctorPeopleinfo) {
		doctorPeopleinfo.clearNoUseDTO();
        return new R<>(doctorPeopleinfoService.updateById(doctorPeopleinfo));
    }

    /**
     * 通过id删除居民基本信息表
     * @param id id
     * @return R
     */
    @SysLog("删除居民基本信息表" )
    @DeleteMapping("/{id}" )
    @PreAuthorize("@pms.hasPermission('base_doctorpeopleinfo_del')" )
    public R removeById(@PathVariable String id) {
        return new R<>(doctorPeopleinfoService.removeById(id));
    }


	/**
	 * 通过id查询居民高血压专案
	 * @param id 居民编号
	 * @return R
	 */
	@ApiOperation(value = "查询居民高血压专案", notes = "查询居民高血压专案", response = MongoProjectHypertension.class)
	@ApiImplicitParams({
			@ApiImplicitParam(name = "id", value = "居民编号")})
	@GetMapping("/hypertension/{id}" )
	public R getHypertensionById(@PathVariable("id" ) String id) {
		return new R<>(doctorPeopleinfoService.getHypertensionById(id));
	}

	/**
	 * 修改居民高血压专案
	 * @param projectHypertension 居民高血压专案
	 * @return R
	 */
	@ApiOperation(value = "修改居民高血压专案", notes = "修改居民高血压专案")
	@SysLog("修改居民基本信息表" )
	@PutMapping("/hypertension")
	@PreAuthorize("@pms.hasPermission('base_doctorpeopleinfo_edit')" )
	public R updateHypertensionById(@Valid @RequestBody MongoProjectHypertension projectHypertension) {
		return new R<>(doctorPeopleinfoService.updateHypertensionById(projectHypertension));
	}

	/**
	 * 通过id查询居民糖尿病专案
	 * @param id 居民编号
	 * @return R
	 */
	@ApiOperation(value = "查询居民糖尿病专案", notes = "查询居民糖尿病专案", response = MongoProjectHypertension.class)
	@ApiImplicitParams({
			@ApiImplicitParam(name = "id", value = "居民编号")})
	@GetMapping("/diabetes/{id}" )
	public R getDiabetesById(@PathVariable("id" ) String id) {
		return new R<>(doctorPeopleinfoService.getDiabetesById(id));
	}

	/**
	 * 修改居民糖尿病专案
	 * @param projectDiabetes 居民糖尿病专案
	 * @return R
	 */
	@ApiOperation(value = "修改居民糖尿病专案", notes = "修改居民糖尿病专案")
	@SysLog("修改居民基本信息表" )
	@PutMapping("/diabetes")
	@PreAuthorize("@pms.hasPermission('base_doctorpeopleinfo_edit')" )
	public R updateDiabetesById(@Valid @RequestBody MongoProjectDiabetes projectDiabetes) {
		return new R<>(doctorPeopleinfoService.updateDiabetesById(projectDiabetes));
	}

	/**
	 * 通过id查询居民老年人专案
	 * @param id 居民编号
	 * @return R
	 */
	@ApiOperation(value = "查询居民老年人专案", notes = "查询居民老年人专案", response = MongoProjectHypertension.class)
	@ApiImplicitParams({
			@ApiImplicitParam(name = "id", value = "居民编号")})
	@GetMapping("/OldPeople/{id}" )
	public R getOldPeopleById(@PathVariable("id" ) String id) {
		return new R<>(doctorPeopleinfoService.getOldPeopleById(id));
	}

	/**
	 * 修改居民老年人专案
	 * @param projectOldPeople 居民老年人专案
	 * @return R
	 */
	@ApiOperation(value = "修改居民老年人专案", notes = "修改居民老年人专案")
	@SysLog("修改居民基本信息表" )
	@PutMapping("/OldPeople")
	@PreAuthorize("@pms.hasPermission('base_doctorpeopleinfo_edit')" )
	public R updateOldPeopleById(@Valid @RequestBody MongoProjectOldPeople projectOldPeople) {
		return new R<>(doctorPeopleinfoService.updateOldPeopleById(projectOldPeople));
	}
}
