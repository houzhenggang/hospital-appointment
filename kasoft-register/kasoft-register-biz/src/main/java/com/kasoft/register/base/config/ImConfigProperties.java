package com.kasoft.register.base.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;

/**
 * im配置
 *
 * @author charlie
 */
@Data
@RefreshScope
@Configuration
@ConfigurationProperties(prefix = "im")
public class ImConfigProperties {

	/**
	 * 管理员身份
	 */
	private String identifier;
	/**
	 * 私钥
	 */
	private String privateKey;
	/**
	 * 公钥
	 */
	private String publicKey;
	/**
	 * app编号
	 */
	private String sdkAppId;
}
