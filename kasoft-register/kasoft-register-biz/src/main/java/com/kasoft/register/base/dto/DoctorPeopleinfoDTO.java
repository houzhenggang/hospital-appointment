package com.kasoft.register.base.dto;

import com.kasoft.register.base.entity.ArchiveNameTimeBean;
import com.kasoft.register.base.entity.DoctorPeopleinfo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.data.annotation.Id;

import java.util.List;

/**
 * 居民健康档案页面对应类
 * 整合mysql中的peopleinfo表 和 mongo中的档案表
 *
 * @author charlie
 */
@Data
@ApiModel(description = "居民健康档案DTO")
public class DoctorPeopleinfoDTO extends DoctorPeopleinfo {
	/**
	 * 居民编号
	 */
	@Id
	private String peopleId;
	//基础信息
	/**
	 * 现住址地区
	 */
	@ApiModelProperty(value = "现住址地区")
	private String addressAreaId;
	/**
	 * 现住址居委会
	 */
	@ApiModelProperty(value = "现住址居委会")
	private String addressStreet;
	/**
	 * 现住址门牌号
	 */
	@ApiModelProperty(value = "现住址门牌号")
	private String addressCommunity;
	/**
	 * 户籍地址地区
	 */
	@ApiModelProperty(value = "户籍地址地区")
	private String householdAreaId;
	/**
	 * 户籍地址居委会
	 */
	@ApiModelProperty(value = "户籍地址居委会")
	private String householdStreet;
	/**
	 * 户籍地址门牌号
	 */
	@ApiModelProperty(value = "户籍地址门牌号")
	private String householdCommunity;
	/**
	 * 工作单位
	 */
	@ApiModelProperty(value = "工作单位")
	private String workUnit;
	/**
	 * 常住类型-字典
	 */
	@ApiModelProperty(value = "常住类型-字典")
	private String householdTtype;
	/**
	 * 民族-字典
	 */
	@ApiModelProperty(value = "民族-字典")
	private String national;
	/**
	 * 血型-字典
	 */
	@ApiModelProperty(value = "血型-字典")
	private String bloodType;
	/**
	 * RH阴性-字典
	 */
	@ApiModelProperty(value = "RH阴性-字典")
	private String rhNegative;
	/**
	 * 文化程度-字典
	 */
	@ApiModelProperty(value = "文化程度-字典")
	private String educationLevel;
	/**
	 * 职业-字典
	 */
	@ApiModelProperty(value = "职业-字典")
	private String professional;
	/**
	 * 婚姻状况-字典
	 */
	@ApiModelProperty(value = "婚姻状况-字典")
	private String maritalStatus;
	/**
	 * 医疗费用支付方式-字典
	 */
	@ApiModelProperty(value = "医疗费用支付方式-字典")
	private String paymentMethod;
	/**
	 * 其他医疗费用支付方式-字典
	 */
	@ApiModelProperty(value = "其他医疗费用支付方式-字典")
	private String otherPaymentMethod;
	//过敏暴露输出对象
	/**
	 * 药物过敏史 有无字段 0：无；1：有
	 */
	@ApiModelProperty(value = "药物过敏史 有无字段 0：无；1：有")
	private String isDrugAllergy;
	/**
	 * 药物过敏史
	 */
	@ApiModelProperty(value = "药物过敏史")
	private String drugAllergy;
	/**
	 * 其他
	 */
	@ApiModelProperty(value = "其他")
	private String otherAllergy;
	/**
	 * 暴露史 有无字段 0：无；1：有
	 */
	@ApiModelProperty(value = "暴露史 有无字段 0：无；1：有")
	private String isExposed;
	/**
	 * 暴露史
	 */
	@ApiModelProperty(value = "暴露史")
	private String exposed;


