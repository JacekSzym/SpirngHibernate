package pl.coderslab.spirnghibernate.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.coderslab.spirnghibernate.dao.AuthorDao;
import pl.coderslab.spirnghibernate.dao.BookDAO;
import pl.coderslab.spirnghibernate.dao.PublisherDao;
import pl.coderslab.spirnghibernate.entity.Author;
import pl.coderslab.spirnghibernate.entity.Book;
import pl.coderslab.spirnghibernate.entity.Publisher;

import javax.transaction.Transactional;

@Controller
public class BookController {
    private final BookDAO bookDao;
    private final PublisherDao publisherDao;
    private final AuthorDao authorDao;


    public BookController(BookDAO bookDao, PublisherDao publisherDao, AuthorDao authorDao) {
        this.bookDao = bookDao;
        this.publisherDao = publisherDao;
        this.authorDao = authorDao;
    }


    @RequestMapping("/book/add")
    @ResponseBody
    public String addBook() {
        Book book = new Book();
        book.setRating( 9 );
        book.setDescription("Bruce Eckel");

        //na potrzeby OneToMany
        Publisher publisher = publisherDao.findById(1l);
        book.setPublisher(publisher);

        Author author1 = authorDao.findById(1);
        Author author2 = authorDao.findById(2);

        book.setAuthors(author1);
        book.setAuthors(author2);

        bookDao.saveBook(book);
        return "Id dodanej książki to:"
                + book.getId();
    }

    @RequestMapping("/bookandpublisher/add")
    @ResponseBody
    public String addBookWithPublisher(){
        Book book = new Book();

        Publisher publisher = new Publisher();
        publisher.setName("PWN");
        publisherDao.savePublisher(publisher);

        book.setDescription("Test 123 książka");
        book.setRating(10);
        book.setPublisher(publisher);
        bookDao.saveBook(book);

        return "ID: "+book.getId()+" publisher id "+publisher.getId();
    }

    @RequestMapping("/book/getbypublisher/{publisherId}")
    @ResponseBody
    @Transactional
    public String getBooksByPublisherId(@PathVariable long publisherId){
        Publisher publisher = publisherDao.findById(publisherId);
        return publisherDao.getPublisherBooks(publisher) ;
    }


    @RequestMapping("/book/get/{id}")
    @ResponseBody
    public String getBook(@PathVariable long id) {
        Book book = bookDao.findById(id);
        return book.toString();
    }


    @RequestMapping("/book/update/{id}/{description}/{rating}")
    @ResponseBody
    public String updateBook(@PathVariable long id, @PathVariable String description, @PathVariable int rating ) {
        Book book = bookDao.findById(id);
        book.setDescription(description);
        book.setRating(rating);
        bookDao.update(book);
        return book.toString();
    }

    @RequestMapping("/book/delete/{id}")
    @ResponseBody
    public String deleteBook(@PathVariable long id){
        Book book = bookDao.findById(id);
        bookDao.delete(book);
        return "usunięto książkę o id: "+id;
    }

}