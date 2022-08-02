/*
 Navicat Premium Data Transfer

 Source Server         : localhost3306
 Source Server Type    : MySQL
 Source Server Version : 50726
 Source Host           : localhost:3306
 Source Schema         : nacos

 Target Server Type    : MySQL
 Target Server Version : 50726
 File Encoding         : 65001

 Date: 02/08/2022 18:16:02
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for config_info
-- ----------------------------
DROP TABLE IF EXISTS `config_info`;
CREATE TABLE `config_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `data_id` varchar(255) COLLATE utf8_bin NOT NULL COMMENT 'data_id',
  `group_id` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `content` longtext COLLATE utf8_bin NOT NULL COMMENT 'content',
  `md5` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT 'md5',
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `src_user` text COLLATE utf8_bin COMMENT 'source user',
  `src_ip` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT 'source ip',
  `app_name` varchar(128) COLLATE utf8_bin DEFAULT NULL,
  `tenant_id` varchar(128) COLLATE utf8_bin DEFAULT '' COMMENT '租户字段',
  `c_desc` varchar(256) COLLATE utf8_bin DEFAULT NULL,
  `c_use` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `effect` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `type` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `c_schema` text COLLATE utf8_bin,
  `encrypted_data_key` text COLLATE utf8_bin COMMENT '秘钥',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_configinfo_datagrouptenant` (`data_id`,`group_id`,`tenant_id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='config_info';

-- ----------------------------
-- Records of config_info
-- ----------------------------
BEGIN;
INSERT INTO `config_info` VALUES (1, 'application.yml', 'pangu', 'mybatis-plus:\n  mapper-locations: classpath:/mapper/*Mapper.xml\n  typeAliasesPackage: com.camellibby.order.entity\n  global-config:\n    db-config:\n      field-strategy: not-empty\n      id-type: auto\n      db-type: mysql\n  configuration:\n    map-underscore-to-camel-case: true\n    cache-enabled: true\n    auto-mapping-unknown-column-behavior: none\n    log-impl: org.apache.ibatis.logging.stdout.StdOutImpl\n    ', '0b7a80aada24a1b90248a808d80eed81', '2022-06-03 07:01:24', '2022-08-01 15:40:24', NULL, '127.0.0.1', '', '', '', '', '', 'yaml', '', '');
INSERT INTO `config_info` VALUES (2, 'seata-application.yml', 'pangu', 'seata:\n  enabled: true\n  application-id: seata-server\n  tx-service-group: default_tx_group\n  enable-auto-data-source-proxy: true\n  data-source-proxy-mode: AT\n  log:\n    exception-rate: 100\n  config:\n    type: nacos\n    nacos:\n      namespace:\n      server-addr: 127.0.0.1:8861\n      group: pangu\n      username: nacos\n      password: nacos\n      data-id: seata-client.properties\n  security:\n    secretKey: SeataSecretKey0c382ef121d778043159209298fd40bf3850a017\n    tokenValidityInMilliseconds: 1800000\n    ignore:\n      urls: /,/**/*.css,/**/*.js,/**/*.html,/**/*.map,/**/*.svg,/**/*.png,/**/*.ico,/console-fe/public/**,/api/v1/auth/login\n  server:\n   service-port: 8091', '3140eb4918ddd72f41313b586494a53b', '2022-08-02 08:49:17', '2022-08-02 09:48:26', NULL, '127.0.0.1', '', '', '', '', '', 'yaml', '', NULL);
INSERT INTO `config_info` VALUES (3, 'seata-server.properties', 'pangu', 'store.mode=db\n\nstore.db.datasource=druid\nstore.db.dbType=mysql\nstore.db.driverClassName=com.mysql.cj.jdbc.Driver\n# if using mysql to store the data, recommend add rewriteBatchedStatements=true in jdbc connection param\nstore.db.url=jdbc:mysql://127.0.0.1:3306/seata?rewriteBatchedStatements=true\nstore.db.user=root\nstore.db.password=123456\nstore.db.minConn=5\nstore.db.maxConn=100\nstore.db.globalTable=global_table\nstore.db.branchTable=branch_table\nstore.db.lockTable=lock_table\nstore.db.distributedLockTable=distributed_lock\nstore.db.queryLimit=100\nstore.db.maxWait=5000\n\nserver.recovery.committingRetryPeriod=1000\nserver.recovery.asynCommittingRetryPeriod=1000\nserver.recovery.rollbackingRetryPeriod=1000\nserver.recovery.timeoutRetryPeriod=1000\nserver.maxCommitRetryTimeout=-1\nserver.maxRollbackRetryTimeout=-1\nserver.rollbackRetryTimeoutUnlockEnable=false\n\nmetrics.enabled=false\nmetrics.registryType=compact\nmetrics.exporterList=prometheus\nmetrics.exporterPrometheusPort=9898\n', '6621ff7fcde8e1fbe7f8fe212d74d57b', '2022-06-03 08:22:28', '2022-08-02 09:30:33', NULL, '127.0.0.1', '', '', '', '', '', 'properties', '', NULL);
INSERT INTO `config_info` VALUES (4, 'seata-client.properties', 'pangu', 'transport.type=TCP\ntransport.server=NIO\ntransport.heartbeat=true\ntransport.enableClientBatchSendRequest=false\ntransport.threadFactory.bossThreadPrefix=NettyBoss\ntransport.threadFactory.workerThreadPrefix=NettyServerNIOWorker\ntransport.threadFactory.serverExecutorThreadPrefix=NettyServerBizHandler\ntransport.threadFactory.shareBossWorker=false\ntransport.threadFactory.clientSelectorThreadPrefix=NettyClientSelector\ntransport.threadFactory.clientSelectorThreadSize=1\ntransport.threadFactory.clientWorkerThreadPrefix=NettyClientWorkerThread\ntransport.threadFactory.bossThreadSize=1\ntransport.threadFactory.workerThreadSize=default\ntransport.shutdown.wait=3\ntransport.serialization=seata\ntransport.compressor=none\nservice.vgroupMapping.default_tx_group=default\nservice.default.grouplist=127.0.0.1:8091\nservice.enableDegrade=false\nservice.disableGlobalTransaction=false\nclient.rm.asyncCommitBufferLimit=10000\nclient.rm.lock.retryInterval=10\nclient.rm.lock.retryTimes=30\nclient.rm.lock.retryPolicyBranchRollbackOnConflict=true\nclient.rm.reportRetryCount=5\nclient.rm.tableMetaCheckEnable=false\nclient.rm.tableMetaCheckerInterval=60000\nclient.rm.sqlParserType=druid\nclient.rm.reportSuccessEnable=false\nclient.rm.sagaBranchRegisterEnable=false\nclient.tm.commitRetryCount=5\nclient.tm.rollbackRetryCount=5\nclient.tm.defaultGlobalTransactionTimeout=60000\nclient.tm.degradeCheck=false\nclient.tm.degradeCheckAllowTimes=10\nclient.tm.degradeCheckPeriod=2000\nclient.undo.dataValidation=true\nclient.undo.logSerialization=jackson\nclient.undo.onlyCareUpdateColumns=true\nclient.undo.logTable=undo_log\nclient.undo.compress.enable=true\nclient.undo.compress.type=zip\nclient.undo.compress.threshold=64k\nlog.exceptionRate=100', '81af8ae34371ce049901a416064071fd', '2022-06-03 08:21:08', '2022-06-03 08:39:48', NULL, '0:0:0:0:0:0:0:1', '', '', '', '', '', 'properties', '', NULL);
INSERT INTO `config_info` VALUES (5, 'account.yml', 'pangu', 'spring:\n  datasource:\n    type: com.alibaba.druid.pool.DruidDataSource\n    driver-class-name: com.mysql.cj.jdbc.Driver\n    url: jdbc:mysql://127.0.0.1:3306/db_account?useSSL=false&serverTimezone=UTC\n    username: root\n    password: 123456', 'd850c26bd0cad4f7456374107b2d1f37', '2022-06-03 07:08:04', '2022-06-03 07:08:47', NULL, '0:0:0:0:0:0:0:1', '', '', '', '', '', 'yaml', '', NULL);
INSERT INTO `config_info` VALUES (6, 'storage.yml', 'pangu', 'spring:\n  datasource:\n    type: com.alibaba.druid.pool.DruidDataSource\n    driver-class-name: com.mysql.cj.jdbc.Driver\n    url: jdbc:mysql://127.0.0.1:3306/db_storage?useSSL=false&serverTimezone=UTC\n    username: root\n    password: 123456', '70186e6ea463444e76b5af35c2154683', '2022-06-03 07:08:20', '2022-06-03 07:08:57', NULL, '0:0:0:0:0:0:0:1', '', '', '', '', '', 'yaml', '', NULL);
INSERT INTO `config_info` VALUES (7, 'order.yml', 'pangu', 'spring:\r\n  datasource:\r\n    type: com.alibaba.druid.pool.DruidDataSource\r\n    driver-class-name: com.mysql.cj.jdbc.Driver\r\n    url: jdbc:mysql://127.0.0.1:3306/db_order?useSSL=false&serverTimezone=UTC\r\n    username: root\r\n    password: 123456', 'a624bc26e16dc6ab08621e6e10ee2812', '2022-06-03 07:07:28', '2022-08-01 15:40:42', NULL, '127.0.0.1', '', '', 'null', 'null', 'null', 'yaml', 'null', '');
INSERT INTO `config_info` VALUES (8, 'business.yml', 'pangu', 'foo: bar\n# spring:\n#   datasource:\n#     type: com.alibaba.druid.pool.DruidDataSource\n#     driver-class-name: com.mysql.cj.jdbc.Driver\n#     url: jdbc:mysql://127.0.0.1:3306/db_order?useSSL=false&serverTimezone=UTC\n#     username: root\n#     password: 123456', 'a9ec3fb27e8ab68c3821085da4350664', '2022-06-03 07:08:30', '2022-06-03 08:50:09', NULL, '0:0:0:0:0:0:0:1', '', '', '', '', '', 'yaml', '', NULL);
COMMIT;

-- ----------------------------
-- Table structure for config_info_aggr
-- ----------------------------
DROP TABLE IF EXISTS `config_info_aggr`;
CREATE TABLE `config_info_aggr` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `data_id` varchar(255) COLLATE utf8_bin NOT NULL COMMENT 'data_id',
  `group_id` varchar(255) COLLATE utf8_bin NOT NULL COMMENT 'group_id',
  `datum_id` varchar(255) COLLATE utf8_bin NOT NULL COMMENT 'datum_id',
  `content` longtext COLLATE utf8_bin NOT NULL COMMENT '内容',
  `gmt_modified` datetime NOT NULL COMMENT '修改时间',
  `app_name` varchar(128) COLLATE utf8_bin DEFAULT NULL,
  `tenant_id` varchar(128) COLLATE utf8_bin DEFAULT '' COMMENT '租户字段',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_configinfoaggr_datagrouptenantdatum` (`data_id`,`group_id`,`tenant_id`,`datum_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='增加租户字段';

-- ----------------------------
-- Records of config_info_aggr
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for config_info_beta
-- ----------------------------
DROP TABLE IF EXISTS `config_info_beta`;
CREATE TABLE `config_info_beta` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `data_id` varchar(255) COLLATE utf8_bin NOT NULL COMMENT 'data_id',
  `group_id` varchar(128) COLLATE utf8_bin NOT NULL COMMENT 'group_id',
  `app_name` varchar(128) COLLATE utf8_bin DEFAULT NULL COMMENT 'app_name',
  `content` longtext COLLATE utf8_bin NOT NULL COMMENT 'content',
  `beta_ips` varchar(1024) COLLATE utf8_bin DEFAULT NULL COMMENT 'betaIps',
  `md5` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT 'md5',
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `src_user` text COLLATE utf8_bin COMMENT 'source user',
  `src_ip` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT 'source ip',
  `tenant_id` varchar(128) COLLATE utf8_bin DEFAULT '' COMMENT '租户字段',
  `encrypted_data_key` text COLLATE utf8_bin COMMENT '秘钥',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_configinfobeta_datagrouptenant` (`data_id`,`group_id`,`tenant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='config_info_beta';

-- ----------------------------
-- Records of config_info_beta
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for config_info_tag
-- ----------------------------
DROP TABLE IF EXISTS `config_info_tag`;
CREATE TABLE `config_info_tag` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `data_id` varchar(255) COLLATE utf8_bin NOT NULL COMMENT 'data_id',
  `group_id` varchar(128) COLLATE utf8_bin NOT NULL COMMENT 'group_id',
  `tenant_id` varchar(128) COLLATE utf8_bin DEFAULT '' COMMENT 'tenant_id',
  `tag_id` varchar(128) COLLATE utf8_bin NOT NULL COMMENT 'tag_id',
  `app_name` varchar(128) COLLATE utf8_bin DEFAULT NULL COMMENT 'app_name',
  `content` longtext COLLATE utf8_bin NOT NULL COMMENT 'content',
  `md5` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT 'md5',
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `src_user` text COLLATE utf8_bin COMMENT 'source user',
  `src_ip` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT 'source ip',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_configinfotag_datagrouptenanttag` (`data_id`,`group_id`,`tenant_id`,`tag_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='config_info_tag';

-- ----------------------------
-- Records of config_info_tag
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for config_tags_relation
-- ----------------------------
DROP TABLE IF EXISTS `config_tags_relation`;
CREATE TABLE `config_tags_relation` (
  `id` bigint(20) NOT NULL COMMENT 'id',
  `tag_name` varchar(128) COLLATE utf8_bin NOT NULL COMMENT 'tag_name',
  `tag_type` varchar(64) COLLATE utf8_bin DEFAULT NULL COMMENT 'tag_type',
  `data_id` varchar(255) COLLATE utf8_bin NOT NULL COMMENT 'data_id',
  `group_id` varchar(128) COLLATE utf8_bin NOT NULL COMMENT 'group_id',
  `tenant_id` varchar(128) COLLATE utf8_bin DEFAULT '' COMMENT 'tenant_id',
  `nid` bigint(20) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`nid`),
  UNIQUE KEY `uk_configtagrelation_configidtag` (`id`,`tag_name`,`tag_type`),
  KEY `idx_tenant_id` (`tenant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='config_tag_relation';

-- ----------------------------
-- Records of config_tags_relation
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for group_capacity
-- ----------------------------
DROP TABLE IF EXISTS `group_capacity`;
CREATE TABLE `group_capacity` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `group_id` varchar(128) COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT 'Group ID，空字符表示整个集群',
  `quota` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '配额，0表示使用默认值',
  `usage` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '使用量',
  `max_size` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '单个配置大小上限，单位为字节，0表示使用默认值',
  `max_aggr_count` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '聚合子配置最大个数，，0表示使用默认值',
  `max_aggr_size` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '单个聚合数据的子配置大小上限，单位为字节，0表示使用默认值',
  `max_history_count` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '最大变更历史数量',
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_group_id` (`group_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='集群、各Group容量信息表';

-- ----------------------------
-- Records of group_capacity
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for his_config_info
-- ----------------------------
DROP TABLE IF EXISTS `his_config_info`;
CREATE TABLE `his_config_info` (
  `id` bigint(64) unsigned NOT NULL,
  `nid` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `data_id` varchar(255) COLLATE utf8_bin NOT NULL,
  `group_id` varchar(128) COLLATE utf8_bin NOT NULL,
  `app_name` varchar(128) COLLATE utf8_bin DEFAULT NULL COMMENT 'app_name',
  `content` longtext COLLATE utf8_bin NOT NULL,
  `md5` varchar(32) COLLATE utf8_bin DEFAULT NULL,
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `gmt_modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `src_user` text COLLATE utf8_bin,
  `src_ip` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `op_type` char(10) COLLATE utf8_bin DEFAULT NULL,
  `tenant_id` varchar(128) COLLATE utf8_bin DEFAULT '' COMMENT '租户字段',
  `encrypted_data_key` text COLLATE utf8_bin COMMENT '秘钥',
  PRIMARY KEY (`nid`),
  KEY `idx_gmt_create` (`gmt_create`),
  KEY `idx_gmt_modified` (`gmt_modified`),
  KEY `idx_did` (`data_id`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='多租户改造';

-- ----------------------------
-- Records of his_config_info
-- ----------------------------
BEGIN;
INSERT INTO `his_config_info` VALUES (3, 1, 'application.yml', 'pangu', '', 'mybatis-plus:\n  mapper-locations: classpath:/mapper/*Mapper.xml\n  typeAliasesPackage: com.camellibby.order.entity\n  global-config:\n    db-config:\n      field-strategy: not-empty\n      id-type: auto\n      db-type: mysql\n  configuration:\n    map-underscore-to-camel-case: true\n    cache-enabled: true\n    auto-mapping-unknown-column-behavior: none\n    log-impl: org.apache.ibatis.logging.stdout.StdOutImpl\n    \nseata:\n#  enabled: true\n#  application-id: seata\n  tx-service-group: default_tx_group\n#  enable-auto-data-source-proxy: true\n#  data-source-proxy-mode: AT\n#  use-jdk-proxy: false\n  config:\n    type: nacos\n    consul:\n      server-addr: 127.0.0.1:8500\n    apollo:\n      apollo-meta: http://192.168.1.204:8801\n      app-id: seata-server\n      namespace: application\n      apollo-accesskey-secret: \"\"\n    etcd3:\n      server-addr: http://localhost:2379\n    nacos:\n      namespace: \n      server-addr: 127.0.0.1:8848\n      group: pangu\n      username: nacos\n      password: nacos\n      data-id: seata-client.properties\n    zk:\n      server-addr: 127.0.0.1:2181\n      session-timeout: 6000\n      connect-timeout: 2000\n      username: \"\"\n      password: \"\"\n    custom:\n      name: \"\"\n  registry:\n    type: nacos\n    file:\n      name: file.conf\n    consul:\n      server-addr: 127.0.0.1:8500\n      acl-token: \"\"\n    etcd3:\n      server-addr: http://localhost:2379\n    eureka:\n      weight: 1\n      service-url: http://localhost:8761/eureka\n    nacos:\n      application: seata\n      server-addr: 127.0.0.1:8848\n      group : pangu\n      namespace: \n      username: nacos\n      password: nacos\n    redis:\n      server-addr: localhost:6379\n      db: 0\n      password: \"\"\n      timeout: 0\n    sofa:\n      server-addr: 127.0.0.1:9603\n      region: DEFAULT_ZONE\n      datacenter: DefaultDataCenter\n      group: SEATA_GROUP\n      address-wait-time: 3000\n      application: default\n    zk:\n      server-addr: 127.0.0.1:2181\n      session-timeout: 6000\n      connect-timeout: 2000\n      username: \"\"\n      password: \"\"\n    custom:\n      name: \"\"\n#  log:\n#    exception-rate: 100', 'b22b91d1e8ca44eed1461d30de155a71', '2022-07-21 19:27:04', '2022-07-21 11:27:05', NULL, '127.0.0.1', 'U', '', NULL);
INSERT INTO `his_config_info` VALUES (3, 2, 'application.yml', 'pangu', '', 'mybatis-plus:\n  mapper-locations: classpath:/mapper/*Mapper.xml\n  typeAliasesPackage: com.camellibby.order.entity\n  global-config:\n    db-config:\n      field-strategy: not-empty\n      id-type: auto\n      db-type: mysql\n  configuration:\n    map-underscore-to-camel-case: true\n    cache-enabled: true\n    auto-mapping-unknown-column-behavior: none\n    log-impl: org.apache.ibatis.logging.stdout.StdOutImpl\n    \nseata:\n#  enabled: true\n#  application-id: seata\n  tx-service-group: default_tx_group\n#  enable-auto-data-source-proxy: true\n#  data-source-proxy-mode: AT\n#  use-jdk-proxy: false\n  config:\n    type: nacos\n    file:\n      name: file.conf\n    nacos:\n      namespace: \n      server-addr: 127.0.0.1:8848\n      group: pangu\n      username: nacos\n      password: nacos\n      data-id: seata-client.properties\n  registry:\n    type: nacos\n    file:\n      name: file.conf\n    nacos:\n      application: seata\n      server-addr: 127.0.0.1:8848\n      group : pangu\n      namespace: \n      username: nacos\n      password: nacos\n#  log:\n#    exception-rate: 100', '7f6c785788a0cd49eb24045e9e7f9772', '2022-07-27 21:27:43', '2022-07-27 13:27:43', NULL, '127.0.0.1', 'U', '', NULL);
INSERT INTO `his_config_info` VALUES (3, 3, 'application.yml', 'pangu', '', 'mybatis-plus:\n  mapper-locations: classpath:/mapper/*Mapper.xml\n  typeAliasesPackage: com.camellibby.order.entity\n  global-config:\n    db-config:\n      field-strategy: not-empty\n      id-type: auto\n      db-type: mysql\n  configuration:\n    map-underscore-to-camel-case: true\n    cache-enabled: true\n    auto-mapping-unknown-column-behavior: none\n    log-impl: org.apache.ibatis.logging.stdout.StdOutImpl\n    \n# seata:\n# #  enabled: true\n# #  application-id: seata\n#   tx-service-group: default_tx_group\n# #  enable-auto-data-source-proxy: true\n# #  data-source-proxy-mode: AT\n# #  use-jdk-proxy: false\n#   config:\n#     type: nacos\n#     file:\n#       name: file.conf\n#     nacos:\n#       namespace: \n#       server-addr: 127.0.0.1:8848\n#       group: pangu\n#       username: nacos\n#       password: nacos\n#       data-id: seata-client.properties\n#   registry:\n#     type: nacos\n#     file:\n#       name: file.conf\n#     nacos:\n#       application: seata\n#       server-addr: 127.0.0.1:8848\n#       group : pangu\n#       namespace: \n#       username: nacos\n#       password: nacos\n# #  log:\n# #    exception-rate: 100', '91999fb470db0d974285ab14ed30e3c0', '2022-07-27 23:22:37', '2022-07-27 15:22:38', NULL, '127.0.0.1', 'U', '', NULL);
INSERT INTO `his_config_info` VALUES (3, 4, 'application.yml', 'pangu', '', 'mybatis-plus:\n  mapper-locations: classpath:/mapper/*Mapper.xml\n  typeAliasesPackage: com.camellibby.order.entity\n  global-config:\n    db-config:\n      field-strategy: not-empty\n      id-type: auto\n      db-type: mysql\n  configuration:\n    map-underscore-to-camel-case: true\n    cache-enabled: true\n    auto-mapping-unknown-column-behavior: none\n    log-impl: org.apache.ibatis.logging.stdout.StdOutImpl\n    \nseata:\n#  enabled: true\n#  application-id: seata\n  tx-service-group: default_tx_group\n#  enable-auto-data-source-proxy: true\n#  data-source-proxy-mode: AT\n#  use-jdk-proxy: false\n  # config:\n  #   type: nacos\n  #   file:\n  #     name: file.conf\n  #   nacos:\n  #     namespace: \n  #     server-addr: 127.0.0.1:8848\n  #     group: pangu\n  #     username: nacos\n  #     password: nacos\n  #     data-id: seata-client.properties\n  # registry:\n  #   type: nacos\n  #   file:\n  #     name: file.conf\n  #   nacos:\n  #     application: seata\n  #     server-addr: 127.0.0.1:8848\n  #     group : pangu\n  #     namespace: \n  #     username: nacos\n  #     password: nacos\n#  log:\n#    exception-rate: 100', '860f40581fd2d5d5dff4f4b56d7e4858', '2022-07-27 23:27:28', '2022-07-27 15:27:29', NULL, '127.0.0.1', 'U', '', NULL);
INSERT INTO `his_config_info` VALUES (3, 5, 'application.yml', 'pangu', '', 'mybatis-plus:\n  mapper-locations: classpath:/mapper/*Mapper.xml\n  typeAliasesPackage: com.camellibby.order.entity\n  global-config:\n    db-config:\n      field-strategy: not-empty\n      id-type: auto\n      db-type: mysql\n  configuration:\n    map-underscore-to-camel-case: true\n    cache-enabled: true\n    auto-mapping-unknown-column-behavior: none\n    log-impl: org.apache.ibatis.logging.stdout.StdOutImpl\n    \n# seata:\n# #  enabled: true\n# #  application-id: seata\n#   tx-service-group: default_tx_group\n# #  enable-auto-data-source-proxy: true\n# #  data-source-proxy-mode: AT\n# #  use-jdk-proxy: false\n#   config:\n#     type: nacos\n#     file:\n#       name: file.conf\n#     nacos:\n#       namespace: \n#       server-addr: 127.0.0.1:8848\n#       group: pangu\n#       username: nacos\n#       password: nacos\n#       data-id: seata-client.properties\n#   registry:\n#     type: nacos\n#     file:\n#       name: file.conf\n#     nacos:\n#       application: seata\n#       server-addr: 127.0.0.1:8848\n#       group : pangu\n#       namespace: \n#       username: nacos\n#       password: nacos\n# #  log:\n# #    exception-rate: 100', '91999fb470db0d974285ab14ed30e3c0', '2022-07-27 23:27:48', '2022-07-27 15:27:49', NULL, '127.0.0.1', 'U', '', NULL);
INSERT INTO `his_config_info` VALUES (3, 6, 'application.yml', 'pangu', '', 'mybatis-plus:\n  mapper-locations: classpath:/mapper/*Mapper.xml\n  typeAliasesPackage: com.camellibby.order.entity\n  global-config:\n    db-config:\n      field-strategy: not-empty\n      id-type: auto\n      db-type: mysql\n  configuration:\n    map-underscore-to-camel-case: true\n    cache-enabled: true\n    auto-mapping-unknown-column-behavior: none\n    log-impl: org.apache.ibatis.logging.stdout.StdOutImpl\n    \nseata:\n#  enabled: true\n#  application-id: seata\n  tx-service-group: default_tx_group\n#  enable-auto-data-source-proxy: true\n#  data-source-proxy-mode: AT\n#  use-jdk-proxy: false\n  config:\n    type: nacos\n    file:\n      name: file.conf\n    nacos:\n      namespace: \n      server-addr: 127.0.0.1:8848\n      group: pangu\n      username: nacos\n      password: nacos\n      data-id: seata-client.properties\n  registry:\n    type: nacos\n    file:\n      name: file.conf\n    nacos:\n      application: seata\n      server-addr: 127.0.0.1:8848\n      group : pangu\n      namespace: \n      username: nacos\n      password: nacos\n#  log:\n#    exception-rate: 100', '7f6c785788a0cd49eb24045e9e7f9772', '2022-07-27 23:29:20', '2022-07-27 15:29:21', NULL, '127.0.0.1', 'U', '', NULL);
INSERT INTO `his_config_info` VALUES (1, 7, 'seata-client.properties', 'pangu', '', 'transport.type=TCP\ntransport.server=NIO\ntransport.heartbeat=true\ntransport.enableClientBatchSendRequest=false\ntransport.threadFactory.bossThreadPrefix=NettyBoss\ntransport.threadFactory.workerThreadPrefix=NettyServerNIOWorker\ntransport.threadFactory.serverExecutorThreadPrefix=NettyServerBizHandler\ntransport.threadFactory.shareBossWorker=false\ntransport.threadFactory.clientSelectorThreadPrefix=NettyClientSelector\ntransport.threadFactory.clientSelectorThreadSize=1\ntransport.threadFactory.clientWorkerThreadPrefix=NettyClientWorkerThread\ntransport.threadFactory.bossThreadSize=1\ntransport.threadFactory.workerThreadSize=default\ntransport.shutdown.wait=3\ntransport.serialization=seata\ntransport.compressor=none\nservice.vgroupMapping.default_tx_group=default\nservice.default.grouplist=127.0.0.1:8091\nservice.enableDegrade=false\nservice.disableGlobalTransaction=false\nclient.rm.asyncCommitBufferLimit=10000\nclient.rm.lock.retryInterval=10\nclient.rm.lock.retryTimes=30\nclient.rm.lock.retryPolicyBranchRollbackOnConflict=true\nclient.rm.reportRetryCount=5\nclient.rm.tableMetaCheckEnable=false\nclient.rm.tableMetaCheckerInterval=60000\nclient.rm.sqlParserType=druid\nclient.rm.reportSuccessEnable=false\nclient.rm.sagaBranchRegisterEnable=false\nclient.tm.commitRetryCount=5\nclient.tm.rollbackRetryCount=5\nclient.tm.defaultGlobalTransactionTimeout=60000\nclient.tm.degradeCheck=false\nclient.tm.degradeCheckAllowTimes=10\nclient.tm.degradeCheckPeriod=2000\nclient.undo.dataValidation=true\nclient.undo.logSerialization=jackson\nclient.undo.onlyCareUpdateColumns=true\nclient.undo.logTable=undo_log\nclient.undo.compress.enable=true\nclient.undo.compress.type=zip\nclient.undo.compress.threshold=64k\nlog.exceptionRate=100', '81af8ae34371ce049901a416064071fd', '2022-07-31 12:56:25', '2022-07-31 04:56:26', NULL, '127.0.0.1', 'D', '', NULL);
INSERT INTO `his_config_info` VALUES (2, 8, 'seata-server.properties', 'pangu', '', 'store.mode=db\n\nstore.file.dir=sessionStore\nstore.file.maxBranchSessionSize=16384\nstore.file.maxGlobalSessionSize=512\nstore.file.fileWriteBufferCacheSize=16384\nstore.file.sessionReloadReadSize=100\nstore.file.flushDiskMode=async\n\nstore.db.datasource=druid\nstore.db.dbType=mysql\nstore.db.driverClassName=com.mysql.cj.jdbc.Driver\n# if using mysql to store the data, recommend add rewriteBatchedStatements=true in jdbc connection param\nstore.db.url=jdbc:mysql://127.0.0.1:3306/seata?rewriteBatchedStatements=true\nstore.db.user=root\nstore.db.password=123456\nstore.db.minConn=5\nstore.db.maxConn=100\nstore.db.globalTable=global_table\nstore.db.branchTable=branch_table\nstore.db.lockTable=lock_table\nstore.db.queryLimit=100\nstore.db.maxWait=5000\n\nseata.store.redis.mode=single\nseata.store.redis.single.host=127.0.0.1\nseata.store.redis.single.port=6379\nseata.store.redis.sentinel.masterName=\"\"\nseata.store.redis.sentinel.sentinelHosts=\"\"\nseata.store.redis.password=\"\"\nseata.store.redis.database=0\nseata.store.redis.minConn=1\nseata.store.redis.maxConn=10\nseata.store.redis.maxTotal=100\nseata.store.redis.queryLimit=100\n\n\nserver.recovery.committingRetryPeriod=1000\nserver.recovery.asynCommittingRetryPeriod=1000\nserver.recovery.rollbackingRetryPeriod=1000\nserver.recovery.timeoutRetryPeriod=1000\nserver.maxCommitRetryTimeout=-1\nserver.maxRollbackRetryTimeout=-1\nserver.rollbackRetryTimeoutUnlockEnable=false\n\n\nmetrics.enabled=false\nmetrics.registryType=compact\nmetrics.exporterList=prometheus\nmetrics.exporterPrometheusPort=9898\n', '1335970d637e6acf3ffa6261d07bf6e7', '2022-07-31 12:56:29', '2022-07-31 04:56:29', NULL, '127.0.0.1', 'D', '', NULL);
INSERT INTO `his_config_info` VALUES (3, 9, 'application.yml', 'pangu', '', 'mybatis-plus:\n  mapper-locations: classpath:/mapper/*Mapper.xml\n  typeAliasesPackage: com.camellibby.order.entity\n  global-config:\n    db-config:\n      field-strategy: not-empty\n      id-type: auto\n      db-type: mysql\n  configuration:\n    map-underscore-to-camel-case: true\n    cache-enabled: true\n    auto-mapping-unknown-column-behavior: none\n    log-impl: org.apache.ibatis.logging.stdout.StdOutImpl\n    \nseata:\n#  enabled: true\n#  application-id: seata\n  tx-service-group: default_tx_group\n#  enable-auto-data-source-proxy: true\n#  data-source-proxy-mode: AT\n#  use-jdk-proxy: false\n  config:\n    type: nacos\n    file:\n      name: file.conf\n    nacos:\n      namespace: \n      server-addr: 127.0.0.1:8848\n      group: pangu\n      username: nacos\n      password: nacos\n      data-id: seata-client.properties\n  registry:\n    type: nacos\n    file:\n      name: file.conf\n    nacos:\n      application: seata\n      server-addr: 127.0.0.1:8848\n      group : pangu\n      namespace: \n      username: nacos\n      password: nacos\n#  log:\n#    exception-rate: 100', '7f6c785788a0cd49eb24045e9e7f9772', '2022-07-31 12:56:43', '2022-07-31 04:56:43', NULL, '127.0.0.1', 'U', '', NULL);
INSERT INTO `his_config_info` VALUES (3, 10, 'application.yml', 'pangu', '', 'mybatis-plus:\n  mapper-locations: classpath:/mapper/*Mapper.xml\n  typeAliasesPackage: com.camellibby.order.entity\n  global-config:\n    db-config:\n      field-strategy: not-empty\n      id-type: auto\n      db-type: mysql\n  configuration:\n    map-underscore-to-camel-case: true\n    cache-enabled: true\n    auto-mapping-unknown-column-behavior: none\n    log-impl: org.apache.ibatis.logging.stdout.StdOutImpl\n    ', '0b7a80aada24a1b90248a808d80eed81', '2022-08-01 21:36:12', '2022-08-01 13:36:12', NULL, '127.0.0.1', 'U', '', NULL);
INSERT INTO `his_config_info` VALUES (3, 11, 'application.yml', 'pangu', '', 'mybatis-plus:\n  mapper-locations: classpath:/mapper/*Mapper.xml\n  typeAliasesPackage: com.camellibby.order.entity\n  global-config:\n    db-config:\n      field-strategy: not-empty\n      id-type: auto\n      db-type: mysql\n  configuration:\n    map-underscore-to-camel-case: true\n    cache-enabled: true\n    auto-mapping-unknown-column-behavior: none\n    log-impl: org.apache.ibatis.logging.stdout.StdOutImpl\n    ', '0b7a80aada24a1b90248a808d80eed81', '2022-08-01 23:40:23', '2022-08-01 15:40:24', NULL, '127.0.0.1', 'U', '', '');
INSERT INTO `his_config_info` VALUES (4, 12, 'order.yml', 'pangu', '', 'spring:\r\n  datasource:\r\n    type: com.alibaba.druid.pool.DruidDataSource\r\n    driver-class-name: com.mysql.cj.jdbc.Driver\r\n    url: jdbc:mysql://127.0.0.1:3306/db_order?useSSL=false&serverTimezone=UTC\r\n    username: root\r\n    password: 123456', 'a624bc26e16dc6ab08621e6e10ee2812', '2022-08-01 23:40:41', '2022-08-01 15:40:42', NULL, '127.0.0.1', 'U', '', '');
INSERT INTO `his_config_info` VALUES (0, 13, 'seata-client.yml', 'pangu', '', 'service:\n    vgroupMapping:\n        default_tx_group: default\n    default:\n        grouplist: 127.0.0.1:8091\n    enableDegrade: false\n    disableGlobalTransaction: false', '7e2cf02ceee58db99d7e768a8f8ff233', '2022-08-02 14:59:13', '2022-08-02 06:59:14', NULL, '127.0.0.1', 'I', '', NULL);
INSERT INTO `his_config_info` VALUES (11, 14, 'seata-client.yml', 'pangu', '', 'service:\n    vgroupMapping:\n        default_tx_group: default\n    default:\n        grouplist: 127.0.0.1:8091\n    enableDegrade: false\n    disableGlobalTransaction: false', '7e2cf02ceee58db99d7e768a8f8ff233', '2022-08-02 16:19:31', '2022-08-02 08:19:31', NULL, '127.0.0.1', 'U', '', NULL);
INSERT INTO `his_config_info` VALUES (11, 15, 'seata-client.yml', 'pangu', '', 'client:\n  rm:\n    asyncCommitBufferLimit: 10000\n    lock:\n      retryInterval: 10\n      retryPolicyBranchRollbackOnConflict: true\n      retryTimes: 30\n    reportRetryCount: 5\n    reportSuccessEnable: false\n    sagaBranchRegisterEnable: false\n    sqlParserType: druid\n    tableMetaCheckEnable: false\n    tableMetaCheckerInterval: 60000\n  tm:\n    commitRetryCount: 5\n    defaultGlobalTransactionTimeout: 60000\n    degradeCheck: false\n    degradeCheckAllowTimes: 10\n    degradeCheckPeriod: 2000\n    rollbackRetryCount: 5\n  undo:\n    compress:\n      enable: true\n      threshold: 64k\n      type: zip\n    dataValidation: true\n    logSerialization: jackson\n    logTable: undo_log\n    onlyCareUpdateColumns: true\nlog:\n  exceptionRate: 100\nservice:\n  default:\n    grouplist: 127.0.0.1:8091\n  disableGlobalTransaction: false\n  enableDegrade: false\n  vgroupMapping:\n    default_tx_group: default\ntransport:\n  compressor: none\n  enableClientBatchSendRequest: false\n  heartbeat: true\n  serialization: seata\n  server: NIO\n  shutdown:\n    wait: 3\n  threadFactory:\n    bossThreadPrefix: NettyBoss\n    bossThreadSize: 1\n    clientSelectorThreadPrefix: NettyClientSelector\n    clientSelectorThreadSize: 1\n    clientWorkerThreadPrefix: NettyClientWorkerThread\n    serverExecutorThreadPrefix: NettyServerBizHandler\n    shareBossWorker: false\n    workerThreadPrefix: NettyServerNIOWorker\n    workerThreadSize: default\n  type: TCP\n', '6e857a403f6853abe1e51cc2c043ca5e', '2022-08-02 16:20:47', '2022-08-02 08:20:48', NULL, '127.0.0.1', 'D', '', NULL);
INSERT INTO `his_config_info` VALUES (0, 16, 'seata-application.yml', 'pangu', '', 'seata:\n  enabled: true\n  application-id: seata-server\n  tx-service-group: default_tx_group\n  enable-auto-data-source-proxy: true\n  data-source-proxy-mode: AT\n  log:\n    exception-rate: 100\n  config:\n    type: nacos\n    nacos:\n      namespace:\n      server-addr: 127.0.0.1:8861\n      group: pangu\n      username: nacos\n      password: nacos\n      data-id: seata-client.properties', '32f92789fadc7f0ea3c7cbd697a90d8a', '2022-08-02 16:49:16', '2022-08-02 08:49:17', NULL, '127.0.0.1', 'I', '', NULL);
INSERT INTO `his_config_info` VALUES (3, 17, 'seata-server.properties', 'pangu', '', 'store.mode=db\n\nstore.file.dir=sessionStore\nstore.file.maxBranchSessionSize=16384\nstore.file.maxGlobalSessionSize=512\nstore.file.fileWriteBufferCacheSize=16384\nstore.file.sessionReloadReadSize=100\nstore.file.flushDiskMode=async\n\nstore.db.datasource=druid\nstore.db.dbType=mysql\nstore.db.driverClassName=com.mysql.cj.jdbc.Driver\n# if using mysql to store the data, recommend add rewriteBatchedStatements=true in jdbc connection param\nstore.db.url=jdbc:mysql://127.0.0.1:3306/seata?rewriteBatchedStatements=true\nstore.db.user=root\nstore.db.password=123456\nstore.db.minConn=5\nstore.db.maxConn=100\nstore.db.globalTable=global_table\nstore.db.branchTable=branch_table\nstore.db.lockTable=lock_table\nstore.db.queryLimit=100\nstore.db.maxWait=5000\n\nseata.store.redis.mode=single\nseata.store.redis.single.host=127.0.0.1\nseata.store.redis.single.port=6379\nseata.store.redis.sentinel.masterName=\"\"\nseata.store.redis.sentinel.sentinelHosts=\"\"\nseata.store.redis.password=\"\"\nseata.store.redis.database=0\nseata.store.redis.minConn=1\nseata.store.redis.maxConn=10\nseata.store.redis.maxTotal=100\nseata.store.redis.queryLimit=100\n\n\nserver.recovery.committingRetryPeriod=1000\nserver.recovery.asynCommittingRetryPeriod=1000\nserver.recovery.rollbackingRetryPeriod=1000\nserver.recovery.timeoutRetryPeriod=1000\nserver.maxCommitRetryTimeout=-1\nserver.maxRollbackRetryTimeout=-1\nserver.rollbackRetryTimeoutUnlockEnable=false\n\n\nmetrics.enabled=false\nmetrics.registryType=compact\nmetrics.exporterList=prometheus\nmetrics.exporterPrometheusPort=9898\n', '1335970d637e6acf3ffa6261d07bf6e7', '2022-08-02 17:01:42', '2022-08-02 09:01:43', NULL, '127.0.0.1', 'U', '', NULL);
INSERT INTO `his_config_info` VALUES (3, 18, 'seata-server.properties', 'pangu', '', 'store.mode=db\n\nstore.file.dir=sessionStore\nstore.file.maxBranchSessionSize=16384\nstore.file.maxGlobalSessionSize=512\nstore.file.fileWriteBufferCacheSize=16384\nstore.file.sessionReloadReadSize=100\nstore.file.flushDiskMode=async\n\nstore.db.datasource=druid\nstore.db.dbType=mysql\nstore.db.driverClassName=com.mysql.cj.jdbc.Driver\n# if using mysql to store the data, recommend add rewriteBatchedStatements=true in jdbc connection param\nstore.db.url=jdbc:mysql://127.0.0.1:3306/seata?rewriteBatchedStatements=true\nstore.db.user=root\nstore.db.password=123456\nstore.db.minConn=5\nstore.db.maxConn=100\nstore.db.globalTable=global_table\nstore.db.branchTable=branch_table\nstore.db.lockTable=lock_table\nstore.db.queryLimit=100\nstore.db.maxWait=5000\n\nseata.store.redis.mode=single\nseata.store.redis.single.host=127.0.0.1\nseata.store.redis.single.port=6379\nseata.store.redis.sentinel.masterName=\"\"\nseata.store.redis.sentinel.sentinelHosts=\"\"\nseata.store.redis.password=\"\"\nseata.store.redis.database=0\nseata.store.redis.minConn=1\nseata.store.redis.maxConn=10\nseata.store.redis.maxTotal=100\nseata.store.redis.queryLimit=100\n\nserver.recovery.committingRetryPeriod=1000\nserver.recovery.asynCommittingRetryPeriod=1000\nserver.recovery.rollbackingRetryPeriod=1000\nserver.recovery.timeoutRetryPeriod=1000\nserver.maxCommitRetryTimeout=-1\nserver.maxRollbackRetryTimeout=-1\nserver.rollbackRetryTimeoutUnlockEnable=false\n\nmetrics.enabled=false\nmetrics.registryType=compact\nmetrics.exporterList=prometheus\nmetrics.exporterPrometheusPort=9898\n', 'a7a2b97e7df429bab2f3c5bd3ffc342e', '2022-08-02 17:19:23', '2022-08-02 09:19:24', NULL, '127.0.0.1', 'U', '', NULL);
INSERT INTO `his_config_info` VALUES (3, 19, 'seata-server.properties', 'pangu', '', 'store.mode=db\n\nstore.db.datasource=druid\nstore.db.dbType=mysql\nstore.db.driverClassName=com.mysql.cj.jdbc.Driver\n# if using mysql to store the data, recommend add rewriteBatchedStatements=true in jdbc connection param\nstore.db.url=jdbc:mysql://127.0.0.1:3306/seata?rewriteBatchedStatements=true\nstore.db.user=root\nstore.db.password=123456\nstore.db.minConn=5\nstore.db.maxConn=100\nstore.db.globalTable=global_table\nstore.db.branchTable=branch_table\nstore.db.lockTable=lock_table\nstore.db.queryLimit=100\nstore.db.maxWait=5000\n\nserver.recovery.committingRetryPeriod=1000\nserver.recovery.asynCommittingRetryPeriod=1000\nserver.recovery.rollbackingRetryPeriod=1000\nserver.recovery.timeoutRetryPeriod=1000\nserver.maxCommitRetryTimeout=-1\nserver.maxRollbackRetryTimeout=-1\nserver.rollbackRetryTimeoutUnlockEnable=false\n\nmetrics.enabled=false\nmetrics.registryType=compact\nmetrics.exporterList=prometheus\nmetrics.exporterPrometheusPort=9898\n', 'dcd887d67fb261fb89ec8083bd888150', '2022-08-02 17:21:47', '2022-08-02 09:21:48', NULL, '127.0.0.1', 'U', '', NULL);
INSERT INTO `his_config_info` VALUES (3, 20, 'seata-server.properties', 'pangu', '', 'store.mode=db\n\nstore.db.datasource=druid\nstore.db.dbType=mysql\nstore.db.driverClassName=com.mysql.cj.jdbc.Driver\n# if using mysql to store the data, recommend add rewriteBatchedStatements=true in jdbc connection param\nstore.db.url=jdbc:mysql://127.0.0.1:3306/seata?rewriteBatchedStatements=true\nstore.db.user=root\nstore.db.password=123456\nstore.db.minConn=5\nstore.db.maxConn=100\nstore.db.globalTable=global_table\nstore.db.branchTable=branch_table\nstore.db.distributedLockTable=lock_table\nstore.db.queryLimit=100\nstore.db.maxWait=5000\n\nserver.recovery.committingRetryPeriod=1000\nserver.recovery.asynCommittingRetryPeriod=1000\nserver.recovery.rollbackingRetryPeriod=1000\nserver.recovery.timeoutRetryPeriod=1000\nserver.maxCommitRetryTimeout=-1\nserver.maxRollbackRetryTimeout=-1\nserver.rollbackRetryTimeoutUnlockEnable=false\n\nmetrics.enabled=false\nmetrics.registryType=compact\nmetrics.exporterList=prometheus\nmetrics.exporterPrometheusPort=9898\n', 'bbca9f80178216fd3c8ac1aed7cd0dcf', '2022-08-02 17:30:32', '2022-08-02 09:30:33', NULL, '127.0.0.1', 'U', '', NULL);
INSERT INTO `his_config_info` VALUES (2, 21, 'seata-application.yml', 'pangu', '', 'seata:\n  enabled: true\n  application-id: seata-server\n  tx-service-group: default_tx_group\n  enable-auto-data-source-proxy: true\n  data-source-proxy-mode: AT\n  log:\n    exception-rate: 100\n  config:\n    type: nacos\n    nacos:\n      namespace:\n      server-addr: 127.0.0.1:8861\n      group: pangu\n      username: nacos\n      password: nacos\n      data-id: seata-client.properties', '32f92789fadc7f0ea3c7cbd697a90d8a', '2022-08-02 17:48:25', '2022-08-02 09:48:26', NULL, '127.0.0.1', 'U', '', NULL);
COMMIT;

-- ----------------------------
-- Table structure for permissions
-- ----------------------------
DROP TABLE IF EXISTS `permissions`;
CREATE TABLE `permissions` (
  `role` varchar(50) NOT NULL,
  `resource` varchar(255) NOT NULL,
  `action` varchar(8) NOT NULL,
  UNIQUE KEY `uk_role_permission` (`role`,`resource`,`action`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of permissions
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for roles
-- ----------------------------
DROP TABLE IF EXISTS `roles`;
CREATE TABLE `roles` (
  `username` varchar(50) NOT NULL,
  `role` varchar(50) NOT NULL,
  UNIQUE KEY `idx_user_role` (`username`,`role`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of roles
-- ----------------------------
BEGIN;
INSERT INTO `roles` VALUES ('nacos', 'ROLE_ADMIN');
COMMIT;

-- ----------------------------
-- Table structure for tenant_capacity
-- ----------------------------
DROP TABLE IF EXISTS `tenant_capacity`;
CREATE TABLE `tenant_capacity` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `tenant_id` varchar(128) COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT 'Tenant ID',
  `quota` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '配额，0表示使用默认值',
  `usage` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '使用量',
  `max_size` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '单个配置大小上限，单位为字节，0表示使用默认值',
  `max_aggr_count` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '聚合子配置最大个数',
  `max_aggr_size` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '单个聚合数据的子配置大小上限，单位为字节，0表示使用默认值',
  `max_history_count` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '最大变更历史数量',
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_tenant_id` (`tenant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='租户容量信息表';

-- ----------------------------
-- Records of tenant_capacity
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for tenant_info
-- ----------------------------
DROP TABLE IF EXISTS `tenant_info`;
CREATE TABLE `tenant_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `kp` varchar(128) COLLATE utf8_bin NOT NULL COMMENT 'kp',
  `tenant_id` varchar(128) COLLATE utf8_bin DEFAULT '' COMMENT 'tenant_id',
  `tenant_name` varchar(128) COLLATE utf8_bin DEFAULT '' COMMENT 'tenant_name',
  `tenant_desc` varchar(256) COLLATE utf8_bin DEFAULT NULL COMMENT 'tenant_desc',
  `create_source` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT 'create_source',
  `gmt_create` bigint(20) NOT NULL COMMENT '创建时间',
  `gmt_modified` bigint(20) NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_tenant_info_kptenantid` (`kp`,`tenant_id`),
  KEY `idx_tenant_id` (`tenant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='tenant_info';

-- ----------------------------
-- Records of tenant_info
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for users
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
  `username` varchar(50) NOT NULL,
  `password` varchar(500) NOT NULL,
  `enabled` tinyint(1) NOT NULL,
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of users
-- ----------------------------
BEGIN;
INSERT INTO `users` VALUES ('nacos', '$2a$10$EuWPZHzz32dJN7jexM34MOeYirDdFAZm2kuWj7VEOJhhZkDrxfvUu', 1);
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
