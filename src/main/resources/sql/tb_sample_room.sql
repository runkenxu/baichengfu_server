/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50729
 Source Host           : localhost:3306
 Source Schema         : baichengfu

 Target Server Type    : MySQL
 Target Server Version : 50729
 File Encoding         : 65001

 Date: 11/04/2020 16:37:16
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for tb_sample_room
-- ----------------------------
DROP TABLE IF EXISTS `tb_sample_room`;
CREATE TABLE `tb_sample_room`
(
    `id`                varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci  NOT NULL COMMENT '主键id',
    `manager_id`        varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci  NULL DEFAULT NULL COMMENT '管理员id',
    `appuser_id`        varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci  NULL DEFAULT NULL COMMENT 'app用户id',
    `uploader_type`     int(11)                                                 NULL DEFAULT NULL COMMENT '上传者类型10管理员20用户）',
    `sample_title`      varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '样板间标题',
    `sample_area`       varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '样板间面积（平方）',
    `sample_link`       varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '样板间链接',
    `sample_type`       varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '样板间风格',
    `sample_image`      text CHARACTER SET utf8 COLLATE utf8_general_ci         NULL COMMENT '样板间封面图，每个图片链接使用分号分割',
    `s_favorite_number` int(30)                                                 NULL DEFAULT 0 COMMENT '样板间浏览',
    `order_count`       int(11)                                                 NULL DEFAULT NULL COMMENT '排序权重，收藏数+浏览数',
    `focus_count`       int(30)                                                 NULL DEFAULT 0 COMMENT '样板间收藏',
    `is_delete`         int(11)                                                 NULL DEFAULT 0 COMMENT '删除状态 0正常 1删除',
    `creater_id`        varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci  NULL DEFAULT NULL COMMENT '创建人id',
    `create_date`       TIMESTAMP                                               NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `operator_id`       varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci  NULL DEFAULT NULL COMMENT '最后修改人id',
    `operate_date`      datetime                                                NULL DEFAULT '0000-00-00 00:00:00' COMMENT '最后修改日期',
    `furniture`         varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci  NULL DEFAULT NULL COMMENT '定制家具',
    `material`          varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci  NULL DEFAULT NULL COMMENT '材质',
    `cost`              decimal(10, 2)                                          NULL DEFAULT NULL COMMENT '费用',
    `note`              text CHARACTER SET utf8 COLLATE utf8_general_ci         NULL COMMENT '简介',
    `tips`              text CHARACTER SET utf8 COLLATE utf8_general_ci         NULL COMMENT '摘要',
    `designer_id`       varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci  NULL DEFAULT NULL COMMENT '设计师id',
    `designer_head`     varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '设计师头像',
    `designer_name`     varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '设计师昵称',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  CHARACTER SET = utf8
  COLLATE = utf8_general_ci COMMENT = '样板间表'
  ROW_FORMAT = Compact;

-- ----------------------------
-- Records of tb_sample_room
-- ----------------------------
INSERT INTO `tb_sample_room`
VALUES ('1', NULL, NULL, NULL, '1234', NULL, NULL, NULL, NULL, 0, NULL, 0, 0, NULL, '2020-04-11 16:09:43', NULL,
        '2020-04-11 16:09:43', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);

SET FOREIGN_KEY_CHECKS = 1;
