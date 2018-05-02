package no.salbjo16.exams.backend.service;

import no.salbjo16.exams.backend.StubApplication;
import no.salbjo16.exams.backend.entity.Book;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = StubApplication.class,
        webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class BookServiceTest extends ServiceTestBase {

    @Autowired
    private BookService bookService;

    @Autowired
    private UserService userService;

    private Long createBook() {
        List<String> authors = new ArrayList<>();
        authors.add("TEST_AUTHOR_ONE");
        authors.add("TEST_AUTHOR_TWO");
        return bookService.createBook("TEST_BOOK",authors, "TEST_COURSE");
    }

    @Test
    public void testCreateBook(){
        assertNotNull(createBook());
    }

    @Test
    public void testDeleteBook() {

    }


}
