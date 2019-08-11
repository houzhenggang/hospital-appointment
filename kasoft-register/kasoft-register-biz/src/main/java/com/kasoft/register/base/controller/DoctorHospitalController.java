package com.kasoft.register.base.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kasoft.register.base.utils.KrbConstants;
import com.pig4cloud.pigx.common.core.util.R;
import com.pig4cloud.pigx.common.log.annotation.SysLog;
import com.kasoft.register.base.api.entity.DoctorHospital;
import com.kasoft.register.base.service.DoctorHospitalService;
import com.pig4cloud.pigx.common.security.annotation.Inner;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


/**
 * 医院
 *
 * @author charlie
 * @date 2019-04-30 17:09:57
 */
@RestController
@AllArgsConstructor
@RequestMapping("/doctorhospital" )
@Api(value = "doctordoctorinfoteam", tags = "基础-医院")
public class DoctorHospitalController {

    private final  DoctorHospitalService doctorHospitalService;

    /**
     * 分页查询
     * @param page 分页对象
     * @param doctorHospital 医院
     * @return
     */
    @GetMapping("/page" )
    public R getDoctorHospitalPage(Page page, DoctorHospital doctorHospital) {
        return R.ok(doctorHospitalService.page(page, Wrappers.query(doctorHospital)));
    }

	/**
	 * 查询医院字典
	 * @return
	 */
	@Inner(false)
	@ApiOperation(value = "查询医院字典", notes = "查询医院字典")
	@GetMapping("/dict" )
	@Cacheable(value = KrbConstants.ED_HOSPITAL_DETAILS_DICT, unless = "#result == null ")
	public R getHospitalDict() {
		return R.ok(doctorHospitalService.getHospitalByName(null));
	}


    /**
     * 通过id查询医院
     * @param id id
     * @return R
     */
    @GetMapping("/{id}" )
    public R getById(@PathVariable("id" ) String id) {
        return R.ok(doctorHospitalService.getById(id));
    }

    /**
     * 新增医院
     * @param doctorHospital 医院
     * @return R
     */
    @SysLog("新增医院" )
    @PostMapping
    @PreAuthorize("@pms.hasPermission('base_doctorhospital_add')" )
	@CacheEvict(value = {KrbConstants.ED_HOSPITAL_DETAILS, KrbConstants.ED_HOSPITAL_DETAILS_DICT}, allEntries = true)
	public R save(@Valid @RequestBody DoctorHospital doctorHospital) {
		doctorHospital.clearNoUseDTO();
		return R.ok(doctorHospitalService.save(doctorHospital));
    }

    /**
     * 修改医院
     * @param doctorHospital 医院
     * @return R
     */
    @SysLog("修改医院" )
    @PutMapping
    @PreAuthorize("@pms.hasPermission('base_doctorhospital_edit')" )
	@CacheEvict(value = {KrbConstants.ED_HOSPITAL_DETAILS, KrbConstants.ED_HOSPITAL_DETAILS_DICT}, allEntries = true)
	public R updateById(@Valid @RequestBody DoctorHospital doctorHospital) {
		doctorHospital.clearNoUseDTO();
        return R.ok(doctorHospitalService.updateById(doctorHospital));
    }

    /**
     * 通过id删除医院
     * @param id id
     * @return R
     */
    @SysLog("删除医院" )
    @DeleteMapping("/{id}" )
    @PreAuthorize("@pms.hasPermission('base_doctorhospital_del')" )
	@CacheEvict(value = {KrbConstants.ED_HOSPITAL_DETAILS, KrbConstants.ED_HOSPITAL_DETAILS_DICT}, allEntries = true)
	public R removeById(@PathVariable String id) {
        return R.ok(doctorHospitalService.removeById(id));
    }

}
