package com.kasoft.register.base.controller;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kasoft.register.base.api.entity.DoctorApplyorder;
import com.kasoft.register.base.api.entity.DoctorInspectresource;
import com.kasoft.register.base.api.vo.InspSourcesVO;
import com.kasoft.register.base.mapper.DoctorApplyorderMapper;
import com.kasoft.register.base.service.DoctorInspectresourceService;
import com.kasoft.register.base.utils.KrbConstants;
import com.pig4cloud.pigx.common.core.constant.ReturnMsgConstants;
import com.pig4cloud.pigx.common.core.util.R;
import com.pig4cloud.pigx.common.log.annotation.SysLog;
import com.pig4cloud.pigx.common.security.annotation.Inner;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;


/**
 * 检查资源
 *
 * @author kylin
 * @date 2019-07-27 10:32:30
 */
@RestController
@AllArgsConstructor
@RequestMapping("/doctorinspectresource")
@Api(value = "doctorinspectresource", tags = "基础-检查资源")
public class DoctorInspectresourceController {

    private final DoctorInspectresourceService doctorInspectresourceService;

	private final DoctorApplyorderMapper doctorApplyorderMapper;

	/**
	 * 查询资源分类列表
	 * @return R
	 */
	@Inner(false)
	@ApiOperation(value = "查询资源分类列表", notes = "查询资源分类列表")
	@GetMapping("/page/all/item/group")
	public R getAllItemGroupPage() {
		List<DoctorInspectresource> list = doctorInspectresourceService.getAllItemGroupPage();
		return R.ok(list);
	}


    /**
     * 移动端资源列表
     * @param page 分页对象
     * @param args 参数
     * @return R
     */
    @ApiOperation(value = "分页查询", notes = "分页查询")
    @GetMapping("/page/group")
    public R getDoctorInspectresourcePage(Page page, InspSourcesVO args) {
        return R.ok(doctorInspectresourceService.page(page, new QueryWrapper<DoctorInspectresource>()
				.select("SUM(quantity) as quantity,MAX(hospital_id) as hospital_id,MAX(hospital_name) as hospital_name,MAX(hospital_image) as hospital_image," +
						"MAX(hospital_phone) as hospital_phone,MAX(insp_item_id) as insp_item_id, MAX(insp_item_type) as insp_item_type, MAX(insp_item_name) as insp_item_name," +
						"MAX(insp_item_exp) as insp_item_exp,MIN(unit_price) as unit_price, MAX(unit_price) as max_unit_price, MAX(insp_resource_id) as insp_resource_id")
			.eq(StrUtil.isNotBlank(args.getInspItemType()), "insp_item_type", args.getInspItemType())
			.between(StrUtil.isNotBlank(args.getStartDate()), "insp_item_date", args.getStartDate(), args.getEndDate())
			.between(StrUtil.isBlank(args.getStartDate()), "insp_item_date", DateUtil.format(new Date(), DatePattern.NORM_DATE_PATTERN),
				DateUtil.format(DateUtil.offsetDay(new Date(), 14), DatePattern.NORM_DATE_PATTERN))
				.and(StrUtil.isNotBlank(args.getInspItemName()), wrapper -> wrapper.like("insp_item_type", args.getInspItemName()).or()
				.like("insp_item_name", args.getInspItemName()))
			.groupBy("hospital_id, insp_item_id")
		));
    }

    /**
     * 移动端资源列表(实时医院信息)
     * @param page 分页对象
     * @param args 参数
     * @return R
     */
    @Inner(false)
    @ApiOperation(value = "分页查询", notes = "分页查询")
    @GetMapping("/page/group/new")
    public R getDoctorInspectresourcePageNew(Page page, InspSourcesVO args) {
        return R.ok(doctorInspectresourceService.getDoctorInspectresourcePageNew(page, Wrappers.<DoctorInspectresource>query()
				.isNotNull("b.hospital_id")
				.eq("a.del_flag", 0)
				.eq(StrUtil.isNotBlank(args.getInspItemType()), "insp_item_type", args.getInspItemType())
				.between(StrUtil.isNotBlank(args.getStartDate()), "insp_item_date", args.getStartDate(), args.getEndDate())
				.between(StrUtil.isBlank(args.getStartDate()), "insp_item_date", DateUtil.format(new Date(), DatePattern.NORM_DATE_PATTERN),
						DateUtil.format(DateUtil.offsetDay(new Date(), 14), DatePattern.NORM_DATE_PATTERN))
				.like(StrUtil.isNotBlank(args.getInspItemName()), "insp_item_name", args.getInspItemName())
		));
    }

    /**
     * 分页查询
     * @param page 分页对象
     * @param args 参数
     * @return R
     */
    @ApiOperation(value = "分页查询", notes = "分页查询")
    @GetMapping("/page")
    public R page(Page page, DoctorInspectresource args) {
        return R.ok(doctorInspectresourceService.page(page, Wrappers.query(args).orderByDesc("create_time")));
    }

    /**
     * 列表查询
     * @param args 检查资源
     * @return R
     */
    @ApiOperation(value = "查询", notes = "查询")
    @GetMapping("/list")
    public R getDoctorInspectresourceList(InspSourcesVO args) {
        return R.ok(doctorInspectresourceService.list(new QueryWrapper<DoctorInspectresource>()
			.like(StrUtil.isNotBlank(args.getInspItemName()), "insp_item_name", args.getInspItemName())
			.eq(StrUtil.isNotBlank(args.getInspItemId()), "insp_item_id", args.getInspItemId())
				.ge(StrUtil.isNotBlank(args.getStartTime()), "start_time", args.getStartTime())
				.le(StrUtil.isNotBlank(args.getEndTime()), "end_time", args.getEndTime())
				.eq(StrUtil.isNotBlank(args.getHospitalId()), "hospital_id", args.getHospitalId())
		));
    }

