package no.salbjo16.exams.backend.service;

import no.salbjo16.exams.backend.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.Collections;

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
}