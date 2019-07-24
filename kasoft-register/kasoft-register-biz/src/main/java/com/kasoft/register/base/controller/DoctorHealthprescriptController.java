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
import com.kasoft.register.base.entity.DoctorHealthprescript;
import com.kasoft.register.base.service.DoctorHealthprescriptService;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


/**
 * 健康处方
 *
 * @author charlie
 * @date 2019-07-11 17:10:18
 */
@RestController
@AllArgsConstructor
@RequestMapping("/doctorhealthprescript" )
@Api(value = "doctorhealthprescript", tags = "基础-健康指导")
public class DoctorHealthprescriptController {

    private final  DoctorHealthprescriptService doctorHealthprescriptService;

    /**
     * 分页查询
     * @param page 分页对象
     * @param doctorHealthprescript 健康处方
     * @return
     */
    @GetMapping("/page" )
    public R getDoctorHealthprescriptPage(Page page, DoctorHealthprescript doctorHealthprescript) {
        return new R<>(doctorHealthprescriptService.page(page, Wrappers.query(doctorHealthprescript)));
    }


    /**
     * 通过id查询健康处方
     * @param healthprescriptId id
     * @return R
     */
    @GetMapping("/{healthprescriptId}" )
    public R getById(@PathVariable("healthprescriptId" ) String healthprescriptId) {
        return new R<>(doctorHealthprescriptService.getById(healthprescriptId));
    }

    /**
     * 新增健康处方
     * @param doctorHealthprescript 健康处方
     * @return R
     */
    @SysLog("新增健康处方" )
    @PostMapping
    @PreAuthorize("@pms.hasPermission('base_doctorhealthprescript_add')" )
    public R save(@RequestBody DoctorHealthprescript doctorHealthprescript) {
		doctorHealthprescript.clearNoUseDTO();
        return new R<>(doctorHealthprescriptService.save(doctorHealthprescript));
    }

    /**
     * 修改健康处方
     * @param doctorHealthprescript 健康处方
     * @return R
     */
    @SysLog("修改健康处方" )
    @PutMapping
    @PreAuthorize("@pms.hasPermission('base_doctorhealthprescript_edit')" )
    public R updateById(@RequestBody DoctorHealthprescript doctorHealthprescript) {
		doctorHealthprescript.clearNoUseDTO();
        return new R<>(doctorHealthprescriptService.updateById(doctorHealthprescript));
    }

    /**
     * 通过id删除健康处方
     * @param healthprescriptId id
     * @return R
     */
    @SysLog("删除健康处方" )
    @DeleteMapping("/{healthprescriptId}" )
    @PreAuthorize("@pms.hasPermission('base_doctorhealthprescript_del')" )
    public R removeById(@PathVariable String healthprescriptId) {
        return new R<>(doctorHealthprescriptService.removeById(healthprescriptId));
    }

}
