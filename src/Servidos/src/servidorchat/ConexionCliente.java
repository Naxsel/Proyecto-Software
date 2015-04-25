package servidorchat;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.ConcurrentHashMap;


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
    private DataInputStream entradaDatos;
    private DataOutputStream salidaDatos;
    
    public ConexionCliente (Socket socket, TratamientoEntradas entradas, Usuarios usuarios, ConcurrentHashMap hashUsuario, Entradas entradasBD){
        this.hashUsuario = hashUsuario;
        this.socket = socket;
        this.entradas = entradas;
        this.usuarios = usuarios;
        this.entradasBD = entradasBD;
        
        
        try {
            entradaDatos = new DataInputStream(socket.getInputStream());
            salidaDatos = new DataOutputStream(socket.getOutputStream());
        } catch (IOException ex) {
            System.out.println("Error al crear los stream de entrada y salida : " + ex.getMessage());
        }
    }
    
    @Override
    public void run(){
        String entradaRecibida;
        boolean conectado = true;
        // Se apunta a la lista de observadores de mensajes
        entradas.addObserver(this);
        
        while (conectado) {
            try {
                // Lee un mensaje enviado por el cliente
                entradaRecibida = entradaDatos.readUTF();
                System.out.println(entradaRecibida);
                String[] opciones = entradaRecibida.split("-");
                switch(opciones[0]) {
                    case ALTA:
                        //System.out.println("Hola");
                        if (hashUsuario.containsKey(opciones[1])) {
                            salidaDatos.writeUTF("ALTA-false-El usuario ya está"
                                                 + " logueado.");
                        } else {
                            if (usuarios.AltaUsuario(opciones[1], opciones[2])) {
                                login = opciones[1];
                                hashUsuario.put(opciones[1], opciones[2]);
    //                            String ss = (String) hashUsuario.get(opciones[1]);
                                salidaDatos.writeUTF("ALTA-true");
                            } else {
                                salidaDatos.writeUTF("ALTA-false-Usuario y contraseña"
                                                     + " no coinciden.");
                            }
                        }
                        break;
                    case BAJA:
                        if (login != null) {
                            hashUsuario.remove(login);
                            login = null;
                            salidaDatos.writeUTF("BAJA-true");
                        } else {
                            salidaDatos.writeUTF("BAJA-false");
                        }
                        
                        break;
                    case ELIMINAR:
                        break;
                    case ENTRADA:
                        if (login != null) {
                            //entradas.setEntrada(entradaRecibida);
                            entradasBD.entrada(login, opciones[1], opciones[2]);
                        }
                        
                        break;
                }
                //salidaDatos.writeUTF("ok");
                // Pone el mensaje recibido en mensajes para que se notifique 
                // a sus observadores que hay un nuevo mensaje.
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
} 