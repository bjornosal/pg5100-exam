package no.salbjo16.exams.backend.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

@Entity
public class EntityB {
    @Id
    @GeneratedValue
    private Long id;

    @OneToOne
    @NotNull
    private User user;

}
