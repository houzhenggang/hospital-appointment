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
import com.kasoft.register.base.service.DoctorStreetdictionaryService;
import com.kasoft.register.base.utils.EdConstants;
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
	@Cacheable(value = EdConstants.ED_STREET_DETAILS, key = "#id", unless = "#result == null ")
	public DoctorStreetdictionary getById(String id) {
		return super.getById(id);
	}
}
