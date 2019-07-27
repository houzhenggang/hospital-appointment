package com.kasoft.register.base.api.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

/**
 * 居民基本信息表
 *
 * @author charlie
 * @date 2019-07-26 09:58:13
 */
@Data
@TableName("kasoft_doctor_peopleinfo")
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "居民基本信息表")
public class DoctorPeopleinfo extends Model<DoctorPeopleinfo> {
private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
	@TableId(value = "people_id", type = IdType.UUID)
    @ApiModelProperty(value="主键")
    private String peopleId;
    /**
     * 医保卡号
     */
    @ApiModelProperty(value="医保卡号")
    private String cardNumber;
    /**
     * 医保卡照片
     */
    @ApiModelProperty(value="医保卡照片")
    private String cardNumberImg;
    /**
     * 慢性病 有无字段 0：无；1：有
     */
    @ApiModelProperty(value="慢性病 有无字段 0：无；1：有")
    private String hasChronicDisease;
    /**
     * 慢性病内容
     */
    @ApiModelProperty(value="慢性病内容")
    private String chronicDisease;
    /**
     * 身份证
     */
    @ApiModelProperty(value="身份证")
    private String idCard;
    /**
     * 身份证正面照片
     */
    @ApiModelProperty(value="身份证正面照片")
    private String idCardFront;
    /**
     * 身份证背面照片
     */
    @ApiModelProperty(value="身份证背面照片")
    private String idCardBack;
    /**
     * 头像
     */
    @ApiModelProperty(value="头像")
    private String headImg;
    /**
     * 姓名
     */
    @ApiModelProperty(value="姓名")
    private String name;
    /**
     * 昵称
     */
    @ApiModelProperty(value="昵称")
    private String nickname;
    /**
     * 联系电话
     */
    @ApiModelProperty(value="联系电话")
    private String phone;
    /**
     * 固定电话
     */
    @ApiModelProperty(value="固定电话")
    private String tel;
    /**
     * 性别  1：男  2：女
     */
    @ApiModelProperty(value="性别  1：男  2：女")
    private String sex;
    /**
     * 出生日期
     */
    @ApiModelProperty(value="出生日期")
    private String birthDate;
    /**
     * 联系人姓名
     */
    @ApiModelProperty(value="联系人姓名")
    private String linkName;
    /**
     * 联系人电话
     */
    @ApiModelProperty(value="联系人电话")
    private String linkPhone;
    /**
     * 摘要
     */
    @ApiModelProperty(value="摘要")
    private String note;
    /**
     * 地区
     */
    @ApiModelProperty(value="地区")
    private String areaId;
    /**
     * 第三方关联编号
     */
    @ApiModelProperty(value="第三方关联编号")
    private String syncId;
    /**
     * 关联用户编号
     */
    @ApiModelProperty(value="关联用户编号")
    private Integer userId;
	/**
	 * 创建时间
	 */
	@ApiModelProperty(value = "创建时间")
	private LocalDateTime createTime;
	/**
	 * 修改时间
	 */
	@ApiModelProperty(value = "修改时间")
	private LocalDateTime updateTime;
	/**
	 * 是否删除  -1：已删除  0：正常
	 */
	@TableLogic
	@ApiModelProperty(hidden=true)
	private String delFlag;
	/**
	 * 租户
	 */
	@ApiModelProperty(hidden=true)
	private Integer tenantId;
	/**
	 * 清理不使用的DTO参数
	 */
	public void clearNoUseDTO() {
		createTime = null;
		updateTime = null;
		delFlag = null;
		tenantId = null;
	}
}