	//既往史输出对象
	/**
	 * 疾病 有无字段 0：无；1：有
	 */
	@ApiModelProperty(value = "疾病 有无字段 0：无；1：有")
	private String disease;
	/**
	 * 疾病确诊 名称,时间;名称,时间;(名称是字典)
	 */
	@ApiModelProperty(value = "疾病确诊 名称,时间;名称,时间;(名称是字典)")
	private String diseaseTime;
	/**
	 * 疾病恶性肿瘤文本串列表  信息是 名称 时间
	 */
	@ApiModelProperty(value = "疾病恶性肿瘤文本串列表  信息是 名称 时间")
	private List<ArchiveNameTimeBean> malignantTumorList;
	/**
	 * 疾病职业病文本串列表  信息是 名称 时间
	 */
	@ApiModelProperty(value = "疾病职业病文本串列表  信息是 名称 时间")
	private List<ArchiveNameTimeBean> occupationalDiseaseList;
	/**
	 * 疾病其他文本串
	 */
	@ApiModelProperty(value = "疾病其他文本串")
	private String[] otherDiseaseArray;
	/**
	 * 手术有无字段 0：无；1：有
	 */
	@ApiModelProperty(value = "手术有无字段 0：无；1：有")
	private String surgery;
	/**
	 * 手术文本串列表  信息是 名称 时间
	 */
	@ApiModelProperty(value = "手术文本串列表  信息是 名称 时间")
	private List<ArchiveNameTimeBean> surgeryContentList;
	/**
	 * 外伤有无字段 0：无；1：有
	 */
	@ApiModelProperty(value = "外伤有无字段 0：无；1：有")
	private String trauma;
	/**
	 * 外伤本串列表  信息是 名称 时间
	 */
	@ApiModelProperty(value = "外伤本串列表  信息是 名称 时间")
	private List<ArchiveNameTimeBean> traumaContentList;
	/**
	 * 输血有无字段 0：无；1：有
	 */
	@ApiModelProperty(value = "输血有无字段 0：无；1：有")
	private String blood;
	/**
	 * 输血串列表  信息是 原因 时间
	 */
	@ApiModelProperty(value = "输血串列表  信息是 原因 时间")
	private List<ArchiveNameTimeBean> bloodContentList;

	//家族史输出对象
	/**
	 * 父亲 疾病 有无字段 0：无；1：有
	 */
	@ApiModelProperty(value = "父亲 疾病 有无字段 0：无；1：有")
	private String isFatherDisease;
	/**
	 * 父亲 疾病
	 */
	@ApiModelProperty(value = "父亲 疾病")
	private String[] fatherDiseaseArray;
	/**
	 * 父亲 其他字符串
	 */
	@ApiModelProperty(value = "父亲 其他字符串")
	private String fatherOtherDisease;
	/**
	 * 母亲 疾病 有无字段 0：无；1：有
	 */
	@ApiModelProperty(value = "母亲 疾病 有无字段 0：无；1：有")
	private String isMotherDisease;
	/**
	 * 母亲 疾病
	 */
	@ApiModelProperty(value = "母亲 疾病")
	private String[] motherDiseaseArray;
	/**
	 * 母亲 其他字符串
	 */
	@ApiModelProperty(value = "母亲 其他字符串")
	private String motherOtherDisease;
	/**
	 * 兄弟姐妹 有无字段 0：无；1：有
	 */
	@ApiModelProperty(value = "兄弟姐妹 有无字段 0：无；1：有")
	private String isBasDisease;
	/**
	 * 兄弟姐妹 疾病
	 */
	@ApiModelProperty(value = "兄弟姐妹 疾病")
	private String[] basDiseaseArray;
	/**
	 * 兄弟姐妹 其他字符串
	 */
	@ApiModelProperty(value = "兄弟姐妹 其他字符串")
	private String basOtherDisease;
	/**
	 * 子女 疾病 有无字段 0：无；1：有
	 */
	@ApiModelProperty(value = "子女 疾病 有无字段 0：无；1：有")
	private String isChildrenDisease;
	/**
	 * 子女 疾病
	 */
	@ApiModelProperty(value = "子女 疾病")
	private String[] childrenDiseaseArray;
	/**
	 * 子女 其他字符串
	 */
	@ApiModelProperty(value = "子女 其他字符串")
	private String childrenOtherDisease;

