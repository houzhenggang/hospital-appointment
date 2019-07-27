package com.kasoft.register.base.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kasoft.register.base.service.DoctorStreetdictionaryService;
import com.kasoft.register.base.utils.KrbConstants;
import com.kasoft.register.base.entity.DoctorStreetdictionary;
import com.kasoft.register.base.mapper.DoctorStreetdictionaryMapper;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * 街道居委会
 *
 * @author charlie
 * @date 2019-04-30 17:10:33
 */
@Service
public class DoctorStreetdictionaryServiceImpl extends ServiceImpl<DoctorStreetdictionaryMapper, DoctorStreetdictionary> implements DoctorStreetdictionaryService {


	@Override
	@Cacheable(value = KrbConstants.ED_STREET_DETAILS, key = "#id", unless = "#result == null ")
	public DoctorStreetdictionary getById(String id) {
		return super.getById(id);
	}
}
