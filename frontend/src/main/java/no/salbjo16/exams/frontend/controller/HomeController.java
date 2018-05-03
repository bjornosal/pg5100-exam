package no.salbjo16.exams.frontend.controller;

import no.salbjo16.exams.backend.entity.Book;
import no.salbjo16.exams.backend.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Named
@ApplicationScoped
public class HomeController implements Serializable{

    @Autowired
    private BookService bookService;

    @Autowired
    private UserInfoController userInfoController;


    private Book book;

    //TODO this is at fault?
    //TODO create service method which creates a hashmap with books that current user is selling?
    private Map<Long, Boolean> checksForm = new HashMap<>();

    public List<Book> getAllBooks() {
        return bookService.getAllBooks();
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

    public String toDetailPage(Book book) {
        this.book = book;

        return "/ui/book-detail.jsf&faces-redirect=true";
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Map<Long, Boolean> getChecksForm() {
        return checksForm;
    }

    public void setChecksForm(Map<Long, Boolean> checksForm) {
        this.checksForm = checksForm;
    }



}
