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

 Date: 08/03/2019 18:58:07
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

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
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of df_dynamic_form
-- ----------------------------
BEGIN;
INSERT INTO `df_dynamic_form` VALUES (30, '测试111', 'POST', 'action', NULL, 0, '2019-02-24 14:34:00');
COMMIT;

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
  `key` varchar(255) DEFAULT NULL,
  `model` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=135 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of df_form_field
-- ----------------------------
BEGIN;
INSERT INTO `df_form_field` VALUES (126, 30, '姓名', NULL, 'input', '', NULL, 0, '1550989910000_43862', 'input_1550989910000_43862');
INSERT INTO `df_form_field` VALUES (127, 30, '个人简介', NULL, 'textarea', '', NULL, 0, '1550989911000_7657', 'textarea_1550989911000_7657');
INSERT INTO `df_form_field` VALUES (128, 30, '兴趣爱好', NULL, 'checkbox', '足球,篮球', NULL, 0, '1550989912000_36553', 'checkbox_1550989912000_36553');
INSERT INTO `df_form_field` VALUES (129, 30, '性别', NULL, 'radio', '女', NULL, 0, '1550989914000_64035', 'radio_1550989914000_64035');
INSERT INTO `df_form_field` VALUES (130, 30, '政治面貌', NULL, 'select', '团员', NULL, 0, '1550989916000_4817', 'select_1550989916000_4817');
INSERT INTO `df_form_field` VALUES (131, 30, '出生日期', NULL, 'date', '', NULL, 0, '1550989917000_63297', 'date_1550989917000_63297');
INSERT INTO `df_form_field` VALUES (132, 30, '栅栏式布局', NULL, 'grid', '', NULL, 0, '1550989918000_27035', 'grid_1550989918000_27035');
INSERT INTO `df_form_field` VALUES (133, 30, '联系方式', NULL, 'input', '', 132, 0, '1550989920000_9299', 'input_1550989920000_9299');
INSERT INTO `df_form_field` VALUES (134, 30, '家庭住址', NULL, 'input', '', 132, 1, '1550989921000_99058', 'input_1550989921000_99058');
COMMIT;

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
) ENGINE=InnoDB AUTO_INCREMENT=121 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of df_form_item
-- ----------------------------
BEGIN;
INSERT INTO `df_form_item` VALUES (112, 128, '足球', '足球', '0');
INSERT INTO `df_form_item` VALUES (113, 128, '篮球', '篮球', '1');
INSERT INTO `df_form_item` VALUES (114, 128, '羽毛球', '羽毛球', '2');
INSERT INTO `df_form_item` VALUES (115, 129, '男', '男', '0');
INSERT INTO `df_form_item` VALUES (116, 129, '女', '女', '1');
INSERT INTO `df_form_item` VALUES (117, 129, '保密', '保密', '2');
INSERT INTO `df_form_item` VALUES (118, 130, '党员', '党员', '0');
INSERT INTO `df_form_item` VALUES (119, 130, '团员', '团员', '1');
INSERT INTO `df_form_item` VALUES (120, 130, '群众', '群众', '2');
COMMIT;

-- ----------------------------
-- Table structure for df_user
-- ----------------------------
DROP TABLE IF EXISTS `df_user`;
CREATE TABLE `df_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `sex` varchar(255) DEFAULT NULL,
  `email` varchar(255) NOT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `email` (`email`) USING BTREE,
  UNIQUE KEY `phone` (`phone`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of df_user
-- ----------------------------
BEGIN;
INSERT INTO `df_user` VALUES (1, '戏舞', NULL, '2715196252@qq.com', '18323098103', 'a875685561');
COMMIT;

-- ----------------------------
-- Table structure for pcc_additional_info_type
-- ----------------------------
DROP TABLE IF EXISTS `pcc_additional_info_type`;
CREATE TABLE `pcc_additional_info_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `table_name` varchar(50) NOT NULL,
  `filters` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of pcc_additional_info_type
-- ----------------------------
BEGIN;
INSERT INTO `pcc_additional_info_type` VALUES (1, '提交文本信息', 'pcc_schedule_text', 'pcc_user_id,pcc_schedule_id');
INSERT INTO `pcc_additional_info_type` VALUES (2, '提交文件', 'pcc_schedule_file', 'pcc_user_id,pcc_schedule_id');
COMMIT;

-- ----------------------------
-- Table structure for pcc_file
-- ----------------------------
DROP TABLE IF EXISTS `pcc_file`;
CREATE TABLE `pcc_file` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `url` varchar(200) NOT NULL,
  `url_type` varchar(20) NOT NULL,
  `type` varchar(50) NOT NULL,
  `size` double NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=64 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of pcc_file
