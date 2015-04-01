package admin;

import java.io.IOException;
import java.util.Scanner;
import util.*;

/**
 * Created by agustin on 1/4/15.
 */
public class console {

    private static ClientSend server;

    public static void main(String args[]) {
        Scanner input = new Scanner(System.in);
        String action;

        try {
            server = new ClientSend("localhost", 8080);
            while (true) {
                System.out.print("Acción (h ayuda): ");
                action = input.nextLine();

                switch (action) {
                    case "h":
                        help();
                        break;
                    case "a":
                        add();
                        break;
                    case "m":
                        edit();
                        break;
                    case "d":
                        erase();
                        break;
                    default:
                        System.out.println("Comando no reconocido.");
                        break;
                }
            }
        } catch (IOException e){
            System.out.println("Problema con el servidor");
            return;
        } catch (ClassNotFoundException e){
            System.out.println("Problema con el servidor");
            return;
        }
    }

    /**
     * Show help menu
     */
    public static void help(){
        System.out.println("Comandos:");
        System.out.println("h - muestra el menu de ayuda.");
        System.out.println("a - añadir un nuevo libro a la colección.");
        System.out.println("m - modifica un nuevo libro a la colección.");
        System.out.println("d - borra un libro de la colección.");
    }

    /**
     * Add a book
     */
    public static void add() throws IOException, ClassNotFoundException {
        Scanner input = new Scanner(System.in);
        String title, isbn, date, author, publisher, genre, description, ruta;
        Book libro;
        Request<Book> req;
        Reply reply;


        System.out.print("Titulo del libro: ");
        title = input.nextLine();
        while(title == null || title.equalsIgnoreCase("")){
            System.out.println("Error con el titulo del libro.");
            System.out.print("Titulo del libro: ");
            title = input.nextLine();
        }

        System.out.print("ISBN del libro: ");
        isbn = input.nextLine();
        while(isbn == null || isbn.equalsIgnoreCase("")){
            System.out.println("Error con el ISBN del libro.");
            System.out.print("ISBN del libro: ");
            isbn = input.nextLine();
        }

        System.out.print("Autor del libro: ");
        author = input.nextLine();
        while(author == null || author.equalsIgnoreCase("")){
            System.out.print("Error con el titulo del libro.");
            System.out.print("Autor del libro: ");
            author = input.nextLine();
        }

        System.out.print("Genero del libro: ");
        genre = input.nextLine();
        while(genre == null || genre.equalsIgnoreCase("")){
            System.out.println("Error con el titulo del libro.");
            System.out.print("Genero del libro: ");
            genre = input.nextLine();
        }

        System.out.print("Editorial del libro: ");
        publisher = input.nextLine();
        while(publisher == null || publisher.equalsIgnoreCase("")){
            System.out.println("Error con el titulo del libro.");
            System.out.print("Editorial del libro: ");
            publisher = input.nextLine();
        }

        System.out.print("Fecha de lanzamiento del libro: ");
        date = input.nextLine();
        while(date == null || date.equalsIgnoreCase("")){
            System.out.println("Error con el titulo del libro.");
            System.out.print("Fecha de lanzamiento del libro: ");
            date = input.nextLine();
        }

        System.out.print("Descripción del libro: ");
        description = input.nextLine();
        while(description == null || description.equalsIgnoreCase("")){
            System.out.println("Error con el titulo del libro.");
            System.out.print("Descripción del libro: ");
            description = input.nextLine();
        }

        System.out.print("Ruta de la imagen del libro: ");
        ruta = input.nextLine();
        while(ruta == null || ruta.equalsIgnoreCase("")){
            System.out.println("Error con el titulo del libro.");
            System.out.print("Ruta de la imagen del libro: ");
            ruta = input.nextLine();
        }

        libro = new Book(title, isbn, author, genre, date, ruta, publisher, description);
        req = new Request<>(Request.TypeRequest.ADD, libro);

        //Send book to server
        reply = server.send2Server(req);

        if(reply.getReply() == Reply.TypeReply.OK){
            System.out.println("Libro añadido");
        } else{
            System.out.println("Ha ocurrido un error.");
        }

    }

    /**
     * Delete a book
     */
    public static void erase() throws IOException, ClassNotFoundException {
        Scanner input = new Scanner(System.in);
        String isbn;
        Request request;
        Reply reply;

        System.out.print("ISBN del libro a borrar: ");
        isbn = input.nextLine();
        while(isbn == null || isbn.equals("")){
            System.out.println("ISBN erroneo");
            System.out.print("ISBN del libro a borrar: ");
            isbn = input.nextLine();
        }
        request = new Request(Request.TypeRequest.DELETE, isbn);
        reply = server.send2Server(request);

        if (reply.getReply() == Reply.TypeReply.OK){
            System.out.println("Libro borrado correctamente");
        } else{
            System.out.println("El libro no se pudo borrar correctamente");
        }
    }

    /**
     * Edit a book's information
     */
    public static void edit() throws IOException, ClassNotFoundException {
        Scanner input = new Scanner(System.in);
        String isbn, type, change;
        Request request;
        Reply reply;
        Book libro;

        System.out.print("ISBN del libro a borrar: ");
        isbn = input.nextLine();
        while(isbn == null || isbn.equals("")){
            System.out.println("ISBN erroneo");
            System.out.print("ISBN del libro a borrar: ");
            isbn = input.nextLine();
        }

        //Busca el libro
        request = new Request(Request.TypeRequest.ISBN, isbn);
        reply = server.send2Server(request);
        if (reply.getReply() == Reply.TypeReply.BOOK){
            libro = (Book) reply.getDummy();

            //Pide la nueva info del libro
            while(true) {
                System.out.print("Parámetro a cambiar (TITULO, AUTOR, EDITOR, DESCRIPCION, GENERO, FECHA, ISBN, IMAGEN)" +
                        ", fin para acabar: ");
                type = input.nextLine();
                while(type != null || type.equals("")){
                    System.out.println("Parámetro no valido.");
                    System.out.print("Parámetro a cambiar (TITULO, AUTOR, EDITOR, DESCRIPCION, GENERO, FECHA, ISBN, IMAGEN)" +
                            ", fin para acabar: ");
                    type = input.nextLine();
                }
                System.out.print("Nuevo valor: ");
                change = input.nextLine();
                while(change != null || change.equals("")){
                    System.out.println("Valor no valido.");
                    System.out.println("Nuevo valor: ");
                    change = input.nextLine();
                }
                switch (type){
                    case "TITULO" :
                        libro.setTitle(change);
                        break;
                    case "AUTHOR" :
                        libro.setAuthor(change);
                        break;
                    case "EDITOR" :
                        libro.setPublisher(change);
                        break;
                    case "DESCRIPCION" :
                        libro.setInfo(change);
                        break;
                    case "GENERO" :
                        libro.setGenre(change);
                        break;
                    case "FECHA" :
                        libro.setDate(change);
                        break;
                    case "ISBN" :
                        libro.setIsbn(change);
                        break;
                    case "IMAGEN" :
                        libro.setImg(change);
                        break;
                    case "FIN" :
                        request = new Request(Request.TypeRequest.EDIT, libro);
                        reply = server.send2Server(request);
                        if (reply.getReply() == Reply.TypeReply.OK)
                            System.out.println("Libro modificado.");
                        else
                            System.out.println("Error al modificar el libro.");
                        return;
                    default:
                        System.out.println("Parámetro no valido.");
                        break;
                }
            }
        } else{
            System.out.println("Libro no encontrado");
        }
    }
}
