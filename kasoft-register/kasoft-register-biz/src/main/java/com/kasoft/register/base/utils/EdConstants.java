package com.kasoft.register.base.utils;

import cn.hutool.core.lang.TypeReference;
import com.alibaba.fastjson.JSONArray;
import com.kasoft.register.base.entity.DictionariesBean;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * 常量对象
 * @author charlie
 */
@Component
public class EdConstants {
	/**
	 * 地区信息缓存
	 */
	public static final String ED_AREA_DETAILS = "ed_area_details";
	/**
	 * 地区全部信息缓存
	 */
	public static final String ED_AREA_DETAILS_ALL = "ed_area_details:all";
	/**
	 * 地区省信息缓存
	 */
	public static final String ED_AREA_DETAILS_PROVINCE = "ed_area_details:province";
	/**
	 * 地区市信息缓存
	 */
	public static final String ED_AREA_DETAILS_CITY = "ed_area_details:city";
	/**
	 * 地区区信息缓存
	 */
	public static final String ED_AREA_DETAILS_AREA = "ed_area_details:area";
	/**
	 * 街道信息缓存
	 */
	public static final String ED_STREET_DETAILS = "ed_street_details";
	/**
	 * 街道-街道信息缓存
	 */
	public static final String ED_STREET_DETAILS_STREET = "ed_street_details:street";
	/**
	 * 街道-居委会信息缓存
	 */
	public static final String ED_STREET_DETAILS_NEIGHBORHOOD = "ed_street_details:neighborhood";
	/**
	 * 医院信息缓存
	 */
	public static final String ED_HOSPITAL_DETAILS = "ed_hospital_details";
	/**
	 * 医院字典信息缓存
	 */
	public static final String ED_HOSPITAL_DETAILS_DICT = "ed_hospital_details:dict";
	/**
	 * 科室信息缓存
	 */
	public static final String ED_DEPARTMENT_DETAILS = "ed_department_details";
	/**
	 * 数根默认编号
	 */
	public static final String TREE_ROOT_ID = "0";
	/**
	 * 医生默认权限
	 */
	public static final String ROLE_DEFAULT_DOCTOR = "ROLE_DEFAULT_DOCTOR";
	/**
	 * 用户默认权限
	 */
	public static final String ROLE_DEFAULT_USER = "ROLE_DEFAULT_USER";

	public static final String ALL_KEY = "{{key}}";
	/**
	 * 地区类型
	 */
	public interface AreaType {
		/**
		 * 省
		 */
		String PROVINCE = "1";
		/**
		 * 市
		 */
		String CITY = "2";
		/**
		 * 区
		 */
		String AREA = "3";
		/**
		 * 字典列表
		 */
		List<DictionariesBean> dictionariesList= JSONArray.parseObject(
				"[{'label':'省','value':'1'},{'label':'市','value':'2'},{'label':'区','value':'3'}]",
				new TypeReference<ArrayList<DictionariesBean>>() {});
	}

	/**
	 * 街道类型
	 */
	public interface StreetType {
		/**
		 * 街道
		 */
		String STREET = "1";
		/**
		 * 居委会
		 */
		String NEIGHBORHOOD = "2";
		/**
		 * 字典列表
		 */
		List<DictionariesBean> dictionariesList= JSONArray.parseObject(
				"[{'label':'街道','value':'1'},{'label':'居委会','value':'2'}]",
				new TypeReference<ArrayList<DictionariesBean>>() {});
	}

	/**
	 * 协议上传状态
	 */
	public interface ProtocolUpStatus {
		/**
		 * 未上传
		 */
		String NO = "0";
		/**
		 * 已上传
		 */
		String YES = "1";
		/**
		 * 字典列表
		 */
		List<DictionariesBean> dictionariesList= JSONArray.parseObject(
				"[{'label':'未上传','value':'0'},{'label':'已上传','value':'1'}]",
				new TypeReference<ArrayList<DictionariesBean>>() {});
	}

	/**
	 * 协议使用状态
	 */
	public interface ProtocolUseStatus {
		/**
		 * 正常
		 */
		String NORMAL = "1";
		/**
		 * 已续约
		 */
		String RENEWALS = "2";
		/**
		 * 已解约
		 */
		String CANCELLED = "3";
		/**
		 * 字典列表
		 */
		List<DictionariesBean> dictionariesList= JSONArray.parseObject(
				"[{'label':'正常','value':'1'},{'label':'已续约','value':'2'},{'label':'已解约','value':'3'}]",
				new TypeReference<ArrayList<DictionariesBean>>() {});
	}

