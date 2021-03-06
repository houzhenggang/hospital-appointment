package com.kasoft.register.base.api.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 检查资源
 *
 * @author kylin
 * @date 2019-07-27 10:32:30
 */
@Data
@TableName("kasoft_doctor_inspectresource")
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "检查资源")
public class DoctorInspectresource extends Model<DoctorInspectresource> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
	@TableId(value = "insp_resource_id", type = IdType.UUID)
	@ApiModelProperty(value="主键")
    private String inspResourceId;

	/**
	 * 医院ID
	 */
	@ApiModelProperty(value="医院ID")
	private String hospitalId;

	/**
     * 医院名称
     */
    @ApiModelProperty(value="医院名称")
    private String hospitalName;

	/**
	 * 医院图片
	 */
	@ApiModelProperty(value="医院图片")
    private String hospitalImage;

	/**
     * 医院电话
     */
    @ApiModelProperty(value="医院电话")
    private String hospitalPhone;

    /**
     * 检查项目ID
     */
    @ApiModelProperty(value="检查项目ID")
    private String inspItemId;

    /**
     * 检查项目类别
     */
    @ApiModelProperty(value="检查项目类别")
    private String inspItemType;

    /**
     * 检查项目名称
     */
    @ApiModelProperty(value="检查项目名称")
    private String inspItemName;

	/**
	 * 检查项目说明
	 */
	@ApiModelProperty(value="检查项目说明")
	private String inspItemExp;

    /**
     * 收费单价
     */
    @ApiModelProperty(value="收费单价")
    private BigDecimal unitPrice;
    /**
     * 数量
     */
    @ApiModelProperty(value="数量")
    private Integer quantity;

	/**
	 * 预约日期
	 */
	@ApiModelProperty(value="预约日期")
	private LocalDate inspItemDate;

	/**
	 * 预约星期
	 */
	@ApiModelProperty(value="预约星期")
	private String inspItemWeek;

	/**
	 * 预约上下午
	 */
	@ApiModelProperty(value="预约上下午")
	private String inspItemAp;

	/**
	 * 预约时间段
	 */
	@ApiModelProperty(value="预约时间段")
	private String period;

    /**
     * 开始时间
     */
	@ApiModelProperty(value="开始时间")
    private LocalDateTime startTime;

    /**
     * 结束时间
     */
	@ApiModelProperty(value="结束时间")
    private LocalDateTime endTime;
    /**
     * 备注或说明
     */
    @ApiModelProperty(value="备注或说明")
    private String remark;
    /**
     * 创建时间
     */
    @ApiModelProperty(value="创建时间")
    private LocalDateTime createTime;
    /**
     * 修改时间
     */
    @ApiModelProperty(value="修改时间")
    private LocalDateTime updateTime;
    /**
     * 乐观锁字段
     */
    @ApiModelProperty(value="乐观锁字段")
    private Integer version;
    /**
     * 逻辑删除标记(0--正常 1--删除)
     */
	@TableLogic
	@ApiModelProperty(value="逻辑删除标记(0--正常 1--删除)")
    private String delFlag;

	/**
	 * 最高价格
	 */
	@TableField(exist = false)
	private BigDecimal maxUnitPrice;
}
