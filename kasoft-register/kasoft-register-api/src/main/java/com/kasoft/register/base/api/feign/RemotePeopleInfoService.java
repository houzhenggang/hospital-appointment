package com.kasoft.register.base.api.feign;

import com.pig4cloud.pigx.common.core.constant.ServiceNameConstants;
import com.pig4cloud.pigx.common.core.util.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author kylin
 * @since 2019/7/27 20:55
 */
@FeignClient(contextId = "remotePeopleInfoService", value = ServiceNameConstants.REGISTER_MANAGER)
public interface RemotePeopleInfoService {

	/**
	 * 获取用户信息
	 * @param id
	 * @return 用户对象
	 */
	@GetMapping("/doctorpeopleinfo/{id}")
	R<String> getPeopleinfoById(@PathVariable("id") String id);
}
