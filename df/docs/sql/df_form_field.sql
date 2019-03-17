/*
Navicat MySQL Data Transfer

Source Server         : GraduationProject
Source Server Version : 50724
Source Host           : 106.12.215.87:3306
Source Database       : cc

Target Server Type    : MYSQL
Target Server Version : 50724
File Encoding         : 65001

Date: 2019-02-16 17:26:12
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for df_form_field
-- ----------------------------
DROP TABLE IF EXISTS `df_form_field`;
CREATE TABLE `df_form_field` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `form_id` int(11) DEFAULT NULL,
  `label` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  `value` varchar(255) DEFAULT NULL,
  `parent_id` int(11) DEFAULT NULL,
  `display_index` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=84 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of df_form_field
-- ----------------------------
INSERT INTO `df_form_field` VALUES ('75', '12', '姓名', null, 'input', '123', null, '0');
INSERT INTO `df_form_field` VALUES ('76', '12', '简介', null, 'textarea', '123', null, '0');
INSERT INTO `df_form_field` VALUES ('77', '12', '兴趣爱好', null, 'checkbox', '足球,篮球', null, '0');
INSERT INTO `df_form_field` VALUES ('78', '12', '性别', null, 'radio', '男', null, '0');
INSERT INTO `df_form_field` VALUES ('79', '12', '政治面貌', null, 'select', '党员', null, '0');
INSERT INTO `df_form_field` VALUES ('80', '12', '出生日期', null, 'date', '2019-01-03 00:00:00', null, '0');
INSERT INTO `df_form_field` VALUES ('81', '12', '栅栏式布局', null, 'grid', '', null, '0');
INSERT INTO `df_form_field` VALUES ('82', '12', '联系方式', null, 'input', '123123', '81', '0');
INSERT INTO `df_form_field` VALUES ('83', '12', '家庭住址', null, 'input', '1231232', '81', '1');
SET FOREIGN_KEY_CHECKS=1;
