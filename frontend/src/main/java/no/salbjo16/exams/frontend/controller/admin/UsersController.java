package no.salbjo16.exams.frontend.controller.admin;

import no.salbjo16.exams.backend.entity.User;
import no.salbjo16.exams.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import java.util.List;
import java.util.Set;

@Named
@RequestScoped
public class UsersController {

    @Autowired
    private UserService userService;

    private String email;
    private String password;
    private String name;
    private String surname;
    private Boolean enabled;
    private Set<String> roles;

    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }



    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public Set<String> getRoles() {
        return roles;
    }

    public void setRoles(Set<String> roles) {
        this.roles = roles;
    }
}
