package com.kasoft.register.base.dto;

import com.kasoft.register.base.entity.DoctorProjectlibrary;
import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * 项目库DTO
 *
 * @author charlie
 */
@Data
@ApiModel(description = "项目库DTO")
public class DoctorProjectlibraryDTO extends DoctorProjectlibrary {

	@Override
	public void clearNoUseDTO() {
		super.clearNoUseDTO();
	}

}
