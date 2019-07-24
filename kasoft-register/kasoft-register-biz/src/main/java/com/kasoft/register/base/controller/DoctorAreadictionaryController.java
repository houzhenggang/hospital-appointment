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
import com.kasoft.register.base.entity.DoctorAreadictionary;
import com.kasoft.register.base.service.DoctorAreadictionaryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.access.prepost.PreAuthorize;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


/**
 * 地区字典
 *
 * @author charlie
 * @date 2019-04-30 17:10:28
 */
@RestController
@AllArgsConstructor
@RequestMapping("/doctorareadictionary" )
@Api(value = "doctorareadictionary", tags = "基础业务-地区")
public class DoctorAreadictionaryController {

    private final  DoctorAreadictionaryService doctorAreadictionaryService;

    /**
     * 分页查询
     * @param page 分页对象
     * @param doctorAreadictionary 地区字典
     * @return
     */
    @GetMapping("/page" )
    public R getDoctorAreadictionaryPage(Page page, DoctorAreadictionary doctorAreadictionary) {
        return new R<>(doctorAreadictionaryService.page(page, Wrappers.query(doctorAreadictionary)));
    }

	/**
	 * 获取全部地区树
	 * @return
	 */
	@ApiOperation(value = "获取全部地区树", notes = "获取全部地区树")
	@GetMapping("/allTree" )
	public R getAllTree() {
		return new R<>(doctorAreadictionaryService.allTree());
	}

	/**
	 * 获取全部省
	 * @return
	 */
	@ApiOperation(value = "获取全部省", notes = "获取全部省")
	@GetMapping("/province" )
	@Cacheable(value = EdConstants.ED_AREA_DETAILS_PROVINCE, unless = "#result == null ")
	public R getProvince() {
		DoctorAreadictionary doctorAreadictionary = new DoctorAreadictionary();
		doctorAreadictionary.setAreaType(EdConstants.AreaType.PROVINCE);
		return new R<>(doctorAreadictionaryService.list(Wrappers.query(doctorAreadictionary)));
	}

	/**
	 * 获取省下的全部市
	 * @param pAreaId 省
	 * @return
	 */
	@ApiOperation(value = "获取省下的全部市", notes = "获取省下的全部市")
	@GetMapping("/city/{pAreaId}" )
	@Cacheable(value = EdConstants.ED_AREA_DETAILS_CITY,  key = "#pAreaId", unless = "#result == null ")
	public R getCity(@PathVariable("pAreaId" ) String pAreaId) {
		DoctorAreadictionary doctorAreadictionary = new DoctorAreadictionary();
		doctorAreadictionary.setAreaType(EdConstants.AreaType.CITY);
		doctorAreadictionary.setParentAreaId(pAreaId);
		return new R<>(doctorAreadictionaryService.list(Wrappers.query(doctorAreadictionary)));
	}

	/**
	 * 获取市下的全部区
	 * @param pAreaId 市
	 * @return
	 */
	@ApiOperation(value = "获取市下的全部区", notes = "获取市下的全部区")
	@GetMapping("/area/{pAreaId}" )
	@Cacheable(value = EdConstants.ED_AREA_DETAILS_AREA,  key = "#pAreaId", unless = "#result == null ")
	public R getArea(@PathVariable("pAreaId" ) String pAreaId) {
		DoctorAreadictionary doctorAreadictionary = new DoctorAreadictionary();
		doctorAreadictionary.setAreaType(EdConstants.AreaType.AREA);
		doctorAreadictionary.setParentAreaId(pAreaId);
		return new R<>(doctorAreadictionaryService.list(Wrappers.query(doctorAreadictionary)));
	}

    /**
     * 通过id查询地区字典
     * @param id id
     * @return R
     */
    @GetMapping("/{id}" )
    public R getById(@PathVariable("id" ) String id) {
        return new R<>(doctorAreadictionaryService.getById(id));
    }

    /**
     * 新增地区字典
     * @param doctorAreadictionary 地区字典
     * @return R
     */
    @SysLog("新增地区字典" )
    @PostMapping
    @PreAuthorize("@pms.hasPermission('base_doctorareadictionary_add')" )
	@CacheEvict(value = {EdConstants.ED_AREA_DETAILS, EdConstants.ED_AREA_DETAILS_PROVINCE,  EdConstants.ED_AREA_DETAILS_CITY,
			EdConstants.ED_AREA_DETAILS_AREA, EdConstants.ED_AREA_DETAILS_ALL}, allEntries = true)
	public R save(@Valid @RequestBody DoctorAreadictionary doctorAreadictionary) {
		doctorAreadictionary.clearNoUseDTO();
    	return new R<>(doctorAreadictionaryService.save(doctorAreadictionary));
    }

    /**
     * 修改地区字典
     * @param doctorAreadictionary 地区字典
     * @return R
     */
    @SysLog("修改地区字典" )
    @PutMapping
    @PreAuthorize("@pms.hasPermission('base_doctorareadictionary_edit')" )
	@CacheEvict(value = {EdConstants.ED_AREA_DETAILS, EdConstants.ED_AREA_DETAILS_PROVINCE,  EdConstants.ED_AREA_DETAILS_CITY,
			EdConstants.ED_AREA_DETAILS_AREA, EdConstants.ED_AREA_DETAILS_ALL}, allEntries = true)
	public R updateById(@Valid @RequestBody DoctorAreadictionary doctorAreadictionary) {
		doctorAreadictionary.clearNoUseDTO();
    	return new R<>(doctorAreadictionaryService.updateById(doctorAreadictionary));
    }

    /**
     * 通过id删除地区字典
     * @param id id
     * @return R
     */
    @SysLog("删除地区字典" )
    @DeleteMapping("/{id}" )
    @PreAuthorize("@pms.hasPermission('base_doctorareadictionary_del')" )
	@CacheEvict(value = {EdConstants.ED_AREA_DETAILS, EdConstants.ED_AREA_DETAILS_PROVINCE,  EdConstants.ED_AREA_DETAILS_CITY,
			EdConstants.ED_AREA_DETAILS_AREA, EdConstants.ED_AREA_DETAILS_ALL}, allEntries = true)
	public R removeById(@PathVariable String id) {
        return new R<>(doctorAreadictionaryService.removeById(id));
    }

}
