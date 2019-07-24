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
import com.kasoft.register.base.entity.DoctorServiceteam;
import com.kasoft.register.base.service.DoctorServiceteamService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.access.prepost.PreAuthorize;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


/**
 * 服务团队
 *
 * @author charlie
 * @date 2019-04-30 17:10:16
 */
@RestController
@AllArgsConstructor
@RequestMapping("/doctorserviceteam" )
@Api(value = "doctorserviceteam", tags = "组织-团队")
public class DoctorServiceteamController {

    private final  DoctorServiceteamService doctorServiceteamService;

    /**
     * 分页查询
     * @param page 分页对象
     * @param doctorServiceteam 服务团队
     * @return
     */
    @GetMapping("/page" )
    public R getDoctorServiceteamPage(Page page, DoctorServiceteam doctorServiceteam) {
        return new R<>(doctorServiceteamService.page(page, Wrappers.query(doctorServiceteam)));
    }

	/**
	 * 根据医院编号查询团队字典
	 *  @param hospitalId 医院编号
	 * @return
	 */
	@ApiOperation(value = "根据医院编号查询团队字典", notes = "根据医院编号查询团队字典,备注{{key}}表示查询全部")
	@GetMapping("/dict/{hospitalId}" )
	public R getServiceteamDictByHospital(@PathVariable("hospitalId" )String hospitalId) {
		if(EdConstants.ALL_KEY.equals(hospitalId)) {
			hospitalId = null;
		}
		return new R<>(doctorServiceteamService.getServiceteamDictByHospital(hospitalId));
	}
	/**
	 * 查询全部团队字典
	 * @return
	 */
	@ApiOperation(value = "查询全部团队字典", notes = "查询全部团队字典")
	@GetMapping("/dict-all" )
	public R getServiceteamDictAll() {
		return new R<>(doctorServiceteamService.getServiceteamDictByHospital(null));
	}


    /**
     * 通过id查询服务团队
     * @param id id
     * @return R
     */
    @GetMapping("/{id}" )
    public R getById(@PathVariable("id" ) String id) {
        return new R<>(doctorServiceteamService.getById(id));
    }

    /**
     * 新增服务团队
     * @param doctorServiceteam 服务团队
     * @return R
     */
    @SysLog("新增服务团队" )
    @PostMapping
    @PreAuthorize("@pms.hasPermission('base_doctorserviceteam_add')" )
    public R save(@Valid @RequestBody DoctorServiceteam doctorServiceteam) {
		doctorServiceteam.clearNoUseDTO();
        return new R<>(doctorServiceteamService.save(doctorServiceteam));
    }

    /**
     * 修改服务团队
     * @param doctorServiceteam 服务团队
     * @return R
     */
    @SysLog("修改服务团队" )
    @PutMapping
    @PreAuthorize("@pms.hasPermission('base_doctorserviceteam_edit')" )
    public R updateById(@Valid @RequestBody DoctorServiceteam doctorServiceteam) {
    	doctorServiceteam.clearNoUseDTO();
    	return new R<>(doctorServiceteamService.updateById(doctorServiceteam));
    }

    /**
     * 通过id删除服务团队
     * @param id id
     * @return R
     */
    @SysLog("删除服务团队" )
    @DeleteMapping("/{id}" )
    @PreAuthorize("@pms.hasPermission('base_doctorserviceteam_del')" )
    public R removeById(@PathVariable String id) {
        return new R<>(doctorServiceteamService.removeById(id));
    }

}
