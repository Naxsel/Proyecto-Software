package servidor;

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
import java.util.Vector;
import util.*;
import java.sql.SQLException;


/**
 * Clase que gestiona la conexión con un cliente
 */
public class ConexionCliente extends Thread implements Observer{

    private Socket socket;
    private BooksController booksController;

    private static ConcurrentHashMap hashUsuario;
    private String login;

    private ObjectInputStream entradaDatos;
    private ObjectOutputStream salidaDatos;

    /**
      * Constructor de la conexión con el cliente
      * @param socket: socket de conxión
      * @param booksController: objeto que guarda las peticiones que se pueden
      * realizar al servidor
      */
    public ConexionCliente (Socket socket, BooksController booksController){
        this.socket = socket;
        this.booksController = booksController;


        try {
            entradaDatos = new ObjectInputStream(socket.getInputStream());
            salidaDatos = new ObjectOutputStream(socket.getOutputStream());
            System.out.println("SERVER");
            System.out.println("****************************************");

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
                        break;
                    case AUTHOR:
                        buscarByAuthor(entradaRecibida);
                        break;
                    case GENRE:
                        buscarByGenre(entradaRecibida);
                        break;
                    case PUBLISHER:
                        buscarByPublisher(entradaRecibida);
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
        System.out.println((Books) entradaRecibida.getDummy());
        booksController.create((Books) entradaRecibida.getDummy());

        try {
            salidaDatos.writeObject(new Reply<String>(Reply.TypeReply.OK, ""));
        } catch (IOException ex) {
            Logger.getLogger(ConexionCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void borrar(Request entradaRecibida) throws IOException {
        try {
            booksController.delete((String) entradaRecibida.getDummy());
            salidaDatos.writeObject(new Reply<String>(Reply.TypeReply.OK, ""));
        } catch (SQLException ex) {
            Logger.getLogger(ConexionCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
      * Busca un libro por ISN
      * @param entradaRecibida: request del cliente
      */
    private void buscarByISBN(Request entradaRecibida) {
        Vector<Books> resultados;
        try {
            resultados = booksController.buscarPorISBN((String) entradaRecibida.getDummy());
            salidaDatos.writeObject(new Reply<Vector>(Reply.TypeReply.VECTOR, resultados));
        } catch (SQLException ex) {
            Logger.getLogger(ConexionCliente.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException e){
            Logger.getLogger(ConexionCliente.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    /**
      * Busca un libro por titulo
      * @param entradaRecibida: request del cliente
      */
    private void buscarByTitle(Request entradaRecibida) {
        Vector<Books> resultados;
        try {
            resultados = booksController.buscarPorTitle((String) entradaRecibida.getDummy());
            salidaDatos.writeObject(new Reply<Vector>(Reply.TypeReply.VECTOR, resultados));
        } catch (SQLException ex) {
            Logger.getLogger(ConexionCliente.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException e){
            Logger.getLogger(ConexionCliente.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    /**
      * Busca un libro por autor
      * @param entradaRecibida: request del cliente
      */
    private void buscarByAuthor(Request entradaRecibida) {
        Vector<Books> resultados;
        try {
            resultados = booksController.buscarPorAuthor((String) entradaRecibida.getDummy());
            salidaDatos.writeObject(new Reply<Vector>(Reply.TypeReply.VECTOR, resultados));
        } catch (SQLException ex) {
            Logger.getLogger(ConexionCliente.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException e){
            Logger.getLogger(ConexionCliente.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    /**
      * Busca un libro por Genero
      * @param entradaRecibida: request del cliente
      */
    private void buscarByGenre(Request entradaRecibida) {
        Vector<Books> resultados;
        try {
            resultados = booksController.buscarPorGenre((String) entradaRecibida.getDummy());
            salidaDatos.writeObject(new Reply<Vector>(Reply.TypeReply.VECTOR, resultados));
        } catch (SQLException ex) {
            Logger.getLogger(ConexionCliente.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException e){
            Logger.getLogger(ConexionCliente.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    /**
      * Busca un libro por Editorial
      * @param entradaRecibida: request del cliente
      */
    private void buscarByPublisher(Request entradaRecibida) {
        Vector<Books> resultados;
        try {
            resultados = booksController.buscarPorPublisher((String) entradaRecibida.getDummy());
            salidaDatos.writeObject(new Reply<Vector>(Reply.TypeReply.VECTOR, resultados));
        } catch (SQLException ex) {
            Logger.getLogger(ConexionCliente.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException e){
            Logger.getLogger(ConexionCliente.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    private void modificar(Request entradaRecibida) {
//        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ServidosPU");
//        EntityManager em = emf.createEntityManager();
//        BooksJpaController service =  new BooksJpaController(emf);
//        em.getTransaction().begin();
//        try {
//            service.edit((Books) entradaRecibida.getDummy());
//        } catch (Exception ex) {
//            Logger.getLogger(ConexionCliente.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        em.getTransaction().commit();
//        System.out.println("Modificado" + (Books) entradaRecibida.getDummy());
//        em.close();
//        emf.close();
//        try {
//            salidaDatos.writeObject(new Reply<String>(Reply.TypeReply.OK, "http://pythoniza.me/wp-content/uploads/2014/10/ibHNQU.png"));
//        } catch (IOException ex) {
//            Logger.getLogger(ConexionCliente.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }
}
