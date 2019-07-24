/*
 *    Copyright (c) 2018-2025, lengleng All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice,
 * this list of conditions and the following disclaimer.
 * Redistributions in binary form must reproduce the above copyright
 * notice, this list of conditions and the following disclaimer in the
 * documentation and/or other materials provided with the distribution.
 * Neither the name of the pig4cloud.com developer nor the names of its
 * contributors may be used to endorse or promote products derived from
 * this software without specific prior written permission.
 * Author: lengleng (wangiegie@gmail.com)
 */

package com.kasoft.register.base.controller;

import cn.hutool.core.util.ReflectUtil;
import com.kasoft.register.base.utils.CertificateNo;
import com.pig4cloud.pigx.common.core.util.R;
import com.kasoft.register.base.entity.DictionariesBean;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 字典工具接口
 *
 * @author charlie
 * @date 2019-05-07 15:43:03
 */
@RestController
@AllArgsConstructor
@RequestMapping("/dictionariesutils" )
@Api(value = "dictionariesutils", tags = "字典工具")
@Slf4j
public class DictionariesUtilsController {


	/**
	 * 通过字典类型查找字典
	 *
	 * @param type 类型(字典接口名称)
	 * @return 同类型字典
	 */
	@GetMapping("/type/{type}")
	@ApiOperation(value="根据类型获取字典", notes="参数：AreaType 地区类型；StreetType 街道类型；ProtocolUpStatus 协议上传状态；" +
			"ProtocolUseStatus 协议使用状态; ProtocolWorkStatus 协议当前状态; AgreementType 签约类型 ； SexType 性别类型；" +
			"DoctorType 医生人员类别；YesNo 是否; HaveNo 有无; PeopleState 人员状态; AbnormalState 异常状态" +
			"SigningType 签约类型; FeedbackType 反馈类型; HandleState 处理状态;ProjectType  项目类型；" +
			"ProjectState 项目状态；ReviewState 审核状态；")
	public R getDictByType(@PathVariable String type) {
		List<DictionariesBean> list = new ArrayList<>();
		try {
			list = (List<DictionariesBean>)ReflectUtil.getField(Class.forName("com.twtsoft.health.base.utils.EdConstants$" + type),
					"dictionariesList").get(null);
		} catch (Exception e) {
			log.error("静态接口获取失败：" + type);
		}
		return new R<>(list);
	}

	/**
	 * 查找全部字典
	 *
	 * @return 全部字典
	 */
	@GetMapping("/type-all")
	@ApiOperation(value="查找全部字典", notes="查找全部字典")
	public R getDictApp() {
		Map<String, List<DictionariesBean>> map = new HashMap<>(100);
		map.put("AreaType", null);
		map.put("StreetType", null);
		map.put("ProtocolUpStatus", null);
		map.put("ProtocolUseStatus", null);
		map.put("ProtocolWorkStatus", null);
		map.put("AgreementType", null);
		map.put("SexType", null);
		map.put("DoctorType", null);
		map.put("YesNo", null);
		map.put("HaveNo", null);
		map.put("PeopleState", null);
		map.put("AbnormalState", null);
		map.put("SigningType", null);
		map.put("FeedbackType", null);
		map.put("HandleState", null);
		map.put("ProjectType", null);
		map.put("ProjectState", null);
		map.put("ReviewState", null);
		map.forEach((k,v)->{
			List<DictionariesBean> list;
			try {
				list = (List<DictionariesBean>)ReflectUtil.getField(Class.forName("com.twtsoft.health.base.utils.EdConstants$" + k),
						"dictionariesList").get(null);
				map.put(k, list);
			} catch (Exception e) {
				log.error("静态全部接口获取失败");
			}
		});
		return new R<>(map);
	}

	/**
	 * 身份证号校验
	 *
	 * @return 身份证校验后对象
	 */
	@GetMapping("/idCare/{idCare}")
	@ApiOperation(value="身份证号校验", notes="身份证号校验")
	public R getDictApp(String idCare) {
		CertificateNo.CertificateBean bean = CertificateNo.parseCertificateNo(idCare);
		return new R<>(bean);
	}
}