-- ----------------------------
BEGIN;
INSERT INTO `pcc_file` VALUES (1, '阴阳师', 'D:\\upload\\txt\\2019\\0\\16\\458098559493655.txt', 'local', 'txt', 119);
INSERT INTO `pcc_file` VALUES (2, '阴阳师', 'D:\\upload\\txt\\2019\\0\\16\\458311832737375.txt', 'local', 'txt', 119);
INSERT INTO `pcc_file` VALUES (3, '阴阳师', 'D:\\upload\\txt\\2019\\0\\16\\462264596601971.txt', 'local', 'txt', 119);
INSERT INTO `pcc_file` VALUES (4, '阴阳师', 'D:\\upload\\txt\\2019\\0\\16\\462339194148298.txt', 'local', 'txt', 119);
INSERT INTO `pcc_file` VALUES (5, '阴阳师', 'D:\\upload\\txt\\2019\\0\\16\\462447841139005.txt', 'local', 'txt', 119);
INSERT INTO `pcc_file` VALUES (6, '阴阳师', 'D:\\upload\\txt\\2019\\0\\16\\463399813987376.txt', 'local', 'txt', 119);
INSERT INTO `pcc_file` VALUES (7, '阴阳师', 'D:\\upload\\txt\\2019\\0\\16\\463442829993341.txt', 'local', 'txt', 119);
INSERT INTO `pcc_file` VALUES (8, '阴阳师', 'D:\\upload\\txt\\2019\\0\\16\\463475092851716.txt', 'local', 'txt', 119);
INSERT INTO `pcc_file` VALUES (9, '阴阳师', 'D:\\upload\\txt\\2019\\0\\16\\463914776239629.txt', 'local', 'txt', 119);
INSERT INTO `pcc_file` VALUES (10, '阴阳师', 'D:\\upload\\txt\\2019\\0\\16\\463945551507881.txt', 'local', 'txt', 119);
INSERT INTO `pcc_file` VALUES (11, '阴阳师', 'D:\\upload\\txt\\2019\\0\\16\\464104000067413.txt', 'local', 'txt', 119);
INSERT INTO `pcc_file` VALUES (16, '阴阳师', 'D:\\upload\\txt\\2019\\0\\16\\470023862246762.txt', 'local', 'txt', 119);
INSERT INTO `pcc_file` VALUES (18, '阴阳师', 'D:\\upload\\txt\\2019\\0\\17\\479859962390492.txt', 'local', 'txt', 119);
INSERT INTO `pcc_file` VALUES (19, '阴阳师', 'D:\\upload\\txt\\2019\\0\\17\\479999495690242.txt', 'local', 'txt', 119);
INSERT INTO `pcc_file` VALUES (20, '阴阳师', 'D:\\upload\\txt\\2019\\0\\17\\480076962752874.txt', 'local', 'txt', 119);
INSERT INTO `pcc_file` VALUES (27, '阴阳师', 'D:\\upload\\txt\\2019\\0\\17\\532752361286905.txt', 'local', 'txt', 119);
INSERT INTO `pcc_file` VALUES (34, '阴阳师', 'D:\\upload\\txt\\2019\\0\\20\\788297168118802.txt', 'local', 'txt', 119);
INSERT INTO `pcc_file` VALUES (35, '阴阳师', 'D:\\upload\\txt\\2019\\0\\20\\788542681109514.txt', 'local', 'txt', 119);
INSERT INTO `pcc_file` VALUES (36, '阴阳师', 'D:\\upload\\txt\\2019\\0\\20\\790687738461162.txt', 'local', 'txt', 119);
INSERT INTO `pcc_file` VALUES (37, '阴阳师', 'D:\\upload\\txt\\2019\\0\\20\\801529050589553.txt', 'local', 'txt', 119);
INSERT INTO `pcc_file` VALUES (38, '阴阳师', 'D:\\upload\\txt\\2019\\0\\20\\801531393585269.txt', 'local', 'txt', 119);
INSERT INTO `pcc_file` VALUES (39, '阴阳师', 'D:\\upload\\txt\\2019\\0\\23\\1074787100865080.txt', 'local', 'txt', 119);
INSERT INTO `pcc_file` VALUES (40, '阴阳师', 'D:\\upload\\txt\\2019\\0\\24\\1141888389173538.txt', 'local', 'txt', 119);
INSERT INTO `pcc_file` VALUES (41, '阴阳师', 'D:\\upload\\txt\\2019\\0\\24\\1141932997883165.txt', 'local', 'txt', 119);
INSERT INTO `pcc_file` VALUES (42, '6Q4N`)}[E2@1B99~2_NE80Z', 'D:\\upload\\png\\2019\\0\\26\\1304931322493825.png', 'local', 'png', 715519);
INSERT INTO `pcc_file` VALUES (43, '6Q4N`)}[E2@1B99~2_NE80Z', 'D:\\upload\\png\\2019\\0\\26\\1305045001011199.png', 'local', 'png', 715519);
INSERT INTO `pcc_file` VALUES (44, '6Q4N`)}[E2@1B99~2_NE80Z', 'D:\\upload\\png\\2019\\0\\26\\1305116489971490.png', 'local', 'png', 715519);
INSERT INTO `pcc_file` VALUES (45, '6Q4N`)}[E2@1B99~2_NE80Z', 'D:\\upload\\png\\2019\\0\\26\\1305342211806315.png', 'local', 'png', 715519);
INSERT INTO `pcc_file` VALUES (46, 'a', 'D:\\upload\\jpg\\2019\\0\\26\\1335031843311591.jpg', 'local', 'jpg', 123010);
INSERT INTO `pcc_file` VALUES (47, 'myqrcode', 'D:\\upload\\png\\2019\\0\\26\\1336149236193999.png', 'local', 'png', 575);
INSERT INTO `pcc_file` VALUES (48, 'bb84e7cf-4938-4c0d-89fc-c62803d409b6', 'D:\\upload\\png\\2019\\0\\26\\1336274990705783.png', 'local', 'png', 644558);
INSERT INTO `pcc_file` VALUES (49, 'bb84e7cf-4938-4c0d-89fc-c62803d409b6', 'D:\\upload\\png\\2019\\0\\26\\1336448794039299.png', 'local', 'png', 644558);
INSERT INTO `pcc_file` VALUES (50, 'bb84e7cf-4938-4c0d-89fc-c62803d409b6', 'D:\\upload\\png\\2019\\0\\26\\1336463223861063.png', 'local', 'png', 644558);
INSERT INTO `pcc_file` VALUES (51, 'bb84e7cf-4938-4c0d-89fc-c62803d409b6', 'D:\\upload\\png\\2019\\0\\26\\1336487444736315.png', 'local', 'png', 644558);
INSERT INTO `pcc_file` VALUES (52, 'bb84e7cf-4938-4c0d-89fc-c62803d409b6', 'D:\\upload\\png\\2019\\0\\26\\1336503278498259.png', 'local', 'png', 644558);
INSERT INTO `pcc_file` VALUES (53, 'bb84e7cf-4938-4c0d-89fc-c62803d409b6', 'D:\\upload\\png\\2019\\0\\26\\1336542300347562.png', 'local', 'png', 644558);
INSERT INTO `pcc_file` VALUES (54, 'bb84e7cf-4938-4c0d-89fc-c62803d409b6', 'D:\\upload\\png\\2019\\0\\26\\1336615022227045.png', 'local', 'png', 644558);
INSERT INTO `pcc_file` VALUES (55, 'bb84e7cf-4938-4c0d-89fc-c62803d409b6', 'D:\\upload\\png\\2019\\0\\26\\1336637775810687.png', 'local', 'png', 644558);
INSERT INTO `pcc_file` VALUES (56, 'myqrcode', 'D:\\upload\\png\\2019\\0\\27\\1336705471647305.png', 'local', 'png', 575);
INSERT INTO `pcc_file` VALUES (57, 'a', 'D:\\upload\\jpg\\2019\\0\\27\\1336723170515529.jpg', 'local', 'jpg', 123010);
INSERT INTO `pcc_file` VALUES (58, 'myqrcode', 'D:\\upload\\png\\2019\\0\\27\\1336729374682240.png', 'local', 'png', 575);
INSERT INTO `pcc_file` VALUES (59, '5c2f00ac5e6027d6f3181929roxrckhc_small', 'D:\\upload\\png\\2019\\0\\27\\1336752917176367.png', 'local', 'png', 34243);
INSERT INTO `pcc_file` VALUES (60, '5c2f05795e6027698b63ad2f9lvlfx3m_small', 'D:\\upload\\png\\2019\\0\\27\\1336813777008482.png', 'local', 'png', 74851);
INSERT INTO `pcc_file` VALUES (61, '5c2f00ac5e6027d6f3181929roxrckhc_small', 'D:\\upload\\png\\2019\\0\\27\\1336829352483355.png', 'local', 'png', 34243);
INSERT INTO `pcc_file` VALUES (62, '5c2f05795e6027698b63ad2f9lvlfx3m_small', 'D:\\upload\\png\\2019\\0\\28\\1481300457235366.png', 'local', 'png', 74851);
INSERT INTO `pcc_file` VALUES (63, '郑合惠子', 'D:\\upload\\jpg\\2019\\1\\2\\1986943262084865.jpg', 'local', 'jpg', 595167);
COMMIT;

