package com.evoltech.register.model.jpa;

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
public class Libro implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @org.hibernate.annotations.Type(type = "pg-uuid")
    private UUID guid;

    private LocalDateTime created;
    private LocalDateTime modified;

    private String titulo;

    @ManyToOne(fetch = FetchType.LAZY)
    private Coleccion coleccion;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = false)
    private List<Planeacion> planeaciones = new ArrayList<Planeacion>();

    public void addPLaneacion(Planeacion planeacion){
        this.planeaciones.add(planeacion);
        planeacion.setLibro(this);
    }

    public void removePLaneacion(Planeacion planeacion){
        planeacion.setLibro(null);
        this.planeaciones.remove(planeacion);
    }

    public void removePlaneaciones() {
        Iterator<Planeacion> iterator = this.planeaciones.iterator();

        while(iterator.hasNext()){
            Planeacion planeacion = iterator.next();

            planeacion.setLibro(null);
            iterator.remove();
        }
    }

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
