import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
public class Administrador {

	public void cargarProducto()throws SQLException  {
		String nombreProducto;
		int stockProducto;
		float precioProducto;
		int categoriaProducto;
		//short categoriaProducto;
		
		Scanner entrada=new Scanner(System.in);
		
		System.out.println("Ingresar datos del producto\n Nombre:");
		nombreProducto=entrada.nextLine();
		System.out.println("Ingresar datos del producto\n Precio:");
		precioProducto=entrada.nextFloat();
		System.out.println("Ingresar datos del producto\n Stock:");
		stockProducto=entrada.nextInt();
		
		System.out.println("Elige la opcion de categoria:\n 1.Lacteos\n 2.Limpieza\n 3.Bebidas\n 4.Frutas y Verduras\n 5.Alimentos");
		 categoriaProducto= entrada.nextInt();
		
		CONEXION conexion=new CONEXION();
		String sql="INSERT INTO producto(Nombre_Producto, Precio_Producto, Stock_Producto, categoria_idCategoria) VALUES ('"+nombreProducto+"', "+precioProducto+","+stockProducto+", "+categoriaProducto+");";
		conexion.realizaConsulta(sql);
		System.out.println("Producto cargado");
	}
	
	public void modificarProducto() throws SQLException {
		String nombreBusqueda;
		String nombreProducto=null;
		int idProducto;
		
		Scanner entrada=new Scanner(System.in);

		System.out.println("¿Que producto deseas modificar? Ingresa el nombre y te aparecerá una lista de productos en la que podras identificar el ID");
		nombreBusqueda=entrada.next();
		
		CONEXION conexion=new CONEXION();
		
		String sql="SELECT idProducto, Nombre_Producto FROM producto WHERE nombre_Producto LIKE '%"+nombreBusqueda+"%'";
		ResultSet rs= conexion.devuelveConsulta(sql);//guardo el resultado de la consulta en rs
		while(rs.next()) {//mostramos por consola los productos coincidentes con el nombre
			idProducto=rs.getInt("idProducto");
			nombreProducto=rs.getString("Nombre_Producto");
			System.out.println("ID:"+idProducto+" Nombre de Producto:"+nombreProducto+"");
		}
		entrada.nextLine();		
		if (nombreProducto==null) {
			System.out.println("No se encontro el producto en la lista");
		}else {
			int idIngresado;
			idProducto=0;
			int stockProducto;
			float precioProducto;
			
			do {
				System.out.println("Ingresa el ID");
				idIngresado=entrada.nextInt();
				String sql1="SELECT idProducto, Nombre_Producto, Stock_Producto, Precio_Producto FROM producto WHERE idProducto="+idIngresado;
				ResultSet rs1= conexion.devuelveConsulta(sql1);

				while(rs1.next()) {
					idProducto=rs1.getInt("idProducto");
					nombreProducto=rs1.getString("Nombre_Producto");
					stockProducto=rs1.getInt("Stock_Producto");
					precioProducto=rs1.getFloat("Precio_Producto");
					
					System.out.println("El producto a modificar es:\n  ID:"+idProducto+" Nombre de Producto:"+nombreProducto+" Stock:"+stockProducto+" Precio:"+precioProducto);
				}
				
				if(idProducto==0) {
					System.out.println("ID incorrecto, vuelve a interntarlo");
				}
				else {
					int opc;
					do {
						System.out.println("Que columnas desea modificar?\n 1.Nombre del Producto\n 2.Stock\n 3.Precio \n 4.Salir");
						opc=entrada.nextInt();

						switch(opc) {
						case 1:
							entrada.nextLine();		

							System.out.println("Ingrese nuevo Nombre:");
							String nuevNombre=entrada.next();
							System.out.println(nuevNombre);

							String sql2="UPDATE producto SET Nombre_Producto='"+nuevNombre+"' WHERE idProducto="+idProducto+";";
							conexion.realizaConsulta(sql2);
							System.out.println("Producto modificado");
							
							break;
						case 2:
							 entrada.nextLine();		

							System.out.println("Ingrese nuevo Stock:");
							int nuevStock=entrada.nextInt();
							
							String sql3="UPDATE producto SET Stock_Producto="+nuevStock+" WHERE idProducto="+idProducto+";";
							conexion.realizaConsulta(sql3);
							System.out.println("Producto modificado");
							
							break;
						case 3:
							 entrada.nextLine();		

							System.out.println("Ingrese nuevo Precio:");
							float nuevPrecio=entrada.nextFloat();
							
							String sql4="UPDATE producto SET Precio_Producto="+nuevPrecio+" WHERE idProducto="+idProducto+";";
							conexion.realizaConsulta(sql4);
							System.out.println("Producto modificado");
							
							break;
						case 4:
							break;
						
						default:
							System.out.println("Opcion incorrecta");
						};
						
					}while(opc!=4);
				}
			}while(idProducto==0);	
		};/////////es el corchete del else		
		
	};//es corchete del metodo MODIFICAR
	
