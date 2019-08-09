package com.kasoft.register.base.utils;

import com.yunpian.sdk.YunpianClient;
import com.yunpian.sdk.model.Result;
import com.yunpian.sdk.model.SmsSingleSend;
import lombok.experimental.UtilityClass;

import java.util.Map;

/**
 * @author kylin
 * @create 2019-08-09 20:40
 */
@UtilityClass
public class SmsUtils {

	/**
	 * 使用SDK发送单条短信,智能匹配短信模板
	 *
	 * @param mobile 患者手机号
	 * @param name 患者姓名
	 * @param time 预约时间
	 * @param hospital 预约医院
	 */
	public void sendApplySuccessSms(String mobile, String name, String time, String hospital) {
		//初始化client,apikey作为所有请求的默认值(可以为空)
		YunpianClient clnt = new YunpianClient("7a9a24894961a2377760f79b44bdf7be").init();
		Map<String, String> param = clnt.newParam(2);
		param.put(YunpianClient.MOBILE, mobile);
		param.put(YunpianClient.TEXT, "【南京擎卡医疗】" + name + "您预约已成功:请您携带身份证于" + time + "到达" + hospital + "，前日请勿饮酒,注意休息.当日晨勿进食,水,药。祝您生活愉快！");
		Result<SmsSingleSend> r = clnt.sms().single_send(param);
		//获取返回结果，返回码:r.getCode(),返回码描述:r.getMsg(),API结果:r.getData(),其他说明:r.getDetail(),调用异常:r.getThrowable()
		r.getData();
		clnt.close();
	}

	public String getPeriodDetail(String period) {
		String periodDetail;
		switch (period){
			case "1":
				periodDetail = "8:00~9:00";
				break;
			case "2":
				periodDetail = "9:00~10:00";
				break;
			case "3":
				periodDetail = "10:00~11:00";
				break;
			case "4":
				periodDetail = "11:00~12:00";
				break;
			case "5":
				periodDetail = "13:00~14:00";
				break;
			case "6":
				periodDetail = "14:00~15:00";
				break;
			case "7":
				periodDetail = "15:00~16:00";
				break;
			case "8":
				periodDetail = "16:00~17:00";
				break;
			default:
				periodDetail = "全天";
				break;
		}
		return periodDetail;
	}
}
