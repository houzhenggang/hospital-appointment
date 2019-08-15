package com.kasoft.register.base.service;

import cn.hutool.json.JSONArray;
import com.baomidou.mybatisplus.extension.service.IService;
import com.kasoft.register.base.api.entity.DoctorInspectresource;

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
}
