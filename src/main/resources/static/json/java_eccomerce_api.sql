-- phpMyAdmin SQL Dump
-- version 5.0.1
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: Aug 27, 2020 at 04:33 PM
-- Server version: 10.4.11-MariaDB
-- PHP Version: 7.2.28

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `java_eccomerce_api`
--

-- --------------------------------------------------------

--
-- Table structure for table `cards`
--

CREATE TABLE `cards` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `user_id` bigint(20) UNSIGNED NOT NULL,
  `charge_token` text COLLATE utf8mb4_unicode_ci NOT NULL,
  `bin` bigint(20) NOT NULL,
  `ref` varchar(191) COLLATE utf8mb4_unicode_ci NOT NULL,
  `brand` varchar(191) COLLATE utf8mb4_unicode_ci NOT NULL,
  `card_type` varchar(191) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `issuing_country` varchar(191) COLLATE utf8mb4_unicode_ci NOT NULL,
  `exp_month` bigint(20) DEFAULT NULL,
  `exp_year` bigint(20) DEFAULT NULL,
  `last4` bigint(20) DEFAULT NULL,
  `date` varchar(191) COLLATE utf8mb4_unicode_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `cards`
--

INSERT INTO `cards` (`id`, `user_id`, `charge_token`, `bin`, `ref`, `brand`, `card_type`, `issuing_country`, `exp_month`, `exp_year`, `last4`, `date`) VALUES
(6, 10, 'flw-t0-a88baeb82a098862ee15ace6ba840e08-m03k', 418742, 'refGODirect-1591098517603', 'ACCESS BANK PLC DEBITCLASSIC', 'VISA', 'NIGERIA NG', 12, 34, 4246, '2020-07-26 02:18:15'),
(12, 10, 'flw-t0-6d53dde3cc205960711f08ab18ab01d8-m03k', 553188, 'refGODirect-1590768240138', ' CREDIT', 'MASTERCARD', 'NIGERIA NG', 12, 34, 2950, '2020-08-07 15:03:18');

-- --------------------------------------------------------

--
-- Table structure for table `categories`
--

CREATE TABLE `categories` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `name` varchar(191) COLLATE utf8mb4_unicode_ci NOT NULL,
  `slug` varchar(191) COLLATE utf8mb4_unicode_ci NOT NULL,
  `description` text COLLATE utf8mb4_unicode_ci NOT NULL,
  `date` varchar(191) COLLATE utf8mb4_unicode_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `categories`
--

INSERT INTO `categories` (`id`, `name`, `slug`, `description`, `date`) VALUES
(1, 'Smalls', 'smalls', 'smalls', '2020-05-20 10:58:39'),
(2, 'Big', 'big', 'big', '2020-05-20 11:02:05'),
(3, 'mobile', 'mobile', 'Very nice', '2020-07-24 20:05:32'),
(5, 'fresh', 'fresh', 'Very nice', '2020-07-24 20:06:36'),
(6, 'tablet', 'tablet', 'Very nice', '2020-07-24 22:39:10'),
(7, 'iphone', 'iphone', 'Very nice', '2020-07-24 23:44:10');

-- --------------------------------------------------------

--
-- Table structure for table `drivers`
--

CREATE TABLE `drivers` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `fname` varchar(191) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `lname` varchar(191) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `email` varchar(191) COLLATE utf8mb4_unicode_ci NOT NULL,
  `phone` varchar(191) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `status` varchar(191) COLLATE utf8mb4_unicode_ci DEFAULT 'available',
  `lat` double(11,8) DEFAULT NULL,
  `lng` double(11,8) DEFAULT NULL,
  `password` varchar(191) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `date` varchar(191) COLLATE utf8mb4_unicode_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `drivers`
--

