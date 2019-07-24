package com.kasoft.register.base.dto;

import cn.hutool.core.collection.CollUtil;
import com.kasoft.register.base.entity.DoctorAgreement;
import com.kasoft.register.base.entity.DoctorPeopleservicepackage;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;
/**
 * 签约信息
 *
 * @author charlie
 */
@Data
@ApiModel(description = "签约信息dto")
public class DoctorAgreementDTO extends DoctorAgreement {

	/**
	 * 包含签约项（套餐） 还有套餐项 列表
	 */
	@ApiModelProperty(value = "包含签约项（套餐列表）")
	private List<DoctorPeopleservicepackage> doctorServicepackageList;
	/**
	 * mongo中签约详情
	 */
	@ApiModelProperty(value = "签约详情")
	private MongoAgreement mongoAgreement;
	@Override
	public void clearNoUseDTO() {
		super.clearNoUseDTO();
		if(CollUtil.isNotEmpty(doctorServicepackageList)) {
			doctorServicepackageList.forEach(item -> {
				item.clearNoUseDTO();
			});
		}
	}
}
