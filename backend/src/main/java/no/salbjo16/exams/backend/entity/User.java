package no.salbjo16.exams.backend.entity;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

//Naming table as 'User' is not appreciated as table name
@Entity
@Table(name="USERS")
public class User {

    //Assuming user will use email as identifier instead of username as in previous example
    @Id
    @NotNull
    @Email
    @Column(unique = true)
    private String email;

    @NotBlank
    private String name;

    @NotBlank
    private String surname;

    @NotBlank
    private String password;

    @NotNull
    private Boolean enabled;

    @OneToMany(mappedBy = "sender", cascade =   CascadeType.ALL)
    private List<Message> sentMessages;

    @OneToMany(mappedBy = "receiver", cascade =   CascadeType.ALL)
    private List<Message> receiverMessages;


    //Needs an empty constructor
    public User() {
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
