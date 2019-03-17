/*
Navicat MySQL Data Transfer

Source Server         : GraduationProject
Source Server Version : 50724
Source Host           : 106.12.215.87:3306
Source Database       : cc

Target Server Type    : MYSQL
Target Server Version : 50724
File Encoding         : 65001

Date: 2019-02-16 17:26:20
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for df_form_attachment
-- ----------------------------
DROP TABLE IF EXISTS `df_form_attachment`;
CREATE TABLE `df_form_attachment` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `form_field_id` int(11) DEFAULT NULL,
  `attachment_name` varchar(255) DEFAULT NULL,
  `attachment_path` varchar(255) DEFAULT NULL,
  `attachment_size` double DEFAULT NULL,
  `upload_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of df_form_attachment
-- ----------------------------
SET FOREIGN_KEY_CHECKS=1;