	public void eliminarProducto() throws SQLException {
		String nombreBusqueda;
		String nombreProducto=null;
		int idProducto;
		Scanner entrada=new Scanner(System.in);

		System.out.println("¿Que producto deseas Eliminar? Ingresa el nombre y te aparecerá una lista de productos en la que podras identificar el ID");
		nombreBusqueda=entrada.next();
		
		CONEXION conexion=new CONEXION();
		
		String sql="SELECT idProducto, Nombre_Producto FROM producto WHERE nombre_Producto like '%"+nombreBusqueda+"%'";
		ResultSet rs= conexion.devuelveConsulta(sql);//guardo el resultado de la consulta en rs2
		while(rs.next()) {//mostramos por consola los productos coincidentes con el nombre
			idProducto=rs.getInt("idProducto");
			nombreProducto=rs.getString("Nombre_Producto");
			System.out.println("ID:"+idProducto+" Nombre de Producto:"+nombreProducto+"");
		}
		entrada.nextLine();		
		if (nombreProducto==null) {
			System.out.println("No se encontro el producto en la lista");
		}else {
			int idIngresado;
			idProducto=0;
			
			do {
				System.out.println("Ingresa el ID");
				idIngresado=entrada.nextInt();
				String sql1="SELECT idProducto, Nombre_Producto FROM producto WHERE idProducto="+idIngresado;
				ResultSet rs1= conexion.devuelveConsulta(sql1);

				while(rs1.next()) {
					idProducto=rs1.getInt("idProducto");
					nombreProducto=rs1.getString("nombre_Producto");
					
					
					System.out.println("El producto a eliminar es:\n  ID:"+idProducto+" Nombre de Producto:"+nombreProducto);
				}
				
				if(idProducto==0) {
					System.out.println("ID incorrecto, vuelve a interntarlo");
				}
				else {
					System.out.println("ID correcto... eliminando");
					String sql2="DELETE FROM producto WHERE idProducto="+idProducto+";";
					conexion.realizaConsulta(sql2);
					System.out.println("Producto eliminado");
					
				}
			}while(idProducto==0);	
		};
	
	}

public void verCompradores() throws SQLException {
	CONEXION conexion= new CONEXION();
	System.out.println("Los clientes que realizaron al menos una compra son;");

	String sql="SELECT u.Nombre_Usuario, c.Apellido_Cliente  "
			+ "FROM usuario as u inner join cliente as c "
			+ "on u.idUsuario=c.Usuario_idUsuario "
			+ "where NumCompras_Cliente>0;";
	
	ResultSet rs= conexion.devuelveConsulta(sql);
	while(rs.next()) {
		String nombreUsuario=rs.getString("Nombre_Usuario");
		String apellidoCliente=rs.getString("Apellido_Cliente");
		System.out.println("Nombre:"+nombreUsuario+"  Apellido:"+apellidoCliente);
		}
	};
	
}//NO SE TOCA
