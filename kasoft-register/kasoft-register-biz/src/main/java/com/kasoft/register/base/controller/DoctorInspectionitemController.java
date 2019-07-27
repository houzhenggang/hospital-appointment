package com.kasoft.register.base.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kasoft.register.base.api.entity.DoctorInspectionitem;
import com.kasoft.register.base.service.DoctorInspectionitemService;
import com.kasoft.register.base.utils.KrbConstants;
import com.pig4cloud.pigx.common.core.util.R;
import com.pig4cloud.pigx.common.log.annotation.SysLog;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


/**
 * 检查项目
 *
 * @author kylin
 * @date 2019-07-26 21:33:19
 */
@RestController
@AllArgsConstructor
@RequestMapping("/doctorinspectionitem" )
@Api(value = "doctorinspectionitem", tags = "基础-检查项目")
public class DoctorInspectionitemController {

    private final DoctorInspectionitemService doctorInspectionitemService;

    /**
     * 分页查询
     * @param page 分页对象
     * @param doctorInspectionitem 检查项目
     * @return R
     */
    @ApiOperation(value = "分页查询", notes = "分页查询")
    @GetMapping("/page" )
    public R getDoctorInspectionitemPage(Page page, DoctorInspectionitem doctorInspectionitem) {
        return R.ok(doctorInspectionitemService.page(page, Wrappers.query(doctorInspectionitem)));
    }

	/**
	 * 查询检查项目字典
	 * @return R
	 */
	@ApiOperation(value = "查询检查项目字典", notes = "查询检查项目字典")
	@GetMapping("/dict")
	@Cacheable(value = KrbConstants.ED_INSPECTION_ITEM_DICT, unless = "#result == null")
	public R getInspectionitemDict() {
		return R.ok(doctorInspectionitemService.list(new QueryWrapper<>()));
	}

	/**
	 * 根据检查类别查询检查项目
	 * @param inspType 检查类别
	 * @return R
	 */
	@ApiOperation(value = "根据检查类别查询检查项目", notes = "根据检查类别查询检查项目")
	@GetMapping("/item/{inspType}")
	@Cacheable(value = KrbConstants.ED_INSPECTION_ITEM_DETAIL,  key = "#inspType", unless = "#result == null")
	public R getCity(@PathVariable("inspType" ) String inspType) {
		DoctorInspectionitem doctorInspectionitem = new DoctorInspectionitem();
		doctorInspectionitem.setInspItemType(inspType);
		return R.ok(doctorInspectionitemService.list(Wrappers.query(doctorInspectionitem)),"查询成功!");
	}

    /**
     * 通过id查询检查项目
     * @param inspItemId id
     * @return R
     */
    @ApiOperation(value = "通过id查询", notes = "通过id查询")
    @GetMapping("/{inspItemId}" )
    public R getById(@PathVariable("inspItemId" ) Long inspItemId) {
        return R.ok(doctorInspectionitemService.getById(inspItemId));
    }

    /**
     * 新增检查项目
     * @param doctorInspectionitem 检查项目
     * @return R
     */
    @ApiOperation(value = "新增检查项目", notes = "新增检查项目")
    @SysLog("新增检查项目" )
    @PostMapping
    @PreAuthorize("@pms.hasPermission('base_doctorinspectionitem_add')" )
	@CacheEvict(value = {KrbConstants.ED_INSPECTION_ITEM_DETAIL, KrbConstants.ED_INSPECTION_ITEM_DICT}, allEntries = true)
    public R save(@RequestBody DoctorInspectionitem doctorInspectionitem) {
        return R.ok(doctorInspectionitemService.save(doctorInspectionitem));
    }

    /**
     * 修改检查项目
     * @param doctorInspectionitem 检查项目
     * @return R
     */
    @ApiOperation(value = "修改检查项目", notes = "修改检查项目")
    @SysLog("修改检查项目" )
    @PutMapping
    @PreAuthorize("@pms.hasPermission('base_doctorinspectionitem_edit')")
	@CacheEvict(value = {KrbConstants.ED_INSPECTION_ITEM_DETAIL, KrbConstants.ED_INSPECTION_ITEM_DICT}, allEntries = true)
	public R updateById(@RequestBody DoctorInspectionitem doctorInspectionitem) {
        return R.ok(doctorInspectionitemService.updateById(doctorInspectionitem));
    }

    /**
     * 通过id删除检查项目
     * @param inspItemId id
     * @return R
     */
    @ApiOperation(value = "通过id删除检查项目", notes = "通过id删除检查项目")
    @SysLog("通过id删除检查项目" )
    @DeleteMapping("/{inspItemId}")
    @PreAuthorize("@pms.hasPermission('base_doctorinspectionitem_del')" )
	@CacheEvict(value = {KrbConstants.ED_INSPECTION_ITEM_DETAIL, KrbConstants.ED_INSPECTION_ITEM_DICT}, allEntries = true)
	public R removeById(@PathVariable Long inspItemId) {
        return R.ok(doctorInspectionitemService.removeById(inspItemId));
    }

}
