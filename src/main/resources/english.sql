/*
Navicat MySQL Data Transfer

Source Server         : local
Source Server Version : 50634
Source Host           : localhost:3306
Source Database       : english

Target Server Type    : MYSQL
Target Server Version : 50634
File Encoding         : 65001

Date: 2017-08-23 17:56:11
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for cn_definition
-- ----------------------------
DROP TABLE IF EXISTS `cn_definition`;
CREATE TABLE `cn_definition` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `word_id` int(11) DEFAULT NULL COMMENT '单词id',
  `pos` varchar(30) DEFAULT NULL COMMENT '词类',
  `defn` varchar(255) DEFAULT NULL COMMENT '释义',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of cn_definition
-- ----------------------------
INSERT INTO `cn_definition` VALUES ('1', '1', '', 'n. 伞,保护伞\nvt. 用伞遮住');
INSERT INTO `cn_definition` VALUES ('2', '2', '', 'n. 早晨, 上午, 开端');
INSERT INTO `cn_definition` VALUES ('3', '3', '', 'n.字母y');

-- ----------------------------
-- Table structure for en_definition
-- ----------------------------
DROP TABLE IF EXISTS `en_definition`;
CREATE TABLE `en_definition` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `word_id` int(11) DEFAULT NULL COMMENT '单词id',
  `pos` varchar(10) DEFAULT NULL COMMENT '词类',
  `defn` varchar(255) DEFAULT NULL COMMENT '释义',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of en_definition
-- ----------------------------
INSERT INTO `en_definition` VALUES ('1', '1', 'adj', 'covering or applying simultaneously to a number of similar items or elements or groups');
INSERT INTO `en_definition` VALUES ('2', '2', 'n', 'the time period between dawn and noon; a conventional expression of greeting or farewell; the first light of day');
INSERT INTO `en_definition` VALUES ('3', '3', null, null);

-- ----------------------------
-- Table structure for en_defn_adj
-- ----------------------------
DROP TABLE IF EXISTS `en_defn_adj`;
CREATE TABLE `en_defn_adj` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `word_id` int(11) DEFAULT NULL COMMENT '单词id',
  `adj` varchar(255) DEFAULT NULL COMMENT '形容词解释',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of en_defn_adj
-- ----------------------------
INSERT INTO `en_defn_adj` VALUES ('1', '1', 'covering or applying simultaneously to a number of similar items or elements or groups');

-- ----------------------------
-- Table structure for en_defn_adv
-- ----------------------------
DROP TABLE IF EXISTS `en_defn_adv`;
CREATE TABLE `en_defn_adv` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `word_id` int(11) DEFAULT NULL COMMENT '单词id',
  `adv` varchar(255) DEFAULT NULL COMMENT '副词解释',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of en_defn_adv
-- ----------------------------

-- ----------------------------
-- Table structure for en_defn_art
-- ----------------------------
DROP TABLE IF EXISTS `en_defn_art`;
CREATE TABLE `en_defn_art` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `word_id` int(11) DEFAULT NULL COMMENT '单词id',
  `art` varchar(255) DEFAULT NULL COMMENT '冠词解释',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of en_defn_art
-- ----------------------------

-- ----------------------------
-- Table structure for en_defn_conj
-- ----------------------------
DROP TABLE IF EXISTS `en_defn_conj`;
CREATE TABLE `en_defn_conj` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `word_id` int(11) DEFAULT NULL COMMENT '单词id',
  `conj` varchar(255) DEFAULT NULL COMMENT '连词解释',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of en_defn_conj
-- ----------------------------

-- ----------------------------
-- Table structure for en_defn_interj
-- ----------------------------
DROP TABLE IF EXISTS `en_defn_interj`;
CREATE TABLE `en_defn_interj` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `word_id` int(11) DEFAULT NULL COMMENT '单词id',
  `interj` varchar(255) DEFAULT NULL COMMENT '感叹词解释',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of en_defn_interj
-- ----------------------------

-- ----------------------------
-- Table structure for en_defn_n
-- ----------------------------
DROP TABLE IF EXISTS `en_defn_n`;
CREATE TABLE `en_defn_n` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `word_id` int(11) DEFAULT NULL COMMENT '单词id',
  `n` varchar(255) DEFAULT NULL COMMENT '名词解释',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of en_defn_n
-- ----------------------------
INSERT INTO `en_defn_n` VALUES ('1', '1', 'a lightweight handheld collapsible canopy');
INSERT INTO `en_defn_n` VALUES ('2', '1', 'a formation of military planes maintained over ground operations or targets');
INSERT INTO `en_defn_n` VALUES ('3', '1', 'having the function of uniting a group of similar things');
INSERT INTO `en_defn_n` VALUES ('4', '2', 'the time period between dawn and noon');
INSERT INTO `en_defn_n` VALUES ('5', '2', 'a conventional expression of greeting or farewell');
INSERT INTO `en_defn_n` VALUES ('6', '2', 'the first light of day');

-- ----------------------------
-- Table structure for en_defn_num
-- ----------------------------
DROP TABLE IF EXISTS `en_defn_num`;
CREATE TABLE `en_defn_num` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `word_id` int(11) DEFAULT NULL COMMENT '单词id',
  `num` varchar(255) DEFAULT NULL COMMENT '数词解释',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of en_defn_num
-- ----------------------------

-- ----------------------------
-- Table structure for en_defn_prep
-- ----------------------------
DROP TABLE IF EXISTS `en_defn_prep`;
CREATE TABLE `en_defn_prep` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `word_id` int(11) DEFAULT NULL COMMENT '单词id',
  `prep` varchar(255) DEFAULT NULL COMMENT '介词解释',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of en_defn_prep
-- ----------------------------

-- ----------------------------
-- Table structure for en_defn_pron
-- ----------------------------
DROP TABLE IF EXISTS `en_defn_pron`;
CREATE TABLE `en_defn_pron` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `word_id` int(11) DEFAULT NULL COMMENT '单词id',
  `pron` varchar(255) DEFAULT NULL COMMENT '代词解释',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of en_defn_pron
-- ----------------------------

-- ----------------------------
-- Table structure for en_defn_v
-- ----------------------------
DROP TABLE IF EXISTS `en_defn_v`;
CREATE TABLE `en_defn_v` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `word_id` int(11) DEFAULT NULL COMMENT '单词id',
  `v` varchar(255) DEFAULT NULL COMMENT '动词解释',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of en_defn_v
-- ----------------------------

-- ----------------------------
-- Table structure for pronunciations
-- ----------------------------
DROP TABLE IF EXISTS `pronunciations`;
CREATE TABLE `pronunciations` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `word_id` int(255) DEFAULT NULL COMMENT '单词表id',
  `us` varchar(100) DEFAULT '' COMMENT '美语音标',
  `uk` varchar(255) DEFAULT NULL COMMENT '英语音标',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of pronunciations
-- ----------------------------
INSERT INTO `pronunciations` VALUES ('1', '1', 'ʌm\'brelə', 'ʌm\'brelə');
INSERT INTO `pronunciations` VALUES ('2', '2', '\'mɔːrnɪŋ', '\'mɔːnɪŋ');
INSERT INTO `pronunciations` VALUES ('3', '3', 'waɪ', 'waɪ');

-- ----------------------------
-- Table structure for uk_audio_addresses
-- ----------------------------
DROP TABLE IF EXISTS `uk_audio_addresses`;
CREATE TABLE `uk_audio_addresses` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `word_id` int(11) DEFAULT NULL COMMENT '单词id',
  `url` varchar(255) DEFAULT NULL COMMENT '发音文件地址',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of uk_audio_addresses
-- ----------------------------
INSERT INTO `uk_audio_addresses` VALUES ('1', '1', 'https://media-audio1.baydn.com/uk%2Fu%2Fum%2Fumbrella_v4.mp3');
INSERT INTO `uk_audio_addresses` VALUES ('2', '1', 'http://media-audio1.qiniu.baydn.com/uk/u/um/umbrella_v4.mp3');
INSERT INTO `uk_audio_addresses` VALUES ('3', '2', 'https://media-audio1.baydn.com/uk%2Fm%2Fmo%2Fmorning_v3.mp3');
INSERT INTO `uk_audio_addresses` VALUES ('4', '2', 'http://media-audio1.qiniu.baydn.com/uk/m/mo/morning_v3.mp3');
INSERT INTO `uk_audio_addresses` VALUES ('5', '3', 'https://media-audio1.baydn.com/uk%2FY%2FY%2FY.mp3');
INSERT INTO `uk_audio_addresses` VALUES ('6', '3', 'http://media-audio1.qiniu.baydn.com/uk/Y/Y/Y.mp3');

-- ----------------------------
-- Table structure for us_audio_addresses
-- ----------------------------
DROP TABLE IF EXISTS `us_audio_addresses`;
CREATE TABLE `us_audio_addresses` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `word_id` int(11) DEFAULT NULL COMMENT '单词id',
  `url` varchar(255) DEFAULT NULL COMMENT '发音地址url',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of us_audio_addresses
-- ----------------------------
INSERT INTO `us_audio_addresses` VALUES ('1', '1', 'https://media-audio1.baydn.com/us%2Fu%2Fum%2Fumbrella_v4.mp3');
INSERT INTO `us_audio_addresses` VALUES ('2', '1', 'http://media-audio1.qiniu.baydn.com/us/u/um/umbrella_v4.mp3');
INSERT INTO `us_audio_addresses` VALUES ('3', '2', 'https://media-audio1.baydn.com/us%2Fm%2Fmo%2Fmorning_v3.mp3');
INSERT INTO `us_audio_addresses` VALUES ('4', '2', 'http://media-audio1.qiniu.baydn.com/us/m/mo/morning_v3.mp3');
INSERT INTO `us_audio_addresses` VALUES ('5', '3', 'https://media-audio1.baydn.com/us%2FY%2FY%2FY.mp3');
INSERT INTO `us_audio_addresses` VALUES ('6', '3', 'http://media-audio1.qiniu.baydn.com/us/Y/Y/Y.mp3');

-- ----------------------------
-- Table structure for word
-- ----------------------------
DROP TABLE IF EXISTS `word`;
CREATE TABLE `word` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `uk_audio` varchar(255) DEFAULT NULL COMMENT '发音url',
  `conent_id` int(11) DEFAULT NULL COMMENT '单词ID',
  `audio_name` varchar(255) DEFAULT NULL COMMENT '发音文件的名称',
  `num_sense` int(11) DEFAULT NULL,
  `content_type` varchar(30) DEFAULT NULL,
  `sense_id` int(11) DEFAULT NULL,
  `definition` varchar(255) DEFAULT NULL,
  `content_id` int(11) DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL,
  `has_audio` int(1) DEFAULT NULL COMMENT '是否有语音 0 无 1 有',
  `object_id` int(11) DEFAULT NULL,
  `content` varchar(255) DEFAULT NULL COMMENT '单词内容',
  `pron` varchar(255) DEFAULT NULL COMMENT '音标',
  `pronunciation` varchar(255) DEFAULT NULL COMMENT '音标',
  `audio` varchar(255) DEFAULT NULL COMMENT '发音的地址',
  `us_audio` varchar(255) DEFAULT NULL COMMENT '发音的地址',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of word
-- ----------------------------
INSERT INTO `word` VALUES ('1', 'http://media.shanbay.com/audio/uk/umbrella.mp3', '6313', 'umbrella_v4', '1', null, null, null, '6313', 'https://www.shanbay.com/bdc/mobile/preview/word?word=umbrella', '1', '6313', 'umbrella', 'ʌm\'brelə', 'ʌm\'brelə', 'http://media.shanbay.com/audio/us/umbrella.mp3', 'http://media.shanbay.com/audio/us/umbrella.mp3');
INSERT INTO `word` VALUES ('2', 'http://media.shanbay.com/audio/uk/morning.mp3', '310', 'morning_v3', '1', null, null, null, '310', 'https://www.shanbay.com/bdc/mobile/preview/word?word=morning', '1', '310', 'morning', '\'mɔːrnɪŋ', '\'mɔːrnɪŋ', 'http://media.shanbay.com/audio/us/morning.mp3', 'http://media.shanbay.com/audio/us/morning.mp3');
INSERT INTO `word` VALUES ('3', 'http://media.shanbay.com/audio/uk/y.mp3', '415555', 'Y', '0', null, null, null, '415555', 'https://www.shanbay.com/bdc/mobile/preview/word?word=Y', '1', '415555', 'Y', 'waɪ', 'waɪ', 'http://media.shanbay.com/audio/us/Y.mp3', 'http://media.shanbay.com/audio/us/y.mp3');