-- ----------------------------
-- Table structure for pcc_remind_service
-- ----------------------------
DROP TABLE IF EXISTS `pcc_remind_service`;
CREATE TABLE `pcc_remind_service` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(20) NOT NULL COMMENT '提醒服务名称',
  `is_usable` tinyint(1) NOT NULL COMMENT '服务是否可用',
  `api` varchar(255) DEFAULT NULL COMMENT '服务 API',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of pcc_remind_service
-- ----------------------------
BEGIN;
INSERT INTO `pcc_remind_service` VALUES (1, '邮箱提醒', 1, NULL);
INSERT INTO `pcc_remind_service` VALUES (2, '页面提醒', 0, NULL);
INSERT INTO `pcc_remind_service` VALUES (3, '短信提醒', 0, NULL);
COMMIT;

-- ----------------------------
-- Table structure for pcc_schedule
-- ----------------------------
DROP TABLE IF EXISTS `pcc_schedule`;
CREATE TABLE `pcc_schedule` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `remind_time` datetime NOT NULL COMMENT '提醒时间',
  `pcc_user_id` int(11) NOT NULL COMMENT '所属用户（主键）',
  `content` varchar(255) NOT NULL COMMENT '任务内容',
  `deadline` datetime DEFAULT NULL COMMENT '截止时间',
  `is_delete` char(2) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of pcc_schedule
