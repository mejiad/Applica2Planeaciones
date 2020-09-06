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
public class Coleccion implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @org.hibernate.annotations.Type(type = "pg-uuid")
    private UUID guid;
    private String nombre;
    private LocalDateTime created;
    private LocalDateTime modified;

    @ManyToOne(fetch = FetchType.LAZY)
    private Licencia licencia;

    // liga con libros
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = false)
    private List<Libro> libros = new ArrayList<>();

    public void addLibro(Libro libro){
        this.libros.add(libro);
        libro.setColeccion(this);
    }

    public void removeLibro(Libro libro){
       libro.setColeccion(null);
       this.libros.remove(libro);
    }

    public void removeLibros() {
        Iterator<Libro> iterator = this.libros.iterator();

        while(iterator.hasNext()){
            Libro libro = iterator.next();

            libro.setColeccion(null);
            iterator.remove();
        }

    }

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = false)
    private List<Planeacion> planeaciones = new ArrayList<>();

    public void addPlaneacion(Planeacion planeacion){
        this.planeaciones.add(planeacion);
        planeacion.setColeccion(this);
    }

    public void removePlaneacion(Planeacion planeacion){
        planeacion.setColeccion(null);
        this.planeaciones.remove(planeacion);
    }

    public void removePlaneaciones(){
        for (Planeacion planeacion : this.planeaciones) {
            planeacion.setColeccion(null);
        }
        this.planeaciones = new ArrayList<Planeacion>();
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