    /**
     * 分组查询头部信息
     * @param args 入参
     * @return R
     */
    @ApiOperation(value = "分组查询头部信息", notes = "分组查询头部信息")
    @GetMapping("/list/group")
    public R getDoctorInspectresourceGroupList(InspSourcesVO args) {
        return R.ok(doctorInspectresourceService.list(new QueryWrapper<DoctorInspectresource>()
			.select("SUM(quantity) as quantity,insp_item_date,insp_item_week,insp_item_ap")
			.between("insp_item_date", args.getStartDate(), args.getEndDate())
				.gt("end_time", new Date())
				.eq(StrUtil.isNotBlank(args.getInspItemId()), "insp_item_id", args.getInspItemId())
				.eq(StrUtil.isNotBlank(args.getHospitalId()), "hospital_id", args.getHospitalId())
				.groupBy("insp_item_date,insp_item_week,insp_item_ap")
				.orderByAsc("insp_item_date,insp_item_week,insp_item_ap")
		),  ReturnMsgConstants.QUERY_SUCCESS);
    }

    /**
     * 分组查询详情信息
     * @param args 入参
     * @return R
     */
    @ApiOperation(value = "分组查询详情信息", notes = "分组查询详情信息")
    @GetMapping("/detail/group")
    public R getDoctorInspectresourceGroupDetail(InspSourcesVO args) {
        return R.ok(doctorInspectresourceService.list(new QueryWrapper<DoctorInspectresource>()
				.select("SUM(quantity) as quantity, insp_item_date, insp_item_week, insp_item_ap," +
						"period, max(insp_resource_id) as insp_resource_id")
				.gt("end_time", new Date())
				.eq(StrUtil.isNotBlank(args.getHospitalId()), "hospital_id", args.getHospitalId())
				.eq(StrUtil.isNotBlank(args.getInspItemId()), "insp_item_id", args.getInspItemId())
				.eq("insp_item_date", args.getQueryDate())
				.eq("insp_item_ap", args.getInspItemAp())
				.groupBy("insp_item_date,insp_item_week,insp_item_ap,period")
				.orderByAsc("insp_item_date,insp_item_week,insp_item_ap,period")
		), ReturnMsgConstants.QUERY_SUCCESS);
    }

	/**
	 * 查询检查资源字典
	 * @return R
	 */
	@ApiOperation(value = "查询检查资源字典", notes = "查询检查资源字典")
	@GetMapping("/dict")
	@Cacheable(value = KrbConstants.ED_INSPECTION_RESOURCE_DICT, unless = "#result == null")
	public R getInspectionResourceDict() {
		return R.ok(doctorInspectresourceService.list(new QueryWrapper<>()));
	}

    /**
     * 通过id查询检查资源
     * @param inspResourceId id
     * @return R
     */
    @ApiOperation(value = "通过id查询", notes = "通过id查询")
    @GetMapping("/{inspResourceId}")
    public R getById(@PathVariable("inspResourceId") String inspResourceId) {
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
    @PreAuthorize("@pms.hasPermission('base_doctorinspectresource_add')")
	@CacheEvict(value = {KrbConstants.ED_INSPECTION_RESOURCE_DICT}, allEntries = true)
	public R save(@RequestBody DoctorInspectresource doctorInspectresource) {
		int count = doctorInspectresourceService.count(new QueryWrapper<DoctorInspectresource>()
			.eq("hospital_id", doctorInspectresource.getHospitalId())
			.eq("insp_item_id", doctorInspectresource.getInspItemId())
			.eq("insp_item_date", doctorInspectresource.getInspItemDate())
			.eq("period", doctorInspectresource.getPeriod())
		);
		if (count > 0) {
			return R.failed("同一医院,同一项目,同一时间段只允许添加一个检查资源!");
		}
		return R.ok(doctorInspectresourceService.save(doctorInspectresource));
    }

    /**
     * 新增检查资源-批量
     * @param inspectresources 检查资源
     * @return R
     */
    @Inner(false)
    @ApiOperation(value = "批量新增检查资源", notes = "批量新增检查资源")
    @SysLog("批量新增检查资源")
    @PostMapping("/save/batch")
    @PreAuthorize("@pms.hasPermission('base_doctorinspectresource_add')")
	@CacheEvict(value = {KrbConstants.ED_INSPECTION_RESOURCE_DICT}, allEntries = true)
	public R saveInspectresourceBatch(@RequestBody List<DoctorInspectresource> inspectresources) {
		return R.ok(doctorInspectresourceService.saveInspectresourceBatch(inspectresources));
    }

    /**
     * 修改检查资源
     * @param doctorInspectresource 检查资源
     * @return R
     */
    @ApiOperation(value = "修改检查资源", notes = "修改检查资源")
    @SysLog("修改检查资源")
    @PutMapping
    @PreAuthorize("@pms.hasPermission('base_doctorinspectresource_edit')")
	@CacheEvict(value = {KrbConstants.ED_INSPECTION_RESOURCE_DICT}, allEntries = true)
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
    @PreAuthorize("@pms.hasPermission('base_doctorinspectresource_del')")
	@CacheEvict(value = {KrbConstants.ED_INSPECTION_RESOURCE_DICT}, allEntries = true)
	public R removeById(@PathVariable String inspResourceId) {
		int count = doctorApplyorderMapper.selectCount(Wrappers.<DoctorApplyorder>lambdaQuery()
			.eq(DoctorApplyorder::getInspResourceId, inspResourceId)
		);
    	if (count > 0){
			return R.failed("该资源存在预约订单,不允许删除!");
		}
        return R.ok(doctorInspectresourceService.removeById(inspResourceId));
    }

}