-- ----------------------------
BEGIN;
INSERT INTO `pcc_schedule` VALUES (14, '2019-01-10 16:50:29', '2019-01-10 16:06:32', 1, '提交考核文档。', '2019-01-10 16:06:34', NULL);
INSERT INTO `pcc_schedule` VALUES (15, '2019-01-10 17:22:20', '2019-01-10 17:22:09', 1, '完成接口测试。', '2019-01-17 00:00:00', NULL);
INSERT INTO `pcc_schedule` VALUES (16, '2019-01-13 19:57:47', '2019-01-13 21:00:00', 1, '完成任务对接', '2019-01-14 10:00:00', NULL);
INSERT INTO `pcc_schedule` VALUES (17, '2019-01-13 20:04:03', '2019-01-13 20:03:57', 1, '团建通知', '2019-01-17 00:00:00', NULL);
INSERT INTO `pcc_schedule` VALUES (18, '2019-01-13 20:05:43', '2019-01-13 20:03:57', 1, '吃饭吃饭吃饭。', '2019-01-17 00:00:00', NULL);
INSERT INTO `pcc_schedule` VALUES (19, '2019-01-14 23:26:20', '2019-01-14 23:26:14', 1, '开开心心', '2019-01-22 00:00:00', NULL);
INSERT INTO `pcc_schedule` VALUES (20, '2019-01-14 23:28:28', '2019-01-14 23:28:20', 1, '吃饱喝足', '2019-01-17 00:00:00', NULL);
INSERT INTO `pcc_schedule` VALUES (21, '2019-01-14 23:29:30', '2019-01-15 00:00:00', 1, '可爱女人', '2019-01-14 23:29:28', NULL);
INSERT INTO `pcc_schedule` VALUES (22, '2019-01-14 23:33:33', '2019-01-15 00:00:00', 1, '超跑女神', '2019-01-30 00:00:00', NULL);
INSERT INTO `pcc_schedule` VALUES (23, '2019-01-14 23:35:47', '2019-01-14 23:35:43', 1, '甜甜的', '2019-01-31 00:00:00', NULL);
INSERT INTO `pcc_schedule` VALUES (24, '2019-01-16 00:01:06', '2019-01-16 00:01:01', 1, '测试插入完成条件', '2019-01-31 00:00:00', NULL);
INSERT INTO `pcc_schedule` VALUES (25, '2019-01-17 18:21:58', '2019-01-17 18:21:53', 1, '测试', '2019-01-24 00:00:00', NULL);
INSERT INTO `pcc_schedule` VALUES (26, '2019-01-17 18:22:21', '2019-01-17 18:22:18', 1, '方法', '2019-01-25 00:00:00', NULL);
INSERT INTO `pcc_schedule` VALUES (27, '2019-01-17 18:23:21', '2019-01-17 18:23:17', 1, '嘎嘎嘎嘎嘎', '2019-01-25 00:00:00', NULL);
INSERT INTO `pcc_schedule` VALUES (28, '2019-01-20 16:16:04', '2019-01-20 16:15:59', 1, 'heiha', '2019-01-22 00:00:00', NULL);
INSERT INTO `pcc_schedule` VALUES (29, '2019-01-20 16:20:02', '2019-01-20 16:19:56', 1, 'ff', '2019-01-22 00:00:00', NULL);
INSERT INTO `pcc_schedule` VALUES (30, '2019-01-20 19:20:44', '2019-01-20 19:20:37', 1, 'cccc', '2019-01-22 00:00:00', NULL);
INSERT INTO `pcc_schedule` VALUES (31, '2019-01-24 17:53:01', '2019-01-24 17:52:54', 1, '嘿哈', '2019-01-26 00:00:00', NULL);
INSERT INTO `pcc_schedule` VALUES (32, '2019-01-24 17:53:40', '2019-01-24 17:53:37', 1, '反反复复', '2019-01-24 17:53:39', NULL);
INSERT INTO `pcc_schedule` VALUES (33, '2019-01-28 22:06:20', '2019-01-28 22:06:15', 2, 'ddd', '2019-01-25 00:00:00', NULL);
COMMIT;

