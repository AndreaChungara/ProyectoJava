use supermark1;
SELECT * FROM producto;
DELETE FROM producto WHERE FechaV_Producto='0000-00-00';
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

1.Lacteos\n 2.Limpieza\n 3.Bebidas\n 4.Frutas y Verduras\n 5.Alimentos\n 


alter table compra drop column Fecha_Compra;
insert into usuario (Nombre_usuario, email_usuario, password_usuario) values ('Andrea', 'andrea_emilce@live.com', '4399764');
insert into administrador(Usuario_idUsuario) values(1);
SELECT u.email_usuario, u.password_usuario, u.Nombre_usuario FROM usuario as u inner join administrador as a on u.idUsuario=a.Usuario_idUsuario where email_usuario="andrea_emilce@live.com";
SELECT * FROM usuario as u inner join cliente as c on u.idUsuario=c.Usuario_idUsuario;
SELECT Nombre_Usuario, Email_Usuario, Apellido_Cliente, Domicilio_Cliente, Tarjeta_Cliente, Premio_Cliente, NumCompras_Cliente FROM usuario as u inner join cliente as c on u.idUsuario=c.Usuario_idUsuario where Email_Usuario='felipe@outlook.com';

insert into producto(nombre_producto, stock_producto, precio_producto, fechav_producto) values ('Yogurt Vainilla Cosalta 50gr', 2500, 100, '2022-12-03');
select idProducto, Nombre_Producto from producto where Nombre_Producto like '%Yogurt%';
select idProducto, nombre_Producto from producto where idProducto;
UPDATE producto SET Nombre_Producto='Papas Fritas' WHERE idProducto=5;
DROP table producto;

SELECT Precio_Producto FROM producto WHERE idProducto=1;
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
    REFERENCES `supermark1`.`categoria` (idCategoria)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
    
CREATE TABLE listaP (
  idListaP INT NOT NULL AUTO_INCREMENT,
  Compra_idCompra INT NOT NULL,
  Producto_idProducto INT NOT NULL,
  PRIMARY KEY (idListaP),
  INDEX fk_ListaProd_Compra1_idx (Compra_idCompra ASC) VISIBLE,
  INDEX fk_ListaProd_Producto1_idx (Producto_idProducto ASC) VISIBLE,
  CONSTRAINT fk_ListaProd_Producto1
    FOREIGN KEY (Producto_idProducto)
    REFERENCES supermark1.producto (idProducto),
  CONSTRAINT fk_ListaProd_Compra1
    FOREIGN KEY (Compra_idCompra)
    REFERENCES supermark1.compra (idCompra));
insert into usuario (Nombre_usuario, email_usuario, password_usuario) values ('Emi', 'emilce@live.co,', '43329403');

INSERT INTO cliente (Apellido_Cliente, Domicilio_Cliente, Tarjeta_Cliente, Premio_Cliente, NumCompras_Cliente, Usuario_idUsuario)VALUES ('Chungara','Juramento',TRUE,TRUE,0,2);
delete from usuario where idCliente=1;
CREATE TABLE Categoria(
   idCategoria INT NOT NULL AUTO_INCREMENT,
   Nombre_Categoria VARCHAR(20),
   PRIMARY KEY (idCategoria)
);
describe producto;
ALTER table producto add constraint fk_cat_pro foreign key(idCategoria) references categoria(idCategoria);
ALTER TABLE compra add column FormaPago_Compra VARCHAR(30);
UPDATE producto SET idCategoria = '1' WHERE idProducto = '26';