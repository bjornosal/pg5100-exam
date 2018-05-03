package no.salbjo16.exams.backend.entity;


import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.Set;

//Naming table as 'User' is not appreciated as table name
@Entity
@Table(name="USERS")
public class User {

    @Id
    @NotBlank
    @Email
    private String email;

    @NotBlank
    @Size(min = 1, max = 1024)
    private String name;

    @NotBlank
    @Size(min = 1, max = 1024)
    private String surname;

    @NotBlank
    @Size(min = 5, max = 1024)
    private String password;

    @ElementCollection(fetch = FetchType.EAGER)
    private Set<String> roles;

    @NotNull
    private Boolean enabled;

    @OneToMany(mappedBy = "sender", cascade =   CascadeType.ALL)
    private List<Message> sentMessages;

    @OneToMany(mappedBy = "recipient", cascade =   CascadeType.ALL)
    private List<Message> receivedMessages;

    //Needs an empty constructor
    public User() {
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

    public Set<String> getRoles() {
        return roles;
    }

    public void setRoles(Set<String> roles) {
        this.roles = roles;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public List<Message> getSentMessages() {
        return sentMessages;
    }

    public void setSentMessages(List<Message> sentMessages) {
        this.sentMessages = sentMessages;
    }

    public List<Message> getReceivedMessages() {
        return receivedMessages;
    }

    public void setReceivedMessages(List<Message> receiverMessages) {
        this.receivedMessages = receiverMessages;
    }
}