INSERT INTO `drivers` (`id`, `fname`, `lname`, `email`, `phone`, `status`, `lat`, `lng`, `password`, `date`) VALUES
(101, 'Adalberto', 'Boyle', 'jamil.kovacek@yahoo.com', '(339) 990-7917', 'available', 6.53585489, 3.51919092, '$2a$10$LmuH1G0tDDbUalsuKEcl0.ZijXDXn6Z4dISnsIx./p1IFXLgOLIbW', '2020-07-26 11:18:58'),
(102, 'Joshuah', 'Terry', 'lizeth.nienow@hotmail.com', '1-176-449-2740', 'available', 6.45336185, 3.42926612, '$2a$10$JTyFcXmIMv6/fEfti9qF5.86qj8mkWmu1VCqZarlzSrlT0ZYgc1ou', '2020-07-26 11:18:58'),
(103, 'Cole', 'Denesik', 'perry.pollich@yahoo.com', '1-622-926-5419 x8327', 'available', 6.45845659, 3.39786285, '$2a$10$cxPEYVjrlwxTT1DazMj2SOfRbNDjO.AcJV4GXTCqnNA4C57Zlf4Zq', '2020-07-26 11:18:59'),
(104, 'Hubert', 'Beahan', 'milan.harris@hotmail.com', '(483) 319-2811 x4229', 'available', 6.49875374, 3.37487710, '$2a$10$oeVC7bDMrdFj1aafXZ08NOWpw4.C71AGvX3WAImLxt0eoTdUp0Ejy', '2020-07-26 11:18:59'),
(105, 'Jamarcus', 'Schiller', 'trent.graham@yahoo.com', '1-665-155-2053 x903', 'available', 6.45253746, 3.42795370, '$2a$10$AP82h69SRUjkVI4BWwdUpecep4XCROwdZgdEQ30KAaQbgP4hqKEcO', '2020-07-26 11:18:59'),
(106, 'Sim', 'Howe', 'dorcas.dooley@hotmail.com', '(229) 776-9901 x12997', 'available', 6.44957696, 3.39406391, '$2a$10$y/Ie8hwmzu5Oli8xcH8bmOp8.OLO31QKuBJWEoUp0C63YmhVLef/y', '2020-07-26 11:18:59'),
(107, 'Narciso', 'Rowe', 'athena.daugherty@yahoo.com', '835-649-7441 x700', 'available', 6.43234497, 3.43221704, '$2a$10$p0TiDk8MJMfqbCk0Ph7q0ufCzvFVKzVob3NHQ62S9arT.1R9.l0K2', '2020-07-26 11:19:00'),
(108, 'Shad', 'Kuphal', 'lea.doyle@yahoo.com', '450-449-4214', 'available', 6.43752788, 3.40548128, '$2a$10$jyDD6dJP3Ec6xPdcGYApneWypaIYQZxfzg0zwBI.Wsz62f4lzxTFO', '2020-07-26 11:19:00'),
(109, 'Ally', 'Pollich', 'kariane.bernier@yahoo.com', '1-213-165-0734 x28997', 'available', 6.39934718, 3.42169645, '$2a$10$DIdVen//6MT6UMD8b/g4zeQ7KA8.1i4vWd27Ar2PQzfEhNSFe1v6S', '2020-07-26 11:19:00'),
(110, 'Wilmer', 'Reichel', 'blair.barton@gmail.com', '366-080-3988 x867', 'available', 6.58927231, 3.37999248, '$2a$10$LKMyf0qFrxPOXAU65tliGepHvJhUYFZY9V3RQJKZkX.0g99YQQ65O', '2020-07-26 11:19:00'),
(111, 'Destany', 'Koepp', 'fay.runolfsdottir@yahoo.com', '978-439-1206', 'available', 6.53585489, 3.51919092, '$2a$10$axzpOZSyqumOIMnL6cjOJ.nnlLqU.Kv9WkDd/NnbsZOZfu0qFI6CW', '2020-07-26 11:19:00'),
(112, 'Rozella', 'Wuckert', 'vaughn.kohler@hotmail.com', '1-181-270-5896', 'available', 6.52717216, 3.38092213, '$2a$10$sFaknKs8q7D/A8adSvbyqee85e3Evky6tRHUp5CoCiX.oOioNJCKy', '2020-07-26 11:19:01'),
(113, 'Kamryn', 'Leuschke', 'micaela.beier@yahoo.com', '769-552-1989 x17002', 'available', 6.58533056, 3.45609827, '$2a$10$7/dQrUQyX.rF6MS4ROuo..Y/kr4hbkS.FyTpDzyM1/ESPs54/rZO.', '2020-07-26 11:19:01'),
(114, 'Earl', 'Willms', 'brayan.greenholt@hotmail.com', '389.574.2489 x43198', 'available', 6.52956710, 3.29803616, '$2a$10$znQIKsLg/t40tx5lvTBP1OZS7i46uagKOGLCKPrQKbWeCUMQRFzbe', '2020-07-26 11:19:01'),
(115, 'Breanna', 'Halvorson', 'jacklyn.terry@hotmail.com', '932-819-7546', 'available', 6.53057480, 3.48830353, '$2a$10$KNxKeN6QwZp0k.hNWONB8.oXZndDPes6inUpQQSPhUdUd0TEr8F2q', '2020-07-26 11:19:01'),
(116, 'Abdullah', 'Berge', 'norval.willms@yahoo.com', '1-337-317-0904', 'available', 6.42260054, 3.49156917, '$2a$10$CLMg4VTpGXeq9bpWy0t9kumhk24R4vibA9o0E6RJ1i0LQzOToT2ou', '2020-07-26 11:19:01'),
(117, 'Jean', 'Abernathy', 'marjolaine.rowe@yahoo.com', '1-629-913-1330 x5339', 'available', 6.41942366, 3.43075346, '$2a$10$ySHjWrTTc2SAKjUg0C/PX.lJFMSyJX8VO9taiDQnSZyZOHeSN2toq', '2020-07-26 11:19:01'),
(118, 'Alexander', 'Stoltenberg', 'laurel.kertzmann@yahoo.com', '782.863.7451', 'available', 6.53585489, 3.51919092, '$2a$10$4WChrG.rlJRTZV7dURsjT.9BAMjTBYQe7qObCA9BkrRyh9yDyUGHK', '2020-07-26 11:19:01'),
(119, 'Christian', 'Stanton', 'millie.monahan@yahoo.com', '365-706-6056 x26733', 'available', 6.49194592, 3.30597321, '$2a$10$mYuC8P5ZRFnr3gUBDp6.luRO/kELj0n5QdHYykzSY9OYjtDaSKWb2', '2020-07-26 11:19:02'),
(120, 'Leon', 'Cartwright', 'eula.veum@gmail.com', '(787) 830-4545 x866', 'available', 6.42653518, 3.27948923, '$2a$10$wXoa6bt3NCw/k/f9U4t8/.dLKhqod.2E2QpJml/pZAGfFwisim5Ue', '2020-07-26 11:19:02'),
(121, 'Clifton', 'Ernser', 'maia.powlowski@yahoo.com', '729-861-4275', 'available', 6.38371541, 3.34312127, '$2a$10$LqmZgPvPCZX1pyY7Ikxw1e2LLT8Xl9exnt97tJdzh8jn05KK08l3.', '2020-07-26 11:19:02'),
(122, 'Rickey', 'Fahey', 'payton.steuber@gmail.com', '873.445.2144', 'available', 6.36177034, 3.38595920, '$2a$10$qJ.OK9PLjC6MOYgk0GYxOOc0femyjIzGONgQkBAxe41xD5jrYWrq2', '2020-07-26 11:19:02'),
(123, 'Giovanna', 'Funk', 'zita.flatley@gmail.com', '(865) 469-0191', 'available', 6.44432668, 3.27860190, '$2a$10$m3FuMruPe/e66j8y4U710OH6QLos20ZSUPHFZray0S09Fj8SONsWy', '2020-07-26 11:19:02'),
(124, 'Griffin', 'Harvey', 'wilfrid.walter@yahoo.com', '(665) 645-5833', 'available', 6.44496539, 3.36184367, '$2a$10$wUtT6PFEMvAJnLk6tC8Rc.2PzCQ1AgTMlLkaC4TD4oQiV2DcP0SSq', '2020-07-26 11:19:02'),
(125, 'Clair', 'Lakin', 'ewell.nitzsche@hotmail.com', '(178) 952-8915', 'available', 6.56975097, 3.45049093, '$2a$10$vywaPdRK6haRVzpTopGSeuO24CAIk8OAFEO8p.cCUP/PeXPFlQ1B2', '2020-07-26 11:19:02'),
(126, 'Lourdes', 'Shanahan', 'ambrose.kuhic@hotmail.com', '(947) 414-4048 x06619', 'available', 6.40142917, 3.43097683, '$2a$10$SFjEbndCjNUHJT7zXjwntuxmypl7BaAOTai6Qj1yr07UuO0jyxL4u', '2020-07-26 11:19:02'),
(127, 'Clay', 'Oberbrunner', 'camila.wilkinson@yahoo.com', '472-304-5146', 'available', 6.47910588, 3.53295803, '$2a$10$xaxpYHqwkBx3xodcUhKSzu0dNvAfWwcKajlB64hFhMTS7eGkfyn/C', '2020-07-26 11:19:03'),
(128, 'Gerardo', 'Cremin', 'jules.kassulke@yahoo.com', '1-872-817-3689 x473', 'available', 6.38721142, 3.41093303, '$2a$10$llrUVHBWz2gCuo6X4vb.b.pgZytQX1Oa99UGlScc9dH0t9CSfk/iC', '2020-07-26 11:19:03'),
(129, 'Carlos', 'Kunze', 'brody.hayes@yahoo.com', '601.109.7571', 'available', 6.59372464, 3.39452046, '$2a$10$eG3vwC7U5tWKDf8NDYyFPunQfdxqnY1Q.jKr0F1QwhVI5o00SIhUq', '2020-07-26 11:19:03'),
(130, 'Amaya', 'Runolfsson', 'winifred.rippin@hotmail.com', '552.201.5238', 'available', 6.47433802, 3.31550802, '$2a$10$WEHCFrU7fc9Mj0P9AROgNupPCSEKf3En//C1.K4OQykVCVHljO.XK', '2020-07-26 11:19:03'),
(131, 'Bo', 'Jacobson', 'orion.kihn@gmail.com', '982.930.3781 x86133', 'available', 6.37246793, 3.42305564, '$2a$10$l9bM.KhfSzSSt4ZayU8U8.RUNVzYZI9Wxf9qcvmyYHDgWIq5MAhaG', '2020-07-26 11:19:03'),
(132, 'Selena', 'Wiegand', 'juwan.quitzon@hotmail.com', '315.733.3510 x5690', 'available', 6.49716481, 3.33273629, '$2a$10$mUyZjhNhJUadGM42v5E29umL1tIYhCKd3139VaNcexETxI8ob88nO', '2020-07-26 11:19:03'),
(133, 'Rylan', 'Bernhard', 'nova.mckenzie@yahoo.com', '146-570-7999', 'available', 6.50122160, 3.34913581, '$2a$10$gxQmVHXwKTxcfCKqyAzi8uWzHh2GuojU5Ec7qeL0eYpxJanGdkxE.', '2020-07-26 11:19:03'),
(134, 'Shayna', 'Baumbach', 'quincy.ferry@yahoo.com', '(443) 205-6958 x6003', 'available', 6.46025923, 3.40667303, '$2a$10$LUu.EVTKBw18OHAWaS9NqujUlUrVfsCY/KvuVMHgfCQRpPrT6q2Ge', '2020-07-26 11:19:03'),
(135, 'Bianka', 'Zemlak', 'alisha.ratke@hotmail.com', '298.584.8906', 'available', 6.47229849, 3.52155028, '$2a$10$KhNhpkuzg6BDLHgoH/daw.8vNy106Uj//cjnz1MSdEWIW7C18M/x.', '2020-07-26 11:19:04'),
(136, 'Darby', 'Wisoky', 'delia.gaylord@hotmail.com', '647.883.3056', 'available', 6.45709080, 3.42811088, '$2a$10$/8MxTHw2I/7jif3IsCghyulu1YIrCeU5k1GnKg74a6woxAw2NhYQG', '2020-07-26 11:19:04'),
(137, 'Lester', 'Stehr', 'johnnie.aufderhar@gmail.com', '225.916.9748 x476', 'available', 6.44288431, 3.37508097, '$2a$10$BMDK/rsyhBq.6wLdTF.9OOlek2gz7/7VMImHXVHaowsCU1cf9R3YK', '2020-07-26 11:19:04'),
(138, 'Victoria', 'Brakus', 'olaf.rau@hotmail.com', '176.748.6240 x05973', 'available', 6.56124092, 3.33272164, '$2a$10$9c2d/Uj7SlMex7/ytO4mp.iu6fuDnPURCASLBMDFxyLv4Kg1.SZ2q', '2020-07-26 11:19:04'),
(139, 'Dawn', 'Kassulke', 'shaina.barton@hotmail.com', '1-646-683-4475 x68640', 'available', 6.46830648, 3.43647831, '$2a$10$OWqEBYDBHT3810AseDJJJ.sE6A2fJpI.lcyz15iZkZrTaCKE0RPCO', '2020-07-26 11:19:04'),
(140, 'Birdie', 'Batz', 'jermain.beier@hotmail.com', '(687) 981-0835 x2603', 'available', 6.52214988, 3.29382404, '$2a$10$xDz7KGqKHc5ooQx9VUfmuuznXt77xij/Ii8oiTz/oJt9H4SlyFRRi', '2020-07-26 11:19:04'),
(141, 'Myrtice', 'Heathcote', 'frida.hudson@hotmail.com', '800-370-7468', 'available', 6.58019324, 3.34878387, '$2a$10$CM.rfS7ucMKJ4GSMEJom.O0oWHyJEj6iEFrcbGZuJrb2SKFFZfJdK', '2020-07-26 11:19:04'),
(142, 'Madelyn', 'Weber', 'hulda.feeney@yahoo.com', '(646) 689-6832', 'available', 6.37246793, 3.42305564, '$2a$10$2Ttk9IzPMYj4HZb6/r4xnemBwfVGp5guP54fghjzBOOiRzZ7Ax83y', '2020-07-26 11:19:04'),
(143, 'Jody', 'Gottlieb', 'imogene.wiegand@hotmail.com', '873.707.3837 x237', 'available', 6.44038682, 3.51034704, '$2a$10$CGNpszzyCX7Ly0TicudmuO33r2c1b0k6auHjVmaSF8WPhxMSklPfi', '2020-07-26 11:19:04'),
(144, 'Jameson', 'Russel', 'rupert.ruecker@gmail.com', '486.133.9150 x30450', 'available', 6.36925669, 3.35467801, '$2a$10$8eZcpxIo1TiWa3DOxoYXSuxzMMc6XgyH8w6xIxA1PaYdE4i.X0bSy', '2020-07-26 11:19:05'),
(145, 'Leta', 'Nolan', 'al.carter@hotmail.com', '951-662-5900 x414', 'available', 6.38939531, 3.35520063, '$2a$10$Mj0mxBtr6Dsd/CHBWH65BuL5ZbYqvPmZT.dH.pZKRWTJ6yCAwn1NO', '2020-07-26 11:19:05'),
(146, 'Zachariah', 'Bradtke', 'bertrand.wiegand@gmail.com', '947.070.1034 x578', 'available', 6.40909866, 3.37938716, '$2a$10$TsEQfsg5mDpqNfPYgV23kuJUq4xtv4KvWNYi4jDnc7FMKrbbYHE9a', '2020-07-26 11:19:05'),
(147, 'Adella', 'Barrows', 'ted.raynor@hotmail.com', '(569) 542-1740 x41656', 'available', 6.45274498, 3.33683067, '$2a$10$MXQ9aW6gGztMpuRrIEi38uLqBqWimVx0wnRLnlrncE2G6SFIDzzne', '2020-07-26 11:19:05'),
(148, 'Darryl', 'Blanda', 'frederick.hagenes@gmail.com', '(882) 562-7380', 'available', 6.55948451, 3.45998607, '$2a$10$DwTLbVSWpameB1rxh3ZKtuXDNZ4CM3HeIc9OsNjpWUj3GbzMgGIwO', '2020-07-26 11:19:05'),
(149, 'Clotilde', 'Fahey', 'griffin.murazik@gmail.com', '1-685-757-9255', 'available', 6.44391526, 3.42441695, '$2a$10$0nmUoSPBdCpN8UYX33BCf.pPgF2d8bBOFNtK5T.8gcfO/Knv7Qt/m', '2020-07-26 11:19:05'),
(150, 'Miles', 'Borer', 'lexus.casper@hotmail.com', '1-358-613-7672 x033', 'available', 6.44838852, 3.42108008, '$2a$10$jKH/kHQRhN.hMpzmvgORwOhQ1c4dGZ7.FD.7VVL8.cypuGRLCoQFy', '2020-07-26 11:19:05');

