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
import com.kasoft.register.base.dto.DoctorCommonformDTO;
import com.pig4cloud.pigx.common.core.util.R;
import com.pig4cloud.pigx.common.log.annotation.SysLog;
import com.kasoft.register.base.entity.DoctorCommonform;
import com.kasoft.register.base.service.DoctorCommonformService;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


/**
 * 通用随访
 *
 * @author charlie
 * @date 2019-06-15 11:40:08
 */
@RestController
@AllArgsConstructor
@RequestMapping("/doctorcommonform" )
@Api(value = "doctorcommonform", tags = "核心-通用随访")
public class DoctorCommonformController {

    private final  DoctorCommonformService doctorCommonformService;

    /**
     * 分页查询
     * @param page 分页对象
     * @param doctorCommonform 通用随访
     * @return
     */
    @GetMapping("/page" )
    public R getDoctorCommonformPage(Page page, DoctorCommonform doctorCommonform) {
        return new R<>(doctorCommonformService.page(page, Wrappers.query(doctorCommonform)));
    }


    /**
     * 通过id查询通用随访
     * @param commonFormId id
     * @return R
     */
    @GetMapping("/{commonFormId}" )
    public R getById(@PathVariable("commonFormId" ) String commonFormId) {
        return new R<>(doctorCommonformService.getById(commonFormId));
    }

    /**
     * 新增通用随访
     * @param doctorCommonform 通用随访
     * @return R
     */
    @SysLog("新增通用随访" )
    @PostMapping
    @PreAuthorize("@pms.hasPermission('base_doctorcommonform_add')" )
    public R save(@Valid @RequestBody DoctorCommonformDTO doctorCommonform) {
		doctorCommonform.clearNoUseDTO();
		return new R<>(doctorCommonformService.save(doctorCommonform));
    }

    /**
     * 修改通用随访
     * @param doctorCommonform 通用随访
     * @return R
     */
    @SysLog("修改通用随访" )
    @PutMapping
    @PreAuthorize("@pms.hasPermission('base_doctorcommonform_edit')" )
    public R updateById(@Valid @RequestBody DoctorCommonformDTO doctorCommonform) {
		doctorCommonform.clearNoUseDTO();
        return new R<>(doctorCommonformService.updateById(doctorCommonform));
    }

    /**
     * 通过id删除通用随访
     * @param commonFormId id
     * @return R
     */
    @SysLog("删除通用随访" )
    @DeleteMapping("/{commonFormId}" )
    @PreAuthorize("@pms.hasPermission('base_doctorcommonform_del')" )
    public R removeById(@PathVariable String commonFormId) {
        return new R<>(doctorCommonformService.removeById(commonFormId));
    }

}
