-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Aug 24, 2024 at 05:22 AM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `digitalmall`
--

DELIMITER $$
--
-- Procedures
--
CREATE DEFINER=`root`@`localhost` PROCEDURE `insertFashion` (IN `in_pName` VARCHAR(200), IN `in_quantity` INT(10), IN `in_price` DOUBLE)   BEGIN
INSERT INTO fashion (product_name,quantity,price)
VALUES (in_pName,in_quantity,in_price);

INSERT INTO searchproduct (product_name,quantity,price)
VALUES (in_pName,in_quantity,in_price);
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `insertGrocery` (IN `in_pName` VARCHAR(200), IN `in_quantity` INT(10), IN `in_price` DOUBLE)   BEGIN
INSERT INTO grocery (product_name,quantity,price)
VALUES (in_pName,in_quantity,in_price);

INSERT INTO searchproduct (product_name,quantity,price)
VALUES (in_pName,in_quantity,in_price);
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `insertUtensils` (IN `in_pName` VARCHAR(200), IN `in_quantity` INT(10), IN `in_price` DOUBLE)   BEGIN
INSERT INTO utensils (product_name,quantity,price)
VALUES (in_pName,in_quantity,in_price);

INSERT INTO searchproduct (product_name,quantity,price)
VALUES (in_pName,in_quantity,in_price);
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `removeFashion` (IN `in_pid` INT(10))   BEGIN
DECLARE
p_name varchar(100);
SELECT product_name into p_name from fashion where product_id = in_pid;
DELETE FROM fashion WHERE product_id = in_pid;
DELETE FROM searchproduct where product_name = p_name;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `removeGrocery` (IN `in_pid` INT(10))   BEGIN
DECLARE
p_name varchar(100);
SELECT product_name into p_name from grocery where product_id = in_pid;
DELETE FROM grocery WHERE product_id = in_pid;
DELETE FROM searchproduct where product_name = p_name;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `removeUtensils` (IN `in_pid` INT(10))   BEGIN
DECLARE
p_name varchar(100);
SELECT product_name into p_name from utensils where product_id = in_pid;
DELETE FROM utensils WHERE product_id = in_pid;
DELETE FROM searchproduct where product_name = p_name;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `updateQuantity` (IN `in_quantity` INT(10), IN `in_pname` VARCHAR(200))   BEGIN

update grocery set quantity = quantity - in_quantity where product_name = in_pname;
update fashion set quantity = quantity - in_quantity where product_name = in_pname;
update utensils set quantity = quantity - in_quantity where product_name = in_pname;
update searchproduct set quantity = quantity - in_quantity where product_name = in_pname;

END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `updateQuantityInFashion` (IN `in_pid` INT(10), IN `in_quantity` INT(10))   BEGIN
DECLARE
p_name varchar(100);
SELECT product_name into p_name from fashion where product_id = in_pid;
UPDATE fashion set quantity=in_quantity WHERE product_id = in_pid;
UPDATE searchproduct set quantity=in_quantity where product_name = p_name;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `updateQuantityInGrocery` (IN `in_pid` INT(10), IN `in_quantity` INT(10))   BEGIN
DECLARE
p_name varchar(100);
SELECT product_name into p_name from grocery where product_id = in_pid;
UPDATE grocery set quantity=in_quantity WHERE product_id = in_pid;
UPDATE searchproduct set quantity=in_quantity where product_name = p_name;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `updateQuantityInUtensils` (IN `in_pid` INT(10), IN `in_quantity` INT(10))   BEGIN
DECLARE
p_name varchar(100);
SELECT product_name into p_name from utensils where product_id = in_pid;
UPDATE utensils set quantity=in_quantity WHERE product_id = in_pid;
UPDATE searchproduct set quantity=in_quantity where product_name = p_name;
END$$

DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `customer_data`
--

