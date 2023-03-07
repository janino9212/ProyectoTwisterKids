-- MySQL dump 10.13  Distrib 8.0.32, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: twisterkids
-- ------------------------------------------------------
-- Server version	8.0.32

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
-- Table structure for table `cliente`
--

DROP TABLE IF EXISTS `cliente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cliente` (
  `id` int NOT NULL AUTO_INCREMENT,
  `documento` varchar(100) DEFAULT NULL,
  `nombre` varchar(60) NOT NULL,
  `correo` varchar(45) NOT NULL,
  `telefono` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cliente`
--

LOCK TABLES `cliente` WRITE;
/*!40000 ALTER TABLE `cliente` DISABLE KEYS */;
INSERT INTO `cliente` VALUES (1,'1001880891','ferney Stiven Rueda Gonzalez','fernerueda@gmail.com','3155916337'),(2,'28410995','Myriam Roja Villareal','miriam45@gmail.com','318833389559'),(3,'50432678','samuel vasquez martines','samvasquez@gmail.com','3509245689'),(5,'100893763','luis miguel rueda hoyos','luis@gmail.com','3156729802');
/*!40000 ALTER TABLE `cliente` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `detalle`
--

DROP TABLE IF EXISTS `detalle`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `detalle` (
  `id` int NOT NULL AUTO_INCREMENT,
  `codigopro` varchar(100) DEFAULT NULL,
  `marca` varchar(100) DEFAULT NULL,
  `talla` int DEFAULT NULL,
  `cantidad` int DEFAULT NULL,
  `precio` int DEFAULT NULL,
  `valor` int DEFAULT NULL,
  `total` int DEFAULT NULL,
  `id_venta` int DEFAULT NULL,
  `fecha` date DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `detalle`
--

LOCK TABLES `detalle` WRITE;
/*!40000 ALTER TABLE `detalle` DISABLE KEYS */;
INSERT INTO `detalle` VALUES (1,'adidas lego','concha',21,1,90000,60000,90000,1,'2023-01-25'),(2,'boost','Oscar',30,1,140000,110000,140000,2,'2023-01-25'),(3,'frozen new','Cindy',35,2,80000,50000,160000,3,'2023-01-25'),(4,'adidas lego','german',35,1,100000,60000,100000,4,'2023-02-02'),(5,'adidas lego','cindy',35,1,80000,60000,80000,5,'2023-02-22'),(6,'adidas lego','ferney',25,1,70000,60000,70000,6,'2023-02-22'),(7,'adidas lego','german',29,1,65000,60000,65000,7,'2023-02-22');
/*!40000 ALTER TABLE `detalle` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `gasto`
--

DROP TABLE IF EXISTS `gasto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `gasto` (
  `id` int NOT NULL AUTO_INCREMENT,
  `tipogasto` varchar(100) DEFAULT NULL,
  `descripcion` varchar(100) DEFAULT NULL,
  `cantidad` int DEFAULT NULL,
  `fecha` date DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `gasto`
--

LOCK TABLES `gasto` WRITE;
/*!40000 ALTER TABLE `gasto` DISABLE KEYS */;
INSERT INTO `gasto` VALUES (1,'flete','efectivo',15000,'2023-01-25'),(2,'flete','efectivo',18000,'2023-02-02'),(3,'flete1','marca',18000,'2023-02-22');
/*!40000 ALTER TABLE `gasto` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `producto`
--

DROP TABLE IF EXISTS `producto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `producto` (
  `id` int NOT NULL AUTO_INCREMENT,
  `codigo` varchar(45) DEFAULT NULL,
  `marca` varchar(45) DEFAULT NULL,
  `talla21` int DEFAULT NULL,
  `talla22` int DEFAULT NULL,
  `talla23` int DEFAULT NULL,
  `talla24` int DEFAULT NULL,
  `talla25` int DEFAULT NULL,
  `talla26` int DEFAULT NULL,
  `talla27` int DEFAULT NULL,
  `talla28` int DEFAULT NULL,
  `talla29` int DEFAULT NULL,
  `talla30` int DEFAULT NULL,
  `talla31` int DEFAULT NULL,
  `talla32` int DEFAULT NULL,
  `talla33` int DEFAULT NULL,
  `talla34` int DEFAULT NULL,
  `talla35` int DEFAULT NULL,
  `descripcion` varchar(45) DEFAULT NULL,
  `cantidad` int DEFAULT NULL,
  `precio` int DEFAULT NULL,
  `provedor` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `producto`
--

LOCK TABLES `producto` WRITE;
/*!40000 ALTER TABLE `producto` DISABLE KEYS */;
INSERT INTO `producto` VALUES (1,'adidas lego','adidas',0,0,1,0,0,0,1,0,0,0,1,1,1,1,1,'niño y niña',7,60000,'HOYOS LUIS EVARDO '),(2,'bota for one','adidas',1,2,1,1,2,1,1,1,1,2,1,1,2,2,2,'niño',21,40000,'MENDEZ GIRALDO RODRIGO ALBERTO '),(3,'runer ax2','runner',0,1,1,1,1,0,0,1,2,0,0,1,2,1,2,'niño y niña',13,60000,'ÁLAMO DAIANA BELEN'),(4,'frozen new','nikke',1,1,2,1,2,1,1,1,1,2,1,1,2,2,1,'niño',13,50000,'MENDEZ GIRALDO RODRIGO ALBERTO '),(5,'boost','puma',1,1,0,0,2,1,1,0,0,1,1,0,1,1,2,'niño',21,110000,' OCHOA CADAVID MARTA LUCIA '),(6,'acis','runner',1,0,1,0,2,0,1,0,1,0,0,1,2,1,2,'niño ',12,70000,'HOYOS LUIS EVARDO ');
/*!40000 ALTER TABLE `producto` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `provedor`
--

DROP TABLE IF EXISTS `provedor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `provedor` (
  `id` int NOT NULL AUTO_INCREMENT,
  `documento` varchar(100) DEFAULT NULL,
  `nombre` varchar(100) DEFAULT NULL,
  `telefono` varchar(100) DEFAULT NULL,
  `correo` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `provedor`
--

LOCK TABLES `provedor` WRITE;
/*!40000 ALTER TABLE `provedor` DISABLE KEYS */;
INSERT INTO `provedor` VALUES (1,'105478086','ÁLAMO DAIANA BELEN','31567483345','DAIANABELEN@GMAIL.COM'),(2,'76308616','CAMACHO LEGARDA LUZ STELLA','3157676308','Luzcamacho@hotmail.com'),(3,'105452834','HOYOS LUIS EVARDO ','3130545286','LUISHOYOS@GMAIL.COM'),(4,'72328586','MENDEZ GIRALDO RODRIGO ALBERTO ','3106328585','GIRA23@GMAIL.COM'),(5,'34564288',' OCHOA CADAVID MARTA LUCIA ','3204564268','ocho345@hotmail.com'),(6,'34564234','Martin Lutero Arguello ','3204564290','ocho345@hotmail.com');
/*!40000 ALTER TABLE `provedor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuario`
--

DROP TABLE IF EXISTS `usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usuario` (
  `id` int NOT NULL,
  `usuario` varchar(40) NOT NULL,
  `contraseña` varchar(40) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuario`
--

LOCK TABLES `usuario` WRITE;
/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
INSERT INTO `usuario` VALUES (1,'ferney','1001880891');
/*!40000 ALTER TABLE `usuario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `venta`
--

DROP TABLE IF EXISTS `venta`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `venta` (
  `id` int NOT NULL AUTO_INCREMENT,
  `cliente` varchar(100) DEFAULT NULL,
  `vendedor` varchar(100) DEFAULT NULL,
  `total` int DEFAULT NULL,
  `fecha` date DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `venta`
--

LOCK TABLES `venta` WRITE;
/*!40000 ALTER TABLE `venta` DISABLE KEYS */;
INSERT INTO `venta` VALUES (1,'ferney Stiven Rueda Gonzalez','concha',90000,'2023-01-25'),(2,'Myriam Roja Villareal','Oscar',140000,'2023-01-25'),(3,'Andres Rueda Hoyos','Cindy',160000,'2023-01-25'),(4,'ferney Stiven Rueda Gonzalez','german',100000,'2023-02-02'),(5,'ferney Stiven Rueda Gonzalez','cindy',80000,'2023-02-22'),(6,'ferney Stiven Rueda Gonzalez','ferney',70000,'2023-02-22'),(7,'ferney Stiven Rueda Gonzalez','german',65000,'2023-02-22');
/*!40000 ALTER TABLE `venta` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-03-07 10:10:32
