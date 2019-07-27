package com.kasoft.register.base.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kasoft.register.base.utils.KrbConstants;
import com.pig4cloud.pigx.common.core.util.R;
import com.pig4cloud.pigx.common.log.annotation.SysLog;
import com.kasoft.register.base.api.entity.DoctorDepartmentdictionary;
import com.kasoft.register.base.service.DoctorDepartmentdictionaryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


/**
 * 科室字典
 *
 * @author charlie
 * @date 2019-04-30 17:10:43
 */
@RestController
@AllArgsConstructor
@RequestMapping("/doctordepartmentdictionary" )
@Api(value = "doctordepartmentdictionary", tags = "基础-科室")
public class DoctorDepartmentdictionaryController {

    private final  DoctorDepartmentdictionaryService doctorDepartmentdictionaryService;

    /**
     * 分页查询
     * @param page 分页对象
     * @param doctorDepartmentdictionary 科室字典
     * @return
     */
    @GetMapping("/page" )
    public R getDoctorDepartmentdictionaryPage(Page page, DoctorDepartmentdictionary doctorDepartmentdictionary) {
        return R.ok(doctorDepartmentdictionaryService.page(page, Wrappers.query(doctorDepartmentdictionary)));
    }

	/**
	 * 根据医院编号查询科室字典
	 *  @param hospitalId 医院编号
	 * @return
	 */
	@ApiOperation(value = "根据医院编号查询科室字典", notes = "根据医院编号查询科室字典,备注{{key}}表示查询全部")
	@GetMapping("/dict/{hospitalId}" )
	public R getDepartmentDictByHospital(@PathVariable("hospitalId" )String hospitalId) {
		if(KrbConstants.ALL_KEY.equals(hospitalId)) {
			hospitalId = null;
		}
		return R.ok(doctorDepartmentdictionaryService.getDepartmentDictByHospital(hospitalId));
	}

	/**
	 * 查询全部科室字典
	 * @return
	 */
	@ApiOperation(value = "查询全部科室字典", notes = "查询全部科室字典")
	@GetMapping("/dict-all" )
	public R getDepartmentDictAll() {
		return R.ok(doctorDepartmentdictionaryService.getDepartmentDictByHospital(null));
	}



	/**
     * 通过id查询科室字典
     * @param id id
     * @return R
     */
    @GetMapping("/{id}" )
    public R getById(@PathVariable("id" ) String id) {
        return R.ok(doctorDepartmentdictionaryService.getById(id));
    }

    /**
     * 新增科室字典
     * @param doctorDepartmentdictionary 科室字典
     * @return R
     */
    @SysLog("新增科室字典" )
    @PostMapping
    @PreAuthorize("@pms.hasPermission('base_doctordepartment_add')" )
	@CacheEvict(value = KrbConstants.ED_DEPARTMENT_DETAILS, allEntries = true)
	public R save(@Valid @RequestBody DoctorDepartmentdictionary doctorDepartmentdictionary) {
		doctorDepartmentdictionary.clearNoUseDTO();
        return R.ok(doctorDepartmentdictionaryService.save(doctorDepartmentdictionary));
    }

    /**
     * 修改科室字典
     * @param doctorDepartmentdictionary 科室字典
     * @return R
     */
    @SysLog("修改科室字典" )
    @PutMapping
    @PreAuthorize("@pms.hasPermission('base_doctordepartment_edit')" )
	@CacheEvict(value = KrbConstants.ED_DEPARTMENT_DETAILS, allEntries = true)
	public R updateById(@Valid @RequestBody DoctorDepartmentdictionary doctorDepartmentdictionary) {
		doctorDepartmentdictionary.clearNoUseDTO();
    	return R.ok(doctorDepartmentdictionaryService.updateById(doctorDepartmentdictionary));
    }

    /**
     * 通过id删除科室字典
     * @param id id
     * @return R
     */
    @SysLog("删除科室字典" )
    @DeleteMapping("/{id}" )
    @PreAuthorize("@pms.hasPermission('base_doctordepartment_del')" )
	@CacheEvict(value = KrbConstants.ED_DEPARTMENT_DETAILS, allEntries = true)
	public R removeById(@PathVariable String id) {
        return R.ok(doctorDepartmentdictionaryService.removeById(id));
    }

}
