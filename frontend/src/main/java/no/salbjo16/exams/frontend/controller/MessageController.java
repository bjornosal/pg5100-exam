package no.salbjo16.exams.frontend.controller;

import no.salbjo16.exams.backend.service.MessageService;
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
    private UserInfoController userInfoController;

    private Map<String, String> messageForm = new HashMap<>();


    public void sendMessage(String recipientEmail) {
        messageService.sendMessage(messageForm.get(recipientEmail), userInfoController.getUserName(),recipientEmail);
        messageForm.put(recipientEmail, "");
    }

    public Map<String, String> getMessageForm() {
        return messageForm;
    }

    public void setMessageForm(Map<String, String> messageForm) {
        this.messageForm = messageForm;
    }
}
