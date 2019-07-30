package com.kasoft.register.base.api.vo;

import lombok.Data;

/**
 * 医生信息展现类
 * @author charlie
 */
@Data
public class InspSourcesVO {

	private String queryDate;

	private String startDate;

	private String endDate;

	private String inspItemAp;

	private String inspItemName;

	private String startTime;

	private String endTime;

	private String hospitalId;

}
