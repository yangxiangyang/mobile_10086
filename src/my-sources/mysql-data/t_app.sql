/*
Navicat MySQL Data Transfer

Source Server         : 实训
Source Server Version : 50163
Source Host           : localhost:3306
Source Database       : appshop

Target Server Type    : MYSQL
Target Server Version : 50163
File Encoding         : 65001

Date: 2016-01-08 10:34:55
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_app
-- ----------------------------
DROP TABLE IF EXISTS `t_app`;
CREATE TABLE `t_app` (
  `id` varchar(255) NOT NULL,
  `appname` varchar(30) DEFAULT NULL COMMENT '应用名称',
  `version` varchar(20) DEFAULT NULL COMMENT '版本',
  `appicon` varchar(100) DEFAULT NULL COMMENT '应用图标',
  `apkurl` varchar(100) DEFAULT NULL COMMENT '应用下载路径',
  `description` varchar(500) DEFAULT NULL COMMENT '描述',
  `filesize` varchar(30) DEFAULT NULL COMMENT '文件大小',
  `updatetime` datetime DEFAULT NULL COMMENT '更新时间',
  `developer` varchar(30) DEFAULT NULL COMMENT '开发者',
  `apptype` varchar(30) DEFAULT NULL COMMENT '应用类型',
  `price` float DEFAULT NULL COMMENT '价格',
  `downloadNums` varchar(30) DEFAULT NULL COMMENT '下载量',
  `platform` varchar(20) DEFAULT NULL COMMENT '所属平台',
  `status` varchar(50) DEFAULT NULL COMMENT '应用状态',
  `codeurl` varchar(50) DEFAULT NULL COMMENT '二维码图片路径',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_app
-- ----------------------------
