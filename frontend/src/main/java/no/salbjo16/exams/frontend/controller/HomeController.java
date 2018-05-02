package no.salbjo16.exams.frontend.controller;

import no.salbjo16.exams.backend.entity.Book;
import no.salbjo16.exams.backend.service.BookService;
import no.salbjo16.exams.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import java.util.List;

@Named
@RequestScoped
public class HomeController {

    @Autowired
    private BookService bookService;


    public List<Book> getAllBooks() {
        System.out.println("STORRELSE: "+bookService.getAllBooks().size());
        return bookService.getAllBooks();
    }



}
