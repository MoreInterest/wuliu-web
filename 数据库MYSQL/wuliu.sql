/*
MySQL Data Transfer
Source Host: localhost
Source Database: 9186kdglxt
Target Host: localhost
Target Database: 9186kdglxt
Date: 2018-11-07 12:15:10
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for t_cars
-- ----------------------------
CREATE TABLE `t_cars` (
  `id` int(11) NOT NULL auto_increment COMMENT '车辆id,主键',
  `name` varchar(200) default NULL COMMENT '车辆编号',
  `leixing` varchar(200) default NULL COMMENT '车辆类型',
  `zaizhong` varchar(200) default NULL COMMENT '载重',
  `xiulicontent` text COMMENT '修理记录',
  `jiayou` varchar(200) default NULL COMMENT '最后一次加油时间',
  `beizhucontent` text COMMENT '备注',
  `jiashiyuancontent` text COMMENT '驾驶员信息',
  `carno` varchar(200) default NULL COMMENT '车牌号',
  `luxiancontent` text COMMENT '路线',
  `youfei` varchar(200) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_express
-- ----------------------------
CREATE TABLE `t_express` (
  `id` int(11) NOT NULL auto_increment COMMENT '快件id,主键',
  `name` varchar(200) default NULL COMMENT '快件编号',
  `member` int(11) default NULL COMMENT '发件人id,外键',
  `chufa` varchar(200) default NULL COMMENT '出发地',
  `shoujianname` varchar(200) default NULL COMMENT '收件人姓名',
  `tel` varchar(200) default NULL COMMENT '收件人联系方式',
  `place` varchar(200) default NULL COMMENT '目的地',
  `leibie` varchar(200) default NULL COMMENT '快件类别',
  `settime` datetime default NULL COMMENT '下单时间',
  `descp` varchar(200) default NULL COMMENT '备注',
  `state` varchar(200) default NULL COMMENT '状态',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_finance
-- ----------------------------
CREATE TABLE `t_finance` (
  `id` int(11) NOT NULL auto_increment COMMENT '财务收支id,主键',
  `name` varchar(200) default NULL COMMENT '标题',
  `settime` datetime default NULL COMMENT '提交时间',
  `mingxi` varchar(200) default NULL COMMENT '财务明细',
  `descp` varchar(200) default NULL COMMENT '备注',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_member
-- ----------------------------
CREATE TABLE `t_member` (
  `id` int(11) NOT NULL auto_increment COMMENT '用户id,主键',
  `name` varchar(200) default NULL COMMENT '姓名',
  `sex` varchar(200) default NULL COMMENT '性别',
  `age` varchar(200) default NULL COMMENT '年龄',
  `tel` varchar(200) default NULL COMMENT '联系电话',
  `address` varchar(200) default NULL COMMENT '住址',
  `email` varchar(200) default NULL COMMENT '邮箱',
  `users` int(11) default NULL COMMENT '登录用户id,外键',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_message
-- ----------------------------
CREATE TABLE `t_message` (
  `id` int(11) NOT NULL auto_increment COMMENT '权限id,主键',
  `staff` int(11) NOT NULL,
  `settime` datetime default NULL,
  `content` text,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_role
-- ----------------------------
CREATE TABLE `t_role` (
  `id` int(11) NOT NULL auto_increment COMMENT '权限id,主键',
  `name` varchar(200) default NULL COMMENT '权限名称',
  `descp` varchar(200) default NULL COMMENT '权限',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_staff
-- ----------------------------
CREATE TABLE `t_staff` (
  `id` int(11) NOT NULL auto_increment COMMENT '员工id,主键',
  `name` varchar(200) default NULL COMMENT '姓名',
  `sex` varchar(200) default NULL COMMENT '性别',
  `age` varchar(200) default NULL COMMENT '年龄',
  `tel` varchar(200) default NULL COMMENT '联系电话',
  `address` varchar(200) default NULL COMMENT '住址',
  `email` varchar(200) default NULL COMMENT '邮箱',
  `users` int(11) default NULL COMMENT '登录用户id,外键',
  `zhandian` varchar(200) default NULL COMMENT '所属站点',
  `gongzi` varchar(200) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
CREATE TABLE `t_user` (
  `id` int(11) NOT NULL auto_increment COMMENT '用户id,主键',
  `username` varchar(200) default NULL COMMENT '用户名',
  `password` varchar(200) default NULL COMMENT '登录密码',
  `role` int(11) default NULL COMMENT '权限id,外键',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records 
-- ----------------------------
INSERT INTO `t_cars` VALUES ('1', '22', '2', '22', '<p>22</p>\r\n', '22', '<p>22</p>\r\n', '<p>22</p>\r\n', '33', '<p>33</p>\r\n', '222');
INSERT INTO `t_express` VALUES ('1', '22', '1', '2', '22', '2', '22', '2', '2018-11-14 00:00:00', '22', '222222222');
INSERT INTO `t_express` VALUES ('2', '33', '1', '33', '33', '3', '3', '33', '2018-11-07 00:00:00', '3', '333');
INSERT INTO `t_finance` VALUES ('1', '车辆2每月油费', '2018-11-07 12:04:27', '每月油费222元', 'cars0');
INSERT INTO `t_finance` VALUES ('2', '车辆222每月油费', '2018-11-07 12:05:25', '每月油费元', 'cars0');
INSERT INTO `t_finance` VALUES ('3', '车辆2每月油费', '2018-11-07 12:05:45', '每月油费22元', 'cars0');
INSERT INTO `t_finance` VALUES ('4', '车辆22每月油费', '2018-11-07 12:06:41', '每月油费222元', 'cars1');
INSERT INTO `t_finance` VALUES ('6', '员工22每月工资', '2018-11-07 12:11:51', '每月工资2222元', 'staff0');
INSERT INTO `t_finance` VALUES ('8', '员工77每月工资', '2018-11-07 12:13:33', '每月工资77元', 'staff3');
INSERT INTO `t_member` VALUES ('1', '22', '222', '2', '22', '2', '2222', '2');
INSERT INTO `t_message` VALUES ('1', '3', '2018-11-07 00:00:00', '<p>777</p>\r\n');
INSERT INTO `t_role` VALUES ('1', '管理员', null);
INSERT INTO `t_role` VALUES ('2', '员工', null);
INSERT INTO `t_role` VALUES ('3', '用户', null);
INSERT INTO `t_staff` VALUES ('3', '77', '7777', '7', '77', '77', '77', '5', '7', '777777');
INSERT INTO `t_user` VALUES ('1', 'admin', '123', '1');
INSERT INTO `t_user` VALUES ('2', 'zhangsan', '123', '3');
INSERT INTO `t_user` VALUES ('3', '22222', '22', '2');
INSERT INTO `t_user` VALUES ('4', '33', '33', '2');
INSERT INTO `t_user` VALUES ('5', '77', '77', '2');
