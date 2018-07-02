/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 80011
 Source Host           : localhost:3306
 Source Schema         : shopping

 Target Server Type    : MySQL
 Target Server Version : 80011
 File Encoding         : 65001

 Date: 02/07/2018 10:37:12
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for adminform
-- ----------------------------
DROP TABLE IF EXISTS `adminform`;
CREATE TABLE `adminform`  (
  `adminname` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `apassword` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`adminname`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of adminform
-- ----------------------------
INSERT INTO `adminform` VALUES ('admin', '123456');

-- ----------------------------
-- Table structure for comment
-- ----------------------------
DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment`  (
  `pid` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `username` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `comment` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `time` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  INDEX `username`(`username`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of comment
-- ----------------------------
INSERT INTO `comment` VALUES ('1', 'admin', 'adsf', '2018-06-21 18:53:04');
INSERT INTO `comment` VALUES ('1', 'admin', 'sdf', '2018-06-21 18:53:20');
INSERT INTO `comment` VALUES ('1', 'admin', 'test', '2018-06-21 18:53:46');
INSERT INTO `comment` VALUES ('1', 'admin', 'test', '2018-06-21 18:55:14');
INSERT INTO `comment` VALUES ('1', 'admin', 'test', '2018-06-21 18:55:14');
INSERT INTO `comment` VALUES ('1', 'admin', 'test', '2018-06-21 18:56:57');
INSERT INTO `comment` VALUES ('11', 'admin', '1111111', '2018-06-21 19:03:41');
INSERT INTO `comment` VALUES ('11', 'admin', '1111111', '2018-06-21 19:03:41');
INSERT INTO `comment` VALUES ('11', 'admin', '1111111', '2018-06-21 19:44:53');
INSERT INTO `comment` VALUES ('11', 'admin', '1111111', '2018-06-21 19:45:05');
INSERT INTO `comment` VALUES ('11', 'admin', '1111111', '2018-06-21 19:46:54');
INSERT INTO `comment` VALUES ('11', 'admin', '1111111', '2018-06-21 19:46:59');
INSERT INTO `comment` VALUES ('11', 'admin', '1111111', '2018-06-21 19:46:59');
INSERT INTO `comment` VALUES ('11', 'admin', '1111111', '2018-06-21 19:48:12');
INSERT INTO `comment` VALUES ('11', 'admin', '1111111', '2018-06-21 19:48:12');
INSERT INTO `comment` VALUES ('1', 'admin', 'etst', '2018-06-21 19:48:50');
INSERT INTO `comment` VALUES ('1', 'admin', 'etst', '2018-06-21 19:48:50');
INSERT INTO `comment` VALUES ('1', 'admin', '11111', '2018-06-21 19:49:02');
INSERT INTO `comment` VALUES ('1', 'admin', 'qqqqqqqqqqqqq', '2018-06-21 19:50:46');
INSERT INTO `comment` VALUES ('1', 'admin', 'qqqqqqqqqqqqq', '2018-06-21 19:51:19');
INSERT INTO `comment` VALUES ('1', 'admin', 'qqqqqqqqq', '2018-06-21 19:51:25');
INSERT INTO `comment` VALUES ('1', 'admin', 'qqqqqqqqqq', '2018-06-21 19:52:18');
INSERT INTO `comment` VALUES ('19', 'admin', '好好好', '2018-06-22 15:01:59');

-- ----------------------------
-- Table structure for indent
-- ----------------------------
DROP TABLE IF EXISTS `indent`;
CREATE TABLE `indent`  (
  `indentid` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `username` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `pid` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `pname` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `counts` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `cprice` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `ctime` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `indentsta` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`indentid`) USING BTREE,
  INDEX `username`(`username`) USING BTREE,
  INDEX `pid`(`pid`) USING BTREE,
  CONSTRAINT `indent_ibfk_1` FOREIGN KEY (`username`) REFERENCES `user` (`username`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `indent_ibfk_2` FOREIGN KEY (`pid`) REFERENCES `product` (`pid`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of indent
-- ----------------------------
INSERT INTO `indent` VALUES ('00F48F5E77BF4B938F6A7CC9061C4FF1', 'admin', '17', '短袖T恤', '23', '1127.0', '2018-06-21 21:48:03', '待发货');
INSERT INTO `indent` VALUES ('0B9DE51996FE467E9BC526601BE65D7C', 'admin', '16', '小米6', '6', '18780.0', '2018-06-21 21:51:08', '待发货');
INSERT INTO `indent` VALUES ('1112', 'admin', '1', '面膜', '1', NULL, NULL, '未评论');
INSERT INTO `indent` VALUES ('14F78AC3BB64443DB17838DC09E27EE1', 'admin', '1', '面膜', '13', '949.0', '2018-06-14 23:01:58', '配送中');
INSERT INTO `indent` VALUES ('22881160058848558FFD424B7062977A', 'admin', '18', '帽子', '23', '736.0', '2018-06-21 21:48:27', '待发货');
INSERT INTO `indent` VALUES ('3833F8A929214348AF032C682174C705', 'admin', '1', '面膜', '22', '1606.0', '2018-06-15 10:25:39', '未评论');
INSERT INTO `indent` VALUES ('4537ECF142FC4E83A7570B6C2893836B', 'admin', '1', '面膜', '23', '1679.0', '2018-06-14 23:25:44', '已完成');
INSERT INTO `indent` VALUES ('4AA9374D063A4CD386EEEE578744EE35', 'admin', '19', '牛仔裤', '3', '354.0', '2018-06-22 12:16:22', '未评论');
INSERT INTO `indent` VALUES ('5BB1A56DA05C43F0B583B5973E5B7988', 'admin', '16', '小米6', '3', '9390.0', '2018-06-22 10:59:06', '待发货');
INSERT INTO `indent` VALUES ('609B4EAE8E3945158220E3E699C8B866', 'admin', '17', '短袖T恤', '4', '196.0', '2018-06-21 21:50:01', '待发货');
INSERT INTO `indent` VALUES ('625CC9D4884840BFBE7A31CCE17D8C65', 'admin', '11', 'iPhone X', '3', '21870.0', '2018-06-21 23:35:08', '待发货');
INSERT INTO `indent` VALUES ('631CEDD0896D4284AB52CC0147D342D5', 'admin', '18', '帽子', '3', '96.0', '2018-06-21 23:33:55', '待发货');
INSERT INTO `indent` VALUES ('6AA6C00F950B4F1996BEBCC5782EB616', 'admin', '17', '短袖T恤', '1', '49.0', '2018-06-22 14:58:17', '待发货');
INSERT INTO `indent` VALUES ('6D1EED2924664343B6ECD7DD57932F78', 'admin', '17', '短袖T恤', '4', '196.0', '2018-06-22 12:16:21', '待发货');
INSERT INTO `indent` VALUES ('6E520E67CAED476F8C34F225C33845A5', 'admin', '1', '面膜', '23', '1679.0', '2018-06-14 23:25:51', '配送中');
INSERT INTO `indent` VALUES ('75D24BFD184B479D9D925E1015D0D4AD', 'admin', '19', '牛仔裤', '1', '118.0', '2018-06-22 12:15:07', '待发货');
INSERT INTO `indent` VALUES ('768C59A4FBE34D4F896C4F5DEBE01529', 'admin', '18', '帽子', '1', '32.0', '2018-06-22 12:15:07', '待发货');
INSERT INTO `indent` VALUES ('86BEA21FB6FA494C943A28EA4AD74855', 'admin', '17', '短袖T恤', '1', '49.0', '2018-06-21 21:38:57', '待发货');
INSERT INTO `indent` VALUES ('941E403812354FC78CC464B784A327D6', 'admin', '12', 'VR眼镜', '1', '238.0', '2018-06-21 23:45:37', '待发货');
INSERT INTO `indent` VALUES ('A4200CCD70A3463B8D098F5986C6DB14', 'admin', '12', 'VR眼镜', '3', '714.0', '2018-06-22 14:59:26', '待发货');
INSERT INTO `indent` VALUES ('A7C538C2BF1C4104B2CD54A93CBB8B39', 'admin', '1', '面膜', '23', '1679.0', '2018-06-14 23:26:14', '待发货');
INSERT INTO `indent` VALUES ('B9ADE00157EF4CDBB99B9ED20AFBE0DD', 'admin', '10', '追风筝的人', '23', '1175.2999', '2018-06-14 23:26:10', '配送中');
INSERT INTO `indent` VALUES ('C30C4E32BD144DEFB63C426993F89378', 'admin', '1', '面膜', '23', '1679.0', '2018-06-14 23:24:17', '待发货');
INSERT INTO `indent` VALUES ('C9C6417EE974480FBA954C5AF2BE40A5', 'admin', '19', '牛仔裤', '1', '118.0', '2018-06-22 14:59:27', '已完成');
INSERT INTO `indent` VALUES ('D33C6AA3F1314D23B6D098471904AB8C', 'admin', '19', '牛仔裤', '8', '944.0', '2018-06-22 10:59:06', '待发货');
INSERT INTO `indent` VALUES ('DC6DB18223074B00B327AC53B86CD743', 'admin', '1', '面膜', '23', '1679.0', '2018-06-14 23:32:13', '待发货');
INSERT INTO `indent` VALUES ('E52894C94FA9480DB921F4E341C02075', 'admin', '12', 'VR眼镜', '23', '5474.0', '2018-06-21 21:49:38', '待发货');
INSERT INTO `indent` VALUES ('ED8BD6EAE8E147928369066E76EBA83C', 'admin', '12', 'VR眼镜', '1', '238.0', '2018-06-22 12:15:24', '待发货');
INSERT INTO `indent` VALUES ('EE239A7E938D4E41A9514EE3FD4BB947', 'admin', '17', '短袖T恤', '4', '196.0', '2018-06-21 21:43:01', '待发货');
INSERT INTO `indent` VALUES ('FD638FE503ED4846888BC954E77DCA99', 'admin', '18', '帽子', '3', '96.0', '2018-06-21 23:34:13', '待发货');

-- ----------------------------
-- Table structure for pclass
-- ----------------------------
DROP TABLE IF EXISTS `pclass`;
CREATE TABLE `pclass`  (
  `cid` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `cname` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`cid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of pclass
-- ----------------------------
INSERT INTO `pclass` VALUES ('1', '化妆用品\r\n');
INSERT INTO `pclass` VALUES ('2', '生活用品');
INSERT INTO `pclass` VALUES ('3', '书籍办公');
INSERT INTO `pclass` VALUES ('4', '数码产品');
INSERT INTO `pclass` VALUES ('5', '衣服配饰');
INSERT INTO `pclass` VALUES ('6', '运动用品');
INSERT INTO `pclass` VALUES ('7', '游戏周边');

-- ----------------------------
-- Table structure for product
-- ----------------------------
DROP TABLE IF EXISTS `product`;
CREATE TABLE `product`  (
  `pid` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `pname` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `price` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `cid` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `cname` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `store` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `description` varchar(10000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `pimage` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`pid`) USING BTREE,
  INDEX `cid`(`cid`) USING BTREE,
  CONSTRAINT `product_ibfk_1` FOREIGN KEY (`cid`) REFERENCES `pclass` (`cid`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of product
-- ----------------------------
INSERT INTO `product` VALUES ('1', '面膜', '73.00', '1', '化妆用品', '200', 'test', 'images/面膜.jpg');
INSERT INTO `product` VALUES ('10', '追风筝的人', '51.895', '3', '书籍办公', '2000', '追风筝的人 胡塞尼著 中文小说畅销文艺正版书籍 两千万读者口耳相传\r\n出版社名称: 上海人民出版社\r\n出版时间: 2013年06月\r\n作者: 卡勒德.胡赛尼\r\n作者地区: 美国\r\n译者: 李继宏\r\n开本: 32本\r\ntest: test', 'images/追风筝的人.jpg');
INSERT INTO `product` VALUES ('11', 'iPhone X', '7290.00', '4', '数码产品', '197', 'Apple/苹果 iPhone X 苹果8x 美版日版全网通移动联通电信4G手机，¥4970.00-7290.00，品牌：Apple/苹果，Apple型号：iPhone X，网络网络 ：电信4G 移动4G 移动4G ，网类型：无需合约版\\r，后置摄像头: 双1200万，存储容量：64GB 256GB，', 'images/iPhone X.jpg');
INSERT INTO `product` VALUES ('12', 'VR眼镜', '238.00', '4', '数码产品', '172', 'VR眼镜手机虚拟现实头盔3D游戏眼睛头戴式家庭影院电影RV苹果安卓，\n¥238.00，\n兼容平台: ANDROID Windows Mobile iOS，\n品牌: RGKNSE/隆客色，\n型号: VR CASE 6，\n生产企业: 隆客色，\nVR眼镜手机虚拟现实头盔3D游戏眼睛头戴式家庭影院电影RV苹果安卓，\n¥238.00，\n兼容平台: ANDROID Windows Mobile iOS，\n品牌: RGKNSE/隆客色，\n型号: VR CASE 6，\n生产企业: 隆客色，\nVR眼', 'images/VR眼镜.jpg');
INSERT INTO `product` VALUES ('13', '单反相机', '6199.00', '4', '数码产品', '200', 'Canon/佳能EOS 200D 入门级 单反相机数码高清旅游，¥3298.00-6199.00，接口类型: 佳能口   单反级别: 入门级，屏幕尺寸: 3英寸及以上 像素: 2416万  储存介质: SD卡，单反画幅: APS-C画幅   感光元件类型: CMOS  对焦点数: 9点，  重量: 401g(含)-500g(含)  取景器类型: 光学取景器，传感器尺寸: 23.6mmx15.6mm ， 品牌: Canon/佳能  佳能单反系列: EOS 200D（18-55mm STM)，', 'images/单反相机.jpg');
INSERT INTO `product` VALUES ('14', '华为P10', '4299.00', '4', '数码产品', '200', 'Huawei/华为 P10 Plus6G+128G曜石黑全网通4G手机双卡双待特价，¥3599.00-4299.00，品牌: Huawei/华为 华为型号: P10 Plus，屏幕分辨率: WQHD 2560 x 1440，网络类型: 4G+全网通 网络模式: 双卡双待单通，CPU品牌 麒麟960 核心数: 八核 + 微智核i6，运行内存RAM: 6GB  存储容量: 128GB，后置摄像头: 2000万像素（黑白）+1200万像素（彩色）摄像头类型: 三摄像头（后双）\r\n', 'images/华为P10.jpg');
INSERT INTO `product` VALUES ('1435221903', 'test', '234', '1', '化妆用品', '234', 'tet，特务', 'images/wallhaven-576244.jpg');
INSERT INTO `product` VALUES ('15', '键盘', '235.00', '4', '数码产品', '200', 'RK复古朋克机械键盘RGB吃鸡电竞游戏有线台式笔记本电脑青轴黑轴，¥ 145.00-235.00，产品名称：RK S108   保修期: 12个月，品牌: RK型号: S108，连接方式: 有线 接口类型: USB，有无手托: 有，', 'images/键盘.jpg');
INSERT INTO `product` VALUES ('16', '小米6', '3130.00', '4', '数码产品', '191', 'Xiaomi/小米 小米手机6 全网通4G手机128G黑色亮现货全新正品分期，¥3130.00，品牌: Xiaomi/小米 型号: 小米手机6，分辨率: 1920*1080，网络类型: 4G+全网通 网络模式: 双卡多模，CPU：高通骁龙835 核心数: 八核2.45GHz，运行内存RAM: 6GB 存储容量: 128GB，后置摄像头: 双1200万 摄像头类型: 三摄像头（后双），', 'images/小米6.jpg');
INSERT INTO `product` VALUES ('17', '短袖T恤', '49.00', '5', '衣服配饰', '163', '短袖t恤男夏装大码宽松情侣卡通印花韩版潮流半袖打底衫，¥ 49.00，厚薄: 常规材质成分: 棉100%货号: DT21415，', 'images/短袖T恤.jpg');
INSERT INTO `product` VALUES ('18', '帽子', '32.00', '5', '衣服配饰', '199', '礼帽男 大帽檐复古上海滩帽子新郞帽子表演帽 黑色英伦爵士帽子男，¥ 29.00-32.00，品牌名称：SiCooZoe/随潮主义，适用对象: 中年 情侣 老年 青年款，适用年龄: 20-24周岁 25-29周岁 30-34周岁 ，', 'images/帽子.jpg');
INSERT INTO `product` VALUES ('19', '牛仔裤', '118.00', '5', '衣服配饰', '195', '新款 POLO品牌原单剪标特价清货薄款修身弹力牛仔蓝牛仔裤 男裤子，¥118.00，材质成分: 棉95% 其他5%裤长: 长裤货号: 001，牛仔面料: 常规牛仔布 厚薄: 薄款款式版型: 合体直筒，', 'images/牛仔裤.jpg');
INSERT INTO `product` VALUES ('20', '杠铃', '1335.00', '6', '运动用品', '200', '烤漆杠铃 全铁杠铃 奥杆大孔专业级别 家用健身套装组合，¥ 260.00-1335.00，品牌名称：KING BOX/黑金刚，货号: 大孔烤漆杠铃，按健身效果选择: 健身综合练习，', 'images/杠铃.jpg');
INSERT INTO `product` VALUES ('21', '篮球', '79.00', '6', '运动用品', '200', 'Molten摩腾篮球BGW5X青少年学生篮球5号PU耐磨室内外通用，¥30.00-79.00，品牌: DHS/红双喜，篮球规格: 七号篮球(标准球) 材质: PU，', 'images/篮球.jpg');
INSERT INTO `product` VALUES ('22', '足球', '88.00', '6', '运动用品', '200', 'star世达足球4号儿童小学生足球训练比赛用耐磨SB6404C，¥46.00-88.00，品牌: Star/世达 货号: SB5164，足球尺寸: 5号球(正规11人制用)，材质: PU  足球缝线: 机缝足球，', 'images/足球.jpg');
INSERT INTO `product` VALUES ('23', '坦克模型', '56.00', '7', '游戏周边', '200', '军事战车T55合金坦克模型仿真金属儿童玩具车59式坦克世界收藏品，¥56.00，型号: T-55比例: 1:43 玩具类型: 金属玩具，模型类型: 成品，', 'images/坦克模型.jpg');
INSERT INTO `product` VALUES ('24', '游戏光盘', '290.00', '7', '游戏周边', '200', 'PS4游戏 使命召唤14 二战 cod14 中文正版 全新现货，¥ 290.00，生产企业: 索尼游戏，主机类型: 索尼PS4，游戏名称: 使命召唤14，游戏类型: STG射击游戏，语种分类: 繁体中文，游戏版本: 标准版（盒装）', 'images/游戏光盘.jpg');
INSERT INTO `product` VALUES ('25', '游戏手柄', '288.00', '7', '游戏周边', '200', 'handjoy游戏手柄拳皇命运手游qq飞车刺激战场苹果手机王者送荣耀，¥ 288.00，产品名称：HANDJOY T-MAX触屏手柄，手柄特性: 无震动', 'images/游戏手柄.jpg');
INSERT INTO `product` VALUES ('4', '电吹风', '75.00', '2', '生活用品', '200', '电吹风机家用大功率发廊理发店专用6000吃风机冷热风吹风筒不伤发，¥38.00-75.00，电吹风品牌: 飞尔特 型号: FL-8138，电吹风最大功率: 2000W以上，电吹风档位: 5档以上，电吹风风嘴样式: 专业集风嘴大风罩，功能: 负离子 细微水离子 恒温 冷热风 水离子 胶原蛋白，适用人群: 男女通用，', 'images/电吹风.jpg');
INSERT INTO `product` VALUES ('6', '电风扇', '119.00', '2', '生活用品', '200', '格力电风扇 转页扇迷你扇小台式学生宿舍鸿运扇 静音家用，¥88.00-119.00，电扇类别:台式转页扇能，电风扇品牌: 格力，型号: KYT-30-1，控制方式: 机械式，送风类型: 转页，电源方式: 交流电，', 'images/电风扇.jpg');
INSERT INTO `product` VALUES ('8', '打印机', '5291.00', '3', '书籍办公', '200', '佳能ir2204n复印机 A3黑白激光打印机 打印机扫描一体机2002G升级 网络打印复印 wifi 选配输稿器，¥ 5291.00，品牌名称：Canon/佳能，功能: 打印/复印/扫描 ，最大复印尺寸: A3，连续复印张数: 1张(含)-99张(含) ，', 'images/打印机.jpg');
INSERT INTO `product` VALUES ('9', '时间简史', '31.10', '3', '书籍办公', '200', '时间简史（插图本）全彩 宇宙学家史蒂芬霍金著作 时间简史霍金 中学生科普畅销书， ¥ 31.10，作  者:(美)史蒂芬·霍金 (Stephen Hawking) 著;许明贤,吴忠超 译，出 版 社:湖南科学技术出版社，页  数:239，装 帧:平装，', 'images/时间简史.jpg');

-- ----------------------------
-- Table structure for shopping_cart
-- ----------------------------
DROP TABLE IF EXISTS `shopping_cart`;
CREATE TABLE `shopping_cart`  (
  `username` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `pid` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `pname` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `price` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `counts` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`username`, `pid`) USING BTREE,
  INDEX `pid`(`pid`) USING BTREE,
  CONSTRAINT `shopping_cart_ibfk_1` FOREIGN KEY (`username`) REFERENCES `user` (`username`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `shopping_cart_ibfk_2` FOREIGN KEY (`pid`) REFERENCES `product` (`pid`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of shopping_cart
-- ----------------------------
INSERT INTO `shopping_cart` VALUES ('admin', '13', '单反相机', '6199.00', '1');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `username` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `password` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `sex` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `email` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `telephone` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `postcode` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `address` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `money` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`username`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('admin', '1234567', 'male', 'abcd@qq.com', '13584562563', '456845', 'test address', '9.9947808E7');
INSERT INTO `user` VALUES ('adminasdf', '123456', 'male', 'wzpeng97@gmail.com', NULL, NULL, '5456465', NULL);
INSERT INTO `user` VALUES ('adminqq', '123456', 'male', 'wzpeng97@gmail.com', '12345678900', '141542', '湖南', '0');
INSERT INTO `user` VALUES ('adminqqq', '654321', 'male', 'wzpeng97@gmail.com', '12345678900', '414111', '华南', '2000.0');
INSERT INTO `user` VALUES ('asdfasfasdf', 'asdfasdfas', 'male', 'wzpeng97@gmail.com', '12458563254', '123542', 'asdfasf', NULL);
INSERT INTO `user` VALUES ('asdfsadf', 'asdfasdfasdf', 'male', 'wzpeng97@gmail.com', '12354648562', '135455', '5465', NULL);

SET FOREIGN_KEY_CHECKS = 1;
