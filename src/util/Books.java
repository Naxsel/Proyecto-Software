/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.io.Serializable;
import java.net.URL;

public class Books implements Serializable {

	//Atributos
    private Integer idBook;
    private String title;
    private String isbn;
    private String author;
    private String genre;
    private String date;
    private String img;
    private String publisher;
    private String info;

    /**
     *
     * @param title = book's title
     * @param isbn = book's isbn
     * @param author = book's author
     * @param genre = book's genre
     * @param date = book's date
     * @param img = book's img
     */
    public Books(String title, String isbn, String author, String genre,
		String date, String img, String publisher, String info) {
        this.title = title;
        this.isbn = isbn;
        this.author = author;
        this.genre = genre;
        this.date = date;
        this.img = img;
        this.publisher = publisher;
        this.info = info;
    }

    /**
     *
     * @param title = book's title
     * @param isbn = book's isbn
     * @param author = book's author
     * @param genre = book's genre
     * @param date = book's date
     * @param img = book's img
     */
    public Books(Integer idBook, String title, String isbn, String author,
		String genre, String date, String img, String publisher, String info) {
        this.idBook = idBook;
		this.title = title;
        this.isbn = isbn;
        this.author = author;
        this.genre = genre;
        this.date = date;
        this.img = img;
        this.publisher = publisher;
        this.info = info;
    }

    /**
      *
      * @param idBook: this.idBook = param
      */
    public Books(Integer idBook) {
        this.idBook = idBook;
    }

    /**
      *
      * @param idBook: this.idBook = param
      */
    public Books(Integer idBook, String title) {
        this.idBook = idBook;
        this.title = title;
    }

    /**
      *
      * @return idBook
      */
    public Integer getIdBook() {
        return idBook;
    }

    /**
      *
      * @param idBook: this.idBook = param
      */
    public void setIdBook(Integer idBook) {
        this.idBook = idBook;
    }

    /**
     *
     * @return title
     */
    public String getTitle() {
        return title;
    }

    /**
      *
      * @param title: this.title = param
      */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     *
     * @return isbn
    */
    public String getIsbn() {
        return isbn;
    }

    /**
      *
      * @param isbn: this.isbn = param
      */
    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    /**
     *
     * @return author
     */
    public String getAuthor() {
        return author;
    }

    /**
      *
      * @param author: this.author = param
      */
    public void setAuthor(String author) {
        this.author = author;
    }

    /**
     *
     * @return genre
     */
    public String getGenre() {
        return genre;
    }

    /**
      *
      * @param genre: this.genre = param
      */
    public void setGenre(String genre) {
        this.genre = genre;
    }


    /**
     *
     * @return date
     */
    public String getDate() {
        return date;
    }

    /**
      *
      * @param date: this.date = param
      */
    public void setDate(String date) {
        this.date = date;
    }


    /**
     *
     * @return img
     */
    public String getImg() {
        return img;
    }

    /**
      *
      * @param img: this.img = param
      */
    public void setImg(String img) {
        this.img = img;
    }


    /**
     *
     * @return publisher
     */
    public String getPublisher() {
        return publisher;
    }

    /**
      *
      * @param publisher: this.publisher = param
      */
    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }


    /**
     *
     * @return info
     */
    public String getInfo() {
        return info;
    }

    /**
      *
      * @param info: this.info = param
      */
    public void setInfo(String info) {
        this.info = info;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idBook != null ? idBook.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Books)) {
            return false;
        }
        Books other = (Books) object;
        if ((this.idBook == null && other.idBook != null) || (this.idBook != null && !this.idBook.equals(other.idBook))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString(){
        return "ISBN: " + isbn + "\nTitle: " + title + "\nAuthor: " + author +
                "\nGenre: " + genre + "\nDate: " + date +
                "\nImage: " + img + "\nEditor: " + publisher + "\nDescripción: " + info;
    }

    /**
     * A simple test
     */
    public static void main(String args[]) throws IOException {
        Books dummy = new Books("T-Title","T-ISBN","T-Author","T-Genre","T-Date",
            "http://pythoniza.me/wp-content/uploads/2014/10/ibHNQU.png","T-Publisher","T-Info");
        URL url = new URL(dummy.getImg());
        //Read a image from Url
        //Throws MalFormedURLExcaption
        Image img = ImageIO.read(url);
        JFrame frame = new JFrame();
        frame.setSize(600, 800);
        JLabel label = new JLabel(new ImageIcon(img));
        frame.add(label);
        frame.setVisible(true);
        System.out.print(dummy);
    }

}
