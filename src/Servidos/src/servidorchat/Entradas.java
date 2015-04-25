/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package servidorchat;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Phyrion
 */
public class Entradas {
    private Connection conection;
	private final String TABLA_ENTRADAS = "entradas";
	
	public Entradas(Connection conection) {
		this.conection = conection;
	}       
    
    public int entrada(String usuario, String fecha, String entrada) {
        try {
            Statement sentencia = conection.createStatement();
//		ResultSet resultado = sentencia.executeQuery("SELECT * FROM " +
//                                                        TABLA_ENTRADAS + " WHERE nombre='" + nombre + "'");
            sentencia.execute("INSERT INTO " + TABLA_ENTRADAS +
                    " (fecha,entrada,usuario) VALUES ('" + fecha
                    + "','" + entrada + "','" + usuario + "')");
            ResultSet resultado = sentencia.executeQuery("SELECT MAX(id_entradas) max_id FROM " +
                                                TABLA_ENTRADAS + " WHERE usuario='" + usuario + "'");
            while (resultado.next()) {
                System.out.println(resultado.getInt("max_id"));
            }
            
            return -1;
        } catch (SQLException ex) {
            Logger.getLogger(Entradas.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
        
    }
}
