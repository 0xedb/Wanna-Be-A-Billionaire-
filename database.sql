-- MySQL dump 10.13  Distrib 5.7.17, for Linux (x86_64)
--
-- Host: localhost    Database: ywtbab
-- ------------------------------------------------------
-- Server version	5.7.17-0ubuntu0.16.04.1

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
-- Table structure for table `game_question`
--

DROP TABLE IF EXISTS `game_question`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `game_question` (
  `qNo` int(11) NOT NULL AUTO_INCREMENT,
  `level` int(11) NOT NULL,
  `question` varchar(1000) NOT NULL,
  `ans` varchar(100) NOT NULL,
  `pAns` varchar(500) NOT NULL,
  PRIMARY KEY (`qNo`,`question`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `game_question`
--

LOCK TABLES `game_question` WRITE;
/*!40000 ALTER TABLE `game_question` DISABLE KEYS */;
INSERT INTO `game_question` VALUES (1,1,'When did Ghana gain independence?','1957','1975, 1955, 2002'),(2,1,'Where are you most likely to find an Inuit?','Greenland','Togo,Japan,Russia'),(3,2,'What is lychee?','A Fruit','A Person,A Vehicle,A Festival'),(4,1,'Where was Benjamin Franklin born?','Boston','Houston, Seatle, Baltimore'),(5,2,'Which year did Steve Jobs die?','2011','2010, 2008, 2005'),(6,3,'What is the capital of Kyrgyzstan?','Bishkek','Maseru, Vaduz, Lilongwe, Valletta'),(7,2,'Where will you find the Potala Palace?','Tibet','USA, Malta,Hungary'),(8,1,'How many bits make a byte?','8','2, 4, 16'),(9,3,'Who is the 23rd President of the U.S.A?','Benjamin Harrison','Franklin Pierce, Woodrow Wilson, Harry Truman'),(10,3,'How many Grammies has Switchfoot won?','1','0, 4, 6'),(11,1,'Which of these schools did Nana Akufo-Addo attend?','New College','MIT, Stanford, Yale'),(12,3,'In which month in 2016 was Chinese New Year celebrated?','February','April, December, January'),(13,1,'Who once chaired the African Union?','Yayi Boni','Nelson Mandela, Samora Machel, Thomas Sankara');
/*!40000 ALTER TABLE `game_question` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_info`
--

DROP TABLE IF EXISTS `user_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_info` (
  `user` varchar(100) NOT NULL,
  `password` varchar(100) NOT NULL,
  `dob` date DEFAULT NULL,
  `sex` varchar(1) DEFAULT NULL,
  `email` varchar(100) NOT NULL,
  `sec_question` longtext NOT NULL,
  `sec_question_response` varchar(100) NOT NULL,
  `money` int(11) DEFAULT '0',
  PRIMARY KEY (`user`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_info`
--

LOCK TABLES `user_info` WRITE;
/*!40000 ALTER TABLE `user_info` DISABLE KEYS */;
INSERT INTO `user_info` VALUES ('a','a','2017-03-01','M','a@adfa.com','What\'s your favorite city?','a',900000),('adfadf','asdfgh','2017-03-08','M','daf@.adco','What\'s your pet name?','afd',0),('claire','njugu','2016-03-11','F','claire@gmail.ke','What\'s your favorite city?','nairobi',123456789),('nanis','nanis123','2016-03-16','F','nanis@.KEd','What\'s your favorite city?','maua',5000);
/*!40000 ALTER TABLE `user_info` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-03-30 16:03:04
