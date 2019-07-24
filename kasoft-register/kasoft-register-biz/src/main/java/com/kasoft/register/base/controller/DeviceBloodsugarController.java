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
import com.kasoft.register.base.entity.DeviceBloodsugar;
import com.kasoft.register.base.service.DeviceBloodsugarService;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


/**
 * 血糖管理
 *
 * @author charlie
 * @date 2019-07-11 16:35:16
 */
@RestController
@AllArgsConstructor
@RequestMapping("/devicebloodsugar" )
@Api(value = "devicebloodsugar", tags = "数据-血糖")
public class DeviceBloodsugarController {

    private final  DeviceBloodsugarService deviceBloodsugarService;

    /**
     * 分页查询
     * @param page 分页对象
     * @param deviceBloodsugar 血糖管理
     * @return
     */
    @GetMapping("/page" )
    public R getDeviceBloodsugarPage(Page page, DeviceBloodsugar deviceBloodsugar) {
        return new R<>(deviceBloodsugarService.page(page, Wrappers.query(deviceBloodsugar).orderByDesc("update_time")));
    }


    /**
     * 通过id查询血糖管理
     * @param bloodsugarId id
     * @return R
     */
    @GetMapping("/{bloodsugarId}" )
    public R getById(@PathVariable("bloodsugarId" ) String bloodsugarId) {
        return new R<>(deviceBloodsugarService.getById(bloodsugarId));
    }

    /**
     * 新增血糖管理
     * @param deviceBloodsugar 血糖管理
     * @return R
     */
    @SysLog("新增血糖管理" )
    @PostMapping
    @PreAuthorize("@pms.hasPermission('base_devicebloodsugar_add')" )
    public R save(@RequestBody DeviceBloodsugar deviceBloodsugar) {
		deviceBloodsugar.clearNoUseDTO();
        return new R<>(deviceBloodsugarService.save(deviceBloodsugar));
    }

    /**
     * 修改血糖管理
     * @param deviceBloodsugar 血糖管理
     * @return R
     */
    @SysLog("修改血糖管理" )
    @PutMapping
    @PreAuthorize("@pms.hasPermission('base_devicebloodsugar_edit')" )
    public R updateById(@RequestBody DeviceBloodsugar deviceBloodsugar) {
		deviceBloodsugar.clearNoUseDTO();
        return new R<>(deviceBloodsugarService.updateById(deviceBloodsugar));
    }

    /**
     * 通过id删除血糖管理
     * @param bloodsugarId id
     * @return R
     */
    @SysLog("删除血糖管理" )
    @DeleteMapping("/{bloodsugarId}" )
    @PreAuthorize("@pms.hasPermission('base_devicebloodsugar_del')" )
    public R removeById(@PathVariable String bloodsugarId) {
        return new R<>(deviceBloodsugarService.removeById(bloodsugarId));
    }

}
