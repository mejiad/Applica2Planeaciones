package com.evoltech.register.model.jpa;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Entity
@Data
public class Documento implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @org.hibernate.annotations.Type(type = "pg-uuid")
    private UUID guid;

    private LocalDateTime created;
    private LocalDateTime modified;

    private String nombre;
    private String descripcion;
    private String uri;
    private String mimeType;
    private String content;

    @ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE})
    private Set<Planeacion> planeaciones = new HashSet<Planeacion>();

    @PrePersist
    void onCreate() {
        this.setCreated(LocalDateTime.now());
        this.setModified(LocalDateTime.now());
    }

    @PreUpdate
    void onUpdate() {
        this.setModified(LocalDateTime.now());
    }
}