CREATE TABLE `customer_data` (
  `No.` int(10) NOT NULL,
  `FullName` varchar(50) NOT NULL,
  `username` varchar(20) NOT NULL,
  `password` varchar(50) NOT NULL,
  `address` varchar(100) NOT NULL,
  `mobile_number` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `customer_data`
--

INSERT INTO `customer_data` (`No.`, `FullName`, `username`, `password`, `address`, `mobile_number`) VALUES
(12, 'DAKSH MAHESHBHAI S', 'dak', '123', 'NIKOL', '1111111111'),
(13, 'NIKKI DINESHKUMAR AGJA', 'nikki@26', '1234', 'SARASPUR', '2664762123'),
(14, 'MAHESH D D', 'mah', '000', 'vastral gam', '1234567890'),
(15, 'D D D', 'demo', '10', 'NIKOL', '1919192345');

-- --------------------------------------------------------

--
-- Table structure for table `employee_data`
--

CREATE TABLE `employee_data` (
  `No.` int(10) NOT NULL,
  `Employee_ID` int(10) NOT NULL,
  `Password` varchar(50) NOT NULL,
  `Employee_Name` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `employee_data`
--

INSERT INTO `employee_data` (`No.`, `Employee_ID`, `Password`, `Employee_Name`) VALUES
(1, 1, '111', 'krish'),
(2, 2, '222', 'krish2');

-- --------------------------------------------------------

--
-- Table structure for table `fashion`
--

CREATE TABLE `fashion` (
  `product_id` int(10) NOT NULL,
  `product_name` varchar(100) NOT NULL,
  `quantity` int(100) NOT NULL,
  `price` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `fashion`
--

INSERT INTO `fashion` (`product_id`, `product_name`, `quantity`, `price`) VALUES
(4, 'SHIT XXL', 3, 399),
(5, 'PARAGON OFFICE CHAPPAL ', 10, 599),
(6, 'SHIT XXL , RED/WHITE COLOR-MIXUP', 14, 15),
(7, 'ZARA SHIRT XXXL', 20, 599),
(8, 'ZARA T-SHIRT FOR MEN\'S', 40, 700),
(9, 'MEN\'S SPORTS SHOES', 15, 1500),
(10, 'WOMEN\'S SPORTS SHOES', 15, 1599);

-- --------------------------------------------------------

--
-- Table structure for table `grocery`
--

CREATE TABLE `grocery` (
  `product_id` int(10) NOT NULL,
  `product_name` varchar(100) NOT NULL,
  `quantity` int(100) NOT NULL,
  `price` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `grocery`
--

INSERT INTO `grocery` (`product_id`, `product_name`, `quantity`, `price`) VALUES
(13, 'AMUL GOLD MILK ,50 GM', 3, 39),
(14, 'AMUL MILK, 50 ML', 80, 49),
(16, 'UJALLA COFFEE , 50 GM', 18, 29),
(17, 'KISSAN FRESH TOMATO KETCHUP , 250 GM', 5, 82),
(18, 'AMUL CHEESE , 250 GM', 14, 141),
(19, 'MOTHER DAIRY CHEESE , 250 GM', 15, 120),
(20, 'AASHIRVAD ATTA MULTIGRAINS , 1 KG ', 8, 250);

-- --------------------------------------------------------

--
-- Table structure for table `orderhistory`
--

CREATE TABLE `orderhistory` (
  `No` int(100) NOT NULL,
  `username` varchar(50) NOT NULL,
  `product_name` varchar(100) NOT NULL,
  `total_quantity` int(10) NOT NULL,
  `total_price` double NOT NULL,
  `order_date` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `orderhistory`
--

INSERT INTO `orderhistory` (`No`, `username`, `product_name`, `total_quantity`, `total_price`, `order_date`) VALUES
(9, 'dak', 'VAGHBAKRI TEA, 500 GM', 5, 345, 'Fri Aug 09 14:46:13 IST 2024'),
(10, 'dak', 'SHIT XXL', 5, 1995, 'Fri Aug 09 14:46:13 IST 2024'),
(11, 'chiki', 'COOKER', 1, 100, 'Sat Aug 17 21:59:45 IST 2024'),
(12, 'dak', 'AMUL GOLD MILK ,50 GM', 5, 195, 'Tue Aug 20 19:26:02 IST 2024'),
(13, 'dak', 'AMUL MILK, 50 ML', 20, 980, 'Wed Aug 21 11:41:31 IST 2024'),
(14, 'dak2', 'COOKER', 1, 100, 'Wed Aug 21 14:28:24 IST 2024'),
(15, 'dak2', 'AMUL GOLD MILK ,50 GM', 1, 39, 'Wed Aug 21 14:30:35 IST 2024'),
(16, 'dak2', 'AMUL CHEESE , 250 GM', 1, 141, 'Fri Aug 23 11:19:28 IST 2024'),
(17, 'dak2', 'SHIT XXL', 1, 399, 'Fri Aug 23 11:30:27 IST 2024'),
(18, 'dak2', 'COOKER', 2, 200, 'Fri Aug 23 11:30:27 IST 2024'),
(19, 'dak2', 'UJALLA COFFEE , 50 GM', 2, 58, 'Fri Aug 23 11:37:54 IST 2024'),
(20, 'dak', 'AASHIRVAD ATTA MULTIGRAINS , 1 KG ', 1, 250, 'Fri Aug 23 18:42:03 IST 2024'),
(21, 'dak', 'AASHIRVAD ATTA MULTIGRAINS , 1 KG ', 1, 250, 'Fri Aug 23 18:42:03 IST 2024'),
(22, 'demo', 'KISSAN FRESH TOMATO KETCHUP , 250 GM', 5, 410, 'Fri Aug 23 22:38:13 IST 2024'),
(23, 'dak', 'AMUL GOLD MILK ,50 GM', 1, 39, 'Sat Aug 24 08:39:22 IST 2024'),
(24, 'dak', 'SHIT XXL', 1, 399, 'Sat Aug 24 08:39:22 IST 2024'),
(25, 'dak', 'COOKER', 1, 100, 'Sat Aug 24 08:39:22 IST 2024'),
(26, 'dak', 'SHIT XXL , RED/WHITE COLOR-MIXUP', 1, 15, 'Sat Aug 24 08:39:22 IST 2024');

-- --------------------------------------------------------

--
-- Table structure for table `searchproduct`
--

CREATE TABLE `searchproduct` (
  `product_id` int(100) NOT NULL,
  `product_name` varchar(100) NOT NULL,
  `quantity` int(100) NOT NULL,
  `price` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `searchproduct`
--

INSERT INTO `searchproduct` (`product_id`, `product_name`, `quantity`, `price`) VALUES
(2, 'AMUL GOLD MILK ,50 GM', 3, 39),
(3, 'SHIT XXL', 3, 399),
(4, 'COOKER', 5, 100),
(5, 'AMUL MILK, 50 ML', 80, 49),
(7, 'UJALLA COFFEE , 50 GM', 18, 29),
(8, 'KISSAN FRESH TOMATO KETCHUP , 250 GM', 5, 82),
(9, 'AMUL CHEESE , 250 GM', 14, 141),
(10, 'MOTHER DAIRY CHEESE , 250 GM', 15, 120),
(11, 'AASHIRVAD ATTA MULTIGRAINS , 1 KG ', 8, 250),
(12, 'PARAGON OFFICE CHAPPAL ', 10, 599),
(13, 'SHIT XXL , RED/WHITE COLOR-MIXUP', 14, 15),
(14, 'ZARA SHIRT XXXL', 20, 599),
(15, 'ZARA T-SHIRT FOR MEN\'S', 40, 700),
(16, 'MEN\'S SPORTS SHOES', 15, 1500),
(17, 'WOMEN\'S SPORTS SHOES', 15, 1599),
(18, 'MILTON PRO COOK , KITCHEN JEWEL SET OF 5', 20, 1899),
(19, 'PIGEON BY STOVE NON-STICK', 20, 150),
(20, 'AGARAO KITCHEN SET , NYLON AND STAINLESS STEEL', 20, 4000),
(21, 'YOKO KNIFE ', 20, 400),
(22, 'PRESTIGE OMEGA SELECT PLUS , NON-STICK KITCHEN SET , 3-PIECES', 30, 1899);

-- --------------------------------------------------------

--
-- Table structure for table `utensils`
--

CREATE TABLE `utensils` (
  `product_id` int(10) NOT NULL,
  `product_name` varchar(100) NOT NULL,
  `quantity` int(100) NOT NULL,
  `price` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `utensils`
--

INSERT INTO `utensils` (`product_id`, `product_name`, `quantity`, `price`) VALUES
(24, 'COOKER', 5, 100),
(25, 'MILTON PRO COOK , KITCHEN JEWEL SET OF 5', 20, 1899),
(26, 'PIGEON BY STOVE NON-STICK', 20, 150),
(27, 'AGARAO KITCHEN SET , NYLON AND STAINLESS STEEL', 20, 4000),
(28, 'YOKO KNIFE ', 20, 400),
(29, 'PRESTIGE OMEGA SELECT PLUS , NON-STICK KITCHEN SET , 3-PIECES', 30, 1899);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `customer_data`
--
ALTER TABLE `customer_data`
  ADD PRIMARY KEY (`No.`),
  ADD UNIQUE KEY `UNIQUE` (`username`);

--
-- Indexes for table `employee_data`
--
ALTER TABLE `employee_data`
  ADD PRIMARY KEY (`No.`),
  ADD UNIQUE KEY `Employee_ID` (`Employee_ID`);

--
-- Indexes for table `fashion`
--
ALTER TABLE `fashion`
  ADD PRIMARY KEY (`product_id`);

--
-- Indexes for table `grocery`
--
ALTER TABLE `grocery`
  ADD PRIMARY KEY (`product_id`);

--
-- Indexes for table `orderhistory`
--
ALTER TABLE `orderhistory`
  ADD PRIMARY KEY (`No`);

--
-- Indexes for table `searchproduct`
--
ALTER TABLE `searchproduct`
  ADD PRIMARY KEY (`product_id`);

--
-- Indexes for table `utensils`
--
ALTER TABLE `utensils`
  ADD PRIMARY KEY (`product_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `customer_data`
--
ALTER TABLE `customer_data`
  MODIFY `No.` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;

--
-- AUTO_INCREMENT for table `employee_data`
--
ALTER TABLE `employee_data`
  MODIFY `No.` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `fashion`
--
ALTER TABLE `fashion`
  MODIFY `product_id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT for table `grocery`
--
ALTER TABLE `grocery`
  MODIFY `product_id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=21;

--
-- AUTO_INCREMENT for table `orderhistory`
--
ALTER TABLE `orderhistory`
  MODIFY `No` int(100) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=27;

--
-- AUTO_INCREMENT for table `searchproduct`
--
ALTER TABLE `searchproduct`
  MODIFY `product_id` int(100) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=23;

--
-- AUTO_INCREMENT for table `utensils`
--
ALTER TABLE `utensils`
  MODIFY `product_id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=30;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
