DROP DATABASE IF EXISTS supermark;
CREATE DATABASE supermark CHARACTER SET utf8mb4;
USE supermark;


CREATE TABLE cliente(
	id INT unsigned AUTO_INCREMENT PRIMARY KEY,
	nombre VARCHAR(20) NOT NULL,
    apellido VARCHAR(20) NOT NULL,
    cuil VARCHAR(20) NOT NULL,
    domicilio VARCHAR(70) NOT NULL,
    provincia VARCHAR(20) NOT NULL,
    codigo_postal VARCHAR(10) NOT NULL,
    telefono VARCHAR(10),
    nombre_usuario varchar(30) not null,
    pass varchar(20) not null
);

CREATE TABLE categoria (
    id INT UNSIGNED auto_increment PRIMARY KEY,
    nombre VARCHAR(20)
);
insert into categoria values(1,'Bebidas');
insert into categoria values(2,'Panes');
insert into categoria values(3,'Pastas');
insert into categoria values(4,'Carnes');
insert into categoria values(5,'Lavandería');
insert into categoria values(6,'CuidadoCapilar');
insert into categoria values(7,'CuidadoOral');
insert into categoria values(8,'Celulares');
insert into categoria values(9,'Computadoras');


CREATE TABLE producto (
    id INT UNSIGNED auto_increment PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL,
    precio DOUBLE UNSIGNED NOT NULL,
    id_categoria int unsigned not null,
    stock INT UNSIGNED NOT NULL,
    FOREIGN KEY (id_categoria) REFERENCES categoria(id)
);
insert into producto values(1,'Gaseosa Coca-Cola 1,5l', '300', '1', '50');
insert into producto values(2,'Jabón ropa Skip', '500', '5', '30');
insert into producto values(3,'Shampoo Head & Shoulders', '430', '6', '15');
insert into producto values(4,'Milanesa peceto', '1600', '4', '5');
insert into producto values(5,'Matambre', '1450', '4', '10');
insert into producto values(6,'Alfajor Tofi', '80', '6', '100');
insert into producto values(7,'Pasata dental Colgate', '250', '7', '30');
insert into producto values(8,'Celular Motorola G31', '42000', '8', '3');
insert into producto values(9,'Notebook Lenovo', '80000', '9', '5');
insert into producto values(10,'Baguette de salvado', '100', '2', '70');
insert into producto values(11,'Fideos Dedalito Matarazzo X500 Gr', '99', '3', '40');

CREATE TABLE venta(
	id int unsigned primary key,
    fecha date not null,
    total double not null
);

CREATE TABLE detalle_venta(
	catidad int unsigned not null,
    precio_unitrio double unsigned not null,
    id_producto int unsigned not null,
    id_venta  int unsigned not null,
    foreign key (id_producto) references producto(id),
    foreign key (id_venta) references venta(id)
);