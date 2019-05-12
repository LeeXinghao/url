# url

## 一. 实现功能及说明
### 基本要求
#### 1.输入长链接，可以生成短链接。    
* 用户输入一个长链接过后，系统进行加密计算生成对应的短链接，并将短链接和长链接的对应关系记录入库。每一条关系只记录一次，若用户输如的长链接在数据库中已经存在，直接返回对应的短链接，不在走加密的流程。
* 加密机制使用MD5，将长链接进行MD5加密过后，在进行位运算处理，匹配设置的字符集，最终生成对应的短链接
#### 2. 访问短链接，跳转长链接。
* 访问短链接时，在数据库中搜索对应的长链接。如果长链接存在直接重定向跳转，如果不存在返回相应的提示信息。
#### 3. 支持访问计数。
* 访问计数使用了缓存机制来实现，如果系统重启后数据会被清理。如果需要实现访问数据长久有效的话，可以将访问数据存入数据库。
#### 4. 支持自定义短链接，可以指定字符串作为短链接的key
* 用户可以自定任意字符串来为短链接的key，目前只在代码中实现。也可以实现将自定义key作为可配置参数来使用

### 扩展要求
#### 1. 支持配置功能，可以配置各种服务参数，比如：长度，字符集等，越多越好。
* 目前配置功能只实现生成短链接长度的配置。
* 字符集配置的思路为，建立多种生成短链接的字符集，根据配置使用不同的字符集类型来使用不同的字符集生成短链接。
#### 2. 要写测试代码完成测试，覆盖尽量多的case。
* 项目中所有功能基本都实现了测试覆盖。测试主要分为两方面一是对传入参入的测试，二是对功能实现的测试

## 三. 接口设计
* 长链接转短链接
```
    type : GET
    url : /link/api
    param : 
      String longUrl
```

* 访问短链接跳转长链接
```
   type : GET
    url : /link/{shortUrl}
    param : 
      String shortUrl
```

## 二. 框架
 主体框架 springboot+mybatis ；
 使用Maven进行管理；
 数据库为 Mysql。

## 三. 数据库
### 库表创建文件路径 ： File/link.sql
### 语句
```
SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for link
-- ----------------------------
DROP TABLE IF EXISTS `link`;
CREATE TABLE `link` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `shortUrl` text COLLATE utf8_unicode_ci NOT NULL,
  `longUrl` text COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

SET FOREIGN_KEY_CHECKS = 1;
```

## 四.改进
1. 可以对长链接的合法性进行校验。
