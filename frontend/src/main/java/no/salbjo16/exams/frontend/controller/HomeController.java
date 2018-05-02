package no.salbjo16.exams.frontend.controller;

import no.salbjo16.exams.backend.entity.Book;
import no.salbjo16.exams.backend.service.BookService;
import no.salbjo16.exams.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Named
@RequestScoped
public class HomeController {

    @Autowired
    private BookService bookService;

    @Autowired
    private UserInfoController userInfoController;

    private Map<Long, Boolean> checksForm = new HashMap<>();



    public List<Book> getAllBooks() {
        return bookService.getAllBooks();
    }

    public boolean markAsSelling(Book book) {
        String email = userInfoController.getUserName();
        return bookService.addUserAsSeller(email, book.getId());
    }



    public Map<Long, Boolean> getChecksForm() {
        return checksForm;
    }

    public void setChecksForm(Map<Long, Boolean> checksForm) {
        this.checksForm = checksForm;
    }



}
