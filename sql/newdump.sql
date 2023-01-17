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
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `artist`
--

LOCK TABLES `artist` WRITE;
/*!40000 ALTER TABLE `artist` DISABLE KEYS */;
INSERT INTO `artist` VALUES (7,'Avenged Sevenfold'),(4,'Disturbed'),(3,'Killswitch Engage'),(2,'Metallica'),(6,'Red Hot Chili Peppers'),(5,'Shinedown'),(8,'System Of A Down'),(1,'Tenacious D');
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
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `playlist`
--

LOCK TABLES `playlist` WRITE;
/*!40000 ALTER TABLE `playlist` DISABLE KEYS */;
INSERT INTO `playlist` VALUES (1,'PlayListThatKicks',1),(3,'MusicallyMusical',2),(4,'SOAD',1),(5,'NewPlayList',1),(6,'ExtraPlayList',1),(7,'AnotherAnotherPlayList',1),(8,'OneMoreAgainPlayList',1);
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
INSERT INTO `playlist_song` VALUES (3,8),(3,6),(3,9),(3,10),(3,2),(3,11),(3,12),(4,18),(4,19),(4,20),(1,3),(1,13),(1,15),(1,14),(1,1),(1,2),(1,3);
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
  `preview_url` varchar(255) NOT NULL,
  `title` varchar(255) NOT NULL,
  `artist_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKa21ft97nj7thwrp5d31xdaxr` (`artist_id`),
  CONSTRAINT `FKa21ft97nj7thwrp5d31xdaxr` FOREIGN KEY (`artist_id`) REFERENCES `artist` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `song`
--

