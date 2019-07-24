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
import com.kasoft.register.base.entity.DoctorStreetdictionary;
import com.kasoft.register.base.service.DoctorStreetdictionaryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.access.prepost.PreAuthorize;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


/**
 * 街道居委会
 *
 * @author charlie
 * @date 2019-04-30 17:10:33
 */
@RestController
@AllArgsConstructor
@RequestMapping("/doctorstreetdictionary" )
@Api(value = "doctorstreetdictionary", tags = "基础业务-街道居委会")
public class DoctorStreetdictionaryController {

    private final  DoctorStreetdictionaryService doctorStreetdictionaryService;

    /**
     * 分页查询
     * @param page 分页对象
     * @param doctorStreetdictionary 街道居委会
     * @return
     */
    @GetMapping("/page" )
    public R getDoctorStreetdictionaryPage(Page page, DoctorStreetdictionary doctorStreetdictionary) {
        return new R<>(doctorStreetdictionaryService.page(page, Wrappers.query(doctorStreetdictionary)));
    }

	/**
	 * 获取区下面全部街道
	 * @param pAreaId 区
	 * @return
	 */
	@ApiOperation(value = "获取区下面全部街道", notes = "获取区下面全部街道")
	@GetMapping("/street/{pAreaId}" )
	@Cacheable(value = EdConstants.ED_STREET_DETAILS_STREET,  key = "#pAreaId", unless = "#result == null ")
	public R getStreet(@PathVariable("pAreaId" ) String pAreaId) {
		DoctorStreetdictionary doctorStreetdictionary = new DoctorStreetdictionary();
		doctorStreetdictionary.setStreetType(EdConstants.StreetType.STREET);
		doctorStreetdictionary.setParentId(pAreaId);
		return new R<>(doctorStreetdictionaryService.list(Wrappers.query(doctorStreetdictionary)));
	}

	/**
	 * 获取街道下的全部居委会
	 * @param pAreaId 市
	 * @return
	 */
	@ApiOperation(value = "获取街道下的全部居委会", notes = "获取街道下的全部居委会")
	@GetMapping("/neighborhood/{pAreaId}" )
	@Cacheable(value = EdConstants.ED_STREET_DETAILS_NEIGHBORHOOD,  key = "#pAreaId", unless = "#result == null ")
	public R getNeighborhood(@PathVariable("pAreaId" ) String pAreaId) {
		DoctorStreetdictionary doctorStreetdictionary = new DoctorStreetdictionary();
		doctorStreetdictionary.setStreetType(EdConstants.StreetType.NEIGHBORHOOD);
		doctorStreetdictionary.setParentId(pAreaId);
		return new R<>(doctorStreetdictionaryService.list(Wrappers.query(doctorStreetdictionary)));
	}


    /**
     * 通过id查询街道居委会
     * @param id id
     * @return R
     */
    @GetMapping("/{id}" )
    public R getById(@PathVariable("id" ) String id) {
        return new R<>(doctorStreetdictionaryService.getById(id));
    }

    /**
     * 新增街道居委会
     * @param doctorStreetdictionary 街道居委会
     * @return R
     */
    @SysLog("新增街道居委会" )
    @PostMapping
    @PreAuthorize("@pms.hasPermission('base_doctorstreetdictionary_add')" )
	@CacheEvict(value = {EdConstants.ED_STREET_DETAILS, EdConstants.ED_STREET_DETAILS_STREET,
			EdConstants.ED_STREET_DETAILS_NEIGHBORHOOD}, allEntries = true)
	public R save(@Valid @RequestBody DoctorStreetdictionary doctorStreetdictionary) {
		doctorStreetdictionary.clearNoUseDTO();
    	return new R<>(doctorStreetdictionaryService.save(doctorStreetdictionary));
    }

    /**
     * 修改街道居委会
     * @param doctorStreetdictionary 街道居委会
     * @return R
     */
    @SysLog("修改街道居委会" )
    @PutMapping
    @PreAuthorize("@pms.hasPermission('base_doctorstreetdictionary_edit')" )
	@CacheEvict(value = {EdConstants.ED_STREET_DETAILS, EdConstants.ED_STREET_DETAILS_STREET,
			EdConstants.ED_STREET_DETAILS_NEIGHBORHOOD}, allEntries = true)
	public R updateById(@Valid @RequestBody DoctorStreetdictionary doctorStreetdictionary) {
		doctorStreetdictionary.clearNoUseDTO();
    	return new R<>(doctorStreetdictionaryService.updateById(doctorStreetdictionary));
    }

    /**
     * 通过id删除街道居委会
     * @param id id
     * @return R
     */
    @SysLog("删除街道居委会" )
    @DeleteMapping("/{id}" )
    @PreAuthorize("@pms.hasPermission('base_doctorstreetdictionary_del')" )
	@CacheEvict(value = {EdConstants.ED_STREET_DETAILS, EdConstants.ED_STREET_DETAILS_STREET,
			EdConstants.ED_STREET_DETAILS_NEIGHBORHOOD}, allEntries = true)
	public R removeById(@PathVariable String id) {
        return new R<>(doctorStreetdictionaryService.removeById(id));
    }

}
