package com.kasoft.register.base.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kasoft.register.base.api.dto.AreaTree;
import com.kasoft.register.base.service.DoctorAreadictionaryService;
import com.kasoft.register.base.utils.KrbConstants;
import com.kasoft.register.base.utils.EdTreeUtils;
import com.kasoft.register.base.api.entity.DoctorAreadictionary;
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
	@Cacheable(value = KrbConstants.ED_AREA_DETAILS, key = "#id", unless = "#result == null ")
	public DoctorAreadictionary getById(String id) {
		return super.getById(id);
	}

	@Override
	@Cacheable(value = KrbConstants.ED_AREA_DETAILS_ALL, unless = "#result == null ")
	public List<AreaTree> allTree() {
		List<DoctorAreadictionary> areaList = list();
		List<AreaTree> areaTreeList = new ArrayList<>();
		areaList.forEach(item -> {
			AreaTree tree = new AreaTree();
			tree.setParentId(item.getParentAreaId());
			BeanUtils.copyProperties(item, tree);
			areaTreeList.add(tree);
		});
		List<AreaTree> areaTreeTrees = EdTreeUtils.buildByRecursive(areaTreeList, KrbConstants.TREE_ROOT_ID);
		return areaTreeTrees;
	}
}
