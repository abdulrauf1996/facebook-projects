-- phpMyAdmin SQL Dump
-- version 4.0.10deb1
-- http://www.phpmyadmin.net
--
-- Host: localhost:3306
-- Generation Time: Feb 21, 2017 at 04:26 AM
-- Server version: 5.5.52-0ubuntu0.14.04.1
-- PHP Version: 5.5.9-1ubuntu4.20

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `Rauf_Facebook`
--

-- --------------------------------------------------------

--
-- Table structure for table `tbl_check_in`
--

CREATE TABLE IF NOT EXISTS `tbl_check_in` (
  `pk_int_check_id` int(11) NOT NULL AUTO_INCREMENT,
  `location` varchar(500) NOT NULL,
  `status` varchar(500) NOT NULL,
  PRIMARY KEY (`pk_int_check_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=51 ;

--
-- Dumping data for table `tbl_check_in`
--

INSERT INTO `tbl_check_in` (`pk_int_check_id`, `location`, `status`) VALUES
(1, 'vadakaraaaaaa', 'qwertyuiopasdfghjklzxcvbnml'),
(2, 'Poovangal\nKozhikode, Kerala 673014\n', 'zvdhdjdjdd'),
(3, 'Poovangal\nKozhikode, Kerala 673014\n', 'sgzbxnxnxnxbxn'),
(4, 'Kozhikode, Kerala\n', 'dgdhdjdjdj'),
(5, 'Poovangal\nKozhikode, Kerala 673014\n', 'sshshs'),
(6, 'Pantheeramkavu, Kerala\n', 'dbdhdbdj'),
(7, 'Poovangal\nKozhikode, Kerala 673014\n', 'djdjfjfhfd'),
(8, 'Pantheeramkavu, Kerala\n', 'dhdhdjdj'),
(9, 'Poovangal\nKozhikode, Kerala 673014\n', 'dbdjshsbdj'),
(10, 'Poovangal\nKozhikode, Kerala 673014\n', 'sgdbdjdjd'),
(11, 'Pantheeramkavu, Kerala\n', 'sgdhdjd'),
(12, 'Poovangal\nKozhikode, Kerala 673014\n', 'egdgdgd'),
(13, 'Kozhikode, Kerala\n', 'dfgbbbnhd'),
(14, 'Poovangal\nKozhikode, Kerala 673014\n', 'ddghjjj'),
(15, 'Poovangal\nKozhikode, Kerala 673014\n', 'xhxjdbf'),
(16, 'Poovangal\nKozhikode, Kerala 673014\n', 'hdjdkdkj'),
(17, 'Poovangal\nKozhikode, Kerala 673014\n', 'dgbhdd'),
(18, 'Poovangal\nKozhikode, Kerala 673014\n', 'djdjdkdbdjdj'),
(19, 'Poovangal\nKozhikode, Kerala 673014\n', 'fhdkdfn '),
(20, 'Poovangal\nKozhikode, Kerala 673014\n', 'djjl'),
(21, 'Kozhikode, Kerala\n', 'fghhh'),
(31, '', 'dfsdfssfs'),
(32, '', 'dfsdfssfs'),
(33, 'h', 'dfsdfssfs'),
(39, 'Poovangal\nKozhikode, Kerala 673014\n', 'gdjdjdhdkdhfvvkj'),
(40, 'Kozhikode, Kerala\n', 'hdjdjrjrjfnfjfhfjfjfbbbbb'),
(41, 'Poovangal\nKozhikode, Kerala 673014\n', 'dhdjshshs'),
(42, 'Kozhikode, Kerala\n', 'xxvbbbg'),
(43, 'Kozhikode\nKerala\n', 'wtehdhd'),
(44, 'Poovangal\nKozhikode, Kerala 673014\n', 'weryuuhh'),
(45, 'Kozhikode, Kerala\n', 'dbdjsndjdhdhdjdjdj'),
(46, 'Pantheeramkavu, Kerala\n', 'heyeyeywy'),
(47, 'Pantheeramkavu, Kerala\n', 'ahsjsjsjdj'),
(48, 'Pantheeramkavu, Kerala\n', 'hdjdjfh'),
(49, 'Kozhikode, Kerala\n', 'sfgjjh'),
(50, 'Pantheeramkavu, Kerala\n', 'ahahsh');

-- --------------------------------------------------------

--
-- Table structure for table `tbl_image_uploading`
--

CREATE TABLE IF NOT EXISTS `tbl_image_uploading` (
  `pk_int_img_id` int(11) NOT NULL AUTO_INCREMENT,
  `img_name` varchar(200) NOT NULL,
  `img_path` varchar(500) NOT NULL,
  `fk_int_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`pk_int_img_id`),
  KEY `fk_int_id` (`fk_int_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=2 ;

--
-- Dumping data for table `tbl_image_uploading`
--

INSERT INTO `tbl_image_uploading` (`pk_int_img_id`, `img_name`, `img_path`, `fk_int_id`) VALUES
(1, 'me', 'http://services.trainees.baabtra.com/BM-41776/uploads/1.jpg', 1);

-- --------------------------------------------------------

--
-- Table structure for table `tbl_login`
--

CREATE TABLE IF NOT EXISTS `tbl_login` (
  `pk_int_log_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_email_phone` varchar(50) NOT NULL,
  `user_password` varchar(20) NOT NULL,
  PRIMARY KEY (`pk_int_log_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `tbl_posting`
--

CREATE TABLE IF NOT EXISTS `tbl_posting` (
  `pk_int_primary_key` int(11) NOT NULL AUTO_INCREMENT,
  `Status` varchar(50) DEFAULT NULL,
  `fk_int_reg_id` int(11) NOT NULL,
  PRIMARY KEY (`pk_int_primary_key`),
  KEY `fk_int_reg_id` (`fk_int_reg_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=11 ;

--
-- Dumping data for table `tbl_posting`
--

INSERT INTO `tbl_posting` (`pk_int_primary_key`, `Status`, `fk_int_reg_id`) VALUES
(1, 'wryu', 23),
(2, 'hai', 23),
(3, 'post', 23),
(4, 'xcbh', 23),
(5, 'qsty', 1),
(6, 'asdf', 1),
(7, 'vgy', 1),
(8, 'qety', 1),
(9, 'haaaiaia', 1),
(10, 'hehejjeh', 1);

-- --------------------------------------------------------

--
-- Table structure for table `tbl_registration`
--

CREATE TABLE IF NOT EXISTS `tbl_registration` (
  `pk_int_reg_id` int(11) NOT NULL AUTO_INCREMENT,
  `first_name` varchar(20) DEFAULT NULL,
  `surname` varchar(20) DEFAULT NULL,
  `Email_id` varchar(20) DEFAULT NULL,
  `Gender` varchar(20) DEFAULT NULL,
  `Date` int(11) NOT NULL,
  `Month` varchar(15) NOT NULL,
  `Year` int(11) NOT NULL,
  `Password` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`pk_int_reg_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=24 ;

--
-- Dumping data for table `tbl_registration`
--

INSERT INTO `tbl_registration` (`pk_int_reg_id`, `first_name`, `surname`, `Email_id`, `Gender`, `Date`, `Month`, `Year`, `Password`) VALUES
(1, 'rauf', 'abdul', 'abdul@gmail.com', 'Male', 5, 'may', 1995, 'abdulrauf'),
(18, 'abdul', 'rauf', '9446974364', 'male', 14, 'may', 2000, 'pling'),
(19, 'haris', 'mangalad', '1234567', 'male', 13, 'gah', 1998, 'qwerty'),
(23, 'neha', 'abdulla', 'neha@gmail.com', 'female', 23, '04', 1993, 'nehaneha');

--
-- Constraints for dumped tables
--

--
-- Constraints for table `tbl_image_uploading`
--
ALTER TABLE `tbl_image_uploading`
  ADD CONSTRAINT `tbl_image_uploading_ibfk_1` FOREIGN KEY (`fk_int_id`) REFERENCES `tbl_registration` (`pk_int_reg_id`);

--
-- Constraints for table `tbl_posting`
--
ALTER TABLE `tbl_posting`
  ADD CONSTRAINT `tbl_posting_ibfk_1` FOREIGN KEY (`fk_int_reg_id`) REFERENCES `tbl_registration` (`pk_int_reg_id`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
