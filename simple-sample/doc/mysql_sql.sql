CREATE TABLE `account` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` bigint(20) NOT NULL COMMENT '用户ID',
  `amount` DECIMAL(11,2) NOT NULL COMMENT '账户余额',
  `status` tinyint(3) DEFAULT '1' COMMENT '状态, 1:启用,0:删除',
  `createTime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updateTime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

CREATE TABLE `account_amount_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` bigint(20) NOT NULL COMMENT '用户ID',
  `amount` DECIMAL(11,2) NOT NULL COMMENT '消费金额',
  `status` tinyint(3) DEFAULT '1' COMMENT '状态, 1:启用,0:删除',
  `createTime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

CREATE TABLE `city` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(25) NOT NULL COMMENT '城市名字',
  `provinceId` bigint(20) NOT NULL COMMENT '省份ID',
  `description` varchar(250) DEFAULT NULL COMMENT '描述',
  `status` tinyint(3) DEFAULT '1' COMMENT '状态, 1:启用,0:删除',
  `createTime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updateTime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

CREATE TABLE `province` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(25) NOT NULL COMMENT '省份名字',
  `status` tinyint(3) DEFAULT '1' COMMENT '状态, 1:启用,0:删除',
  `createTime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updateTime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='省份';

CREATE TABLE `user` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(64) DEFAULT NULL,
  `age` int(11) DEFAULT NULL,
  `password` varchar(64) NOT NULL COMMENT '密码',
  `card` varchar(64) DEFAULT NULL COMMENT '身份证',
  `phone` varchar(64) DEFAULT NULL COMMENT '手机',
  `status` tinyint(3) DEFAULT '1' COMMENT '状态, 1:启用,0:删除',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`) USING BTREE,
  KEY `index_user` (`name`,`password`,`card`,`phone`,`age`) USING BTREE,
  FULLTEXT KEY `ft_name` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='用户表';

CREATE TABLE `kafka_message` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `topic` varchar(225) NOT NULL COMMENT '主题',
  `messageBody` varchar(225) NOT NULL COMMENT '消息主体',
  `messageByte` tinyblob NOT NULL COMMENT '消息byte',
  `version` bigint(20) NOT NULL DEFAULT '0' COMMENT '版本号',
  `createTime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updateTime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `status` tinyint(3) DEFAULT '1' COMMENT '状态, 1:启用,0:删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='kafka消息';