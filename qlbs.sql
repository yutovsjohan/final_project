-- phpMyAdmin SQL Dump
-- version 4.7.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Mar 29, 2019 at 05:01 PM
-- Server version: 10.1.26-MariaDB
-- PHP Version: 7.1.8

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `tmanga`
--

-- --------------------------------------------------------

--
-- Table structure for table `chucvu`
--

CREATE TABLE `chucvu` (
  `id` int(11) NOT NULL,
  `tenchucvu` varchar(100) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `cthoadon`
--

CREATE TABLE `cthoadon` (
  `id` int(11) NOT NULL,
  `idhoadon` int(11) NOT NULL,
  `idtruyen` int(11) NOT NULL,
  `gia` int(11) NOT NULL,
  `soluong` tinyint(4) NOT NULL,
  `tongcong` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `danhmuc`
--

CREATE TABLE `danhmuc` (
  `id` int(11) NOT NULL,
  `tenmuc` varchar(255) NOT NULL,
  `tenkhongdau` varchar(255) DEFAULT NULL,
  `trangthai` tinyint(2) NOT NULL DEFAULT '1'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `dsyeuthich`
--

CREATE TABLE `dsyeuthich` (
  `iduser` int(11) NOT NULL,
  `idtruyen` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `hoadon`
--

CREATE TABLE `hoadon` (
  `id` int(11) NOT NULL,
  `iduser` int(11) NOT NULL,
  `ngaydathang` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `tongcong` int(11) NOT NULL,
  `tinhtrang` varchar(120) DEFAULT NULL,
  `ngaygiao` date DEFAULT NULL,
  `nguoigiao` int(11) DEFAULT '0',
  `hople` int(1) NOT NULL DEFAULT '0',
  `xem` tinyint(4) NOT NULL DEFAULT '0',
  `ghichu` text
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `lienhe`
--

CREATE TABLE `lienhe` (
  `id` int(11) NOT NULL,
  `nguoigui` varchar(100) DEFAULT NULL,
  `tieude` varchar(250) DEFAULT NULL,
  `noidung` text,
  `xem` tinyint(4) NOT NULL DEFAULT '0',
  `tinhtrang` tinyint(1) NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `nxb`
--

CREATE TABLE `nxb` (
  `id` int(11) NOT NULL,
  `tennxb` varchar(255) NOT NULL,
  `tenkhongdau` varchar(255) DEFAULT NULL,
  `trangthai` tinyint(2) NOT NULL DEFAULT '1'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `tacgia`
--

CREATE TABLE `tacgia` (
  `id` int(11) NOT NULL,
  `tentacgia` varchar(255) NOT NULL,
  `tenkhongdau` varchar(255) DEFAULT NULL,
  `trangthai` tinyint(2) NOT NULL DEFAULT '1'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `thongbao`
--

CREATE TABLE `thongbao` (
  `id` int(11) NOT NULL,
  `nguoigui` varchar(100) DEFAULT NULL,
  `tieude` varchar(250) DEFAULT NULL,
  `noidung` varchar(200) DEFAULT NULL,
  `idtruyen` int(11) NOT NULL,
  `xem` tinyint(4) NOT NULL DEFAULT '0',
  `tinhtrang` tinyint(1) NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `tinhtrangdonhang`
--

CREATE TABLE `tinhtrangdonhang` (
  `id` int(11) NOT NULL,
  `idhoadon` int(11) NOT NULL,
  `noidung` varchar(200) DEFAULT NULL,
  `ghichu` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `tintuc`
--

CREATE TABLE `tintuc` (
  `id` int(11) NOT NULL,
  `tieude` varchar(255) NOT NULL,
  `tieudekhongdau` varchar(255) NOT NULL,
  `tomtat` text,
  `noidung` longtext,
  `hinh` varchar(255) DEFAULT NULL,
  `trangthai` tinyint(1) NOT NULL DEFAULT '1'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `truyen`
--

CREATE TABLE `truyen` (
  `id` int(11) NOT NULL,
  `tentruyen` varchar(255) NOT NULL,
  `tenkhongdau` varchar(255) DEFAULT NULL,
  `madanhmuc` int(11) NOT NULL,
  `matacgia` int(11) DEFAULT NULL,
  `manxb` int(11) DEFAULT NULL,
  `gia` int(11) NOT NULL,
  `soluong` smallint(6) NOT NULL,
  `hinh` text,
  `ngayxuatban` date DEFAULT NULL,
  `kichthuoc` varchar(30) DEFAULT NULL,
  `trongluong` int(11) DEFAULT NULL,
  `bia` varchar(50) DEFAULT NULL,
  `tinhtrang` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL DEFAULT 'Luôn có',
  `noidung` text,
  `giamgia` int(11) DEFAULT NULL,
  `soluongdaban` int(11) NOT NULL DEFAULT '0',
  `trangthai` tinyint(1) NOT NULL DEFAULT '1'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `id` int(11) NOT NULL,
  `ten` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `diachi` text NOT NULL,
  `dienthoai` varchar(15) NOT NULL,
  `machucvu` int(11) NOT NULL,
  `kickhoat` tinyint(4) NOT NULL DEFAULT '0',
  `theodoi` tinyint(1) NOT NULL DEFAULT '0',
  `passcode` varchar(120) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `chucvu`
--
ALTER TABLE `chucvu`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `cthoadon`
--
ALTER TABLE `cthoadon`
  ADD PRIMARY KEY (`id`),
  ADD KEY `idhoadon` (`idhoadon`),
  ADD KEY `idtruyen` (`idtruyen`);

--
-- Indexes for table `danhmuc`
--
ALTER TABLE `danhmuc`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `dsyeuthich`
--
ALTER TABLE `dsyeuthich`
  ADD PRIMARY KEY (`iduser`,`idtruyen`),
  ADD KEY `idtruyen` (`idtruyen`);

--
-- Indexes for table `hoadon`
--
ALTER TABLE `hoadon`
  ADD PRIMARY KEY (`id`),
  ADD KEY `iduser` (`iduser`),
  ADD KEY `nguoigiao` (`nguoigiao`);

--
-- Indexes for table `lienhe`
--
ALTER TABLE `lienhe`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `nxb`
--
ALTER TABLE `nxb`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `tacgia`
--
ALTER TABLE `tacgia`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `thongbao`
--
ALTER TABLE `thongbao`
  ADD PRIMARY KEY (`id`),
  ADD KEY `idtruyen` (`idtruyen`);

--
-- Indexes for table `tinhtrangdonhang`
--
ALTER TABLE `tinhtrangdonhang`
  ADD PRIMARY KEY (`id`),
  ADD KEY `idhoadon` (`idhoadon`);

--
-- Indexes for table `tintuc`
--
ALTER TABLE `tintuc`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `truyen`
--
ALTER TABLE `truyen`
  ADD PRIMARY KEY (`id`),
  ADD KEY `madanhmuc` (`madanhmuc`),
  ADD KEY `manxb` (`manxb`),
  ADD KEY `matacgia` (`matacgia`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`),
  ADD KEY `machucvu` (`machucvu`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `chucvu`
--
ALTER TABLE `chucvu`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `cthoadon`
--
ALTER TABLE `cthoadon`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `danhmuc`
--
ALTER TABLE `danhmuc`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `hoadon`
--
ALTER TABLE `hoadon`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `nxb`
--
ALTER TABLE `nxb`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `tacgia`
--
ALTER TABLE `tacgia`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `thongbao`
--
ALTER TABLE `thongbao`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `tinhtrangdonhang`
--
ALTER TABLE `tinhtrangdonhang`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `tintuc`
--
ALTER TABLE `tintuc`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `truyen`
--
ALTER TABLE `truyen`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
--
-- Constraints for dumped tables
--

--
-- Constraints for table `cthoadon`
--
ALTER TABLE `cthoadon`
  ADD CONSTRAINT `cthoadon_ibfk_1` FOREIGN KEY (`idhoadon`) REFERENCES `hoadon` (`id`),
  ADD CONSTRAINT `cthoadon_ibfk_2` FOREIGN KEY (`idtruyen`) REFERENCES `truyen` (`id`);

--
-- Constraints for table `dsyeuthich`
--
ALTER TABLE `dsyeuthich`
  ADD CONSTRAINT `dsyeuthich_ibfk_1` FOREIGN KEY (`idtruyen`) REFERENCES `truyen` (`id`),
  ADD CONSTRAINT `dsyeuthich_ibfk_2` FOREIGN KEY (`iduser`) REFERENCES `users` (`id`);

--
-- Constraints for table `hoadon`
--
ALTER TABLE `hoadon`
  ADD CONSTRAINT `hoadon_ibfk_1` FOREIGN KEY (`iduser`) REFERENCES `users` (`id`),
  ADD CONSTRAINT `hoadon_ibfk_2` FOREIGN KEY (`nguoigiao`) REFERENCES `users` (`id`);

--
-- Constraints for table `thongbao`
--
ALTER TABLE `thongbao`
  ADD CONSTRAINT `thongbao_ibfk_1` FOREIGN KEY (`idtruyen`) REFERENCES `truyen` (`id`);

--
-- Constraints for table `tinhtrangdonhang`
--
ALTER TABLE `tinhtrangdonhang`
  ADD CONSTRAINT `tinhtrangdonhang_ibfk_1` FOREIGN KEY (`idhoadon`) REFERENCES `hoadon` (`id`);

--
-- Constraints for table `truyen`
--
ALTER TABLE `truyen`
  ADD CONSTRAINT `truyen_ibfk_1` FOREIGN KEY (`madanhmuc`) REFERENCES `danhmuc` (`id`),
  ADD CONSTRAINT `truyen_ibfk_2` FOREIGN KEY (`manxb`) REFERENCES `nxb` (`id`),
  ADD CONSTRAINT `truyen_ibfk_3` FOREIGN KEY (`matacgia`) REFERENCES `tacgia` (`id`);

--
-- Constraints for table `users`
--
ALTER TABLE `users`
  ADD CONSTRAINT `users_ibfk_1` FOREIGN KEY (`machucvu`) REFERENCES `chucvu` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
