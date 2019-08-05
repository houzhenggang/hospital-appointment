package com.kasoft.register.base.api.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.math.BigDecimal;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 检查价格
 *
 * @author kylin
 * @date 2019-08-03 14:30:41
 */
@Data
@TableName("kasoft_doctor_inspectionprice")
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "检查价格")
public class Inspectionprice extends Model<Inspectionprice> {

    private static final long serialVersionUID = 1L;

        /**
     * 主键
     */
    @TableId(type = IdType.UUID)
    @ApiModelProperty(value="主键")
    private String inspPriceId;
        /**
     * 医院ID
     */
    @ApiModelProperty(value="医院ID")
    private String hospitalId;
        /**
     * 检查项目ID
     */
    @ApiModelProperty(value="检查项目ID")
    private String inspItemId;
        /**
     * 价格
     */
    @ApiModelProperty(value="价格")
    private BigDecimal inspPrice;
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
     * 租户
     */
    @ApiModelProperty(value="租户",hidden=true)
    private Integer tenantId;
        /**
     * 逻辑删除标记(0--正常 1--删除)
     */
    @ApiModelProperty(value="逻辑删除标记(0--正常 1--删除)")
    private String delFlag;
    }
