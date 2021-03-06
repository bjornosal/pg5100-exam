package no.salbjo16.exams.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Service
public class DefaultDataInitializerService {


    @Autowired
    private BookService bookService;

    @Autowired
    private UserService userService;

    @PostConstruct
    public void initialize() {
        List<String> algorithmsAuthors = new ArrayList<>();
        algorithmsAuthors.add("Robert Sedgewick");

        List<String> javaEEAuthors = new ArrayList<>();
        javaEEAuthors.add("Antonio Goncalves");

        bookService.createBook("Algorithms", algorithmsAuthors,"Algorithms and Datastructures");
        bookService.createBook("Beginning Java EE 7", javaEEAuthors, "Enterprise Programming 1");

        userService.createAdmin("admin@book.com", "admin", "Super", "User");
    }
}
