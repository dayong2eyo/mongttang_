-- MariaDB dump 10.19  Distrib 10.11.2-MariaDB, for Win64 (AMD64)
--
-- Host: j8a308.p.ssafy.io    Database: mongttang_db
-- ------------------------------------------------------
-- Server version	10.3.38-MariaDB-0ubuntu0.20.04.1

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `booklike`
--

DROP TABLE IF EXISTS `booklike`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `booklike` (
  `booklike_id` int(11) NOT NULL AUTO_INCREMENT,
  `created_time` datetime DEFAULT NULL,
  `updated_time` datetime DEFAULT NULL,
  `booklike_challeng_id` int(11) NOT NULL,
  `booklike_user_id` int(11) NOT NULL,
  `booklike_book_id` int(11) NOT NULL,
  PRIMARY KEY (`booklike_id`),
  KEY `FK5n5dp3gncln5wub7wdupk912d` (`booklike_book_id`),
  CONSTRAINT `FK5n5dp3gncln5wub7wdupk912d` FOREIGN KEY (`booklike_book_id`) REFERENCES `book` (`book_id`)
) ENGINE=InnoDB AUTO_INCREMENT=49 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `booklike`
--

LOCK TABLES `booklike` WRITE;
/*!40000 ALTER TABLE `booklike` DISABLE KEYS */;
INSERT INTO `booklike` VALUES
(1,'2023-04-07 03:18:17','2023-04-07 03:18:17',1,0,1),
(2,'2023-04-07 03:19:08','2023-04-07 03:19:08',1,0,2),
(3,'2023-04-07 03:20:25','2023-04-07 03:20:25',1,0,3),
(4,'2023-04-07 03:21:49','2023-04-07 03:21:49',1,0,4),
(5,'2023-04-07 03:21:49','2023-04-07 03:21:49',2,0,5),
(6,'2023-04-07 03:23:34','2023-04-07 03:23:34',2,0,6),
(7,'2023-04-07 03:25:42','2023-04-07 03:25:42',2,0,7),
(8,'2023-04-07 03:26:43','2023-04-07 03:26:43',3,0,8),
(9,'2023-04-07 03:27:20','2023-04-07 03:27:20',3,0,9),
(10,'2023-04-07 03:27:58','2023-04-07 03:27:58',3,0,10),
(11,'2023-04-07 03:28:55','2023-04-07 03:28:55',4,0,11),
(12,'2023-04-07 03:29:44','2023-04-07 03:29:44',2,1,5),
(13,'2023-04-07 03:29:48','2023-04-07 03:29:48',2,1,6),
(14,'2023-04-07 03:30:01','2023-04-07 03:30:01',3,1,9),
(15,'2023-04-07 03:30:48','2023-04-07 03:30:48',1,3,4),
(16,'2023-04-07 03:30:53','2023-04-07 03:30:53',4,0,12),
(17,'2023-04-07 03:31:17','2023-04-07 03:31:17',2,4,5),
(18,'2023-04-07 03:32:07','2023-04-07 03:32:07',4,0,13),
(19,'2023-04-07 03:32:07','2023-04-07 03:32:07',4,4,11),
(20,'2023-04-07 03:32:21','2023-04-07 03:32:21',2,4,7),
(21,'2023-04-07 03:32:27','2023-04-07 03:32:27',4,4,12),
(22,'2023-04-07 03:32:34','2023-04-07 03:32:34',4,4,13),
(23,'2023-04-07 03:33:14','2023-04-07 03:33:14',4,5,11),
(24,'2023-04-07 03:33:21','2023-04-07 03:33:21',5,0,14),
(25,'2023-04-07 03:33:28','2023-04-07 03:33:28',2,5,5),
(26,'2023-04-07 03:34:25','2023-04-07 03:34:25',5,0,15),
(27,'2023-04-07 03:35:08','2023-04-07 03:35:08',5,6,15),
(28,'2023-04-07 03:35:23','2023-04-07 03:35:23',5,0,16),
(29,'2023-04-07 03:35:59','2023-04-07 03:35:59',2,6,5),
(30,'2023-04-07 03:36:42','2023-04-07 03:36:42',6,0,17),
(31,'2023-04-07 03:37:23','2023-04-07 03:37:23',1,6,4),
(32,'2023-04-07 03:37:45','2023-04-07 03:37:45',5,6,14),
(33,'2023-04-07 03:37:57','2023-04-07 03:37:57',6,0,18),
(34,'2023-04-07 03:39:10','2023-04-07 03:39:10',6,1,17),
(35,'2023-04-07 03:39:40','2023-04-07 03:39:40',6,0,19),
(36,'2023-04-07 03:40:11','2023-04-07 03:40:11',2,7,5),
(37,'2023-04-07 03:40:39','2023-04-07 03:40:39',1,7,2),
(38,'2023-04-07 03:40:44','2023-04-07 03:40:44',1,7,4),
(39,'2023-04-07 03:41:26','2023-04-07 03:41:26',3,7,9),
(40,'2023-04-07 03:42:04','2023-04-07 03:42:04',3,1,10),
(42,'2023-04-07 03:46:19','2023-04-07 03:46:19',2,3,6),
(43,'2023-04-07 03:46:23','2023-04-07 03:46:23',2,3,7),
(44,'2023-04-07 03:47:19','2023-04-07 03:47:19',6,3,18),
(45,'2023-04-07 03:50:19','2023-04-07 03:50:19',2,3,5),
(46,'2023-04-07 03:50:43','2023-04-07 03:50:43',5,3,15),
(47,'2023-04-07 03:51:24','2023-04-07 03:51:24',5,7,15),
(48,'2023-04-07 03:58:59','2023-04-07 03:58:59',1,1,2);
/*!40000 ALTER TABLE `booklike` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-04-07 13:11:28