package pl.coderslab.spirnghibernate.dao;


import org.hibernate.Hibernate;
import org.springframework.stereotype.Repository;
import pl.coderslab.spirnghibernate.entity.Author;
import pl.coderslab.spirnghibernate.entity.Publisher;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Repository
@Transactional
public class AuthorDao {

    @PersistenceContext
    EntityManager entityManager;


    public void addAuthor(Author author){
        entityManager.persist(author);
    }

    public Author findById(long id){
        return entityManager.find(Author.class, id);
    }

    public void updateAuthor(Author author){
        entityManager.merge(author);
    }

    public void deleteAuthor(Author author){
        entityManager.remove(entityManager.contains(author) ?
                author : entityManager.merge(author)); }

    public String getAuthorBooks(Author author){
        Hibernate.initialize(author.getBooks() );

        return author.getBooks().toString();
    }

}

