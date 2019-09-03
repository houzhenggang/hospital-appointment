package com.kasoft.register.base.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kasoft.register.base.api.entity.Applyerinfo;
import com.kasoft.register.base.api.entity.DoctorPeopleinfo;
import com.kasoft.register.base.mapper.ApplyerinfoMapper;
import com.kasoft.register.base.mapper.DoctorPeopleinfoMapper;
import com.kasoft.register.base.service.ApplyerinfoService;
import com.pig4cloud.pigx.common.core.constant.CommonConstants;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 预约人信息
 *
 * @author kylin
 * @date 2019-08-14 21:06:00
 */
@Service
@AllArgsConstructor
public class ApplyerinfoServiceImpl extends ServiceImpl<ApplyerinfoMapper, Applyerinfo> implements ApplyerinfoService {

	private final DoctorPeopleinfoMapper doctorPeopleinfoMapper;

//	@Override
//	@Transactional(rollbackFor = Exception.class)
//	public Boolean updateApplyerinfo(Applyerinfo applyerinfo) {
//		if (applyerinfo.getIsSelf() != null && StrUtil.equals(applyerinfo.getIsSelf(), CommonConstants.IS_SELF) ) {
//			DoctorPeopleinfo upDoctorPeople = new DoctorPeopleinfo();
//			upDoctorPeople.setIdCard(applyerinfo.getIdCard());
//			upDoctorPeople.setName(applyerinfo.getApplyerName());
//			upDoctorPeople.setSex(applyerinfo.getSex());
//			doctorPeopleinfoMapper.updateById(upDoctorPeople);
//		}
//		this.updateById(applyerinfo);
//		return Boolean.TRUE;
//	}
}
