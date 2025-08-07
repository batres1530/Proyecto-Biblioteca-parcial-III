CREATE DATABASE BDBiblioteca;
USE bdbiblioteca;

show databases;

CREATE TABLE Categorias(
    Categoria_ID INT AUTO_INCREMENT PRIMARY KEY,
    Descripcion VARCHAR(50) NOT NULL
);

CREATE TABLE Ejemplares(
    Ejemplar_ID INT AUTO_INCREMENT PRIMARY KEY,
    Libros_ID INT NOT NULL,
    Estado VARCHAR(50) NOT NULL
);

CREATE TABLE Autores(
    Autor_ID INT AUTO_INCREMENT PRIMARY KEY,
    Nombre VARCHAR(100) NOT NULL,
    Apellido VARCHAR(100) NOT NULL
);

CREATE TABLE Editorial(
    Editorial_ID INT AUTO_INCREMENT PRIMARY KEY,
    Nombre VARCHAR(100) NOT NULL,
    Direccion VARCHAR(100) NOT NULL,
    Telefono VARCHAR(100) NOT NULL
);

CREATE TABLE Libros(
    Libro_ID INT AUTO_INCREMENT PRIMARY KEY,
    Titulo_Libro VARCHAR(100) NOT NULL,
    Fecha_Publicación DATE NOT NULL,
    Editorial_ID INT NOT NULL,
    Categoria_ID INT NOT NULL
);

CREATE TABLE Libros_Autores (
    LibroAutor_ID INT AUTO_INCREMENT PRIMARY KEY,
    Libro_ID INT NOT NULL,
    Autor_ID INT NOT NULL
);

CREATE TABLE Cargos(
    Cargos_ID INT AUTO_INCREMENT PRIMARY KEY,
    Descripcion VARCHAR(100) NOT NULL
);

CREATE TABLE Empleados(
    Empleado_ID INT AUTO_INCREMENT PRIMARY KEY,
    Nombre VARCHAR(100) NOT NULL,
    Apellido VARCHAR(100) NOT NULL,
    Cargos_ID INT NOT NULL,
    Telefono VARCHAR(15)
);

CREATE TABLE Prestamos(
    Prestamo_ID INT AUTO_INCREMENT PRIMARY KEY,
    Usuario_ID INT NOT NULL,
    Libro_ID INT NOT NULL,
    Empleado_ID INT NOT NULL,
    Fecha_Prestamo DATE NOT NULL,
    Fecha_Limite DATE NOT NULL
);

CREATE TABLE Usuarios(
    Usuario_ID INT AUTO_INCREMENT PRIMARY KEY,
    Nombre VARCHAR(100) NOT NULL,
    Correo VARCHAR(100) NOT NULL,
    TipoUsuario_ID INT NOT NULL
);

CREATE TABLE TipoUsuario(
    TipoUsuario_ID INT AUTO_INCREMENT PRIMARY KEY,
    Descripcion VARCHAR(50) NOT NULL
);

CREATE TABLE Devoluciones(
    Devoluciones_ID INT AUTO_INCREMENT PRIMARY KEY,
    Prestamo_ID INT NOT NULL,
    Fecha_devolucion DATE NOT NULL,
    Multa FLOAT
);

-- Relaciones Tabla Libros 
ALTER TABLE Libros ADD CONSTRAINT fk_libros FOREIGN KEY (Categoria_ID) REFERENCES Categorias(Categoria_ID);
ALTER TABLE Libros ADD CONSTRAINT fk_libros2 FOREIGN KEY (Editorial_ID) REFERENCES Editorial(Editorial_ID);

-- Relaciones Tabla LibrosAutor
ALTER TABLE Libros_Autores ADD CONSTRAINT fk_librosautor FOREIGN KEY (Libro_ID) REFERENCES Libros(Libro_ID);
ALTER TABLE Libros_Autores ADD CONSTRAINT fk_librosautor2 FOREIGN KEY (Autor_ID) REFERENCES Autores(Autor_ID);

-- Relaciones Tabla Ejemplares 
ALTER TABLE Ejemplares ADD CONSTRAINT fk_ejemplares FOREIGN KEY (Libros_ID) REFERENCES Libros(Libro_ID);

