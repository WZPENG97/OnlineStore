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

 Date: 21/06/2018 00:31:46
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
INSERT INTO `indent` VALUES ('1112', 'admin', '1', '面膜', '1', NULL, NULL, '已完成');
INSERT INTO `indent` VALUES ('14F78AC3BB64443DB17838DC09E27EE1', 'admin', '1', '面膜', '13', '949.0', '2018-06-14 23:01:58', '配送中');
INSERT INTO `indent` VALUES ('3833F8A929214348AF032C682174C705', 'admin', '1', '面膜', '22', '1606.0', '2018-06-15 10:25:39', '已完成');
INSERT INTO `indent` VALUES ('4537ECF142FC4E83A7570B6C2893836B', 'admin', '1', '面膜', '23', '1679.0', '2018-06-14 23:25:44', '已完成');
INSERT INTO `indent` VALUES ('6E520E67CAED476F8C34F225C33845A5', 'admin', '1', '面膜', '23', '1679.0', '2018-06-14 23:25:51', '配送中');
INSERT INTO `indent` VALUES ('A7C538C2BF1C4104B2CD54A93CBB8B39', 'admin', '1', '面膜', '23', '1679.0', '2018-06-14 23:26:14', '待发货');
INSERT INTO `indent` VALUES ('B9ADE00157EF4CDBB99B9ED20AFBE0DD', 'admin', '10', '追风筝的人', '23', '1175.2999', '2018-06-14 23:26:10', '配送中');
INSERT INTO `indent` VALUES ('C30C4E32BD144DEFB63C426993F89378', 'admin', '1', '面膜', '23', '1679.0', '2018-06-14 23:24:17', '待发货');
INSERT INTO `indent` VALUES ('DC6DB18223074B00B327AC53B86CD743', 'admin', '1', '面膜', '23', '1679.0', '2018-06-14 23:32:13', '待发货');

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
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
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
INSERT INTO `product` VALUES ('11', 'iPhone X', '7290.00', '4', '数码产品', '200', 'Apple/苹果 iPhone X 苹果8x 美版日版全网通移动联通电信4G手机\\n¥4970.00-7290.00\\n品牌：Apple/苹果\\nApple型号：iPhone X\\n网络网络 ：电信4G 移动4G 移动4G \\n网类型：无需合约版\\r\\n后置摄像头: 双1200万\\n存储容量：64GB 256GB\\n', 'images/iPhone X.jpg');
INSERT INTO `product` VALUES ('12', 'VR眼镜', '238.00', '4', '数码产品', '200', 'VR眼镜手机虚拟现实头盔3D游戏眼睛头戴式家庭影院电影RV苹果安卓\\n\n¥238.00\\n\n兼容平台: ANDROID Windows Mobile iOS\\n\n品牌: RGKNSE/隆客色\\n\n型号: VR CASE 6\\n\n生产企业: 隆客色\\n\nVR眼镜手机虚拟现实头盔3D游戏眼睛头戴式家庭影院电影RV苹果安卓\\n\n¥238.00\\n\n兼容平台: ANDROID Windows Mobile iOS\\n\n品牌: RGKNSE/隆客色\\n\n型号: VR CASE 6\\n\n生产企业: 隆客色\\n\nVR眼', 'images/VR眼镜.jpg');
INSERT INTO `product` VALUES ('13', '单反相机', '6199.00', '4', '数码产品', '200', 'Canon/佳能EOS 200D 入门级 单反相机数码高清旅游\\n¥3298.00-6199.00\\n接口类型: 佳能口   单反级别: 入门级\\n屏幕尺寸: 3英寸及以上 像素: 2416万  储存介质: SD卡\\n单反画幅: APS-C画幅   感光元件类型: CMOS  对焦点数: 9点\\n  重量: 401g(含)-500g(含)  取景器类型: 光学取景器\\n传感器尺寸: 23.6mmx15.6mm \\n 品牌: Canon/佳能  佳能单反系列: EOS 200D（18-55mm STM)\\n', 'images/单反相机.jpg');
INSERT INTO `product` VALUES ('14', '华为P10', '4299.00', '4', '数码产品', '200', 'Huawei/华为 P10 Plus6G+128G曜石黑全网通4G手机双卡双待特价\\n¥3599.00-4299.00\\n品牌: Huawei/华为 华为型号: P10 Plus\\n屏幕分辨率: WQHD 2560 x 1440\\n网络类型: 4G+全网通 网络模式: 双卡双待单通\\nCPU品牌 麒麟960 核心数: 八核 + 微智核i6\\n运行内存RAM: 6GB  存储容量: 128GB\\n后置摄像头: 2000万像素（黑白）+1200万像素（彩色）摄像头类型: 三摄像头（后双）\\n', 'images/华为P10.jpg');
INSERT INTO `product` VALUES ('15', '键盘', '235.00', '4', '数码产品', '200', 'RK复古朋克机械键盘RGB吃鸡电竞游戏有线台式笔记本电脑青轴黑轴\\n¥ 145.00-235.00\\n产品名称：RK S108   保修期: 12个月\\n品牌: RK型号: S108\\n连接方式: 有线 接口类型: USB\\n有无手托: 有\\n', 'images/键盘.jpg');
INSERT INTO `product` VALUES ('16', '小米6', '3130.00', '4', '数码产品', '200', 'Xiaomi/小米 小米手机6 全网通4G手机128G黑色亮现货全新正品分期\\n¥3130.00\\n品牌: Xiaomi/小米 型号: 小米手机6\\n分辨率: 1920*1080\\n网络类型: 4G+全网通 网络模式: 双卡多模\\nCPU：高通骁龙835 核心数: 八核2.45GHz\\n运行内存RAM: 6GB 存储容量: 128GB\\n后置摄像头: 双1200万 摄像头类型: 三摄像头（后双）\\n', 'images/小米6.jpg');
INSERT INTO `product` VALUES ('17', '短袖T恤', '49.00', '5', '衣服配饰', '200', '短袖t恤男夏装大码宽松情侣卡通印花韩版潮流半袖打底衫\\n¥ 49.00\\n厚薄: 常规材质成分: 棉100%货号: DT21415\\n', 'images/短袖T恤.jpg');
INSERT INTO `product` VALUES ('18', '帽子', '32.00', '5', '衣服配饰', '200', '礼帽男 大帽檐复古上海滩帽子新郞帽子表演帽 黑色英伦爵士帽子男\\n¥ 29.00-32.00\\n品牌名称：SiCooZoe/随潮主义\\n适用对象: 中年 情侣 老年 青年款\\n适用年龄: 20-24周岁 25-29周岁 30-34周岁 \\n', 'images/帽子.jpg');
INSERT INTO `product` VALUES ('19', '牛仔裤', '118.00', '5', '衣服配饰', '200', '新款 POLO品牌原单剪标特价清货薄款修身弹力牛仔蓝牛仔裤 男裤子\\n¥118.00\\n材质成分: 棉95% 其他5%裤长: 长裤货号: 001\\n牛仔面料: 常规牛仔布 厚薄: 薄款款式版型: 合体直筒\\n', 'images/牛仔裤.jpg');
INSERT INTO `product` VALUES ('20', '杠铃', '1335.00', '6', '运动用品', '200', '烤漆杠铃 全铁杠铃 奥杆大孔专业级别 家用健身套装组合\\n¥ 260.00-1335.00\\n品牌名称：KING BOX/黑金刚\\n货号: 大孔烤漆杠铃\\n按健身效果选择: 健身综合练习\\n', 'images/杠铃.jpg');
INSERT INTO `product` VALUES ('21', '篮球', '79.00', '6', '运动用品', '200', 'Molten摩腾篮球BGW5X青少年学生篮球5号PU耐磨室内外通用\\n¥30.00-79.00\\n品牌: DHS/红双喜\\n篮球规格: 七号篮球(标准球) 材质: PU\\n', 'images/篮球.jpg');
INSERT INTO `product` VALUES ('22', '足球', '88.00', '6', '运动用品', '200', 'star世达足球4号儿童小学生足球训练比赛用耐磨SB6404C\\n¥46.00-88.00\\n品牌: Star/世达 货号: SB5164\\n足球尺寸: 5号球(正规11人制用)\\n材质: PU  足球缝线: 机缝足球\\n', 'images/足球.jpg');
INSERT INTO `product` VALUES ('23', '坦克模型', '56.00', '7', '游戏周边', '200', '军事战车T55合金坦克模型仿真金属儿童玩具车59式坦克世界收藏品\\n¥56.00\\n型号: T-55比例: 1:43 玩具类型: 金属玩具\\n模型类型: 成品\\n', 'images/坦克模型.jpg');
INSERT INTO `product` VALUES ('24', '游戏光盘', '290.00', '7', '游戏周边', '200', 'PS4游戏 使命召唤14 二战 cod14 中文正版 全新现货\\n¥ 290.00\\n生产企业: 索尼游戏\\n主机类型: 索尼PS4\\n游戏名称: 使命召唤14\\n游戏类型: STG射击游戏\\n语种分类: 繁体中文\\n游戏版本: 标准版（盒装）', 'images/游戏光盘.jpg');
INSERT INTO `product` VALUES ('25', '游戏手柄', '288.00', '7', '游戏周边', '200', 'handjoy游戏手柄拳皇命运手游qq飞车刺激战场苹果手机王者送荣耀\\n¥ 288.00\\n产品名称：HANDJOY T-MAX触屏手柄\\n手柄特性: 无震动', 'images/游戏手柄.jpg');
INSERT INTO `product` VALUES ('4', '电吹风', '75.00', '2', '生活用品', '200', '电吹风机家用大功率发廊理发店专用6000吃风机冷热风吹风筒不伤发\\n¥38.00-75.00\\n电吹风品牌: 飞尔特 型号: FL-8138\\n电吹风最大功率: 2000W以上\\n电吹风档位: 5档以上\\n电吹风风嘴样式: 专业集风嘴大风罩\\n功能: 负离子 细微水离子 恒温 冷热风 水离子 胶原蛋白\\n适用人群: 男女通用\\n', 'images/电吹风.jpg');
INSERT INTO `product` VALUES ('6', '电风扇', '119.00', '2', '生活用品', '200', '格力电风扇 转页扇迷你扇小台式学生宿舍鸿运扇 静音家用\\n¥88.00-119.00\\n电扇类别:台式转页扇能\\n电风扇品牌: 格力\\n型号: KYT-30-1\\n控制方式: 机械式\\n送风类型: 转页\\n电源方式: 交流电\\n', 'images/电风扇.jpg');
INSERT INTO `product` VALUES ('8', '打印机', '5291.00', '3', '书籍办公', '200', '佳能ir2204n复印机 A3黑白激光打印机 打印机扫描一体机2002G升级 网络打印复印 wifi 选配输稿器\\n¥ 5291.00\\n品牌名称：Canon/佳能\\n功能: 打印/复印/扫描 \\n最大复印尺寸: A3\\n连续复印张数: 1张(含)-99张(含) \\n', 'images/打印机.jpg');
INSERT INTO `product` VALUES ('9', '时间简史', '31.10', '3', '书籍办公', '200', '时间简史（插图本）全彩 宇宙学家史蒂芬霍金著作 时间简史霍金 中学生科普畅销书\\n ¥ 31.10\\n作  者:(美)史蒂芬·霍金 (Stephen Hawking) 著;许明贤,吴忠超 译\\n出 版 社:湖南科学技术出版社\\n页  数:239\\n装 帧:平装\\n', 'images/时间简史.jpg');

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
INSERT INTO `shopping_cart` VALUES ('admin', '12', '眼镜', '3', '23');

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
INSERT INTO `user` VALUES ('admin', '1234567', 'male', 'abcd@qq.com', '13584562563', '456845', 'test address', '10001.0');
INSERT INTO `user` VALUES ('adminasdf', '123456', 'male', 'wzpeng97@gmail.com', NULL, NULL, '5456465', NULL);
INSERT INTO `user` VALUES ('asdfasfasdf', 'asdfasdfas', 'male', 'wzpeng97@gmail.com', '12458563254', '123542', 'asdfasf', NULL);
INSERT INTO `user` VALUES ('asdfsadf', 'asdfasdfasdf', 'male', 'wzpeng97@gmail.com', '12354648562', '135455', '5465', NULL);

SET FOREIGN_KEY_CHECKS = 1;
