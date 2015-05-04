/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidor;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author Ruben
 */
public class Main {

    /**
      * Crea e inicializa el servidor
      */
    public static void main(String[] args) {

        ServerSocket servidor = null;
        Socket socket = null;
        RecursosXml recursos = new RecursosXml();
        ConexionBBDD conexion = new ConexionBBDD(recursos);
        BooksController booksController = new BooksController(conexion.conectar());

        try {
            // Se crea el serverSocket
            servidor = new ServerSocket(recursos.getPuerto());

            // Bucle infinito para esperar conexiones
            while (true) {
                System.out.println("Servidor a la espera de conexiones. En puerto: " + recursos.getPuerto());
                socket = servidor.accept();
                System.out.println("Cliente con la IP " + socket.getInetAddress().getHostName() + " conectado.");

                ConexionCliente cc = new ConexionCliente(socket, booksController);
                cc.start();

            }
        } catch (IOException ex) {
            System.out.println("Error: " + ex.getMessage());
        } finally {
            try {
                socket.close();
                servidor.close();
            } catch (IOException ex) {
                System.out.println("Error al cerrar el servidor: " + ex.getMessage());
            }
        }
    }
}
