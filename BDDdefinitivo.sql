
CREATE TABLE usuario (
  idUsuario INT NOT NULL AUTO_INCREMENT,
  Nombre_Usuario VARCHAR(20) NULL DEFAULT NULL,
  Email_Usuario VARCHAR(30) NULL DEFAULT NULL,
  Password_Usuario VARCHAR(15) NULL DEFAULT NULL,
  PRIMARY KEY (idUsuario))

CREATE TABLE administrador (
  idAdmin INT NOT NULL AUTO_INCREMENT,
  Usuario_idUsuario INT NOT NULL,
  PRIMARY KEY (idAdmin),
  INDEX fk_Admin_Usuario1_idx (Usuario_idUsuario ASC) VISIBLE,
  CONSTRAINT fk_Admin_Usuario1
    FOREIGN KEY (Usuario_idUsuario)
    REFERENCES supermark1.usuario (idUsuario))

CREATE TABLE categoria (
  idCategoria INT NOT NULL AUTO_INCREMENT,
  Nombre_Categoria VARCHAR(20) NULL DEFAULT NULL,
  PRIMARY KEY (idCategoria))

CREATE TABLE IF NOT EXISTS supermark1.cliente (
  idCliente INT NOT NULL AUTO_INCREMENT,
  Apellido_Cliente VARCHAR(20) NULL DEFAULT NULL,
  Domicilio_Cliente VARCHAR(50) NULL DEFAULT NULL,
  Tarjeta_Cliente TINYINT NOT NULL,
  Premio_Cliente TINYINT NULL DEFAULT NULL,
  NumCompras_Cliente INT NULL DEFAULT NULL,
  Usuario_idUsuario INT NOT NULL,
  PRIMARY KEY (idCliente),
  INDEX fk_Cliente_Usuario1_idx (Usuario_idUsuario ASC) VISIBLE,
  CONSTRAINT fk_Cliente_Usuario1
    FOREIGN KEY (Usuario_idUsuario)
    REFERENCES supermark1.usuario (idUsuario))
E
CREATE TABLE compra (
  idCompra INT NOT NULL AUTO_INCREMENT,
  Total_Compra DOUBLE NOT NULL,
  Cliente_idCliente INT NOT NULL,
  FormaPago_Compra VARCHAR(30) NULL DEFAULT NULL,
  PRIMARY KEY (idCompra),
  INDEX fk_Compra_Cliente1_idx (Cliente_idCliente ASC) VISIBLE,
  CONSTRAINT fk_Compra_Cliente1
    FOREIGN KEY (Cliente_idCliente)
    REFERENCES supermark1.cliente (idCliente))

CREATE TABLE producto (
  idProducto INT NOT NULL AUTO_INCREMENT,
  Nombre_Producto VARCHAR(50) NOT NULL,
  Precio_Producto DOUBLE NOT NULL,
  Stock_Producto INT NOT NULL,
  categoria_idCategoria INT NOT NULL,
  PRIMARY KEY (idProducto),
  INDEX fk_producto_categoria1_idx (categoria_idCategoria ASC) VISIBLE,
  CONSTRAINT fk_producto_categoria1
    FOREIGN KEY (categoria_idCategoria)
    REFERENCES supermark1.categoria (idCategoria))

CREATE TABLE listap (
  idListaP INT NOT NULL AUTO_INCREMENT,
  cantidad INT NULL DEFAULT NULL,
  Compra_idCompra INT NOT NULL,
  Producto_idProducto INT NOT NULL,
  PRIMARY KEY (idListaP),
  INDEX fk_ListaProd_Compra1_idx (Compra_idCompra ASC) VISIBLE,
  INDEX fk_ListaProd_Producto1_idx (Producto_idProducto ASC) VISIBLE,
  CONSTRAINT fk_ListaProd_Compra1
    FOREIGN KEY (Compra_idCompra)
    REFERENCES supermark1.compra (idCompra),
  CONSTRAINT fk_ListaProd_Producto1
    FOREIGN KEY (Producto_idProducto)
    REFERENCES supermark1.producto (idProducto))

insert into producto (Nombre_Producto,Precio_Producto,Stock_Producto,categoria_idCategoria) values 
('Arroz Integral Gallo 900gr', 80, 2000,5),
('Yogurt Vainilla Cosalta 50gr', 100, 2700,1),
('Azucar Blanca Ledesma 1kg', 250, 800,5),
('Galleta Surtido Diversion 900gr', 150, 1500,5),
('Sprite 2,5L gaseosa',230,300,3),
('Pasta dental Colgate 50gr', 120, 2000,2);


INSERT INTO categoria (Nombre_Categoria) values
('Lacteos'),
('Limpieza'),
('Bebidas'),
('Frutas y Verduras'),
('Alimentos');

INSERT INTO usuario (Nombre_usuario, email_usuario, password_usuario) values ('Andrea', 'andrea_emilce@live.com', '4399764');
INSERT INTO producto(Nombre_Producto, Precio_Producto, Stock_Producto, categoria_idCategoria) VALUES ('lentejas',30,200, 5);
