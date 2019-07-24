package com.kasoft.register.base.init;

import com.kasoft.register.base.config.ImConfigProperties;
import com.wakeup.qcloud.DefaultQCloudClient;
import com.wakeup.qcloud.domain.IMConfigDO;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 * api初始化
 * @author charlie
 */
@Slf4j
@Component
public class TimRunner implements ApplicationRunner {
	@Getter
	private static DefaultQCloudClient qcloudClient;
	@Autowired
	private ImConfigProperties imConfigProperties;
    @Override
    public void run(ApplicationArguments applicationArguments) {
        log.info("IM初始化");
		qcloudClient = initQCloudClient();
		log.info("IM初始化完成");
    }

	/**
	 * 初始化
	 * @return
	 */
	private DefaultQCloudClient initQCloudClient(){
		IMConfigDO imConfig = new IMConfigDO();
		imConfig.setIdentifier(imConfigProperties.getIdentifier());
		imConfig.setPrivateKey(imConfigProperties.getPrivateKey());
		imConfig.setPublicKey(imConfigProperties.getPublicKey());
		imConfig.setSdkAppId(imConfigProperties.getSdkAppId());
		return new DefaultQCloudClient(imConfig);
	}

}