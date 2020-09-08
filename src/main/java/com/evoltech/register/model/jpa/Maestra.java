package com.evoltech.register.model.jpa;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;


@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Entity
@Data
public class Maestra implements Serializable {

    public Maestra(String email, String nombre){
        this.email= email;
        this.nombre= nombre;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // @org.hibernate.annotations.Type(type = "pg-uuid")
    private UUID guid;

    @Column(unique = true)
    String email;
    String nombre;

    private LocalDateTime created;
    private LocalDateTime modified;

    @ManyToOne(fetch = FetchType.LAZY)
    private Escuela escuela;

    @PrePersist
    void onCreate() {
        this.setCreated(LocalDateTime.now());
        this.setModified(LocalDateTime.now());
        this.setGuid(UUID.randomUUID());
    }

    @PreUpdate
    void onUpdate() {
        this.setModified(LocalDateTime.now());
    }
}