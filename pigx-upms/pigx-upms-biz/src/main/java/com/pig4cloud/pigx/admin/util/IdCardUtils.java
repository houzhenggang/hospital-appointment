package com.pig4cloud.pigx.admin.util;

import cn.hutool.core.codec.Base64;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

/**
 * 身份证识别工具类
 * @author charlie
 */
@Slf4j
public class IdCardUtils {
	/**
	 * 身份证解析对象
	 * @author charlie
	 */
	@Data
	@ToString
	@ApiModel(description ="身份证解析对象")
	public static class IdCardBean {
		/**
		 * 姓名
		 */
		@ApiModelProperty(value = "姓名")
		private String name;
		/**
		 * 民族
		 */
		@ApiModelProperty(value = "民族")
		private String nationality;
		/**
		 * 身份证号
		 */
		@ApiModelProperty(value = "身份证号")
		private String num;
		/**
		 * 性别
		 */
		@ApiModelProperty(value = "性别")
		private String sex;
		/**
		 * 出生日期
		 */
		@ApiModelProperty(value = "出生日期")
		private String birth;
		/**
		 * 识别结果，true表示成功，false表示失败
		 */
		@ApiModelProperty(value = "识别结果，true表示成功，false表示失败")
		private boolean success;
		/**
		 * 地址信息
		 */
		@ApiModelProperty(value = "地址信息")
		private String address;
		/**
		 * 配置信息，正面还是反面
		 */
		@ApiModelProperty(value = "配置信息，正面还是反面")
		private String config_str;
		/**
		 * 有效期起始时间
		 */
		@ApiModelProperty(value = "有效期起始时间")
		private String start_date;
		/**
		 * 有效期结束时间
		 */
		@ApiModelProperty(value = "有效期结束时间")
		private String end_date;
		/**
		 * 签发机关
		 */
		@ApiModelProperty(value = "签发机关")
		private String issue;
	}
	/**
	 * 获取参数的json对象
	 * @param type
	 * @param dataValue
	 * @return
	 */
	public static JSONObject getParam(int type, String dataValue) {
		JSONObject obj = new JSONObject();
		try {
			obj.put("dataType", type);
			obj.put("dataValue", dataValue);
		} catch (JSONException e) {
			log.error(e.getMessage(), e);
		}
		return obj;
	}

	/**
	 * 调用阿里云的身份证解析服务
	 * @param bytes 图片数组
	 * @param appcode 授权吗
	 * @return
	 */
	public static IdCardBean paseIdCard(byte[] bytes, String appcode){
		if(ObjectUtil.isNull(bytes)) {
			return null;
		}
		String host = "http://dm-51.data.aliyun.com";
		String path = "/rest/160601/ocr/ocr_idcard.json";
		//如果文档的输入中含有inputs字段，设置为True， 否则设置为False
		Boolean is_old_format = false;
		//请根据线上文档修改configure字段
		JSONObject configObj = new JSONObject();
		configObj.put("side", "face");
		String config_str = configObj.toString();

		// 对图像进行base64编码
		String imgBase64 = Base64.encode(bytes);
		// 拼装请求body的json字符串
		JSONObject requestObj = new JSONObject();
		try {
			if(is_old_format) {
				JSONObject obj = new JSONObject();
				obj.put("image", getParam(50, imgBase64));
				if(config_str.length() > 0) {
					obj.put("configure", getParam(50, config_str));
				}
				JSONArray inputArray = new JSONArray();
				inputArray.add(obj);
				requestObj.put("inputs", inputArray);
			}else{
				requestObj.put("image", imgBase64);
				if(config_str.length() > 0) {
					requestObj.put("configure", config_str);
				}
			}
		} catch (JSONException e) {
			log.error(e.getMessage(), e);
		}
		String bodys = requestObj.toString();

		try {

			//链式构建请求
			HttpResponse httpResponse = HttpRequest.post(host + path)
					//头信息，多个头信息多次调用此方法即可
					.header("Authorization", "APPCODE " + appcode)
					//表单内容
					.body(bodys)
					//超时，毫秒
					.timeout(20000)
					.execute();
			int stat = httpResponse.getStatus();
			if(stat != 200){
				log.warn("身份证接口调用失败" + stat);
				return null;
			}

			String res = httpResponse.body();
			JSONObject res_obj = JSON.parseObject(res);
			IdCardBean bean;
			if(is_old_format) {
				JSONArray outputArray = res_obj.getJSONArray("outputs");
				String output = outputArray.getJSONObject(0).getJSONObject("outputValue").getString("dataValue");
				bean = JSON.parseObject(output, IdCardBean.class);
			}else{
				bean = res_obj.toJavaObject(IdCardBean.class);
			}
			return bean;
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return null;
	}
}

