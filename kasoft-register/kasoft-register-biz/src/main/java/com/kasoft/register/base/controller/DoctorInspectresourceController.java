package com.kasoft.register.base.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kasoft.register.base.entity.DoctorInspectresource;
import com.kasoft.register.base.service.DoctorInspectresourceService;
import com.pig4cloud.pigx.common.core.util.R;
import com.pig4cloud.pigx.common.log.annotation.SysLog;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;


/**
 * 检查资源
 *
 * @author kylin
 * @date 2019-07-27 10:32:30
 */
@RestController
@AllArgsConstructor
@RequestMapping("/doctorinspectresource")
@Api(value = "doctorinspectresource", tags = "检查资源管理")
public class DoctorInspectresourceController {

    private final DoctorInspectresourceService doctorInspectresourceService;

    /**
     * 分页查询
     * @param page 分页对象
     * @param doctorInspectresource 检查资源
     * @return
     */
    @ApiOperation(value = "分页查询", notes = "分页查询")
    @GetMapping("/page")
    public R getDoctorInspectresourcePage(Page page, DoctorInspectresource doctorInspectresource) {
        return R.ok(doctorInspectresourceService.page(page, Wrappers.query(doctorInspectresource)));
    }


    /**
     * 通过id查询检查资源
     * @param inspResourceId id
     * @return R
     */
    @ApiOperation(value = "通过id查询", notes = "通过id查询")
    @GetMapping("/{inspResourceId}")
    public R getById(@PathVariable("inspResourceId") Long inspResourceId) {
        return R.ok(doctorInspectresourceService.getById(inspResourceId));
    }

    /**
     * 新增检查资源
     * @param doctorInspectresource 检查资源
     * @return R
     */
    @ApiOperation(value = "新增检查资源", notes = "新增检查资源")
    @SysLog("新增检查资源")
    @PostMapping
    @PreAuthorize("@pms.hasPermission('generator_doctorinspectresource_add')")
    public R save(@RequestBody DoctorInspectresource doctorInspectresource) {
        return R.ok(doctorInspectresourceService.save(doctorInspectresource));
    }

    /**
     * 修改检查资源
     * @param doctorInspectresource 检查资源
     * @return R
     */
    @ApiOperation(value = "修改检查资源", notes = "修改检查资源")
    @SysLog("修改检查资源")
    @PutMapping
    @PreAuthorize("@pms.hasPermission('generator_doctorinspectresource_edit')")
    public R updateById(@RequestBody DoctorInspectresource doctorInspectresource) {
        return R.ok(doctorInspectresourceService.updateById(doctorInspectresource));
    }

    /**
     * 通过id删除检查资源
     * @param inspResourceId id
     * @return R
     */
    @ApiOperation(value = "通过id删除检查资源", notes = "通过id删除检查资源")
    @SysLog("通过id删除检查资源")
    @DeleteMapping("/{inspResourceId}")
    @PreAuthorize("@pms.hasPermission('generator_doctorinspectresource_del')")
    public R removeById(@PathVariable Long inspResourceId) {
        return R.ok(doctorInspectresourceService.removeById(inspResourceId));
    }

}
