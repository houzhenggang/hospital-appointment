package com.kasoft.register.base.api.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.time.LocalDateTime;

/**
 * 预约人信息
 *
 * @author kylin
 * @date 2019-08-14 21:06:00
 */
@Data
@TableName("kasoft_doctor_applyerinfo")
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "预约人信息")
public class Applyerinfo extends Model<Applyerinfo> {

    private static final long serialVersionUID = 1L;

        /**
     * 主键
     */
    @TableId
    @ApiModelProperty(value="主键")
    private String applyerId;
        /**
     * 姓名
     */
    @ApiModelProperty(value="姓名")
    private String applyerName;
        /**
     * 身份证号
     */
    @ApiModelProperty(value="身份证号")
    private String idCard;
        /**
     * 手机号
     */
    @ApiModelProperty(value="手机号")
    private String phone;
        /**
     * 性别  1：男  2：女
     */
    @ApiModelProperty(value="性别  1：男  2：女")
    private String sex;
        /**
     * 出生日期
     */
    @ApiModelProperty(value="出生日期")
    private LocalDateTime birthDate;
        /**
     * 关联用户编号
     */
    @ApiModelProperty(value="关联用户编号")
    private Integer userId;
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
     * 是否删除  -1：已删除  0：正常
     */
    @ApiModelProperty(value="是否删除  -1：已删除  0：正常")
    private String delFlag;
        /**
     * 租户
     */
    @ApiModelProperty(value="租户",hidden=true)
    private Integer tenantId;
        /**
     * 乐观锁
     */
    @ApiModelProperty(value="乐观锁")
    private Integer version;
    }
