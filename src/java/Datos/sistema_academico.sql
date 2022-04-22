--Lab1 Sistema de gestion academica


-- -----------------------------------------------------
-- Dropeo de tablas y objetos
-- -----------------------------------------------------


DROP TABLE carrera cascade constraints;
DROP TABLE curso cascade constraints;
DROP TABLE rol cascade constraints;
DROP TABLE usuario cascade constraints;
DROP TABLE profesor cascade constraints;
DROP TABLE grupo cascade constraints;
DROP TABLE alumno cascade constraints;
DROP TABLE ciclo cascade constraints;
DROP TABLE matricula cascade constraints;
DROP TABLE estado cascade constraints;

-- -----------------------------------------------------
-- Creacion de tablas
-- -----------------------------------------------------

CREATE TABLE  carrera (codigo int  NOT NULL, nombre varchar(50) NOT NULL, titulo varchar(50) NOT NULL) tablespace system;
CREATE TABLE curso (codigo int NOT NULL, nombre varchar(50) NOT NULL, creditos int NOT NULL, horas_semanales int NOT NULL ,carrera_id int,ciclo_id int) tablespace system;
CREATE TABLE rol (id_rol INT NOT NULL, descripcion VARCHAR(45) NOT NULL) tablespace system;
CREATE TABLE usuario (usuario_id VARCHAR(45) NOT NULL, rol_id INT NOT NULL, clave VARCHAR(45) NOT NULL) tablespace system;
CREATE TABLE profesor (cedula INT NOT NULL, usuario_id VARCHAR(50) NOT NULL, nombre varchar(50) NOT NULL,telefono varchar(50),email varchar(50), fecha_nacimiento VARCHAR(50) NOT NULL) tablespace system;
CREATE TABLE grupo (codigo int NOT NULL,ciclo_id int DEFAULT NULL,num_grupo INT NOT NULL,curso_id INT NOT NULL,horario varchar(50) NOT NULL,profesor_id INT NOT NULL) tablespace system;
CREATE TABLE alumno (cedula INT NOT NULL, usuario_id VARCHAR(45) NOT NULL, nombre varchar(50) NOT NULL, telefono varchar(50), email varchar(50), fecha_nacimiento VARCHAR(50) NOT NULL, carrera_id int) tablespace system;
CREATE TABLE estado (id_estado INT NOT NULL, descripcion VARCHAR(45) NOT NULL) tablespace system;
CREATE TABLE matricula (alumno_id INT NOT NULL, grupo_num INT NOT NULL, curso_id INT NOT NULL, estado_id INT NOT NULL, nota INT) tablespace system;
CREATE TABLE  ciclo (codigo int  NOT NULL, anho varchar(50) NOT NULL, numero int NOT NULL, fecha_inicio varchar(50) DEFAULT NULL, fecha_final varchar(50) DEFAULT NULL) tablespace system;

-- -----------------------------------------------------
-- Creacion de PKs
-- -----------------------------------------------------
alter table carrera add constraint carrera_pk primary key (codigo);
alter table curso add constraint curso_pk primary key (codigo);
alter table rol add constraint rol_pk primary key (id_rol);
alter table usuario add constraint usuario_pk primary key (usuario_id);
alter table profesor add constraint profesor_pk primary key (cedula);
alter table grupo add constraint grupo_pk primary key (codigo, curso_id);
alter table alumno add constraint alumno_pk primary key (cedula);
alter table estado add constraint estado_pk primary key (id_estado);
alter table matricula add constraint matricula_pk primary key (alumno_id, grupo_num, curso_id);
alter table ciclo add constraint ciclo_pk primary key (codigo);

-- -----------------------------------------------------
-- Creacion de FKs
-- -----------------------------------------------------

alter table curso add constraint fk_curso_carrera1 foreign key (carrera_id) references carrera;
alter table curso add constraint fk_curso_ciclo1 foreign key (ciclo_id) references ciclo;
alter table usuario add constraint fk_usuario_rol foreign key (rol_id) references rol;
alter table profesor add constraint fk_profesor_usuario1 foreign key (usuario_id) references usuario;
alter table grupo add constraint fk_grupo_curso1 foreign key (curso_id) references curso;
alter table grupo add constraint fk_grupo_profesor1 foreign key (profesor_id) references profesor;
alter table grupo add constraint fk_grupo_ciclo1 foreign key (ciclo_id) references ciclo;
alter table alumno add constraint fk_alumno_usuario1 foreign key (usuario_id) references usuario;
alter table alumno add constraint fk_alumno_carrera1 foreign key (carrera_id) references carrera;
alter table matricula add constraint fk_matricula_alumno1 foreign key (alumno_id) references alumno;
alter table matricula add constraint fk_matricula_grupo1 foreign key (grupo_num , curso_id) references grupo;
alter table matricula add constraint fk_matricula_estado1 foreign key (estado_id) references estado;




