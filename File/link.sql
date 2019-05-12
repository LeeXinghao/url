/*
 Navicat Premium Data Transfer

 Source Server         : local aicsdb
 Source Server Type    : MariaDB
 Source Server Version : 100314
 Source Host           : localhost:3306
 Source Schema         : urldb

 Target Server Type    : MariaDB
 Target Server Version : 100314
 File Encoding         : 65001

 Date: 12/05/2019 21:34:37
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for link
-- ----------------------------
DROP TABLE IF EXISTS `link`;
CREATE TABLE `link` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `shortUrl` text COLLATE utf8_unicode_ci NOT NULL,
  `longUrl` text COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

SET FOREIGN_KEY_CHECKS = 1;