-- Relaciones Tabla Empleados
ALTER TABLE Empleados ADD CONSTRAINT fk_empleados FOREIGN KEY (Cargos_ID) REFERENCES Cargos(Cargos_ID);

-- Relaciones Tabla Prestamos 
ALTER TABLE Prestamos ADD CONSTRAINT fk_prestamos FOREIGN KEY (Usuario_ID) REFERENCES Usuarios(Usuario_ID);
ALTER TABLE Prestamos ADD CONSTRAINT fk_prestamos2 FOREIGN KEY (Libro_ID) REFERENCES Libros(Libro_ID);
ALTER TABLE Prestamos ADD CONSTRAINT fk_prestamos3 FOREIGN KEY (Empleado_ID) REFERENCES Empleados(Empleado_ID);

-- Relaciones Tabla Usuarios 
ALTER TABLE Usuarios ADD CONSTRAINT fk_usuarios FOREIGN KEY (TipoUsuario_ID) REFERENCES TipoUsuario(TipoUsuario_ID);

-- Relaciones  Devoluciones
ALTER TABLE Devoluciones ADD CONSTRAINT fk_devoluciones FOREIGN KEY (Prestamo_ID) REFERENCES Prestamos(Prestamo_ID);


/*INSERT EN LA BD*/
/*Categorias*/
-- Categorias
INSERT INTO Categorias (Descripcion) VALUES 
('Ficción'), 
('Ciencia'), 
('Historia'), 
('Tecnología'), 
('Infantil');

-- Tipo Usuario
INSERT INTO TipoUsuario (Descripcion) VALUES 
('Estudiante'), 
('Docente'), 
('Administrativo');

-- Autores
INSERT INTO Autores (Nombre, Apellido) VALUES 
('Gabriel', 'García Márquez'),
('Isaac', 'Asimov'),
('Julio', 'Verne'),
('Jane', 'Austen'),
('Stephen', 'King'),
('George', 'Orwell'),
('Mary', 'Shelley'),
('Mark', 'Twain'),
('Miguel', 'de Cervantes'),
('Harper', 'Lee');

-- Editorial
INSERT INTO Editorial (Nombre, Direccion, Telefono) VALUES 
('Planeta', 'Calle 1, Ciudad A', '1111-1111'),
('Santillana', 'Calle 2, Ciudad B', '2222-2222'),
('Alfaguara', 'Calle 3, Ciudad C', '3333-3333'),
('Anaya', 'Calle 4, Ciudad D', '4444-4444'),
('Oxford Press', 'Calle 5, Ciudad E', '5555-5555'),
('McGraw-Hill', 'Calle 6, Ciudad F', '6666-6666'),
('Pearson', 'Calle 7, Ciudad G', '7777-7777'),
('HarperCollins', 'Calle 8, Ciudad H', '8888-8888'),
('Penguin Random', 'Calle 9, Ciudad I', '9999-9999'),
('Grupo SM', 'Calle 10, Ciudad J', '1010-1010');

-- Libros
INSERT INTO Libros (Titulo_Libro, Fecha_Publicación, Editorial_ID, Categoria_ID) VALUES 
('Cien años de soledad', '1967-05-30', 1, 1),
('1984', '1949-06-08', 6, 1),
('Frankenstein', '1818-01-01', 7, 2),
('Orgullo y prejuicio', '1813-01-28', 3, 1),
('La guerra de los mundos', '1898-01-01', 4, 2),
('Don Quijote de la Mancha', '1605-01-16', 9, 3),
('Viaje al centro de la tierra', '1864-11-25', 2, 1),
('El código Da Vinci', '2003-03-18', 8, 4),
('Harry Potter y la piedra filosofal', '1997-06-26', 5, 5),
('Matar a un ruiseñor', '1960-07-11', 10, 1);

-- Libros_Autores
INSERT INTO Libros_Autores (Libro_ID, Autor_ID) VALUES 
(1, 1), (2, 6), (3, 7), (4, 4), (5, 2),
(6, 9), (7, 3), (8, 5), (9, 10), (10, 10);

