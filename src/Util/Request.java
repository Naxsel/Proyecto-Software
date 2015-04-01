package Util;

import java.io.Serializable;

/**
 * Client's request
 * Created by agustin on 31/3/15.
 */
public class Request<T> implements Serializable {

    public enum TypeRequest{
        ISBN, TITLE, AUTHOR, PUBLISHER, GENRE, DATE, ADD, EDIT, DELETE
    }
    public enum Sort{
        ISBN, TITLE, AUTHOR, PUBLISHER, GENRE, DATE, NONE
    }

    //Atributes
    private TypeRequest request;
    private Sort sort;
    private T dummy;

    /**
     * Constructor
     * @param request : Client's request
     * @param dummy : object to send
     */
    public Request(TypeRequest request, T dummy) {
        this.request = request;
        this.dummy = dummy;
        this.sort = Sort.NONE;
    }

    /**
     * Constructor
     * @param request : client's request
     * @param sort : sort type
     * @param dummy : object to send: name, isbn, libro
     */
    public Request(TypeRequest request, Sort sort,T dummy){
        this.request = request;
        this.sort = sort;
        this.dummy = dummy;
    }

    /**
     *
     * @return : request
     */
    public TypeRequest getRequest(){
        return request;
    }

    /**
     *
     * @return : sort
     */
    public Sort getSort(){
        return sort;
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
     * @return : TypeRequest + sort + dummy
     */
    public String toString(){
        return "TypeRequest: " + request + "\nSort: " + sort + "\ndummy: " + dummy;
    }
}
