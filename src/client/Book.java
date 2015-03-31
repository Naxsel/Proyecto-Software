package client;

import java.awt.*;
import java.io.Serializable;

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
    private Image img;

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
                String date, Image img){
        this.title = title;
        this.isbn = isbn;
        this.author = author;
        this.date = date;
        this.img = img;
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
     * @return img
     */
    public Image getImg(){
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
    public void setImg(Image img){
        this.img = img;
    }

    public String toString(){
        return "ISBN: " + isbn + "\nTitle: " + title + "\nAuthor: " + author + "\nGenre: " + genre + "\nDate: " + date +
                "\nImage: " + img;
    }
}
