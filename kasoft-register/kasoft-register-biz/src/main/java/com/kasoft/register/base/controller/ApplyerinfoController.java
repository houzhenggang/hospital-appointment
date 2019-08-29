package com.kasoft.register.base.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kasoft.register.base.api.entity.Applyerinfo;
import com.kasoft.register.base.service.ApplyerinfoService;
import com.pig4cloud.pigx.common.core.util.R;
import com.pig4cloud.pigx.common.log.annotation.SysLog;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;


/**
 * 预约人信息
 *
 * @author kylin
 * @date 2019-08-14 21:06:00
 */
@RestController
@AllArgsConstructor
@RequestMapping("/applyerinfo")
@Api(value = "applyerinfo", tags = "预约人信息管理")
public class ApplyerinfoController {

    private final ApplyerinfoService applyerinfoService;

    /**
     * 分页查询
     * @param page 分页对象
     * @param applyerinfo 预约人信息
     * @return
     */
    @ApiOperation(value = "分页查询", notes = "分页查询")
    @GetMapping("/page")
    public R getApplyerinfoPage(Page page, Applyerinfo applyerinfo) {
        return R.ok(applyerinfoService.page(page, Wrappers.query(applyerinfo)));
    }


    /**
     * 通过id查询预约人信息
     * @param applyerId id
     * @return R
     */
    @ApiOperation(value = "通过id查询", notes = "通过id查询")
    @GetMapping("/{applyerId}")
    public R getById(@PathVariable("applyerId") String applyerId) {
        return R.ok(applyerinfoService.getById(applyerId));
    }

    /**
     * 新增预约人信息
     * @param applyerinfo 预约人信息
     * @return R
     */
    @ApiOperation(value = "新增预约人信息", notes = "新增预约人信息")
    @SysLog("新增预约人信息")
    @PostMapping
    public R save(@RequestBody Applyerinfo applyerinfo) {
        return R.ok(applyerinfoService.save(applyerinfo));
    }

    /**
     * 修改预约人信息
     * @param applyerinfo 预约人信息
     * @return R
     */
    @ApiOperation(value = "修改预约人信息", notes = "修改预约人信息")
    @SysLog("修改预约人信息")
    @PutMapping
    public R updateById(@RequestBody Applyerinfo applyerinfo) {
        return R.ok(applyerinfoService.updateById(applyerinfo));
    }

    /**
     * 通过id删除预约人信息
     * @param applyerId id
     * @return R
     */
    @ApiOperation(value = "通过id删除预约人信息", notes = "通过id删除预约人信息")
    @SysLog("通过id删除预约人信息")
    @DeleteMapping("/{applyerId}")
    public R removeById(@PathVariable String applyerId) {
        return R.ok(applyerinfoService.removeById(applyerId));
    }

}
