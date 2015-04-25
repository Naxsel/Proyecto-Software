/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidorchat;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Ruben
 */
public class Main {
    public static void main(String[] a) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ServidosPU");
        EntityManager em = emf.createEntityManager();
        BooksJpaController service =  new BooksJpaController(emf);
        em.getTransaction().begin();
        Books libro =  new Books();
        libro.setTitle("Prueba");
        libro.setDate("12/05/1989");
        libro.setAuthor("Ruben");
        libro.setGenre("Masculino");
        libro.setIsbn("sasa2121");
        libro.setPublisher("sasa");
        libro.setInfo("Op libro tu");
        service.create(libro);
        em.getTransaction().commit();
        System.out.println("Persisted" + libro);
        em.close();
        emf.close();
    }
} 
