CREATE DATABASE  IF NOT EXISTS `kanpm` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `kanpm`;
-- MySQL dump 10.13  Distrib 8.0.20, for Win64 (x86_64)
--
-- Host: localhost    Database: kanpm
-- ------------------------------------------------------
-- Server version	8.0.20

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
-- Table structure for table `project`
--

DROP TABLE IF EXISTS `project`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `project` (
  `id` varchar(128) NOT NULL,
  `project_name` varchar(255) NOT NULL,
  `creator_id` varchar(128) NOT NULL,
  `owner_id` varchar(128) NOT NULL,
  `team_id` varchar(128) NOT NULL,
  `create_time` datetime NOT NULL,
  `is_archived` tinyint(1) NOT NULL DEFAULT '0',
  `description` text,
  `avatar` text,
  PRIMARY KEY (`id`),
  KEY `project_team_fk` (`team_id`),
  KEY `project_creator_fk` (`creator_id`),
  KEY `project_owner_fk` (`owner_id`),
  CONSTRAINT `project_creator_fk` FOREIGN KEY (`creator_id`) REFERENCES `user` (`id`),
  CONSTRAINT `project_owner_fk` FOREIGN KEY (`owner_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `project`
--

LOCK TABLES `project` WRITE;
/*!40000 ALTER TABLE `project` DISABLE KEYS */;
INSERT INTO `project` VALUES ('9adaddc9-cd95-11ea-a58a-00155d6db02a','project-test-01','ed8f5bfe-c943-4aab-ad93-ae8ce4b2095a','ed8f5bfe-c943-4aab-ad93-ae8ce4b2095a','420d17e8-ccb5-11ea-a58a-00155d6db02a','2020-07-24 10:08:22',0,'for test',NULL);
/*!40000 ALTER TABLE `project` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `role` (
  `id` varchar(128) NOT NULL,
  `role_name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tag`
--

DROP TABLE IF EXISTS `tag`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tag` (
  `id` varchar(128) NOT NULL,
  `tag_name` varchar(255) NOT NULL,
  `tag_color` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tag`
--

LOCK TABLES `tag` WRITE;
/*!40000 ALTER TABLE `tag` DISABLE KEYS */;
/*!40000 ALTER TABLE `tag` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `task`
--

DROP TABLE IF EXISTS `task`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `task` (
  `id` varchar(128) NOT NULL,
  `task_name` varchar(255) NOT NULL,
  `task_priority` tinyint(1) NOT NULL,
  `stack_id` varchar(128) NOT NULL,
  `creator_id` varchar(128) NOT NULL,
  `principal_user_id` varchar(128) DEFAULT NULL,
  `task_description` varchar(255) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `start_time` datetime DEFAULT NULL,
  `end_time` datetime DEFAULT NULL,
  `completed_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `task_stack_fk` (`stack_id`),
  KEY `task_principal_fk` (`principal_user_id`),
  KEY `task_creator_fk` (`creator_id`),
  CONSTRAINT `task_creator_fk` FOREIGN KEY (`creator_id`) REFERENCES `user` (`id`),
  CONSTRAINT `task_principal_fk` FOREIGN KEY (`principal_user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `task_stack_fk` FOREIGN KEY (`stack_id`) REFERENCES `task_stack` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `task`
--

LOCK TABLES `task` WRITE;
/*!40000 ALTER TABLE `task` DISABLE KEYS */;
/*!40000 ALTER TABLE `task` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `task_collaborators`
--

DROP TABLE IF EXISTS `task_collaborators`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `task_collaborators` (
  `id` varchar(128) NOT NULL,
  `task_id` varchar(128) NOT NULL,
  `collaborator_id` varchar(128) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `task_collaborator_fk` (`task_id`),
  KEY `collaborator_task_fk` (`collaborator_id`),
  CONSTRAINT `collaborator_task_fk` FOREIGN KEY (`collaborator_id`) REFERENCES `user` (`id`),
  CONSTRAINT `task_collaborator_fk` FOREIGN KEY (`task_id`) REFERENCES `task` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `task_collaborators`
--

LOCK TABLES `task_collaborators` WRITE;
/*!40000 ALTER TABLE `task_collaborators` DISABLE KEYS */;
/*!40000 ALTER TABLE `task_collaborators` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `task_stack`
--

DROP TABLE IF EXISTS `task_stack`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `task_stack` (
  `id` varchar(128) NOT NULL,
  `stack_name` varchar(255) NOT NULL,
  `creator_id` varchar(128) NOT NULL,
  `project_id` varchar(128) NOT NULL,
  `order` int NOT NULL,
  `create_time` datetime NOT NULL,
  `sort_by` tinyint NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `task_stack_project_fk` (`project_id`),
  KEY `task_stack_creator_fk` (`creator_id`),
  CONSTRAINT `task_stack_creator_fk` FOREIGN KEY (`creator_id`) REFERENCES `user` (`id`),
  CONSTRAINT `task_stack_project_fk` FOREIGN KEY (`project_id`) REFERENCES `project` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `task_stack`
--

LOCK TABLES `task_stack` WRITE;
/*!40000 ALTER TABLE `task_stack` DISABLE KEYS */;
/*!40000 ALTER TABLE `task_stack` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `task_tags`
--

DROP TABLE IF EXISTS `task_tags`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `task_tags` (
  `id` varchar(128) NOT NULL,
  `task_id` varchar(128) NOT NULL,
  `tag_id` varchar(128) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `task_tag_fk` (`task_id`),
  KEY `tag_task_fk` (`tag_id`),
  CONSTRAINT `tag_task_fk` FOREIGN KEY (`tag_id`) REFERENCES `tag` (`id`),
  CONSTRAINT `task_tag_fk` FOREIGN KEY (`task_id`) REFERENCES `task` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `task_tags`
--

LOCK TABLES `task_tags` WRITE;
/*!40000 ALTER TABLE `task_tags` DISABLE KEYS */;
/*!40000 ALTER TABLE `task_tags` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `team`
--

DROP TABLE IF EXISTS `team`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `team` (
  `id` varchar(128) NOT NULL,
  `team_name` varchar(255) NOT NULL,
  `creator_id` varchar(128) NOT NULL,
  `owner_id` varchar(128) NOT NULL,
  `create_time` datetime NOT NULL,
  `is_archived` tinyint(1) NOT NULL DEFAULT '0',
  `description` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `team_owner_fk` (`owner_id`),
  KEY `team_creator_fk` (`creator_id`),
  CONSTRAINT `team_creator_fk` FOREIGN KEY (`creator_id`) REFERENCES `user` (`id`),
  CONSTRAINT `team_owner_fk` FOREIGN KEY (`owner_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `team`
--

LOCK TABLES `team` WRITE;
/*!40000 ALTER TABLE `team` DISABLE KEYS */;
INSERT INTO `team` VALUES ('06ca8f15-c0ff-11ea-87bd-00155d6e4002','team-001','38103886-1d49-4cb7-bd68-ee09be00f9f0','38103886-1d49-4cb7-bd68-ee09be00f9f0','2020-07-08 00:00:00',0,''),('0ea64a06-c0ff-11ea-87bd-00155d6e4002','team-002','38103886-1d49-4cb7-bd68-ee09be00f9f0','38103886-1d49-4cb7-bd68-ee09be00f9f0','2020-07-08 00:00:00',0,''),('420d17e8-ccb5-11ea-a58a-00155d6db02a','team-test','ed8f5bfe-c943-4aab-ad93-ae8ce4b2095a','ed8f5bfe-c943-4aab-ad93-ae8ce4b2095a','2020-07-23 00:00:00',0,'for test'),('48ce4178-c1ae-11ea-8cea-f875a490f2fd','team-004','38103886-1d49-4cb7-bd68-ee09be00f9f0','38103886-1d49-4cb7-bd68-ee09be00f9f0','2020-07-09 00:00:00',0,''),('7001aaab-c1aa-11ea-8cea-f875a490f2fd','team-003','38103886-1d49-4cb7-bd68-ee09be00f9f0','38103886-1d49-4cb7-bd68-ee09be00f9f0','2020-07-09 00:00:00',0,'team create test 003'),('943046da-c1ae-11ea-8cea-f875a490f2fd','team-005','38103886-1d49-4cb7-bd68-ee09be00f9f0','38103886-1d49-4cb7-bd68-ee09be00f9f0','2020-07-09 00:00:00',0,'');
/*!40000 ALTER TABLE `team` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `team_members`
--

DROP TABLE IF EXISTS `team_members`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `team_members` (
  `id` varchar(128) NOT NULL,
  `team_id` varchar(128) NOT NULL,
  `member_id` varchar(128) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `team_member_fk` (`team_id`),
  KEY `member_team_fk` (`member_id`),
  CONSTRAINT `member_team_fk` FOREIGN KEY (`member_id`) REFERENCES `user` (`id`),
  CONSTRAINT `team_member_fk` FOREIGN KEY (`team_id`) REFERENCES `team` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `team_members`
--

LOCK TABLES `team_members` WRITE;
/*!40000 ALTER TABLE `team_members` DISABLE KEYS */;
INSERT INTO `team_members` VALUES ('06ccb4b2-c0ff-11ea-87bd-00155d6e4002','06ca8f15-c0ff-11ea-87bd-00155d6e4002','38103886-1d49-4cb7-bd68-ee09be00f9f0'),('0ea70db5-c0ff-11ea-87bd-00155d6e4002','0ea64a06-c0ff-11ea-87bd-00155d6e4002','38103886-1d49-4cb7-bd68-ee09be00f9f0'),('420f6dd4-ccb5-11ea-a58a-00155d6db02a','420d17e8-ccb5-11ea-a58a-00155d6db02a','ed8f5bfe-c943-4aab-ad93-ae8ce4b2095a'),('48d09f62-c1ae-11ea-8cea-f875a490f2fd','48ce4178-c1ae-11ea-8cea-f875a490f2fd','38103886-1d49-4cb7-bd68-ee09be00f9f0'),('700717ca-c1aa-11ea-8cea-f875a490f2fd','7001aaab-c1aa-11ea-8cea-f875a490f2fd','38103886-1d49-4cb7-bd68-ee09be00f9f0'),('9432c1c4-c1ae-11ea-8cea-f875a490f2fd','943046da-c1ae-11ea-8cea-f875a490f2fd','38103886-1d49-4cb7-bd68-ee09be00f9f0');
/*!40000 ALTER TABLE `team_members` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `id` varchar(128) NOT NULL,
  `user_name` varchar(128) NOT NULL,
  `password` varchar(128) NOT NULL,
  `email` varchar(40) DEFAULT NULL,
  `alias` varchar(128) DEFAULT NULL,
  `avatar` text,
  `real_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES ('38103886-1d49-4cb7-bd68-ee09be00f9f0','test-001','$2a$10$OgonUsh.Ew4kAbs1jGfmPebb6sXe0.nEZ3dBzvUGU9FPtBEQFc5lK',NULL,NULL,NULL,NULL),('ed8f5bfe-c943-4aab-ad93-ae8ce4b2095a','locotor','$2a$10$NfFpawFtRwEbvTbnnAyn4eABUhOV74iYC7R34lGug.7XTENmYKdre',NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_roles`
--

DROP TABLE IF EXISTS `user_roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_roles` (
  `id` varchar(128) NOT NULL,
  `user_id` varchar(128) NOT NULL,
  `role_id` varchar(128) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `user_role_fk` (`user_id`),
  KEY `role_user_fk` (`role_id`),
  CONSTRAINT `role_user_fk` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`),
  CONSTRAINT `user_role_fk` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_roles`
--

LOCK TABLES `user_roles` WRITE;
/*!40000 ALTER TABLE `user_roles` DISABLE KEYS */;
/*!40000 ALTER TABLE `user_roles` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-07-24 18:34:28
