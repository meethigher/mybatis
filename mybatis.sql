/*
 Navicat Premium Data Transfer

 Source Server         : local
 Source Server Type    : MySQL
 Source Server Version : 80012
 Source Host           : localhost:3306
 Source Schema         : mybatis

 Target Server Type    : MySQL
 Target Server Version : 80012
 File Encoding         : 65001

 Date: 17/02/2021 04:11:22
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for account
-- ----------------------------
DROP TABLE IF EXISTS `account`;
CREATE TABLE `account`  (
  `aid` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `uid` int(11) NOT NULL COMMENT '用户编号',
  `money` double NOT NULL COMMENT '金额',
  PRIMARY KEY (`aid`) USING BTREE,
  INDEX `u_a_pk`(`uid`) USING BTREE,
  CONSTRAINT `u_a_pk` FOREIGN KEY (`uid`) REFERENCES `user` (`uid`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of account
-- ----------------------------
INSERT INTO `account` VALUES (1, 1, 2000);
INSERT INTO `account` VALUES (2, 2, 3000);
INSERT INTO `account` VALUES (3, 1, 1000);
INSERT INTO `account` VALUES (4, 1, 1500);

-- ----------------------------
-- Table structure for fairy
-- ----------------------------
DROP TABLE IF EXISTS `fairy`;
CREATE TABLE `fairy`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `gender` varchar(2) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `age` int(11) NOT NULL,
  `grade` int(11) NOT NULL,
  `school` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `position` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 20 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of fairy
-- ----------------------------
INSERT INTO `fairy` VALUES (1, '胡列娜', '女', 22, 52, '武魂殿学院', '武魂殿');
INSERT INTO `fairy` VALUES (2, '邱若水', '女', 18, 37, '天水学院', '天斗帝国');
INSERT INTO `fairy` VALUES (3, '水冰儿', '女', 17, 43, '天水学院', '天斗帝国');
INSERT INTO `fairy` VALUES (4, '水月儿', '女', 17, 36, '天水学院', '天斗帝国');
INSERT INTO `fairy` VALUES (5, '邪月', '男', 22, 53, '武魂殿学院', '武魂殿');
INSERT INTO `fairy` VALUES (6, '唐三', '男', 16, 44, '史莱克', '天斗帝国');
INSERT INTO `fairy` VALUES (7, '戴沐白', '男', 21, 48, '史莱克学院', '天斗帝国');
INSERT INTO `fairy` VALUES (8, '胡列娜', '女', 22, 52, '武魂殿学院', '武魂殿');
INSERT INTO `fairy` VALUES (12, '肖战', '男', 8, 1, '腾讯', '日本');
INSERT INTO `fairy` VALUES (14, '肖战', '女', 28, 1, '腾讯', '中国');
INSERT INTO `fairy` VALUES (15, '肖战', '中', 38, 1, '腾讯', '泰国');
INSERT INTO `fairy` VALUES (18, '雷军', '男', 1, 1, '武大', '武汉');
INSERT INTO `fairy` VALUES (19, '雷布斯', '男', 1, 1, '武大', '武汉');

-- ----------------------------
-- Table structure for people
-- ----------------------------
DROP TABLE IF EXISTS `people`;
CREATE TABLE `people`  (
  `pid` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户编号',
  `username` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '名字',
  `birthday` datetime(0) NULL DEFAULT NULL COMMENT '生日',
  `sex` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '性别',
  `address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '住址',
  PRIMARY KEY (`pid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of people
-- ----------------------------
INSERT INTO `people` VALUES (1, '白沉香', '2021-02-14 05:03:52', '女', '敏之一族');
INSERT INTO `people` VALUES (2, '肖战', '2021-02-14 05:04:14', '男', '沙雕');
INSERT INTO `people` VALUES (3, '范明', '2021-02-15 09:59:34', '男', '江苏徐州');

-- ----------------------------
-- Table structure for people_role
-- ----------------------------
DROP TABLE IF EXISTS `people_role`;
CREATE TABLE `people_role`  (
  `pid` int(11) NOT NULL COMMENT '用户编号',
  `rid` int(11) NOT NULL COMMENT '角色编号',
  PRIMARY KEY (`pid`, `rid`) USING BTREE,
  INDEX `r_fk`(`rid`) USING BTREE,
  CONSTRAINT `p_fk` FOREIGN KEY (`pid`) REFERENCES `people` (`pid`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `r_fk` FOREIGN KEY (`rid`) REFERENCES `role` (`rid`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of people_role
-- ----------------------------
INSERT INTO `people_role` VALUES (2, 1);
INSERT INTO `people_role` VALUES (3, 1);
INSERT INTO `people_role` VALUES (1, 2);
INSERT INTO `people_role` VALUES (2, 3);

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role`  (
  `rid` int(11) NOT NULL AUTO_INCREMENT COMMENT '角色编号',
  `rolename` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '角色名称',
  `roledesc` varchar(60) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '角色描述',
  PRIMARY KEY (`rid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES (1, '演员', '演戏的');
INSERT INTO `role` VALUES (2, '动漫', '动画片');
INSERT INTO `role` VALUES (3, '歌手', '唱歌的');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `uid` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户编号',
  `username` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '名字',
  `birthday` datetime(0) NULL DEFAULT NULL COMMENT '生日',
  `sex` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '性别',
  `address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '住址',
  PRIMARY KEY (`uid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, '白沉香', '2021-02-14 05:03:52', '女', '敏之一族');
INSERT INTO `user` VALUES (2, '肖战', NULL, NULL, '腾讯');
INSERT INTO `user` VALUES (3, '范明', '2021-02-15 09:59:34', '男', '江苏徐州');
INSERT INTO `user` VALUES (5, '孙尚香', '2021-02-16 23:23:56', '女', '王者荣耀');

SET FOREIGN_KEY_CHECKS = 1;
