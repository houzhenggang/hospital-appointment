package com.kasoft.register.base;

import com.pig4cloud.pigx.common.security.annotation.EnablePigxResourceServer;
import com.pig4cloud.pigx.common.security.annotation.EnablePigxFeignClients;
import com.pig4cloud.pigx.common.swagger.annotation.EnablePigxSwagger2;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;

/**
 * @author lengleng
 * @date 2018/07/29
 *
 */
@EnablePigxSwagger2
@SpringCloudApplication
@EnablePigxFeignClients
@EnablePigxResourceServer
public class KasoftRegisterApplication {

	public static void main(String[] args) {
		SpringApplication.run(KasoftRegisterApplication.class, args);
	}
}
