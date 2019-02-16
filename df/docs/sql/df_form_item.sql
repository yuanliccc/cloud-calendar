/*
Navicat MySQL Data Transfer

Source Server         : GraduationProject
Source Server Version : 50724
Source Host           : 106.12.215.87:3306
Source Database       : cc

Target Server Type    : MYSQL
Target Server Version : 50724
File Encoding         : 65001

Date: 2019-02-16 17:26:03
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for df_form_item
-- ----------------------------
DROP TABLE IF EXISTS `df_form_item`;
CREATE TABLE `df_form_item` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `form_field_id` int(11) DEFAULT NULL,
  `value` varchar(255) DEFAULT NULL,
  `label` varchar(255) DEFAULT NULL,
  `item_index` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=73 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of df_form_item
-- ----------------------------
INSERT INTO `df_form_item` VALUES ('65', '77', '足球', '足球', '0');
INSERT INTO `df_form_item` VALUES ('66', '77', '篮球', '篮球', '1');
INSERT INTO `df_form_item` VALUES ('67', '77', '羽毛球', '羽毛球', '2');
INSERT INTO `df_form_item` VALUES ('68', '78', '男', '男', '0');
INSERT INTO `df_form_item` VALUES ('69', '78', '女', '女', '1');
INSERT INTO `df_form_item` VALUES ('70', '79', '党员', '党员', '0');
INSERT INTO `df_form_item` VALUES ('71', '79', '团员', '团员', '1');
INSERT INTO `df_form_item` VALUES ('72', '79', '群众', '群众', '2');
SET FOREIGN_KEY_CHECKS=1;