	/**
	 * 协议当前状态
	 */
	public interface ProtocolWorkStatus {
		/**
		 * 生效中
		 */
		String IN_FORCE = "1";
		/**
		 * 未生效
		 */
		String NO_FORCE = "2";
		/**
		 * 已失效
		 */
		String OUT_FORCE = "3";
		/**
		 * 字典列表
		 */
		List<DictionariesBean> dictionariesList= JSONArray.parseObject(
				"[{'label':'生效中','value':'1'},{'label':'未生效','value':'2'},{'label':'已失效','value':'3'}]",
				new TypeReference<ArrayList<DictionariesBean>>() {});
	}
	/**
	 * 签约类型
	 */
	public interface AgreementType {
		/**
		 * 基础
		 */
		String BASE = "1";
		/**
		 * 个性化
		 */
		String PERSONALIZATION = "2";
		/**
		 * 字典列表
		 */
		List<DictionariesBean> dictionariesList= JSONArray.parseObject(
				"[{'label':'基础','value':'1'},{'label':'个性化','value':'2'}]",
				new TypeReference<ArrayList<DictionariesBean>>() {});
	}
	/**
	 * 性别类型
	 */
	public  interface SexType {
		/**
		 * 男
		 */
		String MAN = "1";
		/**
		 * 女
		 */
		String WOMAN = "2";
		/**
		 * 未说明
		 */
		String UNKNOWN = "3";
		/**
		 * 字典列表
		 */
		List<DictionariesBean> dictionariesList= JSONArray.parseObject(
				"[{'label':'男','value':'1'},{'label':'女','value':'2'},{'label':'未说明','value':'3'}]",
				new TypeReference<ArrayList<DictionariesBean>>() {});
	}

	/**
	 * 医生人员类别
	 */
	public interface  DoctorType {
		/**
		 * 医生
		 */
		String DOCTOR = "1";
		/**
		 * 护士
		 */
		String nurse = "2";
		/**
		 * 健康专员
		 */
		String HEALTH_COMMISSIONER = "3";
		/**
		 * 字典列表
		 */
		List<DictionariesBean> dictionariesList= JSONArray.parseObject(
				"[{'label':'医生','value':'1'},{'label':'护士','value':'2'},{'label':'健康专员','value':'3'}]",
				new TypeReference<ArrayList<DictionariesBean>>() {});
	}

	/**
	 * 是否
	 */
	public interface  YesNo {
		/**
		 * 是
		 */
		String YES = "0";
		/**
		 * 否
		 */
		String NO = "1";
		/**
		 * 字典列表
		 */
		List<DictionariesBean> dictionariesList= JSONArray.parseObject(
				"[{'label':'是','value':'0'},{'label':'否','value':'1'}]",
				new TypeReference<ArrayList<DictionariesBean>>() {});
	}

	/**
	 * 有无
	 */
	public interface  HaveNo {
		/**
		 * 无
		 */
		String NO = "0";
		/**
		 * 有
		 */
		String HAVE = "1";
		/**
		 * 字典列表
		 */
		List<DictionariesBean> dictionariesList= JSONArray.parseObject(
				"[{'label':'无','value':'0'},{'label':'有','value':'1'}]",
				new TypeReference<ArrayList<DictionariesBean>>() {});
	}

	/**
	 * 人员状态
	 */
	public interface PeopleState {
		/**
		 * 在管
		 */
		String MANAGEMENT = "1";
		/**
		 * 死亡
		 */
		String DEATH = "2";
		/**
		 * 迁出
		 */
		String MOVE_OUT = "3";
		/**
		 * 失访
		 */
		String LOSS_FOLLOW = "4";
		/**
		 * 拒绝
		 */
		String REFUSE = "5";
		/**
		 * 字典列表
		 */
		List<DictionariesBean> dictionariesList= JSONArray.parseObject(
				"[{'label':'在管','value':'1'},{'label':'死亡','value':'2'},{'label':'迁出','value':'3'},{'label':'失访','value':'4'},{'label':'拒绝','value':'5'}]",
				new TypeReference<ArrayList<DictionariesBean>>() {});
	}
	/**
	 * 异常状态
	 */
	public interface AbnormalState {
		/**
		 * 正常
		 */
		String NORMAL = "1";
		/**
		 * 异常
		 */
		String ERROR = "2";
		/**
		 * 字典列表
		 */
		List<DictionariesBean> dictionariesList= JSONArray.parseObject(
				"[{'label':'正常','value':'1'},{'label':'异常','value':'2'}]",
				new TypeReference<ArrayList<DictionariesBean>>() {});
	}

	/**
	 * 签约类型
	 */
	public interface SigningType {
		/**
		 * 项目
		 */
		String PROJECT = "1";
		/**
		 * 套餐
		 */
		String PACKAGER = "2";
		/**
		 * 字典列表
		 */
		List<DictionariesBean> dictionariesList= JSONArray.parseObject(
				"[{'label':'项目','value':'1'},{'label':'套餐','value':'2'}]",
				new TypeReference<ArrayList<DictionariesBean>>() {});
	}

