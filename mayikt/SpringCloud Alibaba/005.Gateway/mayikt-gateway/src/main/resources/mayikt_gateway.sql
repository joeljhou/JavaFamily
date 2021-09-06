/*
 Navicat Premium Data Transfer

 Source Server         : 127.0.0.1
 Source Server Type    : MySQL
 Source Server Version : 50734
 Source Host           : localhost:3306
 Source Schema         : mayikt_gateway

 Target Server Type    : MySQL
 Target Server Version : 50734
 File Encoding         : 65001

 Date: 06/09/2021 22:00:12
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for mayikt_gateway
-- ----------------------------
DROP TABLE IF EXISTS `mayikt_gateway`;
CREATE TABLE `mayikt_gateway`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `route_id` varchar(11) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `route_name` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `route_pattern` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `route_type` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `route_url` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of mayikt_gateway
-- ----------------------------
INSERT INTO `mayikt_gateway` VALUES (1, 'member', 'mayiktmayikt', '/member/**', '0', 'mayikt-member');
INSERT INTO `mayikt_gateway` VALUES (2, 'order', 'mayiktorder', '/order/**', '0', 'mayikt-order');

SET FOREIGN_KEY_CHECKS = 1;
