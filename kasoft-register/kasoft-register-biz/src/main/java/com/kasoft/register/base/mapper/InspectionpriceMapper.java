package com.kasoft.register.base.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kasoft.register.base.api.entity.Inspectionprice;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

/**
 * 检查价格
 *
 * @author kylin
 * @date 2019-08-03 14:30:41
 */
public interface InspectionpriceMapper extends BaseMapper<Inspectionprice> {

	IPage<Inspectionprice> getPriceLeftItemPage(Page<Inspectionprice> page,@Param(Constants.WRAPPER) QueryWrapper wrapper);
}
