

SET FOREIGN_KEY_CHECKS=0;

DROP TABLE IF EXISTS `department`;
CREATE TABLE `department` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `departmentName` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;



insert into department 
values (1001,'軟體部'),
(1002,'硬體部'),
(1003,'雲端部'),
(1004,'資料部'),
(1005,'會計部');

