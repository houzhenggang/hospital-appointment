package com.kasoft.register.base.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.kasoft.register.base.api.entity.DoctorInspectresource;
import com.kasoft.register.base.api.entity.Inspectionprice;

import java.util.Map;

/**
 * 检查价格
 *
 * @author kylin
 * @date 2019-08-03 14:30:41
 */
public interface InspectionpriceService extends IService<Inspectionprice> {

	IPage<Inspectionprice> getPriceLeftItemPage(Page<Inspectionprice> page, QueryWrapper wrapper);
}
