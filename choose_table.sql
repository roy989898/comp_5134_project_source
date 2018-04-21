/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50721
Source Host           : localhost:3306
Source Database       : course

Target Server Type    : MYSQL
Target Server Version : 50721
File Encoding         : 65001

Date: 2018-04-20 00:44:00
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `choose_table`
-- ----------------------------
DROP TABLE IF EXISTS `choose_table`;
CREATE TABLE `choose_table` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `studentid` int(11) NOT NULL,
  `teacherid` int(11) NOT NULL,
  `teachername` varchar(255) NOT NULL,
  `teachercourse` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of choose_table
-- ----------------------------
INSERT INTO `choose_table` VALUES ('7', '1', '22', 'teacher22', 'course22');
INSERT INTO `choose_table` VALUES ('8', '1', '6', 'teacher6', 'course6');
INSERT INTO `choose_table` VALUES ('9', '1', '7', 'teacher7', 'course7');
INSERT INTO `choose_table` VALUES ('10', '1', '10', 'teacher10', 'course10');
INSERT INTO `choose_table` VALUES ('11', '1', '1', 'teacher1', 'course1');
