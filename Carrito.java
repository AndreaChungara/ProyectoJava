import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class Carrito {
	ArrayList <Integer> idProducto;
	ArrayList <Integer> cantidad;

	int idCompra;
	float total=0;
	
	
	


	public Carrito(ArrayList<Integer> idProducto, ArrayList<Integer> cantidad) {
		super();
		this.idProducto = idProducto;
		this.cantidad = cantidad;
	}
	public ArrayList<Integer> getIdProducto() {
		return idProducto;
	}
	public void setIdProducto(ArrayList<Integer> idProducto) {
		this.idProducto = idProducto;
	}
	public ArrayList<Integer> getCantidad() {
		return cantidad;
	}
	public void setCantidad(ArrayList<Integer> cantidad) {
		this.cantidad = cantidad;
	}
	public int getIdCompra() {
		return idCompra;
	}
	public void setIdCarrito(int idCompra) {
		this.idCompra = idCompra;
	}
	public float getTotal() {
		return total;
	}
	public void setTotal(float total) {
		this.total = total;
	}


	public float calcularTotal() throws SQLException {
		CONEXION conexion= new CONEXION();
		int id;
		float precio=0;
		int cant;
		float calculo=0;
		
		for(int i=0; i<this.idProducto.size(); i++) {
			id=this.idProducto.get(i);

			String sql2 = "SELECT Precio_Producto FROM producto WHERE idProducto="+id+";";
			ResultSet rs2 = conexion.devuelveConsulta(sql2);
			
			while(rs2.next()) {
				precio=rs2.getFloat("Precio_Producto");	
			}
			System.out.println("precio: "+precio);

			cant=cantidad.get(i);
			System.out.println("cantidad: "+cant);

			calculo+=precio*cant;

		}
		this.total=calculo;

		return calculo;
	}
	
	public int agregarCompra(int idCliente) throws SQLException {
		Scanner entrada=new Scanner(System.in);
		CONEXION conexion=new CONEXION();
		
		String formaPago = null;
		int opc=0;
		int idcomp=0;
		
		if(this.total==0) {
			this.total=calcularTotal();
		}
		
		do {
			System.out.println("Elige tu forma de pago:\n 1.Transferencia bancaria\n 2.Tarjeta de Credito\n 3.Tarjeta Prepaga\n 4.Salir");
			opc=entrada.nextInt();
			switch(opc) {
			case 1:
				formaPago="Transferencia bancaria";
				break;
			case 2:
				formaPago="Tarjeta de Credito";
				break;
			case 3:
				formaPago="Tarjeta Prepaga";
				break;
			case 4:
				break;
			default:
				System.out.println("Opcion incorrecta, vuelve a intentarlo");

			}
		}while(opc<1 || opc>4);
		
		if(opc!=4) {
			String sql="INSERT INTO compra (Total_Compra, FormaPago_Compra, Cliente_idCliente) VALUES ("+this.total+",'"+formaPago+"',"+idCliente+");";
			conexion.realizaConsulta(sql);
			
			String sql2 = "SELECT idCompra FROM compra WHERE Cliente_idCliente="+idCliente+" and Total_Compra="+this.total+" and FormaPago_Compra='"+formaPago+"';";
			ResultSet rs2 = conexion.devuelveConsulta(sql2);
			
			while(rs2.next()) {
				idcomp= rs2.getInt("idCompra");
			
			}
			this.idCompra=idcomp;
			return idCompra;
		}
		else {
			return 0;
		}
		
	}
	///PARA METODO DE AGREGAR
	
	
	
	
	//PARA METODO DE ELIMINAR
	
	
	
	//PARA CONFIRMAR
}