LOCK TABLES `song` WRITE;
/*!40000 ALTER TABLE `song` DISABLE KEYS */;
INSERT INTO `song` VALUES (1,'https://i.scdn.co/image/ab67616d00004851bc90b4b507565e1fae75033d','https://p.scdn.co/mp3-preview/ddc02e05e6f2dfdb28eea269fb08499254f94ca1?cid=8fa94147df104e5cbfc6519c3ce4bff1','The Metal',1),(2,'https://i.scdn.co/image/ab67616d00004851533fd0b248052d04e6b732c0','https://p.scdn.co/mp3-preview/8e4aa633a3567a8fb1e33820f39f502a67b051fb?cid=8fa94147df104e5cbfc6519c3ce4bff1','For Whom The Bell Tolls (Remastered)',2),(3,'https://i.scdn.co/image/ab67616d000048511f9edf15e43f4c2f4938b869','https://p.scdn.co/mp3-preview/122128827440024e3ff35f5b3359d611a5ddb7e5?cid=8fa94147df104e5cbfc6519c3ce4bff1','The Unforgiven (Remastered)',2),(4,'https://i.scdn.co/image/ab67616d00004851cc5bb1a3f809896baf67dba4','https://p.scdn.co/mp3-preview/7decd366692b8cee986717fca446c3fdadaea6ed?cid=8fa94147df104e5cbfc6519c3ce4bff1','My Curse',3),(5,'https://i.scdn.co/image/ab67616d00004851cc5bb1a3f809896baf67dba4','https://p.scdn.co/mp3-preview/17e7fe032d8231a1429da2af0a2cba85858cfa01?cid=8fa94147df104e5cbfc6519c3ce4bff1','Holy Diver',3),(6,'https://i.scdn.co/image/ab67616d00004851d7bd0f8e4d2a399020b333e8','https://p.scdn.co/mp3-preview/60ef7e2b9b1514b6342b5c2713b18507bfd3de15?cid=8fa94147df104e5cbfc6519c3ce4bff1','The Sound of Silence',4),(7,'https://i.scdn.co/image/ab67616d00004851d609342d04c349815b5fe099','https://p.scdn.co/mp3-preview/4e9d9ac1a7c0c4b5be2d668e2aedf73a900305f0?cid=8fa94147df104e5cbfc6519c3ce4bff1','Simple Man - Acoustic',5),(8,'https://i.scdn.co/image/ab67616d000048512b222dcd5c4fcac7c0e81da2','https://p.scdn.co/mp3-preview/c478f7cf25b4aa2758438bddc12532365db2f38e?cid=8fa94147df104e5cbfc6519c3ce4bff1','Down with the Sickness',4),(9,'https://i.scdn.co/image/ab67616d00004851bbdafec608fc5329ec2ad831','https://p.scdn.co/mp3-preview/29d4000ba9f1b91b36c63615c0e42eab9204d3a1?cid=8fa94147df104e5cbfc6519c3ce4bff1','Stricken',4),(10,'https://i.scdn.co/image/ab67616d0000485194a42e7b21b0fab4f4773db2','https://p.scdn.co/mp3-preview/d4e12499ee2340288494db698de10816ebf18f1d?cid=8fa94147df104e5cbfc6519c3ce4bff1','Stupify',4),(11,'https://i.scdn.co/image/ab67616d000048511f9edf15e43f4c2f4938b869','https://p.scdn.co/mp3-preview/872e1c326c6ed346ece99a1abee1414384936656?cid=8fa94147df104e5cbfc6519c3ce4bff1','Enter Sandman (Remastered)',2),(12,'https://i.scdn.co/image/ab67616d00004851cad4832cb7b5844343278daa','https://p.scdn.co/mp3-preview/c3818dcb62a5cc3701b08c3465ab93199e2786e3?cid=8fa94147df104e5cbfc6519c3ce4bff1','Master of Puppets (Remastered)',2),(13,'https://i.scdn.co/image/ab67616d00004851153d79816d853f2694b2cc70','https://p.scdn.co/mp3-preview/f5d616de3e68c150375d909fce0447bbdc33ad40?cid=8fa94147df104e5cbfc6519c3ce4bff1','Under the Bridge',6),(14,'https://i.scdn.co/image/ab67616d0000485109fd83d32aee93dceba78517','https://p.scdn.co/mp3-preview/89c75d88795a8b49a074dbe476169a4327862960?cid=8fa94147df104e5cbfc6519c3ce4bff1','Snow (Hey Oh)',6),(15,'https://i.scdn.co/image/ab67616d0000485194d08ab63e57b0cae74e8595','https://p.scdn.co/mp3-preview/d5094f7d32c25e92d8d362246c0a3f3e7f02a813?cid=8fa94147df104e5cbfc6519c3ce4bff1','Californication',6),(16,'https://i.scdn.co/image/ab67616d0000485194d08ab63e57b0cae74e8595','https://p.scdn.co/mp3-preview/505082fa90f314d9198348d5496251ed17d333c5?cid=8fa94147df104e5cbfc6519c3ce4bff1','Scar Tissue',6),(17,'https://i.scdn.co/image/ab67616d000048518b4d1c3dadc474e4abf751db','https://p.scdn.co/mp3-preview/b62131be57466d250944fdd30fcf2f4bc812fb7d?cid=8fa94147df104e5cbfc6519c3ce4bff1','Nightmare',7),(18,'https://i.scdn.co/image/ab67616d0000485130d45198d0c9e8841f9a9578','https://p.scdn.co/mp3-preview/668891dce18fb36b6dbf8f3f3f0ecf71c1f95298?cid=8fa94147df104e5cbfc6519c3ce4bff1','Chop Suey!',8),(19,'https://i.scdn.co/image/ab67616d000048512dc63e977bd5101072adcef6','https://p.scdn.co/mp3-preview/5dabe769e9b9a04fcc87a49f248faf9d94e5c1b6?cid=8fa94147df104e5cbfc6519c3ce4bff1','Sugar',8),(20,'https://i.scdn.co/image/ab67616d0000485130d45198d0c9e8841f9a9578','https://p.scdn.co/mp3-preview/ea84dd88c04dbe12f17d220019533c591ebf45ab?cid=8fa94147df104e5cbfc6519c3ce4bff1','Toxicity',8),(21,'https://i.scdn.co/image/ab67616d00004851f5e7b2e5adaa87430a3eccff','https://p.scdn.co/mp3-preview/d55fa9b55debdadec3d43953794086ae7c8648f7?cid=8fa94147df104e5cbfc6519c3ce4bff1','Lonely Day',8);
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
INSERT INTO `user` VALUES (1,'thevaliantjosh@gmail.com','$2a$10$CuCwng1b5gghL0cLPN0m4.4lOzRMI9AbP5UqAmA14axuaM63wGhqS','thevaliantjosh'),(2,'musicman101@gmail.com','$2a$10$xd2KkXwqubOpwr357newp.twSKt.hS5iXWeoVfSIewPJrIsihmy7G','MusicMan101'),(3,'codeythecoder@gmail.com','$2a$10$5BSTbraW0kCTSIJXxPw.wea1GW5WlSYygUzo9HBhWrlpdgXkzL6LS','CodeyTheCoder'),(4,'gimmeTheDice@gmail.com','$2a$10$DxHckDgp3dkwfnyC28yvKeSWLBjHsI2/TgpRe2Ec2azEUKzNmvG5i','MrTableTop'),(5,'silver@gmail.com','$2a$10$cpKr03tgH8puMoA4xgMMUOZcxH9kIPiofT/cLBStcQvZ5PjDVZvnS','Silver'),(6,'titanium@gmail.com','$2a$10$lf3xcfyU751tqndMDao0ju1s3Re7AiKC0rq2vT05kQojUykLO17qG','Titanium'),(7,'bronze@gmail.com','$2a$10$f/A8Qn0eeeIzdc8aMfwe/O1sdrFsXtisuX0nYKuTLgMV85V6rKXN2','Bronze'),(8,'miso1@gmail.com','$2a$10$bDNQXonMR9BGY3T.P9s6AugSIzTzqJLZJYMCAeUWYb0mXYonXU7ey','miso1'),(9,'miso2@gmail.com','$2a$10$wgFF066PMZQWzxsR.bO4C.FPW87PwEgJcmAGZxhliU0QZaoXbZQD6','miso2'),(10,'miso3@gmail.com','$2a$10$0b6fdyZECPau4tfT9RNpve.TawtOPIDtVEG7ostWfn7vbcDAAiL.e','miso3'),(11,'miso4@gmail.com','$2a$10$qKWX83bDLGSSe7hOm2XMcubXAfQPSjQaAOlCrDGnTphv2pN9Dl9Be','miso4'),(12,'miso5@gmail.com','$2a$10$MEM1dflXI5JtoLkpjMhgEevsZ4MjOPgh8kI8l6RYN55d3DBO1Cv86','miso5'),(13,'mrname@gmail.com','$2a$10$kQ7bJx76kQUiBk7aKkSarOoQGf0XaZjX8PeCOfmO2T/Ce.W2FiZkC','MrName'),(14,'ceo@gmail.com','$2a$10$SrFTTO4gPSjKKPwR9wJMN.HYZALw/YakbGwO6tVtYODtCjb9vgFB6','ceo');
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

-- Dump completed on 2023-01-17 14:31:01