-- -----------------------------------------------------
-- Procedimientos almacenados 
-- -----------------------------------------------------
--
CREATE OR REPLACE PACKAGE TYPES
AS
TYPE REF_CURSOR IS REF CURSOR;
END;
/


----------------- TABLA CARRERA -----------------
---INSERTAR
CREATE OR REPLACE PROCEDURE INSERTAR_CARRERA(CODIGO_CARRERA IN CARRERA.CODIGO%TYPE, NOMBRE IN CARRERA.NOMBRE%TYPE, TITULO IN CARRERA.TITULO%TYPE)
AS 
BEGIN
INSERT INTO CARRERA VALUES(CODIGO_CARRERA, NOMBRE, TITULO);

END;
/
---MODIFICAR
CREATE OR REPLACE PROCEDURE MODIFICAR_CARRERA (CODIGO_CARRERAIN IN CARRERA.CODIGO%TYPE, NOMBREIN IN CARRERA.NOMBRE%TYPE, TITULOIN IN CARRERA.TITULO%TYPE)
AS
BEGIN
UPDATE carrera SET nombre=NOMBREIN, titulo=TITULOIN WHERE codigo=CODIGO_CARRERAIN;
END;
/
---BUSCAR
CREATE OR REPLACE FUNCTION BUSCAR_CARRERA(idbuscar IN CARRERA.CODIGO%TYPE)
RETURN Types.ref_cursor 
AS 
        carrera_cursor types.ref_cursor; 
BEGIN 
  OPEN carrera_cursor FOR 
       SELECT codigo,nombre,titulo FROM carrera WHERE codigo=idbuscar; 
RETURN carrera_cursor; 
END;
/
---LISTAR
CREATE OR REPLACE FUNCTION LISTAR_CARRERA
RETURN Types.ref_cursor 
AS 
        carrera_cursor types.ref_cursor; 
BEGIN 
  OPEN carrera_cursor FOR 
       SELECT codigo,nombre,titulo FROM carrera; 
RETURN carrera_cursor; 
END;
/
---BORRAR 
create or replace procedure ELIMINAR_CARRERA(CODIGO_CARRERAIN IN CARRERA.CODIGO%TYPE)
as
begin
    delete from carrera where codigo=CODIGO_CARRERAIN;
end;
/
----------------- TABLA CURSO ----------------- 
---INSERTAR
CREATE OR REPLACE PROCEDURE INSERTAR_CURSO(CODIGO_CURSO IN CURSO.CODIGO%TYPE, NOMBRE IN CURSO.NOMBRE%TYPE, CREDITOS IN CURSO.CREDITOS%TYPE, HORAS_SEMANALES IN CURSO.HORAS_SEMANALES%TYPE, CARRERA_ID IN CURSO.CARRERA_ID%TYPE, CICLO_ID IN CURSO.CICLO_ID%TYPE)
AS 
BEGIN
INSERT INTO CURSO VALUES(CODIGO_CURSO, NOMBRE, CREDITOS, HORAS_SEMANALES,CARRERA_ID,CICLO_ID);

END;
/
---MODIFICAR
CREATE OR REPLACE PROCEDURE MODIFICAR_CURSO (CODIGO_CURSOIN IN CURSO.CODIGO%TYPE, NOMBREIN IN CURSO.NOMBRE%TYPE, CREDITOSIN IN CURSO.CREDITOS%TYPE, HORAS_SEMANALESIN IN CURSO.HORAS_SEMANALES%TYPE, CARRERA_IDIN IN CURSO.CARRERA_ID%TYPE, CICLO_IDIN IN CURSO.CICLO_ID%TYPE)
AS
BEGIN
UPDATE CURSO SET NOMBRE=NOMBREIN, CREDITOS=CREDITOSIN, HORAS_SEMANALES=HORAS_SEMANALESIN,CARRERA_ID=CARRERA_IDIN,CICLO_ID=CICLO_IDIN WHERE codigo=CODIGO_CURSOIN;
END;
/
---BUSCAR
CREATE OR REPLACE FUNCTION BUSCAR_CURSO_ID(idbuscar IN CURSO.CODIGO%TYPE)
RETURN Types.ref_cursor 
AS 
        curso_cursor types.ref_cursor; 
BEGIN 
  OPEN curso_cursor FOR 
       SELECT CODIGO, NOMBRE, CREDITOS, HORAS_SEMANALES,CARRERA_ID,CICLO_ID FROM CURSO WHERE codigo=idbuscar OR CARRERA_ID=idbuscar;
