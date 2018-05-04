package no.salbjo16.exams.frontend.controller.admin;

import no.salbjo16.exams.backend.entity.User;
import no.salbjo16.exams.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import java.util.List;

@Named
@RequestScoped
public class UsersController {

    @Autowired
    private UserService userService;

    private String email;

    private String name;


    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    public void changeEnabled(String email) {
        userService.changeEnabled(email);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
