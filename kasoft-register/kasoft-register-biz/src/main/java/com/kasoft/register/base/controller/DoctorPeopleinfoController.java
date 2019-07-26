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
import com.kasoft.register.base.entity.DoctorPeopleinfo;
import com.kasoft.register.base.service.DoctorPeopleinfoService;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;


/**
 * 居民基本信息表
 *
 * @author charlie
 * @date 2019-07-26 09:58:13
 */
@RestController
@AllArgsConstructor
@RequestMapping("/doctorpeopleinfo" )
@Api(value = "doctorpeopleinfo", tags = "基础-居民")
public class DoctorPeopleinfoController {

    private final  DoctorPeopleinfoService doctorPeopleinfoService;

    /**
     * 分页查询
     * @param page 分页对象
     * @param doctorPeopleinfo 居民基本信息表
     * @return
     */
    @ApiOperation(value = "分页查询", notes = "分页查询")
    @GetMapping("/page" )
    public R getDoctorPeopleinfoPage(Page page, DoctorPeopleinfo doctorPeopleinfo) {
        return R.ok(doctorPeopleinfoService.page(page, Wrappers.query(doctorPeopleinfo)));
    }


    /**
     * 通过id查询居民基本信息表
     * @param id id
     * @return R
     */
    @ApiOperation(value = "通过id查询", notes = "通过id查询")
    @GetMapping("/{id}" )
    public R getById(@PathVariable("id" ) String id) {
        return R.ok(doctorPeopleinfoService.getById(id));
    }

    /**
     * 新增居民基本信息表
     * @param doctorPeopleinfo 居民基本信息表
     * @return R
     */
    @ApiOperation(value = "新增居民基本信息表", notes = "新增居民基本信息表")
    @SysLog("新增居民基本信息表" )
    @PostMapping
    @PreAuthorize("@pms.hasPermission('base_doctorpeopleinfo_add')" )
    public R save(@RequestBody DoctorPeopleinfo doctorPeopleinfo) {
    	doctorPeopleinfo.clearNoUseDTO();
        return R.ok(doctorPeopleinfoService.save(doctorPeopleinfo));
    }

    /**
     * 修改居民基本信息表
     * @param doctorPeopleinfo 居民基本信息表
     * @return R
     */
    @ApiOperation(value = "修改居民基本信息表", notes = "修改居民基本信息表")
    @SysLog("修改居民基本信息表" )
    @PutMapping
    @PreAuthorize("@pms.hasPermission('base_doctorpeopleinfo_edit')" )
    public R updateById(@RequestBody DoctorPeopleinfo doctorPeopleinfo) {
		doctorPeopleinfo.clearNoUseDTO();
        return R.ok(doctorPeopleinfoService.updateById(doctorPeopleinfo));
    }

    /**
     * 通过id删除居民基本信息表
     * @param id id
     * @return R
     */
    @ApiOperation(value = "通过id删除居民基本信息表", notes = "通过id删除居民基本信息表")
    @SysLog("通过id删除居民基本信息表" )
    @DeleteMapping("/{id}" )
    @PreAuthorize("@pms.hasPermission('base_doctorpeopleinfo_del')" )
    public R removeById(@PathVariable String id) {
        return R.ok(doctorPeopleinfoService.removeById(id));
    }

}
