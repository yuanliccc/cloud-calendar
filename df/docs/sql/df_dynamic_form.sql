/*
Navicat MySQL Data Transfer

Source Server         : GraduationProject
Source Server Version : 50724
Source Host           : 106.12.215.87:3306
Source Database       : cc

Target Server Type    : MYSQL
Target Server Version : 50724
File Encoding         : 65001

Date: 2019-02-16 17:26:31
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for df_dynamic_form
-- ----------------------------
DROP TABLE IF EXISTS `df_dynamic_form`;
CREATE TABLE `df_dynamic_form` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `method` varchar(255) DEFAULT NULL,
  `action` varchar(255) DEFAULT NULL,
  `enctype` varchar(255) DEFAULT NULL,
  `employee_id` int(11) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of df_dynamic_form
-- ----------------------------
INSERT INTO `df_dynamic_form` VALUES ('12', '测试表单', 'POST', null, null, '0', '2019-01-13 20:43:14');
SET FOREIGN_KEY_CHECKS=1;
