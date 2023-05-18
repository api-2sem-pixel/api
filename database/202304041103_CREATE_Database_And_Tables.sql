CREATE DATABASE api2sem;
USE api2sem;

DROP TABLE IF EXISTS `Parametrizacao`;
CREATE TABLE `Parametrizacao` (
  `Parametro` varchar(100) NOT NULL,
  `Valor` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`Parametro`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
INSERT INTO `Parametrizacao` VALUES ('1601','2.00'),
                                    ('1602','1.00'),
                                    ('1809','4.00'),
                                    ('3000','5.00'),
                                    ('3001','1.00');

DROP TABLE IF EXISTS `Tipo_Usuario`;
CREATE TABLE `Tipo_Usuario` (
  `Id` int NOT NULL,
  `Descricao` varchar(20) NOT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
INSERT INTO `Tipo_Usuario` VALUES (1,'Colaborador'),
                                  (2,'Gestor'),
                                  (3,'Administrador');

DROP TABLE IF EXISTS `Modalidade`;
CREATE TABLE `Modalidade` (
  `Id` int NOT NULL AUTO_INCREMENT,
  `Descricao` varchar(20) NOT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
INSERT INTO `Modalidade` VALUES (1,'Hora Extra'),
                                (2,'Sobreaviso');

DROP TABLE IF EXISTS `Etapa_Extrato`;
CREATE TABLE `Etapa_Extrato` (
  `Id` int NOT NULL AUTO_INCREMENT,
  `Descricao` varchar(40) NOT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
INSERT INTO `Etapa_Extrato` VALUES (1,'Em Aprovação'),
                                   (2,'Aprovada'),
                                   (3,'Reprovada'),
                                   (4,'Pendente de Correção');

DROP TABLE IF EXISTS `Cliente`;
CREATE TABLE `Cliente` (
  `Id` int NOT NULL AUTO_INCREMENT,
  `Razao_Social` varchar(100) NOT NULL,
  `Cnpj` varchar(14) NOT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
INSERT INTO `Cliente` VALUES (1,'Americanas','90800298000107'),
                             (2,'Google','47753663000140'),
                             (5,'Embraer','01234567898485'),
                             (7,'time','545465');

DROP TABLE IF EXISTS `Cr`;
CREATE TABLE `Cr` (
  `Id` int NOT NULL AUTO_INCREMENT,
  `Nome` varchar(40) NOT NULL,
  `Sigla` varchar(50) DEFAULT NULL,
  `Codigo_CR` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO `Cr` VALUES (3,'Pixel','PX','1A'),
                        (4,'Conectados','Conect','1B'),
                        (5,'Tech','TC','1C'),
                        (9,'teste','a1','px');


DROP TABLE IF EXISTS `Usuario`;
CREATE TABLE `Usuario` (
  `Id` int NOT NULL AUTO_INCREMENT,
  `Id_Tipo_Usuario` int NOT NULL,
  `Cpf_Cnpj` varchar(14) NOT NULL,
  `Nome` varchar(150) NOT NULL,
  `Telefone` int DEFAULT NULL,
  `Email` varchar(100) NOT NULL,
  PRIMARY KEY (`Id`),
  KEY `Id_Tipo_Usuario` (`Id_Tipo_Usuario`),
  CONSTRAINT `Usuario_ibfk_1` FOREIGN KEY (`Id_Tipo_Usuario`) REFERENCES `Tipo_Usuario` (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO `Usuario` VALUES (1,2,'38333147887','Robert Renan',NULL,'robert@rp.com'),
                             (2,1,'25333144887','Mario Alberto',NULL,'mario@rp.com'),
                             (3,1,'67481629803','Robson de Souza',NULL,'rbsouza@rp.com'),
                             (4,1,'56702257830','Katia Fonseca',NULL,'katiaf@rp.com'),
                             (5,1,'123','Teste',123,'teste'),
                             (6,1,'090567832504','Carlos de Souza',123,'carlos.souza@rp.com'),
                             (7,1,'45765235409','Joaquim Pereira',123456789,'joca.p@rp.com'),
                             (10,1,'454587151','alisson',12545454,'alisson@rp.com'),
                             (11,2,'123','Gestor',NULL,'gestor@gestor.com'),
                             (12,3,'123','Admin',NULL,'admin@admin.com');

DROP TABLE IF EXISTS `Cr_Usuario`;
CREATE TABLE `Cr_Usuario` (
  `Id_Usuario` int NOT NULL,
  `Id_Cr` int NOT NULL,
  `Temporario` bit(1) NOT NULL DEFAULT b'0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
INSERT INTO `Cr_Usuario` VALUES (1,3,_binary '\0'),
                                (2,3,_binary '\0'),
                                (4,4,_binary '\0'),
                                (1,4,_binary '\0'),
                                (1,3,_binary '\0'),
                                (2,3,_binary '\0'),
                                (3,3,_binary '\0'),
                                (4,3,_binary '\0'),
                                (2,3,_binary ''),
                                (3,3,_binary ''),
                                (2,3,_binary ''),
                                (5,4,_binary '\0'),
                                (4,5,_binary ''),
                                (9,3,_binary ''),
                                (1,3,_binary '');

DROP TABLE IF EXISTS `Extrato_Hora`;
CREATE TABLE `Extrato_Hora` (
  `Id` int NOT NULL AUTO_INCREMENT,
  `Projeto` varchar(100) NOT NULL,
  `Id_Cr` int NOT NULL,
  `Id_Usuario` int NOT NULL,
  `Id_Etapa_Extrato` int NOT NULL,
  `Id_Modalidade` int NOT NULL,
  `Motivo` varchar(300) DEFAULT NULL,
  `DataHora_Inicio` datetime NOT NULL,
  `DataHora_Fim` datetime NOT NULL,
  `Justificativa` varchar(300) DEFAULT NULL,
  `Id_Cliente` int DEFAULT NULL,
  PRIMARY KEY (`Id`),
  KEY `Id_Cr` (`Id_Cr`),
  KEY `Id_Usuario` (`Id_Usuario`),
  KEY `Id_Etapa_Extrato` (`Id_Etapa_Extrato`),
  KEY `Id_Modalidade` (`Id_Modalidade`),
  KEY `Id_Cliente` (`Id_Cliente`),
  CONSTRAINT `Extrato_Hora_ibfk_1` FOREIGN KEY (`Id_Cr`) REFERENCES `Cr` (`Id`),
  CONSTRAINT `Extrato_Hora_ibfk_2` FOREIGN KEY (`Id_Usuario`) REFERENCES `Usuario` (`Id`),
  CONSTRAINT `Extrato_Hora_ibfk_3` FOREIGN KEY (`Id_Etapa_Extrato`) REFERENCES `Etapa_Extrato` (`Id`),
  CONSTRAINT `Extrato_Hora_ibfk_4` FOREIGN KEY (`Id_Modalidade`) REFERENCES `Modalidade` (`Id`),
  CONSTRAINT `Extrato_Hora_ibfk_6` FOREIGN KEY (`Id_Cliente`) REFERENCES `Cliente` (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
INSERT INTO `Extrato_Hora` VALUES (1,'PREENCHA',3,5,1,1,1,'2023-04-23 14:38:39','2023-04-23 14:38:39','Teste',1),
                                  (2,'TEste',4,5,1,1,1,'2023-04-24 00:00:00','2023-04-25 00:00:00','Teste5',2),
                                  (3,'teste',4,1,1,1,NULL,'2024-04-18 00:00:00','2023-04-25 23:35:38','testando',2),
                                  (4,'PREENCHA',3,1,1,1,1,'2023-04-26 20:08:21','2023-04-28 00:00:00','PREENCHA',1);

ALTER TABLE Cliente ADD COLUMN Ativo bit DEFAULT 1;
ALTER TABLE Cr_Usuario ADD COLUMN Ativo bit DEFAULT 1;
ALTER TABLE Usuario ADD COLUMN Ativo bit DEFAULT 1;

alter table Extrato_Hora drop constraint Extrato_Hora_ibfk_5;
alter table Extrato_Hora drop column Id_Motivo;
alter table Extrato_Hora add column Motivo varchar(300);

insert into parametrizacao values('Dia_Fechamento_Inicial', '26');
insert into parametrizacao values('Dia_Fechamento_Final', '25');