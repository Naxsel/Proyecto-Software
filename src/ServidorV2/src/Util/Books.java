/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Util;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Ruben
 */
@Entity
@Table(name = "books")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Books.findAll", query = "SELECT b FROM Books b"),
    @NamedQuery(name = "Books.findByIdBook", query = "SELECT b FROM Books b WHERE b.idBook = :idBook"),
    @NamedQuery(name = "Books.findByTitle", query = "SELECT b FROM Books b WHERE b.title = :title"),
    @NamedQuery(name = "Books.findByIsbn", query = "SELECT b FROM Books b WHERE b.isbn = :isbn"),
    @NamedQuery(name = "Books.findByAuthor", query = "SELECT b FROM Books b WHERE b.author = :author"),
    @NamedQuery(name = "Books.findByGenre", query = "SELECT b FROM Books b WHERE b.genre = :genre"),
    @NamedQuery(name = "Books.findByDate", query = "SELECT b FROM Books b WHERE b.date = :date"),
    @NamedQuery(name = "Books.findByImg", query = "SELECT b FROM Books b WHERE b.img = :img"),
    @NamedQuery(name = "Books.findByPublisher", query = "SELECT b FROM Books b WHERE b.publisher = :publisher")})
public class Books implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_book")
    private Integer idBook;
    @Basic(optional = false)
    @Column(name = "title")
    private String title;
    @Column(name = "isbn")
    private String isbn;
    @Column(name = "author")
    private String author;
    @Column(name = "genre")
    private String genre;
    @Column(name = "date")
    private String date;
    @Column(name = "img")
    private String img;
    @Column(name = "publisher")
    private String publisher;
    @Lob
    @Column(name = "info")
    private String info;

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
    public String toString() {
        return "Util.Books[ idBook=" + idBook + " ]";
    }
    
}
