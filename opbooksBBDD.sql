-- Configrar BBDD, ejecutar mysql y realizar:
CREATE DATABASE IF NOT EXISTS opbooks;

USE opbooks;

CREATE TABLE IF NOT EXISTS `books` (`id_book` int(10) unsigned NOT NULL,  `title` varchar(255) NOT NULL DEFAULT '',  `isbn` varchar(45) DEFAULT NULL,  `author` varchar(255) DEFAULT NULL,  `genre` varchar(100) DEFAULT NULL,  `date` varchar(45) DEFAULT NULL,  `img` varchar(800) DEFAULT NULL,  `publisher` varchar(200) DEFAULT NULL,  `info` text) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

ALTER TABLE `books` ADD PRIMARY KEY (`id_book`);

ALTER TABLE `books` MODIFY `id_book` int(10) unsigned NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=1;

INSERT INTO `books` (`id_book`,  `title`,  `isbn`,  `author`,  `genre`,  `date`,  `img`,  `publisher`,  `info`) VALUES ("id_book1",  "title1",  "isbn1",  "author1",  "genre1",  "date1",  "http://blogs.lainformacion.com/legal-e-digital/files/2011/05/Libro.jpg",  "publisher1",  "info1");
INSERT INTO `books` (`id_book`,  `title`,  `isbn`,  `author`,  `genre`,  `date`,  `img`,  `publisher`,  `info`) VALUES ("id_book2",  "title2",  "isbn2",  "author2",  "genre2",  "date2",  "http://www.proviasdes.gob.pe/extranet/imagenes/libro.JPG",  "publisher2",  "info2");
INSERT INTO `books` (`id_book`,  `title`,  `isbn`,  `author`,  `genre`,  `date`,  `img`,  `publisher`,  `info`) VALUES ("id_book3",  "title3",  "isbn3",  "author3",  "genre3",  "date3",  "http://www.mineduc.edu.gt/recursoseducativos/wp-content/uploads/2011/02/libro3.jpg",  "publisher3",  "info3");
INSERT INTO `books` (`id_book`,  `title`,  `isbn`,  `author`,  `genre`,  `date`,  `img`,  `publisher`,  `info`) VALUES ("id_book4",  "title4",  "isbn4",  "author4",  "genre4",  "date4",  "http://ideasylibros.es/wp-content/uploads/2013/05/libro.jpg",  "publisher4",  "info4");
INSERT INTO `books` (`id_book`,  `title`,  `isbn`,  `author`,  `genre`,  `date`,  `img`,  `publisher`,  `info`) VALUES ("id_book5",  "title5",  "isbn5",  "author4",  "genre5",  "date5",  "http://ideasylibros.es/wp-content/uploads/2013/05/libro.jpg",  "publisher5",  "info5");
INSERT INTO `books` (`id_book`,  `title`,  `isbn`,  `author`,  `genre`,  `date`,  `img`,  `publisher`,  `info`) VALUES ("id_book6",  "title6",  "isbn6",  "author4",  "genre6",  "date6",  "http://ideasylibros.es/wp-content/uploads/2013/05/libro.jpg",  "publisher6",  "info6");
INSERT INTO `books` (`id_book`,  `title`,  `isbn`,  `author`,  `genre`,  `date`,  `img`,  `publisher`,  `info`) VALUES ("id_book7",  "title7",  "isbn7",  "author4",  "genre7",  "date7",  "http://ideasylibros.es/wp-content/uploads/2013/05/libro.jpg",  "publisher7",  "info7");
INSERT INTO `books` (`id_book`,  `title`,  `isbn`,  `author`,  `genre`,  `date`,  `img`,  `publisher`,  `info`) VALUES ("id_book8",  "title8",  "isbn8",  "author4",  "genre8",  "date8",  "http://ideasylibros.es/wp-content/uploads/2013/05/libro.jpg",  "publisher8",  "info8");
INSERT INTO `books` (`id_book`,  `title`,  `isbn`,  `author`,  `genre`,  `date`,  `img`,  `publisher`,  `info`) VALUES ("id_book9",  "title1",  "isbn2",  "author4",  "genre3",  "date1",  "http://ideasylibros.es/wp-content/uploads/2013/05/libro.jpg",  "publisher2",  "info8");
INSERT INTO `books` (`id_book`,  `title`,  `isbn`,  `author`,  `genre`,  `date`,  `img`,  `publisher`,  `info`) VALUES ("id_bookA",  "title2",  "isbn3",  "author4",  "genre1",  "date3",  "http://ideasylibros.es/wp-content/uploads/2013/05/libro.jpg",  "publisher1",  "info8");
INSERT INTO `books` (`id_book`,  `title`,  `isbn`,  `author`,  `genre`,  `date`,  `img`,  `publisher`,  `info`) VALUES ("id_bookB",  "title3",  "isbn1",  "author4",  "genre2",  "date2",  "http://ideasylibros.es/wp-content/uploads/2013/05/libro.jpg",  "publisher3",  "info8");

