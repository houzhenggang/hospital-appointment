package com.kasoft.register.base.config;

import lombok.Data;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;

/**
 * @author kylin
 * @create 2019-08-15 11:00
 */
@Data
@Configuration
@RefreshScope
@ConditionalOnExpression("!'${yunpian}'.isEmpty()")
@ConfigurationProperties(prefix = "yunpian")
public class YunpianPropertiesConfig {

	private String apikey;

	private String signature;
}
