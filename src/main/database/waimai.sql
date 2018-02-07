/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50720
Source Host           : localhost:3306
Source Database       : waimai

Target Server Type    : MYSQL
Target Server Version : 50720
File Encoding         : 65001

Date: 2018-02-07 20:57:37
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for address
-- ----------------------------
DROP TABLE IF EXISTS `address`;
CREATE TABLE `address` (
  `address_id` int(20) NOT NULL AUTO_INCREMENT,
  `address_lianxi` varchar(255) DEFAULT NULL,
  `address_phone` varchar(255) DEFAULT NULL,
  `address_name` varchar(255) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  `address_create_time` date DEFAULT NULL,
  PRIMARY KEY (`address_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of address
-- ----------------------------

-- ----------------------------
-- Table structure for advert
-- ----------------------------
DROP TABLE IF EXISTS `advert`;
CREATE TABLE `advert` (
  `advert_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '广告ID',
  `advert_name` varchar(40) DEFAULT NULL,
  `advert_create_time` date DEFAULT NULL,
  `advert_picture_url` varchar(255) DEFAULT NULL,
  `food_id` int(11) DEFAULT NULL COMMENT '对应的食物的id',
  `advert_description` varchar(255) DEFAULT NULL,
  `advert_button_text` varchar(255) DEFAULT NULL,
  `advert_show` int(10) DEFAULT NULL COMMENT '广告是否显示，1为显示，2为不显示',
  PRIMARY KEY (`advert_id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of advert
-- ----------------------------
INSERT INTO `advert` VALUES ('1', '黄焖鸡', '2018-02-02', '/waimai/advert/c69fc5121a1d4d5f98843335ad656746.jpg', '8', '最好吃的黄焖鸡', '快点我', '1');
INSERT INTO `advert` VALUES ('2', '必胜客', '2018-02-02', '/waimai/advert/129302600b1747b387a47710f663b2ad.jpg', '21', '北京必胜客', '快速购买', '1');
INSERT INTO `advert` VALUES ('3', '庆丰包子', '2018-02-02', '/waimai/advert/f7ce8fbeb4e04815be63b8c1169e1606.jpg', '22', '豪华庆丰', '小笼包姑姑', '1');
INSERT INTO `advert` VALUES ('4', '重庆鸡公煲', '2018-02-02', '/waimai/advert/5df4532559b14b1bbb2cff4cf0987cc8.jpg', '25', '最香的鸡公煲', '豪华套餐', '1');
INSERT INTO `advert` VALUES ('5', '饺子', '2018-02-02', '/waimai/advert/1a8b2654293e45fba94464e3b44923b1.jpg', '6', '饺子，饺子', '来咬我啊', '1');
INSERT INTO `advert` VALUES ('10', '123', '2018-02-03', '/waimai/advert/499574839857407a99ef4e617818c8ec.jpg', '1', '123', '123', '2');

-- ----------------------------
-- Table structure for caritem
-- ----------------------------
DROP TABLE IF EXISTS `caritem`;
CREATE TABLE `caritem` (
  `caritem_id` int(11) NOT NULL AUTO_INCREMENT,
  `caritem_create_time` date DEFAULT NULL,
  `caritem_number` int(20) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  `food_id` int(11) DEFAULT NULL,
  `caritem_brought` int(11) DEFAULT NULL COMMENT '1表示买过，2表示没有买过',
  PRIMARY KEY (`caritem_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of caritem
-- ----------------------------

-- ----------------------------
-- Table structure for comsume
-- ----------------------------
DROP TABLE IF EXISTS `comsume`;
CREATE TABLE `comsume` (
  `consume_id` int(11) NOT NULL AUTO_INCREMENT,
  `consume_create_time` date DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  `consume_money` int(255) DEFAULT NULL,
  `comsume_food` varchar(255) DEFAULT NULL,
  `comsume_number` int(11) DEFAULT NULL,
  `spend_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`consume_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of comsume
-- ----------------------------

-- ----------------------------
-- Table structure for discuss
-- ----------------------------
DROP TABLE IF EXISTS `discuss`;
CREATE TABLE `discuss` (
  `discuss_id` int(11) NOT NULL AUTO_INCREMENT,
  `discuss_create_time` date DEFAULT NULL,
  `discuss_message` varchar(255) DEFAULT NULL,
  `cartitem_id` int(11) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`discuss_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of discuss
-- ----------------------------

-- ----------------------------
-- Table structure for feedback
-- ----------------------------
DROP TABLE IF EXISTS `feedback`;
CREATE TABLE `feedback` (
  `feedback_id` int(11) NOT NULL AUTO_INCREMENT,
  `feedback_name` varchar(40) DEFAULT NULL,
  `feedback_create_time` date DEFAULT NULL,
  `feedback_message` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`feedback_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of feedback
-- ----------------------------

-- ----------------------------
-- Table structure for food
-- ----------------------------
DROP TABLE IF EXISTS `food`;
CREATE TABLE `food` (
  `food_id` int(20) NOT NULL AUTO_INCREMENT COMMENT '食物ID',
  `food_price` int(20) NOT NULL DEFAULT '0' COMMENT '食物价格',
  `food_name` varchar(40) NOT NULL COMMENT '食物名字',
  `food_sale_count` int(20) DEFAULT '0' COMMENT '食物卖出数量',
  `food_create_time` date DEFAULT NULL COMMENT '上线时间',
  `food_picture_url` varchar(255) DEFAULT NULL COMMENT '食物图片链接',
  `food_description` varchar(255) DEFAULT NULL COMMENT '食物的描述',
  `food_type_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`food_id`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of food
-- ----------------------------
INSERT INTO `food` VALUES ('1', '15', '刀削面', '0', '2018-02-01', '/waimai/food/702061d479bb49d3a0678b3eecdb8ced.jpg', '刀削面', '1');
INSERT INTO `food` VALUES ('2', '20', '米线', '0', '2018-02-01', '/waimai/food/9b6f4c067bee4b8e97c23c028021d481.jpg', '米线', '1');
INSERT INTO `food` VALUES ('3', '6', '肉夹馍', '0', '2018-02-01', '/waimai/food/34564c61170d426d9988b420f0af1740.jpg', '肉夹馍', '1');
INSERT INTO `food` VALUES ('4', '30', '肉炒饭', '0', '2018-02-01', '/waimai/food/089442dd83b341f4a9eafecb4342bfa2.jpg', '肉炒饭', '1');
INSERT INTO `food` VALUES ('5', '18', '脆皮烤肉拌饭', '0', '2018-02-01', '/waimai/food/e50d67be3c56407ab755692e037c082d.jpg', '脆皮烤肉拌饭', '1');
INSERT INTO `food` VALUES ('6', '17', '饺子', '0', '2018-02-01', '/waimai/food/dc588a6325a542f79bdcdea5e474e4cd.jpg', '饺子', '1');
INSERT INTO `food` VALUES ('7', '40', '麻辣香锅', '0', '2018-02-01', '/waimai/food/709bcf27ffc14b8f87bc421ba08c9ab7.jpg', '麻辣香锅', '1');
INSERT INTO `food` VALUES ('8', '21', '黄焖鸡', '0', '2018-02-01', '/waimai/food/40267d3ddf28406a9f6e8453b72510a6.jpg', '黄焖鸡', '1');
INSERT INTO `food` VALUES ('17', '123', '123', '0', '2018-02-01', '/waimai/food/eea9571f76994798a47a8d04f029df76.jpg', '123', '1');
INSERT INTO `food` VALUES ('18', '25', '麦当劳', '0', '2018-02-01', '/waimai/food/5fd88dd00b76429c8e81b86c369eb532.jpg', '麦当劳', '4');
INSERT INTO `food` VALUES ('19', '50', '李先生', '0', '2018-02-01', '/waimai/food/eb4fc97bd3b44d448ebff6e7f1797bd3.jpg', '李先生', '4');
INSERT INTO `food` VALUES ('20', '15', 'A+鸡', '0', '2018-02-01', '/waimai/food/7a815e2cdc2a4f728806261b767c2291.jpg', 'A+鸡', '4');
INSERT INTO `food` VALUES ('21', '40', '必胜客', '0', '2018-02-01', '/waimai/food/028a7ba992684369a34c08eb7574bb4c.jpg', '必胜客', '4');
INSERT INTO `food` VALUES ('22', '10', '庆丰包子', '0', '2018-02-01', '/waimai/food/f440d64ae32046658806798279a3ba6c.jpg', '庆丰包子', '2');
INSERT INTO `food` VALUES ('23', '13', '傻四肉饼', '0', '2018-02-01', '/waimai/food/24d58e2557914361bfcbe41c42b7d11d.jpg', '傻四肉饼', '2');
INSERT INTO `food` VALUES ('24', '21', '西安小厨', '0', '2018-02-01', '/waimai/food/1b111b282eba435bab6769874144b589.jpg', '西安小厨', '2');
INSERT INTO `food` VALUES ('25', '30', '重庆鸡公煲', '0', '2018-02-01', '/waimai/food/9d5a4b082fd04307ab9e8245c3f4e442.jpg', '重庆鸡公煲', '2');
INSERT INTO `food` VALUES ('26', '5', 'CoCo都可', '0', '2018-02-01', '/waimai/food/d747170a6c874ae5bcdafb70b81079a0.jpg', 'CoCo都可', '3');
INSERT INTO `food` VALUES ('27', '10', '茶物语', '0', '2018-02-01', '/waimai/food/3c87f04e746f4ae482f4ade29a07a644.jpg', '茶物语', '3');
INSERT INTO `food` VALUES ('28', '10', '快乐柠檬', '0', '2018-02-01', '/waimai/food/d3d3158f2bfe4b309a39e08c255308b9\r\n\r\n.jpg', '快乐柠檬', '3');
INSERT INTO `food` VALUES ('29', '30', '水果先生', '0', '2018-02-01', '/waimai/food/cdbcf8c50b8c48809440d6decac8183a.jpg', '水果先生', '3');

-- ----------------------------
-- Table structure for food_type
-- ----------------------------
DROP TABLE IF EXISTS `food_type`;
CREATE TABLE `food_type` (
  `food_type_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '食物类型',
  `food_type_name` varchar(40) NOT NULL,
  PRIMARY KEY (`food_type_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of food_type
-- ----------------------------
INSERT INTO `food_type` VALUES ('1', '美食');
INSERT INTO `food_type` VALUES ('2', '小吃');
INSERT INTO `food_type` VALUES ('3', '零食');
INSERT INTO `food_type` VALUES ('4', '饮品');
INSERT INTO `food_type` VALUES ('5', '123');

-- ----------------------------
-- Table structure for manager
-- ----------------------------
DROP TABLE IF EXISTS `manager`;
CREATE TABLE `manager` (
  `managerid` int(20) NOT NULL AUTO_INCREMENT,
  `managername` varchar(40) NOT NULL,
  `managerpassword` varchar(20) NOT NULL,
  PRIMARY KEY (`managerid`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of manager
-- ----------------------------
INSERT INTO `manager` VALUES ('1', 'root', '123456');
INSERT INTO `manager` VALUES ('2', 'admin', '123456');

-- ----------------------------
-- Table structure for news
-- ----------------------------
DROP TABLE IF EXISTS `news`;
CREATE TABLE `news` (
  `news_id` int(20) NOT NULL AUTO_INCREMENT,
  `news_type_id` int(20) DEFAULT NULL,
  `news_title` varchar(100) DEFAULT NULL,
  `news_url` varchar(255) DEFAULT NULL,
  `news_create_time` date DEFAULT NULL,
  `news_author_name` varchar(255) DEFAULT NULL,
  `news_text` text,
  PRIMARY KEY (`news_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of news
-- ----------------------------
INSERT INTO `news` VALUES ('1', '1', '123', null, '2018-02-03', '123123', '<p>\r\n	jf\r\n</p>\r\n<p>\r\n	fsfff\r\n</p>\r\n<p>\r\n	<br />\r\n</p>\r\n<p>\r\n	fdf地方\r\n</p>\r\n<p>\r\n	<br />\r\n</p>');
INSERT INTO `news` VALUES ('2', '1', '吴晓波是个大帅哥', null, '2018-02-03', '吴晓波', '地方大幅度发大幅度');
INSERT INTO `news` VALUES ('3', '1', '黄焖鸡米饭', null, '2018-02-03', '吴晓波', '黄焖鸡米饭是最好吃的米饭&nbsp;&nbsp;&nbsp;&nbsp;');
INSERT INTO `news` VALUES ('4', '2', '黄焖鸡如何制作', null, '2018-02-03', '小诸葛', '<div class=\"recipeTip\" style=\"margin:0px;padding:0px;font-size:16px;font-family:&quot;background-color:#FFFFFF;\">\r\n	鸡本身有油，但放少量的油炒鸡不会腥\r\n</div>\r\n<div class=\"recipeTip mt16\" style=\"margin:16px 0px 0px;padding:0px;font-size:16px;font-family:&quot;background-color:#FFFFFF;\">\r\n	使用的厨具：不粘锅、平底锅、炒锅\r\n</div>\r\n<div class=\"recipeTip mt16\" style=\"margin:16px 0px 0px;padding:0px;font-size:16px;font-family:&quot;background-color:#FFFFFF;\">\r\n	所属分类：<span class=\"Apple-converted-space\">&nbsp;</span><a href=\"http://home.meishichina.com/recipe/lucai/\" target=\"_blank\">鲁菜</a>&nbsp;&nbsp;<span class=\"Apple-converted-space\">&nbsp;</span><a href=\"http://home.meishichina.com/recipe/laoren/\" target=\"_blank\">老人</a>&nbsp;&nbsp;<span class=\"Apple-converted-space\">&nbsp;</span><a href=\"http://home.meishichina.com/recipe/recai/\" target=\"_blank\">热菜</a>&nbsp;&nbsp;<span class=\"Apple-converted-space\">&nbsp;</span><a href=\"http://home.meishichina.com/recipe/qiuji/\" target=\"_blank\">秋季食谱</a>&nbsp;&nbsp;<span class=\"Apple-converted-space\">&nbsp;</span><a href=\"http://home.meishichina.com/recipe/yankecai/\" target=\"_blank\">宴客菜</a>&nbsp;&nbsp;<span class=\"Apple-converted-space\">&nbsp;</span><a href=\"http://home.meishichina.com/recipe/wucan/\" target=\"_blank\">午餐</a>&nbsp;&nbsp;\r\n</div>');
INSERT INTO `news` VALUES ('5', '1', '河南美食', null, '2018-02-03', '河南人', '<div class=\"para\" style=\"font-size:14px;color:#333333;font-family:arial, 宋体, sans-serif;background-color:#FFFFFF;\">\r\n	河南省有着悠久的文化历史，不仅为我们留下了丰富的文物古迹，还给我们留下了灿烂的文化财富--豫菜. 豫菜的烹调方法，共有50余种。扒、烧、炸、熘、爆、炒、炝别有特色，葱椒炝和凹，独树一帜。其中扒菜更为独到，素有\"扒菜不勾芡，汤汁自来黏\"的美称。另外，河南爆菜时多用武火，热锅凉油，操作迅速，质地脆嫩，汁色乳白。\"糖醋熘黄河鲤鱼焙面\"、\"炒三不粘\"、\"桂花皮丝\"、\"糖醋鲤鱼\"等，这些历史悠久的豫菜名菜，至今仍名扬遐迩，为中外人士所赞扬。\r\n</div>\r\n<div class=\"anchor-list\" style=\"color:#333333;font-family:arial, 宋体, sans-serif;background-color:#FFFFFF;\">\r\n	<a name=\"1\"></a><a name=\"sub20655055_1\"></a><a name=\"河南特色美食\"></a>\r\n</div>\r\n<div class=\"para-title level-2\" style=\"font-size:22px;font-family:&quot;margin:35px 0px 15px -30px;background:url(&quot;color:#333333;\">\r\n	<h2 class=\"title-text\" style=\"font-size:22px;color:#000000;font-weight:400;\">\r\n		河南特色美食\r\n	</h2>\r\n<a class=\"edit-icon j-edit-link\"><span class=\"cmn-icon wiki-lemma-icons wiki-lemma-icons_edit-lemma\" style=\"font-family:baikeFont_layout;line-height:1;vertical-align:text-bottom;color:#AAAAAA;\"></span>编辑</a>\r\n</div>\r\n<div class=\"para\" style=\"font-size:14px;color:#333333;font-family:arial, 宋体, sans-serif;background-color:#FFFFFF;\">\r\n	河南菜又名豫菜,历史悠久,风味独特,早在宋代,就已形成色香味俱全的宫廷风味菜肴。是中国传统美食之一。主要特点是：选料精良、讲究配菜、汤鲜香浓、色形俱佳。河南美食历史悠久口味独特，河南特色美食虽种类多样。河南特色小吃一直是来河南游客心中的那抹回不去的记忆，河南的小吃虽然不贵，但是却有着回味无穷的能量，河南特色小吃还等什么，来河南一定要来品尝这等美食哦！\r\n</div>');

-- ----------------------------
-- Table structure for news_type
-- ----------------------------
DROP TABLE IF EXISTS `news_type`;
CREATE TABLE `news_type` (
  `news_type_id` int(20) NOT NULL AUTO_INCREMENT,
  `news_type_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`news_type_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of news_type
-- ----------------------------
INSERT INTO `news_type` VALUES ('1', '美食推荐');
INSERT INTO `news_type` VALUES ('2', '美食制作');
INSERT INTO `news_type` VALUES ('3', '小吃大全');
INSERT INTO `news_type` VALUES ('4', '食料百科');
INSERT INTO `news_type` VALUES ('6', '123');

-- ----------------------------
-- Table structure for speed
-- ----------------------------
DROP TABLE IF EXISTS `speed`;
CREATE TABLE `speed` (
  `speed_id` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`speed_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of speed
-- ----------------------------

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `userid` int(20) NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `username` varchar(30) NOT NULL,
  `userpassword` varchar(30) NOT NULL DEFAULT '666666',
  `userregistertime` date DEFAULT NULL COMMENT '用户注册时间',
  `userphone` varchar(20) DEFAULT NULL COMMENT '用户手机号码',
  `useremail` varchar(30) DEFAULT NULL COMMENT '用户邮箱',
  `userheadurl` varchar(40) DEFAULT NULL COMMENT '用户头像保存地址',
  PRIMARY KEY (`userid`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'admin', 'admin', '2018-01-31', '18339290851', '18339290851', '');
INSERT INTO `user` VALUES ('2', 'bobo', '123456', '2018-01-30', '17600148757', '17600148757@163.com', null);
INSERT INTO `user` VALUES ('3', 'test1', 'test1', '2018-01-31', '18339290851', '18339290851@qq.com', null);
INSERT INTO `user` VALUES ('4', 'test2', 'test2', '2018-01-31', '18339290851', '18339290851@qq.com', null);
INSERT INTO `user` VALUES ('5', 'test3', 'test3', '2018-01-31', '18339290851', '18339290851@qq.com', null);
INSERT INTO `user` VALUES ('6', 'test4', 'test4', '2018-01-31', '18339290851', '18339290851@qq.com', null);
INSERT INTO `user` VALUES ('7', 'test5', 'test5', '2018-01-31', '18339290851', '18339290851@qq.com', null);
