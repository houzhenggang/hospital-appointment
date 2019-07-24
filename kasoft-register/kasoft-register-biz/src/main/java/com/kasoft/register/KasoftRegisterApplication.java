package com.kasoft.register;

import com.pig4cloud.pigx.common.security.annotation.EnablePigxFeignClients;
import com.pig4cloud.pigx.common.security.annotation.EnablePigxResourceServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.SpringCloudApplication;

/**
 * <p>
 * 预约挂号服务启动类
 * </p>
 *
 * @author kylin
 * @since 2019/7/24 16:56
 */
@EnablePigxFeignClients
@SpringCloudApplication
//@EnablePigxResourceServer
public class KasoftRegisterApplication {

	public static void main(String[] args) {
		SpringApplication.run(KasoftRegisterApplication.class, args);
	}

}
