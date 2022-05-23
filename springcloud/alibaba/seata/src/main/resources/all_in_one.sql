# Account
DROP SCHEMA IF EXISTS db_account;
CREATE SCHEMA db_account;
USE db_account;

CREATE TABLE `account`
(
    `id`      bigint(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
    `user_id` bigint(11) DEFAULT NULL COMMENT '用户id',
    `total`   decimal(10, 0) DEFAULT NULL COMMENT '总额度',
    `used`    decimal(10, 0) DEFAULT NULL COMMENT '已用余额',
    `residue` decimal(10, 0) DEFAULT '0' COMMENT '剩余可用额度',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

INSERT INTO `account` (`id`, `user_id`, `total`, `used`, `residue`)
VALUES ('1', '1', '1000', '0', '100');

# Order
DROP SCHEMA IF EXISTS db_order;
CREATE SCHEMA db_order;
USE db_order;

CREATE TABLE `order`
(
    `id`         bigint(11) NOT NULL AUTO_INCREMENT,
    `user_id`    bigint(11) DEFAULT NULL COMMENT '用户id',
    `product_id` bigint(11) DEFAULT NULL COMMENT '产品id',
    `count`      int(11) DEFAULT NULL COMMENT '数量',
    `money`      decimal(11, 0) DEFAULT NULL COMMENT '金额',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

ALTER TABLE `order`
    ADD COLUMN `status` int(1) DEFAULT NULL COMMENT '订单状态：0：创建中；1：已完结' AFTER `money`;

# stock
DROP SCHEMA IF EXISTS db_stock;
CREATE SCHEMA db_stock;
USE db_stock;

CREATE TABLE `stock`
(
    `id`         bigint(11) NOT NULL AUTO_INCREMENT,
    `product_id` bigint(11) DEFAULT NULL COMMENT '产品id',
    `total`      int(11) DEFAULT NULL COMMENT '总库存',
    `used`       int(11) DEFAULT NULL COMMENT '已用库存',
    `residue`    int(11) DEFAULT NULL COMMENT '剩余库存',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

INSERT INTO `stock` (`id`, `product_id`, `total`, `used`, `residue`)
VALUES ('1', '1', '100', '0', '100');