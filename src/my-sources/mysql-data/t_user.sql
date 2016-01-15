/*
Navicat MySQL Data Transfer

Source Server         : 实训
Source Server Version : 50163
Source Host           : localhost:3306
Source Database       : appshop

Target Server Type    : MYSQL
Target Server Version : 50163
File Encoding         : 65001

Date: 2016-01-04 10:08:54
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `id` varchar(30) NOT NULL,
  `loginName` varchar(30) DEFAULT NULL COMMENT '登录名',
  `password` varchar(30) DEFAULT NULL COMMENT '密码',
  `nickName` varchar(30) DEFAULT NULL COMMENT '昵称',
  `phone` varchar(12) DEFAULT NULL,
  `mail` varchar(30) DEFAULT NULL,
  `realName` varchar(30) DEFAULT NULL COMMENT '真实姓名',
  `cardNum` varchar(20) DEFAULT NULL COMMENT '身份证号',
  `cardUrl` varchar(100) DEFAULT NULL COMMENT '扫描件路径',
  `company` varchar(30) DEFAULT NULL COMMENT '公司名称',
  `licenseNum` varchar(30) DEFAULT NULL COMMENT '营业执照注册号',
  `address` varchar(100) DEFAULT NULL COMMENT '联系地址',
  `roleid` varchar(30) DEFAULT NULL COMMENT '用户类型',
  `status` varchar(30) DEFAULT NULL COMMENT '用户状态',
  `social_uid` varchar(40) DEFAULT NULL COMMENT '社会化登陆ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='主键\r\n登陆名称\r\n密码\r\n昵称\r\n手机号码\r\n邮箱\r\n真实姓名\r\n身份证号码\r\n身份证扫描件\r\n公司名称\r\n营业执照注';

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES ('1', 'admin', 'admin', null, null, null, null, null, null, null, null, null, null, null, null);
