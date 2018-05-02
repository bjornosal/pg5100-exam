package no.salbjo16.exams.frontend.controller;

import no.salbjo16.exams.backend.entity.Book;
import no.salbjo16.exams.backend.service.BookService;
import no.salbjo16.exams.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Named
@SessionScoped
public class HomeController implements Serializable{

    @Autowired
    private BookService bookService;

    @Autowired
    private UserInfoController userInfoController;

    private Map<Long, Boolean> checksForm = new HashMap<>();



    public List<Book> getAllBooks() {
        return bookService.getAllBooks();
    }

    public List<Book> getAllBooksWithSellers() {
        return bookService.getAllBooksWithSellers();
    }

    public boolean markBook(Book book) {
        if(checksForm.get(book.getId()) != null) {

            if(checksForm.get(book.getId())) {
                return markAsNotSelling(book);
            } else {
                return markAsSelling(book);
            }
        }
        return markAsSelling(book);
    }


    private boolean markAsSelling(Book book) {
        String email = userInfoController.getUserName();
        checksForm.put(book.getId(), true);
        System.out.println(book.getId());
        return bookService.addUserAsSeller(email, book.getId());

    }

    private boolean markAsNotSelling(Book book) {
        String email = userInfoController.getUserName();
        checksForm.put(book.getId(), false);
        System.out.println(book.getId());
        return bookService.removeUserAsSeller(email, book.getId());

    }

    public Map<Long, Boolean> getChecksForm() {
        return checksForm;
    }

    public void setChecksForm(Map<Long, Boolean> checksForm) {
        this.checksForm = checksForm;
    }



}
