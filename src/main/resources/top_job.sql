-- MySQL dump 10.13  Distrib 8.0.23, for Win64 (x86_64)
--
-- Host: localhost    Database: top_job
-- ------------------------------------------------------
-- Server version	8.0.23

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
-- Table structure for table `company`
--

DROP TABLE IF EXISTS `company`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `company` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `about` varchar(2000) NOT NULL,
  `bio` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `industry` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `phone` varchar(255) NOT NULL,
  `website` varchar(255) NOT NULL,
  `founded_date` datetime(6) NOT NULL,
  `city` varchar(255) NOT NULL,
  `country` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_bma9lv19ba3yjwf12a34xord3` (`email`),
  UNIQUE KEY `UK_b30186265qwkg4gpbvcb8afas` (`phone`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `company`
--

LOCK TABLES `company` WRITE;
/*!40000 ALTER TABLE `company` DISABLE KEYS */;
INSERT INTO `company` VALUES (20,'The firm ADVINTIC GmbH, founded 2014 in Germany, is an international software house and a provider of technological\nsolutions with a long history back to 2003.\n\nIt flashes back to 2003 with the early start up Optimal Systems, it was situated in and focusing on the dynamic Egyptian market and middle east.\n\nToday with the new launch into the European market and being centrally located in Germany ,we aspire to serve and lead the market by mastering and Striving to shape the future of human lives, our quest is to develop and create innovative, meaningful, and cutting- edge IT solutions for both the industrial as well as healthcare worlds.\nproviding cutting-edge solutions that help in shaping the future of human lives.\n\nA quite significant part of our investment is directed to develop new technologies that serve as platforms for the Healthcare IT, big-data and internet-of-things. \n\nOur partner’s network spans over the USA, Germany and the Middle east including key universities as well as key opinion leaders in their fields.','We build solutions that inspire','advintic@gmail.com','IT Services and IT Consulting','ADVINTIC','$2a$10$CQjUiUKZkfsaVnLcngxD.euh45zpDuZVu7ZNkBDkducA4QK8IYHgy','01256073902','http://www.advintic.com','2015-06-10 02:00:00.000000','Madrid','Spain');
/*!40000 ALTER TABLE `company` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `company_follower`
--

DROP TABLE IF EXISTS `company_follower`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `company_follower` (
  `company_id` bigint NOT NULL,
  `user_id` bigint NOT NULL,
  PRIMARY KEY (`company_id`,`user_id`),
  KEY `FK5ub05x7xf38bemfnhv3apliy` (`user_id`),
  CONSTRAINT `FK5ub05x7xf38bemfnhv3apliy` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FKkatck7an5hnsu133jx63mdc4b` FOREIGN KEY (`company_id`) REFERENCES `company` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `company_follower`
--