-- --------------------------------------------------------

--
-- Table structure for table `orders`
--

CREATE TABLE `orders` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `total_price` varchar(191) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `user_id` bigint(20) UNSIGNED NOT NULL,
  `user_address_id` bigint(20) UNSIGNED DEFAULT NULL,
  `code` varchar(191) COLLATE utf8mb4_unicode_ci NOT NULL,
  `status` varchar(191) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `date` timestamp NULL DEFAULT NULL,
  `delivery_address` text COLLATE utf8mb4_unicode_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `orders`
--

INSERT INTO `orders` (`id`, `total_price`, `user_id`, `user_address_id`, `code`, `status`, `date`, `delivery_address`) VALUES
(1, '5200', 10, NULL, 'e0fOZ7kQ', 'ordered', '2020-07-26 19:53:06', NULL),
(2, '5200', 10, 3, 'kcKmoVb2', 'ordered', '2020-07-27 08:26:58', 'Maryland Third Mainland'),
(3, '5200', 10, 3, 'OqTlw6F2', 'ordered', '2020-08-07 21:48:02', 'Maryland Third Mainland'),
(5, '5200', 10, 3, 'VHHzz92X', 'ordered', '2020-08-07 21:59:51', 'Maryland Third Mainland'),
(6, '5200', 10, 3, 'KmPlxxEg', 'ordered', '2020-08-07 22:24:46', 'Maryland Third Mainland');