	/**
	 * 反馈类型
	 */
	public interface FeedbackType {
		/**
		 * 软件问题
		 */
		String PROJECT = "1";
		/**
		 * 投诉
		 */
		String PACKAGER = "2";
		/**
		 * 字典列表
		 */
		List<DictionariesBean> dictionariesList= JSONArray.parseObject(
				"[{'label':'软件问题','value':'1'},{'label':'投诉','value':'2'}]",
				new TypeReference<ArrayList<DictionariesBean>>() {});
	}

	/**
	 * 处理状态
	 */
	public interface HandleState {
		/**
		 * 未处理
		 */
		String NO = "0";
		/**
		 * 已处理
		 */
		String YES = "1";
		/**
		 * 字典列表
		 */
		List<DictionariesBean> dictionariesList= JSONArray.parseObject(
				"[{'label':'未处理','value':'0'},{'label':'已处理','value':'1'}]",
				new TypeReference<ArrayList<DictionariesBean>>() {});
	}

	/**
	 * 项目类型
	 */
	public interface ProjectType {
		/**
		 * 公卫服务
		 */
		String PUBLIC_HEALTH = "1";
		/**
		 * 医疗服务
		 */
		String MEDICAL_TREATMENT = "2";
		/**
		 * 字典列表
		 */
		List<DictionariesBean> dictionariesList= JSONArray.parseObject(
				"[{'label':'公卫服务','value':'1'},{'label':'医疗服务','value':'2'}]",
				new TypeReference<ArrayList<DictionariesBean>>() {});
	}


	/**
	 * 项目状态
	 */
	public interface ProjectState {
		/**
		 * 停用
		 */
		String stop = "0";
		/**
		 * 启用
		 */
		String start = "1";
		/**
		 * 字典列表
		 */
		List<DictionariesBean> dictionariesList= JSONArray.parseObject(
				"[{'label':'停用','value':'0'},{'label':'启用','value':'1'}]",
				new TypeReference<ArrayList<DictionariesBean>>() {});
	}

	/**
	 * 审核状态（0未复核 1复核）
	 */
	public interface ReviewState {
		/**
		 * 未审核
		 */
		String NO = "0";
		/**
		 * 已审核
		 */
		String YES = "1";
		/**
		 * 字典列表
		 */
		List<DictionariesBean> dictionariesList= JSONArray.parseObject(
				"[{'label':'未审核','value':'0'},{'label':'已审核','value':'1'}]",
				new TypeReference<ArrayList<DictionariesBean>>() {});
	}

	/**
	 * 体检指标状态
	 */
	public interface IndicatorsType {
		/**
		 * 特高
		 */
		String ET_00 = "et_00";
		/**
		 * 升高
		 */
		String ET_01 = "et_01";
		/**
		 * 降低
		 */
		String ET_02 = "et_02";
		/**
		 * 正常
		 */
		String ET_03 = "et_03";
		/**
		 * 阴性
		 */
		String ET_04 = "et_04";
		/**
		 * 阳性
		 */
		String ET_05 = "et_05";
	}

	/**
	 * 血糖类型
	 */
	public interface GluType {
		/**
		 * 餐前
		 */
		Integer XT_CANQIAN = 0;

		/**
		 * 随机
		 */
		Integer XT_CANHOU_1 = 1;

		/**
		 * 餐后2小时
		 */
		Integer XT_CANHOU_2 = 2;
	}

	/**
	 * 血压-收缩压
	 */
	public interface SBP {
		String key = "SBP";
		String value = "收缩压";
		String range = "90-140mmHg";
		String unit = "mmHg";
	}
	/**
	 * 血压-舒张压
	 */
	public interface DBP {
		String key = "DBP";
		String value = "舒张压";
		String range = "60-90mmHg";
		String unit = "mmHg";
	}
	/**
	 * 血压-脉搏
	 */
	public interface HR {
		String key = "HR";
		String value = "脉搏";
		String range = "60-100次/分";
		String unit = "次/分";
	}

	/**
	 * 血糖
	 */
	public interface GLU {
		String key = "GLU";
		String value = "血糖";
		String range_0 = "3.9-6.1mmol/L";
		String range_1 = "3.9-7.8mmol/L";
		String range_2 = "3.9-11.1mmol/L";
		String unit = "mmol/L";
	}

	/**
	 * 用户类型
	 */
	public interface UserType {
		/**
		 * 随机
		 */
		String PEOPLE = "0";

		/**
		 * 餐前
		 */
		String DOCKER = "1";
	}
}