-- ----------------------------
-- Table structure for pcc_schedule_additional_type
-- ----------------------------
DROP TABLE IF EXISTS `pcc_schedule_additional_type`;
CREATE TABLE `pcc_schedule_additional_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `pcc_schedule_id` int(11) NOT NULL,
  `additional_info_type_id` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of pcc_schedule_additional_type
-- ----------------------------
BEGIN;
INSERT INTO `pcc_schedule_additional_type` VALUES (1, 24, 1);
INSERT INTO `pcc_schedule_additional_type` VALUES (2, 24, 2);
INSERT INTO `pcc_schedule_additional_type` VALUES (3, 25, 1);
INSERT INTO `pcc_schedule_additional_type` VALUES (4, 26, 1);
INSERT INTO `pcc_schedule_additional_type` VALUES (5, 27, 2);
INSERT INTO `pcc_schedule_additional_type` VALUES (6, 28, 1);
INSERT INTO `pcc_schedule_additional_type` VALUES (7, 29, 2);
INSERT INTO `pcc_schedule_additional_type` VALUES (8, 30, 1);
INSERT INTO `pcc_schedule_additional_type` VALUES (9, 30, 2);
INSERT INTO `pcc_schedule_additional_type` VALUES (10, 31, 1);
INSERT INTO `pcc_schedule_additional_type` VALUES (11, 31, 2);
INSERT INTO `pcc_schedule_additional_type` VALUES (12, 32, 1);
INSERT INTO `pcc_schedule_additional_type` VALUES (13, 32, 2);
INSERT INTO `pcc_schedule_additional_type` VALUES (14, 33, 1);
INSERT INTO `pcc_schedule_additional_type` VALUES (15, 33, 2);
COMMIT;

-- ----------------------------
-- Table structure for pcc_schedule_file
-- ----------------------------
DROP TABLE IF EXISTS `pcc_schedule_file`;
CREATE TABLE `pcc_schedule_file` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `pcc_user_id` int(11) DEFAULT NULL,
  `pcc_schedule_id` int(11) DEFAULT NULL,
  `pcc_file_id` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of pcc_schedule_file
-- ----------------------------
BEGIN;
INSERT INTO `pcc_schedule_file` VALUES (2, 1, 27, 34);
INSERT INTO `pcc_schedule_file` VALUES (3, 1, 24, 35);
INSERT INTO `pcc_schedule_file` VALUES (4, 1, 29, 36);
INSERT INTO `pcc_schedule_file` VALUES (5, 1, 30, 37);
INSERT INTO `pcc_schedule_file` VALUES (6, 1, 30, 38);
INSERT INTO `pcc_schedule_file` VALUES (7, 2, 30, 39);
INSERT INTO `pcc_schedule_file` VALUES (8, 1, 31, 40);
INSERT INTO `pcc_schedule_file` VALUES (9, 2, 32, 41);
COMMIT;

-- ----------------------------
-- Table structure for pcc_schedule_remind
-- ----------------------------
DROP TABLE IF EXISTS `pcc_schedule_remind`;
CREATE TABLE `pcc_schedule_remind` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `pcc_schedule_id` int(11) NOT NULL COMMENT '任务id',
  `pcc_remind_service_id` int(11) NOT NULL COMMENT '提醒服务id',
  `is_execute` char(4) DEFAULT '否' COMMENT '是否执行',
  `result` varchar(255) DEFAULT NULL COMMENT '提醒服务执行结果',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=44 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of pcc_schedule_remind
-- ----------------------------
BEGIN;
INSERT INTO `pcc_schedule_remind` VALUES (1, 14, 1, NULL, NULL);
INSERT INTO `pcc_schedule_remind` VALUES (2, 14, 2, NULL, NULL);
INSERT INTO `pcc_schedule_remind` VALUES (3, 15, 1, NULL, NULL);
INSERT INTO `pcc_schedule_remind` VALUES (4, 15, 2, NULL, NULL);
INSERT INTO `pcc_schedule_remind` VALUES (5, 15, 3, NULL, NULL);
INSERT INTO `pcc_schedule_remind` VALUES (6, 16, 1, NULL, NULL);
INSERT INTO `pcc_schedule_remind` VALUES (7, 16, 3, NULL, NULL);
INSERT INTO `pcc_schedule_remind` VALUES (8, 16, 2, NULL, NULL);
INSERT INTO `pcc_schedule_remind` VALUES (9, 17, 1, NULL, NULL);
INSERT INTO `pcc_schedule_remind` VALUES (10, 17, 2, NULL, NULL);
INSERT INTO `pcc_schedule_remind` VALUES (11, 18, 1, NULL, NULL);
INSERT INTO `pcc_schedule_remind` VALUES (12, 18, 2, NULL, NULL);
INSERT INTO `pcc_schedule_remind` VALUES (13, 19, 1, NULL, NULL);
INSERT INTO `pcc_schedule_remind` VALUES (14, 19, 2, NULL, NULL);
INSERT INTO `pcc_schedule_remind` VALUES (15, 20, 1, NULL, NULL);
INSERT INTO `pcc_schedule_remind` VALUES (16, 20, 2, NULL, NULL);
INSERT INTO `pcc_schedule_remind` VALUES (17, 21, 1, NULL, NULL);
INSERT INTO `pcc_schedule_remind` VALUES (18, 21, 2, NULL, NULL);
INSERT INTO `pcc_schedule_remind` VALUES (19, 21, 3, NULL, NULL);
INSERT INTO `pcc_schedule_remind` VALUES (20, 22, 1, NULL, NULL);
INSERT INTO `pcc_schedule_remind` VALUES (21, 22, 2, NULL, NULL);
INSERT INTO `pcc_schedule_remind` VALUES (22, 23, 1, NULL, NULL);
INSERT INTO `pcc_schedule_remind` VALUES (23, 23, 2, NULL, NULL);
INSERT INTO `pcc_schedule_remind` VALUES (24, 24, 1, NULL, NULL);
INSERT INTO `pcc_schedule_remind` VALUES (25, 24, 2, NULL, NULL);
INSERT INTO `pcc_schedule_remind` VALUES (26, 25, 1, NULL, NULL);
INSERT INTO `pcc_schedule_remind` VALUES (27, 25, 2, NULL, NULL);
INSERT INTO `pcc_schedule_remind` VALUES (28, 26, 1, NULL, NULL);
INSERT INTO `pcc_schedule_remind` VALUES (29, 26, 2, NULL, NULL);
INSERT INTO `pcc_schedule_remind` VALUES (30, 27, 1, NULL, NULL);
INSERT INTO `pcc_schedule_remind` VALUES (31, 27, 2, NULL, NULL);
INSERT INTO `pcc_schedule_remind` VALUES (32, 28, 1, NULL, NULL);
INSERT INTO `pcc_schedule_remind` VALUES (33, 28, 2, NULL, NULL);
INSERT INTO `pcc_schedule_remind` VALUES (34, 29, 1, NULL, NULL);
INSERT INTO `pcc_schedule_remind` VALUES (35, 29, 2, NULL, NULL);
INSERT INTO `pcc_schedule_remind` VALUES (36, 30, 1, NULL, NULL);
INSERT INTO `pcc_schedule_remind` VALUES (37, 30, 2, NULL, NULL);
INSERT INTO `pcc_schedule_remind` VALUES (38, 31, 1, NULL, NULL);
INSERT INTO `pcc_schedule_remind` VALUES (39, 31, 2, NULL, NULL);
INSERT INTO `pcc_schedule_remind` VALUES (40, 32, 1, NULL, NULL);
INSERT INTO `pcc_schedule_remind` VALUES (41, 32, 2, NULL, NULL);
INSERT INTO `pcc_schedule_remind` VALUES (42, 33, 1, NULL, NULL);
INSERT INTO `pcc_schedule_remind` VALUES (43, 33, 2, NULL, NULL);
COMMIT;

-- ----------------------------
-- Table structure for pcc_schedule_text
-- ----------------------------
DROP TABLE IF EXISTS `pcc_schedule_text`;
CREATE TABLE `pcc_schedule_text` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `pcc_schedule_id` int(11) DEFAULT NULL,
  `pcc_user_id` int(11) DEFAULT NULL,
  `pcc_text_id` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of pcc_schedule_text
-- ----------------------------
BEGIN;
INSERT INTO `pcc_schedule_text` VALUES (1, 24, 1, 1);
INSERT INTO `pcc_schedule_text` VALUES (2, 26, 1, 2);
INSERT INTO `pcc_schedule_text` VALUES (3, 25, 1, 3);
INSERT INTO `pcc_schedule_text` VALUES (4, 28, 1, 4);
INSERT INTO `pcc_schedule_text` VALUES (5, 30, 1, 5);
INSERT INTO `pcc_schedule_text` VALUES (6, 30, 2, 6);
INSERT INTO `pcc_schedule_text` VALUES (7, 25, 2, 7);
INSERT INTO `pcc_schedule_text` VALUES (8, 31, 1, 8);
INSERT INTO `pcc_schedule_text` VALUES (9, 32, 2, 9);
COMMIT;

-- ----------------------------
-- Table structure for pcc_schedule_user
-- ----------------------------
DROP TABLE IF EXISTS `pcc_schedule_user`;
CREATE TABLE `pcc_schedule_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `pcc_schedule_id` int(11) NOT NULL COMMENT '任务主键',
  `pcc_user_id` int(11) NOT NULL COMMENT '用户主键',
  `is_complete` char(3) DEFAULT '否' COMMENT '是否完成',
  `complete_date` datetime DEFAULT NULL COMMENT '完成时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=40 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of pcc_schedule_user
