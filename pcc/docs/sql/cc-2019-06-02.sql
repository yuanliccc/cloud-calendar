/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 80001
Source Host           : localhost:3306
Source Database       : cc

Target Server Type    : MYSQL
Target Server Version : 80001
File Encoding         : 65001

Date: 2019-06-02 13:57:56
*/

SET FOREIGN_KEY_CHECKS=0;

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
-- Table structure for pcc_chat_info
-- ----------------------------
DROP TABLE IF EXISTS `pcc_chat_info`;
CREATE TABLE `pcc_chat_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `send_user_id` int(11) NOT NULL COMMENT '发送人id',
  `receive_user_id` int(11) NOT NULL COMMENT '接收人id',
  `content` varchar(200) NOT NULL COMMENT '信息内容',
  `send_time` datetime NOT NULL COMMENT '发送时间',
  `is_received` varchar(3) NOT NULL DEFAULT '否' COMMENT '是否接受',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=111 DEFAULT CHARSET=utf8;

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
) ENGINE=InnoDB AUTO_INCREMENT=69 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for pcc_notice
-- ----------------------------
DROP TABLE IF EXISTS `pcc_notice`;
CREATE TABLE `pcc_notice` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `type` varchar(20) NOT NULL COMMENT '提醒类型',
  `api` varchar(100) NOT NULL COMMENT 'api',
  `content` varchar(300) NOT NULL COMMENT '内容，可为json',
  `is_remind` varchar(3) NOT NULL DEFAULT '否' COMMENT '是否提醒查看',
  `time` datetime NOT NULL COMMENT '时间',
  `pcc_user_id` int(11) NOT NULL COMMENT '所属用户',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

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
) ENGINE=InnoDB AUTO_INCREMENT=50 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for pcc_schedule_additional_type
-- ----------------------------
DROP TABLE IF EXISTS `pcc_schedule_additional_type`;
CREATE TABLE `pcc_schedule_additional_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `pcc_schedule_id` int(11) NOT NULL,
  `additional_info_type_id` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=utf8;

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
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;

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
) ENGINE=InnoDB AUTO_INCREMENT=73 DEFAULT CHARSET=utf8;

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
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

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
  `is_remind` varchar(3) DEFAULT '否' COMMENT '是否提醒',
  `is_dead_remind` varchar(3) DEFAULT '否' COMMENT '是否截止提醒',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=57 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for pcc_task
-- ----------------------------
DROP TABLE IF EXISTS `pcc_task`;
CREATE TABLE `pcc_task` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `pcc_user_id` int(11) NOT NULL COMMENT '所属用户',
  `content` varchar(200) NOT NULL COMMENT '日程内容',
  `time` datetime NOT NULL COMMENT '时间',
  `is_remind` varchar(3) DEFAULT '否' COMMENT '是否提醒',
  `is_delete` varchar(3) DEFAULT '否' COMMENT '是否刪除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for pcc_text
-- ----------------------------
DROP TABLE IF EXISTS `pcc_text`;
CREATE TABLE `pcc_text` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `text` varchar(2000) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

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
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

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
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- View structure for pcc_schedule_file_text_view
-- ----------------------------
DROP VIEW IF EXISTS `pcc_schedule_file_text_view`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `pcc_schedule_file_text_view` AS select `psfv`.`scheduleId` AS `scheduleId`,`psfv`.`userId` AS `userId`,`psfv`.`fileIds` AS `fileIds`,`psfv`.`fileNames` AS `fileNames`,`psfv`.`urls` AS `urls`,`psfv`.`sizes` AS `sizes`,`pstv`.`textIds` AS `textIds`,`pstv`.`textes` AS `textes` from (`pcc_schedule_file_view` `psfv` left join `pcc_schedule_text_view` `pstv` on(((`pstv`.`scheduleId` = `psfv`.`scheduleId`) and (`pstv`.`userId` = `psfv`.`userId`)))) union select (case when isnull(`psfv1`.`scheduleId`) then `pstv1`.`scheduleId` else `psfv1`.`scheduleId` end) AS `scheduleId`,(case when isnull(`psfv1`.`userId`) then `pstv1`.`userId` else `psfv1`.`userId` end) AS `userId`,`psfv1`.`fileIds` AS `fileIds`,`psfv1`.`fileNames` AS `fileNames`,`psfv1`.`urls` AS `urls`,`psfv1`.`sizes` AS `sizes`,`pstv1`.`textIds` AS `textIds`,`pstv1`.`textes` AS `textes` from (`pcc_schedule_text_view` `pstv1` left join `pcc_schedule_file_view` `psfv1` on(((`pstv1`.`scheduleId` = `psfv1`.`scheduleId`) and (`pstv1`.`userId` = `psfv1`.`userId`)))) ;

