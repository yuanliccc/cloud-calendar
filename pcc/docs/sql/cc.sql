/*
 Navicat Premium Data Transfer

 Source Server         : 106.12.215.87
 Source Server Type    : MySQL
 Source Server Version : 50724
 Source Host           : 106.12.215.87:3306
 Source Schema         : cc

 Target Server Type    : MySQL
 Target Server Version : 50724
 File Encoding         : 65001

 Date: 23/12/2018 16:48:31
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for pcc_remind_service
-- ----------------------------
DROP TABLE IF EXISTS `pcc_remind_service`;
CREATE TABLE `pcc_remind_service`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '提醒服务名称',
  `is_usable` tinyint(1) NOT NULL COMMENT '服务是否可用',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for pcc_schedule
-- ----------------------------
DROP TABLE IF EXISTS `pcc_schedule`;
CREATE TABLE `pcc_schedule`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `create_time` datetime(0) NOT NULL COMMENT '创建时间',
  `remind_time` datetime(0) NOT NULL COMMENT '提醒时间',
  `pcc_user_id` int(11) NOT NULL COMMENT '所属用户（主键）',
  `pcc_remind_service_id` int(11) NOT NULL COMMENT '提醒服务（主键）',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for pcc_user
-- ----------------------------
DROP TABLE IF EXISTS `pcc_user`;
CREATE TABLE `pcc_user`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '姓名',
  `sex` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '性别',
  `email` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  `phone` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '电话号码',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
