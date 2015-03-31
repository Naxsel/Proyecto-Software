package client;

import java.io.Serializable;

/**
 * Server's Reply
 * Created by agustin on 31/3/15.
 */
public class Reply<T> implements Serializable{
    public enum TypeReply{
        OK, BOOK, VECTOR, FAIL
    }

    //Atributes
    private TypeReply reply;
    private T dummy;

    /**
     * Constructor
     * @param reply : Servers's reply
     * @param dummy : object to send, vector, libro, nothin
     */
    public Reply (TypeReply reply, T dummy){
        this.reply = reply;
        this.dummy = dummy;
    }

    /**
     *
     * @return : reply
     */
    public TypeReply getReply(){
        return reply;
    }

    /**
     *
     * @return : dummy
     */
    public T getDummy(){
        return dummy;
    }

    /**
     * A simple toString method
     * @return : TypeRequest + dummy
     */
    public String toString(){
        return "TypeReply: " + reply + "\ndummy: " + dummy;
    }
}
