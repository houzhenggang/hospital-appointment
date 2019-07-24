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
import com.kasoft.register.base.dto.DoctorServicepackageDTO;
import com.kasoft.register.base.dto.ServicepackageTree;
import com.pig4cloud.pigx.common.core.util.R;
import com.pig4cloud.pigx.common.log.annotation.SysLog;
import com.kasoft.register.base.entity.DoctorServicepackage;
import com.kasoft.register.base.service.DoctorServicepackageService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


/**
 * 服务套餐包
 *
 * @author charlie
 * @date 2019-05-07 15:56:24
 */
@RestController
@AllArgsConstructor
@RequestMapping("/doctorservicepackage" )
@Api(value = "doctorservicepackage", tags = "组织-套餐")
public class DoctorServicepackageController {

    private final  DoctorServicepackageService doctorServicepackageService;

    /**
     * 分页查询 带复杂搜索实现
     * @param page 分页对象
     * @param doctorServicepackageDTO 服务套餐包
     * @return
     */
    @GetMapping("/page" )
    public R getDoctorServicepackagePage(Page page, DoctorServicepackageDTO doctorServicepackageDTO) {
        return new R<>(doctorServicepackageService.page(page, doctorServicepackageDTO));
    }


    /**
     * 通过id查询服务套餐包
     * @param id id
     * @return R
     */
    @GetMapping("/{id}" )
    public R getById(@PathVariable("id" ) String id) {
        return new R<>(doctorServicepackageService.getById(id));
    }

    /**
     * 新增服务套餐包
     * @param doctorServicepackage 服务套餐包
     * @return R
     */
    @SysLog("新增服务套餐包" )
    @PostMapping
    @PreAuthorize("@pms.hasPermission('base_doctorservicepackage_add')" )
    public R save(@Valid @RequestBody DoctorServicepackage doctorServicepackage) {
		doctorServicepackage.clearNoUseDTO();
    	return new R<>(doctorServicepackageService.save(doctorServicepackage));
    }

    /**
	 * 新增和修改套餐内容
	 * @param doctorServicepackageDTO 服务包DTO
	 * @return R
	 */
	@ApiOperation(value = "新增和修改套餐内容", notes = "新增和修改套餐内容")
	@SysLog("新增和修改套餐内容" )
	@PostMapping("/saveRemoveItemList" )
	public R saveRemoveItemList(@Valid @RequestBody DoctorServicepackageDTO doctorServicepackageDTO) {
		doctorServicepackageDTO.clearNoUseDTO();
		return new R<>(doctorServicepackageService.saveRemoveItemList(doctorServicepackageDTO));
	}

    /**
     * 修改服务套餐包
     * @param doctorServicepackage 服务套餐包
     * @return R
     */
    @SysLog("修改服务套餐包" )
    @PutMapping
    @PreAuthorize("@pms.hasPermission('base_doctorservicepackage_edit')" )
    public R updateById(@Valid @RequestBody DoctorServicepackage doctorServicepackage) {
		doctorServicepackage.clearNoUseDTO();
        return new R<>(doctorServicepackageService.updateById(doctorServicepackage));
    }

    /**
     * 通过id删除服务套餐包
     * @param id id
     * @return R
     */
    @SysLog("删除服务套餐包" )
    @DeleteMapping("/{id}" )
    @PreAuthorize("@pms.hasPermission('base_doctorservicepackage_del')" )
    public R removeById(@PathVariable String id) {
        return new R<>(doctorServicepackageService.removeById(id));
    }

	/**
	 * 返回套餐的树集合
	 *
	 * @param hospitalId 医院编号
	 * @param type 类型 为空是查询全部树，1是查非叶子节点，
	 * @param serviceType 服务类型1基础或2个性化
	 * @return 属性集合
	 */
	@ApiOperation(value = "获取套餐的树集合", notes = "获取套餐的树集合type:空全部 1非叶子，服务类型1基础或2个性化 ", response = ServicepackageTree.class)
	@ApiImplicitParams({
			@ApiImplicitParam(name = "hospitalId", value = "医院编号", required = true),
			@ApiImplicitParam(name = "type", value = "类型"),
	        @ApiImplicitParam(name = "serviceType", value = "服务类型")})
    @GetMapping("/tree/{hospitalId}")
	public List<ServicepackageTree> getServicepackageTree(@PathVariable String hospitalId,
														  @RequestParam(value = "type", required = false)  String type,
														  @RequestParam(value = "serviceType", required = false)  String serviceType) {
		return doctorServicepackageService.getServicepackageTree(hospitalId, type, serviceType);
	}

}
