package com.kasoft.register.base.dto;

import cn.hutool.core.collection.CollUtil;
import com.kasoft.register.base.entity.DoctorServicepackage;
import com.kasoft.register.base.entity.DoctorServicepackageitem;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;


/**
 * 套餐包服务包
 *
 * @author charlie
 */
@Data
@ApiModel(description = "套餐包服务包DTO")
public class DoctorServicepackageDTO extends DoctorServicepackage {

	/**
	 * 包含套餐项列表
	 */
	@ApiModelProperty(value = "包含套餐项列表")
	private List<DoctorServicepackageitem> doctorServicepackageitemList;
	@Override
	public void clearNoUseDTO() {
		super.clearNoUseDTO();
		if(CollUtil.isNotEmpty(doctorServicepackageitemList)) {
			doctorServicepackageitemList.forEach(item -> {
				item.clearNoUseDTO();
			});
		}
	}
}