RETURN curso_cursor; 
END;
/



CREATE OR REPLACE FUNCTION BUSCAR_CURSO_CARRERA(idbuscar in CURSO.CARRERA_ID%TYPE)
RETURN Types.ref_cursor 
AS 
        curso_cursor types.ref_cursor; 
BEGIN 
  OPEN curso_cursor FOR 
       SELECT CODIGO, NOMBRE, CREDITOS, HORAS_SEMANALES,CARRERA_ID,CICLO_ID FROM CURSO WHERE CARRERA_ID=idbuscar;
RETURN curso_cursor; 
END;
/

CREATE OR REPLACE FUNCTION BUSCAR_CURSO_Nombre(idbuscar IN CURSO.nombre%TYPE)
RETURN Types.ref_cursor 
AS 
        curso_cursor types.ref_cursor; 
BEGIN 
  OPEN curso_cursor FOR 
       SELECT CODIGO, NOMBRE, CREDITOS, HORAS_SEMANALES,CARRERA_ID,CICLO_ID FROM CURSO WHERE NOMBRE=idbuscar;
RETURN curso_cursor; 
END;
/


---LISTAR
CREATE OR REPLACE FUNCTION LISTAR_CURSO
RETURN Types.ref_cursor 
AS 
        curso_cursor types.ref_cursor; 
BEGIN 
  OPEN curso_cursor FOR 
       SELECT CODIGO, NOMBRE, CREDITOS, HORAS_SEMANALES,CARRERA_ID,CICLO_ID FROM CURSO; 
RETURN curso_cursor; 
END;
/
---BORRAR 
create or replace procedure ELIMINAR_CURSO(CODIGO_CURSOIN IN CURSO.CODIGO%TYPE)
as
begin
    delete from CURSO where codigo=CODIGO_CURSOIN;
end;
/
----------------- TABLA ROL ----------------- 
---INSERTAR
CREATE OR REPLACE PROCEDURE INSERTAR_ROL(ID_ROL IN ROL.ID_ROL%TYPE, DESCRIPCION IN ROL.DESCRIPCION%TYPE)
AS 
BEGIN
INSERT INTO ROL VALUES(ID_ROL, DESCRIPCION);

END;
/

---BUSCAR
CREATE OR REPLACE FUNCTION BUSCAR_ROL(idbuscar IN ROL.ID_ROL%TYPE)
RETURN Types.ref_cursor 
AS 
        rol_cursor types.ref_cursor; 
BEGIN 
  OPEN rol_cursor FOR 
       SELECT ID_ROL, DESCRIPCION FROM ROL WHERE ID_ROL=idbuscar; 
RETURN rol_cursor; 
END;
/
---LISTAR
CREATE OR REPLACE FUNCTION LISTAR_ROL
RETURN Types.ref_cursor 
AS 
        rol_cursor types.ref_cursor; 
BEGIN 
  OPEN rol_cursor FOR 
       SELECT ID_ROL, DESCRIPCION FROM ROL; 
RETURN rol_cursor; 
END;
/
---BORRAR 
create or replace procedure ELIMINAR_ROL(ID_ROLIN IN ROL.ID_ROL%TYPE)
as
begin
    delete from ROL where ID_ROL=ID_ROLIN;
end;
/
----------------- TABLA USUARIO -----------------
---INSERTAR
CREATE OR REPLACE PROCEDURE INSERTAR_USUARIO(USUARIO_ID IN USUARIO.USUARIO_ID%TYPE, ROL_ID IN USUARIO.ROL_ID%TYPE, CLAVE IN USUARIO.CLAVE%TYPE)
AS 
BEGIN
INSERT INTO USUARIO VALUES (USUARIO_ID, ROL_ID, CLAVE);

END;
/
---MODIFICAR
CREATE OR REPLACE PROCEDURE MODIFICAR_USUARIO (USUARIO_IDIN IN USUARIO.USUARIO_ID%TYPE, ROL_IDIN IN USUARIO.ROL_ID%TYPE, CLAVEIN IN USUARIO.CLAVE%TYPE)
AS
BEGIN
UPDATE USUARIO SET ROL_ID=ROL_IDIN, CLAVE=CLAVEIN WHERE USUARIO_ID=USUARIO_IDIN;
END;
/
---BUSCAR
CREATE OR REPLACE FUNCTION BUSCAR_USUARIO(idbuscar IN USUARIO.USUARIO_ID%TYPE)
RETURN Types.ref_cursor 
AS 
        usuario_cursor types.ref_cursor; 