-- ----------------------------
BEGIN;
INSERT INTO `pcc_schedule_user` VALUES (1, 14, 1, '否', NULL);
INSERT INTO `pcc_schedule_user` VALUES (2, 15, 1, '否', NULL);
INSERT INTO `pcc_schedule_user` VALUES (3, 15, 2, '是', NULL);
INSERT INTO `pcc_schedule_user` VALUES (4, 16, 1, '否', NULL);
INSERT INTO `pcc_schedule_user` VALUES (5, 16, 1, '否', NULL);
INSERT INTO `pcc_schedule_user` VALUES (6, 16, 1, '否', NULL);
INSERT INTO `pcc_schedule_user` VALUES (7, 16, 1, '否', NULL);
INSERT INTO `pcc_schedule_user` VALUES (8, 16, 2, '否', NULL);
INSERT INTO `pcc_schedule_user` VALUES (9, 17, 1, '否', NULL);
INSERT INTO `pcc_schedule_user` VALUES (10, 17, 1, '否', NULL);
INSERT INTO `pcc_schedule_user` VALUES (11, 17, 1, '否', NULL);
INSERT INTO `pcc_schedule_user` VALUES (12, 17, 1, '否', NULL);
INSERT INTO `pcc_schedule_user` VALUES (13, 17, 1, '否', NULL);
INSERT INTO `pcc_schedule_user` VALUES (14, 17, 1, '否', NULL);
INSERT INTO `pcc_schedule_user` VALUES (15, 17, 2, '否', NULL);
INSERT INTO `pcc_schedule_user` VALUES (16, 18, 2, '否', NULL);
INSERT INTO `pcc_schedule_user` VALUES (17, 18, 1, '否', NULL);
INSERT INTO `pcc_schedule_user` VALUES (18, 19, 2, '否', NULL);
INSERT INTO `pcc_schedule_user` VALUES (19, 20, 2, '否', NULL);
INSERT INTO `pcc_schedule_user` VALUES (20, 21, 2, '否', NULL);
INSERT INTO `pcc_schedule_user` VALUES (21, 22, 2, '否', NULL);
INSERT INTO `pcc_schedule_user` VALUES (22, 22, 1, '否', NULL);
INSERT INTO `pcc_schedule_user` VALUES (23, 23, 1, '否', NULL);
INSERT INTO `pcc_schedule_user` VALUES (24, 23, 2, '否', NULL);
INSERT INTO `pcc_schedule_user` VALUES (25, 24, 1, '是', '2019-01-20 15:44:27');
INSERT INTO `pcc_schedule_user` VALUES (26, 25, 1, '是', '2019-01-20 16:10:43');
INSERT INTO `pcc_schedule_user` VALUES (27, 25, 2, '是', '2019-01-23 23:40:11');
INSERT INTO `pcc_schedule_user` VALUES (28, 26, 1, '是', '2019-01-20 16:01:24');
INSERT INTO `pcc_schedule_user` VALUES (29, 27, 1, '是', '2019-01-20 15:43:33');
INSERT INTO `pcc_schedule_user` VALUES (30, 28, 1, '是', '2019-01-20 16:16:21');
INSERT INTO `pcc_schedule_user` VALUES (31, 29, 1, '是', '2019-01-20 16:20:12');
INSERT INTO `pcc_schedule_user` VALUES (32, 30, 1, '是', '2019-01-20 19:20:57');
INSERT INTO `pcc_schedule_user` VALUES (33, 30, 2, '是', '2019-01-23 23:14:56');
INSERT INTO `pcc_schedule_user` VALUES (34, 31, 1, '是', '2019-01-24 17:53:20');
INSERT INTO `pcc_schedule_user` VALUES (35, 31, 2, '否', NULL);
INSERT INTO `pcc_schedule_user` VALUES (36, 32, 1, '是', '2019-01-25 18:09:44');
INSERT INTO `pcc_schedule_user` VALUES (37, 32, 2, '是', '2019-01-24 17:54:00');
INSERT INTO `pcc_schedule_user` VALUES (38, 33, 2, '否', NULL);
INSERT INTO `pcc_schedule_user` VALUES (39, 33, 1, '否', NULL);
COMMIT;

