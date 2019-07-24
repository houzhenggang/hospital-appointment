/*
 *    Copyright (c) 2018-2025, lengleng All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice,
 * this list of conditions and the following disclaimer.
 * Redistributions in binary form must reproduce the above copyright
 * notice, this list of conditions and the following disclaimer in the
 * documentation and/or other materials provided with the distribution.
 * Neither the name of the pig4cloud.com developer nor the names of its
 * contributors may be used to endorse or promote products derived from
 * this software without specific prior written permission.
 * Author: lengleng (wangiegie@gmail.com)
 */
package com.kasoft.register.base.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kasoft.register.base.dto.AreaTree;
import com.kasoft.register.base.service.DoctorAreadictionaryService;
import com.kasoft.register.base.utils.EdConstants;
import com.kasoft.register.base.utils.EdTreeUtils;
import com.kasoft.register.base.entity.DoctorAreadictionary;
import com.kasoft.register.base.mapper.DoctorAreadictionaryMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 地区字典
 *
 * @author charlie
 * @date 2019-04-30 17:10:28
 */
@Service
public class DoctorAreadictionaryServiceImpl extends ServiceImpl<DoctorAreadictionaryMapper, DoctorAreadictionary> implements DoctorAreadictionaryService {

	@Override
	@Cacheable(value = EdConstants.ED_AREA_DETAILS, key = "#id", unless = "#result == null ")
	public DoctorAreadictionary getById(String id) {
		return super.getById(id);
	}

	@Override
	@Cacheable(value = EdConstants.ED_AREA_DETAILS_ALL, unless = "#result == null ")
	public List<AreaTree> allTree() {
		List<DoctorAreadictionary> areaList = list();
		List<AreaTree> areaTreeList = new ArrayList<>();
		areaList.forEach(item -> {
			AreaTree tree = new AreaTree();
			tree.setParentId(item.getParentAreaId());
			BeanUtils.copyProperties(item, tree);
			areaTreeList.add(tree);
		});
		List<AreaTree> areaTreeTrees = EdTreeUtils.buildByRecursive(areaTreeList, EdConstants.TREE_ROOT_ID);
		return areaTreeTrees;
	}
}
