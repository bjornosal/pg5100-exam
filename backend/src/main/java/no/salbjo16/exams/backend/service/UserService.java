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


    public boolean createUser(String username, String password){
        String hashedPassword = passwordEncoder.encode(password);

        if(em.find(User.class, username) != null) {
            return false;
        }

        User user = new User();
        user.setUsername(username);
        user.setPassword(hashedPassword);
        user.setRoles(Collections.singleton("USER"));
        user.setEnabled(true);

        em.persist(user);

        return true;
    }
}

/*

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

}
*/
