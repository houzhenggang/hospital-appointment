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
import com.kasoft.register.base.entity.DoctorSoftmanager;
import com.kasoft.register.base.service.DoctorSoftmanagerService;
import io.swagger.annotations.Api;
import org.springframework.security.access.prepost.PreAuthorize;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


/**
 * 设备软件管理
 *
 * @author charlie
 * @date 2019-04-30 17:10:23
 */
@RestController
@AllArgsConstructor
@RequestMapping("/doctorsoftmanager" )
@Api(value = "doctorserviceteam", tags = "基础业务-软件管理")
public class DoctorSoftmanagerController {

    private final  DoctorSoftmanagerService doctorSoftmanagerService;

    /**
     * 分页查询
     * @param page 分页对象
     * @param doctorSoftmanager 设备软件管理
     * @return
     */
    @GetMapping("/page" )
    public R getDoctorSoftmanagerPage(Page page, DoctorSoftmanager doctorSoftmanager) {
        return new R<>(doctorSoftmanagerService.page(page, Wrappers.query(doctorSoftmanager)));
    }


    /**
     * 通过id查询设备软件管理
     * @param id id
     * @return R
     */
    @GetMapping("/{id}" )
    public R getById(@PathVariable("id" ) String id) {
        return new R<>(doctorSoftmanagerService.getById(id));
    }

    /**
     * 新增设备软件管理
     * @param doctorSoftmanager 设备软件管理
     * @return R
     */
    @SysLog("新增设备软件管理" )
    @PostMapping
    @PreAuthorize("@pms.hasPermission('base_doctorsoftmanager_add')" )
    public R save(@Valid @RequestBody DoctorSoftmanager doctorSoftmanager) {
		doctorSoftmanager.clearNoUseDTO();
        return new R<>(doctorSoftmanagerService.save(doctorSoftmanager));
    }

    /**
     * 修改设备软件管理
     * @param doctorSoftmanager 设备软件管理
     * @return R
     */
    @SysLog("修改设备软件管理" )
    @PutMapping
    @PreAuthorize("@pms.hasPermission('base_doctorsoftmanager_edit')" )
    public R updateById(@Valid @RequestBody DoctorSoftmanager doctorSoftmanager) {
		doctorSoftmanager.clearNoUseDTO();
        return new R<>(doctorSoftmanagerService.updateById(doctorSoftmanager));
    }

    /**
     * 通过id删除设备软件管理
     * @param id id
     * @return R
     */
    @SysLog("删除设备软件管理" )
    @DeleteMapping("/{id}" )
    @PreAuthorize("@pms.hasPermission('base_doctorsoftmanager_del')" )
    public R removeById(@PathVariable String id) {
        return new R<>(doctorSoftmanagerService.removeById(id));
    }

}
