package com.evoltech.register.model.jpa;

import com.evoltech.register.util.LicenciaEstado;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Entity
@Data
public class Licencia implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @org.hibernate.annotations.Type(type = "pg-uuid")
    private UUID guid;
    private LicenciaEstado estatus;
    private String nombre;

    @ManyToOne(fetch = FetchType.LAZY)
    private Escuela escuela;

    @ManyToOne(fetch = FetchType.LAZY)
    private Coleccion coleccion;

    private LocalDateTime created;
    private LocalDateTime modified;

    private LocalDateTime startDate;
    private LocalDateTime finishDate;

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
