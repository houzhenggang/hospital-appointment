package com.kasoft.register.base.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kasoft.register.base.api.entity.DoctorInspectionitem;
import com.kasoft.register.base.mapper.DoctorInspectionitemMapper;
import com.kasoft.register.base.service.DoctorInspectionitemService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 检查项目
 *
 * @author kylin
 * @date 2019-07-26 21:33:19
 */
@Service
@AllArgsConstructor
public class DoctorInspectionitemServiceImpl extends ServiceImpl<DoctorInspectionitemMapper, DoctorInspectionitem> implements DoctorInspectionitemService {

	DoctorInspectionitemMapper doctorInspectionitemMapper;

	@Override
	public List<DoctorInspectionitem> queryHotInspitem() {
		return doctorInspectionitemMapper.queryHotInspitem();
	}
}