	//遗传残疾输出对象
	/**
	 * 遗传病史 0：无；1：有
	 */
	@ApiModelProperty(value = "遗传病史 0：无；1：有")
	private String genetic;
	/**
	 * 遗传病史 名称
	 */
	@ApiModelProperty(value = "遗传病史 名称")
	private String geneticContent;
	/**
	 * 残疾情况 0：无；1：有
	 */
	@ApiModelProperty(value = "残疾情况 0：无；1：有")
	private String disability;
	/**
	 * 残疾情况
	 */
	@ApiModelProperty(value = "残疾情况")
	private String[] disabilityContentArray;
	/**
	 * 残疾情况 其他字符串
	 */
	@ApiModelProperty(value = "残疾情况 其他字符串")
	private String otherDisability;


	//生活环境
	/**
	 * 厨房排风设施
	 */
	@ApiModelProperty(value = "厨房排风设施")
	private String kitchenExhaust;
	/**
	 * 燃料类型
	 */
	@ApiModelProperty(value = "燃料类型")
	private String kitchenFuel;
	/**
	 * 饮水
	 */
	@ApiModelProperty(value = "饮水")
	private String drinkingWater;
	/**
	 * 厕所
	 */
	@ApiModelProperty(value = "厕所")
	private String toilet;
	/**
	 * 禽畜栏
	 */
	@ApiModelProperty(value = "禽畜栏")
	private String livestockBar;

	//并发症输出对象
	/**
	 * 并发症输 疾病
	 */
	@ApiModelProperty(value = "并发症输 疾病")
	private String[] complicationsArray;


	//生活方式输出对象
	/**
	 * 吸烟情况 单选择字典
	 */
	@ApiModelProperty(value = "吸烟情况 单选择字典")
	private String smokingStatus;
	/**
	 * 饮酒情况 单选择字典
	 */
	@ApiModelProperty(value = "饮酒情况 单选择字典")
	private String drinkingStatus;
	/**
	 * 体育锻炼 单选择字典
	 */
	@ApiModelProperty(value = "体育锻炼 单选择字典")
	private String exercise;
	/**
	 * 饮食习惯 单选择字典
	 */
	@ApiModelProperty(value = "饮食习惯 单选择字典")
	private String eatingHabits;


	//生理指标输出对象
	/**
	 * 身高
	 */
	@ApiModelProperty(value = "身高")
	private Double height;
	/**
	 * 体重
	 */
	@ApiModelProperty(value = "体重")
	private Double weight;
	/**
	 * BMI
	 */
	@ApiModelProperty(value = "BMI")
	private Double bmi;
	/**
	 * BMI分析
	 */
	@ApiModelProperty(value = "BMI分析")
	private String bmiAnalysis;
	/**
	 * 腰围
	 */
	@ApiModelProperty(value = "腰围")
	private Double waist;
	/**
	 * 臀围
	 */
	@ApiModelProperty(value = "臀围")
	private Double hip;
	/**
	 * 血压值-收缩压
	 */
	@ApiModelProperty(value = "血压值-收缩压")
	private Integer systolic;
	/**
	 * 血压值-舒张压
	 */
	@ApiModelProperty(value = "血压值-舒张压")
	private Integer diastolic;
	/**
	 * 空腹血糖
	 */
	@ApiModelProperty(value = "空腹血糖")
	private Double bloodSugar;
	/**
	 * 人群分类数组
	 */
	@ApiModelProperty(value = "人群分类数组")
	private String[] peopleTypeListArray;
	@Override
	public void clearNoUseDTO() {
		super.clearNoUseDTO();
	}

}
