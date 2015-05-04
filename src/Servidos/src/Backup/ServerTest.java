package Backup;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * ServerTest
 * Created by agustin on 31/3/15.
 */
public class ServerTest {

    public static void main(String args[]) throws IOException, ClassNotFoundException {
        ServerSocket dummyServerSocket = new ServerSocket(8080);
        System.out.print("ServerUp\n");
        Socket dummySocket = dummyServerSocket.accept();
        ObjectInputStream dummyInput = new ObjectInputStream(dummySocket.getInputStream());
        ObjectOutputStream dummyOutput = new ObjectOutputStream(dummySocket.getOutputStream());

        System.out.println("SERVER");
        System.out.println("****************************************");
        System.out.println((Request) dummyInput.readObject());
        
        BookBAck dummy = new BookBAck("T-Title","T-ISBN","T-Author","T-Genre","T-Date","http://pythoniza.me/wp-content/uploads/2014/10/ibHNQU.png","T-Publisher","T-Info");
        
        dummyOutput.writeObject(new Reply<String>(Reply.TypeReply.BOOK, "http://pythoniza.me/wp-content/uploads/2014/10/ibHNQU.png"));
    }
}
