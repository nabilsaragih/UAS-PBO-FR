-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3306
-- Generation Time: Jun 05, 2024 at 04:15 PM
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
-- Database: `uas-pbo`
--

-- --------------------------------------------------------

--
-- Table structure for table `data_produk`
--

CREATE TABLE `data_produk` (
  `id_produk` int NOT NULL,
  `nama_produk` text NOT NULL,
  `jenis_produk` text NOT NULL,
  `mutu_produk` text NOT NULL,
  `tanggal_produksi` date NOT NULL,
  `tanggal_expired` date NOT NULL,
  `gambar_produk` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `data_produk`
--

INSERT INTO `data_produk` (`id_produk`, `nama_produk`, `jenis_produk`, `mutu_produk`, `tanggal_produksi`, `tanggal_expired`, `gambar_produk`) VALUES
(1, 'zxcvjakdkla', 'Hydantoine', 'Feed Additive', '2024-06-21', '2024-06-19', 'file:/C:/Users/nabil/Downloads/photo.png'),
(3, 'asdffsefe', 'Ca Glycine', 'Pharmaceutical Standard', '2024-06-19', '2024-06-28', 'file:/C:/Users/nabil/Downloads/avatar.png'),
(4, 'test', 'Glycine Carbonate', 'Industrial Grade', '2024-06-19', '2024-06-29', 'file:/C:/Users/nabil/Downloads/kitten.png');

-- --------------------------------------------------------

--
-- Table structure for table `user_creds`
--

CREATE TABLE `user_creds` (
  `id` int NOT NULL,
  `username` text NOT NULL,
  `password` text NOT NULL,
  `role` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `user_creds`
--

INSERT INTO `user_creds` (`id`, `username`, `password`, `role`) VALUES
(1, 'admin', 'admin', 'admin'),
(2, 'admin', 'admin', 'admin'),
(3, 'user', 'user', 'user');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `data_produk`
--
ALTER TABLE `data_produk`
  ADD PRIMARY KEY (`id_produk`);

--
-- Indexes for table `user_creds`
--
ALTER TABLE `user_creds`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `data_produk`
--
ALTER TABLE `data_produk`
  MODIFY `id_produk` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `user_creds`
--
ALTER TABLE `user_creds`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
