# CORRECCIÓN DE LA PRUEBA 2 DE POO
----
# Wilson Guayanay
----
Script de la base de datos.


CREATE DATABASE FARMACIA

USE FARMACIA

CREATE TABLE PRODUCTOS(
 Id_Clie numeric(10) not null, 
 Nom_Clie varchar(20)not null, 
 Ciu_Clie varchar(20) not null,
 Pro_Clie varchar(25) not null,
 Cant_Clie varchar(4)not null, 
 Dir_Clie varchar(25)not null 
 )

CREATE TABLE CIUDAD( 
Nom_Ciu varchar(20) not null 
)

Insert Into CIUDAD VALUES('Quito') 
Insert Into CIUDAD VALUES('Guayaquil') 
Insert Into CIUDAD VALUES('Cuenca')

select * from ciudad

CREATE TABLE NOMBREPRODUCTOS(
 Prod_nom varchar(20) not null 
 )
 
Insert Into NOMBREPRODUCTOS VALUES('Pañales') 
Insert Into NOMBREPRODUCTOS VALUES('Pastillas') 
Insert Into NOMBREPRODUCTOS VALUES('Jarabes') 
Insert Into NOMBREPRODUCTOS VALUES('Preservativos') 


select * from nombreproductos


select * from farmacia.productos

----

Enlace del video:
https://youtu.be/tR0J4BQm8W4

----

Pantalla principal

![image](https://user-images.githubusercontent.com/117754219/221083738-4fccbe09-20f5-40e1-9a43-f80cea626d9c.png)




