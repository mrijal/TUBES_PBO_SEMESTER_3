-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3306
-- Generation Time: Feb 05, 2025 at 02:07 PM
-- Server version: 8.0.30
-- PHP Version: 8.1.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `asset_management`
--

-- --------------------------------------------------------

--
-- Table structure for table `activity_logs`
--

CREATE TABLE `activity_logs` (
  `id_log` int NOT NULL,
  `id_user` int NOT NULL,
  `action` varchar(255) NOT NULL,
  `id_asset` int DEFAULT NULL,
  `timestamp` timestamp NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `activity_logs`
--

INSERT INTO `activity_logs` (`id_log`, `id_user`, `action`, `id_asset`, `timestamp`) VALUES
(1, 1, 'Mengajukan aset', NULL, '2025-02-01 09:56:32'),
(2, 2, 'Mengajukan aset', NULL, '2025-02-01 09:57:29'),
(3, 1, 'Mengubah status aset menjadi approved', NULL, '2025-02-01 09:59:46'),
(4, 1, 'Mengubah status aset menjadi approved', NULL, '2025-02-01 09:59:50'),
(5, 2, 'Mengajukan aset', NULL, '2025-02-01 16:04:49'),
(6, 2, 'Mengajukan aset', NULL, '2025-02-01 17:00:26'),
(7, 2, 'Mengajukan aset', NULL, '2025-02-01 17:00:44'),
(8, 2, 'Mengajukan aset', NULL, '2025-02-01 17:02:53'),
(9, 2, 'Mengajukan aset', NULL, '2025-02-01 17:06:41'),
(10, 2, 'Mengajukan aset', NULL, '2025-02-01 17:08:08'),
(11, 1, 'Mengubah status aset menjadi approved', NULL, '2025-02-01 17:08:29'),
(12, 1, 'Mengubah status aset menjadi approved', NULL, '2025-02-01 17:08:32'),
(13, 1, 'Mengubah status aset menjadi approved', NULL, '2025-02-01 17:08:34'),
(14, 1, 'Mengubah status aset menjadi approved', NULL, '2025-02-01 17:08:36'),
(15, 1, 'Mengubah status aset menjadi approved', NULL, '2025-02-01 17:08:39'),
(16, 1, 'Mengubah status aset menjadi approved', NULL, '2025-02-01 17:08:41'),
(17, 2, 'Mengajukan aset', NULL, '2025-02-01 17:15:08'),
(18, 2, 'Mengajukan aset', NULL, '2025-02-01 17:18:35'),
(19, 2, 'Mengajukan aset', NULL, '2025-02-01 17:52:33'),
(20, 2, 'Mengajukan aset', NULL, '2025-02-01 18:21:29'),
(21, 2, 'Mengajukan aset', 21, '2025-02-02 06:03:31'),
(22, 1, 'Mengubah status aset menjadi approved', NULL, '2025-02-04 01:56:30'),
(23, 1, 'Mengubah status aset menjadi rejected', 21, '2025-02-04 01:56:33'),
(24, 2, 'Mengajukan aset', 22, '2025-02-04 03:07:28'),
(25, 2, 'Mengajukan aset', 23, '2025-02-04 03:09:53'),
(26, 2, 'Mengajukan aset', 24, '2025-02-04 03:23:13'),
(27, 1, 'Mengubah status aset menjadi approved', 24, '2025-02-04 03:23:31'),
(28, 1, 'Mengubah status aset menjadi approved', 23, '2025-02-04 03:23:33'),
(29, 2, 'Mengajukan aset', 25, '2025-02-04 03:34:16'),
(30, 2, 'Mengajukan aset', 26, '2025-02-04 03:50:53'),
(31, 2, 'Mengajukan aset', 27, '2025-02-04 03:53:04'),
(32, 2, 'Mengajukan aset', 28, '2025-02-04 03:58:27'),
(33, 2, 'Mengajukan aset', 29, '2025-02-04 04:02:31'),
(34, 2, 'Mengajukan aset', 30, '2025-02-04 04:05:00'),
(35, 2, 'Mengajukan aset', 31, '2025-02-04 04:05:34'),
(36, 2, 'Mengajukan aset', 32, '2025-02-04 04:24:26'),
(37, 2, 'Mengajukan aset', 33, '2025-02-04 04:31:15'),
(38, 1, 'Mengubah status aset menjadi approved', 33, '2025-02-04 04:33:03'),
(39, 1, 'Mengubah status aset menjadi approved', 32, '2025-02-04 04:33:06'),
(40, 1, 'Mengubah status aset menjadi approved', 31, '2025-02-04 04:33:08'),
(41, 1, 'Mengubah status aset menjadi approved', 30, '2025-02-04 04:33:10'),
(42, 1, 'Mengubah status aset menjadi approved', 29, '2025-02-04 04:33:12');

-- --------------------------------------------------------

--
-- Table structure for table `assets`
--

CREATE TABLE `assets` (
  `id_asset` int NOT NULL,
  `name` varchar(100) NOT NULL,
  `category` varchar(50) NOT NULL,
  `description` text NOT NULL,
  `status` enum('pending','approved','rejected') DEFAULT 'pending',
  `created_by` int NOT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `barcode_path` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `assets`
--

INSERT INTO `assets` (`id_asset`, `name`, `category`, `description`, `status`, `created_by`, `created_at`, `barcode_path`) VALUES
(21, 'mototr', 'Kendaraan', 'test	', 'rejected', 2, '2025-02-02 06:03:31', NULL),
(22, 'barcode', 'Kendaraan', 'barcode', 'pending', 2, '2025-02-04 03:07:28', 'src/main/resources/barcodes/asset_barcode.png'),
(23, 'VAPE', 'Elektronik', 'Vapes', 'approved', 2, '2025-02-04 03:09:53', 'src/main/resources/barcodes/asset_VAPE.png'),
(24, 'AIO', 'Elektronik', 'centaurus', 'approved', 2, '2025-02-04 03:23:13', 'src/main/resources/barcodes/asset_AIO.png'),
(25, 'Motor', 'Kendaraan', 'Motor CBR', 'pending', 2, '2025-02-04 03:34:16', 'src/main/resources/barcodes/asset_Motor.png'),
(26, 'Mobil', 'Kendaraan', 'sigra', 'pending', 2, '2025-02-04 03:50:53', 'src/main/resources/barcodes/asset_Mobil.png'),
(27, 'Motor', 'Elektronik', 'Motor CBR', 'pending', 2, '2025-02-04 03:53:04', 'src/main/resources/barcodes/asset_Motor.png'),
(28, 'mobil', 'Elektronik', 'mobilio', 'pending', 2, '2025-02-04 03:58:27', 'src/main/resources/barcodes/asset_mobil.png'),
(29, 'mobil', 'Properti', 'rumah\n', 'approved', 2, '2025-02-04 04:02:31', 'src/main/resources/barcodes/asset_mobil.png'),
(30, 'motor', 'Properti', 'moo	', 'approved', 2, '2025-02-04 04:05:00', 'src/main/resources/barcodes/asset_motor.png'),
(31, 'rumah', 'Properti', 'apart', 'approved', 2, '2025-02-04 04:05:34', 'src/main/resources/barcodes/asset_rumah.png'),
(32, 'youth', 'Lainnya', 'kami', 'approved', 2, '2025-02-04 04:24:26', 'src/main/resources/barcodes/asset_youth.png'),
(33, 'test path', 'Kendaraan', 'path	', 'approved', 2, '2025-02-04 04:31:15', 'src/main/resources/barcodes/asset_test path.png');

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `id_user` int NOT NULL,
  `username` varchar(50) NOT NULL,
  `password` varchar(100) NOT NULL,
  `role` enum('admin','user') NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`id_user`, `username`, `password`, `role`) VALUES
(1, 'admin', '123', 'admin'),
(2, 'user', '123', 'user');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `activity_logs`
--
ALTER TABLE `activity_logs`
  ADD PRIMARY KEY (`id_log`),
  ADD KEY `id_user` (`id_user`),
  ADD KEY `id_asset` (`id_asset`);

--
-- Indexes for table `assets`
--
ALTER TABLE `assets`
  ADD PRIMARY KEY (`id_asset`),
  ADD KEY `created_by` (`created_by`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id_user`),
  ADD UNIQUE KEY `username` (`username`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `activity_logs`
--
ALTER TABLE `activity_logs`
  MODIFY `id_log` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=43;

--
-- AUTO_INCREMENT for table `assets`
--
ALTER TABLE `assets`
  MODIFY `id_asset` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=34;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `id_user` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `activity_logs`
--
ALTER TABLE `activity_logs`
  ADD CONSTRAINT `activity_logs_ibfk_1` FOREIGN KEY (`id_user`) REFERENCES `users` (`id_user`) ON DELETE CASCADE,
  ADD CONSTRAINT `activity_logs_ibfk_2` FOREIGN KEY (`id_asset`) REFERENCES `assets` (`id_asset`) ON DELETE SET NULL;

--
-- Constraints for table `assets`
--
ALTER TABLE `assets`
  ADD CONSTRAINT `assets_ibfk_1` FOREIGN KEY (`created_by`) REFERENCES `users` (`id_user`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
