package com.kasoft.register.base.controller;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kasoft.register.base.api.entity.DoctorApplyorder;
import com.kasoft.register.base.config.YunpianPropertiesConfig;
import com.kasoft.register.base.service.DoctorApplyorderService;
import com.kasoft.register.base.utils.SmsUtils;
import com.pig4cloud.pigx.common.core.util.R;
import com.pig4cloud.pigx.common.log.annotation.SysLog;
import com.pig4cloud.pigx.common.security.annotation.Inner;
import com.yunpian.sdk.YunpianClient;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;


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

    private final YunpianClient yunpianClient;

    private final YunpianPropertiesConfig yunpianPropertiesConfig;

    /**
     * pc端分页查询
     * @param page 分页对象
     * @param doctorApplyorder 预约订单
     * @return R
     */
    @ApiOperation(value = "pc端分页查询", notes = "pc端分页查询")
    @GetMapping("/page")
    public R getDoctorApplyorderPage(Page page, DoctorApplyorder doctorApplyorder,
									 @RequestParam(required = false)LocalDate startDate, @RequestParam(required = false)LocalDate endDate) {
		return R.ok(doctorApplyorderService.page(page, Wrappers.query(doctorApplyorder)
				.between(ObjectUtil.isNotNull(startDate), "insp_item_date", startDate, endDate)
				.orderByDesc("create_time")));
    }

    /**
     * 移动端分页查询
     * @param page 分页对象
     * @param doctorApplyorder 预约订单
     * @return R
     */
    @ApiOperation(value = "移动端分页查询", notes = "移动端分页查询")
    @GetMapping("/page/mobile")
    public R getDoctorApplyorderPageMobile(Page page, DoctorApplyorder doctorApplyorder,
									 @RequestParam(required = false)LocalDate startDate, @RequestParam(required = false)LocalDate endDate) {
		if (StrUtil.isBlank(doctorApplyorder.getPeopleId())) {
			return R.failed("业务参数缺失:peopleId");
		}
		return R.ok(doctorApplyorderService.page(page, Wrappers.query(doctorApplyorder)
				.between(ObjectUtil.isNotNull(startDate), "insp_item_date", startDate, endDate)
				.orderByDesc("create_time")));
    }

    /**
     * 通过id查询预约订单
     * @param applyOrderId id
     * @return R
     */
    @ApiOperation(value = "通过id查询", notes = "通过id查询")
    @GetMapping("/{applyOrderId}")
    public R getById(@PathVariable("applyOrderId") String applyOrderId) {
        return R.ok(doctorApplyorderService.getById(applyOrderId));
    }

    /**
     * 新增预约订单
     * @param doctorApplyorder 预约订单
     * @return R
     */
    @Inner(false)
    @ApiOperation(value = "新增预约订单", notes = "新增预约订单")
    @SysLog("新增预约订单")
    @PostMapping
//    @PreAuthorize("@pms.hasPermission('base_doctorapplyorder_add')")
    public R save(@RequestBody DoctorApplyorder doctorApplyorder) {
		doctorApplyorderService.addApplyorder(doctorApplyorder);
        return R.ok(doctorApplyorder);
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
		doctorApplyorderService.updateApplyOrder(doctorApplyorder);
        return R.ok();
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
    public R removeById(@PathVariable String applyOrderId) {
        return R.ok(doctorApplyorderService.removeById(applyOrderId));
    }

    /**
     * 短信测试
     * @param mobile 电话号码
     * @return R
     */
    @Inner(false)
    @ApiOperation(value = "短信推送测试", notes = "短信推送测试")
    @GetMapping("/test/send/message")
    public R sendMessageTest(@RequestParam String mobile)throws Exception {
		//初始化clnt,使用单例方式
		String text = yunpianPropertiesConfig.getSignature()+ "您好，您的预约" + "2019年08月15日 8:00-9:00南京市中西医结合医院外科7项" + "服务已成功取消。";
		SmsUtils.sendSms(yunpianClient, mobile, text);
		return R.ok(null, "发送成功!");
    }

}
