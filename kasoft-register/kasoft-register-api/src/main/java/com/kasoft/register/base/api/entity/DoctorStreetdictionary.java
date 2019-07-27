package com.kasoft.register.base.api.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

/**
 * 街道居委会
 *
 * @author charlie
 * @date 2019-04-30 17:10:33
 */
@Data
@TableName("kasoft_doctor_streetdictionary")
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "街道居委会")
public class DoctorStreetdictionary extends Model<DoctorStreetdictionary> {
private static final long serialVersionUID = 1L;

    /**
   * 主键
   */
	@TableId(value = "street_id", type = IdType.INPUT)
	@ApiModelProperty(value = "主键")
	private String streetId;
    /**
   * 街道/乡镇名称
   */
	@ApiModelProperty(value = "街道/乡镇名称")
	@NotBlank(message = "名称不能为空")
    private String name;
    /**
   * 上级编号
   */
	@ApiModelProperty(value = "上级编号")
	@NotBlank(message = "上级编号不能为空")
    private String parentId;
    /**
   * 类型 1：街道 2：居委会
   */
	@ApiModelProperty(value = "类型 1：街道 2：居委会")
	@NotBlank(message = "类型不能为空")
    private String streetType;
    /**
   * 创建时间
   */
	@ApiModelProperty(hidden=true)
	private LocalDateTime createTime;
    /**
   * 修改时间
   */
	@ApiModelProperty(hidden=true)
	private LocalDateTime updateTime;
	/**
	 * 清理不使用的DTO参数
	 */
	public void clearNoUseDTO() {
		createTime = null;
		updateTime = null;
	}
}
