

SET FOREIGN_KEY_CHECKS=0;


DROP TABLE IF EXISTS `employee`;
CREATE TABLE `employee` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `lastName` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `gender` int(2) DEFAULT NULL,
  `d_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;



insert into employee 
values(null,'Bill','123@gmail.com',0,1001),
(null,'David','456@gmail.com',1,1002),
(null,'Peter','789@gmail.com',0,1003),
(null,'Paul','110@gmail.com',0,1004),
(null,'Lagain','119@gmail.com',1,1005);
