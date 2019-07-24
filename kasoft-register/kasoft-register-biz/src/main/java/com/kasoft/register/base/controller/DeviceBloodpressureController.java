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
import com.kasoft.register.base.entity.DeviceBloodpressure;
import com.kasoft.register.base.service.DeviceBloodpressureService;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


/**
 * 血压管理
 *
 * @author charlie
 * @date 2019-07-11 16:35:20
 */
@RestController
@AllArgsConstructor
@RequestMapping("/devicebloodpressure" )
@Api(value = "devicebloodpressure", tags = "数据-血压")
public class DeviceBloodpressureController {

    private final  DeviceBloodpressureService deviceBloodpressureService;

    /**
     * 分页查询
     * @param page 分页对象
     * @param deviceBloodpressure 血压管理
     * @return
     */
    @GetMapping("/page" )
    public R getDeviceBloodpressurePage(Page page, DeviceBloodpressure deviceBloodpressure) {
        return new R<>(deviceBloodpressureService.page(page, Wrappers.query(deviceBloodpressure).orderByDesc("update_time")));
    }


    /**
     * 通过id查询血压管理
     * @param bloodpressureId id
     * @return R
     */
    @GetMapping("/{bloodpressureId}" )
    public R getById(@PathVariable("bloodpressureId" ) String bloodpressureId) {
        return new R<>(deviceBloodpressureService.getById(bloodpressureId));
    }

    /**
     * 新增血压管理
     * @param deviceBloodpressure 血压管理
     * @return R
     */
    @SysLog("新增血压管理" )
    @PostMapping
    @PreAuthorize("@pms.hasPermission('base_devicebloodpressure_add')" )
    public R save(@RequestBody DeviceBloodpressure deviceBloodpressure) {
		deviceBloodpressure.clearNoUseDTO();
        return new R<>(deviceBloodpressureService.save(deviceBloodpressure));
    }

    /**
     * 修改血压管理
     * @param deviceBloodpressure 血压管理
     * @return R
     */
    @SysLog("修改血压管理" )
    @PutMapping
    @PreAuthorize("@pms.hasPermission('base_devicebloodpressure_edit')" )
    public R updateById(@RequestBody DeviceBloodpressure deviceBloodpressure) {
		deviceBloodpressure.clearNoUseDTO();
        return new R<>(deviceBloodpressureService.updateById(deviceBloodpressure));
    }

    /**
     * 通过id删除血压管理
     * @param bloodpressureId id
     * @return R
     */
    @SysLog("删除血压管理" )
    @DeleteMapping("/{bloodpressureId}" )
    @PreAuthorize("@pms.hasPermission('base_devicebloodpressure_del')" )
    public R removeById(@PathVariable String bloodpressureId) {
        return new R<>(deviceBloodpressureService.removeById(bloodpressureId));
    }

}
