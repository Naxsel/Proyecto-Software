/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Util;

import java.io.Serializable;

/**
 *
 * @author Ruben
 */

public class Books implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer idBook;
    private String title;
    private String isbn;
    private String author;
    private String genre;
    private String date;
    private String img;
    private String publisher;
    private String info;

    public Books(Integer idBook, String title, String isbn, String author, String genre, String date, String img, String publisher, String info) {
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
    
    public Books() {
    }

    public Books(Integer idBook) {
        this.idBook = idBook;
    }

    public Books(Integer idBook, String title) {
        this.idBook = idBook;
        this.title = title;
    }

    public Integer getIdBook() {
        return idBook;
    }

    public void setIdBook(Integer idBook) {
        this.idBook = idBook;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getInfo() {
        return info;
    }

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
        return "ISBN: " + isbn + "\nTitle: " + title + "\nAuthor: " + author + "\nGenre: " + genre + "\nDate: " + date +
                "\nImage: " + img + "\nEditor: " + publisher + "\nDescripción: " + info;
    }
    
}
