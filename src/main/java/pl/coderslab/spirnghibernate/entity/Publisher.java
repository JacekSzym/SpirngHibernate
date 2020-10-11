package pl.coderslab.spirnghibernate.entity;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "publishers")
public class Publisher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    @OneToMany (mappedBy = "publisher")
    private List<Book> books = new ArrayList<>();

    public Publisher(){

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String firstName) {
        this.name = firstName;
    }

    public List<Book> getBooks() {
        return books;
    }


    @Override
    public String toString() {
        return "ID: "+this.id+" Name: "+this.name;
    }
}
