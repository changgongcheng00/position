/*
Navicat MySQL Data Transfer

Source Server         : 本地连接
Source Server Version : 50720
Source Host           : 192.168.1.114:3306
Source Database       : trafigura

Target Server Type    : MYSQL
Target Server Version : 50720
File Encoding         : 65001

Date: 2020-07-13 16:01:33
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `transactions`
-- ----------------------------
DROP TABLE IF EXISTS `transactions`;
CREATE TABLE `transactions` (
  `transaction_id` int(10) NOT NULL AUTO_INCREMENT,
  `trade_id` int(10) DEFAULT '0',
  `version` int(10) DEFAULT '0',
  `security_code` varchar(255) DEFAULT NULL,
  `quantity` int(10) DEFAULT '0',
  `operation` varchar(255) DEFAULT NULL COMMENT 'insert/update/cancel',
  `deal` varchar(255) DEFAULT NULL COMMENT 'buy/sell',
  PRIMARY KEY (`transaction_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of transactions
-- ----------------------------
INSERT INTO `transactions` VALUES ('1', '1', '1', 'REL', '50', 'INSERT', 'Buy');
INSERT INTO `transactions` VALUES ('2', '2', '1', 'ITC', '40', 'INSERT', 'Sell');
INSERT INTO `transactions` VALUES ('3', '3', '1', 'INF', '70', 'INSERT', 'Buy');
INSERT INTO `transactions` VALUES ('4', '1', '2', 'REL', '60', 'UPDATE', 'Buy');
INSERT INTO `transactions` VALUES ('5', '2', '2', 'ITC', '30', 'CANCEL', 'Buy');
INSERT INTO `transactions` VALUES ('6', '4', '1', 'INF', '20', 'INSERT', 'Sell');
