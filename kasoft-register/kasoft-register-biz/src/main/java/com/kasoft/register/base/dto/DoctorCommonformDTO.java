package com.kasoft.register.base.dto;

import com.kasoft.register.base.entity.DoctorCommonform;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 通用表单DTO
 *
 * @author charlie
 */
@Data
@ApiModel(description = "通用表单DTO")
public class DoctorCommonformDTO extends DoctorCommonform {
	/**
	 * 当前项目服务次数
	 */
	@ApiModelProperty(value = "当前项目服务次数")
	private Integer nowServiceFrequency;

	@Override
	public void clearNoUseDTO() {
		super.clearNoUseDTO();
	}
}
