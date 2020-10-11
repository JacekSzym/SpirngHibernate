package pl.coderslab.spirnghibernate.entity;


import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "books")
public class Book {


    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private long id;
    private int rating;
    private String description;
    @ManyToMany
    private List<Author> authors = new ArrayList<>();
    @ManyToOne
    private Publisher publisher;



    public Book(){

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Publisher getPublisher() {
        return publisher;
    }

    public void setPublisher(Publisher publisher) {
        this.publisher = publisher;
    }

    public List<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(Author author) {
        this.authors.add(author);
    }

    @Override
    public String toString() {
        return "Twoja książka to: "+this.description+" oceniona na: "+this.rating+" o ID: "+this.id+" wydawca "+this.publisher.getName();
    }
}
