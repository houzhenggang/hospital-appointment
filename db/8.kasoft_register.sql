/*
 Navicat Premium Data Transfer

 Source Server         : mysql测试3
 Source Server Type    : MySQL
 Source Server Version : 80015
 Source Host           : 47.105.164.183:3322
 Source Schema         : edserver

 Target Server Type    : MySQL
 Target Server Version : 80015
 File Encoding         : 65001

 Date: 22/07/2019 22:04:17
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;


-- ----------------------------
-- Table structure for kasoft_doctor_areadictionary
-- ----------------------------
DROP TABLE IF EXISTS `kasoft_doctor_areadictionary`;
CREATE TABLE `kasoft_doctor_areadictionary`  (
  `area_id` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主键',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '省市区名称',
  `parent_area_id` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '父级编号',
  `area_type` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '类型 1：省 2：市 3：区',
  `create_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  PRIMARY KEY (`area_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '地区字典' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for kasoft_doctor_departmentdictionary
-- ----------------------------
DROP TABLE IF EXISTS `kasoft_doctor_departmentdictionary`;
CREATE TABLE `kasoft_doctor_departmentdictionary`  (
  `department_id` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主键',
  `department_code` varchar(60) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '科室代码',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '科室名称',
  `department_image` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '科室图片',
  `introduced` mediumtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '介绍',
  `contact_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '科室联系人',
  `contact_phone` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '联系人电话',
  `area_id` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '地区编号',
  `hospital_id` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '所属医疗机构',
  `create_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  `del_flag` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '是否删除  -1：已删除  0：正常',
  `tenant_id` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`department_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '科室字典' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for kasoft_doctor_doctorinfo
-- ----------------------------
DROP TABLE IF EXISTS `kasoft_doctor_doctorinfo`;
CREATE TABLE `kasoft_doctor_doctorinfo`  (
  `doctor_id` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主键',
  `code` varchar(60) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '工号',
  `head_img` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '头像',
  `name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '姓名',
  `sex` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '性别  1：男  2：女',
  `phone` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '手机号码',
  `birthday` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '出生日期',
  `id_card` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '身份证',
  `doctor_type` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '人员类别  1：医生  2：护士  3：健康专员',
  `doctor_job_title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '医生职称',
  `nurse_job_title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '护士职称',
  `receive_counseling` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '1' COMMENT '接受咨询 0：接受 1：不接受',
  `address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '居住地址',
  `good_at` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '个人擅长',
  `area_id` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '所属地区',
  `hospital_id` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '所属医院',
  `department_id` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '所属科室',
  `doctor_sign` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '医生签名（url）',
  `user_id` int(11) NULL DEFAULT NULL COMMENT '用户编号',
  `dept_id` int(11) NULL DEFAULT NULL COMMENT '部门编号',
  `create_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  `del_flag` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '是否删除  -1：已删除  0：正常',
  `tenant_id` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`doctor_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '医生信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for kasoft_doctor_hospital
-- ----------------------------
DROP TABLE IF EXISTS `kasoft_doctor_hospital`;
CREATE TABLE `kasoft_doctor_hospital`  (
  `hospital_id` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主键',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '医疗机构名称',
  `hospital_image` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '医院图片',
  `hospital_level` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '医疗机构等级',
  `introduced` mediumtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '介绍',
  `address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '地址',
  `phone` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '总机电话',
  `contact_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '机构联系人',
  `contact_phone` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '联系人电话',
  `area_id` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '所属地区',
  `cooperation` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '是否合作0: 是 1：否',
  `archives` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '是否可建档0: 是 1：否',
  `lon` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '经度',
  `lat` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '纬度',
  `msg` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '1' COMMENT '是否推送短信0: 是 1：否',
  `project` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '是否有专案 0：无 1：有',
  `hospital_tel` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '医院服务电话',
  `associated` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '关联医院编号',
  `parent_id` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '上级医院编号',
  `dept_id` int(20) NULL DEFAULT NULL COMMENT '所属组织',
  `create_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  `del_flag` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '是否删除  -1：已删除  0：正常',
  `tenant_id` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`hospital_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '医院' ROW_FORMAT = Dynamic;


-- ----------------------------
-- Table structure for kasoft_doctor_peopleinfo
-- ----------------------------
DROP TABLE IF EXISTS `kasoft_doctor_peopleinfo`;
CREATE TABLE `kasoft_doctor_peopleinfo`  (
  `people_id` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主键',
  `card_number` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '医保卡号',
  `card_number_img` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '医保卡照片',
  `has_chronic_disease` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '慢性病 有无字段 0：无；1：有',
  `chronic_disease` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '慢性病内容',
  `id_card` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '身份证',
  `id_card_front` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '身份证正面照片',
  `id_card_back` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '身份证背面照片',
  `head_img` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '头像',
  `name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '姓名',
  `nickname` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '昵称',
  `phone` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '联系电话',
  `tel` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '固定电话',
  `sex` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '性别  1：男  2：女',
  `birth_date` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '出生日期',
  `link_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '联系人姓名',
  `link_phone` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '联系人电话',
  `note` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '摘要',
  `area_id` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '地区',
  `sync_id` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '第三方关联编号',
  `user_id` int(11) NULL DEFAULT NULL COMMENT '关联用户编号',
  `create_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  `del_flag` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '是否删除  -1：已删除  0：正常',
  `tenant_id` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`people_id`) USING BTREE,
  UNIQUE INDEX `user_id`(`user_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '居民基本信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for kasoft_doctor_problemfeedback
-- ----------------------------
DROP TABLE IF EXISTS `kasoft_doctor_problemfeedback`;
CREATE TABLE `kasoft_doctor_problemfeedback`  (
  `feedback_id` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主键',
  `feedback_type` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '反馈类别  1：软件问题  2：投诉',
  `feedback_content` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '反馈内容',
  `feedback_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '反馈时间',
  `person_id` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '反馈人',
  `person_name` varchar(60) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '反馈人姓名',
  `handle` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '处理结果',
  `handle_id` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '处理人',
  `handle_name` varchar(60) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '处理人姓名',
  `handle_time` timestamp(0) NULL DEFAULT NULL COMMENT '处理时间',
  `feedback_state` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '反馈状态0：未处理，1：已处理',
  `soft_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '软件名称',
  `create_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` timestamp(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  `del_flag` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '是否删除  -1：已删除  0：正常',
  `tenant_id` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`feedback_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '问题反馈' ROW_FORMAT = Dynamic;



-- ----------------------------
-- Table structure for kasoft_doctor_streetdictionary
-- ----------------------------
DROP TABLE IF EXISTS `kasoft_doctor_streetdictionary`;
CREATE TABLE `kasoft_doctor_streetdictionary`  (
  `street_id` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主键',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '街道/乡镇名称',
  `parent_id` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '上级编号',
  `street_type` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '类型 1：街道 2：居委会',
  `create_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  PRIMARY KEY (`street_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '街道居委会' ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