BEGIN 
  OPEN usuario_cursor FOR 
       SELECT USUARIO_ID, ROL_ID, CLAVE FROM USUARIO WHERE USUARIO_ID=idbuscar; 
RETURN usuario_cursor; 
END;
/
---LISTAR
CREATE OR REPLACE FUNCTION LISTAR_USUARIO
RETURN Types.ref_cursor 
AS 
        usuario_cursor types.ref_cursor; 
BEGIN 
  OPEN usuario_cursor FOR 
       SELECT USUARIO_ID, ROL_ID, CLAVE FROM USUARIO; 
RETURN usuario_cursor; 
END;
/
---BORRAR 
create or replace procedure ELIMINAR_USUARIO(USUARIO_IDIN IN USUARIO.USUARIO_ID%TYPE)
as
begin
    delete from USUARIO where USUARIO_ID=USUARIO_IDIN;
end;
/
---Login

CREATE OR REPLACE FUNCTION LOGIN(id IN USUARIO.USUARIO_ID%TYPE, pwd IN USUARIO.CLAVE%TYPE)
RETURN Types.ref_cursor 
AS 
        usuario_cursor types.ref_cursor; 
BEGIN 
  OPEN usuario_cursor FOR 
       SELECT USUARIO_ID, ROL_ID, CLAVE FROM USUARIO WHERE USUARIO_ID=id AND CLAVE=pwd; 
RETURN usuario_cursor; 
END;
/

----------------- TABLA PROFESOR ----------------- 
---INSERTAR
CREATE OR REPLACE PROCEDURE INSERTAR_PROFESOR(CEDULA IN PROFESOR.CEDULA%TYPE, USUARIO_ID IN PROFESOR.USUARIO_ID%TYPE, NOMBRE IN PROFESOR.NOMBRE%TYPE, TELEFONO  IN PROFESOR.TELEFONO%TYPE, EMAIL  IN PROFESOR.EMAIL%TYPE, FECHA_NACIMIENTO IN PROFESOR.FECHA_NACIMIENTO%TYPE)
AS 
BEGIN
INSERT INTO PROFESOR VALUES(CEDULA, USUARIO_ID, NOMBRE,TELEFONO,EMAIL, FECHA_NACIMIENTO);

END;
/
---MODIFICAR
CREATE OR REPLACE PROCEDURE MODIFICAR_PROFESOR (CEDULAIN IN PROFESOR.CEDULA%TYPE, USUARIO_IDIN IN PROFESOR.USUARIO_ID%TYPE, NOMBREIN IN PROFESOR.NOMBRE%TYPE, TELEFONOIN  IN PROFESOR.TELEFONO%TYPE, EMAILIN  IN PROFESOR.EMAIL%TYPE, FECHA_NACIMIENTOIN IN PROFESOR.FECHA_NACIMIENTO%TYPE)
AS
BEGIN
UPDATE PROFESOR SET USUARIO_ID=USUARIO_IDIN, NOMBRE=NOMBREIN, TELEFONO=TELEFONOIN, EMAIL=EMAILIN, FECHA_NACIMIENTO=FECHA_NACIMIENTOIN WHERE CEDULA=CEDULAIN;
END;
/
---BUSCAR
CREATE OR REPLACE FUNCTION BUSCAR_PROFESOR_ID(idbuscar IN PROFESOR.CEDULA%TYPE)
RETURN Types.ref_cursor 
AS 
        profesor_cursor types.ref_cursor; 
BEGIN 
  OPEN profesor_cursor FOR 
       SELECT CEDULA, USUARIO_ID, NOMBRE, TELEFONO, EMAIL, FECHA_NACIMIENTO FROM PROFESOR WHERE CEDULA=idbuscar; 
RETURN profesor_cursor; 
END;
/

CREATE OR REPLACE FUNCTION BUSCAR_PROFESOR_NOMBRE(idbuscar IN PROFESOR.NOMBRE%TYPE)
RETURN Types.ref_cursor 
AS 
        profesor_cursor types.ref_cursor; 
BEGIN 
  OPEN profesor_cursor FOR 
       SELECT CEDULA, USUARIO_ID, NOMBRE, TELEFONO, EMAIL, FECHA_NACIMIENTO FROM PROFESOR WHERE NOMBRE=idbuscar; 
RETURN profesor_cursor; 
END;
/

---LISTAR
CREATE OR REPLACE FUNCTION LISTAR_PROFESOR
RETURN Types.ref_cursor 
AS 
        profesor_cursor types.ref_cursor; 
BEGIN 
  OPEN profesor_cursor FOR 
       SELECT CEDULA, USUARIO_ID, NOMBRE,TELEFONO,EMAIL, FECHA_NACIMIENTO FROM PROFESOR; 
