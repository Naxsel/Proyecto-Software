package admin;

import java.io.IOException;
import java.util.Scanner;
import java.lang.NumberFormatException;
import util.*;
import java.util.Vector;

/**
 * Created by agustin on 1/4/15.
 */
public class console {

    private static ClientSend server;

	/**
	  * Ejecuta la consola del cliente.
	  * @param args[0] : Dirección ip del servidor
	  * @param args[1] : Puerto del servidor
	  */
    public static void main(String args[]) {
        Scanner input = new Scanner(System.in);
        String action;
		if (args.length != 2){
			System.out.println("Parametros erroneos, dirección del servidor "
				+ "puerto.");
			return;
		}

        try {
            server = new ClientSend(args[0], Integer.parseInt(args[1]));
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
					case "q":
						System.out.println("Adios. :)");
						return;
                    default:
                        System.out.println("Comando no reconocido.");
                        break;
                }
            }
        } catch (IOException e){
            System.out.println("Problema con el servidor.");
            return;
        } catch (ClassNotFoundException e){
            System.out.println("Problema con el servidor.");
            return;
        } catch	(NumberFormatException e){
			System.out.println("Problema con el puerto dado.");
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
		System.out.println("q - salir del programa.");
    }

    /**
     * Add a Books
     */
    public static void add() throws IOException, ClassNotFoundException {
        Scanner input = new Scanner(System.in);
        String title, isbn, date, author, publisher, genre, description, ruta;
        Books libro;
        Request<Books> req;
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

        libro = new Books(title, isbn, author, genre, date, ruta, publisher, description);
        req = new Request<>(Request.TypeRequest.ADD, libro);

        //Send Books to server
        reply = server.send2Server(req);

        if(reply.getReply() == Reply.TypeReply.OK){
            System.out.println("Libro añadido");
        } else{
            System.out.println("Ha ocurrido un error.");
        }

    }

    /**
     * Delete a Books
     */
    public static void erase() throws IOException, ClassNotFoundException {
        Scanner input = new Scanner(System.in);
        String isbn;
        Request request;
        Reply reply;
		Vector<Books> devuelto;

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
        if (reply.getReply() == Reply.TypeReply.VECTOR){
            devuelto = (Vector) reply.getDummy();
			if (devuelto.size() == 0){
				System.out.println("No se ha encontrado el libro.");
				return;
			}
		}
        request = new Request(Request.TypeRequest.DELETE, isbn);
        reply = server.send2Server(request);

        if (reply.getReply() == Reply.TypeReply.OK){
            System.out.println("Libro borrado correctamente.");
        } else{
            System.out.println("El libro no se pudo borrar correctamente");
        }
    }

    /**
     * Edit a Books's information
     */
    public static void edit() throws IOException, ClassNotFoundException {
        Scanner input = new Scanner(System.in);
        String isbn, type, change;
        Request request;
        Reply reply;
        Books libro;
		Vector<Books> devuelto;

        System.out.print("ISBN del libro a modificar: ");
        isbn = input.nextLine();
        while(isbn == null || isbn.equals("")){
            System.out.println("ISBN erroneo");
            System.out.print("ISBN del libro a borrar: ");
            isbn = input.nextLine();
        }

        //Busca el libro
        request = new Request(Request.TypeRequest.ISBN, isbn);
        reply = server.send2Server(request);
        if (reply.getReply() == Reply.TypeReply.VECTOR){
            devuelto = (Vector) reply.getDummy();
			if (devuelto.size() == 0){
				System.out.println("No se ha encontrado el libro.");
				return;
			}
			
			libro = devuelto.firstElement();
            //Pide la nueva info del libro
            while(true) {
                System.out.print("Parámetro a cambiar (TITULO, AUTOR, EDITOR,"
						+ " DESCRIPCION, GENERO, FECHA, ISBN, IMAGEN)" +
                        ", FIN para acabar: ");
                type = input.nextLine();
                while(type == null || type.equals("")){
                    System.out.println("Parámetro no valido.");
                    System.out.print("Parámetro a cambiar (TITULO, AUTOR, EDITOR,"
							+ " DESCRIPCION, GENERO, FECHA, ISBN, IMAGEN)" +
                            ", FIN para acabar: ");
                    type = input.nextLine();
                }
				if (!type.equals("FIN")){
                	System.out.print("Nuevo valor: ");
                	change = input.nextLine();
                	while(change == null || change.equals("")){
                   		System.out.println("Valor no valido.");
                	    System.out.print("Nuevo valor: ");
                	    change = input.nextLine();
                	}
				}
				else change="";
                switch (type){
                    case "TITULO" :
                        libro.setTitle(change);
                        break;
                    case "AUTOR" :
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
						try{
							Integer.parseInt(change);
                        } catch(NumberFormatException e){
							System.out.println("La fecha es un número, negativo"
								+ " si es de antes de cristo.");
							break;
						}
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
