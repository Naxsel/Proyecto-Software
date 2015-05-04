package util;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketException;

/**
 * Client connection withServer
 * Created by agustin on 31/3/15.
 */
public class ClientSend {
    private Socket socket;
    private ObjectInputStream inputStream;
    private ObjectOutputStream outputStream;

    public ClientSend(String ip, int port) throws SocketException, IOException {
        socket = new Socket(ip, port);
        outputStream = new ObjectOutputStream(socket.getOutputStream());
        inputStream = new ObjectInputStream(socket.getInputStream());
    }

    /**
     * Send a request to server and return the reply
     * @param request : request to server
     * @return : reply from server
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public Reply send2Server(Request request) throws IOException, ClassNotFoundException {
        //Send request
        outputStream.writeObject(request);
        //Receives reply
        return (Reply) inputStream.readObject();
    }

    /**
     * Main for first test of client
     * @param args
     */
    public static void main(String args[]) throws IOException, ClassNotFoundException {
        ClientSend dummy = new ClientSend("localhost", 8080);

        System.out.println("ClientSend");
        System.out.println("********************************************************************************");
        System.out.println(dummy.send2Server(new Request<String>(Request.TypeRequest.TITLE, Request.Sort.NONE, "Test Client")));
    }
}
