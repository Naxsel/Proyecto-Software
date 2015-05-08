/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidor;

import util.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Vector;

/**
 *
 * @author Ruben
 */
class BooksController {
    private Connection conexion;

    /**
      * Constructor
      * @param conexion: Conexión con la BBDD
      */
    BooksController(Connection conexion) {
        this.conexion = conexion;
    }

    /**
      * Añade un libro a la BBDD
      * @param book: libro a añadir
      */
    void create(Books book) {
        try {
            Statement sentencia = conexion.createStatement();
            sentencia.execute("INSERT INTO books (title,isbn,author,genre,date,img,publisher,info) "
                    + "VALUES ('" + book.getTitle() + "','" + book.getIsbn() + "','" + book.getAuthor()
                    + "','" + book.getGenre()+ "','" + book.getDate() + "','" +
                    book.getImg() + "','" +book.getPublisher() + "','" + book.getInfo() +"')");

        } catch (SQLException ex) {
            Logger.getLogger(BooksController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
      * Borra un libro a la BBDD
      * @param isbn: isbn del libro a borrar
      */
    void delete(String isbn) throws SQLException {
        Statement sentencia = conexion.createStatement();
        sentencia.execute("DELETE FROM books WHERE isbn='" + isbn + "'");
    }

    /**
      * Busca un libro a la BBDD
      * @param isbn: isbn del libro a buscar
      */
    Vector<Books> buscarPorISBN(String isbn) throws SQLException {
        Vector<Books> resultado = new Vector<>();
        Statement sentencia = conexion.createStatement();
        ResultSet result = sentencia.executeQuery("SELECT * FROM books WHERE isbn='" + isbn + "'");

        while (result.next()) {
            Books books = new Books(result.getInt("id_book"),
                                    result.getString("title"),
                                    result.getString("isbn"),
                                    result.getString("author"),
                                    result.getString("genre"),
                                    result.getString("date"),
                                    result.getString("img"),
                                    result.getString("publisher"),
                                    result.getString("info"));
            resultado.add(books);
        }

        return resultado;
    }

    /**
      * Busca un libro a la BBDD
      * @param isbn: titulo del libro a buscar
      */
    Vector<Books> buscarPorTitle(String title) throws SQLException {
        Vector<Books> resultado = new Vector<>();
        Statement sentencia = conexion.createStatement();
        ResultSet result = sentencia.executeQuery("SELECT * FROM books WHERE title='" + title + "'");

        while (result.next()) {
            Books books = new Books(result.getInt("id_book"),
                                    result.getString("title"),
                                    result.getString("isbn"),
                                    result.getString("author"),
                                    result.getString("genre"),
                                    result.getString("date"),
                                    result.getString("img"),
                                    result.getString("publisher"),
                                    result.getString("info"));
            resultado.add(books);
        }

        return resultado;
    }

    /**
      * Busca un libro a la BBDD
      * @param isbn: autor del libro a buscar
      */
    Vector<Books> buscarPorAuthor(String author) throws SQLException {
        Vector<Books> resultado = new Vector<>();
        Statement sentencia = conexion.createStatement();
        ResultSet result = sentencia.executeQuery("SELECT * FROM books WHERE author='" + author + "'");

        while (result.next()) {
            Books books = new Books(result.getInt("id_book"),
                                    result.getString("title"),
                                    result.getString("isbn"),
                                    result.getString("author"),
                                    result.getString("genre"),
                                    result.getString("date"),
                                    result.getString("img"),
                                    result.getString("publisher"),
                                    result.getString("info"));
            resultado.add(books);
        }

        return resultado;
    }

    /**
      * Busca un libro a la BBDD
      * @param isbn: genero del libro a buscar
      */
      Vector<Books> buscarPorGenre(String genre) throws SQLException {
        Vector<Books> resultado = new Vector<>();
        Statement sentencia = conexion.createStatement();
        ResultSet result = sentencia.executeQuery("SELECT * FROM books WHERE genre='" + genre + "'");

        while (result.next()) {
            Books books = new Books(result.getInt("id_book"),
                                    result.getString("title"),
                                    result.getString("isbn"),
                                    result.getString("author"),
                                    result.getString("genre"),
                                    result.getString("date"),
                                    result.getString("img"),
                                    result.getString("publisher"),
                                    result.getString("info"));
            resultado.add(books);
        }

        return resultado;
    }

    /**
      * Busca un libro a la BBDD
      * @param isbn: isbn del editorial a buscar
      */
    Vector<Books> buscarPorPublisher(String publisher) throws SQLException {
        Vector<Books> resultado = new Vector<>();
        Statement sentencia = conexion.createStatement();
        ResultSet result = sentencia.executeQuery("SELECT * FROM books WHERE publisher='" + publisher + "'");

        while (result.next()) {
            Books books = new Books(result.getInt("id_book"),
                                    result.getString("title"),
                                    result.getString("isbn"),
                                    result.getString("author"),
                                    result.getString("genre"),
                                    result.getString("date"),
                                    result.getString("img"),
                                    result.getString("publisher"),
                                    result.getString("info"));
            resultado.add(books);
        }

        return resultado;
    }
    
    Vector<Books> buscarPorFechas(String fechas) throws SQLException {
        Vector<Books> resultado = new Vector<>();
        Statement sentencia = conexion.createStatement();
        String[] fecha = fechas.split("-");
        ResultSet result = sentencia.executeQuery("SELECT * FROM books WHERE "
                    + "date>='" + fecha[0] + "' and date<='" + fecha[1] + "'");

        while (result.next()) {
            Books books = new Books(result.getInt("id_book"),
                                    result.getString("title"),
                                    result.getString("isbn"),
                                    result.getString("author"),
                                    result.getString("genre"),
                                    result.getString("date"),
                                    result.getString("img"),
                                    result.getString("publisher"),
                                    result.getString("info"));
            resultado.add(books);
        }

        return resultado;
    }
    
    public void update(Books libro) {
        try {
            String updateTableSQL = "UPDATE books SET title = ?, author = ?,"
                    + " genre = ?, date = ?, img = ?, publisher = ?, info = ?," 
					+ " isbn = ? WHERE id_book = ?";
            PreparedStatement preSta = conexion.prepareStatement(updateTableSQL);
            preSta.setString(1, libro.getTitle());
            preSta.setString(2, libro.getAuthor());
            preSta.setString(3, libro.getGenre());
            preSta.setString(4, libro.getDate());
            preSta.setString(5, libro.getImg());
            preSta.setString(6, libro.getPublisher());
            preSta.setString(7, libro.getInfo());
			preSta.setString(8, libro.getIsbn());
            preSta.setInt(9, libro.getIdBook());
            preSta.execute();
            
        } catch (SQLException ex) {
            Logger.getLogger(BooksController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
