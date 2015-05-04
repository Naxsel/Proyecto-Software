package controller;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import Util.Books;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;


import Util.*;
import Util.exceptions.NonexistentEntityException;
import java.sql.SQLException;


/**
 *
 */
public class ConexionCliente extends Thread implements Observer{
    
    private Socket socket; 
    private BooksController booksController;
    
    private static ConcurrentHashMap hashUsuario;
    private String login;
    
    private ObjectInputStream entradaDatos;
    private ObjectOutputStream salidaDatos;
    
    public ConexionCliente (Socket socket, BooksController booksController){
        this.socket = socket;
        this.booksController = booksController;
        
        
        try {
            entradaDatos = new ObjectInputStream(socket.getInputStream());
            salidaDatos = new ObjectOutputStream(socket.getOutputStream());
            System.out.println("SERVER");
            System.out.println("****************************************");
            
//            Request req = (Request) entradaDatos.readObject();
//            System.out.println( (Books) req.getDummy());
//        
//            Books dummy = new Books("T-Title","T-ISBN","T-Author","T-Genre","T-Date","http://pythoniza.me/wp-content/uploads/2014/10/ibHNQU.png","T-Publisher","T-Info");
//        
//            salidaDatos.writeObject(new Reply<String>(Reply.TypeReply.BOOK, "http://pythoniza.me/wp-content/uploads/2014/10/ibHNQU.png"));
        } catch (IOException ex) {
            System.out.println("Error al crear los stream de entrada y salida : " + ex.getMessage());
        }
    }
    
    @Override
    public void run(){
        Request entradaRecibida;
        boolean conectado = true;
        // Se apunta a la lista de observadores de mensajesW
        
        while (conectado) {
            try {
                // Lee un mensaje enviado por el cliente
                entradaRecibida = (Request) entradaDatos.readObject();
                //System.out.println("OP " + entradaRecibida);
                switch (entradaRecibida.getRequest()) {
                    case ADD:
                        agregar(entradaRecibida);
                        break;
                    case DELETE:
                        borrar(entradaRecibida);
                        break;
                    case ISBN:
                        buscarByISBN(entradaRecibida);
                        break;
                    case TITLE:
                        buscarByTitle(entradaRecibida);
                    case EDIT:
                        modificar(entradaRecibida);
                        break;
                }
            } catch (IOException ex) {
                if (login != null) {
                    hashUsuario.remove(login);
                }
                System.out.println("Cliente con la IP " + socket.getInetAddress().getHostName() + " desconectado.");
                conectado = false;
                // Si se ha producido un error al recibir datos del cliente se cierra la conexion con el.
                try {
                    entradaDatos.close();
                    salidaDatos.close();
                } catch (IOException ex2) {
                    System.out.println("Error al cerrar los stream de entrada y salida :" + ex2.getMessage());
                }
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(ConexionCliente.class.getName()).log(Level.SEVERE, null, ex);
            }
        }   
    }
    
    @Override
    public void update(Observable o, Object arg) {
        try {
            if (login != null) {
                salidaDatos.writeUTF(arg.toString());
            }
        } catch (IOException ex) {
            System.out.println("Error al enviar mensaje al cliente (" + ex.getMessage() + ").");
        }
    }

    private void agregar(Request entradaRecibida) {
        System.out.println((Books) entradaRecibida.getDummy());
        booksController.create((Books) entradaRecibida.getDummy());
      
        try {
            salidaDatos.writeObject(new Reply<String>(Reply.TypeReply.OK, "http://pythoniza.me/wp-content/uploads/2014/10/ibHNQU.png"));
        } catch (IOException ex) {
            Logger.getLogger(ConexionCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void borrar(Request entradaRecibida) throws IOException {
        try {
            booksController.delete((String) entradaRecibida.getDummy());
            salidaDatos.writeObject(new Reply<String>(Reply.TypeReply.OK, "http://pythoniza.me/wp-content/uploads/2014/10/ibHNQU.png"));
        } catch (SQLException ex) {
            Logger.getLogger(ConexionCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void buscarByISBN(Request entradaRecibida) {
        try {
            booksController.buscarPorISBN((String) entradaRecibida.getDummy());
            //salidaDatos.writeObject(new Reply<Books>(Reply.TypeReply.BOOK, libro));
        } catch (SQLException ex) {
            Logger.getLogger(ConexionCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }

    private void modificar(Request entradaRecibida) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ServidosPU");
        EntityManager em = emf.createEntityManager();
        BooksJpaController service =  new BooksJpaController(emf);
        em.getTransaction().begin();
        try {
            service.edit((Books) entradaRecibida.getDummy());
        } catch (Exception ex) {
            Logger.getLogger(ConexionCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
        em.getTransaction().commit();
        System.out.println("Modificado" + (Books) entradaRecibida.getDummy());
        em.close();
        emf.close();
        try {
            salidaDatos.writeObject(new Reply<String>(Reply.TypeReply.OK, "http://pythoniza.me/wp-content/uploads/2014/10/ibHNQU.png"));
        } catch (IOException ex) {
            Logger.getLogger(ConexionCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
 }

    private void buscarByTitle(Request entradaRecibida) {
        List<Books> libro =  new Vector();
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ServidosPU");
        EntityManager em = emf.createEntityManager();
        TypedQuery<Books> query = em.createNamedQuery("Books.findByTitle", Books.class)
                                                        .setParameter("title", entradaRecibida.getDummy());
        //System.out.println(query.getResultList().get(0));
        libro = query.getResultList();
//        em.getTransaction().commit();
        //System.out.println("Borrado " + entradaRecibida.getDummy());
        em.close();
        emf.close();
        
        try {    
            salidaDatos.writeObject(new Reply<List>(Reply.TypeReply.BOOK, libro));
        } catch (IOException ex) {
            Logger.getLogger(ConexionCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
} 