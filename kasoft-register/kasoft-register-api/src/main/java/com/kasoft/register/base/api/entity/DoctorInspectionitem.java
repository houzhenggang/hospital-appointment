package com.kasoft.register.base.api.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

/**
 * 检查项目
 *
 * @author kylin
 * @date 2019-07-26 21:33:19
 */
@Data
@TableName("kasoft_doctor_inspectionitem")
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "检查项目")
public class DoctorInspectionitem extends Model<DoctorInspectionitem> {
	private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
	@TableId(value = "insp_item_id", type = IdType.UUID)
	@ApiModelProperty(value="主键")
    private String inspItemId;
    /**
     * 检查类别（体格检查，功能检查，化验检查）
     */
    @ApiModelProperty(value="检查类别（体格检查，功能检查，化验检查）")
    private String inspItemType;
    /**
     * 检查项目名称
     */
    @ApiModelProperty(value="检查项目名称")
    private String inspItemName;
    /**
     * 检查项目说明（比如检查前3小时不能进食）
     */
    @ApiModelProperty(value="检查项目说明（比如检查前3小时不能进食）")
    private String inspItemExp;
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
    }
