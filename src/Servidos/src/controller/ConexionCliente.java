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
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import servidorchat.Entradas;
import servidorchat.TratamientoEntradas;
import servidorchat.Usuarios;
import servidorchat.exceptions.NonexistentEntityException;
import util.Book;
import util.Reply;
import util.Request;


/**
 * Esta clase gestiona el envio de datos entre el servidor y el cliente al que atiende.
 * 
 * @author Ivan Salas Corrales <http://programando-o-intentandolo.blogspot.com.es/>
 */
public class ConexionCliente extends Thread implements Observer{
    private final String ALTA = "ALTA";
    private final String ENTRADA = "ENTRADA";
    private final String ELIMINAR = "ELIMINAR";
    private final String BAJA = "BAJA";
    
    private Socket socket; 
    
    private static ConcurrentHashMap hashUsuario;
    private Usuarios usuarios;
    private String login;
    
    private TratamientoEntradas entradas;
    private Entradas entradasBD;
    private ObjectInputStream entradaDatos;
    private ObjectOutputStream salidaDatos;
    
    public ConexionCliente (Socket socket){
        this.socket = socket;
        
        
        
        try {
            entradaDatos = new ObjectInputStream(socket.getInputStream());
            salidaDatos = new ObjectOutputStream(socket.getOutputStream());
            System.out.println("SERVER");
            System.out.println("****************************************");
            
//            Request req = (Request) entradaDatos.readObject();
//            System.out.println( (Books) req.getDummy());
//        
//            Book dummy = new Book("T-Title","T-ISBN","T-Author","T-Genre","T-Date","http://pythoniza.me/wp-content/uploads/2014/10/ibHNQU.png","T-Publisher","T-Info");
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
                System.out.println("OP " + entradaRecibida);
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
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ServidosPU");
        EntityManager em = emf.createEntityManager();
        BooksJpaController service =  new BooksJpaController(emf);
        em.getTransaction().begin();
        service.create((Books) entradaRecibida.getDummy());
        em.getTransaction().commit();
        System.out.println("Persisted" + (Books) entradaRecibida.getDummy());
        em.close();
        emf.close();
        try {
            salidaDatos.writeObject(new Reply<String>(Reply.TypeReply.OK, "http://pythoniza.me/wp-content/uploads/2014/10/ibHNQU.png"));
        } catch (IOException ex) {
            Logger.getLogger(ConexionCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void borrar(Request entradaRecibida) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ServidosPU");
        EntityManager em = emf.createEntityManager();
        BooksJpaController service =  new BooksJpaController(emf);
        em.getTransaction().begin();    
        try {
            service.destroy(Integer.parseInt((String) entradaRecibida.getDummy()));
            em.getTransaction().commit();
            System.out.println("Borrado " + entradaRecibida.getDummy());
            em.close();
            emf.close();
            salidaDatos.writeObject(new Reply<String>(Reply.TypeReply.OK, "http://pythoniza.me/wp-content/uploads/2014/10/ibHNQU.png"));
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(ConexionCliente.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ConexionCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void buscarByISBN(Request entradaRecibida) {
        Books libro =  new Books();
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ServidosPU");
        EntityManager em = emf.createEntityManager();
        TypedQuery<Books> query = em.createNamedQuery("Books.findByIsbn", Books.class)
                                                        .setParameter("isbn", entradaRecibida.getDummy());
        System.out.println(query.getResultList().get(0));
        libro = query.getResultList().get(0);
//        em.getTransaction().commit();
        //System.out.println("Borrado " + entradaRecibida.getDummy());
        em.close();
        emf.close();
        
        try {
            salidaDatos.writeObject(new Reply<Books>(Reply.TypeReply.BOOK, libro));
        } catch (IOException ex) {
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
        }    }
} 