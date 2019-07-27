package com.kasoft.register.base.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pig4cloud.pigx.common.core.util.R;
import com.pig4cloud.pigx.common.log.annotation.SysLog;
import com.kasoft.register.base.api.entity.DoctorProblemfeedback;
import com.kasoft.register.base.service.DoctorProblemfeedbackService;
import io.swagger.annotations.Api;
import org.springframework.security.access.prepost.PreAuthorize;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


/**
 * 问题反馈
 *
 * @author charlie
 * @date 2019-04-30 17:10:08
 */
@RestController
@AllArgsConstructor
@RequestMapping("/doctorproblemfeedback" )
@Api(value = "doctorproblemfeedback", tags = "基础-问题反馈")
public class DoctorProblemfeedbackController {

    private final  DoctorProblemfeedbackService doctorProblemfeedbackService;

    /**
     * 分页查询
     * @param page 分页对象
     * @param doctorProblemfeedback 问题反馈
     * @return
     */
    @GetMapping("/page" )
    public R getDoctorProblemfeedbackPage(Page page, DoctorProblemfeedback doctorProblemfeedback) {
        return R.ok(doctorProblemfeedbackService.page(page, Wrappers.query(doctorProblemfeedback)));
    }


    /**
     * 通过id查询问题反馈
     * @param id id
     * @return R
     */
    @GetMapping("/{id}" )
    public R getById(@PathVariable("id" ) String id) {
        return R.ok(doctorProblemfeedbackService.getById(id));
    }

    /**
     * 新增问题反馈
     * @param doctorProblemfeedback 问题反馈
     * @return R
     */
    @SysLog("新增问题反馈" )
    @PostMapping
    public R save(@Valid @RequestBody DoctorProblemfeedback doctorProblemfeedback) {
		doctorProblemfeedback.clearNoUseDTO();
    	return R.ok(doctorProblemfeedbackService.save(doctorProblemfeedback));
    }

    /**
     * 修改问题反馈
     * @param doctorProblemfeedback 问题反馈
     * @return R
     */
    @SysLog("修改问题反馈" )
    @PutMapping
    @PreAuthorize("@pms.hasPermission('base_doctorproblemfeedback_edit')" )
    public R updateById(@Valid @RequestBody DoctorProblemfeedback doctorProblemfeedback) {
		doctorProblemfeedback.clearNoUseDTO();
        return R.ok(doctorProblemfeedbackService.updateById(doctorProblemfeedback));
    }

    /**
     * 通过id删除问题反馈
     * @param id id
     * @return R
     */
    @SysLog("删除问题反馈" )
    @DeleteMapping("/{id}" )
    @PreAuthorize("@pms.hasPermission('base_doctorproblemfeedback_del')" )
    public R removeById(@PathVariable String id) {
        return R.ok(doctorProblemfeedbackService.removeById(id));
    }

}
