package com.kasoft.register.base.config;

import com.yunpian.sdk.YunpianClient;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * @author kylin
 * @create 2019-08-15 11:01
 */
@Configuration
@AllArgsConstructor
public class YunpianConfiguration {

	private final YunpianPropertiesConfig yunpianPropertiesConfig;

	@Bean
	public YunpianClient yunpianClient() {
		String appKey = yunpianPropertiesConfig.getApikey();
		return new YunpianClient(appKey).init();
	}
}
