package no.salbjo16.exams.backend.service;

import no.salbjo16.exams.backend.entity.Book;
import no.salbjo16.exams.backend.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import javax.validation.constraints.NotNull;
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
    public void deleteBook(@NotNull Book book) {
        em.remove(em.find(Book.class, book));
    }

    public boolean addUserAsSeller(User user, Book book) {
        //Assuming sellers can not have more than one copy of a book
        //TODO this might not work, as it does not use the EM?
        Book bookToAddTo = em.find(Book.class, book);
        if (bookToAddTo.getSellers().contains(user)) {
            return false;
        } else {
            bookToAddTo.getSellers().add(user);
            return true;
        }
    }

    //TODO maybe implement id instead of book?
    //TODO does EM have to be used to remove
    public boolean removeUserAsSeller(User user, Book book) {
        Book bookToRemoveFrom = em.find(Book.class, book);
        if(bookToRemoveFrom.getSellers().contains(user)) {
            bookToRemoveFrom.getSellers().remove(user);
            return true;
        }
        return false;
    }

    public List<Book> getAllBooks() {
        TypedQuery<Book> query = em.createQuery("SELECT b from Book b", Book.class);
        return query.getResultList();
    }

    public List<Book> getAllBooksWithSellers() {
        TypedQuery<Book> query = em.createQuery("SELECT b from Book b WHERE b.sellers.size > 1", Book.class);
        return query.getResultList();
    }



}
