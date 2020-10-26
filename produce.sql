/*
 Navicat Premium Data Transfer

 Source Server         : school_work@114.116.249.41 3306
 Source Server Type    : MySQL
 Source Server Version : 50646
 Source Host           : 114.116.249.41:3306
 Source Schema         : school_work

 Target Server Type    : MySQL
 Target Server Version : 50646
 File Encoding         : 65001

 Date: 26/10/2020 09:55:12
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for market_produce
-- ----------------------------
DROP TABLE IF EXISTS `market_produce`;
CREATE TABLE `market_produce`  (
  `id` varchar(66) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `market` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `produce` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for produce
-- ----------------------------
DROP TABLE IF EXISTS `produce`;
CREATE TABLE `produce`  (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `name` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `category` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for produce_category
-- ----------------------------
DROP TABLE IF EXISTS `produce_category`;
CREATE TABLE `produce_category`  (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `name` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for produce_market
-- ----------------------------
DROP TABLE IF EXISTS `produce_market`;
CREATE TABLE `produce_market`  (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `name` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `info` varchar(2000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `type` int(1) NULL DEFAULT 0,
  `address` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `province` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for produce_price
-- ----------------------------
DROP TABLE IF EXISTS `produce_price`;
CREATE TABLE `produce_price`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `market_price_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `price` double NOT NULL,
  `date` date NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `market_price_id`(`market_price_id`) USING BTREE,
  INDEX `date`(`date`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 954698 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for province
-- ----------------------------
DROP TABLE IF EXISTS `province`;
CREATE TABLE `province`  (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for schedule_msg
-- ----------------------------
DROP TABLE IF EXISTS `schedule_msg`;
CREATE TABLE `schedule_msg`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `msg` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `time` datetime(0) NULL DEFAULT NULL,
  `change` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 469 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `username` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `password` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `gender` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `email` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for user_favorite
-- ----------------------------
DROP TABLE IF EXISTS `user_favorite`;
CREATE TABLE `user_favorite`  (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `user` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `marketProduce` varchar(66) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- View structure for market_produce_table
-- ----------------------------
DROP VIEW IF EXISTS `market_produce_table`;
CREATE ALGORITHM = UNDEFINED SQL SECURITY DEFINER VIEW `market_produce_table` AS select `mp`.`id` AS `marketProduceId`,`mp`.`produce` AS `produceId`,`p`.`name` AS `produceName`,`p`.`category` AS `categoryId`,`pc`.`name` AS `categoryName`,`mp`.`market` AS `marketId`,`pm`.`name` AS `marketName` from (((`market_produce` `mp` join `produce` `p` on((`mp`.`produce` = `p`.`id`))) join `produce_category` `pc` on((`p`.`category` = `pc`.`id`))) join `produce_market` `pm` on((`mp`.`market` = `pm`.`id`)));

-- ----------------------------
-- View structure for market_table
-- ----------------------------
DROP VIEW IF EXISTS `market_table`;
CREATE ALGORITHM = UNDEFINED SQL SECURITY DEFINER VIEW `market_table` AS select `pm`.`id` AS `marketId`,`pm`.`name` AS `marketName`,`pm`.`info` AS `marketInfo`,`pm`.`type` AS `marketType`,`pm`.`address` AS `marketAddress`,`p`.`id` AS `provinceId`,`p`.`name` AS `provinceName` from ((`produce_market` `pm` join `province` `p` on((`pm`.`province` = `p`.`id`))) join `market_produce` `mp` on((`pm`.`id` = `mp`.`market`))) group by `pm`.`id`;

-- ----------------------------
-- View structure for price_table
-- ----------------------------
DROP VIEW IF EXISTS `price_table`;
CREATE ALGORITHM = UNDEFINED SQL SECURITY DEFINER VIEW `price_table` AS select `pp`.`id` AS `id`,`mp`.`id` AS `marketProduceId`,`mp`.`produce` AS `produceId`,`p`.`name` AS `produceName`,`pc`.`id` AS `categoryId`,`pc`.`name` AS `categoryName`,`pm`.`id` AS `marketId`,`pm`.`name` AS `marketName`,`pp`.`price` AS `price`,`pp`.`date` AS `date` from ((((`produce_price` `pp` join `market_produce` `mp` on((`pp`.`market_price_id` = `mp`.`id`))) join `produce_market` `pm` on((`mp`.`market` = `pm`.`id`))) join `produce` `p` on((`mp`.`produce` = `p`.`id`))) join `produce_category` `pc` on((`p`.`category` = `pc`.`id`)));

-- ----------------------------
-- View structure for user_favorite_table
-- ----------------------------
DROP VIEW IF EXISTS `user_favorite_table`;
CREATE ALGORITHM = UNDEFINED SQL SECURITY DEFINER VIEW `user_favorite_table` AS select `uf`.`user` AS `userId`,`uf`.`id` AS `favoriteId`,`mpt`.`marketProduceId` AS `marketProduceId`,`mpt`.`produceId` AS `produceId`,`mpt`.`produceName` AS `produceName`,`mpt`.`categoryId` AS `categoryId`,`mpt`.`categoryName` AS `categoryName`,`mpt`.`marketId` AS `marketId`,`mpt`.`marketName` AS `marketName` from (`user_favorite` `uf` join `market_produce_table` `mpt` on((`uf`.`marketProduce` = `mpt`.`marketProduceId`)));

SET FOREIGN_KEY_CHECKS = 1;
