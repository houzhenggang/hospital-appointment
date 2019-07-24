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
import com.kasoft.register.base.dto.DoctorProjectlibraryDTO;
import com.kasoft.register.base.dto.ProjectlibraryTree;
import com.pig4cloud.pigx.common.core.util.R;
import com.pig4cloud.pigx.common.log.annotation.SysLog;
import com.kasoft.register.base.entity.DoctorProjectlibrary;
import com.kasoft.register.base.service.DoctorProjectlibraryService;
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
 * 项目库
 *
 * @author charlie
 * @date 2019-05-07 15:21:57
 */
@RestController
@AllArgsConstructor
@RequestMapping("/doctorprojectlibrary" )
@Api(value = "doctorprojectlibrary", tags = "组织-项目库")
public class DoctorProjectlibraryController {

    private final  DoctorProjectlibraryService doctorProjectlibraryService;

    /**
     * 分页查询
     * @param page 分页对象
     * @param doctorProjectlibraryDTO 项目库
     * @return
     */
    @GetMapping("/page" )
    public R getDoctorProjectlibraryPage(Page page, DoctorProjectlibraryDTO doctorProjectlibraryDTO) {
        return new R<>(doctorProjectlibraryService.page(page, doctorProjectlibraryDTO));
    }


    /**
     * 通过id查询项目库
     * @param id id
     * @return R
     */
    @GetMapping("/{id}" )
    public R getById(@PathVariable("id" ) String id) {
        return new R<>(doctorProjectlibraryService.getById(id));
    }

    /**
     * 新增项目库
     * @param doctorProjectlibrary 项目库
     * @return R
     */
    @SysLog("新增项目库" )
    @PostMapping
    @PreAuthorize("@pms.hasPermission('base_doctorprojectlibrary_add')" )
    public R save(@Valid @RequestBody DoctorProjectlibrary doctorProjectlibrary) {
		doctorProjectlibrary.clearNoUseDTO();
        return new R<>(doctorProjectlibraryService.save(doctorProjectlibrary));
    }

    /**
     * 修改项目库
     * @param doctorProjectlibrary 项目库
     * @return R
     */
    @SysLog("修改项目库" )
    @PutMapping
    @PreAuthorize("@pms.hasPermission('base_doctorprojectlibrary_edit')" )
    public R updateById(@Valid @RequestBody DoctorProjectlibrary doctorProjectlibrary) {
		doctorProjectlibrary.clearNoUseDTO();
    	return new R<>(doctorProjectlibraryService.updateById(doctorProjectlibrary));
    }

    /**
     * 通过id删除项目库
     * @param id id
     * @return R
     */
    @SysLog("删除项目库" )
    @DeleteMapping("/{id}" )
    @PreAuthorize("@pms.hasPermission('base_doctorprojectlibrary_del')" )
    public R removeById(@PathVariable String id) {
        return new R<>(doctorProjectlibraryService.removeById(id));
    }

	/**
	 * 返回项目库的树集合
	 *
	 * @param hospitalId 医院编号
	 * @param type 类型 为空是查询全部树，1是查非叶子节点
	 * @return 属性集合
	 */
	@ApiOperation(value = "获取项目库的树集合", notes = "获取项目库的树集合type:空全部 1非叶子", response = ProjectlibraryTree.class)
	@ApiImplicitParams({
			@ApiImplicitParam(name = "hospitalId", value = "医院编号", required = true),
			@ApiImplicitParam(name = "type", value = "类型")})
	@GetMapping("/tree/{hospitalId}")
	public List<ProjectlibraryTree> getProjectlibraryTree(@PathVariable String hospitalId, @RequestParam(value = "type", required = false)  String type) {
		return doctorProjectlibraryService.getProjectlibraryTree(hospitalId, type);
	}

}
