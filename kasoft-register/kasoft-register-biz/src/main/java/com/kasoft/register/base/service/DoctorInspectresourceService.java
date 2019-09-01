package com.kasoft.register.base.service;

import cn.hutool.json.JSONArray;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.kasoft.register.base.api.entity.DoctorInspectresource;
import com.kasoft.register.base.api.vo.InspSourcesVO;

import java.sql.Wrapper;
import java.util.List;

/**
 * 检查资源
 *
 * @author kylin
 * @date 2019-07-27 10:32:30
 */
public interface DoctorInspectresourceService extends IService<DoctorInspectresource> {

	/**
	 * 新增检查资源-批量
	 * @param inspectresources 检查资源
	 * @return boolean
	 */
	boolean saveInspectresourceBatch(List<DoctorInspectresource> inspectresources);


	List<DoctorInspectresource> getAllItemGroupPage();

	/**
	 * 移动端资源列表(实时医院信息)
	 * @param page 分页对象
	 * @param wrapper 参数
	 * @return IPage
	 */
	IPage<DoctorInspectresource> getDoctorInspectresourcePageNew(Page<DoctorInspectresource> page, QueryWrapper wrapper);
}