-- ----------------------------
-- Table structure for pcc_text
-- ----------------------------
DROP TABLE IF EXISTS `pcc_text`;
CREATE TABLE `pcc_text` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `text` varchar(2000) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of pcc_text
-- ----------------------------
BEGIN;
INSERT INTO `pcc_text` VALUES (1, 'haha');
INSERT INTO `pcc_text` VALUES (2, 'text');
INSERT INTO `pcc_text` VALUES (3, 'text');
INSERT INTO `pcc_text` VALUES (4, 'text');
INSERT INTO `pcc_text` VALUES (5, 'ddddd');
INSERT INTO `pcc_text` VALUES (6, 'dddd');
INSERT INTO `pcc_text` VALUES (7, '测试就测试啊');
INSERT INTO `pcc_text` VALUES (8, '得得得得');
INSERT INTO `pcc_text` VALUES (9, '发发发');
COMMIT;

-- ----------------------------
-- Table structure for pcc_user
-- ----------------------------
DROP TABLE IF EXISTS `pcc_user`;
CREATE TABLE `pcc_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(20) NOT NULL COMMENT '姓名',
  `sex` varchar(10) DEFAULT NULL COMMENT '性别',
  `email` varchar(50) DEFAULT NULL COMMENT '邮箱',
  `phone` varchar(11) NOT NULL COMMENT '电话号码，唯一',
  `password` varchar(20) NOT NULL COMMENT '密码',
  `pcc_file_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of pcc_user
-- ----------------------------
BEGIN;
INSERT INTO `pcc_user` VALUES (1, '袁李', '女', 'yl123456789lxp@163.com', '18883707422', '1', 63);
INSERT INTO `pcc_user` VALUES (2, '刘旭苹', '女', 'zjyx15202815@163.com', '17785215955', '1', 62);
COMMIT;

-- ----------------------------
-- Table structure for pcc_user_friend
-- ----------------------------
DROP TABLE IF EXISTS `pcc_user_friend`;
CREATE TABLE `pcc_user_friend` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `pcc_user_id` int(11) NOT NULL,
  `friend_pcc_user_id` int(11) NOT NULL,
  `remark` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of pcc_user_friend
-- ----------------------------
BEGIN;
INSERT INTO `pcc_user_friend` VALUES (1, 1, 2, 'Lover');
INSERT INTO `pcc_user_friend` VALUES (2, 2, 1, 'Lover');
COMMIT;

