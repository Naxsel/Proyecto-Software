///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//
//package conexionBBDD;
//
//import java.sql.Connection;
//import java.sql.SQLException;
//import java.sql.Statement;
//import java.util.logging.Level;
//import java.util.logging.Logger;
//import recursos.RecursosXml;
//
///**
// *
// * @author Phyrion
// */
//public class CrearBBDD {
//    
//    /**
//     *
//     * @param conexion
//     */
//    public CrearBBDD (Connection conexion) {
//        try {
//            Statement st = conexion.createStatement();
//            
//            // Elimina posible tabla anterior
//            st.execute("DROP TABLE IF EXISTS entradas");
//            
//            // Crea nueva tabla de Contactos
//            st.execute("CREATE TABLE entradas (" +
//                "id_entradas INTEGER UNSIGNED NOT NULL AUTO_INCREMENT," +
//                "fecha VARCHAR(45) NOT NULL DEFAULT ''," +
//                "entrada VARCHAR(200) NOT NULL DEFAULT ''," +
//                "usuario VARCHAR(45) NOT NULL DEFAULT ''," +
//                "PRIMARY KEY(id_entradas))");
//        } catch (SQLException ex) {
//            Logger.getLogger(CrearBBDD.class.getName()).log(Level.SEVERE, null, ex);
//        }
//  }
//    public static void main (String[] args) {
//        new CrearBBDD(new ConexionBBDD(new RecursosXml()).conectar());
//    }
//}
