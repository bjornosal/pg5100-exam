package no.salbjo16.exams.frontend.controller;

import no.salbjo16.exams.backend.entity.Message;
import no.salbjo16.exams.backend.service.MessageService;
import no.salbjo16.exams.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.*;

@Named
@SessionScoped
public class MessageController implements Serializable {

    @Autowired
    private MessageService messageService;

    @Autowired
    private UserService userService;

    @Autowired
    private UserInfoController userInfoController;


    private Map<String, String> messageForm = new HashMap<>();


    public String sendMessage(String recipientEmail) {
        if(messageForm.get(recipientEmail).isEmpty()) {
            return "/book-detail.jsf?faces-redirect=true&error=true";
        } else {
            messageService.sendMessage(messageForm.get(recipientEmail), userInfoController.getUserName(), recipientEmail);
            messageForm.put(recipientEmail, "");
            return "/book-detail.jsf?faces-redirect=true&success=true";
        }
    }

    public String sendReply(String recipientEmail) {
        if(messageForm.get(recipientEmail).isEmpty()) {
            return "/ui/message.jsf?faces-redirect=true&error=true";
        } else {
            messageService.sendMessage(messageForm.get(recipientEmail), userInfoController.getUserName(), recipientEmail);
            messageForm.put(recipientEmail, "");
            return "/ui/message.jsf?faces-redirect=true&success=true";
        }
    }

    public List<Message> getSentMessages() {
        return userService.getSentMessages(userInfoController.getUserName());
    }

    public List<Message> getReceivedMessages() {
        return userService.getReceivedMessages(userInfoController.getUserName());
    }

    public Map<String, String> getMessageForm() {
        return messageForm;
    }

    public void setMessageForm(Map<String, String> messageForm) {
        this.messageForm = messageForm;
    }
}
