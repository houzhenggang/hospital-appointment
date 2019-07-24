package com.kasoft.register.base.dto;

import com.kasoft.register.base.entity.DeviceImuser;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Map;

/**
 * IM用户管理dto
 * @author charlie
 */
@Data
@ApiModel(description = "IM用户管理dto")
public class DeviceImuserDTO  extends DeviceImuser {
	@ApiModelProperty(value = "用户签名信息")
	private Map<String, String> userSigInfo;
}
