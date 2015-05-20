-- Configrar BBDD, ejecutar mysql y realizar:
-- Crea la base de datos
CREATE DATABASE IF NOT EXISTS opbooks;
-- Cambia a la base de datos de la aplicación
USE opbooks;
-- Crea la tabla en caso de que no exista.
CREATE TABLE IF NOT EXISTS `books` (`id_book` int(10) unsigned NOT NULL,  `title` varchar(255) NOT NULL DEFAULT '',  `isbn` varchar(45) DEFAULT NULL,  `author` varchar(255) DEFAULT NULL,  `genre` varchar(100) DEFAULT NULL,  `date` varchar(45) DEFAULT NULL,  `img` varchar(800) DEFAULT NULL,  `publisher` varchar(200) DEFAULT NULL,  `info` text) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;
-- Modificaciones de la tabla
ALTER TABLE `books` ADD PRIMARY KEY (`id_book`);

ALTER TABLE `books` MODIFY `id_book` int(10) unsigned NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=1;
