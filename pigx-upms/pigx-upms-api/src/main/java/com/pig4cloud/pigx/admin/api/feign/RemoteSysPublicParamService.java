package com.pig4cloud.pigx.admin.api.feign;

import com.pig4cloud.pigx.common.core.constant.ServiceNameConstants;
import com.pig4cloud.pigx.common.core.util.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * 公共参数
 * @author lengleng
 * @date 2018/6/22
 */
@FeignClient(contextId = "remoteSysPublicParamService", value = ServiceNameConstants.UMPS_SERVICE)
public interface RemoteSysPublicParamService {

	/**
	 * 获取用户信息
	 * @param publicKey 键名
	 * @return 用户对象
	 */
	@GetMapping("/param/publicValue/{publicKey}")
	R<String> publicKey(@PathVariable("publicKey") String publicKey);
}
