import java.sql.ResultSet;
import java.sql.SQLException;

public class Registro {
	private String nombreU;
	private String emailU;
	private String passwordU;
	private String apellidoCliente;
	private String domicilioCliente;
	private boolean tarjetaCliente;
	private boolean premioCliente;
	private int numCompras;
	
	public Registro(String nombreU, String emailU, String passwordU, String apellidoCliente, String domicilioCliente,boolean tarjetaCliente, boolean premioCliente, int numCompras) {
		this.nombreU = nombreU;
		this.emailU = emailU;
		this.passwordU = passwordU;
		this.apellidoCliente = apellidoCliente;
		this.domicilioCliente = domicilioCliente;
		this.tarjetaCliente = tarjetaCliente;
		this.premioCliente = premioCliente;
		this.numCompras = numCompras;
	}

	public void subirRegistro() throws SQLException {
		int idUsuario;
		idUsuario=subirUsuario(this.nombreU, this.emailU, this.passwordU);
		
		CONEXION conexion = new CONEXION();
		String sql = "INSERT INTO cliente "
				+ "(Apellido_Cliente, Domicilio_Cliente, Tarjeta_Cliente, Premio_Cliente, NumCompras_Cliente, Usuario_idUsuario) "
				+ "VALUES('"+this.apellidoCliente+"','"+this.domicilioCliente+"',"+this.tarjetaCliente+","+this.premioCliente+","+this.numCompras+","+idUsuario+");" ;
		conexion.realizaConsulta(sql);
		System.out.println("Cliente Registrado\n");
	}
	
	
	private static int subirUsuario(String nombreU, String emailU, String passwordU) throws SQLException {
		int idU=0;
		
		CONEXION conexion=new CONEXION();
		String sql="INSERT INTO usuario (Nombre_Usuario, Email_Usuario, Password_Usuario) VALUES ('"+nombreU+"', '"+emailU+"', '"+passwordU+"');";
		conexion.realizaConsulta(sql);
		System.out.println("Creando el usuario...");
		
		String sql1="SELECT idUsuario FROM usuario WHERE Email_Usuario='"+emailU+"'";
		ResultSet rs= conexion.devuelveConsulta(sql1);
		while(rs.next()) {
			idU=rs.getInt("idUsuario");
			System.out.println("ID:"+idU);
		}
		return idU;
	}

}
