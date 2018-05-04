package no.salbjo16.exams.frontend.controller.admin;

import no.salbjo16.exams.backend.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Named
@RequestScoped
public class AdminController {

    @Autowired
    private BookService bookService;

    private String title;
    private List<String> authors;
    private String authorsString;
    private String course;

    public String createBook() {
        if(title.isEmpty() || !(title.trim().length() > 0) || course.isEmpty() || !(title.trim().length() > 0))
            return "/admin/book-registry.jsf?faces-redirect=true&error=true";

        authors = new ArrayList<>(Arrays.asList(authorsString.split(",")));
        bookService.createBook(title, authors, course);

        return "/admin/book-registry.jsf?faces-redirect=true&success=true";
    }

    public void deleteBook(Long bookId) {
        bookService.deleteBook(bookId);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<String> getAuthors() {
        return authors;
    }

    public void setAuthors(List<String> authors) {
        this.authors = authors;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getAuthorsString() {
        return authorsString;
    }

    public void setAuthorsString(String authorsString) {
        this.authorsString = authorsString;
    }
}
