import java.sql.SQLException;
import java.util.Scanner;

public class Principal {

	public static void main(String[] args) throws SQLException {
		
		Scanner entrada=new Scanner(System.in);
		System.out.println("=*=*=SuperMark=*=*=");
		System.out.println("Eres cliente nuevo? Registrate! con la opcion 1\n\n Ya eres cliente?Inicia sesion con la opcion 2 \n\nAdmin? Inicia sesion con la opcion 3\n");//
		System.out.print("Ingresa tu opcion: ");
		short respuesta=entrada.nextShort();
		
		switch(respuesta) {
		case 1://Registro
			Registro();
			break;
			
		case 2://Inicio Cliente
			IngresoCliente();
			break;
			
		case 3://Inicio Admin
			IngresoAdministrador();
			break;
			
		default:
			System.out.println("Opcion incorrecta");
			break;
		}
		entrada.nextLine();

	}/////Llave del main
	
	
	
	
	public static void IngresoAdministrador() throws SQLException {////////////////////////////////////////////////////
		Scanner entrada=new Scanner(System.in);
		String email;
		String password;
		short respuesta;
		boolean bool;
		String tipo="administrador";
		do {
			System.out.print("Ingresar correo: ");
			email=entrada.nextLine();
			System.out.print("Ingresar contraseña: ");
			password=entrada.nextLine();
			Login login=new Login(email,password);
			bool=login.Validar(tipo);
		}while(bool==false);///Validacion de correo y contraseña
		
		Administrador ad=new Administrador();
		
		do {
			System.out.println("Menu:\n 1.Cargar Productos\n 2.Modificar Producto\n 3.Eliminar Producto\n 4.Ver Compradores\n 5.Salir");//
			respuesta=entrada.nextShort();
			
			switch(respuesta){
			case 1://Cargar Producto
				ad.cargarProducto();
				break;
			case 2://Modificar Producto
				ad.modificarProducto();
				break;
				
			case 3://Eliminar Producto
				ad.eliminarProducto();
				break;
			case 4://Ver compradores
				ad.verCompradores();
				break;
			case 5:
				break;
			
			default:
				System.out.println("Opcion incorrecta");
			
			}
		}while(respuesta!=5);
	}///Llave del ingreso del Administrador
	
	public static void IngresoCliente() throws SQLException {
		Scanner entrada=new Scanner(System.in);
		String email;
		String password;
		Login login;
		//short respuesta;
		boolean bool;
		String tipo="cliente";
		do {
			System.out.print("Ingresar correo: ");
			email=entrada.nextLine();
			System.out.print("Ingresar contraseña: ");
			password=entrada.nextLine();
			login=new Login(email,password);
			bool=login.Validar(tipo);
		}while(bool==false);///Validacion de correo y contraseña
		

		Cliente client=login.devolverDatosCliente();
		
		int opcion;
		do {
			System.out.println("Menu:\n 1.Seleccionar productos\n 2.Ver listado de productos seleccionados\n 3.Salir");
			opcion = entrada.nextInt();
			switch(opcion) {
			case 1 ://Seleccionar Productos
				client.CrearCarrito();
				break;	
			case 2://Ver listado de productos seleccionados YA CONFIRMADOS
				
				break;
			case 3:
				break;
			default:
				System.out.println("Opcion incorrecta");
				break;
				}
		}while(opcion!=3);
		
		
		
		
	}///Llave del ingreso del Cliente
	
	public static void Registro() throws SQLException {
		String nombreU;//
		String emailU;//
		String passwordU;//
		String apellidoCliente;//
		String domicilioCliente;
		boolean tarjetaCliente=false;
		boolean premioCliente=true;
		int numCompras=0;
		
		int opc;
		Scanner entrada = new Scanner(System.in);
	
		System.out.print("\nIngresar nombre: ");
		nombreU=entrada.next();
		
		System.out.print("Ingresar apellido: ");
		apellidoCliente=entrada.next();
		
		System.out.print("Ingresar domicilio: ");
		domicilioCliente=entrada.next();
		
		System.out.print("Ingresar email: ");
		emailU=entrada.next();
		
		//espacio para validar email si se puede
		
		System.out.println("Ingresar password: ");
		passwordU=entrada.next();
		
		entrada.nextLine();
		
		do {
			System.out.println("\nTienes tarjeta de descuento?\n 1.Si\n 2.No");
			System.out.print("Ingresa tu respuesta: ");
			opc=entrada.nextInt();
			if(opc==1) {
				tarjetaCliente=true;
				}
			else if(opc==2){
				tarjetaCliente=false;
				}
			else {
				System.out.println("opcion incorrecta");
				};
		}while(opc!=1 && opc!=2);
		
		Registro registro1= new Registro(nombreU, emailU, passwordU, apellidoCliente, domicilioCliente, tarjetaCliente, premioCliente, numCompras);
		registro1.subirRegistro();
		
	}///Llave del ingreso del Registro
	
	
}