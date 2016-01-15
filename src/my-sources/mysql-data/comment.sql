/*
Navicat MySQL Data Transfer

Source Server         : 实训
Source Server Version : 50163
Source Host           : localhost:3306
Source Database       : appshop

Target Server Type    : MYSQL
Target Server Version : 50163
File Encoding         : 65001

Date: 2016-01-08 10:35:01
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for comment
-- ----------------------------
DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment` (
  `id` varchar(30) NOT NULL,
  `commentUser` varchar(30) DEFAULT NULL COMMENT '评论人',
  `commentDate` datetime DEFAULT NULL COMMENT '评论时间',
  `comment` varchar(200) DEFAULT NULL COMMENT '评论内容',
  `commentNum` varchar(20) DEFAULT NULL COMMENT '评分',
  `appid` varchar(30) DEFAULT NULL COMMENT '所属应用',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='评论人  评论时间 评论内容  评分 评价应用';

-- ----------------------------
-- Records of comment
-- ----------------------------
