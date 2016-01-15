/*
Navicat MySQL Data Transfer

Source Server         : 实训
Source Server Version : 50163
Source Host           : localhost:3306
Source Database       : appshop

Target Server Type    : MYSQL
Target Server Version : 50163
File Encoding         : 65001

Date: 2016-01-08 10:35:09
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_pic
-- ----------------------------
DROP TABLE IF EXISTS `t_pic`;
CREATE TABLE `t_pic` (
  `id` varchar(30) NOT NULL DEFAULT '',
  `picurl` varchar(100) DEFAULT NULL COMMENT '图片路径',
  `description` varchar(500) DEFAULT NULL COMMENT '图片描述',
  `app` varchar(30) DEFAULT NULL COMMENT '所属应用',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_pic
-- ----------------------------
