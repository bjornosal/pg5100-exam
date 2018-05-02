package no.salbjo16.exams.backend.service;

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


    public boolean createUser(String name, String surname, String password, String email){
        String hashedPassword = passwordEncoder.encode(password);

        if(em.find(User.class, email) != null) {
            return false;
        }

        User user = new User();
        user.setName(name);
        user.setSurname(surname);
        user.setPassword(hashedPassword);
        user.setEmail(email);
        user.setEnabled(true);

        em.persist(user);

        return true;
    }

    //TODO is this required?
    public String getUser(String email) {
        TypedQuery<User> query = em.createQuery("SELECT u from User u WHERE u.email =?1", User.class);
        query.setParameter(1, email);

        List<User> userResults = query.getResultList();
        if(!userResults.isEmpty()) {
            return userResults.get(0).getEmail();
        }

        return null;
    }
}
