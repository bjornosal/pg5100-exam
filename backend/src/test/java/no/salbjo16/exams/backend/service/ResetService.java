package no.salbjo16.exams.backend.service;

import no.salbjo16.exams.backend.entity.Book;
import no.salbjo16.exams.backend.entity.Message;
import no.salbjo16.exams.backend.entity.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Service
@Transactional
public class ResetService {

    @PersistenceContext
    private EntityManager em;


    public void resetDatabase() {
        Query query = em.createNativeQuery("DELETE FROM book_authors");
        query.executeUpdate();

        deleteEntities(Message.class);
        deleteEntities(Book.class);
        deleteEntities(User.class);
    }

    private void deleteEntities(Class<?> entity){
        if(entity == null || entity.getAnnotation(Entity.class) == null){
            throw new IllegalArgumentException("Invalid entity class");
        }

        String entityName = entity.getName();

        Query query = em.createQuery("DELETE FROM "+ entityName);
        query.executeUpdate();
    }
}
