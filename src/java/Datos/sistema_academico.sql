host cls

drop database if exists lab_academica;
create database lab_academica;

use lab_academica;

DROP TABLE IF EXISTS `ciclo`;

CREATE TABLE `ciclo` (
  `codigo` int  NOT NULL AUTO_INCREMENT,
  `anho` varchar(50) NOT NULL ,
   `numero` int NOT NULL ,
   `fecha_inicio` date DEFAULT NULL,
   `fecha_final` date DEFAULT NULL,
  PRIMARY KEY (`codigo`)
) ;

DROP TABLE IF EXISTS `carrera`;

CREATE TABLE `carrera` (
  `codigo` int  NOT NULL AUTO_INCREMENT,
  `nombre` varchar(50) NOT NULL ,
    `titulo` varchar(50) NOT NULL ,
  PRIMARY KEY (`codigo`)
) ;


DROP TABLE IF EXISTS `curso`;

CREATE TABLE `curso` (
  `codigo` int NOT NULL AUTO_INCREMENT,
  `nombre` int  NOT NULL ,
  `creditos` varchar(50) NOT NULL ,
  `horas_semanales` varchar(50) NOT NULL ,
  `carrera_id` int,
   `ciclo_id` int,
  CONSTRAINT `carrera_fk` FOREIGN KEY (`carrera_id`) REFERENCES `carrera` (`codigo`),
  CONSTRAINT `ciclo_fk` FOREIGN KEY (`ciclo_id`) REFERENCES `ciclo` (`codigo`),
  PRIMARY KEY (`codigo`)
);

DROP TABLE IF EXISTS `usuario`;

CREATE TABLE `usuarios` (
  `id` varchar(50)  NOT NULL,
  `cedula` varchar(50) NOT NULL ,
  `contrasenna` varchar(50) NOT NULL ,
  `rol` int NOT NULL ,
  PRIMARY KEY (`id`)
) ;

DROP TABLE IF EXISTS `profesor`;

CREATE TABLE `profesor` (
  `cedula` varchar(50)  NOT NULL,
  `nombre` varchar(50) NOT NULL ,
  `telefono` varchar(50) NOT NULL ,
  `email` varchar(50) NOT NULL ,
  `fecha_nacimiento` date NOT NULL ,
  PRIMARY KEY (`cedula`)
) ;

DROP TABLE IF EXISTS `alumno`;

CREATE TABLE `alumno` (
  `cedula` varchar(50)  NOT NULL,
  `nombre` varchar(50) NOT NULL ,
  `telefono` varchar(50) NOT NULL ,
  `email` varchar(50) NOT NULL ,
  `fecha_nacimiento` date NOT NULL ,
  PRIMARY KEY (`cedula`)
) ;


DROP TABLE IF EXISTS `grupo`;

CREATE TABLE `tiquetesComprados` (
  `codigo` int NOT NULL AUTO_INCREMENT,
  `ciclo_id` int DEFAULT NULL,
  `curso_id` varchar(50) NOT NULL,
  `num_grupo` varchar(50) NOT NULL,
  `horario` date NOT NULL,
  `profesor_id` varchar(50) NOT NULL,
  PRIMARY KEY (`codigo` ),
  CONSTRAINT `ciclo_fk` FOREIGN KEY (`ciclo_id`) REFERENCES `ciclo` (`codigo`),
  CONSTRAINT `curso_fk` FOREIGN KEY (`curso_id`) REFERENCES `curso` (`codigo`),
  CONSTRAINT `profesor_fk` FOREIGN KEY (`profesor_id`) REFERENCES `profesor` (`cedula`)
);



-- CREAR PROCEDIMIENTOS
-- CREAR FUNCIONES

--aca programan la funcion 

create or replace function fun_calcular(Pnum1 in number, Pnum2 in number, PID in varchar2) return number is
  
	
	
end fun_calcular;
/
show error
