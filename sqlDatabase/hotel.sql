CREATE DATABASE alura_hotel;
USE Hotel_Alura;

CREATE TABLE reservas (
id INT UNSIGNED AUTO_INCREMENT, 
fecha_entrada DATE NOT NULL,
fecha_salida DATE NOT NULL,
valor double NOT NULL,
forma_de_pago VARCHAR(35) NOT NULL,
PRIMARY KEY (id)
)ENGINE=InnoDB;

CREATE TABLE huespedes (
id INT UNSIGNED AUTO_INCREMENT, 
id_reserva INT UNSIGNED NOT NULL,
nombre VARCHAR(50) NOT NULL,
apellido VARCHAR(50) NOT NULL,
fecha_de_nacimiento DATE NOT NULL,
nacionalidad VARCHAR(50) NOT NULL,
telefono VARCHAR(16) NOT NULL,
PRIMARY KEY (id),
FOREIGN KEY (id_reserva) REFERENCES reservas(id) 
)ENGINE=InnoDB;

CREATE TABLE usuarios (
id INT UNSIGNED AUTO_INCREMENT, 
usuario VARCHAR(25) NOT NULL,
contrasena VARCHAR(25) NOT NULL,
PRIMARY KEY (id)
)ENGINE=InnoDB;

INSERT INTO usuarios(usuario, contrasena) VALUES ('admin', '01234');
INSERT INTO usuarios(usuario, contrasena) VALUES ('recepcionista', '56789');

