/*
Navicat MySQL Data Transfer

Source Server         : mysql
Source Server Version : 50151
Source Host           : localhost:3306
Source Database       : tleip

Target Server Type    : MYSQL
Target Server Version : 50151
File Encoding         : 65001

Date: 2012-05-09 21:10:53
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `basic_department`
-- ----------------------------
DROP TABLE IF EXISTS `basic_department`;
CREATE TABLE `basic_department` (
  `unit_id` bigint(20) DEFAULT NULL,
  `dept_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `dept_code` varchar(20) NOT NULL,
  `dept_name` varchar(60) NOT NULL,
  `up_dept_code` varchar(20) DEFAULT NULL,
  `REMARK` longtext,
  PRIMARY KEY (`dept_id`)
) ENGINE=InnoDB AUTO_INCREMENT=41 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of basic_department
-- ----------------------------
INSERT INTO `basic_department` VALUES (null, '40', 'jx01', '综合处', '0', '');

-- ----------------------------
-- Table structure for `basic_person`
-- ----------------------------
DROP TABLE IF EXISTS `basic_person`;
CREATE TABLE `basic_person` (
  `person_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `dept_id` bigint(20) DEFAULT NULL,
  `person_code` varchar(20) NOT NULL,
  `person_name` varchar(60) NOT NULL,
  `person_account` varchar(20) NOT NULL,
  `password` varchar(50) NOT NULL,
  `person_type` varchar(1) DEFAULT NULL,
  PRIMARY KEY (`person_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of basic_person
-- ----------------------------
INSERT INTO `basic_person` VALUES ('3', '40', 'feng', 'feng', 'feng', 'AHAH8550778ZG9A10H9AA5NNXZHKHHZK', '1');

-- ----------------------------
-- Table structure for `basic_unit`
-- ----------------------------
DROP TABLE IF EXISTS `basic_unit`;
CREATE TABLE `basic_unit` (
  `UNIT_ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `UNIT_CODE` varchar(20) NOT NULL,
  `UNIT_NAME` varchar(100) NOT NULL,
  `UNIT_MASTER` varchar(40) DEFAULT NULL,
  `POSTCODE` varchar(20) DEFAULT NULL,
  `LINKMAN` varchar(40) DEFAULT NULL,
  `TELEPHONE` varchar(40) DEFAULT NULL,
  `FAX` varchar(40) DEFAULT NULL,
  `EMAIL` varchar(60) DEFAULT NULL,
  `HOMEPAGE` varchar(100) DEFAULT NULL,
  `REMARK` longtext,
  PRIMARY KEY (`UNIT_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of basic_unit
-- ----------------------------
INSERT INTO `basic_unit` VALUES ('2', '10000', '深圳市南山区塘朗小学', '', '', '', '', '', '', '', '');

-- ----------------------------
-- Table structure for `front_message`
-- ----------------------------
DROP TABLE IF EXISTS `front_message`;
CREATE TABLE `front_message` (
  `message_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) NOT NULL,
  `message_content` text,
  `message_date` varchar(20) DEFAULT NULL,
  `face_pic` varchar(20) DEFAULT NULL,
  `head_pic` varchar(20) DEFAULT NULL,
  `reply_content` text,
  `reply_date` varchar(20) DEFAULT NULL,
  `approve_status` tinyint(4) DEFAULT '0',
  `is_top` tinyint(4) DEFAULT '0',
  PRIMARY KEY (`message_id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `front_message_user` FOREIGN KEY (`user_id`) REFERENCES `front_user` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of front_message
-- ----------------------------
INSERT INTO `front_message` VALUES ('2', '1', 'just test 哈哈哈1', '2012-04-23 04:24:47', 'face1.gif', 'pic1.gif', null, null, '1', null);
INSERT INTO `front_message` VALUES ('3', '1', 'just test 哈哈哈2', '2012-04-23 04:24:47', 'face1.gif', 'pic1.gif', null, null, '1', null);
INSERT INTO `front_message` VALUES ('4', '1', 'just test 哈哈哈3', '2012-04-23 04:24:47', 'face1.gif', 'pic1.gif', null, null, '1', null);
INSERT INTO `front_message` VALUES ('5', '1', 'just test 哈哈哈4', '2012-04-23 04:24:47', 'face1.gif', 'pic1.gif', null, null, '1', null);
INSERT INTO `front_message` VALUES ('6', '1', 'just test 哈哈哈5', '2012-04-23 04:24:47', 'face1.gif', 'pic1.gif', null, null, '1', null);
INSERT INTO `front_message` VALUES ('7', '1', 'just test 哈哈哈6', '2012-04-23 04:24:47', 'face1.gif', 'pic1.gif', null, null, '1', null);
INSERT INTO `front_message` VALUES ('8', '1', 'just test 哈哈哈7', '2012-04-23 04:24:47', 'face1.gif', 'pic1.gif', null, null, '1', null);
INSERT INTO `front_message` VALUES ('9', '1', 'just test 哈哈哈8', '2012-04-23 04:24:47', 'face1.gif', 'pic1.gif', null, null, '1', null);
INSERT INTO `front_message` VALUES ('22', '1', '哈哈哈哈我要发表留言', '2012-04-24 04:59:57', 'face1.gif', 'pic1.gif', null, null, '0', '0');
INSERT INTO `front_message` VALUES ('23', '1', '撒旦法阿斯蒂芬阿斯蒂芬阿斯蒂芬阿斯蒂芬阿斯蒂芬', '2012-04-24 05:05:11', 'face1.gif', 'pic1.gif', null, null, '0', '0');
INSERT INTO `front_message` VALUES ('24', '1', '阿斯蒂芬', '2012-04-24 05:05:27', 'face1.gif', 'pic1.gif', null, null, '0', '0');
INSERT INTO `front_message` VALUES ('25', '1', '阿萨德发射点发斯蒂芬斯蒂芬', '2012-04-24 05:07:52', 'face1.gif', 'pic1.gif', null, null, '0', '0');
INSERT INTO `front_message` VALUES ('26', '1', '个人第三个地方', '2012-04-24 05:08:22', 'face1.gif', 'pic1.gif', null, null, '1', '0');
INSERT INTO `front_message` VALUES ('27', '1', '唉 烦呀 我烦呀 改起来痛苦呀~~~', '2012-04-24 07:09:34', 'face5.gif', 'pic4.gif', null, null, '1', '0');
INSERT INTO `front_message` VALUES ('28', '1', 'asdfds 斯蒂芬', '2012-04-24 07:11:28', 'face9.gif', 'pic9.gif', null, null, '1', '0');
INSERT INTO `front_message` VALUES ('29', '1', '阿斯蒂芬', '2012-04-24 07:11:49', 'face16.gif', 'pic2.gif', null, null, '1', '0');
INSERT INTO `front_message` VALUES ('30', '1', '不错哟~', '2012-05-05 11:22:21', 'face8.gif', 'pic9.gif', null, null, '1', '0');

-- ----------------------------
-- Table structure for `front_user`
-- ----------------------------
DROP TABLE IF EXISTS `front_user`;
CREATE TABLE `front_user` (
  `user_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_account` varchar(20) NOT NULL DEFAULT '',
  `user_name` varchar(20) NOT NULL DEFAULT '',
  `password` varchar(32) NOT NULL DEFAULT '',
  `sex` char(2) DEFAULT NULL,
  `user_type` varchar(10) DEFAULT '',
  `birthday` varchar(10) DEFAULT NULL,
  `address` varchar(100) DEFAULT NULL,
  `phone` varchar(20) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `create_time` varchar(20) NOT NULL,
  `update_time` varchar(20) DEFAULT NULL,
  `approve_status` tinyint(4) NOT NULL,
  `available` tinyint(4) NOT NULL COMMENT '禁用状态',
  `record_status` tinyint(4) NOT NULL COMMENT '状态标识（逻辑删除字段）',
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `user_account` (`user_account`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of front_user
-- ----------------------------
INSERT INTO `front_user` VALUES ('1', 'renlei', '任磊', 'AG1XA775ZHGE58E78EE919Z599N0N917', '男', 'teacher', '2012-04-09', '国外', '110', '1124715454@qq.com', '2012-04-20 00:00:00', '2012-04-20 00:00:00', '1', '1', '1');
INSERT INTO `front_user` VALUES ('2', 'test', 'test', 'Z98ZE159GEAH9N7N509XGX8NAEA71GZE', '男', 'teacher', '2012-04-11', 'test', '', 'boleyn@test.com', '2012-04-20 00:00:00', '2012-04-20 00:00:00', '1', '1', '1');
INSERT INTO `front_user` VALUES ('3', 'sss', 'sss', '0ZHK9KZ9059KZ9ZX0NZZX8808XAKNX8A', '男', 'teacher', '2012-04-11', 'test', '', 'sdfsd', '2012-04-20 00:00:00', '2012-04-20 00:00:00', '1', '0', '0');
INSERT INTO `front_user` VALUES ('4', 'sssssss', 'sssssss', 'HEZ51HZ9HZ80Z557Z59EXAZZ97Z99AHN', '男', 'teacher', '2012-04-11', '', '', 'sssssss', '2012-04-20 00:00:00', '2012-04-20 00:00:00', '0', '0', '1');
INSERT INTO `front_user` VALUES ('5', 'sssssssss', 'ssssssss', '9AKKGG97Z9Z59NEENKNHKGEZZ8Z11X98', '男', 'teacher', '2012-04-11', '', '', 'renlei413326889@163.com', '2012-04-20 00:00:00', '2012-04-20 00:00:00', '0', '0', '1');
INSERT INTO `front_user` VALUES ('6', 'boleyn', 'boleyn', 'HAGZK9A91HHHNGG1AZ1GK1XH95E5Z891', '男', 'teacher', '', '', '', 'renlei413326889@163.com', '2012-04-20 00:00:00', '2012-04-20 00:00:00', '1', '1', '0');
INSERT INTO `front_user` VALUES ('7', 'k', 'k', 'XHZ095N9G910K9011XKEXZK7ZAZZ88NX', '男', 'teacher', '2012-05-01', '', '', '1350880086@qq.com', '2012-05-05 02:38:23', null, '0', '1', '1');

-- ----------------------------
-- Table structure for `monitor`
-- ----------------------------
DROP TABLE IF EXISTS `monitor`;
CREATE TABLE `monitor` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `loginfo` text,
  `operate_time` datetime DEFAULT NULL,
  `operator` varchar(60) DEFAULT NULL,
  `class_name` varchar(50) DEFAULT NULL,
  `type_name` varchar(50) DEFAULT NULL,
  `status` char(10) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=46 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of monitor
-- ----------------------------
INSERT INTO `monitor` VALUES ('1', '<font color=\"#ff0000\">feng</font>  添加：电子图书馆', '2012-04-15 12:15:53', 'feng', '资源库', '资源库', 'add');
INSERT INTO `monitor` VALUES ('2', '<font color=\"#ff0000\">feng</font>  添加：K12资源库', '2012-04-15 12:20:34', 'feng', '资源库', '资源库', 'add');
INSERT INTO `monitor` VALUES ('3', '<font color=\"#ff0000\">feng</font>  添加：语文课件库', '2012-04-15 12:21:01', 'feng', '资源库', '资源库', 'add');
INSERT INTO `monitor` VALUES ('4', '<font color=\"#ff0000\">feng</font>  添加：新华英语站', '2012-04-15 12:21:10', 'feng', '资源库', '资源库', 'add');
INSERT INTO `monitor` VALUES ('5', '<font color=\"#ff0000\">feng</font>  添加：校园网FTP', '2012-04-15 12:21:19', 'feng', '资源库', '资源库', 'add');
INSERT INTO `monitor` VALUES ('6', '<font color=\"#ff0000\">feng</font>  添加：视频服务器', '2012-04-15 12:21:26', 'feng', '资源库', '资源库', 'add');
INSERT INTO `monitor` VALUES ('7', '<font color=\"#ff0000\">feng</font>  修改：新华英语站', '2012-04-15 12:23:15', 'feng', '资源库', '资源库', 'update');
INSERT INTO `monitor` VALUES ('8', '<font color=\"#ff0000\">feng</font>  修改：校园网FTP', '2012-04-15 12:23:28', 'feng', '资源库', '资源库', 'update');
INSERT INTO `monitor` VALUES ('9', '<font color=\"#ff0000\">feng</font>  修改：视频服务器', '2012-04-15 12:23:45', 'feng', '资源库', '资源库', 'update');
INSERT INTO `monitor` VALUES ('10', '<font color=\"#ff0000\">feng</font>  修改：语文课件库', '2012-04-15 12:24:11', 'feng', '资源库', '资源库', 'update');
INSERT INTO `monitor` VALUES ('11', '<font color=\"#ff0000\">feng</font>  修改：电子图书馆', '2012-04-15 12:24:27', 'feng', '资源库', '资源库', 'update');
INSERT INTO `monitor` VALUES ('12', '<font color=\"#ff0000\">feng</font>  添加：中华人民共和国教育部', '2012-04-15 15:07:25', 'feng', '友情链接', '友情链接', 'add');
INSERT INTO `monitor` VALUES ('13', '<font color=\"#ff0000\">feng</font>  添加：广东省教育厅', '2012-04-15 15:08:05', 'feng', '友情链接', '友情链接', 'add');
INSERT INTO `monitor` VALUES ('14', '<font color=\"#ff0000\">feng</font>  添加：深圳市教育局', '2012-04-15 15:08:18', 'feng', '友情链接', '友情链接', 'add');
INSERT INTO `monitor` VALUES ('15', '<font color=\"#ff0000\">feng</font>  添加：少年之星', '2012-04-15 15:08:30', 'feng', '友情链接', '友情链接', 'add');
INSERT INTO `monitor` VALUES ('16', '<font color=\"#ff0000\">feng</font>  添加：生命教育', '2012-04-15 15:08:40', 'feng', '友情链接', '友情链接', 'add');
INSERT INTO `monitor` VALUES ('17', '<font color=\"#ff0000\">feng</font>  添加：校长寄语', '2012-04-15 15:51:36', 'feng', '全景塘小', '校长寄语', 'add');
INSERT INTO `monitor` VALUES ('18', '<font color=\"#ff0000\">feng</font>  添加：我校开展向甘肃省地震灾区“送温暖、献爱心”活动', '2012-04-15 16:55:23', 'feng', '塘小新闻', '塘小新闻', 'add');
INSERT INTO `monitor` VALUES ('19', '<font color=\"#ff0000\">feng</font>  添加：塘朗小学党支部红色之旅', '2012-04-15 16:55:35', 'feng', '塘小新闻', '塘小新闻', 'add');
INSERT INTO `monitor` VALUES ('20', '<font color=\"#ff0000\">feng</font>  添加：“微笑迎大运，欢乐庆六一”艺术节展演', '2012-04-15 16:55:47', 'feng', '塘小新闻', '塘小新闻', 'add');
INSERT INTO `monitor` VALUES ('21', '<font color=\"#ff0000\">feng</font>  添加：塘朗小学师生“百笑图”迎大运', '2012-04-15 16:55:59', 'feng', '塘小新闻', '塘小新闻', 'add');
INSERT INTO `monitor` VALUES ('22', '<font color=\"#ff0000\">feng</font>  添加：第四届“环保之星”小学生科技大赛试题', '2012-04-15 16:56:10', 'feng', '塘小新闻', '塘小新闻', 'add');
INSERT INTO `monitor` VALUES ('23', '<font color=\"#ff0000\">feng</font>  添加：共享三个教学工作重要文件。', '2012-04-15 16:56:18', 'feng', '塘小新闻', '塘小新闻', 'add');
INSERT INTO `monitor` VALUES ('24', '<font color=\"#ff0000\">feng</font>  添加：共享三个教学工作重要文件。', '2012-04-15 21:04:35', 'feng', '校园公告', '校园公告', 'add');
INSERT INTO `monitor` VALUES ('25', '<font color=\"#ff0000\">feng</font>  修改：校长寄语', '2012-04-15 21:13:49', 'feng', '全景塘小', '校长寄语', 'update');
INSERT INTO `monitor` VALUES ('26', '<font color=\"#ff0000\">feng</font>  修改：共享三个教学工作重要文件。', '2012-04-17 11:56:04', 'feng', '塘小新闻', '塘小新闻', 'update');
INSERT INTO `monitor` VALUES ('27', '<font color=\"#ff0000\">feng</font>  添加：我校展开献爱心活动', '2012-04-17 11:59:50', 'feng', '塘小新闻', '塘小新闻', 'add');
INSERT INTO `monitor` VALUES ('28', '<font color=\"#ff0000\">feng</font>  修改：K12资源库', '2012-04-17 12:25:52', 'feng', '资源库', '资源库', 'update');
INSERT INTO `monitor` VALUES ('29', '<font color=\"#ff0000\">feng</font>  修改：电子图书馆', '2012-04-17 12:26:08', 'feng', '资源库', '资源库', 'update');
INSERT INTO `monitor` VALUES ('30', '<font color=\"#ff0000\">feng</font>  修改：语文课件库', '2012-04-17 12:26:26', 'feng', '资源库', '资源库', 'update');
INSERT INTO `monitor` VALUES ('31', '<font color=\"#ff0000\">feng</font>  修改：新华英语站', '2012-04-17 12:26:50', 'feng', '资源库', '资源库', 'update');
INSERT INTO `monitor` VALUES ('32', '<font color=\"#ff0000\">feng</font>  修改：校园网FTP', '2012-04-17 12:27:03', 'feng', '资源库', '资源库', 'update');
INSERT INTO `monitor` VALUES ('33', '<font color=\"#ff0000\">feng</font>  修改：视频服务器', '2012-04-17 12:27:20', 'feng', '资源库', '资源库', 'update');
INSERT INTO `monitor` VALUES ('34', '<font color=\"#ff0000\">feng</font>  添加：请上传图片', '2012-04-30 09:26:54', 'feng', '图片链接', '轮播图片', 'add');
INSERT INTO `monitor` VALUES ('35', '<font color=\"#ff0000\">feng</font>  添加：校长', '2012-04-30 11:51:22', 'feng', '图片链接', '校长', 'add');
INSERT INTO `monitor` VALUES ('36', '<font color=\"#ff0000\">feng</font>  添加：副校长', '2012-04-30 11:51:33', 'feng', '图片链接', '副校长', 'add');
INSERT INTO `monitor` VALUES ('37', '<font color=\"#ff0000\">feng</font>  添加：拥抱蓝色的海洋', '2012-04-30 11:54:23', 'feng', '三味德育', '三味德育', 'add');
INSERT INTO `monitor` VALUES ('38', '<font color=\"#ff0000\">feng</font>  添加：关爱生命  安全出行', '2012-04-30 11:54:59', 'feng', '三味德育', '三味德育', 'add');
INSERT INTO `monitor` VALUES ('39', '<font color=\"#ff0000\">feng</font>  添加：我校举行“世界防治结核病日”宣传月活动', '2012-04-30 11:55:06', 'feng', '三味德育', '三味德育', 'add');
INSERT INTO `monitor` VALUES ('40', '<font color=\"#ff0000\">feng</font>  添加：塘朗小学铁路安全法规宣传片', '2012-04-30 11:55:14', 'feng', '三味德育', '三味德育', 'add');
INSERT INTO `monitor` VALUES ('41', '<font color=\"#ff0000\">feng</font>  添加：塘朗小学第三届英语节活动采撷', '2012-04-30 11:55:27', 'feng', '三味德育', '幸福课堂', 'add');
INSERT INTO `monitor` VALUES ('42', '<font color=\"#ff0000\">feng</font>  添加：向丛飞叔叔学习，交一元特殊队费活动', '2012-04-30 13:01:36', 'feng', '三味德育', '生态乐园', 'add');
INSERT INTO `monitor` VALUES ('43', '<font color=\"#ff0000\">feng</font>  添加：联系我们', '2012-05-05 18:01:24', 'feng', '文字链接', '联系我们', 'add');
INSERT INTO `monitor` VALUES ('44', '<font color=\"#ff0000\">feng</font>  修改：联系我们', '2012-05-05 18:01:33', 'feng', '文字链接', '联系我们', 'update');
INSERT INTO `monitor` VALUES ('45', '<font color=\"#ff0000\">feng</font>  添加：联系我们', '2012-05-05 19:13:55', 'feng', '联系我们', '联系我们', 'add');

-- ----------------------------
-- Table structure for `news_ad_manage`
-- ----------------------------
DROP TABLE IF EXISTS `news_ad_manage`;
CREATE TABLE `news_ad_manage` (
  `ad_id` int(11) NOT NULL AUTO_INCREMENT,
  `ad_name` varchar(100) DEFAULT NULL,
  `ad_position` int(11) DEFAULT NULL,
  `ad_type_id` int(11) DEFAULT NULL,
  `ad_img_url` varchar(100) DEFAULT NULL,
  `ad_http_url` varchar(200) DEFAULT NULL,
  `person_code` varchar(20) DEFAULT NULL,
  `ad_announce_date` datetime DEFAULT NULL,
  `ad_validity_date` datetime DEFAULT NULL,
  `if_display` char(1) DEFAULT NULL,
  PRIMARY KEY (`ad_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of news_ad_manage
-- ----------------------------
INSERT INTO `news_ad_manage` VALUES ('1', '2222', '0', '1', '', '222', 'feng', '2012-02-29 16:01:51', '2012-02-29 16:01:51', '1');

-- ----------------------------
-- Table structure for `news_ad_type`
-- ----------------------------
DROP TABLE IF EXISTS `news_ad_type`;
CREATE TABLE `news_ad_type` (
  `class_id` varchar(20) NOT NULL,
  `ad_type_id` int(11) NOT NULL AUTO_INCREMENT,
  `ad_type_name` varchar(100) DEFAULT NULL,
  `ad_height` int(11) DEFAULT NULL,
  `ad_width` int(11) DEFAULT NULL,
  `ad_type_tag` char(1) NOT NULL,
  PRIMARY KEY (`ad_type_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of news_ad_type
-- ----------------------------
INSERT INTO `news_ad_type` VALUES ('100002', '1', '1', '160', '735', '0');

-- ----------------------------
-- Table structure for `news_content_manage`
-- ----------------------------
DROP TABLE IF EXISTS `news_content_manage`;
CREATE TABLE `news_content_manage` (
  `news_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `announce_type` char(1) DEFAULT NULL,
  `news_title` varchar(200) DEFAULT NULL,
  `news_content` longtext,
  `news_keyword` text,
  `news_writer` varchar(50) DEFAULT NULL,
  `news_source` varchar(100) DEFAULT NULL,
  `announce_person` varchar(50) DEFAULT NULL,
  `news_date` datetime DEFAULT NULL,
  `news_template` varchar(100) DEFAULT NULL,
  `html_file_name` varchar(100) DEFAULT NULL,
  `if_img_news` char(1) DEFAULT NULL,
  `img_news_address` varchar(200) DEFAULT NULL,
  `img_news_title` longtext,
  `if_title_img` varchar(1) DEFAULT NULL,
  `if_top_row` char(1) DEFAULT NULL,
  `if_recommend` char(1) DEFAULT NULL,
  `http_url` varchar(200) DEFAULT NULL,
  `annex_address` varchar(200) DEFAULT NULL,
  `if_vod_news` char(1) DEFAULT NULL,
  `display_order_id` int(11) DEFAULT NULL,
  `check_person` varchar(20) DEFAULT NULL,
  `check_date` datetime DEFAULT NULL,
  `check_flag` char(1) DEFAULT NULL,
  `visit_count` int(11) DEFAULT NULL,
  `newsFilesPath` text,
  `dept_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`news_id`)
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of news_content_manage
-- ----------------------------
INSERT INTO `news_content_manage` VALUES ('1', '1', '电子图书馆', '<p>&nbsp;</p>', '', '', '', '3', '2012-04-14 12:15:21', '1', null, null, '', '', null, '0', null, 'http://192.168.10.2:8080/', null, null, '0', null, '2012-04-17 12:26:08', '1', '0', 'client/htmlfiles/20120414121521/', '40');
INSERT INTO `news_content_manage` VALUES ('2', '1', 'K12资源库', '<p>&nbsp;</p>', '', '', '', '3', '2012-04-15 12:20:22', '1', null, null, '', '', null, '0', null, 'http://192.168.10.3/', null, null, '0', null, '2012-04-17 12:25:52', '1', '0', 'client/htmlfiles/20120415122022/', '40');
INSERT INTO `news_content_manage` VALUES ('3', '1', '语文课件库', '<p>&nbsp;</p>', '', '', '', '3', '2012-04-13 12:20:48', '1', null, null, '', '', null, '0', null, 'http://192.168.10.3:82/', null, null, '0', null, '2012-04-17 12:26:26', '1', '0', 'client/htmlfiles/20120413122048/', '40');
INSERT INTO `news_content_manage` VALUES ('4', '1', '新华英语站', '<p>&nbsp;</p>', '', '', '', '3', '2012-04-10 12:21:01', '1', null, null, '', '', null, '0', null, 'http://192.168.10.254:85/', null, null, '0', null, '2012-04-17 12:26:50', '1', '0', 'client/htmlfiles/20120410122101/', '40');
INSERT INTO `news_content_manage` VALUES ('5', '1', '校园网FTP', '<p>&nbsp;</p>', '', '', '', '3', '2012-04-11 12:21:10', '1', null, null, '', '', null, '0', null, 'ftp://192.168.10.2/', null, null, '0', null, '2012-04-17 12:27:03', '1', '0', 'client/htmlfiles/20120411122110/', '40');
INSERT INTO `news_content_manage` VALUES ('6', '1', '视频服务器', '<p>&nbsp;</p>', '', '', '', '3', '2012-04-12 12:21:19', '1', null, null, '', '', null, '0', null, 'mms://192.168.10.4/', null, null, '0', null, '2012-04-17 12:27:20', '1', '0', 'client/htmlfiles/20120412122119/', '40');
INSERT INTO `news_content_manage` VALUES ('7', '1', '中华人民共和国教育部', '<p>&nbsp;</p>', '', '', '', '3', '2012-04-15 15:07:04', '1', null, null, '', null, null, '0', null, '', null, null, '0', null, '2012-04-15 15:07:25', '1', '0', 'client/htmlfiles/20120415150704/', '40');
INSERT INTO `news_content_manage` VALUES ('8', '1', '广东省教育厅', '<p>&nbsp;</p>', '', '', '', '3', '2012-04-15 15:07:26', '1', null, null, '', null, null, '0', null, '', null, null, '0', null, '2012-04-15 15:08:05', '1', '0', 'client/htmlfiles/20120415150726/', '40');
INSERT INTO `news_content_manage` VALUES ('9', '1', '深圳市教育局', '<p>&nbsp;</p>', '', '', '', '3', '2012-04-15 15:08:12', '1', null, null, '', null, null, '0', null, '', null, null, '0', null, '2012-04-15 15:08:18', '1', '0', 'client/htmlfiles/20120415150812/', '40');
INSERT INTO `news_content_manage` VALUES ('10', '1', '少年之星', '<p>&nbsp;</p>', '', '', '', '3', '2012-04-15 15:08:18', '1', null, null, '', null, null, '0', null, '', null, null, '0', null, '2012-04-15 15:08:30', '1', '0', 'client/htmlfiles/20120415150818/', '40');
INSERT INTO `news_content_manage` VALUES ('11', '1', '生命教育', '<p>&nbsp;</p>', '', '', '', '3', '2012-04-15 15:08:30', '1', null, null, '', null, null, '0', null, '', null, null, '0', null, '2012-04-15 15:08:40', '1', '0', 'client/htmlfiles/20120415150830/', '40');
INSERT INTO `news_content_manage` VALUES ('12', '0', '校长寄语', '<p style=\"padding-bottom:0px; padding-left:0px; padding-right:0px; padding-top:0px\">&nbsp;叶小勇，男，1957年生，大学本科，中共党员，中学高级教师。现任深圳市塘朗小学校长兼党支部书记，南山区棋牌协会副会长，中国西部教育顾问，中国少年先锋队工作学会少年儿童信息研究员。担任六年级品德与社会课。1974年参加教育工作，先后担任大队辅导员、团委书记、副校长、校长兼党支部书记。曾七次被评为深圳市优秀教师、先进教育工作者、先进校长；十多次被评为南山区优秀党务工作者、优秀共产党员、德育先进工作者、法制宣传教育先进工作者、安全管理突出贡献个人等；深圳市第四期小学校长提高班“双优学员”；全国中小学棋类教学实验课题优秀个人。撰写的十多篇论文在国家、省、市、区刊物发表并获奖。其中《浅谈棋类教学促进德育的研究》获全国中小学科研论文特等奖。</p>', '校长寄语', '', '', '3', '2012-04-15 15:49:49', '1', 'client/htmlfiles/20120415154949/20120415154949_1334476296265.html', null, '', '', null, '0', null, '', null, null, '0', null, '2012-04-15 21:13:49', '1', '17', 'client/htmlfiles/20120415154949/', '40');
INSERT INTO `news_content_manage` VALUES ('13', '0', '我校开展向甘肃省地震灾区“送温暖、献爱心”活动', '<p>&nbsp;</p>', '我校开展向甘肃省地震灾区“送温暖、献爱心”活动', '', '', '3', '2012-04-15 16:54:53', '1', 'client/htmlfiles/20120415165453/20120415165453_1334480123875.html', null, '', null, null, '0', null, '', null, null, '0', null, '2012-04-15 16:55:23', '1', '0', 'client/htmlfiles/20120415165453/', '40');
INSERT INTO `news_content_manage` VALUES ('14', '0', '塘朗小学党支部红色之旅', '<p>&nbsp;</p>', '塘朗小学党支部红色之旅', '', '', '3', '2012-04-15 16:55:24', '1', 'client/htmlfiles/20120415165524/20120415165524_1334480135171.html', null, '', null, null, '0', null, '', null, null, '0', null, '2012-04-15 16:55:35', '1', '0', 'client/htmlfiles/20120415165524/', '40');
INSERT INTO `news_content_manage` VALUES ('15', '0', '“微笑迎大运，欢乐庆六一”艺术节展演', '<p>&nbsp;</p>', '“微笑迎大运，欢乐庆六一”艺术节展演', '', '', '3', '2012-04-15 16:55:35', '1', 'client/htmlfiles/20120415165535/20120415165535_1334480147328.html', null, '', null, null, '0', null, '', null, null, '0', null, '2012-04-15 16:55:47', '1', '0', 'client/htmlfiles/20120415165535/', '40');
INSERT INTO `news_content_manage` VALUES ('16', '0', '塘朗小学师生“百笑图”迎大运', '<p>&nbsp;</p>', '塘朗小学师生“百笑图”迎大运', '', '', '3', '2012-04-15 16:55:47', '1', 'client/htmlfiles/20120415165547/20120415165547_1334480159234.html', null, '', null, null, '0', null, '', null, null, '0', null, '2012-04-15 16:55:59', '1', '0', 'client/htmlfiles/20120415165547/', '40');
INSERT INTO `news_content_manage` VALUES ('17', '0', '第四届“环保之星”小学生科技大赛试题', '<p>&nbsp;</p>', '第四届“环保之星”小学生科技大赛试题', '', '', '3', '2012-04-15 16:55:59', '1', 'client/htmlfiles/20120415165559/20120415165559_1334480170390.html', null, '', null, null, '0', null, '', null, null, '0', null, '2012-04-15 16:56:10', '1', '0', 'client/htmlfiles/20120415165559/', '40');
INSERT INTO `news_content_manage` VALUES ('18', '0', '共享三个教学工作重要文件。', '<p>&nbsp;共享三个教学工作重要文件。</p>', '共享三个教学工作重要文件。', '', '', '3', '2012-04-15 16:56:10', '1', 'client/htmlfiles/20120415165610/20120415165610_1334480178406.html', null, '', '', null, '0', null, '', null, null, '0', null, '2012-04-17 11:56:04', '1', '39', 'client/htmlfiles/20120415165610/', '40');
INSERT INTO `news_content_manage` VALUES ('19', '0', '共享三个教学工作重要文件。', '<p>&nbsp;</p>', '共享三个教学工作重要文件。', '', '', '3', '2012-04-15 21:04:25', '1', 'client/htmlfiles/20120415210425/20120415210425_1334495075937.html', null, '', null, null, '0', null, '', null, null, '0', null, '2012-04-15 21:04:35', '1', '1', 'client/htmlfiles/20120415210425/', '40');
INSERT INTO `news_content_manage` VALUES ('20', '0', '我校展开献爱心活动', '<p>&nbsp;&nbsp;&nbsp;&nbsp;塘朗小学坐落在风景秀丽的深圳大学城畔，巍巍塘朗山下，是南山区属完全小学。原名“上塘小学”，1952年由三所私塾合并成立。1958年更名为“塘朗小学”，一直沿用至今。<br />　　学校成立后，历届学校领导都注意端正办学思想，始终坚持德、智、体全面发展的教育方针，始终坚持建设一支素质高，稳定性强，治学严谨的教师队伍，始终坚持在教育教学改革锐意进取，不断创新，把学生培养成为全面发展的社会主义建设人才。这是塘朗小学办学的重要特征。<br />　　党的十一届三中全会后，塘朗小学进入稳定发展的时期。学校进一步贯彻党的教育方针，开展教育教学改革。以按照“三个面向”、“义务教育法”和德、智、体、美、劳全面发展的精神，为提高全民族素质和培养优秀人才作出贡献为办学指导思想。从严管理，依法治校，培育了“敬业”、“创新”、“严谨”、“求实”的教风和“尊师”、“守纪”、“勤学”、“奋进”的学风。<br />　　1986年，由于原有校舍破旧不堪，在上级领导的关怀下，学校易址新建。热心教育事业的郑籍天先生捐资修建了一幢二层的教学楼。1991年,建造了四层的教学大楼一幢。1992年，在塘朗、福光、长源三个村委的支持下顺利征得新运动场用地。1994年，在教育局支持下，建起教工宿舍楼，教工餐厅，200米跑道带足球场运动场。新建了电教室，校医室、宣传栏，升旗台、体育器材室，充实了6800册图书，新建教工俱乐部，安装了卫星天线地面接收系统。学校占地面积达到24788平方米，是当时南山区内占地面积较大的少数几个学校之一。至此，学校初具规模，教学质量也日益提高。<br />　　在改善办学条件的同时，学校始终坚持把德育工作放在一切工作的首位,坚持教书育人、服务育人、管理育人。在德育工作中采用了“三位一体”的教育模式。同时不断加强校外德育网络和德育基地的建设，逐步完善“学校、社会、家庭”三结合教育网络。坚持以教学工作为中心，学校提倡探索教学规律，改进教学方法虚心向外校学习。教学工作狠抓“双基”，注重学生学习能力和学习习惯的培养，还特别重视第二课堂活动的开展，在抓普及面的同时，强调抓特色，逐渐形成了美育特色和体育特色。狠抓教学常规管理，制定了《塘朗小学教学、课堂常规》、《塘朗小学教研常规、科组活动常规》等，建立教师教学工作手册制度。</p><p>&nbsp;</p>', '我校展开献爱心活动', '', '', '3', '2012-04-17 11:59:27', '1', 'client/htmlfiles/20120417115927/20120417115927_1334635190140.html', null, '', null, null, '0', null, '', null, null, '0', null, '2012-04-17 11:59:50', '1', '12', 'client/htmlfiles/20120417115927/', '40');
INSERT INTO `news_content_manage` VALUES ('21', '0', '请上传图片', '', '请上传图片', '', '', '3', '2012-04-30 09:26:38', '1', 'client/htmlfiles/20120430092638/20120430092638_1335749214656.html', null, null, null, null, '0', null, '', null, null, '0', null, '2012-04-30 09:26:54', '1', '0', 'client/htmlfiles/20120430092638/', '40');
INSERT INTO `news_content_manage` VALUES ('22', '0', '校长', '', '校长', '', '', '3', '2012-04-30 11:51:11', '1', 'client/htmlfiles/20120430115111/20120430115111_1335757882203.html', null, null, null, null, '0', null, '', null, null, '0', null, '2012-04-30 11:51:22', '1', '0', 'client/htmlfiles/20120430115111/', '40');
INSERT INTO `news_content_manage` VALUES ('23', '0', '副校长', '', '副校长', '', '', '3', '2012-04-30 11:51:25', '1', 'client/htmlfiles/20120430115125/20120430115125_1335757893828.html', null, null, null, null, '0', null, '', null, null, '0', null, '2012-04-30 11:51:33', '1', '0', 'client/htmlfiles/20120430115125/', '40');
INSERT INTO `news_content_manage` VALUES ('24', '0', '拥抱蓝色的海洋', '', '拥抱蓝色的海洋', '', '', '3', '2012-04-30 11:52:59', '1', 'client/htmlfiles/20120430115259/20120430115259_1335758062984.html', null, null, null, null, '0', null, '', null, null, '0', null, '2012-04-30 11:54:22', '1', '0', 'client/htmlfiles/20120430115259/', '40');
INSERT INTO `news_content_manage` VALUES ('25', '0', '关爱生命  安全出行', '', '关爱生命  安全出行', '', '', '3', '2012-04-30 11:54:23', '1', 'client/htmlfiles/20120430115423/20120430115423_1335758099171.html', null, null, null, null, '0', null, '', null, null, '0', null, '2012-04-30 11:54:59', '1', '0', 'client/htmlfiles/20120430115423/', '40');
INSERT INTO `news_content_manage` VALUES ('26', '0', '我校举行“世界防治结核病日”宣传月活动', '', '我校举行“世界防治结核病日”宣传月活动', '', '', '3', '2012-04-30 11:54:59', '1', 'client/htmlfiles/20120430115459/20120430115459_1335758106828.html', null, null, null, null, '0', null, '', null, null, '0', null, '2012-04-30 11:55:06', '1', '0', 'client/htmlfiles/20120430115459/', '40');
INSERT INTO `news_content_manage` VALUES ('27', '0', '塘朗小学铁路安全法规宣传片', '', '塘朗小学铁路安全法规宣传片', '', '', '3', '2012-04-30 11:55:07', '1', 'client/htmlfiles/20120430115507/20120430115507_1335758114531.html', null, null, null, null, '0', null, '', null, null, '0', null, '2012-04-30 11:55:14', '1', '1', 'client/htmlfiles/20120430115507/', '40');
INSERT INTO `news_content_manage` VALUES ('28', '0', '塘朗小学第三届英语节活动采撷', '', '塘朗小学第三届英语节活动采撷', '', '', '3', '2012-04-30 11:55:25', '1', 'client/htmlfiles/20120430115525/20120430115525_1335758127468.html', null, null, null, null, '0', null, '', null, null, '0', null, '2012-04-30 11:55:27', '1', '0', 'client/htmlfiles/20120430115525/', '40');
INSERT INTO `news_content_manage` VALUES ('29', '0', '向丛飞叔叔学习，交一元特殊队费活动', '', '向丛飞叔叔学习，交一元特殊队费活动', '', '', '3', '2012-04-30 13:01:32', '1', 'client/htmlfiles/20120430130132/20120430130132_1335762096921.html', null, null, null, null, '0', null, '', null, null, '0', null, '2012-04-30 13:01:36', '1', '7', 'client/htmlfiles/20120430130132/', '40');
INSERT INTO `news_content_manage` VALUES ('30', '0', '联系我们', '联系我们联系我们联系我们联系我们联系我们联系我们联系我们', '联系我们', '', '', '3', '2012-05-05 18:01:11', '1', 'client/htmlfiles/20120505180111/20120505180111_1336212084437.html', null, null, '', null, '0', null, '', null, null, '0', null, '2012-05-05 18:01:33', '1', '0', 'client/htmlfiles/20120505180111/', '40');
INSERT INTO `news_content_manage` VALUES ('31', '0', '联系我们', '联系我们', '联系我们', '', '', '3', '2012-05-05 19:13:40', '1', 'client/htmlfiles/20120505191340/20120505191340_1336216435843.html', null, null, null, null, '0', null, '', null, null, '0', null, '2012-05-05 19:13:55', '1', '1', 'client/htmlfiles/20120505191340/', '40');

-- ----------------------------
-- Table structure for `news_content_review`
-- ----------------------------
DROP TABLE IF EXISTS `news_content_review`;
CREATE TABLE `news_content_review` (
  `news_id` bigint(20) DEFAULT NULL,
  `review_id` int(11) NOT NULL,
  `review_user` varchar(50) DEFAULT NULL,
  `review_content` longtext,
  `review_date` datetime DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `review_ip` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`review_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of news_content_review
-- ----------------------------

-- ----------------------------
-- Table structure for `news_items_contents`
-- ----------------------------
DROP TABLE IF EXISTS `news_items_contents`;
CREATE TABLE `news_items_contents` (
  `type_id` int(11) NOT NULL,
  `news_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of news_items_contents
-- ----------------------------
INSERT INTO `news_items_contents` VALUES ('39', '7');
INSERT INTO `news_items_contents` VALUES ('39', '8');
INSERT INTO `news_items_contents` VALUES ('39', '9');
INSERT INTO `news_items_contents` VALUES ('39', '10');
INSERT INTO `news_items_contents` VALUES ('39', '11');
INSERT INTO `news_items_contents` VALUES ('34', '13');
INSERT INTO `news_items_contents` VALUES ('34', '14');
INSERT INTO `news_items_contents` VALUES ('34', '15');
INSERT INTO `news_items_contents` VALUES ('34', '16');
INSERT INTO `news_items_contents` VALUES ('34', '17');
INSERT INTO `news_items_contents` VALUES ('35', '19');
INSERT INTO `news_items_contents` VALUES ('2', '12');
INSERT INTO `news_items_contents` VALUES ('34', '18');
INSERT INTO `news_items_contents` VALUES ('34', '20');
INSERT INTO `news_items_contents` VALUES ('41', '2');
INSERT INTO `news_items_contents` VALUES ('41', '1');
INSERT INTO `news_items_contents` VALUES ('41', '3');
INSERT INTO `news_items_contents` VALUES ('41', '4');
INSERT INTO `news_items_contents` VALUES ('41', '5');
INSERT INTO `news_items_contents` VALUES ('41', '6');
INSERT INTO `news_items_contents` VALUES ('40', '21');
INSERT INTO `news_items_contents` VALUES ('45', '22');
INSERT INTO `news_items_contents` VALUES ('46', '23');
INSERT INTO `news_items_contents` VALUES ('36', '24');
INSERT INTO `news_items_contents` VALUES ('36', '25');
INSERT INTO `news_items_contents` VALUES ('36', '26');
INSERT INTO `news_items_contents` VALUES ('36', '27');
INSERT INTO `news_items_contents` VALUES ('42', '28');
INSERT INTO `news_items_contents` VALUES ('43', '29');
INSERT INTO `news_items_contents` VALUES ('49', '30');
INSERT INTO `news_items_contents` VALUES ('44', '31');

-- ----------------------------
-- Table structure for `news_item_big`
-- ----------------------------
DROP TABLE IF EXISTS `news_item_big`;
CREATE TABLE `news_item_big` (
  `class_id` varchar(20) NOT NULL,
  `class_name` varchar(50) DEFAULT NULL,
  `order_id` int(11) DEFAULT NULL,
  `if_display` char(1) DEFAULT NULL,
  `if_have_ad` char(1) DEFAULT NULL,
  `yxdm` varchar(5) NOT NULL,
  `if_index` int(11) DEFAULT NULL,
  PRIMARY KEY (`class_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of news_item_big
-- ----------------------------
INSERT INTO `news_item_big` VALUES ('1000001', '全景塘小', '1', '1', '1', '10000', '1');
INSERT INTO `news_item_big` VALUES ('1000002', '名师风采', '2', '1', '1', '10000', '1');
INSERT INTO `news_item_big` VALUES ('1000003', '警德瞭望', '3', '1', '1', '10000', '1');
INSERT INTO `news_item_big` VALUES ('1000004', '智慧教学', '4', '1', '1', '10000', '1');
INSERT INTO `news_item_big` VALUES ('1000005', '塘小特色', '5', '1', '1', '10000', '1');
INSERT INTO `news_item_big` VALUES ('1000006', '校务公开', '6', '1', '1', '10000', '1');
INSERT INTO `news_item_big` VALUES ('1000007', '满天星文学社', '7', '1', '1', '10000', '1');
INSERT INTO `news_item_big` VALUES ('1000010', '塘小新闻', '10', '1', '1', '10000', '0');
INSERT INTO `news_item_big` VALUES ('1000011', '校园公告', '11', '1', '1', '10000', '0');
INSERT INTO `news_item_big` VALUES ('1000012', '三味德育', '12', '1', '1', '10000', '0');
INSERT INTO `news_item_big` VALUES ('1000013', '联系我们', '13', '1', '1', '10000', '0');
INSERT INTO `news_item_big` VALUES ('1000015', '友情链接', '15', '1', '1', '10000', '0');
INSERT INTO `news_item_big` VALUES ('1000016', '图片链接', '16', '1', '1', '10000', '0');
INSERT INTO `news_item_big` VALUES ('1000017', '资源库', '18', '1', '1', '10000', '0');
INSERT INTO `news_item_big` VALUES ('1000018', '文字链接', '17', '1', '1', '10000', '0');

-- ----------------------------
-- Table structure for `news_item_config`
-- ----------------------------
DROP TABLE IF EXISTS `news_item_config`;
CREATE TABLE `news_item_config` (
  `news_config_id` int(11) NOT NULL AUTO_INCREMENT,
  `type_id` int(11) DEFAULT NULL,
  `display_row_count` int(11) DEFAULT NULL,
  `title_character_count` int(11) DEFAULT NULL,
  `title_img_url` varchar(200) DEFAULT NULL,
  `date_format` varchar(30) DEFAULT NULL,
  `more_row_count` int(11) DEFAULT NULL,
  `more_title_count` int(11) DEFAULT NULL,
  `more_title_img_url` varchar(200) DEFAULT NULL,
  `more_date_format` varchar(30) DEFAULT NULL,
  `if_pop_window` char(1) DEFAULT NULL,
  `if_permission_search` char(1) DEFAULT NULL,
  `new_flag_time` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`news_config_id`)
) ENGINE=InnoDB AUTO_INCREMENT=50 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of news_item_config
-- ----------------------------
INSERT INTO `news_item_config` VALUES ('1', '1', '10', '20', null, null, '0', '40', null, null, '0', '1', '0');
INSERT INTO `news_item_config` VALUES ('2', '2', '10', '20', null, null, '0', '285', null, null, '0', '1', '0');
INSERT INTO `news_item_config` VALUES ('3', '3', '10', '20', null, null, '0', '40', null, null, '0', '1', '0');
INSERT INTO `news_item_config` VALUES ('4', '4', '10', '20', null, null, '0', '40', null, null, '0', '1', '0');
INSERT INTO `news_item_config` VALUES ('5', '5', '10', '20', null, null, '0', '40', null, null, '0', '1', '0');
INSERT INTO `news_item_config` VALUES ('6', '6', '10', '20', null, null, '0', '40', null, null, '0', '1', '0');
INSERT INTO `news_item_config` VALUES ('7', '7', '10', '20', null, null, '0', '40', null, null, '0', '1', '0');
INSERT INTO `news_item_config` VALUES ('8', '8', '10', '20', null, null, '0', '40', null, null, '0', '1', '0');
INSERT INTO `news_item_config` VALUES ('9', '9', '10', '20', null, null, '0', '40', null, null, '0', '1', '0');
INSERT INTO `news_item_config` VALUES ('10', '10', '10', '20', null, null, '0', '40', null, null, '0', '1', '0');
INSERT INTO `news_item_config` VALUES ('11', '11', '10', '20', null, null, '0', '40', null, null, '0', '1', '0');
INSERT INTO `news_item_config` VALUES ('12', '12', '10', '20', null, null, '0', '40', null, null, '0', '1', '0');
INSERT INTO `news_item_config` VALUES ('13', '13', '10', '20', null, null, '0', '40', null, null, '0', '1', '0');
INSERT INTO `news_item_config` VALUES ('14', '14', '10', '20', null, null, '0', '40', null, null, '0', '1', '0');
INSERT INTO `news_item_config` VALUES ('15', '15', '10', '20', null, null, '0', '40', null, null, '0', '1', '0');
INSERT INTO `news_item_config` VALUES ('16', '16', '10', '20', null, null, '0', '40', null, null, '0', '1', '0');
INSERT INTO `news_item_config` VALUES ('17', '17', '10', '20', null, null, '0', '40', null, null, '0', '1', '0');
INSERT INTO `news_item_config` VALUES ('18', '18', '10', '20', null, null, '0', '40', null, null, '0', '1', '0');
INSERT INTO `news_item_config` VALUES ('19', '19', '10', '20', null, null, '0', '40', null, null, '0', '1', '0');
INSERT INTO `news_item_config` VALUES ('20', '20', '10', '20', null, null, '0', '40', null, null, '0', '1', '0');
INSERT INTO `news_item_config` VALUES ('21', '21', '10', '20', null, null, '0', '40', null, null, '0', '1', '0');
INSERT INTO `news_item_config` VALUES ('22', '22', '10', '20', null, null, '0', '40', null, null, '0', '1', '0');
INSERT INTO `news_item_config` VALUES ('23', '23', '10', '20', null, null, '0', '40', null, null, '0', '1', '0');
INSERT INTO `news_item_config` VALUES ('24', '24', '10', '20', null, null, '0', '40', null, null, '0', '1', '0');
INSERT INTO `news_item_config` VALUES ('25', '25', '10', '20', null, null, '0', '40', null, null, '0', '1', '0');
INSERT INTO `news_item_config` VALUES ('26', '26', '10', '20', null, null, '0', '40', null, null, '0', '1', '0');
INSERT INTO `news_item_config` VALUES ('27', '27', '10', '20', null, null, '0', '40', null, null, '0', '1', '0');
INSERT INTO `news_item_config` VALUES ('28', '28', '10', '20', null, null, '0', '40', null, null, '0', '1', '0');
INSERT INTO `news_item_config` VALUES ('29', '29', '10', '20', null, null, '0', '40', null, null, '0', '1', '0');
INSERT INTO `news_item_config` VALUES ('30', '30', '10', '20', null, null, '0', '40', null, null, '0', '1', '0');
INSERT INTO `news_item_config` VALUES ('31', '31', '10', '20', null, null, '0', '40', null, null, '0', '1', '0');
INSERT INTO `news_item_config` VALUES ('32', '32', '10', '20', null, null, '0', '40', null, null, '0', '1', '0');
INSERT INTO `news_item_config` VALUES ('33', '33', '10', '20', null, null, '0', '40', null, null, '0', '1', '0');
INSERT INTO `news_item_config` VALUES ('34', '34', '6', '20', null, null, '0', '40', null, null, '0', '1', '0');
INSERT INTO `news_item_config` VALUES ('35', '35', '6', '20', null, null, '0', '40', null, null, '0', '1', '0');
INSERT INTO `news_item_config` VALUES ('36', '36', '10', '20', null, null, '0', '40', null, null, '0', '1', '0');
INSERT INTO `news_item_config` VALUES ('39', '39', '4', '20', null, null, '0', '40', null, null, '0', '1', '0');
INSERT INTO `news_item_config` VALUES ('40', '40', '4', '20', null, null, '0', '40', null, null, '0', '1', '0');
INSERT INTO `news_item_config` VALUES ('41', '41', '6', '10', null, null, '0', '40', null, null, '0', '1', '0');
INSERT INTO `news_item_config` VALUES ('42', '42', '10', '20', null, null, '0', '40', null, null, '0', '1', '0');
INSERT INTO `news_item_config` VALUES ('43', '43', '10', '20', null, null, '0', '40', null, null, '0', '1', '0');
INSERT INTO `news_item_config` VALUES ('44', '44', '10', '20', null, null, '0', '40', null, null, '0', '1', '0');
INSERT INTO `news_item_config` VALUES ('45', '45', '1', '20', null, null, '0', '40', null, null, '0', '1', '0');
INSERT INTO `news_item_config` VALUES ('46', '46', '1', '20', null, null, '0', '40', null, null, '0', '1', '0');
INSERT INTO `news_item_config` VALUES ('47', '47', '10', '20', null, null, '0', '40', null, null, '0', '1', '0');
INSERT INTO `news_item_config` VALUES ('48', '48', '10', '20', null, null, '0', '40', null, null, '0', '1', '0');
INSERT INTO `news_item_config` VALUES ('49', '49', '10', '20', null, null, '0', '40', null, null, '0', '1', '0');

-- ----------------------------
-- Table structure for `news_item_navigation`
-- ----------------------------
DROP TABLE IF EXISTS `news_item_navigation`;
CREATE TABLE `news_item_navigation` (
  `navigation_id` int(11) NOT NULL AUTO_INCREMENT,
  `navigation_name` varchar(50) DEFAULT NULL,
  `class_id` varchar(20) DEFAULT NULL,
  `navigation_url` varchar(200) DEFAULT NULL,
  `order_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`navigation_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of news_item_navigation
-- ----------------------------

-- ----------------------------
-- Table structure for `news_item_small`
-- ----------------------------
DROP TABLE IF EXISTS `news_item_small`;
CREATE TABLE `news_item_small` (
  `type_id` int(11) NOT NULL AUTO_INCREMENT,
  `type_name` varchar(50) DEFAULT NULL,
  `class_id` varchar(20) DEFAULT NULL,
  `order_id` int(11) DEFAULT NULL,
  `if_display` char(1) DEFAULT NULL,
  `check_flag` char(1) DEFAULT NULL,
  `announce_type` varchar(50) DEFAULT NULL,
  `httpUrl` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`type_id`)
) ENGINE=InnoDB AUTO_INCREMENT=50 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of news_item_small
-- ----------------------------
INSERT INTO `news_item_small` VALUES ('1', '塘小简介', '1000001', '1', '1', '1', '0', null);
INSERT INTO `news_item_small` VALUES ('2', '校长寄语', '1000001', '2', '1', '1', '0', null);
INSERT INTO `news_item_small` VALUES ('3', '校园风貌', '1000001', '3', '1', '1', '0', null);
INSERT INTO `news_item_small` VALUES ('4', '历史回眸', '1000001', '4', '1', '1', '0', null);
INSERT INTO `news_item_small` VALUES ('5', '方圆规章', '1000001', '5', '1', '1', '0', null);
INSERT INTO `news_item_small` VALUES ('6', '学校机构', '1000001', '6', '1', '1', '0', null);
INSERT INTO `news_item_small` VALUES ('7', '联系塘小', '1000001', '7', '1', '1', '0', null);
INSERT INTO `news_item_small` VALUES ('8', '骨干老师', '1000002', '1', '1', '1', '0', null);
INSERT INTO `news_item_small` VALUES ('9', '教师文萃', '1000002', '2', '1', '1', '0', null);
INSERT INTO `news_item_small` VALUES ('10', '教学科研', '1000002', '3', '1', '1', '0', null);
INSERT INTO `news_item_small` VALUES ('11', '青少年与法', '1000003', '1', '1', '1', '0', null);
INSERT INTO `news_item_small` VALUES ('12', '家庭教育', '1000003', '2', '1', '1', '0', null);
INSERT INTO `news_item_small` VALUES ('13', '德育动态', '1000003', '3', '1', '1', '0', null);
INSERT INTO `news_item_small` VALUES ('14', '心灵港湾', '1000003', '4', '1', '1', '0', null);
INSERT INTO `news_item_small` VALUES ('15', '安全瞭望', '1000003', '5', '1', '1', '0', null);
INSERT INTO `news_item_small` VALUES ('16', '语文', '1000004', '1', '1', '1', '0', null);
INSERT INTO `news_item_small` VALUES ('17', '数学', '1000004', '2', '1', '1', '0', null);
INSERT INTO `news_item_small` VALUES ('18', '英语', '1000004', '3', '1', '1', '0', null);
INSERT INTO `news_item_small` VALUES ('19', '综合实践', '1000004', '4', '1', '1', '0', null);
INSERT INTO `news_item_small` VALUES ('20', '体育与健康', '1000004', '5', '1', '1', '0', null);
INSERT INTO `news_item_small` VALUES ('21', '音乐与美术', '1000004', '6', '1', '1', '0', null);
INSERT INTO `news_item_small` VALUES ('22', '信息技术', '1000004', '7', '1', '1', '0', null);
INSERT INTO `news_item_small` VALUES ('23', '客家山歌', '1000005', '1', '1', '1', '0', null);
INSERT INTO `news_item_small` VALUES ('24', '让兵乓球飞起来', '1000005', '2', '1', '1', '0', null);
INSERT INTO `news_item_small` VALUES ('25', '招生工作', '1000006', '1', '1', '1', '0', null);
INSERT INTO `news_item_small` VALUES ('26', '采购招标', '1000006', '2', '1', '1', '0', null);
INSERT INTO `news_item_small` VALUES ('27', '致家长的信', '1000006', '3', '1', '1', '0', null);
INSERT INTO `news_item_small` VALUES ('28', '佳作展示', '1000007', '1', '1', '1', '0', null);
INSERT INTO `news_item_small` VALUES ('29', '随感札记', '1000007', '2', '1', '1', '0', null);
INSERT INTO `news_item_small` VALUES ('30', '诗海拾贝', '1000007', '3', '1', '1', '0', null);
INSERT INTO `news_item_small` VALUES ('31', '彩虹画廊', '1000007', '4', '1', '1', '0', null);
INSERT INTO `news_item_small` VALUES ('32', '智星七巧板', '1000007', '5', '1', '1', '0', null);
INSERT INTO `news_item_small` VALUES ('33', '文学大赛', '1000007', '6', '0', '1', '0', null);
INSERT INTO `news_item_small` VALUES ('34', '塘小新闻', '1000010', '1', '1', '1', '0', null);
INSERT INTO `news_item_small` VALUES ('35', '校园公告', '1000011', '1', '1', '1', '0', null);
INSERT INTO `news_item_small` VALUES ('36', '三味德育', '1000012', '1', '1', '1', '0', null);
INSERT INTO `news_item_small` VALUES ('39', '友情链接', '1000015', '1', '1', '1', '0', null);
INSERT INTO `news_item_small` VALUES ('40', '轮播图片', '1000016', '1', '1', '1', '0', null);
INSERT INTO `news_item_small` VALUES ('41', '资源库', '1000017', '1', '1', '1', '0', null);
INSERT INTO `news_item_small` VALUES ('42', '幸福课堂', '1000012', '2', '1', '1', '0', null);
INSERT INTO `news_item_small` VALUES ('43', '生态乐园', '1000012', '3', '1', '1', '0', null);
INSERT INTO `news_item_small` VALUES ('44', '联系我们', '1000013', '1', '1', '1', '2', null);
INSERT INTO `news_item_small` VALUES ('45', '校长', '1000016', '2', '1', '1', '0', null);
INSERT INTO `news_item_small` VALUES ('46', '副校长', '1000016', '3', '1', '1', '0', null);
INSERT INTO `news_item_small` VALUES ('47', '绩效考核', '1000018', '1', '1', '1', '1', '');
INSERT INTO `news_item_small` VALUES ('48', '校讯通', '1000018', '2', '1', '1', '1', '');
INSERT INTO `news_item_small` VALUES ('49', '联系我们', '1000018', '3', '1', '1', '1', 'client/itembig1000013/itemsmall44.html');

-- ----------------------------
-- Table structure for `sch_college_department`
-- ----------------------------
DROP TABLE IF EXISTS `sch_college_department`;
CREATE TABLE `sch_college_department` (
  `xxdm` char(5) DEFAULT NULL,
  `yxdm` varchar(5) NOT NULL,
  `yxmc` varchar(60) NOT NULL,
  `yxsywmc` varchar(180) DEFAULT NULL,
  `yxsjc` varchar(20) DEFAULT NULL,
  `yxsbbm` char(1) DEFAULT NULL,
  `yxslbm` char(1) DEFAULT NULL,
  `jlny` char(6) DEFAULT NULL,
  `xzfzr` varchar(30) DEFAULT NULL,
  `dwfzr` varchar(30) DEFAULT NULL,
  `dept_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`yxdm`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sch_college_department
-- ----------------------------

-- ----------------------------
-- Table structure for `sys_admin`
-- ----------------------------
DROP TABLE IF EXISTS `sys_admin`;
CREATE TABLE `sys_admin` (
  `ADMIN_NAME` varchar(30) NOT NULL,
  `PASSWORD` varchar(100) DEFAULT NULL,
  `UNIT_ID` bigint(20) NOT NULL,
  `USER_TYPE` varchar(10) DEFAULT NULL,
  `FLAG` int(11) DEFAULT NULL,
  PRIMARY KEY (`ADMIN_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_admin
-- ----------------------------
INSERT INTO `sys_admin` VALUES ('admin', 'AHAH8550778ZG9A10H9AA5NNXZHKHHZK', '1', null, null);

-- ----------------------------
-- Table structure for `sys_module`
-- ----------------------------
DROP TABLE IF EXISTS `sys_module`;
CREATE TABLE `sys_module` (
  `module_id` varchar(30) NOT NULL,
  `module_name` varchar(60) DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL,
  `up_module` varchar(100) DEFAULT NULL,
  `module_picture` varchar(100) DEFAULT NULL,
  `module_flag` varchar(30) DEFAULT NULL,
  `if_open` char(1) DEFAULT NULL,
  `YXDM` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`module_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_module
-- ----------------------------
INSERT INTO `sys_module` VALUES ('0100000000', '系统管理', null, '0000000000', 'tb_12.gif', null, null, null);
INSERT INTO `sys_module` VALUES ('0101000000', '系统管理', 'login.do?method=leftList', '0100000000', 'tb_12.gif', null, null, null);
INSERT INTO `sys_module` VALUES ('0101010000', '单位信息', 'view/rightManage.do?method=queryUnit', '0101000000', 'tb_12.gif', null, null, null);
INSERT INTO `sys_module` VALUES ('0101010100', '查询', null, '0101010000', 'tb_12.gif', 'select', null, null);
INSERT INTO `sys_module` VALUES ('0101010200', '新增', null, '0101010000', 'tb_12.gif', 'add', null, null);
INSERT INTO `sys_module` VALUES ('0101010300', '修改', null, '0101010000', 'tb_12.gif', 'update', null, null);
INSERT INTO `sys_module` VALUES ('0101010400', '删除', null, '0101010000', 'tb_12.gif', 'delete', null, null);
INSERT INTO `sys_module` VALUES ('0101020000', '部门管理', 'view/rightManage.do?method=queryDepartment', '0101000000', 'tb_12.gif', null, null, null);
INSERT INTO `sys_module` VALUES ('0101030000', '部门人员', 'view/rightManage.do?method=queryDepartmentPerson', '0101000000', 'tb_12.gif', null, null, null);
INSERT INTO `sys_module` VALUES ('0101040000', '角色管理', 'view/rightManage.do?method=queryRole', '0101000000', 'tb_12.gif', 'jsgl', null, null);
INSERT INTO `sys_module` VALUES ('0101040100', '新增', null, '0101080000', 'tb_12.gif', 'add', null, null);
INSERT INTO `sys_module` VALUES ('0101040200', '查询', null, '0101080000', 'tb_12.gif', 'select', null, null);
INSERT INTO `sys_module` VALUES ('0101040300', '修改', null, '0101080000', 'tb_12.gif', 'update', null, null);
INSERT INTO `sys_module` VALUES ('0101040400', '删除', null, '0101080000', 'tb_12.gif', 'delete', null, null);
INSERT INTO `sys_module` VALUES ('0101050000', '角色权限', 'view/rightManage.do?method=queryRoleRight', '0101000000', 'tb_12.gif', 'jsqx', null, null);
INSERT INTO `sys_module` VALUES ('0101050100', '新增', null, '0101090000', 'tb_12.gif', 'add', null, null);
INSERT INTO `sys_module` VALUES ('0101050200', '查询', null, '0101090000', 'tb_12.gif', 'select', null, null);
INSERT INTO `sys_module` VALUES ('0101060000', '用户授权', 'view/rightManage.do?method=enterAssignUserRight', '0101000000', 'tb_12.gif', 'fpyhqs', null, null);
INSERT INTO `sys_module` VALUES ('0101060100', '新增', null, '0101100000', 'tb_12.gif', 'add', null, null);
INSERT INTO `sys_module` VALUES ('0101060200', '查询', null, '0101100000', 'tb_12.gif', 'select', null, null);
INSERT INTO `sys_module` VALUES ('0101070000', '批量授权', 'view/rightManage.do?method=batchAssignUserRight', '0101000000', 'tb_12.gif', 'plfpqx', null, null);
INSERT INTO `sys_module` VALUES ('0101070100', '新增', null, '0101110000', 'tb_12.gif', 'add', null, null);
INSERT INTO `sys_module` VALUES ('0101070200', '查询', null, '0101110000', 'tb_12.gif', 'select', null, null);
INSERT INTO `sys_module` VALUES ('0101080000', '用户管理', 'view/rightManage.do?method=queryPerson', '0101000000', 'tb_12.gif', null, null, null);
INSERT INTO `sys_module` VALUES ('0200000000', '信息发布管理', null, '0000000000', null, null, null, null);
INSERT INTO `sys_module` VALUES ('0201000000', '栏目设置', 'login.do?method=leftList', '0200000000', null, null, null, null);
INSERT INTO `sys_module` VALUES ('0201010000', '大类管理', 'view/newsmanage.do?method=findNewsItemBig', '0201000000', 'tb_12.gif', 'news_item_big', null, null);
INSERT INTO `sys_module` VALUES ('0201010100', '新增', null, '0201010000', 'tb_12.gif', 'add', null, null);
INSERT INTO `sys_module` VALUES ('0201010200', '查询', null, '0201010000', 'tb_12.gif', 'select', null, null);
INSERT INTO `sys_module` VALUES ('0201010300', '修改', null, '0201010000', 'tb_12.gif', 'update', null, null);
INSERT INTO `sys_module` VALUES ('0201010400', '删除', null, '0201010000', 'tb_12.gif', 'delete', null, null);
INSERT INTO `sys_module` VALUES ('0201020000', '小类管理', 'view/newsmanage.do?method=findNewsItemSmall', '0201000000', 'tb_12.gif', 'news_item_small', null, null);
INSERT INTO `sys_module` VALUES ('0201020100', '新增', null, '0201020000', 'tb_12.gif', 'add', null, null);
INSERT INTO `sys_module` VALUES ('0201020200', '查询', null, '0201020000', 'tb_12.gif', 'select', null, null);
INSERT INTO `sys_module` VALUES ('0201020300', '修改', null, '0201020000', 'tb_12.gif', 'update', null, null);
INSERT INTO `sys_module` VALUES ('0201020400', '删除', null, '0201020000', 'tb_12.gif', 'delete', null, null);
INSERT INTO `sys_module` VALUES ('0202000000', '广告设置', 'login.do?method=leftList', '9990200000000', null, null, null, null);
INSERT INTO `sys_module` VALUES ('0202010000', '广告类型', 'view/newsmanage.do?method=findNewsAdType', '0202000000', 'tb_12.gif', 'news_ad_type', null, null);
INSERT INTO `sys_module` VALUES ('0202010100', '新增', null, '0202010000', 'tb_12.gif', 'add', null, null);
INSERT INTO `sys_module` VALUES ('0202010200', '查询', null, '0202010000', 'tb_12.gif', 'select', null, null);
INSERT INTO `sys_module` VALUES ('0202010300', '修改', null, '0202010000', 'tb_12.gif', 'update', null, null);
INSERT INTO `sys_module` VALUES ('0202010400', '删除', null, '0202010000', 'tb_12.gif', 'delete', null, null);
INSERT INTO `sys_module` VALUES ('0202020000', '广告管理', 'view/newsmanage.do?method=findNewsAdManageInit', '0202000000', 'tb_12.gif', 'news_ad_manage', null, null);
INSERT INTO `sys_module` VALUES ('0202020100', '新增', null, '0202020000', 'tb_12.gif', 'add', null, null);
INSERT INTO `sys_module` VALUES ('0202020200', '查询', null, '0202020000', 'tb_12.gif', 'select', null, null);
INSERT INTO `sys_module` VALUES ('0202020300', '修改', null, '0202020000', 'tb_12.gif', 'update', null, null);
INSERT INTO `sys_module` VALUES ('0202020400', '删除', null, '0202020000', 'tb_12.gif', 'delete', null, null);
INSERT INTO `sys_module` VALUES ('0203000000', '审核设置', 'login.do?method=leftList', '0200000000', null, null, null, null);
INSERT INTO `sys_module` VALUES ('0203010000', '审核设置', 'view/newsmanage.do?method=findNewsItemSmallCheckFlag', '0203000000', 'tb_12.gif', 'news_item_small_check', null, null);
INSERT INTO `sys_module` VALUES ('0203010100', '新增', null, '0203010000', 'tb_12.gif', 'add', null, null);
INSERT INTO `sys_module` VALUES ('0203010200', '查询', null, '0203010000', 'tb_12.gif', 'select', null, null);
INSERT INTO `sys_module` VALUES ('0203010300', '修改', null, '0203010000', 'tb_12.gif', 'update', null, null);
INSERT INTO `sys_module` VALUES ('0203010400', '删除', null, '0203010000', 'tb_12.gif', 'delete', null, null);
INSERT INTO `sys_module` VALUES ('0203020000', '新闻审核', 'view/newsmanage.do?method=findNewsContentCheck', '0203000000', 'tb_12.gif', 'news_content_check', null, null);
INSERT INTO `sys_module` VALUES ('0203020100', '新增', null, '0203020000', 'tb_12.gif', 'add', null, null);
INSERT INTO `sys_module` VALUES ('0203020200', '查询', null, '0203020000', 'tb_12.gif', 'select', null, null);
INSERT INTO `sys_module` VALUES ('0203020300', '修改', null, '0203020000', 'tb_12.gif', 'update', null, null);
INSERT INTO `sys_module` VALUES ('0203020400', '删除', null, '0203020000', 'tb_12.gif', 'delete', null, null);
INSERT INTO `sys_module` VALUES ('0204000000', '内容管理', 'view/newsmanage.do?method=findNewsItemsBigTree', '0200000000', null, null, null, null);
INSERT INTO `sys_module` VALUES ('0204010000', '信息发布', 'view/newsmanage.do?method=findNewsContentManageInit', '0204000000', 'tb_12.gif', 'news_content_manage', null, null);
INSERT INTO `sys_module` VALUES ('0204010100', '新增', null, '0204010000', 'tb_12.gif', 'add', null, null);
INSERT INTO `sys_module` VALUES ('0204010200', '查询', null, '0204010000', 'tb_12.gif', 'select', null, null);
INSERT INTO `sys_module` VALUES ('0204010300', '修改', null, '0204010000', 'tb_12.gif', 'update', null, null);
INSERT INTO `sys_module` VALUES ('0204010400', '删除', null, '0204010000', 'tb_12.gif', 'delete', null, null);
INSERT INTO `sys_module` VALUES ('0205010100', '导入', null, '0205010000', null, 'import', null, null);
INSERT INTO `sys_module` VALUES ('0205010200', '查询', null, '0205010000', 'tb_12.gif', 'select', null, null);
INSERT INTO `sys_module` VALUES ('0205010300', '修改', null, '0205010000', null, 'update', null, null);
INSERT INTO `sys_module` VALUES ('0205010400', '删除', null, '0205010000', null, 'delete', null, null);
INSERT INTO `sys_module` VALUES ('0206000000', '监控管理', 'login.do?method=leftList', '0200000000', null, null, null, null);
INSERT INTO `sys_module` VALUES ('0206010000', '新闻监控', 'view/monitor.do?method=list', '0206000000', null, 'monitor', null, null);
INSERT INTO `sys_module` VALUES ('0206010100', '查询', null, '0206010000', null, 'select', null, null);
INSERT INTO `sys_module` VALUES ('0206010200', '删除', null, '0206010000', null, 'delete', null, null);
INSERT INTO `sys_module` VALUES ('0207000000', '会员管理', 'login.do?method=leftList', '0200000000', null, null, null, null);
INSERT INTO `sys_module` VALUES ('0207010000', '会员管理', 'view/membermanage.do?method=queryMember', '0207000000', null, 'member', null, null);
INSERT INTO `sys_module` VALUES ('0207010100', '查询', null, '0207010000', null, 'select', null, null);
INSERT INTO `sys_module` VALUES ('0207010200', '审批', null, '0207010000', null, 'approve', null, null);
INSERT INTO `sys_module` VALUES ('0207010300', '反审批', null, '0207010000', null, 'unApprove', null, null);
INSERT INTO `sys_module` VALUES ('0207010400', '启用', null, '0207010000', null, 'enable', null, null);
INSERT INTO `sys_module` VALUES ('0207010500', '禁用', null, '0207010000', null, 'disable', null, null);
INSERT INTO `sys_module` VALUES ('0207010600', '删除', null, '0207010000', null, 'delete', null, null);
INSERT INTO `sys_module` VALUES ('0207020000', '留言管理', 'view/messagemanage.do?method=queryMessage', '0207000000', null, 'message', null, null);
INSERT INTO `sys_module` VALUES ('0207020100', '查询', null, '0207020000', null, 'select', null, null);
INSERT INTO `sys_module` VALUES ('0207020200', '审批', null, '0207020000', null, 'approve', null, null);
INSERT INTO `sys_module` VALUES ('0207020300', '反审批', null, '0207020000', null, 'unApprove', null, null);
INSERT INTO `sys_module` VALUES ('0207020400', '删除', null, '0207020000', null, 'delete', null, null);
INSERT INTO `sys_module` VALUES ('0207030000', '投票管理', 'view/votemanage.do?method=queryVoteTitle', '0207000000', null, 'vote', null, null);
INSERT INTO `sys_module` VALUES ('0207030100', '查询', null, '0207030000', null, 'select', null, null);
INSERT INTO `sys_module` VALUES ('0207030200', '新增', null, '0207030000', null, 'add', null, null);
INSERT INTO `sys_module` VALUES ('0207030300', '修改', null, '0207030000', null, 'update', null, null);
INSERT INTO `sys_module` VALUES ('0207030400', '发布', null, '0207030000', null, 'publish', null, null);
INSERT INTO `sys_module` VALUES ('0207030500', '反发布', null, '0207030000', null, 'unPublish', null, null);
INSERT INTO `sys_module` VALUES ('0207030600', '删除', null, '0207030000', null, 'delete', null, null);

-- ----------------------------
-- Table structure for `sys_role`
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `role_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('1', '管理员');
INSERT INTO `sys_role` VALUES ('2', '信息发布员');

-- ----------------------------
-- Table structure for `sys_role_module`
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_module`;
CREATE TABLE `sys_role_module` (
  `module_id` varchar(30) NOT NULL,
  `role_id` bigint(20) NOT NULL,
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=999 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_role_module
-- ----------------------------
INSERT INTO `sys_role_module` VALUES ('0201010000', '2', '57');
INSERT INTO `sys_role_module` VALUES ('0202010000', '2', '58');
INSERT INTO `sys_role_module` VALUES ('0202020000', '2', '59');
INSERT INTO `sys_role_module` VALUES ('0203010000', '2', '60');
INSERT INTO `sys_role_module` VALUES ('0204010000', '2', '61');
INSERT INTO `sys_role_module` VALUES ('0203020000', '2', '62');
INSERT INTO `sys_role_module` VALUES ('0101080000', '2', '63');
INSERT INTO `sys_role_module` VALUES ('0101010000', '2', '64');
INSERT INTO `sys_role_module` VALUES ('0101020000', '2', '65');
INSERT INTO `sys_role_module` VALUES ('0101030000', '2', '66');
INSERT INTO `sys_role_module` VALUES ('0101040000', '2', '67');
INSERT INTO `sys_role_module` VALUES ('0101050000', '2', '68');
INSERT INTO `sys_role_module` VALUES ('0101060000', '2', '69');
INSERT INTO `sys_role_module` VALUES ('0201020000', '2', '70');
INSERT INTO `sys_role_module` VALUES ('0101070000', '2', '71');
INSERT INTO `sys_role_module` VALUES ('0204010000', '2', '73');
INSERT INTO `sys_role_module` VALUES ('0204010100', '2', '74');
INSERT INTO `sys_role_module` VALUES ('0204010200', '2', '75');
INSERT INTO `sys_role_module` VALUES ('0204010300', '2', '76');
INSERT INTO `sys_role_module` VALUES ('0204010400', '2', '77');
INSERT INTO `sys_role_module` VALUES ('0101010000', '1', '935');
INSERT INTO `sys_role_module` VALUES ('0101020000', '1', '936');
INSERT INTO `sys_role_module` VALUES ('0101030000', '1', '937');
INSERT INTO `sys_role_module` VALUES ('0101040000', '1', '938');
INSERT INTO `sys_role_module` VALUES ('0101050000', '1', '939');
INSERT INTO `sys_role_module` VALUES ('0101060000', '1', '940');
INSERT INTO `sys_role_module` VALUES ('0101070000', '1', '941');
INSERT INTO `sys_role_module` VALUES ('0101080000', '1', '942');
INSERT INTO `sys_role_module` VALUES ('0201010000', '1', '943');
INSERT INTO `sys_role_module` VALUES ('0201020000', '1', '944');
INSERT INTO `sys_role_module` VALUES ('0203010000', '1', '945');
INSERT INTO `sys_role_module` VALUES ('0203020000', '1', '946');
INSERT INTO `sys_role_module` VALUES ('0204010000', '1', '947');
INSERT INTO `sys_role_module` VALUES ('0206010000', '1', '948');
INSERT INTO `sys_role_module` VALUES ('0207010000', '1', '949');
INSERT INTO `sys_role_module` VALUES ('0207020000', '1', '950');
INSERT INTO `sys_role_module` VALUES ('0207030000', '1', '951');
INSERT INTO `sys_role_module` VALUES ('0201010000', '1', '952');
INSERT INTO `sys_role_module` VALUES ('0201010100', '1', '953');
INSERT INTO `sys_role_module` VALUES ('0201010200', '1', '954');
INSERT INTO `sys_role_module` VALUES ('0201010300', '1', '955');
INSERT INTO `sys_role_module` VALUES ('0201010400', '1', '956');
INSERT INTO `sys_role_module` VALUES ('0201020000', '1', '957');
INSERT INTO `sys_role_module` VALUES ('0201020100', '1', '958');
INSERT INTO `sys_role_module` VALUES ('0201020200', '1', '959');
INSERT INTO `sys_role_module` VALUES ('0201020300', '1', '960');
INSERT INTO `sys_role_module` VALUES ('0201020400', '1', '961');
INSERT INTO `sys_role_module` VALUES ('0203010000', '1', '962');
INSERT INTO `sys_role_module` VALUES ('0203010100', '1', '963');
INSERT INTO `sys_role_module` VALUES ('0203010200', '1', '964');
INSERT INTO `sys_role_module` VALUES ('0203010300', '1', '965');
INSERT INTO `sys_role_module` VALUES ('0203010400', '1', '966');
INSERT INTO `sys_role_module` VALUES ('0203020000', '1', '967');
INSERT INTO `sys_role_module` VALUES ('0203020100', '1', '968');
INSERT INTO `sys_role_module` VALUES ('0203020200', '1', '969');
INSERT INTO `sys_role_module` VALUES ('0203020300', '1', '970');
INSERT INTO `sys_role_module` VALUES ('0203020400', '1', '971');
INSERT INTO `sys_role_module` VALUES ('0204010000', '1', '972');
INSERT INTO `sys_role_module` VALUES ('0204010100', '1', '973');
INSERT INTO `sys_role_module` VALUES ('0204010200', '1', '974');
INSERT INTO `sys_role_module` VALUES ('0204010300', '1', '975');
INSERT INTO `sys_role_module` VALUES ('0204010400', '1', '976');
INSERT INTO `sys_role_module` VALUES ('0206010000', '1', '977');
INSERT INTO `sys_role_module` VALUES ('0206010100', '1', '978');
INSERT INTO `sys_role_module` VALUES ('0206010200', '1', '979');
INSERT INTO `sys_role_module` VALUES ('0207010000', '1', '980');
INSERT INTO `sys_role_module` VALUES ('0207010100', '1', '981');
INSERT INTO `sys_role_module` VALUES ('0207010200', '1', '982');
INSERT INTO `sys_role_module` VALUES ('0207010300', '1', '983');
INSERT INTO `sys_role_module` VALUES ('0207010400', '1', '984');
INSERT INTO `sys_role_module` VALUES ('0207010500', '1', '985');
INSERT INTO `sys_role_module` VALUES ('0207010600', '1', '986');
INSERT INTO `sys_role_module` VALUES ('0207020000', '1', '987');
INSERT INTO `sys_role_module` VALUES ('0207020100', '1', '988');
INSERT INTO `sys_role_module` VALUES ('0207020200', '1', '989');
INSERT INTO `sys_role_module` VALUES ('0207020300', '1', '990');
INSERT INTO `sys_role_module` VALUES ('0207020400', '1', '991');
INSERT INTO `sys_role_module` VALUES ('0207030000', '1', '992');
INSERT INTO `sys_role_module` VALUES ('0207030100', '1', '993');
INSERT INTO `sys_role_module` VALUES ('0207030200', '1', '994');
INSERT INTO `sys_role_module` VALUES ('0207030300', '1', '995');
INSERT INTO `sys_role_module` VALUES ('0207030400', '1', '996');
INSERT INTO `sys_role_module` VALUES ('0207030500', '1', '997');
INSERT INTO `sys_role_module` VALUES ('0207030600', '1', '998');

-- ----------------------------
-- Table structure for `sys_role_person`
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_person`;
CREATE TABLE `sys_role_person` (
  `role_id` bigint(20) NOT NULL,
  `Person_id` bigint(20) DEFAULT NULL,
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_role_person
-- ----------------------------
INSERT INTO `sys_role_person` VALUES ('2', '4', '7');
INSERT INTO `sys_role_person` VALUES ('2', '2', '8');
INSERT INTO `sys_role_person` VALUES ('1', '2', '12');
INSERT INTO `sys_role_person` VALUES ('1', '3', '17');

-- ----------------------------
-- Table structure for `vote_items`
-- ----------------------------
DROP TABLE IF EXISTS `vote_items`;
CREATE TABLE `vote_items` (
  `item_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `item_name` varchar(200) DEFAULT NULL,
  `vote_id` bigint(20) NOT NULL,
  `item_ballot` int(11) DEFAULT '0',
  PRIMARY KEY (`item_id`),
  KEY `vote_id` (`vote_id`),
  CONSTRAINT `vote_items_fk` FOREIGN KEY (`vote_id`) REFERENCES `vote_title` (`vote_id`)
) ENGINE=InnoDB AUTO_INCREMENT=39 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of vote_items
-- ----------------------------
INSERT INTO `vote_items` VALUES ('27', '很不错', '7', '4');
INSERT INTO `vote_items` VALUES ('28', '好', '7', '3');
INSERT INTO `vote_items` VALUES ('29', '一般般', '7', '0');
INSERT INTO `vote_items` VALUES ('30', '马马虎虎', '7', '0');
INSERT INTO `vote_items` VALUES ('31', '差', '7', '0');
INSERT INTO `vote_items` VALUES ('36', '1', '8', '0');
INSERT INTO `vote_items` VALUES ('37', '2', '8', '0');
INSERT INTO `vote_items` VALUES ('38', '还行吧', '8', '0');

-- ----------------------------
-- Table structure for `vote_title`
-- ----------------------------
DROP TABLE IF EXISTS `vote_title`;
CREATE TABLE `vote_title` (
  `vote_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `vote_name` varchar(30) DEFAULT NULL,
  `vote_type` tinyint(1) DEFAULT NULL,
  `vote_date` varchar(20) DEFAULT NULL,
  `vote_hot` bigint(20) DEFAULT '0',
  `publish_status` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`vote_id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of vote_title
-- ----------------------------
INSERT INTO `vote_title` VALUES ('7', '你觉得学校网站怎么样', '1', '2012-04-30 09:41:32', '7', '1');
INSERT INTO `vote_title` VALUES ('8', '简单测试', '2', '2012-05-07 04:53:17', '0', '0');
