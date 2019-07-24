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
import com.kasoft.register.base.utils.EdConstants;
import com.pig4cloud.pigx.common.core.util.R;
import com.pig4cloud.pigx.common.log.annotation.SysLog;
import com.kasoft.register.base.entity.DoctorDoctorinfo;
import com.kasoft.register.base.service.DoctorDoctorinfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


/**
 * 医生信息表
 *
 * @author charlie
 * @date 2019-04-30 17:10:49
 */
@RestController
@AllArgsConstructor
@RequestMapping("/doctordoctorinfo" )
@Api(value = "doctordoctorinfo", tags = "组织-医生")
public class DoctorDoctorinfoController {

    private final  DoctorDoctorinfoService doctorDoctorinfoService;

    /**
     * 分页查询
     * @param page 分页对象
     * @param doctorDoctorinfo 医生信息表
     * @return
     */
    @GetMapping("/page" )
    public R getDoctorDoctorinfoPage(Page page, DoctorDoctorinfo doctorDoctorinfo) {
        return R.ok(doctorDoctorinfoService.page(page, doctorDoctorinfo));
    }

	/**
	 * 根据医院编号查询医生字典
	 *  @param hospitalId 医院编号
	 * @return
	 */
	@ApiOperation(value = "根据医院编号查询医生字典", notes = "根据医院编号查询医生字典,备注{{key}}表示查询全部")
	@GetMapping("/dict/{hospitalId}" )
	public R getDoctorDictByHospital(@PathVariable("hospitalId" )String hospitalId) {
		if(EdConstants.ALL_KEY.equals(hospitalId)) {
			hospitalId = null;
		}
		return R.ok(doctorDoctorinfoService.getDoctorDictByHospital(hospitalId));
	}
	/**
	 * 查询全部医生字典
	 * @return
	 */
	@ApiOperation(value = "查询全部医生字典", notes = "查询全部医生字典")
	@GetMapping("/dict-all" )
	public R getDoctorDictAll() {
		return R.ok(doctorDoctorinfoService.getDoctorDictByHospital(null));
	}

	/**
	 * 根据团队编号查询医生字典
	 *  @param teamId 团队编号
	 * @return
	 */
	@ApiOperation(value = "根据团队编号查询医生字典", notes = "根据团队编号查询医生字典,备注{{key}}表示查询全部")
	@GetMapping("/dict-team/{teamId}" )
	public R getDoctorDictByTeam(@PathVariable("teamId" )String teamId) {
		if(EdConstants.ALL_KEY.equals(teamId)) {
			teamId = null;
		}
		return R.ok(doctorDoctorinfoService.getDoctorDictByTeam(teamId));
	}


	/**
     * 通过id查询医生信息表
     * @param id id
     * @return R
     */
    @GetMapping("/{id}" )
    public R getById(@PathVariable("id" ) String id) {
        return R.ok(doctorDoctorinfoService.getById(id));
    }

	/**
	 * 通过用户id查询医生信息表
	 * @param userId 用户号
	 * @return R
	 */
	@GetMapping("/user/{userId}" )
	@ApiOperation(value = "通过用户id查询医生信息表", notes = "医生关联用户后可以通过用户编号查询医生信息")
	public R getByUserId(@PathVariable("userId" ) Integer userId) {
		return R.ok(doctorDoctorinfoService.getByUserId(userId));
	}

    /**
     * 新增医生信息表
     * @param doctorDoctorinfo 医生信息表
     * @return R
     */
    @SysLog("新增医生信息表" )
    @PostMapping
    @PreAuthorize("@pms.hasPermission('base_doctordoctorinfo_add')" )
    public R save(@Valid @RequestBody DoctorDoctorinfo doctorDoctorinfo) {
		doctorDoctorinfo.clearNoUseDTO();
        return R.ok(doctorDoctorinfoService.save(doctorDoctorinfo));
    }

    /**
     * 修改医生信息表
     * @param doctorDoctorinfo 医生信息表
     * @return R
     */
    @SysLog("修改医生信息表" )
    @PutMapping
    @PreAuthorize("@pms.hasPermission('base_doctordoctorinfo_edit')" )
    public R updateById(@Valid @RequestBody DoctorDoctorinfo doctorDoctorinfo) {
		doctorDoctorinfo.clearNoUseDTO();
        return R.ok(doctorDoctorinfoService.updateById(doctorDoctorinfo));
    }

    /**
     * 通过id删除医生信息表
     * @param id id
     * @return R
     */
    @SysLog("删除医生信息表" )
    @DeleteMapping("/{id}" )
    @PreAuthorize("@pms.hasPermission('base_doctordoctorinfo_del')" )
    public R removeById(@PathVariable String id) {
        return R.ok(doctorDoctorinfoService.removeById(id));
    }

}
