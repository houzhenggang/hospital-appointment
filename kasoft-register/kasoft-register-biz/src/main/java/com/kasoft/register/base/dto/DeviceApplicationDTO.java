package com.kasoft.register.base.dto;

import cn.hutool.core.collection.CollUtil;
import com.kasoft.register.base.entity.DeviceApplication;
import com.kasoft.register.base.entity.DoctorServicepackage;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * 自助签约dto
 * @author charlie
 */
@Data
@ApiModel(description = "自助签约dto")
public class DeviceApplicationDTO extends DeviceApplication {
	/**
	 * 包含签约项（套餐） 还有套餐项 列表
	 */
	@ApiModelProperty(value = "包含签约项（套餐列表）")
	private List<DoctorServicepackage> doctorServicepackageList;
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
