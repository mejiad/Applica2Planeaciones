package com.evoltech.register.model.jpa;

import com.evoltech.register.util.COLECCION_NIVEL;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
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
    @NotEmpty
    @NotBlank
    private String nombre;

    @NotEmpty
    @NotBlank
    private String nivel;

    private LocalDateTime created;
    private LocalDateTime modified;

    @OneToMany(cascade = CascadeType.ALL,  orphanRemoval = false)
    private List<Licencia> licencias = new ArrayList<Licencia>();

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


    public void addLicencia(Licencia licencia){
        this.licencias.add(licencia);
        licencia.setColeccion(this);
    }

    public void removeLicencia(Licencia licencia){
        licencia.setColeccion(null);
        this.licencias.remove(licencia);
    }

    public void removeLicencias(){
        Iterator<Licencia> iterator = this.licencias.iterator();

        while (iterator.hasNext()){
            Licencia licencia = iterator.next();

            licencia.setColeccion(null);
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
