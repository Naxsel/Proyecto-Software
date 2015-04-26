/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import controller.BooksJpaController;
import Util.Books;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import recursos.RecursosXml;

/**
 *
 * @author Ruben
 */
public class Main {
    public static void main(String[] a) {
        ServerSocket servidor = null; 
        Socket socket = null;
        RecursosXml recursos = new RecursosXml();
        
//        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ServidosPU");
//        EntityManager em = emf.createEntityManager();
//        BooksJpaController service =  new BooksJpaController(emf);
//        em.getTransaction().begin();
//        Books libro =  new Books();
//        TypedQuery<Books> query = em.createNamedQuery("Books.findAll", Books.class);
//        //System.out.println("");
//        libro.setTitle("Prueba");
//        libro.setDate("12/05/1989");
//        libro.setAuthor("Ruben");
//        libro.setGenre("Masculino");
//        libro.setIsbn("sasa2121");
//        libro.setPublisher("sasa");
//        libro.setInfo("Op libro tu");
//        service.create(libro);
//        em.getTransaction().commit();
//        System.out.println("Persisted" + libro);
//        em.close();
//        emf.close();
        
        try {
            // Se crea el serverSocket
//            servidor = new ServerSocket(puerto, maximoConexiones);
            servidor = new ServerSocket(recursos.getPuerto());

            // Bucle infinito para esperar conexiones
            while (true) {
                System.out.println("Servidor a la espera de conexiones. En puerto: " + recursos.getPuerto());
                socket = servidor.accept();
                System.out.println("Cliente con la IP " + socket.getInetAddress().getHostName() + " conectado.");

                ConexionCliente cc = new ConexionCliente(socket);
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
