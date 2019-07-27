package com.kasoft.register.base.utils;

import cn.hutool.core.util.StrUtil;

/**
 * 体检指标工具类
 * @author charlie
 */
public class PhysicalIndicatorsUtils {

    /**
     * 判断血压异常
     *
     * @param num 数
     * @param type
     * @return
     */
    public static String judgeBloodPressure(String num, String type) {
        if (num == null) {
            return null;
        }
        Integer number = Integer.valueOf(num);
        String exceptionType;
        switch (type) {
            case KrbConstants.SBP.key:
                if (number > 140) {
                    exceptionType = KrbConstants.IndicatorsType.ET_01;
                } else if (number < 90) {
                    exceptionType = KrbConstants.IndicatorsType.ET_02;
                } else {
                    exceptionType = KrbConstants.IndicatorsType.ET_03;
                }
                break;
            case KrbConstants.DBP.key:
                if (number > 90) {
                    exceptionType = KrbConstants.IndicatorsType.ET_01;
                } else if (number < 60) {
                    exceptionType = KrbConstants.IndicatorsType.ET_02;
                } else {
                    exceptionType = KrbConstants.IndicatorsType.ET_03;
                }
                break;
            case KrbConstants.HR.key:
                if (number > 100) {
                    exceptionType = KrbConstants.IndicatorsType.ET_01;
                } else if (number < 60) {
                    exceptionType = KrbConstants.IndicatorsType.ET_02;
                } else {
                    exceptionType = KrbConstants.IndicatorsType.ET_03;
                }
                break;
            default:
                exceptionType = null;
                break;
        }
        return exceptionType;
    }
    /**
     * 判断脉率
     * @param pulse 数
     * @return
     */
    public static String judgePulse(String pulse) {
        if (pulse == null) {
            return null;
        }
        Integer number = Integer.valueOf(pulse);
        String exceptionType;
        if(number<60){
            exceptionType = KrbConstants.IndicatorsType.ET_02;
        }else if(number>100){
            exceptionType = KrbConstants.IndicatorsType.ET_01;
        }else {
            exceptionType = KrbConstants.IndicatorsType.ET_03;
        }
        return exceptionType;
    }

	/**
	 * 判断血糖异常
	 * @param xt
	 * @param timeType
	 * @return
	 */
	public static String judgeBloodSugar(String xt, String timeType) {
		if(StrUtil.isEmpty(timeType)) {
			timeType = KrbConstants.GluType.XT_CANQIAN + "";
		}
		return judgeBloodSugar(xt, Integer.parseInt(timeType));
	}
    /**
     * 判断血糖异常
     *
     * @param xt
     * @param timeType
     * @return
     */
    public static String judgeBloodSugar(String xt, Integer timeType) {
        if (xt == null) {
            return null;
        }
        Double glu = Double.valueOf(xt);
        String exceptionType;
        if (glu < 3.9) {
            exceptionType = KrbConstants.IndicatorsType.ET_02;
        } else if (glu <= 6.1) {
            exceptionType = KrbConstants.IndicatorsType.ET_03;
        } else if (glu <= 7.8 && KrbConstants.GluType.XT_CANHOU_2.equals(timeType)) {
            exceptionType = KrbConstants.IndicatorsType.ET_03;
        } else if (glu <= 11.1 && KrbConstants.GluType.XT_CANHOU_1.equals(timeType)) {
            exceptionType = KrbConstants.IndicatorsType.ET_03;
        } else {
            exceptionType = KrbConstants.IndicatorsType.ET_01;
        }
        return exceptionType;
    }

}