-- --------------------------------------------------------

--
-- Table structure for table `order_items`
--

CREATE TABLE `order_items` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `order_id` bigint(20) UNSIGNED NOT NULL,
  `product_id` bigint(20) UNSIGNED NOT NULL,
  `quantity` bigint(20) DEFAULT NULL,
  `unit_price` bigint(20) DEFAULT NULL,
  `date` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `order_items`
--

INSERT INTO `order_items` (`id`, `order_id`, `product_id`, `quantity`, `unit_price`, `date`) VALUES
(1, 1, 2, 4, 1000, '2020-07-26 19:53:06'),
(2, 1, 3, 2, 600, '2020-07-26 19:53:06'),
(3, 2, 2, 4, 1000, '2020-07-27 08:26:58'),
(4, 2, 3, 2, 600, '2020-07-27 08:26:58'),
(5, 3, 2, 4, 1000, '2020-08-07 21:48:02'),
(6, 3, 3, 2, 600, '2020-08-07 21:48:02'),
(7, 5, 2, 4, 1000, '2020-08-07 21:59:51'),
(8, 5, 3, 2, 600, '2020-08-07 21:59:51'),
(9, 6, 2, 4, 1000, '2020-08-07 22:24:46'),
(10, 6, 3, 2, 600, '2020-08-07 22:24:46');

