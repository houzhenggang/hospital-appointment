package com.kasoft.register.base.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.kasoft.register.base.dto.AreaTree;
import com.kasoft.register.base.entity.DoctorAreadictionary;

import java.util.List;

/**
 * 地区字典
 *
 * @author charlie
 * @date 2019-04-30 17:10:28
 */
public interface DoctorAreadictionaryService extends IService<DoctorAreadictionary> {
	/**
	 * 根据id查询地区信息
	 * @param id 地区编号
	 * @return 地区对象
	 */
	DoctorAreadictionary getById(String id);

	/**
	 * 获取全部地区树
	 * @return
	 */
	List<AreaTree> allTree();
}
