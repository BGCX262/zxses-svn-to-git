/* 大类表
Navicat MySQL Data Transfer

Source Server         : work
Source Server Version : 50132
Source Host           : localhost:3306
Source Database       : tleip

Target Server Type    : MYSQL
Target Server Version : 50132
File Encoding         : 65001

Date: 2012-05-05 19:23:59
*/

SET FOREIGN_KEY_CHECKS=0;

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



/* 小类
Navicat MySQL Data Transfer

Source Server         : work
Source Server Version : 50132
Source Host           : localhost:3306
Source Database       : tleip

Target Server Type    : MYSQL
Target Server Version : 50132
File Encoding         : 65001

Date: 2012-05-05 19:24:06
*/

SET FOREIGN_KEY_CHECKS=0;

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




/*  小类属性
Navicat MySQL Data Transfer

Source Server         : work
Source Server Version : 50132
Source Host           : localhost:3306
Source Database       : tleip

Target Server Type    : MYSQL
Target Server Version : 50132
File Encoding         : 65001

Date: 2012-05-05 19:24:11
*/

SET FOREIGN_KEY_CHECKS=0;

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
