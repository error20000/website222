/*
Navicat MySQL Data Transfer

Source Server         : lj
Source Server Version : 50515
Source Host           : localhost:3306
Source Database       : website_all

Target Server Type    : MYSQL
Target Server Version : 50515
File Encoding         : 65001

Date: 2018-08-28 15:37:48
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `s_active`
-- ----------------------------
DROP TABLE IF EXISTS `s_active`;
CREATE TABLE `s_active` (
  `pid` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `type` int(11) DEFAULT '0' COMMENT '分类',
  `config` int(11) DEFAULT '0' COMMENT '配置',
  `date` varchar(20) DEFAULT '' COMMENT '日期  yyyy-MM-dd HH:mm:ss',
  `ip` varchar(255) DEFAULT '' COMMENT 'IP',
  `other` varchar(255) DEFAULT '' COMMENT '参与人',
  `info` varchar(255) DEFAULT '' COMMENT '其他信息',
  PRIMARY KEY (`pid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='活动记录表';

-- ----------------------------
-- Records of s_active
-- ----------------------------

-- ----------------------------
-- Table structure for `s_active_attr`
-- ----------------------------
DROP TABLE IF EXISTS `s_active_attr`;
CREATE TABLE `s_active_attr` (
  `pid` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `type` int(11) DEFAULT '0' COMMENT '分类',
  `active` int(11) DEFAULT '0' COMMENT '活动',
  `name` varchar(100) DEFAULT '' COMMENT '名称',
  `code` varchar(100) DEFAULT '' COMMENT '字段',
  `value` varchar(255) DEFAULT '' COMMENT '值',
  `desc` varchar(255) DEFAULT '' COMMENT '描述',
  PRIMARY KEY (`pid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='活动属性表';

-- ----------------------------
-- Records of s_active_attr
-- ----------------------------

-- ----------------------------
-- Table structure for `s_active_config`
-- ----------------------------
DROP TABLE IF EXISTS `s_active_config`;
CREATE TABLE `s_active_config` (
  `pid` int(11) NOT NULL COMMENT '主键',
  `type` int(11) DEFAULT '0' COMMENT '分类',
  `name` varchar(255) DEFAULT '' COMMENT '名称',
  `sort` int(10) DEFAULT '999' COMMENT '排序',
  `desc` varchar(255) DEFAULT '' COMMENT '描述',
  PRIMARY KEY (`pid`),
  KEY `ip` (`name`(191)) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of s_active_config
-- ----------------------------

-- ----------------------------
-- Table structure for `s_active_config_attr`
-- ----------------------------
DROP TABLE IF EXISTS `s_active_config_attr`;
CREATE TABLE `s_active_config_attr` (
  `pid` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `type` int(11) DEFAULT '0' COMMENT '分类',
  `config` int(11) DEFAULT '0' COMMENT '配置',
  `name` varchar(100) DEFAULT '' COMMENT '名称',
  `code` varchar(100) DEFAULT '' COMMENT '字段',
  `value` varchar(255) DEFAULT '' COMMENT '值',
  `desc` varchar(255) DEFAULT '' COMMENT '描述',
  PRIMARY KEY (`pid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='活动配置的属性表';

-- ----------------------------
-- Records of s_active_config_attr
-- ----------------------------

-- ----------------------------
-- Table structure for `s_active_config_attr_tp`
-- ----------------------------
DROP TABLE IF EXISTS `s_active_config_attr_tp`;
CREATE TABLE `s_active_config_attr_tp` (
  `pid` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `type` int(11) DEFAULT '0' COMMENT '分类',
  `input` tinyint(4) DEFAULT '0' COMMENT '类型  0：text，1：textarea，2：file，3：select，4：radio，5：checkbox',
  `name` varchar(100) DEFAULT '' COMMENT '名称',
  `code` varchar(100) DEFAULT '' COMMENT '字段',
  `def` varchar(255) DEFAULT '' COMMENT '默认值',
  `isnull` tinyint(4) DEFAULT '1' COMMENT '是否必填  0：是，1：否',
  `isshow` tinyint(4) DEFAULT '1' COMMENT '是否展示  0：否，1：是',
  `desc` varchar(255) DEFAULT '' COMMENT '描述',
  PRIMARY KEY (`pid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='活动配置的属性模版表';

-- ----------------------------
-- Records of s_active_config_attr_tp
-- ----------------------------

-- ----------------------------
-- Table structure for `s_active_config_attr_tp_sel`
-- ----------------------------
DROP TABLE IF EXISTS `s_active_config_attr_tp_sel`;
CREATE TABLE `s_active_config_attr_tp_sel` (
  `pid` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `type` int(11) DEFAULT '0' COMMENT '分类',
  `temp` int(11) DEFAULT '0' COMMENT '模版',
  `name` varchar(100) DEFAULT '' COMMENT '名称',
  `code` varchar(100) DEFAULT '' COMMENT '字段',
  `value` varchar(255) DEFAULT '' COMMENT '值',
  PRIMARY KEY (`pid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='活动配置的属性的选项表';

-- ----------------------------
-- Records of s_active_config_attr_tp_sel
-- ----------------------------

-- ----------------------------
-- Table structure for `s_active_type`
-- ----------------------------
DROP TABLE IF EXISTS `s_active_type`;
CREATE TABLE `s_active_type` (
  `pid` int(10) NOT NULL COMMENT '主键',
  `name` varchar(255) DEFAULT '' COMMENT '名称',
  `en` varchar(255) DEFAULT '' COMMENT '标识',
  `mode` tinyint(4) DEFAULT '0' COMMENT '标签  0：一般配置，1：抽奖配置，2：兑换配置',
  `start` varchar(20) DEFAULT '' COMMENT '开始时间  yyyy-MM-dd HH:mm:ss',
  `end` varchar(20) DEFAULT '' COMMENT '结束时间  yyyy-MM-dd HH:mm:ss',
  `status` tinyint(4) DEFAULT '0' COMMENT '状态   0 -- 关闭  1 --开启',
  `count` int(11) DEFAULT '0' COMMENT '参加次数   小于0 --- 无限次',
  `auth` tinyint(4) DEFAULT '0' COMMENT '是否审核   0 -- 否  1 --是',
  `sign` tinyint(4) DEFAULT '0' COMMENT '是否验证  0 -- 否  1 --是',
  `share` tinyint(4) DEFAULT '0' COMMENT '是否分享  0 -- 否  1 --是',
  `share_mode` tinyint(4) DEFAULT '0' COMMENT '分享模式限制',
  `share_count` int(11) DEFAULT '0' COMMENT '每次分享获得参与次数',
  `share_limit` int(11) DEFAULT '0' COMMENT '分享次数   小于0 --- 无限次',
  `smode` tinyint(4) DEFAULT '0' COMMENT '成功后操作  0：无操作，1：发送礼包，2：发送短信，3：发送邮件，4：调用接口',
  `svalue` int(11) DEFAULT '0' COMMENT '操作内容',
  PRIMARY KEY (`pid`),
  KEY `idx_en` (`en`(191)) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of s_active_type
-- ----------------------------

-- ----------------------------
-- Table structure for `s_bespeak`
-- ----------------------------
DROP TABLE IF EXISTS `s_bespeak`;
CREATE TABLE `s_bespeak` (
  `pid` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `date` varchar(20) DEFAULT '' COMMENT '日期  yyyy-MM-dd HH:mm:ss',
  `ip` varchar(255) DEFAULT '' COMMENT 'ip',
  `other` varchar(255) DEFAULT '' COMMENT '参与人',
  `info` varchar(255) DEFAULT '' COMMENT '附加信息',
  `info1` varchar(255) DEFAULT '' COMMENT '附加信息1',
  `info2` varchar(255) DEFAULT '' COMMENT '附加信息2',
  `info3` text COMMENT '附加信息3',
  `info4` text COMMENT '附加信息4',
  PRIMARY KEY (`pid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='预约';

-- ----------------------------
-- Records of s_bespeak
-- ----------------------------

-- ----------------------------
-- Table structure for `s_bespeak_type`
-- ----------------------------
DROP TABLE IF EXISTS `s_bespeak_type`;
CREATE TABLE `s_bespeak_type` (
  `pid` int(10) NOT NULL COMMENT '主键',
  `name` varchar(255) DEFAULT '' COMMENT '名称',
  `start` varchar(20) DEFAULT '' COMMENT '开始时间  yyyy-MM-dd HH:mm:ss',
  `end` varchar(20) DEFAULT '' COMMENT '结束时间  yyyy-MM-dd HH:mm:ss',
  `status` tinyint(4) DEFAULT '0' COMMENT '状态   0 -- 关闭  1 --开启',
  `offset` int(11) DEFAULT '0' COMMENT '偏移量',
  PRIMARY KEY (`pid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of s_bespeak_type
-- ----------------------------

-- ----------------------------
-- Table structure for `s_btn_click`
-- ----------------------------
DROP TABLE IF EXISTS `s_btn_click`;
CREATE TABLE `s_btn_click` (
  `pid` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `date` varchar(20) DEFAULT '' COMMENT '日期',
  `btn` varchar(100) DEFAULT '' COMMENT '按钮',
  `page` varchar(255) DEFAULT '' COMMENT '来源页面',
  `ip` varchar(100) DEFAULT '' COMMENT 'ip',
  `info` varchar(255) DEFAULT '' COMMENT '附加信息',
  PRIMARY KEY (`pid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of s_btn_click
-- ----------------------------

-- ----------------------------
-- Table structure for `s_business`
-- ----------------------------
DROP TABLE IF EXISTS `s_business`;
CREATE TABLE `s_business` (
  `pid` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `type` int(11) DEFAULT '0' COMMENT '分类',
  `name` varchar(100) DEFAULT '' COMMENT '名称',
  `icon` varchar(255) DEFAULT '' COMMENT '图片',
  `site` varchar(255) DEFAULT '' COMMENT '地址',
  `sort` int(11) DEFAULT '999' COMMENT '排序',
  PRIMARY KEY (`pid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of s_business
-- ----------------------------

-- ----------------------------
-- Table structure for `s_business_type`
-- ----------------------------
DROP TABLE IF EXISTS `s_business_type`;
CREATE TABLE `s_business_type` (
  `pid` int(11) NOT NULL DEFAULT '0' COMMENT '主键',
  `name` varchar(100) DEFAULT '' COMMENT '名称',
  PRIMARY KEY (`pid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of s_business_type
-- ----------------------------

-- ----------------------------
-- Table structure for `s_contact`
-- ----------------------------
DROP TABLE IF EXISTS `s_contact`;
CREATE TABLE `s_contact` (
  `pid` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `type` int(11) DEFAULT '0' COMMENT '分类',
  `config` int(11) DEFAULT '0' COMMENT '社交配置',
  `name` varchar(100) DEFAULT '' COMMENT '名称',
  `value` varchar(255) DEFAULT '' COMMENT '值',
  `site` varchar(255) DEFAULT '' COMMENT '地址',
  `pic` varchar(255) DEFAULT '' COMMENT '图片',
  `sort` int(11) DEFAULT '999' COMMENT '排序',
  PRIMARY KEY (`pid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of s_contact
-- ----------------------------

-- ----------------------------
-- Table structure for `s_contact_config`
-- ----------------------------
DROP TABLE IF EXISTS `s_contact_config`;
CREATE TABLE `s_contact_config` (
  `pid` int(11) NOT NULL DEFAULT '0' COMMENT '主键',
  `name` varchar(100) DEFAULT '' COMMENT '名称',
  PRIMARY KEY (`pid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of s_contact_config
-- ----------------------------
INSERT INTO `s_contact_config` VALUES ('1', '微信');
INSERT INTO `s_contact_config` VALUES ('2', '新浪微博');
INSERT INTO `s_contact_config` VALUES ('3', '腾讯微博');
INSERT INTO `s_contact_config` VALUES ('4', '百度贴吧');
INSERT INTO `s_contact_config` VALUES ('5', 'FaceBook');
INSERT INTO `s_contact_config` VALUES ('6', 'Twitter');
INSERT INTO `s_contact_config` VALUES ('7', '邮箱');
INSERT INTO `s_contact_config` VALUES ('8', '电话');
INSERT INTO `s_contact_config` VALUES ('9', 'QQ');
INSERT INTO `s_contact_config` VALUES ('10', 'TapTap');

-- ----------------------------
-- Table structure for `s_contact_type`
-- ----------------------------
DROP TABLE IF EXISTS `s_contact_type`;
CREATE TABLE `s_contact_type` (
  `pid` int(11) NOT NULL DEFAULT '0' COMMENT '主键',
  `name` varchar(100) DEFAULT '' COMMENT '名称',
  PRIMARY KEY (`pid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of s_contact_type
-- ----------------------------
INSERT INTO `s_contact_type` VALUES ('1', '游戏');
INSERT INTO `s_contact_type` VALUES ('2', '客服');

-- ----------------------------
-- Table structure for `s_download`
-- ----------------------------
DROP TABLE IF EXISTS `s_download`;
CREATE TABLE `s_download` (
  `pid` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `type` int(11) DEFAULT '0' COMMENT '分类',
  `name` varchar(100) DEFAULT '' COMMENT '下载名称',
  `site` varchar(255) DEFAULT '' COMMENT '下载地址',
  `pic` varchar(255) DEFAULT '' COMMENT '下载图片  按钮/二维码',
  `sort` int(11) DEFAULT '999' COMMENT '排序',
  PRIMARY KEY (`pid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of s_download
-- ----------------------------

-- ----------------------------
-- Table structure for `s_download_type`
-- ----------------------------
DROP TABLE IF EXISTS `s_download_type`;
CREATE TABLE `s_download_type` (
  `pid` int(11) NOT NULL DEFAULT '0' COMMENT '主键',
  `name` varchar(100) DEFAULT '' COMMENT '名称',
  PRIMARY KEY (`pid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of s_download_type
-- ----------------------------
INSERT INTO `s_download_type` VALUES ('1', '扫码下载');
INSERT INTO `s_download_type` VALUES ('2', 'IOS');
INSERT INTO `s_download_type` VALUES ('3', 'Android');
INSERT INTO `s_download_type` VALUES ('4', 'TapTap');
INSERT INTO `s_download_type` VALUES ('5', 'IOS越狱');

-- ----------------------------
-- Table structure for `s_email_logs`
-- ----------------------------
DROP TABLE IF EXISTS `s_email_logs`;
CREATE TABLE `s_email_logs` (
  `pid` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `date` varchar(20) DEFAULT '' COMMENT '日期',
  `email` varchar(200) DEFAULT '' COMMENT '邮箱',
  `ip` varchar(100) DEFAULT '' COMMENT 'IP',
  `content` text COMMENT '内容',
  `status` tinyint(2) DEFAULT '1' COMMENT '状态  0：失败，1：成功',
  `msg` text COMMENT '返回信息',
  PRIMARY KEY (`pid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of s_email_logs
-- ----------------------------

-- ----------------------------
-- Table structure for `s_gift_code`
-- ----------------------------
DROP TABLE IF EXISTS `s_gift_code`;
CREATE TABLE `s_gift_code` (
  `pid` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `type` int(11) DEFAULT '0' COMMENT '分类',
  `code` varchar(100) DEFAULT '' COMMENT '码',
  `other` varchar(100) DEFAULT '' COMMENT '领取人',
  `info` varchar(200) DEFAULT '' COMMENT '附加信息',
  `date` varchar(20) DEFAULT '' COMMENT '日期',
  `status` tinyint(4) DEFAULT '0' COMMENT '状态   0 -- 未领取， 1 -- 已领取',
  PRIMARY KEY (`pid`),
  KEY `idx_other` (`other`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of s_gift_code
-- ----------------------------

-- ----------------------------
-- Table structure for `s_gift_type`
-- ----------------------------
DROP TABLE IF EXISTS `s_gift_type`;
CREATE TABLE `s_gift_type` (
  `pid` int(11) NOT NULL COMMENT '主键',
  `name` varchar(100) DEFAULT '' COMMENT '名称',
  `mode` tinyint(4) DEFAULT '0' COMMENT '领取方式  0：返回页面，1：短信发送，2：邮件发送，3：接口发送',
  `mvalue` int(11) DEFAULT '0' COMMENT '方式内容',
  `limit` tinyint(4) DEFAULT '0' COMMENT '限制  0：不限制，1：只能自己使用，2：只能他人使用',
  PRIMARY KEY (`pid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of s_gift_type
-- ----------------------------

-- ----------------------------
-- Table structure for `s_heroes`
-- ----------------------------
DROP TABLE IF EXISTS `s_heroes`;
CREATE TABLE `s_heroes` (
  `pid` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `date` varchar(20) DEFAULT '' COMMENT '日期',
  `name` varchar(100) DEFAULT '' COMMENT '名称',
  `type` int(10) DEFAULT '0' COMMENT '分类',
  `tags` varchar(255) DEFAULT '' COMMENT '标签：多个标签“ , ”分隔。',
  `sort` int(11) DEFAULT '999' COMMENT '排序',
  PRIMARY KEY (`pid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of s_heroes
-- ----------------------------

-- ----------------------------
-- Table structure for `s_heroes_attr`
-- ----------------------------
DROP TABLE IF EXISTS `s_heroes_attr`;
CREATE TABLE `s_heroes_attr` (
  `pid` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `hero` int(11) DEFAULT '0' COMMENT '角色',
  `name` varchar(100) DEFAULT '' COMMENT '名称',
  `code` varchar(100) DEFAULT '' COMMENT '字段',
  `value` varchar(255) DEFAULT '' COMMENT '值',
  `desc` varchar(255) DEFAULT '' COMMENT '描述',
  PRIMARY KEY (`pid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='角色配置的属性模版表';

-- ----------------------------
-- Records of s_heroes_attr
-- ----------------------------

-- ----------------------------
-- Table structure for `s_heroes_attr_tp`
-- ----------------------------
DROP TABLE IF EXISTS `s_heroes_attr_tp`;
CREATE TABLE `s_heroes_attr_tp` (
  `pid` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `input` tinyint(4) DEFAULT '0' COMMENT '类型  0：text，1：textarea，2：file，3：select，4：radio，5：checkbox',
  `name` varchar(100) DEFAULT '' COMMENT '名称',
  `code` varchar(100) DEFAULT '' COMMENT '字段',
  `def` varchar(255) DEFAULT '' COMMENT '默认值',
  `isnull` tinyint(4) DEFAULT '1' COMMENT '是否必填  0：是，1：否',
  `isshow` tinyint(4) DEFAULT '1' COMMENT '是否展示  0：否，1：是',
  `desc` varchar(255) DEFAULT '' COMMENT '描述',
  PRIMARY KEY (`pid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='角色配置的属性模版表';

-- ----------------------------
-- Records of s_heroes_attr_tp
-- ----------------------------

-- ----------------------------
-- Table structure for `s_heroes_attr_tp_sel`
-- ----------------------------
DROP TABLE IF EXISTS `s_heroes_attr_tp_sel`;
CREATE TABLE `s_heroes_attr_tp_sel` (
  `pid` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `temp` int(11) DEFAULT '0' COMMENT '模版',
  `name` varchar(100) DEFAULT '' COMMENT '名称',
  `code` varchar(100) DEFAULT '' COMMENT '字段',
  `value` varchar(255) DEFAULT '' COMMENT '值',
  PRIMARY KEY (`pid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='角色配置的属性的选项表';

-- ----------------------------
-- Records of s_heroes_attr_tp_sel
-- ----------------------------

-- ----------------------------
-- Table structure for `s_heroes_type`
-- ----------------------------
DROP TABLE IF EXISTS `s_heroes_type`;
CREATE TABLE `s_heroes_type` (
  `pid` int(11) NOT NULL DEFAULT '0' COMMENT '主键',
  `name` varchar(100) DEFAULT '' COMMENT '名称',
  PRIMARY KEY (`pid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of s_heroes_type
-- ----------------------------

-- ----------------------------
-- Table structure for `s_interface`
-- ----------------------------
DROP TABLE IF EXISTS `s_interface`;
CREATE TABLE `s_interface` (
  `pid` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `name` varchar(100) DEFAULT '' COMMENT '名称',
  `en` varchar(100) DEFAULT '' COMMENT '英文名  调用接口时使用',
  `url` varchar(255) DEFAULT '' COMMENT '接口地址',
  `status` tinyint(4) DEFAULT '0' COMMENT '状态    0：禁用，1：启用',
  `reqType` tinyint(4) DEFAULT '0' COMMENT '请求方式   0：POST，1：GET',
  `reqStructure` tinyint(4) DEFAULT '0' COMMENT '请求数据格式   0：无要求，1：JSON，2：XML',
  `reqParams` text COMMENT '请求参数   多个逗号隔开',
  `signType` tinyint(4) DEFAULT '0' COMMENT '参数加密方式  0：不加密，1：MD5',
  `signName` varchar(100) DEFAULT '' COMMENT '密文参数名',
  `signMode` tinyint(4) DEFAULT '0' COMMENT '参数链接方式   0：key=value&...，1：keyvalue...',
  `connector` varchar(10) DEFAULT '=' COMMENT '连接符',
  `separator` varchar(10) DEFAULT '&' COMMENT '分隔符',
  `keyType` tinyint(4) unsigned DEFAULT '0' COMMENT '密钥方式  0：无密钥，1：服务器密钥，2：时间戳（毫秒），3：时间戳（秒），4：自定义密钥，5：固定密钥123456700',
  `keyName` varchar(100) DEFAULT '' COMMENT '密钥参数名',
  `keyValue` varchar(100) DEFAULT '' COMMENT '自定义密钥',
  `respType` tinyint(4) DEFAULT '0' COMMENT '响应方式   0：respones stream，1：respones body',
  `respStructure` tinyint(4) DEFAULT '0' COMMENT '响应数据类型    0：字符串，1：JSON，2：XML',
  `success` tinyint(4) DEFAULT '0' COMMENT '是否处理结果   0：否，1：是',
  `sname` varchar(100) DEFAULT '' COMMENT '结果的标识',
  `scode` varchar(100) DEFAULT '' COMMENT '成功结果的标识',
  `tcode` tinyint(4) DEFAULT '0' COMMENT '图形码验证  0：不验证，1：正确后失效，2：正确后不失效',
  `vcode` tinyint(4) DEFAULT '0' COMMENT '短信验证码验证   0：不验证，1：正确后失效，2：正确后不失效',
  `sign` tinyint(4) DEFAULT '0' COMMENT '其他验证   0：不验证，1：DH验证后失效，2：DH验证后不失效',
  `limit` tinyint(4) DEFAULT '0' COMMENT 'IP限制  0：否，1：是',
  `reSend` tinyint(4) DEFAULT '0' COMMENT '失败重发  0：否，1：是',
  `reCount` int(11) DEFAULT '0' COMMENT '重发次数',
  `info` text COMMENT '接口文档说明',
  PRIMARY KEY (`pid`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of s_interface
-- ----------------------------
INSERT INTO `s_interface` VALUES ('2', '账号中心_登陆', 'account_login', 'http://inner.ucenter.ppgame.com/normal_login', '1', '0', '1', 'app_id,login_identify,openid,login_pwd,encrypt_mode,sign,client_ip', '0', '', '0', '', '', '0', '', '', '0', '0', '0', '', '', '0', '0', '0', '0', '0', '0', '接口使用场景\n\n	客户端首先连接对外代理，发起登录请求，普通登录(区别于第三方平台登录)，请求登录的信息有四种：openid/密码，email/密码，telphone/密码。\n	对外代理会将这些信息发送给账户中心进行登录。此处描述的接口针对这个操作。\n\n接口说明\n\n	接口中的openid，是客户端从上次成功登录时记录下的信息里获取到的，当登录请求包含openid时，服务端可以省去根据login_id查找openid的步骤。客户端应尽量给出openid。\n	目前的业务处理服务器的设计，仅支持login_pwd为md5加密方式。\n\n发起请求需要的参数\n\n参数名称	类型	描述\napp_id	string	应用在平台中的唯一标识\nlogin_identify 	string	登录使用的标识(identify)，可以是username，email，telphone。\nopenid	string	openid，可能为空\nlogin_pwd	string	通常是密码加密后的密文。macaddr或idfa登录方式此项为空\nencrypt_mode	string	login_pwd的加密方式 md5/none\nsign	string	用于验证数据完整性\nclient_ip	string	客户端ip\n\n响应请求的返回参数\n\n参数名称	类型	描述\napp_id	string	应用在平台中的唯一id。\nopenid	string	用户在平台的唯一标识。\naccess_token	string	访问口令，之后访问app，或绑定邮箱会使用。\nregister_mode	string	devid/email/telphone/username\nclient_ip	string	客户端ip。\nresult	int	0表示成功，其他数字都是错误，错误信息在msg里面\nmsg	string	附带信息，可能为空。当ret不为0时，这里包含错误信息。\n\n错误码及释义\n\n错误码	释义 \n60101	json格式错误\n60121	openid格式错误\n60102	login_identify格式错误\n60103	openid不存在。使用openid登录时会发生\n60104	openid与email不匹配\n60105	openid与telphone不匹配\n60106	openid与devid不匹配\n60107	openid与username不匹配\n60108	login_identify格式错误\n60109	密码不对\n60110	不该发生。devid找到了openid，openid在user表里却不存在\n60111	不存在的email\n60112	不该发生。email找到了openid，openid在user表不存在\n60113	密码不对\n60114	邮箱未激活\n60115	username不存在\n60116	不该发生。username找到了openid，openid在user表不存在\n60117	密码不对\n60118	telphone不存在\n60120	密码不对');
INSERT INTO `s_interface` VALUES ('3', '账号中心_手机注册_大陆', 'account_reg_phone', 'http://inner.ucenter.ppgame.com/telphone_register', '1', '0', '1', 'app_id,channel_id,telphone,pwd,encrypt_mode,vcode', '0', '', '0', '', '', '0', '', '', '0', '0', '0', '', '', '0', '0', '0', '0', '0', '0', '接口使用场景:\n\n	这里是telphone注册的接口。\n\n接口说明:\n\n	无\n\n发起请求需要的参数:\n\n参数名称	类型	描述\napp_id	string	应用在平台中的唯一id\nchannel_id	string	渠道id\ntelphone	string	telphone\npwd	string	加密后的密码\nencrypt_mode	String	Loginitem_2的加密方式 md5/none\nvcode	string	手机收到的短信验证码\n\n响应请求需要的参数:\n\n参数名称	类型	描述\napp_id	string	应用在平台中的唯一id，大于0\ntelphone	string	手机号\nopenid	string	注册成功后，对应的openid\nresult	int	0表示成功，其他数字都是错误码，错误信息在msg里面\nmsg	string	附加信息\n\n错误码及释义:\n\n错误码	释义 \n60501	json格式错误\n60502	telphone格式不对\n60503	telphone已被使用\n60504	vcode过期\n60505	vcode不对\n60506	系统错误。存入user失败\n60507	系统错误。存入telphone_openid失败');
INSERT INTO `s_interface` VALUES ('4', '账号中心_手机验证码_大陆', 'account_vcode', 'http://inner.ucenter.ppgame.com/req_telphone_vcode', '1', '0', '1', 'telphone', '0', '', '0', '', '', '0', '', '', '0', '0', '0', '', '', '1', '0', '0', '0', '0', '0', '接口使用场景:\n\n	在进行手机注册或手机绑定之前，需要获取手机验证码。\n\n接口说明:\n\n	验证码过期时间为120秒。如果同样的手机号，在60秒内再次请求验证码，会返回60903。60秒后，再次请求验证码，会生成新的验证码替代旧验证码。\n\n起请求需要的参数:\n\n参数名称	类型	描述\ntelphone	string	手机号\n\n响应请求需要的参数:\n\n参数名称	类型	描述\nresult	int	0表示成功。其他失败错误码\n\n错误码及释义:\n\n错误码	释义 \n60901	json格式错误\n60902	telphone格式不对\n60903	上次发送的验证码不到60秒');
INSERT INTO `s_interface` VALUES ('7', '账号中心_手机密码修改', 'account_pwd_reset_phone', 'http://inner.ucenter.ppgame.com/telphone_pwd_reset', '1', '0', '1', 'telphone,vcode,pwd', '0', null, '0', '', '', '0', null, '', '0', '0', '0', null, null, '0', '0', '0', '0', '0', '0', '接口使用场景:\n\n	玩家忘记密码后，在已绑定手机的前提下，可以使用手机，请求一个验证码，然后使用验证码，重置密码。\n\n接口说明:\n\n	无\n\n发起请求需要的参数:\n	\n参数名称	类型	描述\ntelphone	string	该账号所绑定的手机号\nvcode	string	短信验证码，发到手机上的6位数字\npwd	string	新密码\n\n响应请求需要的参数:\n\n参数名称	类型	描述\nresult	int	0表示成功。其他失败错误码\nmsg	String	\n\n错误码及释义:\n\n错误码	释义 \n61701	json格式错误\n61702	手机号格式错误\n61703	验证码过期\n61704	验证码错误\n61705	手机号未绑定到任何账号\n61706	不该发生。user表没有对应数据。\n61707	系统错误。存入user表失败');

-- ----------------------------
-- Table structure for `s_interface_logs`
-- ----------------------------
DROP TABLE IF EXISTS `s_interface_logs`;
CREATE TABLE `s_interface_logs` (
  `pid` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `ifs` int(11) DEFAULT '0' COMMENT '接口',
  `params` text COMMENT '参数',
  `date` varchar(20) DEFAULT '' COMMENT '日期  yyyy-MM-dd HH:mm:ss',
  `status` tinyint(4) DEFAULT '0' COMMENT '状态   0：失败 ， 1 ： 成功',
  `ifsRes` text COMMENT '接口返回信息',
  `reCount` tinyint(4) DEFAULT '0' COMMENT '重发次数',
  `reUser` varchar(100) DEFAULT '' COMMENT '重发人',
  `reDate` varchar(20) DEFAULT '' COMMENT '重发时间',
  `reSend` text COMMENT '重发结果',
  `info` varchar(255) DEFAULT '' COMMENT '附加信息',
  PRIMARY KEY (`pid`),
  KEY `idx_ifs` (`ifs`) USING BTREE,
  KEY `idx_date` (`date`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='礼包日志';

-- ----------------------------
-- Records of s_interface_logs
-- ----------------------------

-- ----------------------------
-- Table structure for `s_menu`
-- ----------------------------
DROP TABLE IF EXISTS `s_menu`;
CREATE TABLE `s_menu` (
  `pid` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `parent` int(11) DEFAULT '0' COMMENT '父级pid',
  `name` varchar(20) DEFAULT '' COMMENT '名称',
  `site` varchar(100) DEFAULT '' COMMENT '地址',
  `icon` varchar(100) DEFAULT '' COMMENT '图标',
  `status` tinyint(4) DEFAULT '0' COMMENT '状态  0--禁用，1--启用',
  PRIMARY KEY (`pid`)
) ENGINE=InnoDB AUTO_INCREMENT=82 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of s_menu
-- ----------------------------
INSERT INTO `s_menu` VALUES ('1', '0', '界面配置', null, '', '0');
INSERT INTO `s_menu` VALUES ('2', '0', '下载配置', null, '', '0');
INSERT INTO `s_menu` VALUES ('3', '0', '关注我们', null, '', '0');
INSERT INTO `s_menu` VALUES ('4', '0', '消息发布', null, '', '0');
INSERT INTO `s_menu` VALUES ('5', '0', '礼包管理', null, '', '0');
INSERT INTO `s_menu` VALUES ('6', '0', '游戏音画', null, '', '0');
INSERT INTO `s_menu` VALUES ('7', '0', '游戏资料', null, '', '0');
INSERT INTO `s_menu` VALUES ('8', '0', '游戏数据', null, '', '0');
INSERT INTO `s_menu` VALUES ('9', '0', '合作媒体', null, '', '0');
INSERT INTO `s_menu` VALUES ('10', '0', '玩家天地', null, '', '0');
INSERT INTO `s_menu` VALUES ('11', '0', '邮件系统', null, '', '0');
INSERT INTO `s_menu` VALUES ('12', '0', '短信系统', null, '', '0');
INSERT INTO `s_menu` VALUES ('13', '0', '媒体管理', null, '', '0');
INSERT INTO `s_menu` VALUES ('14', '1', 'LOGO', 'html/logo.html', '', '0');
INSERT INTO `s_menu` VALUES ('15', '1', '背景', 'html/background.html', '', '0');
INSERT INTO `s_menu` VALUES ('16', '1', '推荐(展示)分类', 'html/recommend_type.html', '', '0');
INSERT INTO `s_menu` VALUES ('17', '1', '推荐(展示)', 'html/recommend.html', '', '0');
INSERT INTO `s_menu` VALUES ('18', '1', '其他配置分类', 'html/system_other_type.html', '', '0');
INSERT INTO `s_menu` VALUES ('19', '1', '其他配置', 'html/system_other.html', '', '0');
INSERT INTO `s_menu` VALUES ('20', '2', '下载配置', 'html/download.html', '', '0');
INSERT INTO `s_menu` VALUES ('21', '3', '微博微信分类', 'html/system_type.html', '', '0');
INSERT INTO `s_menu` VALUES ('22', '3', '微博微信', 'html/system.html', '', '0');
INSERT INTO `s_menu` VALUES ('23', '3', '邮箱/QQ', 'html/customer.html', '', '0');
INSERT INTO `s_menu` VALUES ('24', '3', '分享', null, '', '0');
INSERT INTO `s_menu` VALUES ('25', '4', '消息广告图', 'html/news_pic.html', '', '0');
INSERT INTO `s_menu` VALUES ('26', '4', '消息分类', 'html/news_type.html', '', '0');
INSERT INTO `s_menu` VALUES ('27', '4', '消息列表', 'html/news.html', '', '0');
INSERT INTO `s_menu` VALUES ('28', '5', '礼包广告图', 'html/gift_pic.html', '', '0');
INSERT INTO `s_menu` VALUES ('29', '5', '礼包列表', 'html/gift.html', '', '0');
INSERT INTO `s_menu` VALUES ('30', '5', '礼包码', 'html/gift_code.html', '', '0');
INSERT INTO `s_menu` VALUES ('31', '5', '领取统计', 'html/gift_count.html', '', '0');
INSERT INTO `s_menu` VALUES ('32', '6', '图集分类', 'html/picture_type.html', '', '0');
INSERT INTO `s_menu` VALUES ('33', '6', '图集(原画)', 'html/picture.html', '', '0');
INSERT INTO `s_menu` VALUES ('34', '6', '视频分类', 'html/video_type.html', '', '0');
INSERT INTO `s_menu` VALUES ('35', '6', '视频', 'html/video.html', '', '0');
INSERT INTO `s_menu` VALUES ('36', '7', '资料广告图', 'html/resources_pic.html', '', '0');
INSERT INTO `s_menu` VALUES ('37', '7', '资料分类', 'html/resources_type.html', '', '0');
INSERT INTO `s_menu` VALUES ('38', '7', '资料详情', 'html/resources.html', '', '0');
INSERT INTO `s_menu` VALUES ('39', '7', '其他资料', 'html/resources_other.html', '', '0');
INSERT INTO `s_menu` VALUES ('40', '7', '介绍', 'html/product.html', '', '0');
INSERT INTO `s_menu` VALUES ('41', '7', '装备分类', 'html/equipment_type.html', '', '0');
INSERT INTO `s_menu` VALUES ('42', '7', '装备详情', 'html/equipment.html', '', '0');
INSERT INTO `s_menu` VALUES ('43', '7', '攻略分类', 'html/strategy_type.html', '', '0');
INSERT INTO `s_menu` VALUES ('44', '7', '攻略详情', 'html/strategy.html', '', '0');
INSERT INTO `s_menu` VALUES ('45', '7', '英雄分类', 'html/heroes_type.html', '', '0');
INSERT INTO `s_menu` VALUES ('46', '7', '英雄详情', 'html/heroes.html', '', '0');
INSERT INTO `s_menu` VALUES ('47', '7', '英雄详情2', 'html/heroes2.html', '', '0');
INSERT INTO `s_menu` VALUES ('48', '7', '英雄详情3', 'html/heroes3.html', '', '0');
INSERT INTO `s_menu` VALUES ('49', '8', '服务器配置', 'html/server.html', '', '0');
INSERT INTO `s_menu` VALUES ('50', '8', '账号转移', 'html/zhuanyi.html', '', '0');
INSERT INTO `s_menu` VALUES ('51', '8', '数据接口配置', 'html/interfaces.html', '', '0');
INSERT INTO `s_menu` VALUES ('52', '8', '数据导入', null, '', '0');
INSERT INTO `s_menu` VALUES ('53', '8', '查询配置', null, '', '0');
INSERT INTO `s_menu` VALUES ('54', '9', '合作媒体', 'html/business.html', '', '0');
INSERT INTO `s_menu` VALUES ('55', '11', '邮件配置', 'html/email_config.html', '', '0');
INSERT INTO `s_menu` VALUES ('56', '11', '统一回执', 'html/email_normal.html', '', '0');
INSERT INTO `s_menu` VALUES ('57', '11', '发送邮件', 'html/email.html', '', '0');
INSERT INTO `s_menu` VALUES ('58', '12', '短信配置', 'html/phone_config.html', '', '0');
INSERT INTO `s_menu` VALUES ('59', '12', '统一回执', 'html/phone_normal.html', '', '0');
INSERT INTO `s_menu` VALUES ('60', '12', '短信验证码', 'html/phone_code.html', '', '0');
INSERT INTO `s_menu` VALUES ('61', '12', '发送短信', 'html/phone.html', '', '0');
INSERT INTO `s_menu` VALUES ('62', '12', '短信日志', 'html/phone_log.html', '', '0');
INSERT INTO `s_menu` VALUES ('63', '13', '媒体注册', 'html/media.html', '', '0');
INSERT INTO `s_menu` VALUES ('64', '13', '媒体镜像', 'html/media_clone.html', '', '0');
INSERT INTO `s_menu` VALUES ('65', '13', '媒体下载', 'html/media_download.html', '', '0');
INSERT INTO `s_menu` VALUES ('66', '13', '管理图片', 'html/media_pic.html', '', '0');
INSERT INTO `s_menu` VALUES ('67', '13', '管理新闻', 'html/media_news.html', '', '0');
INSERT INTO `s_menu` VALUES ('68', '0', '账号管理', '', '', '0');
INSERT INTO `s_menu` VALUES ('69', '68', '账号', 'html/role.html', '', '0');
INSERT INTO `s_menu` VALUES ('70', '0', '版本管理', '', '', '0');
INSERT INTO `s_menu` VALUES ('71', '70', '版本', 'html/lang.html', '', '0');
INSERT INTO `s_menu` VALUES ('72', '70', '菜单', 'html/menu.html', '', '0');
INSERT INTO `s_menu` VALUES ('73', '8', '接口日志查询', 'html/interfaces_logs.html', '', '0');
INSERT INTO `s_menu` VALUES ('74', '8', '兑换查询', 'html/exchange.html', '', '0');
INSERT INTO `s_menu` VALUES ('75', '8', '活动分类', 'html/active_type.html', '', '0');
INSERT INTO `s_menu` VALUES ('76', '8', '活动配置', 'html/active_config.html', '', '0');
INSERT INTO `s_menu` VALUES ('77', '8', '活动记录', 'html/active.html', '', '0');
INSERT INTO `s_menu` VALUES ('78', '8', '活动统计', 'html/active_count.html', '', '0');
INSERT INTO `s_menu` VALUES ('79', '10', '活动分类', 'html/player_type.html', '', '0');
INSERT INTO `s_menu` VALUES ('80', '10', '活动记录', 'html/player.html', '', '0');
INSERT INTO `s_menu` VALUES ('81', '10', '活动日志', 'html/player_log.html', '', '0');

-- ----------------------------
-- Table structure for `s_news`
-- ----------------------------
DROP TABLE IF EXISTS `s_news`;
CREATE TABLE `s_news` (
  `pid` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `type` int(11) DEFAULT '0' COMMENT '分类',
  `recommend` tinyint(4) DEFAULT '0' COMMENT '推荐  0--否，1--是',
  `illustration` varchar(255) DEFAULT '' COMMENT '插图',
  `title` varchar(255) DEFAULT '' COMMENT '标题',
  `subtitle` varchar(255) DEFAULT '' COMMENT '副标题',
  `date` varchar(20) DEFAULT '' COMMENT '日期  yyyy-MM-dd HH:mm:ss',
  `marks` tinyint(4) DEFAULT '0' COMMENT '使用链接   0--否，1--是',
  `site` varchar(255) DEFAULT '' COMMENT '链接地址',
  `content` text COMMENT '内容',
  `keywords` text COMMENT '关键字 （seo）',
  `description` text COMMENT '描述 （seo）',
  PRIMARY KEY (`pid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of s_news
-- ----------------------------

-- ----------------------------
-- Table structure for `s_news_type`
-- ----------------------------
DROP TABLE IF EXISTS `s_news_type`;
CREATE TABLE `s_news_type` (
  `pid` int(11) NOT NULL DEFAULT '0' COMMENT '主键',
  `name` varchar(100) DEFAULT '' COMMENT '名称',
  `icon` varchar(255) DEFAULT '' COMMENT '图片',
  `sort` int(11) DEFAULT '999' COMMENT '排序',
  `home` tinyint(4) DEFAULT '1' COMMENT '首页展示  0：否，1：是',
  `filter` tinyint(4) DEFAULT '0' COMMENT '是否过滤  0 --否，1--是    适用发布其他渠道的新闻',
  PRIMARY KEY (`pid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of s_news_type
-- ----------------------------
INSERT INTO `s_news_type` VALUES ('1', '新闻', null, '1', '1', '0');
INSERT INTO `s_news_type` VALUES ('2', '活动', null, '3', '1', '0');
INSERT INTO `s_news_type` VALUES ('3', '公告', null, '2', '1', '0');
INSERT INTO `s_news_type` VALUES ('4', '攻略', null, '4', '1', '0');

-- ----------------------------
-- Table structure for `s_picture`
-- ----------------------------
DROP TABLE IF EXISTS `s_picture`;
CREATE TABLE `s_picture` (
  `pid` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `type` int(11) DEFAULT '0' COMMENT '分类',
  `name` varchar(100) DEFAULT '' COMMENT '名称',
  `pic` varchar(255) DEFAULT '' COMMENT '图片地址',
  `date` varchar(20) DEFAULT '' COMMENT '日期',
  `description` text COMMENT '描述',
  `down` varchar(255) DEFAULT '' COMMENT '下载地址',
  `author` varchar(100) DEFAULT '' COMMENT '作者',
  `recommend` tinyint(4) DEFAULT '0' COMMENT '推荐  0--否，1--是',
  `highlight` tinyint(4) DEFAULT '0' COMMENT '加精  0--否，1--是',
  `sort` int(11) DEFAULT '999' COMMENT '排序',
  `home` tinyint(4) DEFAULT '0' COMMENT '是否推到首页  0--否，1--是',
  PRIMARY KEY (`pid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of s_picture
-- ----------------------------

-- ----------------------------
-- Table structure for `s_picture_type`
-- ----------------------------
DROP TABLE IF EXISTS `s_picture_type`;
CREATE TABLE `s_picture_type` (
  `pid` int(11) NOT NULL COMMENT '主键',
  `name` varchar(100) DEFAULT '' COMMENT '名称',
  `sort` int(11) DEFAULT '999' COMMENT '排序',
  `home` tinyint(4) DEFAULT '0' COMMENT '是否推到首页  0--否，1--是',
  PRIMARY KEY (`pid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of s_picture_type
-- ----------------------------
INSERT INTO `s_picture_type` VALUES ('1', '游戏原画', '999', '0');
INSERT INTO `s_picture_type` VALUES ('2', '游戏壁纸', '999', '0');
INSERT INTO `s_picture_type` VALUES ('3', '同人作品', '999', '0');
INSERT INTO `s_picture_type` VALUES ('4', 'COS作品', '999', '0');

-- ----------------------------
-- Table structure for `s_player`
-- ----------------------------
DROP TABLE IF EXISTS `s_player`;
CREATE TABLE `s_player` (
  `pid` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `ip` varchar(100) DEFAULT '' COMMENT 'IP',
  `date` varchar(20) DEFAULT '' COMMENT '日期',
  `type` int(11) DEFAULT '0' COMMENT '分类',
  `group` varchar(100) DEFAULT '' COMMENT '分组',
  `player` varchar(255) DEFAULT '' COMMENT '参与者',
  `icon` varchar(255) DEFAULT '' COMMENT '头像',
  `content` text COMMENT '文章',
  `pic` text COMMENT '图片',
  `video` text COMMENT '视频',
  `audio` text COMMENT '音频',
  `title` varchar(255) DEFAULT '' COMMENT '标题',
  `desc` varchar(255) DEFAULT '' COMMENT '描述',
  `info` varchar(255) DEFAULT '' COMMENT '附加信息',
  `info1` varchar(255) DEFAULT '',
  `info2` varchar(255) DEFAULT '',
  `info3` varchar(255) DEFAULT '',
  `info4` varchar(255) DEFAULT '',
  `view` int(11) DEFAULT '0' COMMENT '浏览量',
  `good` int(11) DEFAULT '0' COMMENT '点赞量',
  `offset` int(11) DEFAULT '0' COMMENT '偏移量',
  `status` tinyint(4) DEFAULT '0' COMMENT '状态：0 -- 待审核，1 -- 通过，2 -- 未通过',
  `auther` varchar(100) DEFAULT '' COMMENT '审核人',
  `adate` varchar(20) DEFAULT '' COMMENT '审核时间',
  PRIMARY KEY (`pid`),
  KEY `ip` (`player`(191),`date`,`info`(191)) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of s_player
-- ----------------------------

-- ----------------------------
-- Table structure for `s_player_logs`
-- ----------------------------
DROP TABLE IF EXISTS `s_player_logs`;
CREATE TABLE `s_player_logs` (
  `pid` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `ip` varchar(100) DEFAULT '' COMMENT 'IP',
  `date` varchar(20) DEFAULT '' COMMENT '日期',
  `type` int(11) DEFAULT '0' COMMENT '分类',
  `other` varchar(255) DEFAULT '' COMMENT '参与者',
  `info` varchar(255) DEFAULT '',
  `info1` varchar(255) DEFAULT '',
  `info2` varchar(255) DEFAULT '',
  `info3` varchar(255) DEFAULT '',
  PRIMARY KEY (`pid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of s_player_logs
-- ----------------------------

-- ----------------------------
-- Table structure for `s_player_type`
-- ----------------------------
DROP TABLE IF EXISTS `s_player_type`;
CREATE TABLE `s_player_type` (
  `pid` int(11) NOT NULL COMMENT '主键',
  `name` varchar(255) DEFAULT '' COMMENT '名称',
  `en` varchar(255) DEFAULT '' COMMENT '标识',
  `start` varchar(20) DEFAULT '' COMMENT '开始时间  yyyy-MM-dd HH:mm:ss',
  `end` varchar(20) DEFAULT '' COMMENT '结束时间  yyyy-MM-dd HH:mm:ss',
  `status` tinyint(4) DEFAULT '0' COMMENT '状态   0 -- 关闭  1 --开启',
  `count` int(11) DEFAULT '0' COMMENT '参加次数   小于0 --- 无限次',
  `auth` tinyint(4) DEFAULT '0' COMMENT '是否审核   0 -- 否  1 --是',
  `sign` tinyint(4) DEFAULT '0' COMMENT '是否验证  0 -- 否  1 --是',
  `share` tinyint(4) DEFAULT '0' COMMENT '是否分享  0 -- 否  1 --是',
  `share_mode` tinyint(4) DEFAULT '0' COMMENT '分享模式限制',
  `share_count` int(11) DEFAULT '0' COMMENT '每次分享获得参与次数',
  `share_limit` int(11) DEFAULT '0' COMMENT '分享次数   小于0 --- 无限次',
  `poll` tinyint(4) DEFAULT '0' COMMENT '是否投票  0 -- 否  1 --是',
  `poll_mode` tinyint(4) DEFAULT '0' COMMENT '投票模式限制',
  `poll_count` int(11) DEFAULT '0' COMMENT '每次投票获得票数',
  `poll_limit` int(11) DEFAULT '0' COMMENT '投票次数   小于0 --- 无限次',
  PRIMARY KEY (`pid`),
  KEY `idx_en` (`en`(191))
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of s_player_type
-- ----------------------------
INSERT INTO `s_player_type` VALUES ('1', '预约', '', '', '2017-08-25 10:00:00', '1', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0');
INSERT INTO `s_player_type` VALUES ('2', '圣捞人形限定大选--A组', '', '', '', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0');
INSERT INTO `s_player_type` VALUES ('14', '2017周年庆抽奖', '', '2017-05-03 00:00:00', '2017-05-30 00:00:00', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0');
INSERT INTO `s_player_type` VALUES ('15', '猎兔行动抽奖', '', '2017-06-17 00:00:00', '2017-06-20 00:00:00', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0');

-- ----------------------------
-- Table structure for `s_pv`
-- ----------------------------
DROP TABLE IF EXISTS `s_pv`;
CREATE TABLE `s_pv` (
  `pid` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `date` varchar(20) DEFAULT '' COMMENT '日期',
  `ip` varchar(100) DEFAULT '' COMMENT 'ip',
  `page` varchar(255) DEFAULT '' COMMENT '受访页面',
  `info` varchar(255) DEFAULT '' COMMENT '附加信息',
  PRIMARY KEY (`pid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of s_pv
-- ----------------------------

-- ----------------------------
-- Table structure for `s_recommend`
-- ----------------------------
DROP TABLE IF EXISTS `s_recommend`;
CREATE TABLE `s_recommend` (
  `pid` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `type` int(11) DEFAULT '0' COMMENT '分类',
  `name` varchar(100) DEFAULT '' COMMENT '名称',
  `pic` varchar(255) DEFAULT '' COMMENT '图片地址',
  `marks` tinyint(2) DEFAULT '0' COMMENT '启用链接：0 -- 不用； 1 -- 启用消息；2 -- 启用地址',
  `newsId` int(11) DEFAULT '0' COMMENT '消息 id',
  `site` varchar(255) DEFAULT '' COMMENT '链接地址',
  `status` tinyint(2) DEFAULT '0' COMMENT '状态：0 --禁用；1 -- 启用',
  `sort` int(11) DEFAULT '999' COMMENT '排序',
  PRIMARY KEY (`pid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of s_recommend
-- ----------------------------

-- ----------------------------
-- Table structure for `s_recommend_type`
-- ----------------------------
DROP TABLE IF EXISTS `s_recommend_type`;
CREATE TABLE `s_recommend_type` (
  `pid` int(11) NOT NULL DEFAULT '0' COMMENT '主键',
  `name` varchar(100) DEFAULT '' COMMENT '名称',
  PRIMARY KEY (`pid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of s_recommend_type
-- ----------------------------

-- ----------------------------
-- Table structure for `s_resources`
-- ----------------------------
DROP TABLE IF EXISTS `s_resources`;
CREATE TABLE `s_resources` (
  `pid` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `type` int(11) DEFAULT '0' COMMENT '分类',
  `recommend` tinyint(4) DEFAULT '0' COMMENT '推荐  0--否，1--是',
  `illustration` varchar(255) DEFAULT '' COMMENT '插图',
  `title` varchar(255) DEFAULT '' COMMENT '标题',
  `subtitle` varchar(255) DEFAULT '' COMMENT '副标题',
  `date` varchar(20) DEFAULT '' COMMENT '日期  yyyy-MM-dd HH:mm:ss',
  `marks` tinyint(4) DEFAULT '0' COMMENT '使用链接   0--否，1--是',
  `site` varchar(255) DEFAULT '' COMMENT '链接地址',
  `content` text COMMENT '内容',
  `keywords` text COMMENT '关键字 （seo）',
  `description` text COMMENT '描述 （seo）',
  PRIMARY KEY (`pid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of s_resources
-- ----------------------------

-- ----------------------------
-- Table structure for `s_resources_type`
-- ----------------------------
DROP TABLE IF EXISTS `s_resources_type`;
CREATE TABLE `s_resources_type` (
  `pid` int(11) NOT NULL COMMENT '主键',
  `parent` int(11) DEFAULT '0' COMMENT '父级',
  `name` varchar(200) DEFAULT '' COMMENT '名称',
  `sort` int(11) DEFAULT '999' COMMENT '排序',
  `home` tinyint(4) DEFAULT '0' COMMENT '是否推到首页  0--否，1--是',
  PRIMARY KEY (`pid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of s_resources_type
-- ----------------------------

-- ----------------------------
-- Table structure for `s_server`
-- ----------------------------
DROP TABLE IF EXISTS `s_server`;
CREATE TABLE `s_server` (
  `pid` int(11) NOT NULL COMMENT '主键',
  `date` varchar(20) DEFAULT '' COMMENT '日期',
  `serverId` varchar(100) NOT NULL DEFAULT '' COMMENT '服务器ID',
  `name` varchar(100) DEFAULT '' COMMENT '名称',
  `url` varchar(255) DEFAULT '' COMMENT '地址',
  `key` varchar(255) DEFAULT '' COMMENT '密钥',
  `status` tinyint(4) DEFAULT '0' COMMENT '状态   0：禁用，1：启用',
  PRIMARY KEY (`pid`,`serverId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of s_server
-- ----------------------------

-- ----------------------------
-- Table structure for `s_share`
-- ----------------------------
DROP TABLE IF EXISTS `s_share`;
CREATE TABLE `s_share` (
  `pid` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `active` int(11) DEFAULT '0' COMMENT '活动',
  `type` int(11) DEFAULT '0' COMMENT '分类',
  `date` varchar(20) DEFAULT '' COMMENT '日期  yyyy-MM-dd HH:mm:ss',
  `ip` varchar(255) DEFAULT '' COMMENT 'ip',
  `other` varchar(255) DEFAULT '' COMMENT '参与人',
  `info` varchar(255) DEFAULT '' COMMENT '附加信息',
  `info1` varchar(255) DEFAULT '' COMMENT '附加信息1',
  `info2` varchar(255) DEFAULT '' COMMENT '附加信息2',
  `info3` text COMMENT '附加信息3',
  `info4` text COMMENT '附加信息4',
  PRIMARY KEY (`pid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='预约';

-- ----------------------------
-- Records of s_share
-- ----------------------------

-- ----------------------------
-- Table structure for `s_share_type`
-- ----------------------------
DROP TABLE IF EXISTS `s_share_type`;
CREATE TABLE `s_share_type` (
  `pid` int(11) NOT NULL DEFAULT '0' COMMENT '主键',
  `name` varchar(100) DEFAULT '' COMMENT '名称',
  PRIMARY KEY (`pid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of s_share_type
-- ----------------------------
INSERT INTO `s_share_type` VALUES ('1', '游戏');
INSERT INTO `s_share_type` VALUES ('2', '客服');

-- ----------------------------
-- Table structure for `s_sms_logs`
-- ----------------------------
DROP TABLE IF EXISTS `s_sms_logs`;
CREATE TABLE `s_sms_logs` (
  `pid` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `date` varchar(20) DEFAULT '' COMMENT '日期',
  `phone` varchar(20) DEFAULT '' COMMENT '手机号',
  `ip` varchar(100) DEFAULT '' COMMENT 'IP',
  `content` varchar(255) DEFAULT '' COMMENT '发送内容',
  `status` tinyint(2) DEFAULT '1' COMMENT '状态   0：失败，1：成功',
  `msg` text COMMENT '返回信息',
  PRIMARY KEY (`pid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of s_sms_logs
-- ----------------------------

-- ----------------------------
-- Table structure for `s_strategy`
-- ----------------------------
DROP TABLE IF EXISTS `s_strategy`;
CREATE TABLE `s_strategy` (
  `pid` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `type` int(11) DEFAULT '0' COMMENT '分类',
  `recommend` tinyint(4) DEFAULT '0' COMMENT '推荐  0--否，1--是',
  `illustration` varchar(255) DEFAULT '' COMMENT '插图',
  `title` varchar(255) DEFAULT '' COMMENT '标题',
  `subtitle` varchar(255) DEFAULT '' COMMENT '副标题',
  `date` varchar(20) DEFAULT '' COMMENT '日期',
  `marks` tinyint(4) DEFAULT '0' COMMENT '使用链接：0--否，1--是',
  `site` varchar(255) DEFAULT '' COMMENT '链接地址',
  `content` text COMMENT '内容',
  `keywords` text COMMENT '关键字 （seo）',
  `description` text COMMENT '描述 （seo）',
  `author` varchar(255) DEFAULT '' COMMENT '作者',
  `adesc` text COMMENT '简介  文章、作者类',
  `highlight` tinyint(4) DEFAULT '0' COMMENT '加精  0--否，1--是',
  `home` tinyint(4) DEFAULT '0' COMMENT '是否推到首页  0--否，1--是',
  PRIMARY KEY (`pid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of s_strategy
-- ----------------------------

-- ----------------------------
-- Table structure for `s_strategy_type`
-- ----------------------------
DROP TABLE IF EXISTS `s_strategy_type`;
CREATE TABLE `s_strategy_type` (
  `pid` int(11) NOT NULL DEFAULT '0' COMMENT '主键',
  `name` varchar(100) DEFAULT '' COMMENT '名称',
  `sort` int(11) DEFAULT '999' COMMENT '排序',
  `home` tinyint(4) DEFAULT '0' COMMENT '是否推到首页  0--否，1--是',
  `group` varchar(255) DEFAULT '' COMMENT '分组',
  PRIMARY KEY (`pid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of s_strategy_type
-- ----------------------------

-- ----------------------------
-- Table structure for `s_system_logs`
-- ----------------------------
DROP TABLE IF EXISTS `s_system_logs`;
CREATE TABLE `s_system_logs` (
  `pid` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `type` varchar(10) DEFAULT '' COMMENT '分类',
  `date` varchar(20) DEFAULT '' COMMENT '日期',
  `user` int(11) DEFAULT '0' COMMENT '用户',
  `ip` varchar(100) DEFAULT '' COMMENT 'ip',
  `uri` varchar(100) DEFAULT '' COMMENT '按钮',
  `params` text COMMENT '参数',
  `before` text COMMENT '更新前',
  `after` text COMMENT '更新后',
  PRIMARY KEY (`pid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of s_system_logs
-- ----------------------------

-- ----------------------------
-- Table structure for `s_user`
-- ----------------------------
DROP TABLE IF EXISTS `s_user`;
CREATE TABLE `s_user` (
  `pid` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `username` varchar(20) DEFAULT '' COMMENT '用户名',
  `password` varchar(32) DEFAULT '' COMMENT '密码   md5',
  `sso` varchar(100) DEFAULT '' COMMENT 'sso标识',
  `system` tinyint(4) DEFAULT '0' COMMENT '系统账号  0--否 1--是',
  `status` tinyint(4) DEFAULT '1' COMMENT '状态  0：禁用，1：启用',
  PRIMARY KEY (`pid`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of s_user
-- ----------------------------
INSERT INTO `s_user` VALUES ('1', 'admin', '21232f297a57a5a743894a0e4a801fc3', '', '1', '0');

-- ----------------------------
-- Table structure for `s_video`
-- ----------------------------
DROP TABLE IF EXISTS `s_video`;
CREATE TABLE `s_video` (
  `pid` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `type` int(11) DEFAULT '0' COMMENT '分类',
  `name` varchar(100) DEFAULT '' COMMENT '名称',
  `pic` varchar(255) DEFAULT '' COMMENT '封面图片',
  `ogg` varchar(255) DEFAULT '' COMMENT 'ogg',
  `mp4` varchar(255) DEFAULT '' COMMENT 'mp4',
  `webm` varchar(255) DEFAULT '' COMMENT 'webm',
  `flash` varchar(255) DEFAULT '' COMMENT 'flash',
  `site` varchar(255) DEFAULT '' COMMENT '网址',
  `date` varchar(20) DEFAULT '' COMMENT '日期',
  `description` text COMMENT '描述',
  `author` varchar(255) DEFAULT '' COMMENT '作者',
  `recommend` tinyint(2) DEFAULT '0' COMMENT '推荐  0--否，1--是',
  `highlight` tinyint(2) DEFAULT '0' COMMENT '加精  0--否，1--是',
  `down` varchar(255) DEFAULT '' COMMENT '下载地址',
  `sort` int(11) DEFAULT '999' COMMENT '排序',
  `home` tinyint(4) DEFAULT '0' COMMENT '是否推到首页  0--否，1--是',
  PRIMARY KEY (`pid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of s_video
-- ----------------------------

-- ----------------------------
-- Table structure for `s_video_type`
-- ----------------------------
DROP TABLE IF EXISTS `s_video_type`;
CREATE TABLE `s_video_type` (
  `pid` int(11) NOT NULL DEFAULT '0' COMMENT '主键',
  `name` varchar(100) DEFAULT '' COMMENT '名称',
  `sort` int(11) DEFAULT '999' COMMENT '排序',
  `home` tinyint(4) DEFAULT '0' COMMENT '是否推到首页  0--否，1--是',
  PRIMARY KEY (`pid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of s_video_type
-- ----------------------------
INSERT INTO `s_video_type` VALUES ('1', '官网首页视频', '999', '0');
