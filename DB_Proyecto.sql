-- MySQL dump 10.13  Distrib 8.0.25, for Win64 (x86_64)
--
-- Host: localhost    Database: db_proyecto
-- ------------------------------------------------------
-- Server version	8.0.25

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
-- Table structure for table `categoria`
--

DROP TABLE IF EXISTS `categoria`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `categoria` (
  `idCategoria` int NOT NULL,
  `nombre` varchar(45) NOT NULL,
  PRIMARY KEY (`idCategoria`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `categoria`
--

LOCK TABLES `categoria` WRITE;
/*!40000 ALTER TABLE `categoria` DISABLE KEYS */;
INSERT INTO `categoria` VALUES (1,'Pan'),(2,'Bollería'),(3,'Tartas'),(4,'Salados'),(5,'Bebidas'),(6,'Otros');
/*!40000 ALTER TABLE `categoria` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `producto`
--

DROP TABLE IF EXISTS `producto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `producto` (
  `idproducto` int NOT NULL,
  `nombre` varchar(45) NOT NULL,
  `idCategoria` int NOT NULL,
  `sinGluten` tinyint NOT NULL,
  `integral` tinyint NOT NULL,
  `precio` decimal(10,2) NOT NULL,
  `peso` int NOT NULL,
  `stock` int NOT NULL,
  PRIMARY KEY (`idproducto`),
  KEY `idCategoria_idx` (`idCategoria`),
  CONSTRAINT `idCategoria` FOREIGN KEY (`idCategoria`) REFERENCES `categoria` (`idCategoria`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `producto`
--

LOCK TABLES `producto` WRITE;
/*!40000 ALTER TABLE `producto` DISABLE KEYS */;
INSERT INTO `producto` VALUES (1,'Telera',1,0,0,1.25,750,20),(2,'Agua',5,1,0,0.75,500,100),(3,'Velas',6,0,0,1.50,250,25),(4,'Galletas Chocolate',2,0,0,0.20,35,150),(5,'Bocapizza',4,0,0,1.75,250,50),(6,'Bocapizza(sin G)',4,1,0,2.50,250,20),(7,'Zanahoria',3,0,0,18.00,1300,5),(8,'Coca-cola',5,1,0,1.80,350,100),(9,'Gallete canela',2,1,1,0.30,35,50),(10,'Queso(Sin G)',3,1,0,20.00,1000,4),(11,'Nestea',5,1,0,1.80,350,50),(12,'Saladitos',4,0,0,0.40,35,100),(13,'Barra (Sin G)',1,1,1,1.20,250,10),(14,'Caja regalo',6,0,0,1.00,100,500),(15,'Brioche',1,0,0,0.45,50,30),(16,'Tres chocolates',3,1,0,12.00,1500,4),(17,'Picos',1,0,0,1.05,500,10),(18,'Fanta de limón',5,1,0,1.80,350,100),(19,'Napolitana (Sin G)',2,1,0,2.20,125,4),(20,'Harina artesana',6,0,1,2.00,1000,12),(21,'Fanta de naranja',5,1,0,1.80,350,100),(22,'Caña de crema',2,0,0,1.00,150,20),(23,'Bollete',1,0,1,0.50,50,15),(24,'De la abuela',3,0,0,8.50,750,4),(25,'Bollete (Sin G)',1,1,0,0.70,50,10),(26,'Bombom',2,1,0,0.40,25,75);
/*!40000 ALTER TABLE `producto` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-05-21 14:32:29
