package com.kasoft.register.base.utils;

import com.yunpian.sdk.YunpianClient;
import com.yunpian.sdk.model.Result;
import com.yunpian.sdk.model.SmsSingleSend;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

/**
 * <p>
 * 发送短息提示工具类
 * </p>
 *
 * @author kylin
 * @since 2019/8/15 15:48
 */
@UtilityClass
public class SmsUtils {

	/**
	 * 短信提醒
	 *
	 * @param mobile   患者手机号
	 * @param text     发送内容
	 */
	public void sendSms(YunpianClient yunpianClient, String mobile, String text) {
		Map<String, String> param = yunpianClient.newParam(2);
		param.put(YunpianClient.MOBILE, mobile);
		param.put(YunpianClient.TEXT, text);
		Result<SmsSingleSend> r = yunpianClient.sms().single_send(param);
		System.out.println(r.getData());
	}
}
