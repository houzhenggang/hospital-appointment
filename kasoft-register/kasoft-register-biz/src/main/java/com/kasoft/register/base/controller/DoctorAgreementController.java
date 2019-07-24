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

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kasoft.register.base.dto.DoctorAgreementDTO;
import com.pig4cloud.pigx.common.core.util.R;
import com.pig4cloud.pigx.common.log.annotation.SysLog;
import com.kasoft.register.base.entity.DoctorAgreement;
import com.kasoft.register.base.service.DoctorAgreementService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


/**
 * 居民签约协议
 *
 * @author charlie
 * @date 2019-05-07 15:43:03
 */
@RestController
@AllArgsConstructor
@RequestMapping("/doctoragreement" )
@Api(value = "doctoragreement", tags = "核心业务-签约")
public class DoctorAgreementController {

    private final  DoctorAgreementService doctorAgreementService;

    /**
     * 分页查询居民签约信息根据用户id
     * @param page 分页对象
     * @param userId 用户编号
     * @return
     */
	@ApiOperation(value = "分页查询居民签约信息根据用户id", notes = "分页查询居民签约信息根据用户id")
	@GetMapping("/page/{userId}" )
    public R getDoctorAgreementPageByUserId(Page page, @PathVariable("userId" ) Integer userId) {
        return new R<>(doctorAgreementService.pageByUserId(page, userId));
    }

	/**
	 * 分页查询
	 * @param page 分页对象
	 * @param doctorAgreement 居民签约协议
	 * @return
	 */
	@GetMapping("/page" )
	public R getDoctorAgreementPage(Page page, DoctorAgreement doctorAgreement) {
		return new R<>(doctorAgreementService.page(page, Wrappers.query(doctorAgreement).orderByDesc("update_time")));
	}


    /**
     * 通过id查询居民签约协议
     * @param id id
     * @return R
     */
    @GetMapping("/{id}" )
    public R getById(@PathVariable("id" ) String id) {
        return new R<>(doctorAgreementService.getById(id));
    }

    /**
     * 新增居民签约协议
     * @param doctorAgreement 居民签约协议
     * @return R
     */
    @SysLog("新增居民签约协议" )
    @PostMapping
    @PreAuthorize("@pms.hasPermission('base_doctoragreement_add')" )
    public R save(@Valid @RequestBody DoctorAgreement doctorAgreement) {
		doctorAgreement.clearNoUseDTO();
        return new R<>(doctorAgreementService.save(doctorAgreement));
    }

	/**
	 * 新增和修改签约内容
	 * @param doctorAgreementDTO 服务包DTO
	 * @return R
	 */
	@ApiOperation(value = "新增和修改签约内容", notes = "新增和修改签约内容")
	@SysLog("新增和修改签约内容" )
	@PostMapping("/saveRemoveItemList" )
	public R saveRemoveItemList(@Valid @RequestBody DoctorAgreementDTO doctorAgreementDTO) {
		doctorAgreementDTO.clearNoUseDTO();
		return new R<>(doctorAgreementService.saveRemoveItemList(doctorAgreementDTO));
	}


	/**
     * 修改居民签约协议
     * @param doctorAgreement 居民签约协议
     * @return R
     */
    @SysLog("修改居民签约协议" )
    @PutMapping
    @PreAuthorize("@pms.hasPermission('base_doctoragreement_edit')" )
    public R updateById(@Valid @RequestBody DoctorAgreement doctorAgreement) {
		doctorAgreement.clearNoUseDTO();
        return new R<>(doctorAgreementService.updateById(doctorAgreement));
    }

    /**
     * 通过id删除居民签约协议
     * @param id id
     * @return R
     */
    @SysLog("删除居民签约协议" )
    @DeleteMapping("/{id}" )
    @PreAuthorize("@pms.hasPermission('base_doctoragreement_del')" )
    public R removeById(@PathVariable String id) {
        return new R<>(doctorAgreementService.removeById(id));
    }

}
