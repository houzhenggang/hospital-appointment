package com.kasoft.register.base.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kasoft.register.base.api.entity.DoctorApplyorder;
import com.kasoft.register.base.service.DoctorApplyorderService;
import com.pig4cloud.pigx.common.core.util.R;
import com.pig4cloud.pigx.common.log.annotation.SysLog;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;


/**
 * 预约订单
 *
 * @author kylin
 * @date 2019-07-27 10:32:12
 */
@RestController
@AllArgsConstructor
@RequestMapping("/doctorapplyorder")
@Api(value = "doctorapplyorder", tags = "预约订单管理")
public class DoctorApplyorderController {

    private final DoctorApplyorderService doctorApplyorderService;

    /**
     * 分页查询
     * @param page 分页对象
     * @param doctorApplyorder 预约订单
     * @return R
     */
    @ApiOperation(value = "分页查询", notes = "分页查询")
    @GetMapping("/page")
    public R getDoctorApplyorderPage(Page page, DoctorApplyorder doctorApplyorder) {
        return R.ok(doctorApplyorderService.page(page, Wrappers.query(doctorApplyorder)));
    }

    /**
     * 通过id查询预约订单
     * @param applyOrderId id
     * @return R
     */
    @ApiOperation(value = "通过id查询", notes = "通过id查询")
    @GetMapping("/{applyOrderId}")
    public R getById(@PathVariable("applyOrderId") Long applyOrderId) {
        return R.ok(doctorApplyorderService.getById(applyOrderId));
    }

    /**
     * 新增预约订单
     * @param doctorApplyorder 预约订单
     * @return R
     */
    @ApiOperation(value = "新增预约订单", notes = "新增预约订单")
    @SysLog("新增预约订单")
    @PostMapping
    @PreAuthorize("@pms.hasPermission('base_doctorapplyorder_add')")
    public R save(@RequestBody DoctorApplyorder doctorApplyorder) {
        return R.ok(doctorApplyorderService.save(doctorApplyorder));
    }

    /**
     * 修改预约订单
     * @param doctorApplyorder 预约订单
     * @return R
     */
    @ApiOperation(value = "修改预约订单", notes = "修改预约订单")
    @SysLog("修改预约订单")
    @PutMapping
    @PreAuthorize("@pms.hasPermission('base_doctorapplyorder_edit')")
    public R updateById(@RequestBody DoctorApplyorder doctorApplyorder) {
        return R.ok(doctorApplyorderService.updateById(doctorApplyorder));
    }

    /**
     * 通过id删除预约订单
     * @param applyOrderId id
     * @return R
     */
    @ApiOperation(value = "通过id删除预约订单", notes = "通过id删除预约订单")
    @SysLog("通过id删除预约订单")
    @DeleteMapping("/{applyOrderId}")
    @PreAuthorize("@pms.hasPermission('base_doctorapplyorder_del')")
    public R removeById(@PathVariable Long applyOrderId) {
        return R.ok(doctorApplyorderService.removeById(applyOrderId));
    }

}