-- Cargos
INSERT INTO Cargos (Descripcion) VALUES 
('Bibliotecario'), ('Asistente'), ('Supervisor'), ('Digitador'), ('Administrador'),
('Recepcionista'), ('Mantenimiento'), ('Técnico de sistemas'), 
('Encargado de colecciones'), ('Ayudante de sala');


-- Empleados
INSERT INTO Empleados (Nombre, Apellido, Cargos_ID, Telefono) VALUES 
('Laura', 'Guzmán', 1, '8888-1111'),
('Pedro', 'Sánchez', 2, '8888-2222'),
('Ana', 'López', 3, '8888-3333'),
('Carlos', 'Reyes', 4, '8888-4444'),
('María', 'Torres', 5, '8888-5555'),
('Luis', 'Castro', 6, '8888-6666'),
('Jorge', 'Ortega', 7, '8888-7777'),
('Carmen', 'Valle', 8, '8888-8888'),
('Esteban', 'Ruiz', 9, '8888-9999'),
('Claudia', 'Martínez', 10, '8888-0000');

-- Usuarios
INSERT INTO Usuarios (Nombre, Correo, TipoUsuario_ID) VALUES 
('Erick Moreno', 'erick@mail.com', 1),
('Sofía Cruz', 'sofia@mail.com', 2),
('Andrés Díaz', 'andres@mail.com', 1),
('Lucía García', 'lucia@mail.com', 3),
('Daniel Pérez', 'daniel@mail.com', 1),
('Valeria Ruiz', 'valeria@mail.com', 2),
('Marco Rivas', 'marco@mail.com', 1),
('Isabela Torres', 'isabela@mail.com', 3),
('René Soto', 'rene@mail.com', 2),
('Camila Fuentes', 'camila@mail.com', 1);

-- Ejemplares
INSERT INTO Ejemplares (Libros_ID, Estado) VALUES 
(1, 'Disponible'), (2, 'Prestado'), (3, 'Disponible'), (4, 'Dañado'),
(5, 'Disponible'), (6, 'Disponible'), (7, 'En reparación'), (8, 'Disponible'),
(9, 'Prestado'), (10, 'Disponible');


-- Prestamos
INSERT INTO Prestamos (Usuario_ID, Libro_ID, Empleado_ID, Fecha_Prestamo, Fecha_Limite) VALUES 
(1, 1, 1, '2025-06-01', '2025-06-15'),
(2, 2, 2, '2025-06-02', '2025-06-16'),
(3, 3, 3, '2025-06-03', '2025-06-17'),
(4, 4, 4, '2025-06-04', '2025-06-18'),
(5, 5, 5, '2025-06-05', '2025-06-19'),
(6, 6, 6, '2025-06-06', '2025-06-20'),
(7, 7, 7, '2025-06-07', '2025-06-21'),
(8, 8, 8, '2025-06-08', '2025-06-22'),
(9, 9, 9, '2025-06-09', '2025-06-23'),
(10, 10, 10, '2025-06-10', '2025-06-24');


-- Devoluciones
INSERT INTO Devoluciones (Prestamo_ID, Fecha_devolucion, Multa) VALUES 
(1, '2025-06-14', 0.00),
(2, '2025-06-20', 10.00),
(3, '2025-06-17', 0.00),
(4, '2025-06-25', 5.00),
(5, '2025-06-19', 0.00),
(6, '2025-06-21', 0.00),
(7, '2025-06-30', 15.00),
(8, '2025-06-22', 0.00),
(9, '2025-06-24', 0.00),
(10, '2025-06-28', 8.00);


-- Índices en columnas de buscar
CREATE INDEX idx_autores_nombre ON autores(Nombre);
CREATE INDEX idx_libros_titulo ON libros(Titulo_Libro);
CREATE INDEX idx_usuarios_correo ON usuarios(Correo);


-- Cpnsultas avanzadas

-- 1) Libros que ha prestado cada usuario
SELECT u.Nombre AS Usuario, l.Titulo_Libro AS Libro, p.Fecha_Prestamo
FROM Prestamos p
JOIN Usuarios u ON p.Usuario_ID = u.Usuario_ID
JOIN Libros l ON p.Libro_ID = l.Libro_ID
ORDER BY u.Nombre;

