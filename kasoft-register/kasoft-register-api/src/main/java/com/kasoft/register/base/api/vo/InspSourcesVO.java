package com.kasoft.register.base.api.vo;

import com.kasoft.register.base.api.entity.DoctorDoctorinfo;
import lombok.Data;

/**
 * 医生信息展现类
 * @author charlie
 */
@Data
public class InspSourcesVO extends DoctorDoctorinfo {

	private String startDate;

	private String endDate;

	private String inspItemAp;
}
