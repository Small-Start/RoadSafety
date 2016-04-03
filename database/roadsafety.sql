-- phpMyAdmin SQL Dump
-- version 3.2.0.1
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Apr 03, 2016 at 12:36 PM
-- Server version: 5.1.36
-- PHP Version: 5.3.0

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `roadsafety`
--

-- --------------------------------------------------------

--
-- Table structure for table `details`
--

CREATE TABLE IF NOT EXISTS `details` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `location` varchar(1000) NOT NULL,
  `lat` double NOT NULL,
  `lng` double NOT NULL,
  `damageRating` float NOT NULL,
  `killed` bigint(20) NOT NULL,
  `injured` bigint(20) NOT NULL,
  `datetime` datetime NOT NULL,
  `crashDetail` varchar(50000) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=7 ;

--
-- Dumping data for table `details`
--

INSERT INTO `details` (`id`, `location`, `lat`, `lng`, `damageRating`, `killed`, `injured`, `datetime`, `crashDetail`) VALUES
(1, 'Quadrangle Lane AIIMS, AIIMS Campus, Ansari Nagar East, New Delhi, Delhi 110016, India', 28.566166, 77.213692, 0.5, 1, 2, '2016-05-05 12:57:22', 'Car driver tried to take over another car from wrong side at 90kph hitting the truck standing by the side of road. Accident prone area.'),
(2, 'fortis hospital sector 62 noida', 28.6189, 77.3725, 0.2, 1, 3, '2015-06-04 06:55:06', 'a truck collides with a car at 70kmph hitting a car hard, accident prone area!'),
(3, 'mamura chowk bus stop, d block, sector 59, noida, uttar pradesh 201307, india', 28.6099, 77.3729, 0.3, 1, 5, '2013-02-12 13:09:08', 'collision between bus and autorikshaw took place injured passengers in it. High traffic density area.. Be careful'),
(4, 'isbt anand vihar, anand vihar, new delhi, delhi, india', 28.6477, 77.3145, 0.8, 2, 8, '2016-01-19 13:25:55', 'A bus fell down due to puncture injured many..accident prone area'),
(5, 'shipra mall underpass, block a, vaibhav khand, indirapuram, noida, delhi 201001, india', 28.6326, 77.3672, 0.5, 1, 2, '2013-03-14 17:28:19', 'Car driver tried to take over another car from wrong side at 90kph hitting the truck standing by the side of road. Accident prone area.'),
(6, 'Tot Mall Market Sector 62, Noida', 28.6126126, 77.356924, 0.1, 1, 2, '2016-04-03 20:55:20', 'A drunk driver hit a pedestrian by a car..Walk carefuly');

-- --------------------------------------------------------

--
-- Table structure for table `user_info`
--

CREATE TABLE IF NOT EXISTS `user_info` (
  `username` varchar(40) COLLATE utf8_unicode_ci NOT NULL,
  `password` varchar(40) COLLATE utf8_unicode_ci NOT NULL,
  `name` text COLLATE utf8_unicode_ci NOT NULL,
  `email` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `bloodgroup` varchar(4) COLLATE utf8_unicode_ci NOT NULL,
  `pno` bigint(14) NOT NULL,
  `score` bigint(20) NOT NULL DEFAULT '0',
  `lat` double NOT NULL,
  `lng` double NOT NULL,
  UNIQUE KEY `username` (`username`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `user_info`
--

INSERT INTO `user_info` (`username`, `password`, `name`, `email`, `bloodgroup`, `pno`, `score`, `lat`, `lng`) VALUES
('fg', 'hjk', 'CG g', 'ggh', 'A+', 1234567895, 0, 28.612612612613, 77.356924859219),
('kan', 'kan', 'kan', 'kan', 'A', 8765432190, 0, 28.61, 77.36),
('testt', 'uiu', 'testt', 'testt', 'B', 7777777778, 0, 28.612612612613, 77.356924859219),
('haik', 'haik', 'haik', 'haik', 'A+', 9191919492, 0, 28.6131774, 77.3593998),
('hon', 'hon', 'hinry', 'hon', 'A+', 8654394837, 0, 28.9658027, 77.6841708),
('jj', 'fgg', 'fg', 'chj', 'A+', 1234657890, 0, 28.612612612613, 77.356924859219),
('kani01', 'kani', 'kanishk', 'k', 'A+', 8860488735, 0, 28.6152568, 77.3695709);
