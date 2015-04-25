//package servidorchat;
//
//import conexionBBDD.ConexionBBDD;
//import java.io.IOException;
//import java.net.ServerSocket;
//import java.net.Socket;
//import java.sql.Connection;
//import java.util.concurrent.ConcurrentHashMap;
//import recursos.RecursosXml;
//
///**
// * Servidor para el chat.
// * 
// * @author Ivan Salas Corrales <http://programando-o-intentandolo.blogspot.com.es/>
// */
//public class ServidorAgenda {
//
//    /**
//     * @param args the command line arguments
//     */
//    public static void main(String[] args) {
//        
//        // Carga el archivo de configuracion de log4J
//        
////        int maximoConexiones = 10; // Maximo de conexiones simultaneas
//        ServerSocket servidor = null; 
//        Socket socket = null;
//        RecursosXml recursos = new RecursosXml();
//        ConcurrentHashMap hashUsuario = new ConcurrentHashMap();
//        TratamientoEntradas entradas = new TratamientoEntradas();
//        ConexionBBDD conexionBD = new ConexionBBDD(recursos);
//        Connection conection = conexionBD.conectar();
//        Usuarios usuarios = new Usuarios(conection);
//        Entradas entradasBD = new Entradas(conection);
//        
//        try {
//            // Se crea el serverSocket
////            servidor = new ServerSocket(puerto, maximoConexiones);
//            servidor = new ServerSocket(recursos.getPuerto());
//            
//            // Bucle infinito para esperar conexiones
//            while (true) {
//                System.out.println("Servidor a la espera de conexiones.");
//                socket = servidor.accept();
//                System.out.println("Cliente con la IP " + socket.getInetAddress().getHostName() + " conectado.");
//                
//                ConexionCliente cc = new ConexionCliente(socket, entradas, usuarios, hashUsuario, entradasBD);
//                cc.start();
//                
//            }
//        } catch (IOException ex) {
//            System.out.println("Error: " + ex.getMessage());
//        } finally{
//            try {
//                socket.close();
//                servidor.close();
//            } catch (IOException ex) {
//                System.out.println("Error al cerrar el servidor: " + ex.getMessage());
//            }
//        }
//    }
//}