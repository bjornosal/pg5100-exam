package no.salbjo16.exams.frontend.controller;

import no.salbjo16.exams.backend.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.annotation.SessionScope;

import javax.inject.Named;

@Named
@SessionScope
public class MessageController {

    @Autowired
    private MessageService messageService;

    @Autowired
    private UserInfoController userInfoController;

    public void sendMessage(String email) {
    }
}
