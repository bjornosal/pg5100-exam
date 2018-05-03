package no.salbjo16.exams.frontend.controller;

import no.salbjo16.exams.backend.service.UserService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@RequestScoped
public class SignUpController {

    @Inject
    private UserService userService;

    @Inject
    private AuthenticationManager authenticationManager;

    @Inject
    private UserDetailsService userDetailsService;


    private String email;
    private String password;
    private String name;
    private String surname;

    public String signUpUser(){

        boolean registered = false;
        try {
            registered = userService.createUser(email, password, name ,surname);
        } catch (Exception e){
            //nothing to do
        }


        if(registered){

            UserDetails userDetails = userDetailsService.loadUserByUsername(email);
            UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
                    userDetails,
                    password,
                    userDetails.getAuthorities());

            authenticationManager.authenticate(token);

            if (token.isAuthenticated()) {
                SecurityContextHolder.getContext().setAuthentication(token);
            }

            return "/index.jsf?faces-redirect=true";
        } else {
            return "/signup.jsf?faces-redirect=true&error=true";
        }
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
}