-- --------------------------------------------------------

--
-- Table structure for table `permissions`
--

CREATE TABLE `permissions` (
  `id` int(10) UNSIGNED NOT NULL,
  `name` varchar(191) COLLATE utf8mb4_unicode_ci NOT NULL,
  `guard_name` varchar(191) COLLATE utf8mb4_unicode_ci NOT NULL,
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `products`
--

CREATE TABLE `products` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `name` varchar(191) COLLATE utf8mb4_unicode_ci NOT NULL,
  `status` tinyint(1) DEFAULT 1,
  `category_id` bigint(20) UNSIGNED NOT NULL,
  `picture` varchar(191) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `description` text COLLATE utf8mb4_unicode_ci NOT NULL,
  `price` bigint(20) NOT NULL,
  `quantity` bigint(20) DEFAULT NULL,
  `date` varchar(191) COLLATE utf8mb4_unicode_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `products`
--

INSERT INTO `products` (`id`, `name`, `status`, `category_id`, `picture`, `description`, `price`, `quantity`, `date`) VALUES
(2, 'Big Gas', 1, 2, 'http://127.0.0.1:8000/uploads/1589973847.jpeg', 'A nice product', 1000, 4, '2020-05-20 11:22:35'),
(3, 'Small Gas', 1, 2, 'http://127.0.0.1:8000/uploads/1594032309.jpeg', 'A nice product', 600, 7, '2020-07-06 10:45:09'),
(4, 'iphone', 0, 2, NULL, 'Very nice', 6700, 30, '2020-07-25 00:19:03'),
(5, 'Mac book', 1, 3, NULL, 'Very nice', 6700, 30, '2020-07-25 00:20:02');

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `name` varchar(250) NOT NULL,
  `username` varchar(191) NOT NULL,
  `email` varchar(250) NOT NULL,
  `wallet_balance` int(11) NOT NULL,
  `phone` varchar(250) NOT NULL,
  `password` varchar(250) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`id`, `name`, `username`, `email`, `wallet_balance`, `phone`, `password`) VALUES
