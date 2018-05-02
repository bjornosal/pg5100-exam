package no.salbjo16.exams.backend.service;


import no.salbjo16.exams.backend.entity.Message;
import no.salbjo16.exams.backend.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

@Service
@Transactional
public class MessageService {

    @Autowired
    private EntityManager em;

    public Long sendMessage(String text, User sender, User receiver) {
        //Sender will always exist as it is the logged in user.
        User recipient  = em.find(User.class, receiver);
        if(recipient == null) {
            throw new IllegalArgumentException("User with email " + receiver.getEmail() + " does not exist.");
        }
        Message message = new Message();
        message.setText(text);
        message.setSender(sender);
        message.setReceiver(recipient);

        em.persist(message);
        return message.getId();
    }

}
