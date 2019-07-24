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
import com.pig4cloud.pigx.common.core.util.R;
import com.pig4cloud.pigx.common.log.annotation.SysLog;
import com.kasoft.register.base.entity.DoctorPeopleservicepackage;
import com.kasoft.register.base.service.DoctorPeopleservicepackageService;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


/**
 * 居民签约协议明细
 *
 * @author charlie
 * @date 2019-05-21 21:54:31
 */
@RestController
@AllArgsConstructor
@RequestMapping("/doctorpeopleservicepackage" )
@Api(value = "doctorpeopleservicepackage", tags = "核心业务-签约明细")
public class DoctorPeopleservicepackageController {

    private final  DoctorPeopleservicepackageService doctorPeopleservicepackageService;

    /**
     * 分页查询
     * @param page 分页对象
     * @param doctorPeopleservicepackage 居民签约协议明细
     * @return
     */
    @GetMapping("/page" )
    public R getDoctorPeopleservicepackagePage(Page page, DoctorPeopleservicepackage doctorPeopleservicepackage) {
        return new R<>(doctorPeopleservicepackageService.page(page, Wrappers.query(doctorPeopleservicepackage)));
    }


    /**
     * 通过id查询居民签约协议明细
     * @param id id
     * @return R
     */
    @GetMapping("/{id}" )
    public R getById(@PathVariable("id" ) String id) {
        return new R<>(doctorPeopleservicepackageService.getById(id));
    }

    /**
     * 新增居民签约协议明细
     * @param doctorPeopleservicepackage 居民签约协议明细
     * @return R
     */
    @SysLog("新增居民签约协议明细" )
    @PostMapping
    @PreAuthorize("@pms.hasPermission('base_doctorpeopleservicepackage_add')" )
    public R save(@Valid @RequestBody DoctorPeopleservicepackage doctorPeopleservicepackage) {
		doctorPeopleservicepackage.clearNoUseDTO();
        return new R<>(doctorPeopleservicepackageService.save(doctorPeopleservicepackage));
    }

    /**
     * 修改居民签约协议明细
     * @param doctorPeopleservicepackage 居民签约协议明细
     * @return R
     */
    @SysLog("修改居民签约协议明细" )
    @PutMapping
    @PreAuthorize("@pms.hasPermission('base_doctorpeopleservicepackage_edit')" )
    public R updateById(@Valid @RequestBody DoctorPeopleservicepackage doctorPeopleservicepackage) {
		doctorPeopleservicepackage.clearNoUseDTO();
    	return new R<>(doctorPeopleservicepackageService.updateById(doctorPeopleservicepackage));
    }

    /**
     * 通过id删除居民签约协议明细
     * @param id id
     * @return R
     */
    @SysLog("删除居民签约协议明细" )
    @DeleteMapping("/{id}" )
    @PreAuthorize("@pms.hasPermission('base_doctorpeopleservicepackage_del')" )
    public R removeById(@PathVariable String id) {
        return new R<>(doctorPeopleservicepackageService.removeById(id));
    }

}
