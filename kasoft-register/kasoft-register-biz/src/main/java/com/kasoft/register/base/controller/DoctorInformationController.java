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
import com.kasoft.register.base.entity.DoctorInformation;
import com.kasoft.register.base.service.DoctorInformationService;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


/**
 * 资讯
 *
 * @author charlie
 * @date 2019-05-26 14:11:18
 */
@RestController
@AllArgsConstructor
@RequestMapping("/doctorinformation" )
@Api(value = "doctorinformation", tags = "组织-资讯")
public class DoctorInformationController {

    private final  DoctorInformationService doctorInformationService;

    /**
     * 分页查询
     * @param page 分页对象
     * @param doctorInformation 资讯
     * @return
     */
    @GetMapping("/page" )
    public R getDoctorInformationPage(Page page, DoctorInformation doctorInformation) {
    	return new R<>(doctorInformationService.page(page, Wrappers.query(doctorInformation)));
    }


    /**
     * 通过id查询资讯
     * @param id id
     * @return R
     */
    @GetMapping("/{id}" )
    public R getById(@PathVariable("id" ) String id) {
        return new R<>(doctorInformationService.getById(id));
    }

    /**
     * 新增资讯
     * @param doctorInformation 资讯
     * @return R
     */
    @SysLog("新增资讯" )
    @PostMapping
    @PreAuthorize("@pms.hasPermission('base_doctorinformation_add')" )
    public R save(@RequestBody DoctorInformation doctorInformation) {
		doctorInformation.clearNoUseDTO();
    	return new R<>(doctorInformationService.save(doctorInformation));
    }

    /**
     * 修改资讯
     * @param doctorInformation 资讯
     * @return R
     */
    @SysLog("修改资讯" )
    @PutMapping
    @PreAuthorize("@pms.hasPermission('base_doctorinformation_edit')" )
    public R updateById(@RequestBody DoctorInformation doctorInformation) {
		doctorInformation.clearNoUseDTO();
        return new R<>(doctorInformationService.updateById(doctorInformation));
    }

    /**
     * 通过id删除资讯
     * @param id id
     * @return R
     */
    @SysLog("删除资讯" )
    @DeleteMapping("/{id}" )
    @PreAuthorize("@pms.hasPermission('base_doctorinformation_del')" )
    public R removeById(@PathVariable String id) {
        return new R<>(doctorInformationService.removeById(id));
    }

}
