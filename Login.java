import java.sql.ResultSet;
import java.sql.SQLException;

public class Login {
	private String email;
	private String password;
	
	public Login(String email, String password) {
		this.email = email;
		this.password = password;
	}
	
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
	
	public boolean Validar(String tipo) throws SQLException {
		String Remail="null";
		String Rpassword=null;
		String Rnombre=null;
		boolean b=false;
		CONEXION conexion = new CONEXION();

		String sql="SELECT u.Email_Usuario, u.Password_Usuario, u.Nombre_Usuario "
				+ "FROM usuario as u inner join "+tipo+" as a "
				+ "on u.idUsuario=a.Usuario_idUsuario "
				+ "where email_usuario=\""+this.email +"\";";
		
				
		ResultSet rs= conexion.devuelveConsulta(sql);//guardo el resultado de la consulta en rs

		while(rs.next()) {//recorro el resultado

			Remail=rs.getString("Email_Usuario");
			Rpassword=rs.getString("Password_Usuario");
			Rnombre=rs.getString("Nombre_Usuario");
		}

		if(Remail.equals(this.email)){
			if(Rpassword.equals(this.password)) {
				
				System.out.println("Bienvenido/a "+Rnombre+"!");
				b=true;
				}
			else {
				System.out.println("Contaseña incorrecta");
				b=false;
			};
		}else {
			System.out.println("Los datos son incorrectos, vuelve a intentarlo");
			b=false;
		};
		return b;
	}
	public Cliente devolverDatosCliente() throws SQLException {
		int idCliente=0;
		String nombreCliente = null;
		String apellidoCliente = null;
		String domicilioCliente = null;
		String emailCliente = null;
		boolean tarjetaCliente = false;
		boolean premioCliente = false;
		int numcomprasCliente = 0;
		
		CONEXION conexion = new CONEXION();
		String sql="SELECT idCliente, Nombre_Usuario, Email_Usuario, Apellido_Cliente, Domicilio_Cliente, Tarjeta_Cliente, Premio_Cliente, NumCompras_Cliente "
				+ "FROM usuario as u inner join cliente as c "
				+ "on u.idUsuario=c.Usuario_idUsuario where Email_Usuario='"+this.email+"';";
		ResultSet rs= conexion.devuelveConsulta(sql);//guardo el resultado de la consulta en rs

		while(rs.next()) {//recorro el resultado
			idCliente=rs.getInt("idCliente");
			nombreCliente=rs.getString("Nombre_Usuario");
			apellidoCliente=rs.getString("Apellido_Cliente");
			domicilioCliente=rs.getString("Domicilio_Cliente");
			emailCliente=rs.getString("Email_Usuario");
			tarjetaCliente=rs.getBoolean("Tarjeta_Cliente");
			premioCliente=rs.getBoolean("Premio_Cliente");
			numcomprasCliente=rs.getInt("NumCompras_Cliente");
			
		}
		Cliente cliente= new Cliente(idCliente, nombreCliente, apellidoCliente, domicilioCliente, emailCliente, tarjetaCliente, premioCliente, numcomprasCliente);
		return cliente;
	}
}