(3, 'Ademola', 'Ade', 'ade@gmail.com', 0, '089499494904', '$2a$10$qZ5obEGY2WPKZBp3KrHeoO45K.LkYPKFBaj3nJ1/0jfwpRAba2EcG'),
(5, 'Ademola', 'Bola', 'bola@gmail.com', 0, '089499494904', '$2a$10$AKgwh2dtDRxrhZVLsVa3cOWQiCebnDU8ZzhsnYWKDsRoNEzG90ni.'),
(6, 'Tade Kun', 'Tade', 'tade@gmail.com', 0, '089499494904', '$2a$10$PvKCu2VMssER0U7ecrG40ur0cA1/I/MKdRm0SnUKY68we18fy50Nq'),
(8, 'Kenny Lola', 'Kenny', 'kenny@gmail.com', 0, '089499494904', '$2a$10$dLDaNWpIDKxGLPBTCgdNz.p1wddEiW8ZC36ay8BkxYZn.5YGJKSt.'),
(10, 'Yunus Abdul', 'YunusDev', 'yunusdev@gmail.com', 118600, '089499494904', '$2a$10$dDh7p2kGSa4rDFnQFlDLauz3rPqKkcUurro416rJ..MQ7d8.Z6kyC'),
(12, 'Kenny Lola', 'goood', 'good@gmail.com', 0, '089499494904', '$2a$10$fgedV.k.NJRAd0KLG4CGNeqUy6KbbNaL7uXIXVa.6VaYYkbfxqcZe'),
(13, 'Kenny Lola', 'gooodYunus', 'good_yunus@gmail.com', 0, '089499494904', '$2a$10$0f0r244NGg/8Z2sTFbLy/eIAJSS.MYnyJrBVE8FA.JHn1RHC9sWf2'),
(17, 'Kenny Lola', 'YunusDevYunus', 'good_yunuss@gmail.com', 0, '089499494904', '$2a$10$aKsNTKG1IDAzspFXOAObo.zFs6mQLW2hKYU6qhXtJXQA5kn5fxUuW'),
(21, 'Sola Lola', 'Sola', 'sola@gmail.com', 0, '089499494904', '$2a$10$IP3x1kRquXBSUREF844fPeTmrdeTfgpLEIIi2ql9LFiMxicBLGpDe'),
(22, 'Tola Lola', 'Bade', 'lola@gmail.com', 0, '089499494904', '$2a$10$p5/j5HuXum7HJGmLs4E6CepCRFd8A44yOBLO8ARdHeDaADuQCefAO'),
(23, 'Tola Lola', 'Badess', 'lolass@gmail.com', 0, '089499494904', '$2a$10$cOw5yYrVtjCF.ZzkgJsFsePEZ9pf1GxBlhQvFPC8t3rx2oIx2Hyya'),
(24, 'Tola Lola', 'Babami', 'babami@gmail.com', 0, '089499494904', '$2a$10$OnocHp2a0W1UnxiijqBK6.OW6WP8mCYxtiwFIPOujmOroqLgve4cy'),
(26, 'Tola Lola', 'Badesdds', 'lolassxxd@gmail.com', 0, '089499494904', '$2a$10$HJwl28ao9NlU4YsMNA0GYegVqnMg4iZfOveN4VFBAlmTE5H1FHGia');

