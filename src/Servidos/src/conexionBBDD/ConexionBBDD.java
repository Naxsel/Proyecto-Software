/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package conexionBBDD;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import recursos.RecursosXml;


/**
 *
 * @author ruben
 */
public class ConexionBBDD {
    RecursosXml recursos;
    Connection conexion = null;

    public ConexionBBDD(RecursosXml recursos) {
        this.recursos = recursos;
        System.out.println(recursos.getDriver());
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
