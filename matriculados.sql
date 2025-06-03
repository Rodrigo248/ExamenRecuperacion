-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 03-06-2025 a las 02:43:47
-- Versión del servidor: 10.4.32-MariaDB
-- Versión de PHP: 8.1.25

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `matriculados`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `alumnoweb`
--

CREATE TABLE `alumnoweb` (
  `codiEstdWeb` int(11) NOT NULL,
  `ndniEstdWeb` varchar(50) NOT NULL,
  `appaEstdWeb` varchar(50) NOT NULL,
  `apmaEstdWeb` varchar(50) NOT NULL,
  `nombEstdWeb` varchar(50) NOT NULL,
  `fechNaciEstdWeb` date NOT NULL,
  `logiEstd` varchar(50) NOT NULL,
  `passEstd` varchar(60) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `alumnoweb`
--

INSERT INTO `alumnoweb` (`codiEstdWeb`, `ndniEstdWeb`, `appaEstdWeb`, `apmaEstdWeb`, `nombEstdWeb`, `fechNaciEstdWeb`, `logiEstd`, `passEstd`) VALUES
(1, '76189808', 'Gamarra', 'Llirod', 'Rodrigo', '2004-12-03', 'rodrigous', '$2a$10$m3jl8ivDKpAJ9I4QRudZpO7YGOlcBO02Qge5FhNitgy24U8Yb5Mgi');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `medico`
--

CREATE TABLE `medico` (
  `codiMedi` int(11) NOT NULL,
  `ndniMedi` varchar(8) NOT NULL,
  `appaMedi` varchar(50) NOT NULL,
  `apmaMedi` varchar(50) NOT NULL,
  `nombMedi` varchar(50) NOT NULL,
  `fechNaciMedi` date NOT NULL,
  `logiMedi` varchar(50) NOT NULL,
  `passMedi` varchar(70) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `medico`
--

INSERT INTO `medico` (`codiMedi`, `ndniMedi`, `appaMedi`, `apmaMedi`, `nombMedi`, `fechNaciMedi`, `logiMedi`, `passMedi`) VALUES
(1, '76189808', 'Gamarra', 'Llirod', 'Rodrigo', '2004-12-03', 'rodrigo', '$2a$10$Mkmxu6JGhfp.E1WHy6UB3upiLSmrtKR4Ve3yyHShWi//oBtztjJZC');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `alumnoweb`
--
ALTER TABLE `alumnoweb`
  ADD PRIMARY KEY (`codiEstdWeb`);

--
-- Indices de la tabla `medico`
--
ALTER TABLE `medico`
  ADD PRIMARY KEY (`codiMedi`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `alumnoweb`
--
ALTER TABLE `alumnoweb`
  MODIFY `codiEstdWeb` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT de la tabla `medico`
--
ALTER TABLE `medico`
  MODIFY `codiMedi` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
