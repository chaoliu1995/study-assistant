/*
 Navicat Premium Data Transfer

 Source Server         : Local
 Source Server Type    : MySQL
 Source Server Version : 50722
 Source Host           : localhost:3306
 Source Schema         : english

 Target Server Type    : MySQL
 Target Server Version : 50722
 File Encoding         : 65001

 Date: 06/12/2018 19:10:41
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for book
-- ----------------------------
DROP TABLE IF EXISTS `book`;
CREATE TABLE `book`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `user_id` int(11) NOT NULL COMMENT '创建者的id',
  `child_ids` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `name_user_id_unique`(`name`, `user_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for book_word
-- ----------------------------
DROP TABLE IF EXISTS `book_word`;
CREATE TABLE `book_word`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `book_id` int(11) NOT NULL,
  `word_id` int(11) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `book_word_unique`(`book_id`, `word_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for cn_definition
-- ----------------------------
DROP TABLE IF EXISTS `cn_definition`;
CREATE TABLE `cn_definition`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `word_id` int(11) DEFAULT NULL COMMENT '单词id',
  `pos` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '词类',
  `defn` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '释义',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 187 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for en_definition
-- ----------------------------
DROP TABLE IF EXISTS `en_definition`;
CREATE TABLE `en_definition`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `word_id` int(11) DEFAULT NULL COMMENT '单词id',
  `pos` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '词类',
  `defn` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '释义',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 187 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for en_defn_adj
-- ----------------------------
DROP TABLE IF EXISTS `en_defn_adj`;
CREATE TABLE `en_defn_adj`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `word_id` int(11) DEFAULT NULL COMMENT '单词id',
  `adj` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '形容词解释',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 193 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for en_defn_adv
-- ----------------------------
DROP TABLE IF EXISTS `en_defn_adv`;
CREATE TABLE `en_defn_adv`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `word_id` int(11) DEFAULT NULL COMMENT '单词id',
  `adv` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '副词解释',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 50 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for en_defn_art
-- ----------------------------
DROP TABLE IF EXISTS `en_defn_art`;
CREATE TABLE `en_defn_art`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `word_id` int(11) DEFAULT NULL COMMENT '单词id',
  `art` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '冠词解释',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for en_defn_conj
-- ----------------------------
DROP TABLE IF EXISTS `en_defn_conj`;
CREATE TABLE `en_defn_conj`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `word_id` int(11) DEFAULT NULL COMMENT '单词id',
  `conj` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '连词解释',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for en_defn_interj
-- ----------------------------
DROP TABLE IF EXISTS `en_defn_interj`;
CREATE TABLE `en_defn_interj`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `word_id` int(11) DEFAULT NULL COMMENT '单词id',
  `interj` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '感叹词解释',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for en_defn_n
-- ----------------------------
DROP TABLE IF EXISTS `en_defn_n`;
CREATE TABLE `en_defn_n`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `word_id` int(11) DEFAULT NULL COMMENT '单词id',
  `n` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '名词解释',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 354 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for en_defn_num
-- ----------------------------
DROP TABLE IF EXISTS `en_defn_num`;
CREATE TABLE `en_defn_num`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `word_id` int(11) DEFAULT NULL COMMENT '单词id',
  `num` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '数词解释',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for en_defn_prep
-- ----------------------------
DROP TABLE IF EXISTS `en_defn_prep`;
CREATE TABLE `en_defn_prep`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `word_id` int(11) DEFAULT NULL COMMENT '单词id',
  `prep` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '介词解释',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for en_defn_pron
-- ----------------------------
DROP TABLE IF EXISTS `en_defn_pron`;
CREATE TABLE `en_defn_pron`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `word_id` int(11) DEFAULT NULL COMMENT '单词id',
  `pron` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '代词解释',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for en_defn_v
-- ----------------------------
DROP TABLE IF EXISTS `en_defn_v`;
CREATE TABLE `en_defn_v`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `word_id` int(11) DEFAULT NULL COMMENT '单词id',
  `v` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '动词解释',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 185 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for pronunciations
-- ----------------------------
DROP TABLE IF EXISTS `pronunciations`;
CREATE TABLE `pronunciations`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `word_id` int(255) DEFAULT NULL COMMENT '单词表id',
  `us` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '' COMMENT '美语音标',
  `uk` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '英语音标',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 187 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for uk_audio_addresses
-- ----------------------------
DROP TABLE IF EXISTS `uk_audio_addresses`;
CREATE TABLE `uk_audio_addresses`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `word_id` int(11) DEFAULT NULL COMMENT '单词id',
  `url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '发音文件地址',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 373 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for us_audio_addresses
-- ----------------------------
DROP TABLE IF EXISTS `us_audio_addresses`;
CREATE TABLE `us_audio_addresses`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `word_id` int(11) DEFAULT NULL COMMENT '单词id',
  `url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '发音地址url',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 373 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int(5) NOT NULL AUTO_INCREMENT,
  `username` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `reviewing_book_id` int(11) DEFAULT NULL,
  `adding_book_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `username_unique`(`username`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for user_book
-- ----------------------------
DROP TABLE IF EXISTS `user_book`;
CREATE TABLE `user_book`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `book_id` int(11) NOT NULL,
  `status` tinyint(2) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `user_book_unique`(`user_id`, `book_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for user_word
-- ----------------------------
DROP TABLE IF EXISTS `user_word`;
CREATE TABLE `user_word`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `word_id` int(11) NOT NULL,
  `search_count` int(10) NOT NULL COMMENT '查询次数',
  `show_time` bigint(15) DEFAULT NULL COMMENT '下次显示时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `user_word_unique`(`user_id`, `word_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for word
-- ----------------------------
DROP TABLE IF EXISTS `word`;
CREATE TABLE `word`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `uk_audio` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '发音url',
  `us_audio` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '发音的地址',
  `audio_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '发音文件的名称',
  `content_type` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `cn_definition` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '中文释义',
  `en_definition` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '英文释义',
  `content_id` int(11) DEFAULT NULL,
  `has_audio` int(1) DEFAULT NULL COMMENT '是否有语音 0 无 1 有',
  `content` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '单词内容',
  `us_pronunciation` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '音标',
  `uk_pronunciation` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `content_unique` (`content`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 187 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
