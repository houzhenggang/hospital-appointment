package com.kasoft.register.base.api.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 预约订单
 *
 * @author kylin
 * @date 2019-07-27 10:32:12
 */
@Data
@TableName("kasoft_doctor_applyorder")
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "预约订单")
public class DoctorApplyorder extends Model<DoctorApplyorder> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId
    @ApiModelProperty(value="主键")
    private Long applyOrderId;
    /**
     * 机构ID
     */
    @ApiModelProperty(value="机构ID")
    private Integer hospitalId;
    /**
     * 机构名称
     */
    @ApiModelProperty(value="机构名称")
    private String hospitalName;
    /**
     * 机构电话
     */
    @ApiModelProperty(value="机构电话")
    private String hospitalPhone;
    /**
     * 机构地址
     */
    @ApiModelProperty(value="机构地址")
    private String hospitalAddr;
    /**
     * 预约人ID
     */
    @ApiModelProperty(value="预约人ID")
    private Integer userId;
    /**
     * 预约人姓名
     */
    @ApiModelProperty(value="预约人姓名")
    private String userName;
    /**
     * 预约人身份证号
     */
    @ApiModelProperty(value="预约人身份证号")
    private String userIdcard;
    /**
     * 预约人手机号
     */
    @ApiModelProperty(value="预约人手机号")
    private String userPhone;
    /**
     * 检查项目ID
     */
    @ApiModelProperty(value="检查项目ID")
    private Integer insptItemId;
    /**
     * 预约检查项目名称
     */
    @ApiModelProperty(value="预约检查项目名称")
    private String insptItemName;
    /**
     * 检查资源ID
     */
    @ApiModelProperty(value="检查资源ID")
    private Integer insptResourceId;
    /**
     * 检查费用
     */
    @ApiModelProperty(value="检查费用")
    private BigDecimal feeTotal;
    /**
     * 数量
     */
    @ApiModelProperty(value="数量")
    private Integer quantity;
    /**
     * 预约时间
     */
    @ApiModelProperty(value="预约时间")
    private String applyTime;
    /**
     * 详细时间
     */
    @ApiModelProperty(value="详细时间")
    private LocalDateTime detailTime;
    /**
     * 备注或说明
     */
    @ApiModelProperty(value="备注或说明")
    private String remark;
    /**
     * 订单状态
     */
    @ApiModelProperty(value="订单状态")
    private Integer orderState;
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
    @ApiModelProperty(value="逻辑删除标记(0--正常 1--删除)")
    private String delFlag;
    }
