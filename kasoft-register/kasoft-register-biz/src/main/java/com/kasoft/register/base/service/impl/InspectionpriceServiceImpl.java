package com.kasoft.register.base.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kasoft.register.base.api.entity.Inspectionprice;
import com.kasoft.register.base.mapper.InspectionpriceMapper;
import com.kasoft.register.base.service.InspectionpriceService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 检查价格
 *
 * @author kylin
 * @date 2019-08-03 14:30:41
 */
@Service
@AllArgsConstructor
public class InspectionpriceServiceImpl extends ServiceImpl<InspectionpriceMapper, Inspectionprice> implements InspectionpriceService {

	private final InspectionpriceMapper inspectionpriceMapper;

	@Override
	public IPage<Inspectionprice> getPriceLeftItemPage(Page<Inspectionprice> page, QueryWrapper wrapper) {
		return inspectionpriceMapper.getPriceLeftItemPage(page, wrapper);
	}
}
