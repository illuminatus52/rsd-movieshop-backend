-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Mar 21, 2022 at 11:13 AM
-- Server version: 10.4.22-MariaDB
-- PHP Version: 8.1.1

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `movieshop`
--
CREATE DATABASE IF NOT EXISTS `movieshop` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
USE `movieshop`;

-- --------------------------------------------------------

--
-- Table structure for table `cart`
--

CREATE TABLE IF NOT EXISTS `cart` (
  `cartid` bigint(20) NOT NULL,
  PRIMARY KEY (`cartid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `cart`
--

INSERT INTO `cart` (`cartid`) VALUES
(1),
(2),
(3),
(4),
(6),
(7);

-- --------------------------------------------------------

--
-- Table structure for table `cart_seq`
--

CREATE TABLE IF NOT EXISTS `cart_seq` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `cart_seq`
--

INSERT INTO `cart_seq` (`next_val`) VALUES
(8);

-- --------------------------------------------------------

--
-- Table structure for table `genre`
--

CREATE TABLE IF NOT EXISTS `genre` (
  `genre_id` bigint(20) NOT NULL,
  `genre_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`genre_id`),
  UNIQUE KEY `UK_af763lrx484eaodu8m088ei0` (`genre_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `genre`
--

INSERT INTO `genre` (`genre_id`, `genre_name`) VALUES
(1, 'Action'),
(6, 'Adventure'),
(5, 'Animation'),
(14, 'Biography'),
(7, 'Comedy'),
(2, 'Crime'),
(3, 'Drama'),
(11, 'Family'),
(13, 'History'),
(10, 'Horror'),
(22, 'kek'),
(4, 'Musical'),
(8, 'Mystery'),
(15, 'red'),
(12, 'Romance'),
(9, 'Thriller');

-- --------------------------------------------------------

--
-- Table structure for table `genre_seq`
--

CREATE TABLE IF NOT EXISTS `genre_seq` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `genre_seq`
--

INSERT INTO `genre_seq` (`next_val`) VALUES
(23);

-- --------------------------------------------------------

--
-- Table structure for table `items`
--

CREATE TABLE IF NOT EXISTS `items` (
  `item_id` bigint(20) NOT NULL,
  `quantity` int(11) NOT NULL,
  `cartid` bigint(20) DEFAULT NULL,
  `movieid` bigint(20) DEFAULT NULL,
  `orderid` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`item_id`),
  KEY `FKgg9rkqkkmqspp32kwita4pnyo` (`cartid`),
  KEY `FK5stpbipohidmo5s4rgjhwdqsx` (`movieid`),
  KEY `FKl1sm91cpsctq0qjxlkeho7dov` (`orderid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `item_seq`
--

CREATE TABLE IF NOT EXISTS `item_seq` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `item_seq`
--

INSERT INTO `item_seq` (`next_val`) VALUES
(5);

-- --------------------------------------------------------

--
-- Table structure for table `movie`
--

CREATE TABLE IF NOT EXISTS `movie` (
  `movie_id` bigint(20) NOT NULL,
  `movie_stock` int(11) NOT NULL,
  `picture` varchar(255) DEFAULT NULL,
  `price` double NOT NULL,
  `release_year` int(11) NOT NULL,
  `title` varchar(255) DEFAULT NULL,
  `imdb_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`movie_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `movie`
--

INSERT INTO `movie` (`movie_id`, `movie_stock`, `picture`, `price`, `release_year`, `title`, `imdb_id`) VALUES
(1, 20, 'https://m.media-amazon.com/images/M/MV5BZTE2YTY3YTMtM2FlMS00YmI3LTgyMWUtM2FhMWIyZWRmMDk5XkEyXkFqcGdeQXVyMTkxNjUyNQ@@._V1_.jpg', 49.99, 2022, 'The Batman', NULL),
(2, 20, 'https://m.media-amazon.com/images/M/MV5BMzQ5ZDZhZDItZTNmZi00MWQ0LWJlNDUtZTE4ZWJmODNlM2Y3XkEyXkFqcGdeQXVyMDA4NzMyOA@@._V1_.jpg', 29.99, 2021, 'West Side Story', NULL),
(3, 20, 'https://m.media-amazon.com/images/M/MV5BNjY0MGEzZmQtZWMxNi00MWVhLWI4NWEtYjQ0MDkyYTJhMDU0XkEyXkFqcGdeQXVyODc0OTEyNDU@._V1_.jpg', 19.99, 2022, 'Turning Red', NULL),
(4, 20, 'https://m.media-amazon.com/images/M/MV5BNmZkMGJlODktNzMzMC00MmY2LTkzNDgtMjUwOWFiNjFjZGIwXkEyXkFqcGdeQXVyODk4OTc3MTY@._V1_.jpg', 29.99, 2022, 'Bullet Train', NULL),
(5, 20, 'https://m.media-amazon.com/images/M/MV5BYjExYTcwYmYtMWY2Zi00MGJlLTk3YjUtZTU1Zjg4MDc0Y2FjXkEyXkFqcGdeQXVyODE5NzE3OTE@._V1_.jpg', 24.99, 2022, 'Scream', NULL),
(6, 20, 'https://m.media-amazon.com/images/M/MV5BOTY2NzFjODctOWUzMC00MGZhLTlhNjMtM2Y2ODBiNGY1ZWRiXkEyXkFqcGdeQXVyMDM2NDM2MQ@@._V1_.jpg', 19.99, 2021, 'Free Guy', NULL),
(7, 20, 'https://m.media-amazon.com/images/M/MV5BNjE5NzA4ZDctOTJkZi00NzM0LTkwOTYtMDI4MmNkMzIxODhkXkEyXkFqcGdeQXVyNjY1MTg4Mzc@._V1_.jpg', 9.99, 2021, 'Encanto', NULL),
(8, 20, 'https://m.media-amazon.com/images/M/MV5BZTgxMWJkMzItMzg1YS00NDJiLTljYjctMTc2YzQzZDZjZDAyXkEyXkFqcGdeQXVyODQ2OTIzNDU@._V1_.jpg', 9.99, 2021, 'Licorice Pizza', NULL),
(9, 20, 'https://m.media-amazon.com/images/M/MV5BZDBmODlkYjQtYWFlZi00ZDBmLWJjYmUtNDBhMjlmMDMxNmQ3XkEyXkFqcGdeQXVyMjI0Mjg2NzE@._V1_.jpg', 19.99, 2022, 'Against The Ice', NULL),
(10, 20, 'https://m.media-amazon.com/images/M/MV5BODMwYTYyY2ItOWQ5Yi00OTI1LTllYTQtYTdlNWM4YzJhYTM0XkEyXkFqcGdeQXVyMTA2MDU0NjM5._V1_.jpg', 19.99, 2021, 'Belfast', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `movie_genre`
--

CREATE TABLE IF NOT EXISTS `movie_genre` (
  `movieid` bigint(20) NOT NULL,
  `genreid` bigint(20) NOT NULL,
  KEY `FKc4pi5cw1cfl9mhsbu23wqxlug` (`genreid`),
  KEY `FKkwo9tdjjxhd7054hkp76bywub` (`movieid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `movie_genre`
--

INSERT INTO `movie_genre` (`movieid`, `genreid`) VALUES
(1, 1),
(1, 2),
(1, 3),
(2, 4),
(2, 2),
(2, 3),
(3, 5),
(3, 6),
(3, 7),
(4, 8),
(4, 9),
(4, 1),
(5, 10),
(5, 8),
(5, 9),
(6, 1),
(6, 6),
(6, 7),
(7, 11),
(7, 1),
(7, 7),
(8, 12),
(8, 7),
(8, 3),
(9, 13),
(9, 6),
(9, 3),
(10, 14),
(10, 3),
(10, 13);

-- --------------------------------------------------------

--
-- Table structure for table `movie_seq`
--

CREATE TABLE IF NOT EXISTS `movie_seq` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `movie_seq`
--

INSERT INTO `movie_seq` (`next_val`) VALUES
(15);

-- --------------------------------------------------------

--
-- Table structure for table `orders`
--

CREATE TABLE IF NOT EXISTS `orders` (
  `order_id` bigint(20) NOT NULL,
  `status` varchar(255) DEFAULT NULL,
  `total_price` double NOT NULL,
  `userid` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`order_id`),
  KEY `FKdxew8n76x1bnoxjas0qxrlbq6` (`userid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `order_seq`
--

CREATE TABLE IF NOT EXISTS `order_seq` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `order_seq`
--

INSERT INTO `order_seq` (`next_val`) VALUES
(2);

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE IF NOT EXISTS `user` (
  `user_id` bigint(20) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `family_name` varchar(255) DEFAULT NULL,
  `first_name` varchar(255) DEFAULT NULL,
  `password` varchar(255) NOT NULL,
  `role` varchar(255) NOT NULL,
  `username` varchar(255) NOT NULL,
  `cart` bigint(20) DEFAULT NULL,
  `is_enabled` bit(1) NOT NULL,
  `picture` varchar(255) DEFAULT NULL,
  `shipping_address` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `UK_sb8bbouer5wak8vyiiy4pf2bx` (`username`),
  KEY `FKpcjo55wtj0cx685k2llhyjnxy` (`cart`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`user_id`, `email`, `family_name`, `first_name`, `password`, `role`, `username`, `cart`, `is_enabled`, `picture`, `shipping_address`) VALUES
(3, 'admin@movieshop.com', 'admin', 'admin', '$2a$10$cOpqAlLtxQdZo3jCpDoh4OWCdwLa0yftyNIFxfKAqdNZmpiVuPk1C', 'ROLE_ADMIN', 'admin', 3, b'1', NULL, 'admin'),
(4, 'techuser@movieshop.com', 'user', 'tech', '$2a$10$HL5BVn7tuDVvPEYZSHENJ.d2Ogk8/krMrgjccUp/LA5Y6WYRt8Nzy', 'ROLE_USER', 'techuser', 4, b'1', NULL, 'techuser'),
(6, 'Toney69@gmail.com', 'Bruen', 'Sterling', '$2a$10$RmlYPoCksX5lIiciOU2Gse19trH1ceWFXnS1z26TqgdLUTeOTeoUC', 'ROLE_USER', 'Jaunita.Jacobson86', 6, b'1', NULL, '5617 Lowe Route, Henriettefort, Trinidad and Tobago'),
(7, 'Quentin.Schmidt29@hotmail.com', 'Greenholt', 'Araceli', '$2a$10$0e0SjIZWCFtjdJqsOP.vBeUcvps1/tWCOkWZvz4.T8qmz.bm89coq', 'ROLE_USER', 'Harmony_Runolfsson77', 7, b'1', NULL, '02790 Katlynn Isle, Port Aubreystad, Saint Kitts and Nevis');

-- --------------------------------------------------------

--
-- Table structure for table `userid_seq`
--

CREATE TABLE IF NOT EXISTS `userid_seq` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `userid_seq`
--

INSERT INTO `userid_seq` (`next_val`) VALUES
(8);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `items`
--
ALTER TABLE `items`
  ADD CONSTRAINT `FK5stpbipohidmo5s4rgjhwdqsx` FOREIGN KEY (`movieid`) REFERENCES `movie` (`movie_id`),
  ADD CONSTRAINT `FKgg9rkqkkmqspp32kwita4pnyo` FOREIGN KEY (`cartid`) REFERENCES `cart` (`cartid`),
  ADD CONSTRAINT `FKl1sm91cpsctq0qjxlkeho7dov` FOREIGN KEY (`orderid`) REFERENCES `orders` (`order_id`);

--
-- Constraints for table `movie_genre`
--
ALTER TABLE `movie_genre`
  ADD CONSTRAINT `FKc4pi5cw1cfl9mhsbu23wqxlug` FOREIGN KEY (`genreid`) REFERENCES `genre` (`genre_id`),
  ADD CONSTRAINT `FKkwo9tdjjxhd7054hkp76bywub` FOREIGN KEY (`movieid`) REFERENCES `movie` (`movie_id`);

--
-- Constraints for table `orders`
--
ALTER TABLE `orders`
  ADD CONSTRAINT `FKdxew8n76x1bnoxjas0qxrlbq6` FOREIGN KEY (`userid`) REFERENCES `user` (`user_id`);

--
-- Constraints for table `user`
--
ALTER TABLE `user`
  ADD CONSTRAINT `FKpcjo55wtj0cx685k2llhyjnxy` FOREIGN KEY (`cart`) REFERENCES `cart` (`cartid`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
