package no.salbjo16.exams.frontend.controller;

import no.salbjo16.exams.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@RequestScoped
public class UserInfoController {

    @Autowired
    private UserService userService;

    public String getUserName(){
        return ((UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
    }

    public String getFullNameOfCurrentUser() {
        return userService.getFullNameByEmail(getUserName());
    }

    public String getFullNameByEmail(String email) {
        return userService.getFullNameByEmail(email);
    }

}
