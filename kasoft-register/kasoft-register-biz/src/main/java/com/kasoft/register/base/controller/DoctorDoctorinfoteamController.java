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

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pig4cloud.pigx.common.core.util.R;
import com.pig4cloud.pigx.common.log.annotation.SysLog;
import com.kasoft.register.base.entity.DoctorDoctorinfoteam;
import com.kasoft.register.base.service.DoctorDoctorinfoteamService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.access.prepost.PreAuthorize;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;


/**
 * 医生团队表
 *
 * @author charlie
 * @date 2019-04-30 17:10:54
 */
@RestController
@AllArgsConstructor
@RequestMapping("/doctordoctorinfoteam" )
@Api(value = "doctordoctorinfoteam", tags = "组织-医生团队关联")
public class DoctorDoctorinfoteamController {

    private final  DoctorDoctorinfoteamService doctorDoctorinfoteamService;

    /**
     * 分页查询
     * @param page 分页对象
     * @param doctorDoctorinfoteam 医生团队表
     * @return
     */
    @GetMapping("/page" )
    public R getDoctorDoctorinfoteamPage(Page page, DoctorDoctorinfoteam doctorDoctorinfoteam) {
        return new R<>(doctorDoctorinfoteamService.page(page, Wrappers.query(doctorDoctorinfoteam)));
    }


    /**
     * 通过id查询医生团队表
     * @param id id
     * @return R
     */
    @GetMapping("/{id}" )
    public R getById(@PathVariable("id" ) String id) {
        return new R<>(doctorDoctorinfoteamService.getById(id));
    }

    /**
     * 新增医生团队表
     * @param doctorDoctorinfoteam 医生团队表
     * @return R
     */
    @SysLog("新增医生团队表" )
    @PostMapping
    @PreAuthorize("@pms.hasPermission('base_doctordoctorinfoteam_add')" )
    public R save(@Valid @RequestBody DoctorDoctorinfoteam doctorDoctorinfoteam) {
		doctorDoctorinfoteam.clearNoUseDTO();
        return new R<>(doctorDoctorinfoteamService.save(doctorDoctorinfoteam));
    }

	/**
	 * 新增医生团队列表表
	 * @param doctorDoctorinfoteamList 医生团队表
	 * @return R
	 */
	@SysLog("新增医生团队表" )
	@PostMapping("/saveList" )
	@Deprecated
	public R saveList(@Valid @RequestBody List<DoctorDoctorinfoteam> doctorDoctorinfoteamList) {
		boolean flag = true;
		doctorDoctorinfoteamList.forEach(item -> {
			item.clearNoUseDTO();
			boolean ret = doctorDoctorinfoteamService.save(item);
		});
		return new R<>(flag);
	}

	/**
	 * 新增医生团队列表表
	 * @param teamId 团队编号
	 * @param doctorIdList 医生编号列表
	 * @return R
	 */
	@ApiOperation(value = "新增某团队医生列表并删除旧的关联", notes = "新增某团队医生列表并删除旧的关联")
	@SysLog("新增某团队医生列表并删除旧的关联" )
	@PostMapping("/saveRemoveList/{teamId}" )
	public R saveRemoveList(@PathVariable("teamId" ) String teamId, @Valid @RequestBody List<String> doctorIdList) {
		if(CollUtil.isEmpty(doctorIdList)) {
			doctorIdList = new ArrayList<>();
		}
		return new R<>(doctorDoctorinfoteamService.saveRemoveList(teamId, doctorIdList));
	}

    /**
     * 修改医生团队表
     * @param doctorDoctorinfoteam 医生团队表
     * @return R
     */
    @SysLog("修改医生团队表" )
    @PutMapping
    @PreAuthorize("@pms.hasPermission('base_doctordoctorinfoteam_edit')" )
    public R updateById(@Valid @RequestBody DoctorDoctorinfoteam doctorDoctorinfoteam) {
		doctorDoctorinfoteam.clearNoUseDTO();
        return new R<>(doctorDoctorinfoteamService.updateById(doctorDoctorinfoteam));
    }

    /**
     * 通过id删除医生团队表
     * @param id id
     * @return R
     */
    @SysLog("删除医生团队表" )
    @DeleteMapping("/{id}" )
    @PreAuthorize("@pms.hasPermission('base_doctordoctorinfoteam_del')" )
    public R removeById(@PathVariable String id) {
        return new R<>(doctorDoctorinfoteamService.removeById(id));
    }

	/**
	 * 通过id删除医生团队表
	 * @param idList id列表
	 * @return R
	 */
	@SysLog("删除医生团队表列表" )
	@DeleteMapping("/removeByIdList" )
	@Deprecated
	public R removeByIdList( @RequestBody List<String> idList) {
		boolean flag = true;
		idList.forEach(item -> {
			doctorDoctorinfoteamService.removeById(item);
		});
		return new R<>(flag);
	}

}