RETURN profesor_cursor; 
END;
/
---BORRAR 
create or replace procedure ELIMINAR_PROFESOR(CEDULAIN IN PROFESOR.cedula%TYPE)
as
begin
    delete from PROFESOR where cedula=CEDULAIN;
end;
/
----------------- TABLA GRUPO ----------------- 
---INSERTAR
CREATE OR REPLACE PROCEDURE INSERTAR_GRUPO(CODIGO IN GRUPO.CODIGO%TYPE,CICLO_ID IN GRUPO.CICLO_ID%TYPE, NUM_GRUPO IN GRUPO.NUM_GRUPO%TYPE,CURSO_ID IN GRUPO.CURSO_ID%TYPE,HORARIO IN GRUPO.HORARIO%TYPE,PROFESOR_ID IN GRUPO.PROFESOR_ID%TYPE)
AS 
BEGIN
INSERT INTO GRUPO VALUES(CODIGO,CICLO_ID,NUM_GRUPO,CURSO_ID,HORARIO,PROFESOR_ID);

END;
/
---MODIFICAR
CREATE OR REPLACE PROCEDURE MODIFICAR_GRUPO (CODIGOIN IN GRUPO.CODIGO%TYPE,CICLO_IDIN IN GRUPO.CICLO_ID%TYPE, NUM_GRUPOIN IN GRUPO.NUM_GRUPO%TYPE,CURSO_IDIN IN GRUPO.CURSO_ID%TYPE,HORARIOIN IN GRUPO.HORARIO%TYPE,PROFESOR_IDIN IN GRUPO.PROFESOR_ID%TYPE)
AS
BEGIN
UPDATE GRUPO SET CODIGO=CODIGOIN,CICLO_ID=CICLO_IDIN,NUM_GRUPO=CICLO_IDIN,CURSO_ID=CURSO_IDIN,HORARIO=HORARIOIN,PROFESOR_ID=PROFESOR_IDIN WHERE CODIGO=CODIGOIN AND CURSO_ID=CURSO_IDIN;
END;
/
---BUSCAR
CREATE OR REPLACE FUNCTION BUSCAR_GRUPO(idbuscar IN GRUPO.CODIGO%TYPE, cursobuscar IN GRUPO.CURSO_ID%TYPE)
RETURN Types.ref_cursor 
AS 
        grupo_cursor types.ref_cursor; 
BEGIN 
  OPEN grupo_cursor FOR 
       SELECT CODIGO,CICLO_ID,NUM_GRUPO,CURSO_ID,HORARIO,PROFESOR_ID FROM GRUPO WHERE codigo=idbuscar AND CURSO_ID=cursobuscar; 
RETURN grupo_cursor; 
END;
/
---LISTAR
CREATE OR REPLACE FUNCTION LISTAR_GRUPO
RETURN Types.ref_cursor 
AS 
        grupo_cursor types.ref_cursor; 
BEGIN 
  OPEN grupo_cursor FOR 
       SELECT CODIGO,CICLO_ID,NUM_GRUPO,CURSO_ID,HORARIO,PROFESOR_ID FROM grupo; 
RETURN grupo_cursor; 
END;
/
---BORRAR 
create or replace procedure ELIMINAR_GRUPO(CODIGOIN IN grupo.CODIGO%TYPE, CURSO_IDIN IN grupo.CURSO_ID%TYPE)
as
begin
    delete from grupo where CODIGO=CODIGOIN AND CURSO_ID=CURSO_IDIN;
end;
/
----------------- TABLA ALUMNO ----------------- 
---INSERTAR
CREATE OR REPLACE PROCEDURE INSERTAR_ALUMNO(CEDULA IN ALUMNO.CEDULA%TYPE, USUARIO_ID IN ALUMNO.USUARIO_ID%TYPE, NOMBRE IN ALUMNO.NOMBRE%TYPE, TELEFONO  IN ALUMNO.TELEFONO%TYPE, EMAIL  IN ALUMNO.EMAIL%TYPE, FECHA_NACIMIENTO IN ALUMNO.FECHA_NACIMIENTO%TYPE, CARRERA_ID IN ALUMNO.CARRERA_ID%TYPE)
AS 
BEGIN
INSERT INTO ALUMNO VALUES(CEDULA, USUARIO_ID, NOMBRE,TELEFONO,EMAIL, FECHA_NACIMIENTO, CARRERA_ID);

