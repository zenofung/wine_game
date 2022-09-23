/*
 Navicat Premium Data Transfer

 Source Server         : wine
 Source Server Type    : MySQL
 Source Server Version : 50737
 Source Host           : 121.40.90.22:3306
 Source Schema         : wine

 Target Server Type    : MySQL
 Target Server Version : 50737
 File Encoding         : 65001

 Date: 23/09/2022 14:53:50
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for w_article
-- ----------------------------
DROP TABLE IF EXISTS `w_article`;
CREATE TABLE `w_article`  (
  `id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `content` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `images` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `user_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `wine_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `article_address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `praise` int(255) NULL DEFAULT 0,
  `status` int(2) NULL DEFAULT 0,
  `update_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0),
  `create_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of w_article
-- ----------------------------
INSERT INTO `w_article` VALUES ('1555028403158761473', 'dfadf sd', 'https://www.homiesocial.cn/static/miniImage/85a6f438-c5b7-4a3e-97d0-709034f1a82e.jpg', '1555027336471756801', '', '百安居(杭海店)', 0, 0, '2022-08-04 03:11:35', '2022-08-04 03:10:37');
INSERT INTO `w_article` VALUES ('1555028518086885378', 'tyuytuyt', 'https://www.homiesocial.cn/static/miniImage/1734bfcb-970f-4a3e-9255-d4d8599b02ab.jpg', '1555027336471756801', NULL, '百安居(杭海店)', NULL, NULL, '2022-08-04 03:11:04', '2022-08-04 03:11:04');

-- ----------------------------
-- Table structure for w_article_label
-- ----------------------------
DROP TABLE IF EXISTS `w_article_label`;
CREATE TABLE `w_article_label`  (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `label_id` int(10) NOT NULL,
  `article_id` int(10) NULL DEFAULT NULL,
  `create_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for w_article_praise
-- ----------------------------
DROP TABLE IF EXISTS `w_article_praise`;
CREATE TABLE `w_article_praise`  (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `user_id` int(10) NULL DEFAULT NULL,
  `article_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `status` int(2) NULL DEFAULT 0,
  `create_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for w_attention
-- ----------------------------
DROP TABLE IF EXISTS `w_attention`;
CREATE TABLE `w_attention`  (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `me_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `follower_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `status` int(2) NULL DEFAULT NULL,
  `create_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0),
  `update_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for w_attention_comment
-- ----------------------------
DROP TABLE IF EXISTS `w_attention_comment`;
CREATE TABLE `w_attention_comment`  (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `article_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `comment_id` int(10) NULL DEFAULT NULL,
  `status` int(2) NULL DEFAULT NULL,
  `update_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0),
  `create_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for w_com_com
-- ----------------------------
DROP TABLE IF EXISTS `w_com_com`;
CREATE TABLE `w_com_com`  (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `com_id` int(10) NULL DEFAULT NULL,
  `user_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `user_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `user_id_two` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `com_id_two` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `com_context` varchar(888) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `status` int(2) NULL DEFAULT NULL,
  `update_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0),
  `create_time` timestamp(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for w_com_com_praise
-- ----------------------------
DROP TABLE IF EXISTS `w_com_com_praise`;
CREATE TABLE `w_com_com_praise`  (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `user_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `com_com_id` int(255) NULL DEFAULT NULL,
  `status` int(2) NULL DEFAULT 0,
  `create_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for w_comment
-- ----------------------------
DROP TABLE IF EXISTS `w_comment`;
CREATE TABLE `w_comment`  (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `user_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `art_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `content` varchar(800) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `praise` int(10) NULL DEFAULT NULL,
  `status` int(11) NULL DEFAULT NULL,
  `create_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for w_im_message
-- ----------------------------
DROP TABLE IF EXISTS `w_im_message`;
CREATE TABLE `w_im_message`  (
  `mag_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `user_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `target_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `mag_content` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `im_mag_list_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `message_status` int(2) NULL DEFAULT 0,
  `message_user` int(255) NULL DEFAULT NULL,
  `status` int(2) NULL DEFAULT 0,
  `update_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0),
  `create_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`mag_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for w_im_message_list
-- ----------------------------
DROP TABLE IF EXISTS `w_im_message_list`;
CREATE TABLE `w_im_message_list`  (
  `id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `user_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `friend_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `status` int(10) NULL DEFAULT 0,
  `height` int(10) NULL DEFAULT NULL,
  `on_line` int(10) NULL DEFAULT 0,
  `update_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0),
  `create_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for w_label
-- ----------------------------
DROP TABLE IF EXISTS `w_label`;
CREATE TABLE `w_label`  (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `label` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `status` int(2) NULL DEFAULT 0,
  `create_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for w_user
-- ----------------------------
DROP TABLE IF EXISTS `w_user`;
CREATE TABLE `w_user`  (
  `id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `open_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `user_nikename` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `user_protrait` varchar(888) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `user_sex` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `user_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `user_phone` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `user_address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `last_ip` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `login_status` int(10) NULL DEFAULT NULL,
  `tourist` int(10) NULL DEFAULT NULL,
  `status` int(2) NULL DEFAULT 0,
  `user_lat` decimal(10, 5) NULL DEFAULT NULL,
  `user_long` decimal(10, 5) NULL DEFAULT NULL,
  `update_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0),
  `create_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of w_user
-- ----------------------------
INSERT INTO `w_user` VALUES ('1555027336471756801', '123456', '塌猾', 'data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAHgAAAB4CAIAAAC2BqGFAAAA5UlEQVR42u3ZQQ0AIAwEQZxjBAmIQwIeSCAtnc0Z6Hzblp7UEIAGLdCgQQs0aIFOCt3HPF5Gjnv3ggYNGjRo0KBBgwYNGjRo0KBBgwYNGjRo0KBBgwYNGjRo0KBBgwYNGjRo0KBBgwYNGjRo0KBBgwYNGjRo0LWgqw00aNCgQYMGDRo0aNCgQYMGDRo0aNCgQYMGDRo06B+hYz46Mz6UQYMGDRo0aNCgQYMGDRo0aNCgQYMGDRo0aNCgQYOuBy3QoEELNGiBBg1aoEELNGjQAg1aoEGDFmjQAg0atECDFmjQoAU6fBs0VnGFYl1TEQAAAABJRU5ErkJggg==', NULL, '123@qq.com', '123@qq.com', NULL, '183.157.59.180', NULL, 0, 0, NULL, NULL, '2022-08-04 03:06:22', '2022-08-04 03:06:22');

-- ----------------------------
-- Table structure for w_user_ranking
-- ----------------------------
DROP TABLE IF EXISTS `w_user_ranking`;
CREATE TABLE `w_user_ranking`  (
  `id` int(10) NOT NULL,
  `user_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `active` decimal(10, 2) NULL DEFAULT NULL,
  `social` decimal(10, 2) NULL DEFAULT NULL,
  `update_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0),
  `create_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for w_wine
-- ----------------------------
DROP TABLE IF EXISTS `w_wine`;
CREATE TABLE `w_wine`  (
  `id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `un_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `subject` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `time` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `place` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `wine_bar_id` int(10) NULL DEFAULT NULL,
  `peple_number` int(10) NULL DEFAULT NULL,
  `title_image` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `object_boy` int(10) NULL DEFAULT NULL,
  `object_girl` int(10) NULL DEFAULT NULL,
  `object` int(10) NULL DEFAULT NULL,
  `wine_type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `paymet_type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `wine_table_number` int(11) NULL DEFAULT NULL,
  `status` int(255) NULL DEFAULT 0,
  `update_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0),
  `create_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for w_wine_attention
-- ----------------------------
DROP TABLE IF EXISTS `w_wine_attention`;
CREATE TABLE `w_wine_attention`  (
  `id` int(10) NOT NULL,
  `user_id` int(10) NULL DEFAULT NULL,
  `wine_bar` int(10) NULL DEFAULT NULL,
  `status` int(10) NULL DEFAULT NULL,
  `update_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for w_wine_bar
-- ----------------------------
DROP TABLE IF EXISTS `w_wine_bar`;
CREATE TABLE `w_wine_bar`  (
  `id` int(11) NOT NULL,
  `bar_tittle` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `bar_banner` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `bar_type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `people_ave_oney` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `openinghours` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `business_phone` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `bar_favorability` int(255) NULL DEFAULT NULL,
  `bar_location` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `barLocation_details` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `bar_location_lat` decimal(10, 2) NULL DEFAULT NULL,
  `bar_location_long` decimal(10, 2) NULL DEFAULT NULL,
  `status` int(255) NULL DEFAULT NULL,
  `bar_sales` int(255) NULL DEFAULT NULL,
  `update_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for w_wine_users
-- ----------------------------
DROP TABLE IF EXISTS `w_wine_users`;
CREATE TABLE `w_wine_users`  (
  `id` int(10) NOT NULL,
  `wine_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `user_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `status` int(10) NULL DEFAULT 0,
  `create_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for w_wine_users_ready
-- ----------------------------
DROP TABLE IF EXISTS `w_wine_users_ready`;
CREATE TABLE `w_wine_users_ready`  (
  `id` int(10) NOT NULL,
  `wine_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `user_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `flag` int(10) NULL DEFAULT NULL,
  `status` int(10) NULL DEFAULT 0,
  `create_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0),
  `update_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
