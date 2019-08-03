package com.kasoft.register.base.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kasoft.register.base.api.entity.Inspectionprice;
import com.pig4cloud.pigx.common.core.util.R;
import com.pig4cloud.pigx.common.log.annotation.SysLog;
import com.kasoft.register.base.service.InspectionpriceService;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;


/**
 * 检查价格
 *
 * @author kylin
 * @date 2019-08-03 14:30:41
 */
@RestController
@AllArgsConstructor
@RequestMapping("/inspectionprice")
@Api(value = "inspectionprice", tags = "检查价格管理")
public class InspectionpriceController {

    private final  InspectionpriceService inspectionpriceService;

    /**
     * 分页查询
     * @param page 分页对象
     * @param inspectionprice 检查价格
     * @return
     */
    @ApiOperation(value = "分页查询", notes = "分页查询")
    @GetMapping("/page")
    public R getInspectionpricePage(Page page, Inspectionprice inspectionprice) {
        return R.ok(inspectionpriceService.page(page, Wrappers.query(inspectionprice)));
    }


    /**
     * 通过id查询检查价格
     * @param inspPriceId id
     * @return R
     */
    @ApiOperation(value = "通过id查询", notes = "通过id查询")
    @GetMapping("/{inspPriceId}")
    public R getById(@PathVariable("inspPriceId") String inspPriceId) {
        return R.ok(inspectionpriceService.getById(inspPriceId));
    }

    /**
     * 新增检查价格
     * @param inspectionprice 检查价格
     * @return R
     */
    @ApiOperation(value = "新增检查价格", notes = "新增检查价格")
    @SysLog("新增检查价格")
    @PostMapping
    @PreAuthorize("@pms.hasPermission('base_inspectionprice_add')")
    public R save(@RequestBody Inspectionprice inspectionprice) {
        return R.ok(inspectionpriceService.save(inspectionprice));
    }

    /**
     * 修改检查价格
     * @param inspectionprice 检查价格
     * @return R
     */
    @ApiOperation(value = "修改检查价格", notes = "修改检查价格")
    @SysLog("修改检查价格")
    @PutMapping
    @PreAuthorize("@pms.hasPermission('base_inspectionprice_edit')")
    public R updateById(@RequestBody Inspectionprice inspectionprice) {
        return R.ok(inspectionpriceService.updateById(inspectionprice));
    }

    /**
     * 通过id删除检查价格
     * @param inspPriceId id
     * @return R
     */
    @ApiOperation(value = "通过id删除检查价格", notes = "通过id删除检查价格")
    @SysLog("通过id删除检查价格")
    @DeleteMapping("/{inspPriceId}")
    @PreAuthorize("@pms.hasPermission('base_inspectionprice_del')")
    public R removeById(@PathVariable String inspPriceId) {
        return R.ok(inspectionpriceService.removeById(inspPriceId));
    }

}
