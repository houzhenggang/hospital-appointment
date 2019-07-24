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
import com.kasoft.register.base.entity.DoctorDepartmentdictionary;
import com.kasoft.register.base.service.DoctorDepartmentdictionaryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


/**
 * 科室字典
 *
 * @author charlie
 * @date 2019-04-30 17:10:43
 */
@RestController
@AllArgsConstructor
@RequestMapping("/doctordepartmentdictionary" )
@Api(value = "doctordepartmentdictionary", tags = "组织-科室")
public class DoctorDepartmentdictionaryController {

    private final  DoctorDepartmentdictionaryService doctorDepartmentdictionaryService;

    /**
     * 分页查询
     * @param page 分页对象
     * @param doctorDepartmentdictionary 科室字典
     * @return
     */
    @GetMapping("/page" )
    public R getDoctorDepartmentdictionaryPage(Page page, DoctorDepartmentdictionary doctorDepartmentdictionary) {
        return new R<>(doctorDepartmentdictionaryService.page(page, Wrappers.query(doctorDepartmentdictionary)));
    }

	/**
	 * 根据医院编号查询科室字典
	 *  @param hospitalId 医院编号
	 * @return
	 */
	@ApiOperation(value = "根据医院编号查询科室字典", notes = "根据医院编号查询科室字典,备注{{key}}表示查询全部")
	@GetMapping("/dict/{hospitalId}" )
	public R getDepartmentDictByHospital(@PathVariable("hospitalId" )String hospitalId) {
		if(EdConstants.ALL_KEY.equals(hospitalId)) {
			hospitalId = null;
		}
		return new R<>(doctorDepartmentdictionaryService.getDepartmentDictByHospital(hospitalId));
	}

	/**
	 * 查询全部科室字典
	 * @return
	 */
	@ApiOperation(value = "查询全部科室字典", notes = "查询全部科室字典")
	@GetMapping("/dict-all" )
	public R getDepartmentDictAll() {
		return new R<>(doctorDepartmentdictionaryService.getDepartmentDictByHospital(null));
	}



	/**
     * 通过id查询科室字典
     * @param id id
     * @return R
     */
    @GetMapping("/{id}" )
    public R getById(@PathVariable("id" ) String id) {
        return new R<>(doctorDepartmentdictionaryService.getById(id));
    }

    /**
     * 新增科室字典
     * @param doctorDepartmentdictionary 科室字典
     * @return R
     */
    @SysLog("新增科室字典" )
    @PostMapping
    @PreAuthorize("@pms.hasPermission('base_doctordepartment_add')" )
	@CacheEvict(value = EdConstants.ED_DEPARTMENT_DETAILS, allEntries = true)
	public R save(@Valid @RequestBody DoctorDepartmentdictionary doctorDepartmentdictionary) {
		doctorDepartmentdictionary.clearNoUseDTO();
        return new R<>(doctorDepartmentdictionaryService.save(doctorDepartmentdictionary));
    }

    /**
     * 修改科室字典
     * @param doctorDepartmentdictionary 科室字典
     * @return R
     */
    @SysLog("修改科室字典" )
    @PutMapping
    @PreAuthorize("@pms.hasPermission('base_doctordepartment_edit')" )
	@CacheEvict(value = EdConstants.ED_DEPARTMENT_DETAILS, allEntries = true)
	public R updateById(@Valid @RequestBody DoctorDepartmentdictionary doctorDepartmentdictionary) {
		doctorDepartmentdictionary.clearNoUseDTO();
    	return new R<>(doctorDepartmentdictionaryService.updateById(doctorDepartmentdictionary));
    }

    /**
     * 通过id删除科室字典
     * @param id id
     * @return R
     */
    @SysLog("删除科室字典" )
    @DeleteMapping("/{id}" )
    @PreAuthorize("@pms.hasPermission('base_doctordepartment_del')" )
	@CacheEvict(value = EdConstants.ED_DEPARTMENT_DETAILS, allEntries = true)
	public R removeById(@PathVariable String id) {
        return new R<>(doctorDepartmentdictionaryService.removeById(id));
    }

}
