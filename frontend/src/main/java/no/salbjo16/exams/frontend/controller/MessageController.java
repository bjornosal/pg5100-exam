package no.salbjo16.exams.frontend.controller;

import no.salbjo16.exams.backend.entity.Message;
import no.salbjo16.exams.backend.service.MessageService;
import no.salbjo16.exams.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.annotation.SessionScope;

import javax.inject.Named;
import java.util.*;

@Named
@SessionScope
public class MessageController {

    @Autowired
    private MessageService messageService;

    @Autowired
    private UserService userService;

    @Autowired
    private UserInfoController userInfoController;

    @Autowired
    private HomeController homeController;

    private Map<String, String> messageForm = new HashMap<>();


    public String sendMessage(String recipientEmail) {
        if(messageForm.get(recipientEmail).isEmpty()) {
            return "/ui/book-detail.jsf?faces-redirect=true&error=true";
        } else {
            messageService.sendMessage(messageForm.get(recipientEmail), userInfoController.getUserName(), recipientEmail);
            messageForm.put(recipientEmail, "");
            return "/ui/book-detail.jsf?faces-redirect=true&success=true";
        }
    }

    public List<Message> getSentMessages() {
        return userService.getSentMessages(userInfoController.getUserName());
    }

    public List<Message> getReceivedMessages() {
        return userService.getReceivedMessages(userInfoController.getUserName());
    }

    public boolean isSellerNotCurrentUser(String email){
        return !userInfoController.getUserName().equalsIgnoreCase(email);
    }

    public boolean isSellerOnlyCurrentUser() {
        return (homeController.getBook().getSellers().size() == 1)
                && homeController.getBook().getSellers().get(0).getEmail().equalsIgnoreCase(userInfoController.getUserName());
    }

    public Map<String, String> getMessageForm() {
        return messageForm;
    }

    public void setMessageForm(Map<String, String> messageForm) {
        this.messageForm = messageForm;
    }
}