END;
/
---MODIFICAR
CREATE OR REPLACE PROCEDURE MODIFICAR_ALUMNO (CEDULAIN IN ALUMNO.CEDULA%TYPE, USUARIO_IDIN IN ALUMNO.USUARIO_ID%TYPE, NOMBREIN IN ALUMNO.NOMBRE%TYPE, TELEFONOIN  IN ALUMNO.TELEFONO%TYPE, EMAILIN  IN ALUMNO.EMAIL%TYPE, FECHA_NACIMIENTOIN IN ALUMNO.FECHA_NACIMIENTO%TYPE, CARRERA_IDIN IN ALUMNO.CARRERA_ID%TYPE)
AS
BEGIN
UPDATE ALUMNO SET USUARIO_ID=USUARIO_IDIN, NOMBRE=NOMBREIN, TELEFONO=TELEFONOIN, EMAIL=EMAILIN, FECHA_NACIMIENTO=FECHA_NACIMIENTOIN, CARRERA_ID=CARRERA_IDIN WHERE CEDULA=CEDULAIN;
END;
/
---BUSCAR
CREATE OR REPLACE FUNCTION BUSCAR_ALUMNO_iD(idbuscar IN ALUMNO.CEDULA%TYPE)
RETURN Types.ref_cursor 
AS 
        alumno_cursor types.ref_cursor; 
BEGIN 
  OPEN alumno_cursor FOR 
       SELECT CEDULA, USUARIO_ID, NOMBRE,TELEFONO,EMAIL, FECHA_NACIMIENTO, carrera_id FROM ALUMNO WHERE CEDULA=idbuscar; 
RETURN alumno_cursor; 
END;
/

CREATE OR REPLACE FUNCTION BUSCAR_ALUMNO_NOMBRE(idbuscar IN ALUMNO.NOMBRE%TYPE)
RETURN Types.ref_cursor 
AS 
        alumno_cursor types.ref_cursor; 
BEGIN 
  OPEN alumno_cursor FOR 
       SELECT CEDULA, USUARIO_ID, NOMBRE,TELEFONO,EMAIL, FECHA_NACIMIENTO, carrera_id FROM ALUMNO WHERE NOMBRE=idbuscar; 
RETURN alumno_cursor; 
END;
/

CREATE OR REPLACE FUNCTION BUSCAR_ALUMNO_CARRERA(idbuscar IN CARRERA.NOMBRE%TYPE)
RETURN Types.ref_cursor 
AS 
        alumno_cursor types.ref_cursor; 
BEGIN 
  OPEN alumno_cursor FOR 
       SELECT CEDULA, USUARIO_ID, NOMBRE,TELEFONO,EMAIL, FECHA_NACIMIENTO, carrera_id FROM ALUMNO WHERE CARRERA_ID IN (SELECT CARRERA_ID FROM CARRERA WHERE NOMBRE=idbuscar);
RETURN alumno_cursor; 
END;
/


---LISTAR
CREATE OR REPLACE FUNCTION LISTAR_ALUMNO
RETURN Types.ref_cursor 
AS 
        alumno_cursor types.ref_cursor; 
BEGIN 
  OPEN alumno_cursor FOR 
       SELECT CEDULA, USUARIO_ID, NOMBRE,TELEFONO,EMAIL, FECHA_NACIMIENTO FROM ALUMNO; 
RETURN alumno_cursor; 
END;
/
---BORRAR 
create or replace procedure ELIMINAR_ALUMNO(CEDULAIN IN ALUMNO.cedula%TYPE)
as
begin
    delete from ALUMNO where cedula=CEDULAIN;
end;
/
----------------- TABLA CICLO ----------------- 
---INSERTAR
CREATE OR REPLACE PROCEDURE INSERTAR_CICLO(CODIGO IN CICLO.CODIGO%TYPE, ANHO IN CICLO.ANHO%TYPE, NUMERO IN CICLO.NUMERO%TYPE, FECHA_INICIO IN CICLO.FECHA_INICIO%TYPE, FECHA_FINAL IN CICLO.FECHA_FINAL%TYPE)
AS 
BEGIN
INSERT INTO CICLO VALUES(CODIGO, ANHO, NUMERO, FECHA_INICIO, FECHA_FINAL);

END;
/
---MODIFICAR
CREATE OR REPLACE PROCEDURE MODIFICAR_CICLO (CODIGOIN IN CICLO.CODIGO%TYPE, ANHOIN IN CICLO.ANHO%TYPE, NUMEROIN IN CICLO.NUMERO%TYPE, FECHA_INICIOIN IN CICLO.FECHA_INICIO%TYPE, FECHA_FINALIN IN CICLO.FECHA_FINAL%TYPE)
AS
BEGIN
UPDATE CICLO SET ANHO=ANHOIN, NUMERO=NUMEROIN, FECHA_INICIO=FECHA_INICIOIN, FECHA_FINAL=FECHA_FINALIN WHERE codigo=CODIGOIN;
END;
/
---BUSCAR
CREATE OR REPLACE FUNCTION BUSCAR_CICLO(idbuscar IN CICLO.CODIGO%TYPE)
RETURN Types.ref_cursor 
AS 
        ciclo_cursor types.ref_cursor; 
