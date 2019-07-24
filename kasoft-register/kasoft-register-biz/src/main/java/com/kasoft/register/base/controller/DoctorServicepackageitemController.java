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
import com.kasoft.register.base.entity.DoctorServicepackageitem;
import com.kasoft.register.base.service.DoctorServicepackageitemService;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


/**
 * 服务包项目
 *
 * @author charlie
 * @date 2019-05-21 21:55:26
 */
@RestController
@AllArgsConstructor
@RequestMapping("/doctorservicepackageitem" )
@Api(value = "doctorservicepackageitem", tags = "组织-套餐明细")
public class DoctorServicepackageitemController {

    private final  DoctorServicepackageitemService doctorServicepackageitemService;

    /**
     * 分页查询
     * @param page 分页对象
     * @param doctorServicepackageitem 服务包项目
     * @return
     */
    @GetMapping("/page" )
    public R getDoctorServicepackageitemPage(Page page, DoctorServicepackageitem doctorServicepackageitem) {
        return new R<>(doctorServicepackageitemService.page(page, Wrappers.query(doctorServicepackageitem)));
    }


    /**
     * 通过id查询服务包项目
     * @param id id
     * @return R
     */
    @GetMapping("/{id}" )
    public R getById(@PathVariable("id" ) String id) {
        return new R<>(doctorServicepackageitemService.getById(id));
    }

    /**
     * 新增服务包项目
     * @param doctorServicepackageitem 服务包项目
     * @return R
     */
    @SysLog("新增服务包项目" )
    @PostMapping
    @PreAuthorize("@pms.hasPermission('base_doctorservicepackageitem_add')" )
    public R save(@Valid @RequestBody DoctorServicepackageitem doctorServicepackageitem) {
		doctorServicepackageitem.clearNoUseDTO();
    	return new R<>(doctorServicepackageitemService.save(doctorServicepackageitem));
    }

    /**
     * 修改服务包项目
     * @param doctorServicepackageitem 服务包项目
     * @return R
     */
    @SysLog("修改服务包项目" )
    @PutMapping
    @PreAuthorize("@pms.hasPermission('base_doctorservicepackageitem_edit')" )
    public R updateById(@Valid @RequestBody DoctorServicepackageitem doctorServicepackageitem) {
		doctorServicepackageitem.clearNoUseDTO();
    	return new R<>(doctorServicepackageitemService.updateById(doctorServicepackageitem));
    }

    /**
     * 通过id删除服务包项目
     * @param id id
     * @return R
     */
    @SysLog("删除服务包项目" )
    @DeleteMapping("/{id}" )
    @PreAuthorize("@pms.hasPermission('base_doctorservicepackageitem_del')" )
    public R removeById(@PathVariable String id) {
        return new R<>(doctorServicepackageitemService.removeById(id));
    }

}
