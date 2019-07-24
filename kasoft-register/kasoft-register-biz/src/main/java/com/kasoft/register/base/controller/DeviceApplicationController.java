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
import com.kasoft.register.base.dto.DeviceApplicationDTO;
import com.pig4cloud.pigx.common.core.util.R;
import com.pig4cloud.pigx.common.log.annotation.SysLog;
import com.kasoft.register.base.entity.DeviceApplication;
import com.kasoft.register.base.service.DeviceApplicationService;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


/**
 * 居民自助建档签约
 *
 * @author charlie
 * @date 2019-05-14 10:55:40
 */
@RestController
@AllArgsConstructor
@RequestMapping("/deviceapplication" )
@Api(value = "deviceapplication", tags = "核心业务-自助建档签约")
public class DeviceApplicationController {

    private final  DeviceApplicationService deviceApplicationService;

    /**
     * 分页查询
     * @param page 分页对象
     * @param deviceApplication 居民自助建档签约
     * @return
     */
    @GetMapping("/page" )
    public R getDeviceApplicationPage(Page page, DeviceApplication deviceApplication) {
        return new R<>(deviceApplicationService.page(page, Wrappers.query(deviceApplication).orderByDesc("update_time")));
    }


    /**
     * 通过id查询居民自助建档签约
     * @param id id
     * @return R
     */
    @GetMapping("/{id}" )
    public R getById(@PathVariable("id" ) String id) {
        return new R<>(deviceApplicationService.getById(id));
    }

    /**
     * 新增居民自助建档签约
     * @param deviceApplicationDTO 居民自助建档签约
     * @return R
     */
    @SysLog("新增居民自助建档签约" )
    @PostMapping
    public R save(@RequestBody DeviceApplicationDTO deviceApplicationDTO) {
		deviceApplicationDTO.clearNoUseDTO();
        return new R<>(deviceApplicationService.save(deviceApplicationDTO));
    }

    /**
     * 修改居民自助建档签约
     * @param deviceApplicationDTO 居民自助建档签约
     * @return R
     */
    @SysLog("修改居民自助建档签约" )
    @PutMapping
    @PreAuthorize("@pms.hasPermission('base_deviceapplication_edit')" )
    public R updateById(@RequestBody DeviceApplicationDTO deviceApplicationDTO) {
		deviceApplicationDTO.clearNoUseDTO();
        return new R<>(deviceApplicationService.updateById(deviceApplicationDTO));
    }

    /**
     * 通过id删除居民自助建档签约
     * @param id id
     * @return R
     */
    @SysLog("删除居民自助建档签约" )
    @DeleteMapping("/{id}" )
    public R removeById(@PathVariable String id) {
        return new R<>(deviceApplicationService.removeById(id));
    }

}
