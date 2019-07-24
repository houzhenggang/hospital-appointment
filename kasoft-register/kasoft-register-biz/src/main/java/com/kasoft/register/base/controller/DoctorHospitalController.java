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
import com.kasoft.register.base.utils.EdConstants;
import com.pig4cloud.pigx.common.core.util.R;
import com.pig4cloud.pigx.common.log.annotation.SysLog;
import com.kasoft.register.base.entity.DoctorHospital;
import com.kasoft.register.base.service.DoctorHospitalService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


/**
 * 医院
 *
 * @author charlie
 * @date 2019-04-30 17:09:57
 */
@RestController
@AllArgsConstructor
@RequestMapping("/doctorhospital" )
@Api(value = "doctordoctorinfoteam", tags = "组织-医院")
public class DoctorHospitalController {

    private final  DoctorHospitalService doctorHospitalService;

    /**
     * 分页查询
     * @param page 分页对象
     * @param doctorHospital 医院
     * @return
     */
    @GetMapping("/page" )
    public R getDoctorHospitalPage(Page page, DoctorHospital doctorHospital) {
        return new R<>(doctorHospitalService.page(page, Wrappers.query(doctorHospital)));
    }

	/**
	 * 查询医院字典
	 * @return
	 */
	@ApiOperation(value = "查询医院字典", notes = "查询医院字典")
	@GetMapping("/dict" )
	@Cacheable(value = EdConstants.ED_HOSPITAL_DETAILS_DICT, unless = "#result == null ")
	public R getHospitalDict() {
		return new R<>(doctorHospitalService.getHospitalByName(null));
	}


    /**
     * 通过id查询医院
     * @param id id
     * @return R
     */
    @GetMapping("/{id}" )
    public R getById(@PathVariable("id" ) String id) {
        return new R<>(doctorHospitalService.getById(id));
    }

    /**
     * 新增医院
     * @param doctorHospital 医院
     * @return R
     */
    @SysLog("新增医院" )
    @PostMapping
    @PreAuthorize("@pms.hasPermission('base_doctorhospital_add')" )
	@CacheEvict(value = {EdConstants.ED_HOSPITAL_DETAILS, EdConstants.ED_HOSPITAL_DETAILS_DICT}, allEntries = true)
	public R save(@Valid @RequestBody DoctorHospital doctorHospital) {
		doctorHospital.clearNoUseDTO();
		return new R<>(doctorHospitalService.save(doctorHospital));
    }

    /**
     * 修改医院
     * @param doctorHospital 医院
     * @return R
     */
    @SysLog("修改医院" )
    @PutMapping
    @PreAuthorize("@pms.hasPermission('base_doctorhospital_edit')" )
	@CacheEvict(value = {EdConstants.ED_HOSPITAL_DETAILS, EdConstants.ED_HOSPITAL_DETAILS_DICT}, allEntries = true)
	public R updateById(@Valid @RequestBody DoctorHospital doctorHospital) {
		doctorHospital.clearNoUseDTO();
        return new R<>(doctorHospitalService.updateById(doctorHospital));
    }

    /**
     * 通过id删除医院
     * @param id id
     * @return R
     */
    @SysLog("删除医院" )
    @DeleteMapping("/{id}" )
    @PreAuthorize("@pms.hasPermission('base_doctorhospital_del')" )
	@CacheEvict(value = {EdConstants.ED_HOSPITAL_DETAILS, EdConstants.ED_HOSPITAL_DETAILS_DICT}, allEntries = true)
	public R removeById(@PathVariable String id) {
        return new R<>(doctorHospitalService.removeById(id));
    }

}
