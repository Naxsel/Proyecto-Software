package conexionBBDD;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import recursos.RecursosXml;


/**
 * ConexionBBDD.java
 * Se encarga de la conexion a la base de datos
 * 
 * @version 3.0.1 10 May 2014
 * @author Rubén Gabás
 */
public class ConexionBBDD {
    RecursosXml recursos;
    Connection conexion = null;

    public ConexionBBDD(RecursosXml recursos) {
        this.recursos = recursos;
    }
    
    public Connection conectar(){
        try {
            Class.forName(recursos.getDriver());
            conexion = (Connection) DriverManager.getConnection(recursos.getHostBBDD(),
                                                                recursos.getUser(),
                                                                recursos.getPasswd());
            if (conexion != null) {
               System.out.println("Conexión a base de datos  ... Ok"); 
               return conexion;
               //conexion.close();
            }
        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
        }
        return null;
        
      
    }
    
    public void desconectar(){
       try {
           this.conexion.close();
       } catch (SQLException ex) {
          
       }
    }
    
}
