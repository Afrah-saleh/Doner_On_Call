-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Dec 21, 2021 at 04:08 PM
-- Server version: 10.4.21-MariaDB
-- PHP Version: 8.0.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `doneroncall`
--

-- --------------------------------------------------------

--
-- Table structure for table `admin`
--

CREATE TABLE `admin` (
  `adminid` int(8) NOT NULL,
  `a_email` varchar(50) NOT NULL,
  `a_name` varchar(30) NOT NULL,
  `a_password` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `admin`
--

INSERT INTO `admin` (`adminid`, `a_email`, `a_name`, `a_password`) VALUES
(1001, 'admin@gmail.com', 'admin', '123456');

-- --------------------------------------------------------

--
-- Table structure for table `bloodannouncement`
--

CREATE TABLE `bloodannouncement` (
  `announcementid` int(8) NOT NULL,
  `announcementdate` varchar(20) NOT NULL,
  `quantity` int(3) NOT NULL,
  `bloodtype` varchar(5) NOT NULL,
  `announcement_status` varchar(20) NOT NULL,
  `hospitalid` int(8) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `bloodannouncement`
--

INSERT INTO `bloodannouncement` (`announcementid`, `announcementdate`, `quantity`, `bloodtype`, `announcement_status`, `hospitalid`) VALUES
(20, '11/30/2021', 2, 'O+', 'Closed', 3002),
(5011, '2021-12-21', 5, 'A+', 'Active', 3002);

-- --------------------------------------------------------

--
-- Table structure for table `donationrecord`
--

CREATE TABLE `donationrecord` (
  `recordid` int(8) NOT NULL,
  `donationdate` varchar(30) NOT NULL,
  `quantity` int(2) NOT NULL,
  `donerid` int(8) NOT NULL,
  `hospitalid` int(8) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `donationrecord`
--

INSERT INTO `donationrecord` (`recordid`, `donationdate`, `quantity`, `donerid`, `hospitalid`) VALUES
(11, '2021-12-21', 1, 2001, 3002);

-- --------------------------------------------------------

--
-- Table structure for table `doner`
--

CREATE TABLE `doner` (
  `donerid` int(8) NOT NULL,
  `d_email` varchar(30) NOT NULL,
  `d_name` varchar(50) NOT NULL,
  `d_password` varchar(10) NOT NULL,
  `d_city` varchar(20) NOT NULL,
  `d_birthdate` varchar(20) NOT NULL,
  `d_blodtype` varchar(5) NOT NULL,
  `d_mobile` varchar(15) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `doner`
--

INSERT INTO `doner` (`donerid`, `d_email`, `d_name`, `d_password`, `d_city`, `d_birthdate`, `d_blodtype`, `d_mobile`) VALUES
(2001, 'doner@gmail.com', 'doner', '123456', 'Ryiadh', '25/10/1988', 'A+', '0590030606');

-- --------------------------------------------------------

--
-- Table structure for table `hospital`
--

CREATE TABLE `hospital` (
  `h_id` int(8) NOT NULL,
  `h_email` varchar(30) NOT NULL,
  `h_name` varchar(50) NOT NULL,
  `h_password` varchar(10) NOT NULL,
  `h_city` varchar(20) NOT NULL,
  `h_phone` varchar(15) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `hospital`
--

INSERT INTO `hospital` (`h_id`, `h_email`, `h_name`, `h_password`, `h_city`, `h_phone`) VALUES
(3002, 'dallah@gmail.com', 'dallah', '123456', 'Ryiadh', '0555555555'),
(3003, 'habib@gmail.com', 'alhabib hospital', '123456', 'Dammam', '15151515');

-- --------------------------------------------------------

--
-- Table structure for table `loghistory`
--

CREATE TABLE `loghistory` (
  `device_id` varchar(30) NOT NULL,
  `logintime` timestamp NOT NULL DEFAULT current_timestamp(),
  `logouttime` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `request`
--

CREATE TABLE `request` (
  `requestid` int(8) NOT NULL,
  `requestdate` varchar(30) NOT NULL,
  `requeststatus` varchar(10) NOT NULL,
  `requesttype` varchar(20) NOT NULL,
  `donerid` int(8) NOT NULL,
  `announcementid` int(8) NOT NULL,
  `expectdonationdate` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `admin`
--
ALTER TABLE `admin`
  ADD PRIMARY KEY (`adminid`);

--
-- Indexes for table `bloodannouncement`
--
ALTER TABLE `bloodannouncement`
  ADD PRIMARY KEY (`announcementid`),
  ADD KEY `fk_hospital` (`hospitalid`);

--
-- Indexes for table `donationrecord`
--
ALTER TABLE `donationrecord`
  ADD PRIMARY KEY (`recordid`),
  ADD KEY `fk_d` (`donerid`),
  ADD KEY `fk_h` (`hospitalid`);

--
-- Indexes for table `doner`
--
ALTER TABLE `doner`
  ADD PRIMARY KEY (`donerid`),
  ADD UNIQUE KEY `d_email` (`d_email`);

--
-- Indexes for table `hospital`
--
ALTER TABLE `hospital`
  ADD PRIMARY KEY (`h_id`),
  ADD UNIQUE KEY `h_email` (`h_email`);

--
-- Indexes for table `loghistory`
--
ALTER TABLE `loghistory`
  ADD PRIMARY KEY (`device_id`);

--
-- Indexes for table `request`
--
ALTER TABLE `request`
  ADD PRIMARY KEY (`requestid`),
  ADD KEY `fk_doner` (`donerid`),
  ADD KEY `fk_announcement` (`announcementid`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `bloodannouncement`
--
ALTER TABLE `bloodannouncement`
  MODIFY `announcementid` int(8) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5015;

--
-- AUTO_INCREMENT for table `donationrecord`
--
ALTER TABLE `donationrecord`
  MODIFY `recordid` int(8) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- AUTO_INCREMENT for table `doner`
--
ALTER TABLE `doner`
  MODIFY `donerid` int(8) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3014;

--
-- AUTO_INCREMENT for table `hospital`
--
ALTER TABLE `hospital`
  MODIFY `h_id` int(8) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3004;

--
-- AUTO_INCREMENT for table `request`
--
ALTER TABLE `request`
  MODIFY `requestid` int(8) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6032;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `bloodannouncement`
--
ALTER TABLE `bloodannouncement`
  ADD CONSTRAINT `fk_hospital` FOREIGN KEY (`hospitalid`) REFERENCES `hospital` (`h_id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `donationrecord`
--
ALTER TABLE `donationrecord`
  ADD CONSTRAINT `fk_d` FOREIGN KEY (`donerid`) REFERENCES `doner` (`donerid`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_h` FOREIGN KEY (`hospitalid`) REFERENCES `hospital` (`h_id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `request`
--
ALTER TABLE `request`
  ADD CONSTRAINT `fk_announcement` FOREIGN KEY (`announcementid`) REFERENCES `bloodannouncement` (`announcementid`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_doner` FOREIGN KEY (`donerid`) REFERENCES `doner` (`donerid`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