LOCK TABLES `company_follower` WRITE;
/*!40000 ALTER TABLE `company_follower` DISABLE KEYS */;
INSERT INTO `company_follower` VALUES (20,20),(20,25),(20,26);
/*!40000 ALTER TABLE `company_follower` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `course`
--

DROP TABLE IF EXISTS `course`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `course` (
  `course_id` bigint NOT NULL AUTO_INCREMENT,
  `course_name` varchar(255) NOT NULL,
  `course_provider` varchar(255) NOT NULL,
  `user_id` bigint DEFAULT NULL,
  PRIMARY KEY (`course_id`),
  KEY `FKo3767wbj6ow5axv38qej0gxo9` (`user_id`),
  CONSTRAINT `FKo3767wbj6ow5axv38qej0gxo9` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `course`
--

LOCK TABLES `course` WRITE;
/*!40000 ALTER TABLE `course` DISABLE KEYS */;
INSERT INTO `course` VALUES (23,'Object Oriented Programming','CodeMasry',20),(24,'Spring Framework','In28Minutes',20),(25,'Hibernate','Udemy',20);
/*!40000 ALTER TABLE `course` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `education`
--

DROP TABLE IF EXISTS `education`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `education` (
  `education_id` bigint NOT NULL AUTO_INCREMENT,
  `education_degree` varchar(255) NOT NULL,
  `education_place` varchar(255) NOT NULL,
  `end_date` datetime(6) NOT NULL,
  `start_date` datetime(6) NOT NULL,
  `user_id` bigint DEFAULT NULL,
  `study_field` varchar(255) NOT NULL,
  PRIMARY KEY (`education_id`),
  KEY `FKaw3ebf3585a1ndgqnk6k6hosc` (`user_id`),
  CONSTRAINT `FKaw3ebf3585a1ndgqnk6k6hosc` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `education`
--

LOCK TABLES `education` WRITE;
/*!40000 ALTER TABLE `education` DISABLE KEYS */;
INSERT INTO `education` VALUES (24,'Bachelor\'s','Benha University','2021-07-14 02:00:00.000000','2017-06-07 02:00:00.000000',20,'Computer Science'),(27,'Master','Must','2022-11-22 02:00:00.000000','2020-07-16 02:00:00.000000',20,'Medical'),(28,'Bachelor\'s','Haravard','2022-11-24 02:00:00.000000','2022-11-02 02:00:00.000000',25,'Computer Science');
/*!40000 ALTER TABLE `education` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `experience`
--

DROP TABLE IF EXISTS `experience`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `experience` (
  `experience_id` bigint NOT NULL AUTO_INCREMENT,
  `end_date` datetime(6) NOT NULL,
  `experience_place` varchar(255) NOT NULL,
  `experience_position` varchar(255) NOT NULL,
  `start_date` datetime(6) NOT NULL,
  `user_id` bigint DEFAULT NULL,
  PRIMARY KEY (`experience_id`),
  KEY `FK41lup37auw1bvwwqpgn0blbic` (`user_id`),
  CONSTRAINT `FK41lup37auw1bvwwqpgn0blbic` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `experience`
--

LOCK TABLES `experience` WRITE;
/*!40000 ALTER TABLE `experience` DISABLE KEYS */;
INSERT INTO `experience` VALUES (8,'2022-12-25 02:00:00.000000','ADVINTIC','Junior Java Developer','2022-05-22 02:00:00.000000',20),(9,'2022-11-17 02:00:00.000000','Asset Technology Group','Software Engineer','2023-01-01 02:00:00.000000',20);
/*!40000 ALTER TABLE `experience` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `job`
--

DROP TABLE IF EXISTS `job`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `job` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `date` datetime(6) NOT NULL,
  `description` varchar(1500) NOT NULL,
  `employment_type` int NOT NULL,
  `location` varchar(255) NOT NULL,
  `requirement` varchar(1500) NOT NULL,
  `title` varchar(255) NOT NULL,
  `company_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK5q04favsasq8y70bsei7wv8fc` (`company_id`),
  CONSTRAINT `FK5q04favsasq8y70bsei7wv8fc` FOREIGN KEY (`company_id`) REFERENCES `company` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `job`
--

LOCK TABLES `job` WRITE;
/*!40000 ALTER TABLE `job` DISABLE KEYS */;
/*!40000 ALTER TABLE `job` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `project`
--

DROP TABLE IF EXISTS `project`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `project` (
  `project_id` bigint NOT NULL AUTO_INCREMENT,
  `project_description` varchar(600) NOT NULL,
  `project_link` varchar(255) NOT NULL,
  `project_name` varchar(255) NOT NULL,
  `user_id` bigint DEFAULT NULL,
  PRIMARY KEY (`project_id`),
  KEY `FKo06v2e9kuapcugnyhttqa1vpt` (`user_id`),
  CONSTRAINT `FKo06v2e9kuapcugnyhttqa1vpt` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `project`
--

LOCK TABLES `project` WRITE;
/*!40000 ALTER TABLE `project` DISABLE KEYS */;
INSERT INTO `project` VALUES (5,'E-commerce web app consists of (products – orders – product types – customers) for ordering products online.\nSkills used?\n-Java, Spring , Hibernate, MySQL and REST API.','https://github.com/Ahmed-Elakeed/Ecommerce-Backend','E-commerce Backend',20),(8,'Website for a medical laboratory to store covid-19 patients data and their PCR test data and they can access the results anywhere by visiting the site and entering social number.\nSkills used?\n-Java EE and JSP.\n-Spring (boot, data, MVC, JPA).\n-MySQL and Hibernate.\n-HTML, CSS, JavaScript.','https://github.com/Ahmed-Elakeed/laboratory','Medical Laboratory(PCR)',20);
/*!40000 ALTER TABLE `project` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `skill`
--

DROP TABLE IF EXISTS `skill`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `skill` (
  `skill_id` bigint NOT NULL AUTO_INCREMENT,
  `skill_name` varchar(255) NOT NULL,
  `user_id` bigint DEFAULT NULL,
  PRIMARY KEY (`skill_id`),
  KEY `FKlgluovqqqd34v705og915ryye` (`user_id`),
  CONSTRAINT `FKlgluovqqqd34v705og915ryye` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `skill`
--

LOCK TABLES `skill` WRITE;
/*!40000 ALTER TABLE `skill` DISABLE KEYS */;
INSERT INTO `skill` VALUES (7,'Teamwork',20),(12,'Github',20),(13,'Leadership',20),(14,'Self-learning',20),(15,'Resful WebServices',20);
/*!40000 ALTER TABLE `skill` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `about` varchar(800) DEFAULT NULL,
  `birth_date` datetime(6) NOT NULL,
  `city` varchar(255) NOT NULL,
  `country` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `first_name` varchar(255) NOT NULL,
  `job_title` varchar(255) NOT NULL,
  `last_name` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `phone` varchar(255) NOT NULL,
  `profile_picture_path` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_ob8kqyqqgmefl0aco34akdtpe` (`email`),
  UNIQUE KEY `UK_589idila9li6a4arw1t8ht1gx` (`phone`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (20,'Skilled software developer with experience in object oriented programming, design, and development of Multi-Tier distributed, Enterprise applications using Java and J2EE technologies with Software Development Life Cycle.\n\nHands on experience in designing and developing Web-based Enterprise applications using Java and J2EE technologies like JSP, Servlets, Spring Framework, and Hibernate.\n\nGood understanding of Core JAVA components, such as Concurrency, Persistency, multi-threading. Experience in Web Services.\n\nExcellent knowledge on Object Oriented Programming concepts. \n\nUnderstanding of relational database concepts, and experience in query construction using SQL. Experience in working with the Databases environments Oracle, SQL Server, and MySQL.','1999-08-17 03:00:00.000000','Benha','Egypt','elakeed@gmail.com','Ahmed','Software Engineer','Elakeed','$2a$10$BYG/KbNlNyVaPowUfZSSxuSyK/VOumvwiHgw0slOnSxRrHrXgff9u','01156073902','http://127.0.0.1:8887/AHMED-ELAKEED.jpg'),(25,NULL,'2022-11-09 02:00:00.000000','Tanta','Egypt','ahmed.elaked220@gmail.com','Mohamed','Junior AI developer','Eslam','$2a$10$uoKi6gbRYJMjPFOsqq/ZUO4cOF3HsUu1fiq.zIaxAIQr6dyNQQeDO','01116713394',NULL),(26,NULL,'2023-02-10 02:00:00.000000','Iowa','United Stated','yahia.elakeed@gmail.com','Yahia','Software Engineer','Elakeed','$2a$10$jcjG2Gx0zfly.1S5lsgXE.wkyN71cQu35Ui1DDgvPecl.zowfrocC','01552827708','http://127.0.0.1:8887/elakeed.jpeg'),(27,NULL,'2023-02-16 02:00:00.000000','benha','Egypt','ahmed123@gmail.com','new ','Software Engineer','user','$2a$10$SyKOjJAaGdCQggc5q02mmuV2UFi6W1X7tqdQvvP5uBk4LftgOa3X2','01156073905','');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_applied_job`
--

DROP TABLE IF EXISTS `user_applied_job`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_applied_job` (
  `user_id` bigint NOT NULL,
  `job_id` bigint NOT NULL,
  PRIMARY KEY (`user_id`,`job_id`),
  KEY `FK3jqqkbr80eswj3gyfrasxqv41` (`job_id`),
  CONSTRAINT `FK3jqqkbr80eswj3gyfrasxqv41` FOREIGN KEY (`job_id`) REFERENCES `job` (`id`),
  CONSTRAINT `FKpvoo0xxrbcrj3fae2std3olr9` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_applied_job`
--

LOCK TABLES `user_applied_job` WRITE;
/*!40000 ALTER TABLE `user_applied_job` DISABLE KEYS */;
/*!40000 ALTER TABLE `user_applied_job` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_connection`
--

DROP TABLE IF EXISTS `user_connection`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_connection` (
  `user_id` bigint NOT NULL,
  `friend_id` bigint NOT NULL,
  PRIMARY KEY (`user_id`,`friend_id`),
  KEY `FKin1qjdc8k0yog4kgqernfxpwh` (`friend_id`),
  CONSTRAINT `FKg621gksxjny780ycoepk9ss0r` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FKin1qjdc8k0yog4kgqernfxpwh` FOREIGN KEY (`friend_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_connection`
--

LOCK TABLES `user_connection` WRITE;
/*!40000 ALTER TABLE `user_connection` DISABLE KEYS */;
INSERT INTO `user_connection` VALUES (25,20),(26,20),(20,25),(20,26);
/*!40000 ALTER TABLE `user_connection` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-02-17 15:09:36
