package com.kasoft.register.base.controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kasoft.register.base.api.entity.Inspectionprice;
import com.kasoft.register.base.service.InspectionpriceService;
import com.kasoft.register.base.utils.KrbConstants;
import com.pig4cloud.pigx.common.core.constant.ReturnMsgConstants;
import com.pig4cloud.pigx.common.core.util.R;
import com.pig4cloud.pigx.common.log.annotation.SysLog;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.access.prepost.PreAuthorize;
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
        return R.ok(inspectionpriceService.page(page, Wrappers.query(inspectionprice).orderByDesc("create_time")));
    }

	/**
	 * 查询价格字典
	 * @return R
	 */
	@ApiOperation(value = "查询价格字典", notes = "查询价格字典")
	@GetMapping("/dict" )
	@Cacheable(value = KrbConstants.ED_HOSPITAL_DETAILS_DICT, unless = "#result == null ")
	public R getPriceDict() {
		return R.ok(inspectionpriceService.list());
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
	 * 通过机构和检查项目查询检查价格
	 * @param hospitalId 机构
	 * @param inspItemId 检查项目
	 * @return R
	 */
	@ApiOperation(value = "通过机构和检查项目查询检查价格", notes = "通过机构和检查项目查询检查价格")
	@GetMapping("/get/price/by/hospital/and/item")
	public R getPricetByHospitalAndItem(@RequestParam String hospitalId,@RequestParam String inspItemId) {
		return R.ok(inspectionpriceService.getOne(new QueryWrapper<Inspectionprice>()
			.eq("hospital_id", hospitalId)
			.eq("insp_item_id", inspItemId)
		), ReturnMsgConstants.QUERY_SUCCESS);
	}

	/**
	 * 项目价格联合项目查询
	 * @return R
	 */
	@ApiOperation(value = "项目价格联合项目查询", notes = "通项目价格联合项目查询")
	@GetMapping("/get/price/left/item/list/page")
	public R getPriceLeftItemPage(Page page, @RequestParam(required = false) String hospitalId,
								  @RequestParam(required = false) String inspItemId,
								  @RequestParam(required = false) String inspItemType) {
		return R.ok(inspectionpriceService.getPriceLeftItemPage(page, Wrappers.query()
				.eq("a.del_flag", 0)
				.eq(StrUtil.isNotBlank(hospitalId), "hospital_id", hospitalId)
				.eq(StrUtil.isNotBlank(inspItemType),"insp_item_type", inspItemType)
				.eq(StrUtil.isNotBlank(inspItemId),"a.insp_item_id", inspItemId)
		), ReturnMsgConstants.QUERY_SUCCESS);
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
		int count = inspectionpriceService.count(new QueryWrapper<Inspectionprice>()
				.eq("hospital_id", inspectionprice.getHospitalId())
				.eq("insp_item_id", inspectionprice.getInspItemId()));
		if (count > 0) {
			return R.failed("同一机构同一项目不允许多个价格!");
		}
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
