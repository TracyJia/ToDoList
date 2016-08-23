/*
MySQL Data Transfer
Source Host: localhost
Source Database: todolist
Target Host: localhost
Target Database: todolist
Date: 2016/8/15 10:58:36
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for tasks
-- ----------------------------
CREATE TABLE `tasks` (
  `username` varchar(30) DEFAULT NULL,
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `taskname` varchar(50) NOT NULL,
  `accomplished` int(2) NOT NULL DEFAULT '0' COMMENT '默认未完成',
  `user_email` varchar(30) NOT NULL,
  PRIMARY KEY (`id`,`user_email`),
  KEY `tasks_belong` (`user_email`),
  CONSTRAINT `tasks_belong` FOREIGN KEY (`user_email`) REFERENCES `users` (`email`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for users
-- ----------------------------
CREATE TABLE `users` (
  `id` int(5) NOT NULL AUTO_INCREMENT,
  `username` varchar(30) DEFAULT NULL,
  `email` varchar(30) NOT NULL,
  `password` varchar(30) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `username` (`username`),
  KEY `email` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------

