package com.kasoft.register.base.vo;

import com.kasoft.register.base.entity.DoctorDoctorinfo;
import lombok.Data;

/**
 * 医生信息展现类
 * @author charlie
 */
@Data
public class DoctorDoctorinfoVO extends DoctorDoctorinfo {
	/**
	 * 地区名称
	 */
	private String areaIdName;
	/**
	 * 医院名称
	 */
	private String hospitalIdName;
	/**
	 * 科室名称
	 */
	private String departmentIdName;
}
