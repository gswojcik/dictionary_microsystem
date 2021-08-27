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
-- Dumping data for table `dict_conf`
--

LOCK TABLES `dict_conf` WRITE;
/*!40000 ALTER TABLE `dict_conf` DISABLE KEYS */;
INSERT INTO `dict_conf` VALUES (17,'Capitals part 1','European Capitals',1,'2020-12-13 10:37:08',NULL,'Unknown',19),(18,'Capitals part 2','African Capitals',1,'2020-12-13 10:37:45','2020-12-13 10:45:03','Unknown',19),(19,'World Capitals','Capitals from the whole world',1,'2020-12-13 10:38:15',NULL,'Unknown',NULL);
/*!40000 ALTER TABLE `dict_conf` ENABLE KEYS */;
UNLOCK TABLES;

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

--
-- Dumping data for table `dict_item`
--

LOCK TABLES `dict_item` WRITE;
/*!40000 ALTER TABLE `dict_item` DISABLE KEYS */;
INSERT INTO `dict_item` VALUES (37,17,'London','The capital of England',1,NULL),(38,17,'Berlin','The capital of Germany',1,NULL),(39,17,'Warsaw','The capital of Poland',1,NULL),(40,17,'Westminster','It is a dictrict of London',1,37),(41,17,'Greenwich','It is a dictrict of London',1,37),(42,18,'Rabat','The capital of Marocco',1,NULL),(43,18,'Kair','The capital of Egypt',1,NULL),(44,18,'Brazzaville','The capital of Congo',1,NULL);
/*!40000 ALTER TABLE `dict_item` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-12-13 11:47:45
