CREATE DATABASE  IF NOT EXISTS `dictionary` /*!40100 DEFAULT CHARACTER SET utf8 COLLATE utf8_polish_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `dictionary`;
-- MySQL dump 10.13  Distrib 8.0.22, for Win64 (x86_64)
--
-- Host: localhost    Database: dictionary
-- ------------------------------------------------------
-- Server version	8.0.22

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `dict_conf`
--

DROP TABLE IF EXISTS `dict_conf`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `dict_conf` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `dict_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_polish_ci NOT NULL,
  `dict_description` varchar(255) CHARACTER SET utf8 COLLATE utf8_polish_ci DEFAULT NULL,
  `is_active` smallint DEFAULT '1',
  `creation_time` timestamp NULL DEFAULT NULL,
  `deactivation_time` timestamp NULL DEFAULT NULL,
  `author` varchar(100) CHARACTER SET utf8 COLLATE utf8_polish_ci DEFAULT NULL,
  `master_dict_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `idx_dict_conf_name` (`dict_name`),
  KEY `fx_master_dict_id_idx` (`master_dict_id`),
  CONSTRAINT `fx_master_dict_id` FOREIGN KEY (`master_dict_id`) REFERENCES `dict_conf` (`id`) ON DELETE SET NULL ON UPDATE SET NULL
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8 COLLATE=utf8_polish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `dict_item`
--

DROP TABLE IF EXISTS `dict_item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `dict_item` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `id_dict_conf` bigint NOT NULL,
  `term_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `term_description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `term_active` smallint DEFAULT '1',
  `alias_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_dict_item_term_name` (`id_dict_conf`,`term_name`) /*!80000 INVISIBLE */,
  KEY `fx_id_dict_conf_idx` (`id_dict_conf`) /*!80000 INVISIBLE */,
  KEY `fx_id_item_alias_idx` (`alias_id`),
  CONSTRAINT `fx_id_dict_conf` FOREIGN KEY (`id_dict_conf`) REFERENCES `dict_conf` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fx_id_item_alias` FOREIGN KEY (`alias_id`) REFERENCES `dict_item` (`id`) ON DELETE SET NULL ON UPDATE SET NULL
) ENGINE=InnoDB AUTO_INCREMENT=45 DEFAULT CHARSET=utf8 COLLATE=utf8_polish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-12-13 11:46:53