BEGIN 
  OPEN ciclo_cursor FOR 
       SELECT CODIGO, ANHO, NUMERO, FECHA_INICIO, FECHA_FINAL FROM CICLO WHERE codigo=idbuscar; 
RETURN ciclo_cursor; 
END;
/
---LISTAR
CREATE OR REPLACE FUNCTION LISTAR_CICLO
RETURN Types.ref_cursor 
AS 
        ciclo_cursor types.ref_cursor; 
BEGIN 
  OPEN ciclo_cursor FOR 
       SELECT CODIGO, ANHO, NUMERO, FECHA_INICIO, FECHA_FINAL FROM CICLO; 
RETURN ciclo_cursor; 
END;
/
---BORRAR 
create or replace procedure ELIMINAR_CICLO(CODIGOIN IN CICLO.CODIGO%TYPE)
as
begin
    delete from CICLO where codigo=CODIGOIN;
end;
/
----------------- TABLA MATRICULA ----------------- 
---INSERTAR
CREATE OR REPLACE PROCEDURE INSERTAR_MATRICULA(ALUMNO_ID IN MATRICULA.ALUMNO_ID%TYPE, GRUPO_NUM IN MATRICULA.GRUPO_NUM%TYPE, CURSO_ID IN MATRICULA.CURSO_ID%TYPE, ESTADO_ID IN MATRICULA.ESTADO_ID%TYPE, NOTA IN MATRICULA.NOTA%TYPE)
AS 
BEGIN
INSERT INTO MATRICULA VALUES(ALUMNO_ID, GRUPO_NUM, CURSO_ID, ESTADO_ID, NOTA);

END;
/
---MODIFICAR
CREATE OR REPLACE PROCEDURE MODIFICAR_MATRICULA (ALUMNO_IDIN IN MATRICULA.ALUMNO_ID%TYPE, GRUPO_NUMIN IN MATRICULA.GRUPO_NUM%TYPE, CURSO_IDIN IN MATRICULA.CURSO_ID%TYPE, ESTADO_IDIN IN MATRICULA.ESTADO_ID%TYPE, NOTAIN IN MATRICULA.NOTA%TYPE)
AS
BEGIN
UPDATE MATRICULA SET ESTADO_ID=ESTADO_IDIN, NOTA=NOTAIN WHERE ALUMNO_ID=ALUMNO_IDIN AND GRUPO_NUM=GRUPO_NUMIN AND CURSO_ID=CURSO_IDIN;
END;
/
---BUSCAR
CREATE OR REPLACE FUNCTION BUSCAR_MATRICULA(ALUMNO_ID IN MATRICULA.ALUMNO_ID%TYPE, GRUPO_NUM IN MATRICULA.GRUPO_NUM%TYPE, CURSO_ID IN MATRICULA.CURSO_ID%TYPE)
RETURN Types.ref_cursor 
AS 
        matricula_cursor types.ref_cursor; 
BEGIN 
  OPEN matricula_cursor FOR 
       SELECT ALUMNO_ID, GRUPO_NUM, CURSO_ID, ESTADO_ID, NOTA FROM MATRICULA WHERE ALUMNO_ID=ALUMNO_ID AND GRUPO_NUM=GRUPO_NUM AND CURSO_ID=CURSO_ID; 
RETURN matricula_cursor; 
END;
/

CREATE OR REPLACE FUNCTION BUSCAR_MATRICULA(ALUMNO_ID IN MATRICULA.ALUMNO_ID%TYPE)
RETURN Types.ref_cursor 
AS 
        matricula_cursor types.ref_cursor; 
BEGIN 
  OPEN matricula_cursor FOR 
       SELECT ALUMNO_ID, GRUPO_NUM, CURSO_ID, ESTADO_ID, NOTA FROM MATRICULA WHERE ALUMNO_ID=ALUMNO_ID;
RETURN matricula_cursor; 
END;
/

---LISTAR
CREATE OR REPLACE FUNCTION LISTAR_MATRICULA
RETURN Types.ref_cursor 
AS 
        matricula_cursor types.ref_cursor; 
BEGIN 
  OPEN matricula_cursor FOR 
       SELECT ALUMNO_ID, GRUPO_NUM, CURSO_ID, ESTADO_ID, NOTA FROM MATRICULA; 
