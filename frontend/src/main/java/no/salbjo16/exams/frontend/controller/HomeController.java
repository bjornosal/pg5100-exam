package no.salbjo16.exams.frontend.controller;

import no.salbjo16.exams.backend.entity.Book;
import no.salbjo16.exams.backend.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
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

    private Map<Long, List<String>> booksForm = new HashMap<>();

    public boolean markBook(Book book) {
        if(booksForm.get(book.getId()) != null) {

            List<String> sellers = booksForm.get(book.getId());
            boolean userIsSelling = false;

            for(String email : sellers) {
                userIsSelling = email.equalsIgnoreCase(userInfoController.getUserName());
            }

            if(userIsSelling) {
                return markAsNotSelling(book);
            } else {
                return markAsSelling(book);
            }
        }
        return markAsSelling(book);
    }

    public boolean isUserSelling(Long bookId) {
        if(booksForm.get(bookId) != null) {
            for (String email : booksForm.get(bookId)) {
                if (email.equalsIgnoreCase(userInfoController.getUserName()))
                    return true;
            }
        }
        return false;
    }

    private boolean markAsSelling(Book book) {
        String email = userInfoController.getUserName();
        List<String> sellers = new ArrayList<>();
        if(booksForm.get(book.getId()) != null) {
            sellers = booksForm.get(book.getId());
            sellers.add(email);
        } else {
            sellers.add(email);
            booksForm.put(book.getId(), sellers);
        }
        return bookService.addUserAsSeller(email, book.getId());
    }

    private boolean markAsNotSelling(Book book) {
        String email = userInfoController.getUserName();
        List<String> sellers = booksForm.get(book.getId());
        sellers.remove(email);
        booksForm.put(book.getId(),sellers);
        return bookService.removeUserAsSeller(email, book.getId());
    }

    public String toDetailPage(Book book) {
        this.book = book;

        return "/book-detail.jsf&faces-redirect=true";
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Map<Long, List<String>> getBooksForm() {
        return booksForm;
    }

    public void setBooksForm(Map<Long, List<String>> booksForm) {
        this.booksForm = booksForm;
    }
}
