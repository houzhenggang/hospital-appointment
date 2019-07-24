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
import com.kasoft.register.base.dto.DoctorDiabetesformDTO;
import com.pig4cloud.pigx.common.core.util.R;
import com.pig4cloud.pigx.common.log.annotation.SysLog;
import com.kasoft.register.base.entity.DoctorDiabetesform;
import com.kasoft.register.base.service.DoctorDiabetesformService;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


/**
 * 糖尿病随访
 *
 * @author charlie
 * @date 2019-06-08 10:40:08
 */
@RestController
@AllArgsConstructor
@RequestMapping("/doctordiabetesform" )
@Api(value = "doctordiabetesform", tags = "核心-糖尿病随访")
public class DoctorDiabetesformController {

    private final  DoctorDiabetesformService doctorDiabetesformService;

    /**
     * 分页查询
     * @param page 分页对象
     * @param doctorDiabetesform 糖尿病随访
     * @return
     */
    @GetMapping("/page" )
    public R getDoctorDiabetesformPage(Page page, DoctorDiabetesform doctorDiabetesform) {
        return new R<>(doctorDiabetesformService.page(page, doctorDiabetesform));
    }


    /**
     * 通过id查询糖尿病随访
     * @param diabetesFormId id
     * @return R
     */
    @GetMapping("/{diabetesFormId}" )
    public R getById(@PathVariable("diabetesFormId" ) String diabetesFormId) {
        return new R<>(doctorDiabetesformService.getById(diabetesFormId));
    }

    /**
     * 新增糖尿病随访
     * @param doctorDiabetesform 糖尿病随访
     * @return R
     */
    @SysLog("新增糖尿病随访" )
    @PostMapping
    @PreAuthorize("@pms.hasPermission('base_doctordiabetesform_add')" )
    public R save(@Valid @RequestBody DoctorDiabetesformDTO doctorDiabetesform) {
		doctorDiabetesform.clearNoUseDTO();
		return new R<>(doctorDiabetesformService.save(doctorDiabetesform));
    }

    /**
     * 修改糖尿病随访
     * @param doctorDiabetesform 糖尿病随访
     * @return R
     */
    @SysLog("修改糖尿病随访" )
    @PutMapping
    @PreAuthorize("@pms.hasPermission('base_doctordiabetesform_edit')" )
    public R updateById(@Valid @RequestBody DoctorDiabetesformDTO doctorDiabetesform) {
		doctorDiabetesform.clearNoUseDTO();
		return new R<>(doctorDiabetesformService.updateById(doctorDiabetesform));
    }

    /**
     * 通过id删除糖尿病随访
     * @param diabetesFormId id
     * @return R
     */
    @SysLog("删除糖尿病随访" )
    @DeleteMapping("/{diabetesFormId}" )
    @PreAuthorize("@pms.hasPermission('base_doctordiabetesform_del')" )
    public R removeById(@PathVariable String diabetesFormId) {
        return new R<>(doctorDiabetesformService.removeById(diabetesFormId));
    }

}
