--Crear una secuencia para utilizarla como incremento del identificador
CREATE SEQUENCE "DB_SEMILLERO"."SEQ_ROL" MINVALUE 1 START WITH 1  INCREMENT BY 1 NOCACHE;


--Crear la tabla ROL con las operaciones y restricciones requeridas
CREATE TABLE "DB_SEMILLERO"."ROL" 
(	"SCID" NUMBER(3,0) NOT NULL ENABLE,
"SCNOMBRE" VARCHAR2(50 BYTE) NOT NULL  ENABLE, 
"SCESTADO" VARCHAR2(50 BYTE), 
CONSTRAINT "ROL_PK" PRIMARY KEY ("SCID"),
CONSTRAINT "ROL_C_1" CHECK (SCESTADO IN ('ACTIVO', 'INACTIVO')),
CONSTRAINT "ROL_C_2" UNIQUE (SCNOMBRE)
);

--Como insertar datos:
insert into ROL (SCID,SCNOMBRE,SCESTADO)
values (DB_SEMILLERO.SEQ_ROL.nextval,'Heroe','ACTIVO');

insert into ROL (SCID,SCNOMBRE,SCESTADO)
values (DB_SEMILLERO.SEQ_ROL.nextval,'Villano','ACTIVO');