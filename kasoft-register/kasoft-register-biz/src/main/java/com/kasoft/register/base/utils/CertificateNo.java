package com.kasoft.register.base.utils;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.regex.Pattern;

/**
 * 身份证号码校验
 * @author charlie
 */
public class CertificateNo {

	/**
	 * 身份证校验后对象
	 */
	@ApiModel(description ="身份证校验后对象")
	@Data
	public static class CertificateBean {
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
		 * 是否校验通过
		 */
		@ApiModelProperty(value = "是否校验通过")
		private boolean isValidCard;
	}

	/**
	 * 解析身份证
	 * @param certificateNo 身份证号码
	 * @return
	 */
	public static CertificateBean parseCertificateNo(String certificateNo) {

		CertificateBean resultDTO = new CertificateBean();
		String myRegExpIDCardNo = "^\\d{6}(((19|20)\\d{2}(0[1-9]|1[0-2])(0[1-9]|[1-2][0-9]|3[0-1])\\d{3}([0-9]|x|X))|(\\d{2}(0[1-9]|1[0-2])(0[1-9]|[1-2][0-9]|3[0-1])\\d{3}))$";
		boolean valid = Pattern.matches(myRegExpIDCardNo, certificateNo) || (certificateNo.length() == 17 && Pattern.matches(myRegExpIDCardNo, certificateNo.substring(0, 15)));
		if (!valid) {
			resultDTO.setValidCard(false);
			return resultDTO;
		}
		int idxSexStart = 16;
		int birthYearSpan = 4;
		//如果是15位的证件号码
		if (certificateNo.length() == 15) {
			idxSexStart = 14;
			birthYearSpan = 2;
		}

		//性别
		String idxSexStr = certificateNo.substring(idxSexStart, idxSexStart + 1);
		int idxSex = Integer.parseInt(idxSexStr) % 2;
		String sex = (idxSex == 1) ? "1" : "2";
		resultDTO.setSex(sex);

		//出生日期
		String year = (birthYearSpan == 2 ? "19" : "") + certificateNo.substring(6, 6 + birthYearSpan);
		String month = certificateNo.substring(6 + birthYearSpan, 6 + birthYearSpan + 2);
		String day = certificateNo.substring(8 + birthYearSpan, 8 + birthYearSpan + 2);
		String birthday = year + '-' + month + '-' + day;
		resultDTO.setBirth(birthday);
		resultDTO.setNum(certificateNo);
		resultDTO.setValidCard(true);
		return resultDTO;
	}
}