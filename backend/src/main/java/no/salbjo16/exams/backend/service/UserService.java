package no.salbjo16.exams.backend.service;

import no.salbjo16.exams.backend.entity.Message;
import no.salbjo16.exams.backend.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.Collections;
import java.util.List;

@Service
@Transactional
public class UserService {

    @Autowired
    EntityManager em;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public boolean createUser(String email, String password, String name, String surname){
        String hashedPassword = passwordEncoder.encode(password);

        if(em.find(User.class, email) != null) {
            return false;
        }

        User user = new User();
        user.setEmail(email);

        user.setPassword(hashedPassword);
        user.setRoles(Collections.singleton("USER"));
        user.setEnabled(true);
        user.setName(name);
        user.setSurname(surname);
        em.persist(user);

        return true;
    }

    public String getFullNameByEmail(String email) {
        String name = em.find(User.class, email).getName();
        String surname = em.find(User.class, email).getSurname();
        return name + " " + surname;
    }

    public List<Message> getSentMessages(String email) {
        TypedQuery<Message> query = em.createQuery("SELECT m FROM Message m WHERE m.sender.email = ?1", Message.class);
        query.setParameter(1, email);

        return query.getResultList();
    }

    public List<Message> getReceivedMessages(String email) {
        TypedQuery<Message> query = em.createQuery("SELECT m FROM Message m WHERE m.recipient.email = ?1", Message.class);
        query.setParameter(1, email);

        return query.getResultList();
    }
}