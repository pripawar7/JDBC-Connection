delimiter ;
DROP SCHEMA IF EXISTS `cs548_account`;
 
CREATE SCHEMA `cs548_account` ;

use `cs548_account`;

delimiter $$

CREATE TABLE `account` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `balanceDue`float DEFAULT NULL,
  `date` date DEFAULT NULL,
   PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=latin1$$

delimiter $$

