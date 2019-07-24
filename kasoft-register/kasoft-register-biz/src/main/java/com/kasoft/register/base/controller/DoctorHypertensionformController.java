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

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kasoft.register.base.dto.DoctorHypertensionformDTO;
import com.pig4cloud.pigx.common.core.util.R;
import com.pig4cloud.pigx.common.log.annotation.SysLog;
import com.kasoft.register.base.entity.DoctorHypertensionform;
import com.kasoft.register.base.service.DoctorHypertensionformService;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


/**
 * 高血压随访
 *
 * @author charlie
 * @date 2019-06-08 10:39:31
 */
@RestController
@AllArgsConstructor
@RequestMapping("/doctorhypertensionform" )
@Api(value = "doctorhypertensionform", tags = "核心-高血压随访")
public class DoctorHypertensionformController {

    private final  DoctorHypertensionformService doctorHypertensionformService;

    /**
     * 分页查询
     * @param page 分页对象
     * @param doctorHypertensionform 高血压随访
     * @return
     */
    @GetMapping("/page" )
    public R getDoctorHypertensionformPage(Page page, DoctorHypertensionform doctorHypertensionform) {
        return new R<>(doctorHypertensionformService.page(page, doctorHypertensionform));
    }


    /**
     * 通过id查询高血压随访
     * @param hypertensionFormId id
     * @return R
     */
    @GetMapping("/{hypertensionFormId}" )
    public R getById(@PathVariable("hypertensionFormId" ) String hypertensionFormId) {
        return new R<>(doctorHypertensionformService.getById(hypertensionFormId));
    }

    /**
     * 新增高血压随访
     * @param doctorHypertensionform 高血压随访
     * @return R
     */
    @SysLog("新增高血压随访" )
    @PostMapping
    @PreAuthorize("@pms.hasPermission('base_doctorhypertensionform_add')" )
    public R save(@Valid @RequestBody DoctorHypertensionformDTO doctorHypertensionform) {
		doctorHypertensionform.clearNoUseDTO();
		return new R<>(doctorHypertensionformService.save(doctorHypertensionform));
    }

    /**
     * 修改高血压随访
     * @param doctorHypertensionform 高血压随访
     * @return R
     */
    @SysLog("修改高血压随访" )
    @PutMapping
    @PreAuthorize("@pms.hasPermission('base_doctorhypertensionform_edit')" )
    public R updateById(@Valid @RequestBody DoctorHypertensionformDTO doctorHypertensionform) {
		doctorHypertensionform.clearNoUseDTO();
		return new R<>(doctorHypertensionformService.updateById(doctorHypertensionform));
    }

    /**
     * 通过id删除高血压随访
     * @param hypertensionFormId id
     * @return R
     */
    @SysLog("删除高血压随访" )
    @DeleteMapping("/{hypertensionFormId}" )
    @PreAuthorize("@pms.hasPermission('base_doctorhypertensionform_del')" )
    public R removeById(@PathVariable String hypertensionFormId) {
        return new R<>(doctorHypertensionformService.removeById(hypertensionFormId));
    }

}