-- ----------------------------
-- View structure for pcc_schedule_file_view
-- ----------------------------
DROP VIEW IF EXISTS `pcc_schedule_file_view`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `pcc_schedule_file_view` AS select `psf`.`pcc_schedule_id` AS `scheduleId`,`psf`.`pcc_user_id` AS `userId`,group_concat(`pf`.`id` separator ',') AS `fileIds`,group_concat(concat(`pf`.`name`,'.',`pf`.`type`) separator '`') AS `fileNames`,group_concat(`pf`.`url` separator ',') AS `urls`,group_concat(`pf`.`size` separator ',') AS `sizes` from (`pcc_schedule_file` `psf` left join `pcc_file` `pf` on((`pf`.`id` = `psf`.`pcc_file_id`))) group by `psf`.`pcc_schedule_id`,`psf`.`pcc_user_id` ;

-- ----------------------------
-- View structure for pcc_schedule_text_view
-- ----------------------------
DROP VIEW IF EXISTS `pcc_schedule_text_view`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `pcc_schedule_text_view` AS select `pst`.`pcc_schedule_id` AS `scheduleId`,`pst`.`pcc_user_id` AS `userId`,group_concat(`pt`.`id` separator '`') AS `textIds`,group_concat(`pt`.`text` separator '`') AS `textes` from (`pcc_schedule_text` `pst` left join `pcc_text` `pt` on((`pt`.`id` = `pst`.`pcc_text_id`))) group by `pst`.`pcc_schedule_id`,`pst`.`pcc_user_id` ;

-- ----------------------------
-- View structure for pcc_schedule_view
-- ----------------------------
DROP VIEW IF EXISTS `pcc_schedule_view`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `pcc_schedule_view` AS select `dd`.`id` AS `id`,`dd`.`content` AS `content`,`dd`.`creatTime` AS `createTime`,`dd`.`remindTime` AS `remindTime`,`dd`.`createUserName` AS `createUserName`,`dd`.`createUserId` AS `createUserId`,`dd`.`deadline` AS `deadline`,group_concat(`dd`.`receiverId` separator ',') AS `receiverIds`,group_concat(`dd`.`receiverName` separator ',') AS `receiverNames`,count(`dd`.`receiverId`) AS `receiverCount`,group_concat(`dd`.`isComplete` separator ',') AS `isCompletes`,group_concat(`dd`.`psuId` separator ',') AS `psuIds`,group_concat((case when isnull(`dd`.`completeDate`) then 0 else `dd`.`completeDate` end) separator ',') AS `completeDates` from (select `ps`.`id` AS `id`,`ps`.`content` AS `content`,`ps`.`create_time` AS `creatTime`,`ps`.`remind_time` AS `remindTime`,`pu1`.`name` AS `createUserName`,`pu1`.`id` AS `createUserId`,`ps`.`deadline` AS `deadline`,`psu`.`id` AS `psuId`,`psu`.`pcc_user_id` AS `receiverId`,`pu2`.`name` AS `receiverName`,`psu`.`is_complete` AS `isComplete`,`psu`.`complete_date` AS `completeDate` from (((`cc`.`pcc_schedule` `ps` left join `cc`.`pcc_schedule_user` `psu` on((`ps`.`id` = `psu`.`pcc_schedule_id`))) left join `cc`.`pcc_user` `pu1` on((`pu1`.`id` = `ps`.`pcc_user_id`))) left join `cc`.`pcc_user` `pu2` on((`pu2`.`id` = `psu`.`pcc_user_id`))) order by `ps`.`create_time` desc) `dd` group by `dd`.`id` order by `dd`.`creatTime` desc ;
