package no.salbjo16.exams.backend.service;

import no.salbjo16.exams.backend.StubApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
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

    private String createUser() {
        String userEmail = "TEST_EMAIL@EMAIL.COM";
        userService.createUser(userEmail, "TEST_SURNAME", "TEST_PASSWORD", "TEST_NAME");
        return userEmail;
    }

    @Test
    public void testCreateBook(){
        assertEquals(0,bookService.getAllBooks().size());
        assertNotNull(createBook());
        assertEquals(1,bookService.getAllBooks().size());
    }

    @Test
    public void testDeleteBook() {
        assertEquals(0,bookService.getAllBooks().size());
        Long bookId = createBook();
        assertEquals(1,bookService.getAllBooks().size());
        bookService.deleteBook(bookId);
        assertEquals(0,bookService.getAllBooks().size());
    }

    @Test
    public void testAddUserAsSeller() {
        Long bookId = createBook();
        String userEmail = createUser();

        assertEquals(1, bookService.getAllBooks().size());
        assertEquals(0, bookService.getAllBooksWithSellers().size());

        assertTrue(bookService.addUserAsSeller(userEmail, bookId));

        assertEquals(1, bookService.getAllBooks().size());
        assertEquals(1, bookService.getAllBooksWithSellers().size());
    }

    @Test
    public void testRemoveUserAsSeller() {
        Long bookId = createBook();
        String userEmail = createUser();

        assertEquals(1, bookService.getAllBooks().size());
        assertEquals(0, bookService.getAllBooksWithSellers().size());
        assertTrue(bookService.addUserAsSeller(userEmail, bookId));
        assertEquals(1, bookService.getAllBooks().size());
        assertEquals(1, bookService.getAllBooksWithSellers().size());
        assertTrue(bookService.removeUserAsSeller(userEmail, bookId));
        assertEquals(0, bookService.getAllBooksWithSellers().size());

    }

    @Test
    public void testGetAllBooks() {
        assertEquals(0, bookService.getAllBooks().size());
        createBook();
        assertEquals(1, bookService.getAllBooks().size());

    }


    @Test
    public void testGetAllBooksWithSellers(){
        Long bookId = createBook();
        String userEmail = createUser();

        assertEquals(0, bookService.getAllBooksWithSellers().size());
        assertTrue(bookService.addUserAsSeller(userEmail, bookId));
        assertEquals(1, bookService.getAllBooks().size());
    }
}