-- ----------------------------
-- View structure for pcc_schedule_file_text_view
-- ----------------------------
DROP VIEW IF EXISTS `pcc_schedule_file_text_view`;
CREATE ALGORITHM = UNDEFINED SQL SECURITY DEFINER VIEW `pcc_schedule_file_text_view` AS select `psfv`.`scheduleId` AS `scheduleId`,`psfv`.`userId` AS `userId`,`psfv`.`fileIds` AS `fileIds`,`psfv`.`fileNames` AS `fileNames`,`psfv`.`urls` AS `urls`,`psfv`.`sizes` AS `sizes`,`pstv`.`textIds` AS `textIds`,`pstv`.`textes` AS `textes` from (`pcc_schedule_file_view` `psfv` left join `pcc_schedule_text_view` `pstv` on(((`pstv`.`scheduleId` = `psfv`.`scheduleId`) and (`pstv`.`userId` = `psfv`.`userId`)))) union select (case when isnull(`psfv1`.`scheduleId`) then `pstv1`.`scheduleId` else `psfv1`.`scheduleId` end) AS `scheduleId`,(case when isnull(`psfv1`.`userId`) then `pstv1`.`userId` else `psfv1`.`userId` end) AS `userId`,`psfv1`.`fileIds` AS `fileIds`,`psfv1`.`fileNames` AS `fileNames`,`psfv1`.`urls` AS `urls`,`psfv1`.`sizes` AS `sizes`,`pstv1`.`textIds` AS `textIds`,`pstv1`.`textes` AS `textes` from (`pcc_schedule_text_view` `pstv1` left join `pcc_schedule_file_view` `psfv1` on(((`pstv1`.`scheduleId` = `psfv1`.`scheduleId`) and (`pstv1`.`userId` = `psfv1`.`userId`))));

-- ----------------------------
-- View structure for pcc_schedule_file_view
-- ----------------------------
DROP VIEW IF EXISTS `pcc_schedule_file_view`;
CREATE ALGORITHM = UNDEFINED SQL SECURITY DEFINER VIEW `pcc_schedule_file_view` AS select `psf`.`pcc_schedule_id` AS `scheduleId`,`psf`.`pcc_user_id` AS `userId`,group_concat(`pf`.`id` separator ',') AS `fileIds`,group_concat(concat(`pf`.`name`,'.',`pf`.`type`) separator '`') AS `fileNames`,group_concat(`pf`.`url` separator ',') AS `urls`,group_concat(`pf`.`size` separator ',') AS `sizes` from (`pcc_schedule_file` `psf` left join `pcc_file` `pf` on((`pf`.`id` = `psf`.`pcc_file_id`))) group by `psf`.`pcc_schedule_id`,`psf`.`pcc_user_id`;

-- ----------------------------
-- View structure for pcc_schedule_text_view
-- ----------------------------
DROP VIEW IF EXISTS `pcc_schedule_text_view`;
CREATE ALGORITHM = UNDEFINED SQL SECURITY DEFINER VIEW `pcc_schedule_text_view` AS select `pst`.`pcc_schedule_id` AS `scheduleId`,`pst`.`pcc_user_id` AS `userId`,group_concat(`pt`.`id` separator '`') AS `textIds`,group_concat(`pt`.`text` separator '`') AS `textes` from (`pcc_schedule_text` `pst` left join `pcc_text` `pt` on((`pt`.`id` = `pst`.`pcc_text_id`))) group by `pst`.`pcc_schedule_id`,`pst`.`pcc_user_id`;

-- ----------------------------
-- View structure for pcc_schedule_view
-- ----------------------------
DROP VIEW IF EXISTS `pcc_schedule_view`;
CREATE ALGORITHM = UNDEFINED SQL SECURITY DEFINER VIEW `pcc_schedule_view` AS select `dd`.`id` AS `id`,`dd`.`content` AS `content`,`dd`.`creatTime` AS `createTime`,`dd`.`remindTime` AS `remindTime`,`dd`.`createUserName` AS `createUserName`,`dd`.`createUserId` AS `createUserId`,`dd`.`deadline` AS `deadline`,group_concat(`dd`.`receiverId` separator ',') AS `receiverIds`,group_concat(`dd`.`receiverName` separator ',') AS `receiverNames`,count(`dd`.`receiverId`) AS `receiverCount`,group_concat(`dd`.`isComplete` separator ',') AS `isCompletes`,group_concat(`dd`.`psuId` separator ',') AS `psuIds`,group_concat((case when isnull(`dd`.`completeDate`) then 0 else `dd`.`completeDate` end) separator ',') AS `completeDates` from (select `ps`.`id` AS `id`,`ps`.`content` AS `content`,`ps`.`create_time` AS `creatTime`,`ps`.`remind_time` AS `remindTime`,`pu1`.`name` AS `createUserName`,`pu1`.`id` AS `createUserId`,`ps`.`deadline` AS `deadline`,`psu`.`id` AS `psuId`,`psu`.`pcc_user_id` AS `receiverId`,`pu2`.`name` AS `receiverName`,`psu`.`is_complete` AS `isComplete`,`psu`.`complete_date` AS `completeDate` from (((`cc`.`pcc_schedule` `ps` left join `cc`.`pcc_schedule_user` `psu` on((`ps`.`id` = `psu`.`pcc_schedule_id`))) left join `cc`.`pcc_user` `pu1` on((`pu1`.`id` = `ps`.`pcc_user_id`))) left join `cc`.`pcc_user` `pu2` on((`pu2`.`id` = `psu`.`pcc_user_id`))) order by `ps`.`create_time` desc) `dd` group by `dd`.`id` order by `dd`.`creatTime` desc;

SET FOREIGN_KEY_CHECKS = 1;
