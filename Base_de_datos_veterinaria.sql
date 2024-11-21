-- MySQL dump 10.13  Distrib 8.0.38, for Win64 (x86_64)
--
-- Host: localhost    Database: veterinaria
-- ------------------------------------------------------
-- Server version	8.0.39

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
-- Table structure for table `beneficio`
--

DROP TABLE IF EXISTS `beneficio`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `beneficio` (
  `codigo` int NOT NULL,
  `nombre` varchar(45) DEFAULT NULL,
  `descripcion` varchar(200) DEFAULT NULL,
  `costo` decimal(10,0) DEFAULT NULL,
  `idTipo_Beneficio` int NOT NULL,
  PRIMARY KEY (`codigo`,`idTipo_Beneficio`),
  KEY `fk_Beneficio_Tipo_Beneficio1_idx` (`idTipo_Beneficio`),
  CONSTRAINT `tipo_beneficio_codigo` FOREIGN KEY (`idTipo_Beneficio`) REFERENCES `tipo_beneficio` (`idTipo_Beneficio`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `beneficio`
--

LOCK TABLES `beneficio` WRITE;
/*!40000 ALTER TABLE `beneficio` DISABLE KEYS */;
INSERT INTO `beneficio` VALUES (1,'Promoción de Bienvenida','10% de descuento en la primera consulta.',5000,1),(2,'Descuento en Medicamentos','5% de descuento en todos los medicamentos.',2000,2),(3,'Servicio Adicional de Baño','Un baño gratuito al año.',30000,3),(4,'Bono de Referidos','Recibe un bono por cada cliente referido.',10000,4),(5,'Promoción de Temporada','15% de descuento en servicios durante octubre.',7500,1),(6,'Descuento en Vacunaciones','10% de descuento en todas las vacunaciones.',4000,2),(7,'Servicio Adicional de Desparasitación','Una desparasitación adicional al año.',15000,3),(8,'Bono de Regalo para Mascotas','Un regalo anual para la mascota.',5000,4),(9,'Promoción en Emergencias','Descuento del 20% en emergencias.',16000,1),(10,'Descuento en Servicios Adicionales','Descuento del 15% en servicios adicionales.',12000,2),(11,'Servicio Adicional de Limpieza Dental','Una limpieza dental gratuita al año.',40000,3),(12,'Bono Especial de Cumpleaños','Un bono especial en el cumpleaños de la mascota.',15000,4),(13,'Promoción Familiar','Descuento del 25% en servicios para la segunda mascota.',25000,1),(14,'Descuento en Terapias','10% de descuento en todas las terapias.',10000,2),(15,'Servicio Adicional de Consulta Familiar','Una consulta familiar gratuita al año.',50000,3),(16,'Bono de Fidelidad','Bono especial por mantener el plan durante más de un año.',20000,4),(17,'Promoción Senior','15% de descuento en chequeos regulares.',10500,1),(18,'Descuento en Medicamentos Senior','Descuento del 20% en medicamentos para mascotas mayores.',8000,2),(19,'Servicio Adicional de Rehabilitación','Una sesión gratuita de rehabilitación.',45000,3),(20,'Bono Especial Senior','Bono especial para mascotas mayores de 8 años.',15000,4),(21,'Promoción en Emergencias','20% de descuento en emergencias nocturnas.',24000,1),(22,'Descuento en Consultas','Descuento del 10% en todas las consultas adicionales.',12000,2),(23,'Servicio Adicional de Transporte','Un transporte gratuito para emergencias.',35000,3),(24,'Bono por Emergencia','Bono especial en caso de emergencia grave.',20000,4),(25,'Promoción en Servicios Dentales','15% de descuento en servicios dentales adicionales.',60000,1),(26,'Descuento en Productos Dentales','5% de descuento en productos para cuidado dental.',25000,2),(27,'Servicio Adicional de Limpieza Dental','Una limpieza dental gratuita cada seis meses.',80000,3),(28,'Bono de Regalo Dental','Un kit dental gratuito para la mascota.',35000,4),(29,'Promoción de Bienestar','Descuento del 10% en todas las sesiones de bienestar.',90000,1),(30,'Descuento en Entrenamiento','5% de descuento en todas las sesiones de entrenamiento.',45000,2),(31,'Servicio Adicional de Terapia','Una sesión de terapia de bienestar incluida.',150000,3),(32,'Bono de Bienestar','Un bono para productos de bienestar animal.',60000,4),(33,'Promoción para Mascotas Activas','Descuento del 20% en chequeos para mascotas activas.',120000,1),(34,'Descuento en Productos Deportivos','10% de descuento en productos deportivos para mascotas.',80000,2),(35,'Servicio Adicional de Entrenamiento Deportivo','Una sesión de entrenamiento deportivo al mes.',200000,3),(36,'Bono de Actividad Física','Un bono especial para mascotas activas.',90000,4),(37,'Promoción en Rehabilitación','Descuento del 10% en sesiones adicionales de rehabilitación.',99000,1),(38,'Descuento en Equipos de Rehabilitación','5% de descuento en equipos para rehabilitación.',50000,2),(39,'Servicio Adicional de Rehabilitación Física','Una sesión adicional de rehabilitación física cada tres meses.',120000,3),(40,'Bono Especial de Rehabilitación','Un bono de descuento en servicios de rehabilitación.',65000,4);
/*!40000 ALTER TABLE `beneficio` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cargo`
--

DROP TABLE IF EXISTS `cargo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cargo` (
  `codigo` int NOT NULL,
  `nombre` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`codigo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cargo`
--

LOCK TABLES `cargo` WRITE;
/*!40000 ALTER TABLE `cargo` DISABLE KEYS */;
INSERT INTO `cargo` VALUES (0,'Recepcionista'),(1,'Limpieza'),(2,'Asistente');
/*!40000 ALTER TABLE `cargo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `centroveterinario`
--

DROP TABLE IF EXISTS `centroveterinario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `centroveterinario` (
  `codigo` int NOT NULL,
  `nombre` varchar(45) DEFAULT NULL,
  `direccion` varchar(45) DEFAULT NULL,
  `horario_atencion` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`codigo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `centroveterinario`
--

LOCK TABLES `centroveterinario` WRITE;
/*!40000 ALTER TABLE `centroveterinario` DISABLE KEYS */;
/*!40000 ALTER TABLE `centroveterinario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `consulta`
--

DROP TABLE IF EXISTS `consulta`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `consulta` (
  `codigo` int NOT NULL,
  `fecha` datetime DEFAULT NULL,
  `motivo` varchar(45) DEFAULT NULL,
  `costo` decimal(10,0) DEFAULT NULL,
  `Mascota_codigo` int NOT NULL,
  PRIMARY KEY (`codigo`),
  KEY `fk_Consulta_Mascota1_idx` (`Mascota_codigo`),
  CONSTRAINT `fk_Consulta_Mascota1` FOREIGN KEY (`Mascota_codigo`) REFERENCES `mascota` (`codigo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `consulta`
--

LOCK TABLES `consulta` WRITE;
/*!40000 ALTER TABLE `consulta` DISABLE KEYS */;
/*!40000 ALTER TABLE `consulta` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `contratoafiliacion`
--

DROP TABLE IF EXISTS `contratoafiliacion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `contratoafiliacion` (
  `codigo` int NOT NULL,
  `fechaInicio` varchar(45) DEFAULT NULL,
  `Afiliado_Usuario_cedula` int NOT NULL,
  `Plan_codigo` int NOT NULL,
  `Pago_codigo` int NOT NULL,
  PRIMARY KEY (`codigo`),
  KEY `fk_ContratoAfiliacion_Afiliado1_idx` (`Afiliado_Usuario_cedula`),
  KEY `fk_ContratoAfiliacion_Plan1_idx` (`Plan_codigo`),
  KEY `fk_ContratoAfiliacion_Pago1_idx` (`Pago_codigo`),
  CONSTRAINT `fk_ContratoAfiliacion_Afiliado1` FOREIGN KEY (`Afiliado_Usuario_cedula`) REFERENCES `afiliado` (`Usuario_cedula`),
  CONSTRAINT `fk_ContratoAfiliacion_Pago1` FOREIGN KEY (`Pago_codigo`) REFERENCES `factura` (`codigo`),
  CONSTRAINT `fk_ContratoAfiliacion_Plan1` FOREIGN KEY (`Plan_codigo`) REFERENCES `plan` (`codigo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `contratoafiliacion`
--

LOCK TABLES `contratoafiliacion` WRITE;
/*!40000 ALTER TABLE `contratoafiliacion` DISABLE KEYS */;
/*!40000 ALTER TABLE `contratoafiliacion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `detalleconsulta`
--

DROP TABLE IF EXISTS `detalleconsulta`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `detalleconsulta` (
  `Veterinario_Usuario_cedula` int NOT NULL,
  `Consulta_codigo` int NOT NULL,
  `observaciones` varchar(255) DEFAULT NULL,
  `Formula_codgio` int NOT NULL,
  KEY `fk_ConsultaTratamiento_Veterinario1_idx` (`Veterinario_Usuario_cedula`),
  KEY `fk_DetalleConsulta_Consulta1_idx` (`Consulta_codigo`),
  KEY `fk_DetalleConsulta_Formula1_idx` (`Formula_codgio`),
  CONSTRAINT `fk_ConsultaTratamiento_Veterinario1` FOREIGN KEY (`Veterinario_Usuario_cedula`) REFERENCES `veterinario` (`Usuario_cedula`),
  CONSTRAINT `fk_DetalleConsulta_Consulta1` FOREIGN KEY (`Consulta_codigo`) REFERENCES `consulta` (`codigo`),
  CONSTRAINT `fk_DetalleConsulta_Formula1` FOREIGN KEY (`Formula_codgio`) REFERENCES `formula` (`codgio`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `detalleconsulta`
--

LOCK TABLES `detalleconsulta` WRITE;
/*!40000 ALTER TABLE `detalleconsulta` DISABLE KEYS */;
/*!40000 ALTER TABLE `detalleconsulta` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ejecutivo`
--

DROP TABLE IF EXISTS `ejecutivo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ejecutivo` (
  `Usuario_cedula` int NOT NULL,
  `Cargo_codigo` int NOT NULL,
  `fecha_ingreso` date DEFAULT NULL,
  `salario` decimal(10,0) DEFAULT NULL,
  `horario_laboral` varchar(60) DEFAULT NULL,
  PRIMARY KEY (`Usuario_cedula`),
  KEY `fk_Ejecutivo_Usuario1_idx` (`Usuario_cedula`),
  KEY `fk_Ejecutivo_Cargo1_idx` (`Cargo_codigo`),
  CONSTRAINT `fk_Ejecutivo_Cargo1` FOREIGN KEY (`Cargo_codigo`) REFERENCES `cargo` (`codigo`),
  CONSTRAINT `fk_Ejecutivo_Usuario1` FOREIGN KEY (`Usuario_cedula`) REFERENCES `persona` (`cedula`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ejecutivo`
--

LOCK TABLES `ejecutivo` WRITE;
/*!40000 ALTER TABLE `ejecutivo` DISABLE KEYS */;
/*!40000 ALTER TABLE `ejecutivo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `especie`
--

DROP TABLE IF EXISTS `especie`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `especie` (
  `codigo` int NOT NULL,
  `nombre` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`codigo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `especie`
--

LOCK TABLES `especie` WRITE;
/*!40000 ALTER TABLE `especie` DISABLE KEYS */;
INSERT INTO `especie` VALUES (0,'PERRO'),(1,'GATO'),(2,'AVE'),(3,'REPTIL'),(4,'ROEDOR');
/*!40000 ALTER TABLE `especie` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `estado`
--

DROP TABLE IF EXISTS `estado`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `estado` (
  `codigo` int NOT NULL,
  `nombre` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`codigo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `estado`
--

LOCK TABLES `estado` WRITE;
/*!40000 ALTER TABLE `estado` DISABLE KEYS */;
INSERT INTO `estado` VALUES (0,'ACTIVO'),(1,'INACTIVO'),(2,'RETIRADO'),(3,'EN_MORA');
/*!40000 ALTER TABLE `estado` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `factura`
--

DROP TABLE IF EXISTS `factura`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `factura` (
  `codigo` int NOT NULL,
  `fechaGeneracion` datetime DEFAULT NULL,
  `monto` decimal(10,0) DEFAULT NULL,
  PRIMARY KEY (`codigo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `factura`
--

LOCK TABLES `factura` WRITE;
/*!40000 ALTER TABLE `factura` DISABLE KEYS */;
/*!40000 ALTER TABLE `factura` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `formula`
--

DROP TABLE IF EXISTS `formula`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `formula` (
  `codgio` int NOT NULL,
  `observaciones` varchar(45) DEFAULT NULL,
  `descripcion` varchar(45) DEFAULT NULL,
  `costo` decimal(10,0) DEFAULT NULL,
  PRIMARY KEY (`codgio`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `formula`
--

LOCK TABLES `formula` WRITE;
/*!40000 ALTER TABLE `formula` DISABLE KEYS */;
/*!40000 ALTER TABLE `formula` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `formulamedicamento`
--

DROP TABLE IF EXISTS `formulamedicamento`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `formulamedicamento` (
  `Formula_codgio` int NOT NULL,
  `Medicamento_codigo` int NOT NULL,
  `cantidad` int DEFAULT NULL,
  `dosis` varchar(45) DEFAULT NULL,
  `duracion` int DEFAULT NULL,
  KEY `fk_formulaMedicamento_Formula1_idx` (`Formula_codgio`),
  KEY `fk_formulaMedicamento_Medicamento1_idx` (`Medicamento_codigo`),
  CONSTRAINT `fk_formulaMedicamento_Formula1` FOREIGN KEY (`Formula_codgio`) REFERENCES `formula` (`codgio`),
  CONSTRAINT `fk_formulaMedicamento_Medicamento1` FOREIGN KEY (`Medicamento_codigo`) REFERENCES `medicamento` (`codigo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `formulamedicamento`
--

LOCK TABLES `formulamedicamento` WRITE;
/*!40000 ALTER TABLE `formulamedicamento` DISABLE KEYS */;
/*!40000 ALTER TABLE `formulamedicamento` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `historiaclinica`
--

DROP TABLE IF EXISTS `historiaclinica`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `historiaclinica` (
  `codigo` int NOT NULL,
  `Antecedentes` varchar(50) DEFAULT NULL,
  `fechaApertura` datetime DEFAULT NULL,
  `fechaUltimaConsulta` datetime DEFAULT NULL,
  `HistoriaClinicacol` datetime DEFAULT NULL,
  PRIMARY KEY (`codigo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `historiaclinica`
--

LOCK TABLES `historiaclinica` WRITE;
/*!40000 ALTER TABLE `historiaclinica` DISABLE KEYS */;
/*!40000 ALTER TABLE `historiaclinica` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `mascota`
--

DROP TABLE IF EXISTS `mascota`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `mascota` (
  `codigo` int NOT NULL,
  `nombre` varchar(45) DEFAULT NULL,
  `peso` decimal(5,0) DEFAULT NULL,
  `fechaNacimiento` datetime DEFAULT NULL,
  `especie_codigo` int NOT NULL,
  `Raza_codigo` int NOT NULL,
  `HistoriaClinica_codigo` int DEFAULT NULL,
  `cedula_persona` int NOT NULL,
  PRIMARY KEY (`codigo`),
  KEY `fk_Mascota_Tipo1_idx` (`especie_codigo`),
  KEY `fk_Mascota_Raza1_idx` (`Raza_codigo`),
  KEY `fk_Mascota_HistoriaClinica1_idx` (`HistoriaClinica_codigo`) /*!80000 INVISIBLE */,
  KEY `fk_persona_idx` (`cedula_persona`),
  CONSTRAINT `fk_historia_clinica` FOREIGN KEY (`HistoriaClinica_codigo`) REFERENCES `historiaclinica` (`codigo`),
  CONSTRAINT `fk_Mascota_Raza1` FOREIGN KEY (`Raza_codigo`) REFERENCES `raza` (`codigo`),
  CONSTRAINT `fk_Mascota_Tipo1` FOREIGN KEY (`especie_codigo`) REFERENCES `especie` (`codigo`),
  CONSTRAINT `fk_persona` FOREIGN KEY (`cedula_persona`) REFERENCES `persona` (`cedula`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mascota`
--

LOCK TABLES `mascota` WRITE;
/*!40000 ALTER TABLE `mascota` DISABLE KEYS */;
INSERT INTO `mascota` VALUES (4,'dunkan',6,'2022-10-12 00:00:00',0,0,NULL,101023),(5,'Alana2',23,'2022-10-14 00:00:00',2,1,NULL,101023);
/*!40000 ALTER TABLE `mascota` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `medicamento`
--

DROP TABLE IF EXISTS `medicamento`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `medicamento` (
  `codigo` int NOT NULL,
  `nombre` varchar(45) DEFAULT NULL,
  `concentracion` varchar(20) DEFAULT NULL,
  `indicaciones` varchar(100) DEFAULT NULL,
  `contraindicaciones` varchar(100) DEFAULT NULL,
  `costo_unitario` decimal(10,0) DEFAULT NULL,
  PRIMARY KEY (`codigo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `medicamento`
--

LOCK TABLES `medicamento` WRITE;
/*!40000 ALTER TABLE `medicamento` DISABLE KEYS */;
/*!40000 ALTER TABLE `medicamento` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pago`
--

DROP TABLE IF EXISTS `pago`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pago` (
  `codigo` int NOT NULL,
  `fechaPago` decimal(10,0) DEFAULT NULL,
  `valorTotal` decimal(10,0) DEFAULT NULL,
  `Factura_codigo` int NOT NULL,
  PRIMARY KEY (`codigo`),
  KEY `fk_Pago_Factura1_idx` (`Factura_codigo`),
  CONSTRAINT `fk_Pago_Factura1` FOREIGN KEY (`Factura_codigo`) REFERENCES `factura` (`codigo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pago`
--

LOCK TABLES `pago` WRITE;
/*!40000 ALTER TABLE `pago` DISABLE KEYS */;
/*!40000 ALTER TABLE `pago` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pagoconsulta`
--

DROP TABLE IF EXISTS `pagoconsulta`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pagoconsulta` (
  `Consulta_codigo` int NOT NULL,
  `Formula_codgio` int NOT NULL,
  `Pago_codigo` int NOT NULL,
  `monto_total` decimal(10,0) DEFAULT NULL,
  KEY `fk_PagoConsulta_Consulta1_idx` (`Consulta_codigo`),
  KEY `fk_PagoConsulta_Formula1_idx` (`Formula_codgio`),
  KEY `fk_PagoConsulta_Pago1_idx` (`Pago_codigo`),
  CONSTRAINT `fk_PagoConsulta_Consulta1` FOREIGN KEY (`Consulta_codigo`) REFERENCES `consulta` (`codigo`),
  CONSTRAINT `fk_PagoConsulta_Formula1` FOREIGN KEY (`Formula_codgio`) REFERENCES `formula` (`codgio`),
  CONSTRAINT `fk_PagoConsulta_Pago1` FOREIGN KEY (`Pago_codigo`) REFERENCES `factura` (`codigo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pagoconsulta`
--

LOCK TABLES `pagoconsulta` WRITE;
/*!40000 ALTER TABLE `pagoconsulta` DISABLE KEYS */;
/*!40000 ALTER TABLE `pagoconsulta` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `particular`
--

DROP TABLE IF EXISTS `particular`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `particular` (
  `Usuario_cedula` int NOT NULL,
  `contacto_emergencia` int DEFAULT NULL,
  `es_frecuente` tinyint DEFAULT NULL,
  `comentarios_visita` varchar(200) DEFAULT NULL,
  `fuente_referido` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`Usuario_cedula`),
  KEY `fk_Particular_Usuario_idx` (`Usuario_cedula`),
  CONSTRAINT `fk_Particular_Usuario` FOREIGN KEY (`Usuario_cedula`) REFERENCES `persona` (`cedula`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `particular`
--

LOCK TABLES `particular` WRITE;
/*!40000 ALTER TABLE `particular` DISABLE KEYS */;
/*!40000 ALTER TABLE `particular` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `particularplan`
--

DROP TABLE IF EXISTS `particularplan`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `particularplan` (
  `Particular_Usuario_cedula` int NOT NULL,
  `Plan_codigo` int NOT NULL,
  KEY `fk_ParticularPlan_Particular1_idx` (`Particular_Usuario_cedula`),
  KEY `fk_ParticularPlan_Plan1_idx` (`Plan_codigo`),
  CONSTRAINT `fk_ParticularPlan_Particular1` FOREIGN KEY (`Particular_Usuario_cedula`) REFERENCES `particular` (`Usuario_cedula`),
  CONSTRAINT `fk_ParticularPlan_Plan1` FOREIGN KEY (`Plan_codigo`) REFERENCES `plan` (`codigo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `particularplan`
--

LOCK TABLES `particularplan` WRITE;
/*!40000 ALTER TABLE `particularplan` DISABLE KEYS */;
/*!40000 ALTER TABLE `particularplan` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `persona`
--

DROP TABLE IF EXISTS `persona`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `persona` (
  `cedula` int NOT NULL,
  `primerNombre` varchar(45) NOT NULL,
  `segundoNombre` varchar(45) DEFAULT NULL,
  `primerApellido` varchar(45) NOT NULL,
  `segundoApellido` varchar(45) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  `direccion` varchar(45) DEFAULT NULL,
  `celular` varchar(15) DEFAULT NULL,
  `fecha_vinculacion` datetime(6) DEFAULT NULL,
  `codigo_estado` int DEFAULT NULL,
  `codigo_vinculo` int DEFAULT NULL,
  PRIMARY KEY (`cedula`),
  KEY `codigo_estado_idx` (`codigo_estado`),
  KEY `codigo_vinculo_idx` (`codigo_vinculo`),
  CONSTRAINT `codigo_estado` FOREIGN KEY (`codigo_estado`) REFERENCES `estado` (`codigo`),
  CONSTRAINT `codigo_vinculo` FOREIGN KEY (`codigo_vinculo`) REFERENCES `tipo_vinculo` (`idtipo_vinculo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `persona`
--

LOCK TABLES `persona` WRITE;
/*!40000 ALTER TABLE `persona` DISABLE KEYS */;
INSERT INTO `persona` VALUES (900,'Yeison','Alberto','Alvarez','Ospina','yei@gmail.com','la rivera','3056908978','2024-10-19 11:47:40.000000',0,1),(101023,'Maria','Alejandra','Perez','Cruz','alejandra@gmail.com','armenia','9049595','2024-10-20 13:35:34.000000',1,1);
/*!40000 ALTER TABLE `persona` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `plan`
--

DROP TABLE IF EXISTS `plan`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `plan` (
  `codigo` int NOT NULL,
  `nombre` varchar(45) DEFAULT NULL,
  `descripcion` varchar(200) DEFAULT NULL,
  `costoMensual` decimal(17,2) DEFAULT NULL,
  PRIMARY KEY (`codigo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `plan`
--

LOCK TABLES `plan` WRITE;
/*!40000 ALTER TABLE `plan` DISABLE KEYS */;
INSERT INTO `plan` VALUES (1,'Plan Básico','Cobertura básica para consultas y vacunaciones.',30000.00),(2,'Plan Intermedio','Incluye consultas, vacunaciones y desparasitaciones.',50000.00),(3,'Plan Completo','Cobertura completa incluyendo consultas, vacunas, y emergencias.',80000.00),(4,'Plan Familiar','Cobertura para hasta 3 mascotas en una familia.',100000.00),(5,'Plan Senior','Plan especial para mascotas mayores de 8 años.',70000.00),(6,'Plan Emergencia','Atención de emergencia y consultas ilimitadas.',120000.00),(7,'Plan Salud Dental','Incluye limpieza dental y chequeos.',400000.00),(8,'Plan de Bienestar','Cobertura en salud y bienestar, incluye sesiones de entrenamiento.',900000.00),(9,'Plan Mascota Activa','Incluye chequeos para mascotas activas y deportes.',600000.00),(10,'Plan de Rehabilitación','Incluye terapia y consultas post-operatorias.',110000.00);
/*!40000 ALTER TABLE `plan` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `planbeneficio`
--

DROP TABLE IF EXISTS `planbeneficio`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `planbeneficio` (
  `Plan_codigo` int NOT NULL,
  `Beneficio_codigo` int NOT NULL,
  `fecha_vigencia` date DEFAULT NULL,
  PRIMARY KEY (`Plan_codigo`,`Beneficio_codigo`),
  KEY `fk_PlanBeneficio_Plan1_idx` (`Plan_codigo`),
  KEY `fk_PlanBeneficio_Beneficio1_idx` (`Beneficio_codigo`),
  CONSTRAINT `fk_PlanBeneficio_Beneficio1` FOREIGN KEY (`Beneficio_codigo`) REFERENCES `beneficio` (`codigo`),
  CONSTRAINT `fk_PlanBeneficio_Plan1` FOREIGN KEY (`Plan_codigo`) REFERENCES `plan` (`codigo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `planbeneficio`
--

LOCK TABLES `planbeneficio` WRITE;
/*!40000 ALTER TABLE `planbeneficio` DISABLE KEYS */;
/*!40000 ALTER TABLE `planbeneficio` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `raza`
--

DROP TABLE IF EXISTS `raza`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `raza` (
  `codigo` int NOT NULL,
  `nombre` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`codigo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `raza`
--

LOCK TABLES `raza` WRITE;
/*!40000 ALTER TABLE `raza` DISABLE KEYS */;
INSERT INTO `raza` VALUES (0,'LABRADOR'),(1,'PERSA'),(2,'BEAGLE'),(3,'SIAMES'),(4,'HUSKY'),(5,'CANARIO'),(6,'IGUANA'),(7,'CONEJO');
/*!40000 ALTER TABLE `raza` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `simulacion`
--

DROP TABLE IF EXISTS `simulacion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `simulacion` (
  `simulacion_id` int NOT NULL AUTO_INCREMENT,
  `plan_codigo` int DEFAULT NULL,
  `costo_simulacion` decimal(10,2) NOT NULL,
  PRIMARY KEY (`simulacion_id`),
  KEY `plan_codigo` (`plan_codigo`),
  CONSTRAINT `simulacion_ibfk_1` FOREIGN KEY (`plan_codigo`) REFERENCES `plan` (`codigo`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `simulacion`
--

LOCK TABLES `simulacion` WRITE;
/*!40000 ALTER TABLE `simulacion` DISABLE KEYS */;
INSERT INTO `simulacion` VALUES (3,1,37000.00),(4,4,140000.00),(5,2,175000.00),(6,2,175000.00),(7,8,915000.00),(8,3,110000.00);
/*!40000 ALTER TABLE `simulacion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `simulacion_beneficio`
--

DROP TABLE IF EXISTS `simulacion_beneficio`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `simulacion_beneficio` (
  `simulacion_id` int NOT NULL,
  `beneficio_codigo` int NOT NULL,
  PRIMARY KEY (`simulacion_id`,`beneficio_codigo`),
  KEY `beneficio_codigo` (`beneficio_codigo`),
  CONSTRAINT `simulacion_beneficio_ibfk_1` FOREIGN KEY (`simulacion_id`) REFERENCES `simulacion` (`simulacion_id`),
  CONSTRAINT `simulacion_beneficio_ibfk_2` FOREIGN KEY (`beneficio_codigo`) REFERENCES `beneficio` (`codigo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `simulacion_beneficio`
--

LOCK TABLES `simulacion_beneficio` WRITE;
/*!40000 ALTER TABLE `simulacion_beneficio` DISABLE KEYS */;
INSERT INTO `simulacion_beneficio` VALUES (3,1),(5,1),(6,1),(7,1),(3,2),(4,3),(8,3),(4,4),(7,4),(5,39),(6,39);
/*!40000 ALTER TABLE `simulacion_beneficio` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tipo_beneficio`
--

DROP TABLE IF EXISTS `tipo_beneficio`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tipo_beneficio` (
  `idTipo_Beneficio` int NOT NULL,
  `descripcion` varchar(60) DEFAULT NULL,
  PRIMARY KEY (`idTipo_Beneficio`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tipo_beneficio`
--

LOCK TABLES `tipo_beneficio` WRITE;
/*!40000 ALTER TABLE `tipo_beneficio` DISABLE KEYS */;
INSERT INTO `tipo_beneficio` VALUES (1,'PROMOCION'),(2,'DESCUENTO'),(3,'SERVICIO ADICIONAL'),(4,'BONO');
/*!40000 ALTER TABLE `tipo_beneficio` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tipo_vinculo`
--

DROP TABLE IF EXISTS `tipo_vinculo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tipo_vinculo` (
  `idtipo_vinculo` int NOT NULL,
  `nombre_vinculo` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idtipo_vinculo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tipo_vinculo`
--

LOCK TABLES `tipo_vinculo` WRITE;
/*!40000 ALTER TABLE `tipo_vinculo` DISABLE KEYS */;
INSERT INTO `tipo_vinculo` VALUES (0,'AFILIADO'),(1,'PARTICULAR'),(2,'VETERINARIO'),(3,'ADMINISTRATIVO');
/*!40000 ALTER TABLE `tipo_vinculo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `veterinario`
--

DROP TABLE IF EXISTS `veterinario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `veterinario` (
  `Usuario_cedula` int NOT NULL,
  `Telefono` varchar(45) DEFAULT NULL,
  `CentroVeterinario_codigo` int NOT NULL,
  `especialidad` varchar(60) DEFAULT NULL,
  `annios_experiencia` int DEFAULT NULL,
  PRIMARY KEY (`Usuario_cedula`),
  KEY `fk_Veterinario_Usuario1_idx` (`Usuario_cedula`),
  KEY `fk_Veterinario_CentroVeterinario1_idx` (`CentroVeterinario_codigo`),
  CONSTRAINT `fk_Veterinario_CentroVeterinario1` FOREIGN KEY (`CentroVeterinario_codigo`) REFERENCES `centroveterinario` (`codigo`),
  CONSTRAINT `fk_Veterinario_Usuario1` FOREIGN KEY (`Usuario_cedula`) REFERENCES `persona` (`cedula`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `veterinario`
--

LOCK TABLES `veterinario` WRITE;
/*!40000 ALTER TABLE `veterinario` DISABLE KEYS */;
/*!40000 ALTER TABLE `veterinario` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-10-20 16:16:28
