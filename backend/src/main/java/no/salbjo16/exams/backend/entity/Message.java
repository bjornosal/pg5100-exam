package no.salbjo16.exams.backend.entity;

import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Message {

    @GeneratedValue @Id
    private Long id;

    @NotBlank
    @Size(min = 1, max = 4096)
    private String text;

    @NotNull
    @ManyToOne
    private User sender;

    @NotNull
    @ManyToOne
    private User recipient;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public User getRecipient() {
        return recipient;
    }

    public void setRecipient(User recipient) {
        this.recipient = recipient;
    }
}
