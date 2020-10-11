package pl.coderslab.spirnghibernate.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.coderslab.spirnghibernate.dao.AuthorDao;
import pl.coderslab.spirnghibernate.entity.Author;

import javax.transaction.Transactional;

@Controller
public class AuthorController {
    private final AuthorDao authorDao;


    public AuthorController(AuthorDao authorDao){
        this.authorDao = authorDao;
    }


    @RequestMapping("/author/add")
    @ResponseBody
    private String addAuthor(){
        Author author = new Author();
        author.setFirstName("Adam");
        author.setLastName("Micikiewicz");
        authorDao.addAuthor(author);
        return author.toString();
    }

    @RequestMapping("/author/get/{id}")
    @ResponseBody
    private String getAuthor(@PathVariable long id){
        Author author = authorDao.findById(id);
        return author.toString();
    }


    @RequestMapping("/author/getBooks/{id}")
    @ResponseBody
    @Transactional
    public String getAuthorBooks(@PathVariable long id){
        Author author = authorDao.findById(id);
        return authorDao.getAuthorBooks(author);
    }


    @RequestMapping("/author/update/{id}/{firstName}/{lastName}")
    @ResponseBody
    private String updateAuthor(@PathVariable long id, @PathVariable String firstName, @PathVariable String lastName){
        Author author = authorDao.findById(id);
        author.setFirstName(firstName);
        author.setLastName(lastName);
        authorDao.updateAuthor(author);
        return author.toString();
    }

    @RequestMapping("/author/delete/{id}")
    @ResponseBody
    private String deleteAuthor(@PathVariable long id){
        Author author = authorDao.findById(id);
        authorDao.deleteAuthor(author);
        return "Usunięto autora o id" + id;
    }

}