-- --------------------------------------------------------

--
-- Table structure for table `user_addresses`
--

CREATE TABLE `user_addresses` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `name` text COLLATE utf8mb4_unicode_ci NOT NULL,
  `token` varchar(191) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `user_id` bigint(20) UNSIGNED NOT NULL,
  `is_verified` tinyint(1) DEFAULT 0,
  `date` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `user_addresses`
--

INSERT INTO `user_addresses` (`id`, `name`, `token`, `user_id`, `is_verified`, `date`) VALUES
(1, 'Lekki Phase 1', NULL, 10, 1, '2020-07-26 21:29:07'),
(2, 'Bajulaiye Compound Somolu, Lagos', NULL, 10, 1, '2020-07-26 22:50:18'),
(3, 'Maryland Third Mainland', NULL, 10, 1, '2020-07-26 22:53:25'),
(4, 'Edjeba Estate, Warri', '17BLnWhMNt95ZZtj3r36f1F1BgaT6a8nh72WOWOTo0fDbPYGWyFnaMyPbRa2', 10, 0, '2020-07-26 23:13:49'),
(5, 'Edjeba Estate, Warri', 'XH8IHJAZe0JKriiYVMgKRrRtvYmwuBESvIlZvzmhLfuwUhxEKa67JU50RNk5', 10, 0, '2020-08-07 22:56:55'),
(6, 'Edjeba Somolu, Warri', 'HWR4w0Bugf6HsjttNr8kVVHwK7Gk6CgG3RdhbMVCw1u3nyRghgNrWNVBKdSL', 10, 0, '2020-08-07 23:33:36');

-- --------------------------------------------------------

--
-- Table structure for table `wallet_logs`
--

CREATE TABLE `wallet_logs` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `card_id` bigint(20) UNSIGNED DEFAULT NULL,
  `user_id` bigint(20) UNSIGNED DEFAULT NULL,
  `order_id` bigint(20) UNSIGNED DEFAULT NULL,
  `ref` text COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `flw_ref` text COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `rave_ref` text COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `type` varchar(191) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `amount` bigint(20) DEFAULT NULL,
  `charged_amount` bigint(20) DEFAULT NULL,
  `is_wallet` tinyint(1) DEFAULT 1,
  `auth_model` varchar(191) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `app_fee` bigint(20) DEFAULT NULL,
  `currency` varchar(191) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `date` varchar(191) COLLATE utf8mb4_unicode_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `wallet_logs`
