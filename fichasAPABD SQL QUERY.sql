DROP DATABASE IF EXISTS APAFichas;
CREATE DATABASE APAFichas;
USE APAFichas;


DROP TABLE IF EXISTS fichasLibros;
CREATE TABLE fichasLibros(
id int auto_increment primary key,
nombre varchar(20),
apellido varchar(20),
ano char(4) DEFAULT '(sf)',
titulo varchar(100),
lugarPublicacion varchar(20),
paisPublicacion varchar(20),
editorial varchar(20),
ficha varchar(200));

DROP TABLE IF EXISTS fichasWeb;
CREATE TABLE fichasWeb(
id int auto_increment primary key,
nombre varchar(20),
apellido varchar(20),
ano char(4),
titulo varchar(100),
fechaRecuperacion varchar(30),
enlaceRecuperacion varchar(100),
ficha varchar(200));





DROP PROCEDURE IF EXISTS insertarFichaLibro;
DELIMITER &&
CREATE PROCEDURE insertarFichaLibro(in nombreAutor varchar(20),
 in apellidoAutor varchar(20), in anoPublicacion char(4), 
 in tituloPublicacion varchar(100), in lugar varchar(20), 
 in pais varchar(20), in editorialLibro varchar(20), in fichaLibro varchar(100)
 )
 BEGIN
	INSERT INTO fichasLibros(nombre, apellido, ano, titulo, lugarPublicacion, 
    paisPublicacion, editorial, ficha)
    VALUES(nombreAutor, apellidoAutor, anoPublicacion, tituloPublicacion, lugar, 
    pais, editorialLibro, fichaLibro);
END &&


DROP PROCEDURE IF EXISTS buscarFichaLibro;
DELIMITER &&
CREATE PROCEDURE buscarFichaLibro(in nombreAutor varchar(20), in apellidoAutor varchar(20),
in anoPublicacion char(4), in tituloPublicacion varchar(100), in lugar varchar(20), 
in pais varchar(20), in editorialLibro varchar(20))
BEGIN
	SELECT id, ficha FROM fichasLibros WHERE nombre LIKE CONCAT('%',nombreAutor,'%')
    AND apellido LIKE CONCAT('%',apellidoAutor,'%') AND ano LIKE CONCAT('%',anoPublicacion,'%')
    AND titulo LIKE CONCAT('%',tituloPublicacion,'%') AND lugarPublicacion LIKE CONCAT('%',lugar,'%')
    AND paisPublicacion LIKE CONCAT('%',pais,'%') AND editorial LIKE CONCAT('%',editorialLibro,'%');
END &&

DROP PROCEDURE IF EXISTS buscarFichaWeb;
DELIMITER &&
CREATE PROCEDURE buscarFichaWeb(in nombreAutor varchar(20),
 in apellidoAutor varchar(20), in anoPublicacion char(4), 
 in tituloPublicacion varchar(100), in fecha varchar(30), 
 in enlace varchar(100)
 )
 BEGIN
	SELECT id, ficha FROM fichasWeb WHERE nombre LIKE CONCAT('%',nombreAutor,'%') 
    AND apellido LIKE CONCAT('%',apellidoAutor,'%')
    AND ano LIKE CONCAT('%',anoPublicacion,'%') AND titulo LIKE CONCAT('%',tituloPublicacion,'%') 
    AND fechaRecuperacion LIKE CONCAT('%',fecha,'%')
    AND enlaceRecuperacion LIKE CONCAT ('%',enlace,'%');
END &&

DROP PROCEDURE IF EXISTS modificarDatosLibro;
DELIMITER &&
CREATE PROCEDURE modificarDatosLibro(in idLibro int, in nombreAutor varchar(20), 
in apellidoAutor varchar(20), in anoPublicacion char(4), in tituloPublicacion varchar(100), 
in lugar varchar(20),  in pais varchar(20), in editorialLibro varchar(20))
BEGIN
	UPDATE fichasLibros SET nombre = nombreAutor, apellido = apellidoAutor, ano = anoPublicacion,
    titulo = tituloPublicacion, lugarPublicacion = lugar, paisPublicacion = pais,
    editorial = editorialLibro
    WHERE id = idLibro;
END &&

DROP PROCEDURE IF EXISTS modificarDatosWeb;
DELIMITER &&
CREATE PROCEDURE modificarDatosWeb(in idWeb int, in nombreAutor varchar(20),
 in apellidoAutor varchar(20), in anoPublicacion char(4), 
 in tituloPublicacion varchar(100), in fecha varchar(30), 
 in enlace varchar(100))
 BEGIN
	UPDATE fichasWeb SET nombre = nombreAutor, apellido = apellidoAutor, ano = anoPublicacion,
    titulo = tituloPublicacion, fechaRecuperacion = fecha, enlaceRecuperacion = enlace
    WHERE id = idWeb;
END &&

DROP PROCEDURE IF EXISTS insertarFichaWeb;
DELIMITER &&
CREATE PROCEDURE insertarFichaWeb(in nombreAutor varchar(20),
 in apellidoAutor varchar(20), in anoPublicacion char(4), 
 in tituloPublicacion varchar(100), in fecha varchar(30), 
 in enlace varchar(100), in fichaWeb varchar(100)
 )
 BEGIN
	INSERT INTO fichasWeb(nombre, apellido, ano, titulo, fechaRecuperacion, 
    enlaceRecuperacion, ficha)
    VALUES(nombreAutor, apellidoAutor, anoPublicacion, tituloPublicacion, fecha, 
    enlace, fichaWeb);
END &&


select * from fichasWeb;
select * from fichaslibros
