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
import com.kasoft.register.base.entity.DoctorDrugreminding;
import com.kasoft.register.base.service.DoctorDrugremindingService;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


/**
 * 用药管理
 *
 * @author charlie
 * @date 2019-07-11 16:35:08
 */
@RestController
@AllArgsConstructor
@RequestMapping("/doctordrugreminding" )
@Api(value = "doctordrugreminding", tags = "核心-用药")
public class DoctorDrugremindingController {

    private final  DoctorDrugremindingService doctorDrugremindingService;

    /**
     * 分页查询
     * @param page 分页对象
     * @param doctorDrugreminding 用药管理
     * @return
     */
    @GetMapping("/page" )
    public R getDoctorDrugremindingPage(Page page, DoctorDrugreminding doctorDrugreminding) {
        return new R<>(doctorDrugremindingService.page(page, Wrappers.query(doctorDrugreminding).orderByDesc("update_time")));
    }


    /**
     * 通过id查询用药管理
     * @param drugremindingId id
     * @return R
     */
    @GetMapping("/{drugremindingId}" )
    public R getById(@PathVariable("drugremindingId" ) String drugremindingId) {
        return new R<>(doctorDrugremindingService.getById(drugremindingId));
    }

    /**
     * 新增用药管理
     * @param doctorDrugreminding 用药管理
     * @return R
     */
    @SysLog("新增用药管理" )
    @PostMapping
    @PreAuthorize("@pms.hasPermission('base_doctordrugreminding_add')" )
    public R save(@RequestBody DoctorDrugreminding doctorDrugreminding) {
		doctorDrugreminding.clearNoUseDTO();
        return new R<>(doctorDrugremindingService.save(doctorDrugreminding));
    }

    /**
     * 修改用药管理
     * @param doctorDrugreminding 用药管理
     * @return R
     */
    @SysLog("修改用药管理" )
    @PutMapping
    @PreAuthorize("@pms.hasPermission('base_doctordrugreminding_edit')" )
    public R updateById(@RequestBody DoctorDrugreminding doctorDrugreminding) {
		doctorDrugreminding.clearNoUseDTO();
        return new R<>(doctorDrugremindingService.updateById(doctorDrugreminding));
    }

    /**
     * 通过id删除用药管理
     * @param drugremindingId id
     * @return R
     */
    @SysLog("删除用药管理" )
    @DeleteMapping("/{drugremindingId}" )
    @PreAuthorize("@pms.hasPermission('base_doctordrugreminding_del')" )
    public R removeById(@PathVariable String drugremindingId) {
        return new R<>(doctorDrugremindingService.removeById(drugremindingId));
    }

}
