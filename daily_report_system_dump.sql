-- MySQL dump 10.13  Distrib 8.0.27, for Win64 (x86_64)
--
-- Host: localhost    Database: daily_report_system
-- ------------------------------------------------------
-- Server version	8.0.27

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `employees`
--

DROP TABLE IF EXISTS `employees`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `employees` (
  `id` int NOT NULL AUTO_INCREMENT,
  `admin_flag` int NOT NULL,
  `code` varchar(255) NOT NULL,
  `created_at` datetime(6) NOT NULL,
  `delete_flag` int NOT NULL,
  `name` varchar(255) NOT NULL,
  `password` varchar(64) NOT NULL,
  `updated_at` datetime(6) NOT NULL,
  `organization_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_3um79qgwg340lpaw7phtwudtc` (`code`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employees`
--

LOCK TABLES `employees` WRITE;
/*!40000 ALTER TABLE `employees` DISABLE KEYS */;
INSERT INTO `employees` VALUES (1,1,'A001','2022-04-27 14:32:24.169306',0,'山田　花子','43EBBD6E889F5E62A7201C015A925F9504CFCF1A5B32641CB728BB264DE281F9','2022-04-27 14:32:24.169306',1),(9,2,'A002','2022-05-10 19:55:58.122786',0,'武田　智仁','BF0C79FAB24516109D8D787D7923DFB79898FE5DA6A128BD440FB8AB3F8B52E8','2022-05-15 16:30:34.036690',2),(10,0,'A003','2022-05-10 20:46:04.534165',0,'鈴木　栄吉','260EDCBD9B15AB938509454C231E47983B0FC02A0D453F8242A5B6E2B169AC21','2022-05-15 16:55:59.051612',2),(11,0,'A004','2022-05-10 20:46:37.479470',0,'朝日　悠馬','E98A8E5EBA265F5AA10AB9CCAA873FC2C3FA885B8CE75C71B205B4E4A39D479F','2022-05-10 20:46:37.479470',1),(12,2,'A005','2022-05-10 20:47:57.252616',0,'佐藤　紘一','7077373BA7DE07CA13128737E21EA38748A12751567A924BB0CFACED7BE6825C','2022-05-15 19:13:37.349865',1),(13,3,'A006','2022-05-10 20:49:41.597410',0,'小田　由美','530C62E227236775D818EE98B8D768B4863DB1BE455E1E4371E6935678683211','2022-05-16 16:07:04.716017',1),(14,3,'A007','2022-05-10 20:50:27.044865',0,'矢田　徹','63E88BEC4621D4FD0793A0E02FCFA3F5577255B3D31C82A8C92951E81F751D58','2022-05-10 20:50:27.044865',1),(15,0,'A011','2022-05-14 21:35:04.414425',0,'田中　文子','34C07EA6F9F422E736ED641CA31C20ADCF0254563CCE9020DCE12EA347ABC444','2022-05-15 16:28:58.075563',1),(16,2,'A012','2022-05-15 18:24:41.267126',0,'小野　恵','74E96DE7467D265B5B230174AC78C5A425C8ED311FC563CF403470B0FCE857E4','2022-05-15 18:24:41.267126',2),(17,2,'A013','2022-05-15 19:04:13.092601',0,'三重　妙子','6746853A0311F34DE8D98E7D5B20B56F40D4AF8D074A675F99B5D011CAF7CC1F','2022-05-15 19:04:36.767332',1);
/*!40000 ALTER TABLE `employees` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `organizations`
--

DROP TABLE IF EXISTS `organizations`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `organizations` (
  `id` int NOT NULL AUTO_INCREMENT,
  `department_name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `organizations`
--

LOCK TABLES `organizations` WRITE;
/*!40000 ALTER TABLE `organizations` DISABLE KEYS */;
INSERT INTO `organizations` VALUES (1,'経理'),(2,'製造'),(3,'システム');
/*!40000 ALTER TABLE `organizations` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `reports`
--

DROP TABLE IF EXISTS `reports`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `reports` (
  `id` int NOT NULL AUTO_INCREMENT,
  `attend_at` varchar(255) NOT NULL,
  `content` longtext NOT NULL,
  `correction_point` longtext,
  `created_at` datetime(6) NOT NULL,
  `delete_flag` int NOT NULL,
  `leaving_at` varchar(255) NOT NULL,
  `report_date` date NOT NULL,
  `title` varchar(255) NOT NULL,
  `updated_at` datetime(6) NOT NULL,
  `employee_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK7m58c0h3phjcm6bco7aqikfen` (`employee_id`),
  CONSTRAINT `FK7m58c0h3phjcm6bco7aqikfen` FOREIGN KEY (`employee_id`) REFERENCES `employees` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reports`
--

LOCK TABLES `reports` WRITE;
/*!40000 ALTER TABLE `reports` DISABLE KEYS */;
INSERT INTO `reports` VALUES (1,'19:15','他部署で研修をする。','','2022-05-13 16:12:37.787223',0,'21:17','2022-05-13','他部署での研修','2022-05-23 23:10:10.998939',12),(2,'19:06','取引先で会議をする',NULL,'2022-05-13 17:04:52.555293',0,'21:08','2022-05-13','取引先での会議','2022-05-23 23:09:29.831300',12),(3,'20:34','昨日、遅くまで残業したので、午後に出勤します。',NULL,'2022-05-13 17:31:49.056733',0,'23:37','2022-05-13','午後出勤','2022-05-23 23:08:31.176748',12),(4,'09:24','午後にシステム運用する。','ffffffffffffffffffffff','2022-05-15 18:22:33.695291',0,'21:25','2022-05-15','システム運用','2022-05-23 22:20:56.021475',10),(5,'23:03','午後に会議がある',NULL,'2022-05-15 19:00:46.856974',0,'05:02','2022-05-15','会議','2022-05-23 22:17:44.473803',1),(6,'02:16','他部署での会議をする。',NULL,'2022-05-15 22:12:17.743613',0,'05:19','2022-05-15','会議','2022-05-23 23:06:35.759080',14),(7,'04:14','自宅でのテレワーク','','2022-05-16 00:10:17.272070',0,'05:15','2022-05-16','テレワーク','2022-05-23 22:22:59.390161',17),(8,'19:02','テスト運用する',NULL,'2022-05-16 17:00:11.551719',0,'21:04','2022-05-16','テスト運用','2022-05-23 22:59:57.038468',10);
/*!40000 ALTER TABLE `reports` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-05-23 23:31:06
