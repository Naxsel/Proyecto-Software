package servidorchat;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class Usuarios {
	private Connection conection;
	private final String TABLA_USUARIOS = "usuarios";
	
	public Usuarios(Connection conection) {
		this.conection = conection;
	}       

	public boolean AltaUsuario (String nombre, String pass) {
		try {
			Statement sentencia = conection.createStatement();
			ResultSet resultado = sentencia.executeQuery("SELECT * FROM " + 
                                                                        TABLA_USUARIOS + " WHERE nombre='" + nombre + "'");
			while (resultado.next()) {
                            //System.out.println(resultado);
                if (resultado.getString("nombre").compareTo(nombre) == 0) {
                    if (resultado.getString("pass").compareTo(pass) == 0) {
                                        System.err.println("Log exito");
                                        return true;
                    } else {
                                        System.err.println("Log exito, usuario creado");
                                        return false;
                    }
                }
			}
			
			sentencia.execute("INSERT INTO " + TABLA_USUARIOS +
                                             " (nombre,pass) VALUES ('" + nombre
                                                + "','" + pass + "')");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 		return true;
		
	}
}
