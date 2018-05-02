package no.salbjo16.exams.backend.service;

import no.salbjo16.exams.backend.entity.Book;
import no.salbjo16.exams.backend.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.List;

@Service
@Transactional
public class BookService {

    @Autowired
    private EntityManager em;

    public Long createBook(@NotNull String title, @NotNull List<String> authors, @NotNull String course) {
        Book book = new Book();
        book.setTitle(title);
        book.setAuthors(authors);
        book.setCourse(course);
        em.persist(book);
        return book.getId();
    }

    /*
    Assuming I will have access to a book when deleting. (Admin?)
     */
    public void deleteBook(@NotNull Long bookId) {
        em.remove(em.find(Book.class, bookId));
    }

    public boolean addUserAsSeller(String userMail, Long bookId) {
        //Assuming sellers can not have more than one copy of a book
        Book book = em.find(Book.class, bookId);
        User user = em.find(User.class, userMail);
        if (book.getSellers().contains(user)) {
            return false;
        } else {
            book.getSellers().add(user);
            return true;
        }
    }

    public boolean removeUserAsSeller(String userEmail, Long bookId) {
        Book book = em.find(Book.class, bookId);
        User user = em.find(User.class, userEmail);
        if(book.getSellers().contains(user)) {
            book.getSellers().remove(user);
            return true;
        }
        return false;
    }

    public List<Book> getAllBooks() {
        TypedQuery<Book> query = em.createQuery("SELECT b from Book b", Book.class);
        return query.getResultList();
    }

    public List<Book> getAllBooksWithSellers() {
        TypedQuery<Book> query = em.createQuery("SELECT b from Book b WHERE b.sellers.size >= 1", Book.class);
        return query.getResultList();
    }

    //TODO remove this
/*
    public HashMap<Long, Boolean> getAllBooksThatUserIsSelling(String email) {

        HashMap<Long, Boolean> booksForSale = new HashMap<>();

        List<Book> books = getAllBooks();
        for(Book book : books) {
            for(User user : book.getSellers()) {
                if(user.getEmail().equals(email)) {
                    booksForSale.put(book.getId(), true);
                }
            }
        }
        return booksForSale;
    }*/


}
