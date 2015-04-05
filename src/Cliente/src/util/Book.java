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

/**
 * Book object
 * Created by agustin on 12/3/15.
 */
public class Book implements Serializable {
    //Atributes
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
    public Book(String title, String isbn, String author, String genre,
                String date, String img, String publisher, String info){
        this.title = title;
        this.isbn = isbn;
        this.author = author;
        this.date = date;
        this.img = img;
        this.genre = genre;
        this.publisher = publisher;
        this.info = info;
    }

    /**
     *
     * @return title
     */
    public String getTitle(){
        return title;
    }

    /**
     *
     * @return isbn
     */
    public String getIsbn(){
        return isbn;
    }

    /**
     *
     * @return author
     */
    public String getAuthor(){
        return author;
    }

    /**
     *
     * @return genre
     */
    public String getGenre(){
        return genre;
    }

    /**
     *
     * @return date
     */
    public String getDate(){
        return date;
    }

    /**
     *
     * @return publisher
     */
    public String getPublisher(){
        return publisher;
    }

    /**
     *
     * @return info
     */
    public String getInfo(){
        return info;
    }
    /**
     *
     * @return img
     */
    public String getImg(){
        return img;
    }

    /**
     *
     * @param title = book's title
     */
    public void setTitle(String title){
        this.title = title;
    }

    /**
     *
     * @param isbn = book's isbn
     */
    public void setIsbn(String isbn){
        this.isbn = isbn;
    }

    /**
     *
     * @param author = book's isbn
     */
    public void setAuthor(String author){
        this.author = author;
    }

    /**
     *
     * @param genre = book's genre
     */
    public void setGenre(String genre){
        this.genre = genre;
    }

    /**
     *
     * @param date = book's date
     */
    public void setDate(String date){
        this.date = date;
    }

    /**
     *
     * @param img = book's image
     */
    public void setImg(String img){
        this.img = img;
    }

    /**
     *
     * @param publisher = book's publisher
     */
    public void setPublisher(String publisher){
        this.publisher = publisher;
    }

    /**
     *
     * @param info = book's info
     */
    public void setInfo(String info){
        this.info = info;
    }

    public String toString(){
        return "ISBN: " + isbn + "\nTitle: " + title + "\nAuthor: " + author + "\nGenre: " + genre + "\nDate: " + date +
                "\nImage: " + img + "\nEditor: " + publisher + "\nDescripción: " + info;
    }
}