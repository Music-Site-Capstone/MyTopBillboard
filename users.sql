-- MySQL dump 10.13  Distrib 8.0.30, for macos12.4 (arm64)
--
-- Host: localhost    Database: mtb_db
-- ------------------------------------------------------
-- Server version	8.0.30

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
-- Table structure for table `artist`
--

DROP TABLE IF EXISTS `artist`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `artist` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `artist_name` varchar(75) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_h269s1w9aa7yf6whby2jwbix6` (`artist_name`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `artist`
--

LOCK TABLES `artist` WRITE;
/*!40000 ALTER TABLE `artist` DISABLE KEYS */;
INSERT INTO `artist` VALUES (3,'Disturbed'),(7,'Fleetwood Mac'),(5,'Led Zeppelin'),(2,'Metallica'),(6,'Rebelution'),(4,'Rise Against'),(1,'Tenacious D');
/*!40000 ALTER TABLE `artist` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `genre`
--

DROP TABLE IF EXISTS `genre`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `genre` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `genre_name` varchar(75) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_af763lrx484eaodu8m088ei0` (`genre_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `genre`
--

LOCK TABLES `genre` WRITE;
/*!40000 ALTER TABLE `genre` DISABLE KEYS */;
/*!40000 ALTER TABLE `genre` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `playlist`
--

DROP TABLE IF EXISTS `playlist`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `playlist` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `playlist_name` varchar(50) NOT NULL,
  `user_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKlbi6wsq41356go2ki0yirfixo` (`user_id`),
  CONSTRAINT `FKlbi6wsq41356go2ki0yirfixo` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `playlist`
--

LOCK TABLES `playlist` WRITE;
/*!40000 ALTER TABLE `playlist` DISABLE KEYS */;
INSERT INTO `playlist` VALUES (1,'PlayListThatKicks',1);
/*!40000 ALTER TABLE `playlist` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `playlist_song`
--

DROP TABLE IF EXISTS `playlist_song`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `playlist_song` (
  `playlist_id` bigint NOT NULL,
  `song_id` bigint NOT NULL,
  KEY `FK8l4jevlmxwsdm3ppymxm56gh2` (`song_id`),
  KEY `FKji5gt6i2hcwyt9x1fcfndclva` (`playlist_id`),
  CONSTRAINT `FK8l4jevlmxwsdm3ppymxm56gh2` FOREIGN KEY (`song_id`) REFERENCES `song` (`id`),
  CONSTRAINT `FKji5gt6i2hcwyt9x1fcfndclva` FOREIGN KEY (`playlist_id`) REFERENCES `playlist` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `playlist_song`
--

LOCK TABLES `playlist_song` WRITE;
/*!40000 ALTER TABLE `playlist_song` DISABLE KEYS */;
INSERT INTO `playlist_song` VALUES (1,1),(1,2),(1,3),(1,4),(1,5),(1,6),(1,7),(1,8),(1,9),(1,10),(1,11);
/*!40000 ALTER TABLE `playlist_song` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rating`
--

DROP TABLE IF EXISTS `rating`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `rating` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `playlist_id` bigint NOT NULL,
  `score` smallint NOT NULL,
  `user_id` bigint NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKrjt4l0q0ku1lrjhipd4ih1g1w` (`playlist_id`),
  KEY `FKpn05vbx6usw0c65tcyuce4dw5` (`user_id`),
  CONSTRAINT `FKpn05vbx6usw0c65tcyuce4dw5` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FKrjt4l0q0ku1lrjhipd4ih1g1w` FOREIGN KEY (`playlist_id`) REFERENCES `playlist` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rating`
--

LOCK TABLES `rating` WRITE;
/*!40000 ALTER TABLE `rating` DISABLE KEYS */;
/*!40000 ALTER TABLE `rating` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `song`
--

DROP TABLE IF EXISTS `song`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `song` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `image` varchar(255) NOT NULL,
  `title` varchar(255) NOT NULL,
  `artist_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKa21ft97nj7thwrp5d31xdaxr` (`artist_id`),
  CONSTRAINT `FKa21ft97nj7thwrp5d31xdaxr` FOREIGN KEY (`artist_id`) REFERENCES `artist` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `song`
--

LOCK TABLES `song` WRITE;
/*!40000 ALTER TABLE `song` DISABLE KEYS */;
INSERT INTO `song` VALUES (1,'https://i.scdn.co/image/ab67616d00004851bc90b4b507565e1fae75033d','The Metal',1),(2,'https://i.scdn.co/image/ab67616d00004851533fd0b248052d04e6b732c0','For Whom The Bell Tolls (Remastered)',2),(3,'https://i.scdn.co/image/ab67616d000048511f9edf15e43f4c2f4938b869','The Unforgiven (Remastered)',2),(4,'https://i.scdn.co/image/ab67616d00004851d7bd0f8e4d2a399020b333e8','The Sound of Silence',3),(5,'https://i.scdn.co/image/ab67616d0000485190fef039e01409cb5a8d560c','Prayer Of The Refugee',4),(6,'https://i.scdn.co/image/ab67616d000048519ad624c49239d6868b5f3aad','Prayer',3),(7,'https://i.scdn.co/image/ab67616d0000485190a50cfe99a4c19ff3cbfbdb','Immigrant Song - Remaster',5),(8,'https://i.scdn.co/image/ab67616d00004851765b0617b572bdd1dbdc7d8e','Kashmir - Remaster',5),(9,'https://i.scdn.co/image/ab67616d00004851e01981eb6c742b355069f147','Count Me In (Acoustic)',6),(10,'https://i.scdn.co/image/ab67616d0000485180fb45268f67e7923804ca6f','Sky Is the Limit - Acoustic',6),(11,'https://i.scdn.co/image/ab67616d000048514fb043195e8d07e72edc7226','Rhiannon',7);
/*!40000 ALTER TABLE `song` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `email` varchar(100) NOT NULL,
  `password` varchar(255) NOT NULL,
  `username` varchar(50) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_ob8kqyqqgmefl0aco34akdtpe` (`email`),
  UNIQUE KEY `UK_sb8bbouer5wak8vyiiy4pf2bx` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'thevaliantjosh@gmail.com','$2a$10$kXsnPVpPyjqF3WVhUq3hnugfFh88cVnRa.NvCiN80wbzd.DWVHg9S','thevaliantjosh'),(2,'musicman101@gmail.com','$2a$10$T4Mp71DPBoGOv9doossyO.ZRPR0oHwGL5tgXrMXcyXqkn69PFMMPC','MusicMan101'),(3,'caleb.ceo.okane@gmail.com','$2a$10$FfiPDFXGoR1T2oHx9WNdJ.vNzea4vzm0sgfTPlrWTgXWxsuhWllb2','ceo'),(4,'codeythecoder@gmail.com','$2a$10$2JlTMaUwqxrcEe1GrnDcJeT8nWi0fnk85JSkEenyBZBQ.ZaKuXEny','CodeyTheCoder'),(5,'mrtabletop@gmail.com','$2a$10$y6kRQUYhkCjbg6uM0NgSCONu/OuIgdoXqHNN4a3YLrGiT.i8g7wwS','MrTableTop'),(6,'silver@gmail.com','$2a$10$lVwHtumfqX.fFODuLsZ7zuKZRfXT9AeaLcq4EsJZ3jajUtiRL5/me','Silver'),(7,'titanium@gmail.com','$2a$10$kvUWiIK29dN3DNoFqt.0zev0aF.8djMEAerZSZ0sOUcaLb60iZ/Du','Titanium'),(8,'bronze@gmail.com','$2a$10$ocjFv5ZXAxgIpSy/dXdDF.pVK1S2cvpMzfJMzljq34r73Dgvh/LGG','Bronze'),(9,'miso2@gmail.com','$2a$10$4fh66zYtnKNBAORHoQtqBO8GYOdGC/NAouI7REp8kHS6i4/JTFtya','miso2'),(10,'miso1@gmail.com','$2a$10$lp/INFcm4mP1BAGwkO9M1O/mAWwfOoEOucE2K.HoA4V5yszK9Ax3W','miso1'),(11,'miso3@gmail.com','$2a$10$4bTttP05JoP1PFDB0dmnHOg0T34TgPFSixGsfKTjspSnZV/.eqfNO','miso3'),(12,'miso4@gmail.com','$2a$10$oIChYB6q4.ZEBCjKlKlpXuuDToni8NE6y6NqObK3FF3gYXc9xlYii','miso4'),(13,'miso5@gmail.com','$2a$10$r1fDudkhEWO.zvnCaUk.dOzy7P6AaZMzLyfKw7NeD7.U7luwV4Vua','miso5'),(14,'mrname@gmail.com','$2a$10$CqCji7cIx/YpwuXImLUKROPn81SFVQXkSJFYXQgqfYk3J0H8m.NDK','MrName');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-01-11 11:21:56
