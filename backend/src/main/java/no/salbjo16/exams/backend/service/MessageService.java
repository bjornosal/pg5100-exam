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

    public Long sendMessage(String messageText, String senderEmail, String receiverEmail) {
        User sender = em.find(User.class, senderEmail);
        User recipient  = em.find(User.class, receiverEmail);
        if(recipient == null) {
            throw new IllegalArgumentException("User with email " + receiverEmail + " does not exist.");
        }
        Message message = new Message();
        message.setText(messageText);
        message.setSender(sender);
        message.setRecipient(recipient);

        em.persist(message);
        return message.getId();
    }

}
