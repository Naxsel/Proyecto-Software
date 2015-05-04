/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import Util.Book;
import Util.Books;
import Util.Request;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ruben
 */
class BooksController {
    private Connection conexion;
    
    BooksController(Connection conexion) {
        this.conexion = conexion;
    }

    void create(Books book) {
        try {
            Statement sentencia = conexion.createStatement();
            sentencia.execute("INSERT INTO books (title,isbn,author,genre,date,img,publisher,info) "
                    + "VALUES ('" + book.getTitle() + "','" + book.getIsbn() + "','" + book.getAuthor() 
                    + "','" + book.getGenre()+ "','" + book.getDate() + "','" + book.getImg() + "','" +book.getPublisher() + "','" + book.getInfo() +"')");
            
        } catch (SQLException ex) {
            Logger.getLogger(BooksController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    void delete(String isbn) throws SQLException {
        Statement sentencia = conexion.createStatement();
        sentencia.execute("DELETE FROM books WHERE isbn='" + isbn + "'");
    }

    void buscarPorISBN(String isbn) throws SQLException {
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
            System.out.println(books);
        }
    }
    
}