--

INSERT INTO `wallet_logs` (`id`, `card_id`, `user_id`, `order_id`, `ref`, `flw_ref`, `rave_ref`, `type`, `amount`, `charged_amount`, `is_wallet`, `auth_model`, `app_fee`, `currency`, `date`) VALUES
(12, 6, 10, NULL, 'refGODirect-1591098517603', 'FLW-MOCK-1ea28438853fb79ec7eade20d80ab2b7', 'RV315910985708333999FE9BC2', 'credit', 1000, 1000, 1, 'ACCESS_OTP', 29, 'NGN', '2020-07-26 02:17:06'),
(13, 6, 10, NULL, 'ref-TBP2397KiuijFNF', 'FLW-M03K-e889215cbd9864c770dfb0d86f6f8bc9', NULL, 'credit', 4000, 4000, 1, 'noauth', 56, 'NGN', '2020-07-26 02:18:15'),
(15, 8, 10, NULL, 'refGODirect-1590768240138', 'FLW-MOCK-cf3a688ba8ef2ab068e4ef423b86ee54', 'RV315907686214065D10CC6334', 'credit', 10000, 10000, 1, 'VBVSECURECODE', 2141, 'NGN', '2020-07-26 02:21:23'),
(25, 12, 10, NULL, 'refGODirect-1590768240138', 'FLW-MOCK-cf3a688ba8ef2ab068e4ef423b86ee54', 'RV315907686214065D10CC6334', 'credit', 10000, 10000, 1, 'VBVSECURECODE', 2141, 'NGN', '2020-08-07 13:46:20'),
(26, 12, 10, NULL, 'ref-z63iDRLpJJEVjKa', 'FLW-M03K-95c7b0732d49467c0dee8f1a32b372c7', NULL, 'credit', 4000, 4000, 1, 'noauth', 56, 'NGN', '2020-08-07 15:03:18');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `cards`
--
ALTER TABLE `cards`
  ADD PRIMARY KEY (`id`),
  ADD KEY `cards_user_id_foreign` (`user_id`);

--
-- Indexes for table `categories`
--
ALTER TABLE `categories`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `slug` (`slug`),
  ADD UNIQUE KEY `name` (`name`);

--
-- Indexes for table `drivers`
--
ALTER TABLE `drivers`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `drivers_email_unique` (`email`);

--
-- Indexes for table `orders`
--
ALTER TABLE `orders`
  ADD PRIMARY KEY (`id`),
  ADD KEY `orders_user_id_foreign` (`user_id`);

--
-- Indexes for table `order_items`
--
ALTER TABLE `order_items`
  ADD PRIMARY KEY (`id`),
  ADD KEY `order_items_order_id_foreign` (`order_id`),
  ADD KEY `order_items_product_id_foreign` (`product_id`);

--
-- Indexes for table `permissions`
--
ALTER TABLE `permissions`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `products`
--
ALTER TABLE `products`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `name` (`name`),
  ADD KEY `products_category_id_foreign` (`category_id`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `username` (`username`),
  ADD UNIQUE KEY `email` (`email`);

--
-- Indexes for table `user_addresses`
--
ALTER TABLE `user_addresses`
  ADD UNIQUE KEY `id` (`id`);

--
-- Indexes for table `wallet_logs`
--
ALTER TABLE `wallet_logs`
  ADD PRIMARY KEY (`id`),
  ADD KEY `wallet_logs_user_id_foreign` (`user_id`),
  ADD KEY `wallet_logs_card_id_foreign` (`card_id`),
  ADD KEY `wallet_logs_order_id_foreign` (`order_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `cards`
--
ALTER TABLE `cards`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT for table `categories`
--
ALTER TABLE `categories`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT for table `drivers`
--
ALTER TABLE `drivers`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=151;

--
-- AUTO_INCREMENT for table `orders`
--
ALTER TABLE `orders`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `order_items`
--
ALTER TABLE `order_items`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT for table `products`
--
ALTER TABLE `products`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=27;

--
-- AUTO_INCREMENT for table `user_addresses`
--
ALTER TABLE `user_addresses`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `wallet_logs`
--
ALTER TABLE `wallet_logs`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=27;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `cards`
--
ALTER TABLE `cards`
  ADD CONSTRAINT `cards_user_id_foreign` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE CASCADE;

--
-- Constraints for table `products`
--
ALTER TABLE `products`
  ADD CONSTRAINT `products_category_id_foreign` FOREIGN KEY (`category_id`) REFERENCES `categories` (`id`) ON DELETE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
