package com.evoltech.register.model.jpa;

import com.evoltech.register.util.LicenciaEstado;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Entity
@Data
public class Licencia implements Serializable {

    public Licencia(String nombre){
        this.nombre = nombre;
    }

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private UUID guid;
    @NotEmpty @NotBlank private String nombre;
    private LicenciaEstado estatus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "escuela_id")
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
        this.setGuid(UUID.randomUUID());
    }

    @PreUpdate
    void onUpdate() {
        this.setModified(LocalDateTime.now());
    }
}