RETURN matricula_cursor; 
END;
/
---BORRAR 
create or replace procedure ELIMINAR_MATRICULA(ALUMNO_ID IN MATRICULA.ALUMNO_ID%TYPE, GRUPO_NUM IN MATRICULA.GRUPO_NUM%TYPE, CURSO_ID IN MATRICULA.CURSO_ID%TYPE)
as
begin
    delete from MATRICULA where ALUMNO_ID =ALUMNO_ID AND GRUPO_NUM=GRUPO_NUM AND CURSO_ID=CURSO_ID;
end;
/
----------------- TABLA ESTADO ----------------- 
---INSERTAR
CREATE OR REPLACE PROCEDURE INSERTAR_ESTADO(ID_ESTADO IN ESTADO.ID_ESTADO%TYPE, DESCRIPCION IN ESTADO.DESCRIPCION%TYPE)
AS 
BEGIN
INSERT INTO ESTADO VALUES(ID_ESTADO, DESCRIPCION);

END;
/
---MODIFICAR
CREATE OR REPLACE PROCEDURE MODIFICAR_ESTADO (ID_ESTADOIN IN ESTADO.ID_ESTADO%TYPE, DESCRIPCIONIN IN ESTADO.DESCRIPCION%TYPE)
AS
BEGIN
UPDATE ESTADO SET DESCRIPCION=DESCRIPCIONIN WHERE ID_ESTADO=ID_ESTADOIN;
END;
/
---BUSCAR
CREATE OR REPLACE FUNCTION BUSCAR_ESTADO(idbuscar IN ESTADO.ID_ESTADO%TYPE)
RETURN Types.ref_cursor 
AS 
        estado_cursor types.ref_cursor; 
BEGIN 
  OPEN estado_cursor FOR 
       SELECT ID_ESTADO, DESCRIPCION FROM ESTADO WHERE ID_ESTADO=idbuscar; 
RETURN estado_cursor; 
END;
/
---LISTAR
CREATE OR REPLACE FUNCTION LISTAR_ESTADO
RETURN Types.ref_cursor 
AS 
        estado_cursor types.ref_cursor; 
BEGIN 
  OPEN estado_cursor FOR 
       SELECT ID_ESTADO, DESCRIPCION FROM ESTADO; 
RETURN estado_cursor; 
END;
/
---BORRAR 
create or replace procedure ELIMINAR_ESTADO(ID_ESTADOIN IN ESTADO.ID_ESTADO%TYPE)
as
begin
    delete from ESTADO where ID_ESTADO=ID_ESTADOIN;
end;
/

----------------- PRUEBAS ----------------- 
---CARRERA
execute INSERTAR_CARRERA(1000, 'INGENIERIA EN SISTEMAS', 'BACHILLERATO');

---ROLES
execute INSERTAR_ROL(1, 'Administrador');
execute INSERTAR_ROL(2, 'Matriculador');
execute INSERTAR_ROL(3, 'Profesor');
execute INSERTAR_ROL(4, 'Alumno');

---USUARIOS
execute INSERTAR_USUARIO(12345, 1, 'root123');
execute INSERTAR_USUARIO(402381288, 3, 'test509');
execute INSERTAR_USUARIO(109381291, 4, 'patito20');

---ESTADOS
execute INSERTAR_ESTADO(101, 'Aprobado');
execute INSERTAR_ESTADO(102, 'En Progreso');
execute INSERTAR_ESTADO(103, 'Reprobado');

    
---PROFESOR
execute INSERTAR_PROFESOR (402381288, '402381288', 'Maria Solis','90909090','profe01@academia.com', '1970-02-01 00:00:01');

select BUSCAR_PROFESOR_ID(402381288) from dual;
select BUSCAR_PROFESOR_NOMBRE('Maria Solis') from dual;


---ALUMNOS
execute INSERTAR_ALUMNO (109381291, '109381291', 'Esteban Fonseca', '87902281', 'alumno01@academia.com', '2000-06-09 00:00:01');


---CICLO
execute INSERTAR_CICLO (200, '2022', 1, '2022-03-08', '2022-06-22');

---CURSO
execute INSERTAR_CURSO (300, 'FUNDAMENTOS DE INFORMATICA', 4, 4,1000,200);

select BUSCAR_CURSO(300) from dual;
select BUSCAR_CURSO('FUNDAMENTOS DE INFORMATICA') from dual;
select BUSCAR_CURSO_CARRERA('INGENIERIA EN SISTEMAS') from dual;


---GRUPO
execute INSERTAR_GRUPO(400,200,1,300,'18:00:00',402381288);

---MATRICULA
execute INSERTAR_MATRICULA (109381291, 400, 300, 102, 0);


