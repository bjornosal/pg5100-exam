package no.salbjo16.exams.backend.entity;

import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
public class Book {
    @Id
    @GeneratedValue
    private Long id;

    @NotBlank
    @Column(unique = true)
    @Size(min = 1, max = 1024)
    private String title;

    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> authors;

    @NotBlank
    @Column(unique = true)
    @Size(min = 1, max = 512)
    private String course;

    @NotNull
    @ManyToMany
    private List<User> sellers = new ArrayList<>();


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<String> getAuthors() {
        return authors;
    }

    public void setAuthors(List<String> authors) {
        this.authors = authors;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public List<User> getSellers() {
        return sellers;
    }

    public void setSellers(List<User> sellers) {
        this.sellers = sellers;
    }
}