-- 2) Libros devueltos con retraso
SELECT u.Nombre AS Usuario, l.Titulo_Libro AS Libro, p.Fecha_Limite, d.Fecha_devolucion
FROM Prestamos p
JOIN Devoluciones d ON d.Prestamo_ID = p.Prestamo_ID
JOIN Usuarios u ON p.Usuario_ID = u.Usuario_ID
JOIN Libros l ON p.Libro_ID = l.Libro_ID
WHERE d.Fecha_devolucion > p.Fecha_Limite;

-- 3) Libros con mayor número de préstamos
SELECT l.Titulo_Libro, COUNT(*) AS Veces_Prestado
FROM Prestamos p
JOIN Libros l ON p.Libro_ID = l.Libro_ID
GROUP BY l.Titulo_Libro
ORDER BY Veces_Prestado DESC
LIMIT 5;

-- 4) Autores que tienen más libros registrados
SELECT a.Nombre, a.Apellido, COUNT(la.Libro_ID) AS Total_Publicaciones
FROM Libros_Autores la
JOIN Autores a ON la.Autor_ID = a.Autor_ID
GROUP BY a.Autor_ID
ORDER BY Total_Publicaciones DESC;

-- 5) Libros prestados organizados por categoría
SELECT c.Descripcion AS Categoria, COUNT(*) AS Total_Prestamos
FROM Prestamos p
JOIN Libros l ON p.Libro_ID = l.Libro_ID
JOIN Categorias c ON l.Categoria_ID = c.Categoria_ID
GROUP BY c.Categoria_ID
ORDER BY Total_Prestamos DESC;

-- 6) Visualizar el indice de autores
EXPLAIN SELECT * FROM autores WHERE Nombre = 'Gabriel';

-- 7) Visualizar el indice de libros
EXPLAIN SELECT * FROM libros WHERE Titulo_Libro = 'Cien años de soledad';

-- 8) Prestamos por mes
SELECT MONTH(Fecha_Prestamo) AS Mes, COUNT(*) AS Total_Prestamos
FROM Prestamos
WHERE YEAR(Fecha_Prestamo) = 2025
GROUP BY MONTH(Fecha_Prestamo)
ORDER BY Mes;

-- 9) Listado de ejemplares con su estado y título de libro
SELECT e.Ejemplar_ID, l.Titulo_Libro,e.Estado
FROM Ejemplares e
JOIN Libros l ON e.Libros_ID = l.Libro_ID;

-- 10) Cantidad de ejemplares por estado
SELECT Estado, COUNT(*) AS Cantidad
FROM Ejemplares
GROUP BY Estado;

-- 11) Total de préstamos realizados por cada empleado
SELECT concat(e.Nombre,' ', e.Apellido) AS NombreEmpleado,COUNT(p.Prestamo_ID) AS Prestamos_Atendidos
FROM Prestamos p
JOIN Empleados e ON p.Empleado_ID = e.Empleado_ID
GROUP BY e.Empleado_ID;

-- 12) Libros con sus autores 
SELECT l.Titulo_Libro, concat(a.Nombre,' ', a.Apellido) AS "Nombre Autor"
FROM Libros l
JOIN Libros_Autores la ON l.Libro_ID = la.Libro_ID
JOIN Autores a ON la.Autor_ID = a.Autor_ID
ORDER BY l.Titulo_Libro;
DESCRIBE prestamos;


CREATE TABLE usuario (
    id INT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(50) UNIQUE,
    password_hash VARCHAR(256),
    rol ENUM('admin', 'recepcionista') DEFAULT 'recepcionista'
);

-- Insertar usuarios encriptando 
INSERT INTO usuario (username, password_hash, rol) 
VALUES 
('jorge', SHA2('usuario123', 256), 'recepcionista'),
('maria', SHA2('admin456', 256), 'admin'),
('pedro', SHA2('uth2025', 256), 'recepcionista'),
('sofia', SHA2('biblioteca', 256), 'recepcionista'),
('luis', SHA2('clave789', 256), 'admin');

select * from usuario;