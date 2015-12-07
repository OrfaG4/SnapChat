-- phpMyAdmin SQL Dump
-- version 4.5.1
-- http://www.phpmyadmin.net
--
-- Φιλοξενητής: 127.0.0.1
-- Χρόνος δημιουργίας: 07 Δεκ 2015 στις 02:41:47
-- Έκδοση διακομιστή: 10.1.9-MariaDB
-- Έκδοση PHP: 5.6.15

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Βάση δεδομένων: `snapchat`
--

-- --------------------------------------------------------

--
-- Δομή πίνακα για τον πίνακα `friends`
--

CREATE TABLE `friends` (
  `myId` int(11) NOT NULL DEFAULT '0',
  `friendId` int(11) NOT NULL DEFAULT '0',
  `status` enum('0','1','2') DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Άδειασμα δεδομένων του πίνακα `friends`
--

INSERT INTO `friends` (`myId`, `friendId`, `status`) VALUES
(1, 5, '0'),
(1, 6, '0');

-- --------------------------------------------------------

--
-- Δομή πίνακα για τον πίνακα `users`
--

CREATE TABLE `users` (
  `id` int(10) NOT NULL,
  `lname` varchar(50) NOT NULL,
  `fname` varchar(50) NOT NULL,
  `username` varchar(50) NOT NULL,
  `password` varchar(255) NOT NULL,
  `email` varchar(50) NOT NULL,
  `online` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Άδειασμα δεδομένων του πίνακα `users`
--

INSERT INTO `users` (`id`, `lname`, `fname`, `username`, `password`, `email`, `online`) VALUES
(1, 'test', 'test', 'test', '1111', 'foo@snapchat.com', 0),
(5, 'Giwrgos', 'Gkeliris', 'J0elGk', '1111', 'george_tame@hotmail.com', 0),
(6, 'Giwrgos', 'Orfanidis', 'Orfa', '1111', 'georgeorfanidis@hotmail.com', 0),
(7, 'Rania', 'Georgiadi', 'RaniaWay', '1111', 'raniaway@hotmail.com', 0),
(9, 'Dimitris', 'Karantelos', 'dimitrisKK', '1111', 'dimitriskk@hotmail.com', 0),
(10, 'Dimitris', 'Karantelos', 'dimitrisKK', '1111', 'dimitriskk@hotmail.com', 0),
(11, 'Kostas', 'Prodromou', 'KPro', '1111', 'kostas_prod@gmail.com', 0),
(12, 'Smaro', 'Pourgani', 'Smaro', '1111', 'smaro@hotmail.com', 0);

--
-- Ευρετήρια για άχρηστους πίνακες
--

--
-- Ευρετήρια για πίνακα `friends`
--
ALTER TABLE `friends`
  ADD PRIMARY KEY (`myId`,`friendId`),
  ADD KEY `friend_two` (`friendId`);

--
-- Ευρετήρια για πίνακα `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT για άχρηστους πίνακες
--

--
-- AUTO_INCREMENT για πίνακα `users`
--
ALTER TABLE `users`
  MODIFY `id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;
--
-- Περιορισμοί για άχρηστους πίνακες
--

--
-- Περιορισμοί για πίνακα `friends`
--
ALTER TABLE `friends`
  ADD CONSTRAINT `friends_ibfk_1` FOREIGN KEY (`myId`) REFERENCES `users` (`id`),
  ADD CONSTRAINT `friends_ibfk_2` FOREIGN KEY (`friendId`) REFERENCES `users` (`id`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
