package com.kasoft.register.base.dto;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.kasoft.register.base.entity.DoctorHypertensionform;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Arrays;
import java.util.List;

/**
 * 高血压表单DTO
 *
 * @author charlie
 */
@Data
@ApiModel(description = "高血压表单DTO")
public class DoctorHypertensionformDTO extends DoctorHypertensionform {
	/**
	 * 症状列表
	 */
	@ApiModelProperty(value = "症状列表")
	private List<String> symptomsList;
	/**
	 * 其他症状
	 */
	@ApiModelProperty(value = "其他症状列表")
	private List<String> otherSymptomsList;

	/**
	 * 其他体征列表
	 */
	@ApiModelProperty(value = "其他体征列表")
	private List<String> otherSignsList;

	/**
	 * 当前项目服务次数
	 */
	@ApiModelProperty(value = "当前项目服务次数")
	private Integer nowServiceFrequency;

	@Override
	public void clearNoUseDTO() {
		super.clearNoUseDTO();
	}

	/**
	 * 实体bean转DTO对象
	 */
	public void entityToDTO() {
		if(StrUtil.isNotEmpty(this.getSymptoms())) {
			this.setSymptomsList(Arrays.asList(this.getSymptoms().split(",")));
		}
		if(StrUtil.isNotEmpty(this.getOtherSymptoms())) {
			this.setOtherSymptomsList(Arrays.asList(this.getOtherSymptoms().split(",")));
		}
		if(StrUtil.isNotEmpty(this.getOtherSigns())) {
			this.setOtherSignsList(Arrays.asList(this.getOtherSigns().split(",")));
		}
	}

	/**
	 * dto转实体bean
	 */
	public void dtoToEntity() {
		if(CollUtil.isNotEmpty(this.getSymptomsList())) {
			this.setSymptoms(CollUtil.join(this.getSymptomsList(), ","));
		}
		if(CollUtil.isNotEmpty(this.getOtherSymptomsList())) {
			this.setOtherSymptoms(CollUtil.join(this.getOtherSymptomsList(), ","));
		}
		if(CollUtil.isNotEmpty(this.getOtherSignsList())) {
			this.setOtherSigns(CollUtil.join(this.getOtherSignsList(), ","));
		}

	}
}
