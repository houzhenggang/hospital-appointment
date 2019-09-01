package com.kasoft.register.base.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kasoft.register.base.api.entity.DoctorInspectresource;
import com.kasoft.register.base.api.vo.InspSourcesVO;
import org.apache.ibatis.annotations.Param;

import java.sql.Wrapper;
import java.util.List;

/**
 * 检查资源
 *
 * @author kylin
 * @date 2019-07-27 10:32:30
 */
public interface DoctorInspectresourceMapper extends BaseMapper<DoctorInspectresource> {

	/**
	 * 查询所有检查项目
	 * @return list
	 */
	List<DoctorInspectresource> getAllItemGroup();

	/**
	 * 移动端资源列表(实时医院信息)
	 * @param page 分页对象
	 * @param wrapper 参数
	 * @return IPage
	 */
	IPage<DoctorInspectresource> getDoctorInspectresourceNew(Page page, @Param(Constants.WRAPPER) QueryWrapper wrapper);
}
