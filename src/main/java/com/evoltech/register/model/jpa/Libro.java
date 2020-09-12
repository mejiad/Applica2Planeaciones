package com.evoltech.register.model.jpa;

import com.evoltech.register.model.base.BaseJpaEntity;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Entity
@NamedQuery(name = "Libro.todosOrdenados",
query = "select u from Libro u where nombreColeccion = ?1 order by nombreColeccion, nivel, categoria")
@Data
public class Libro extends BaseJpaEntity<Long> implements Serializable {

    public Libro(String titulo, String nivel, String tipo, String nombreColeccion ){
        this.titulo = titulo;
        this.nivel = nivel;
        this.categoria = tipo;
        this.nombreColeccion = nombreColeccion;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @NotEmpty
    private String nivel; // Solo: Nivel 1, Nivel 2 y Nivel 3
    // TODO: Ver las categorias posibles
    @NotBlank
    @NotEmpty
    private String categoria; // Libro de trabajo, libro de tareas, etc.O//
    @NotBlank
    @NotEmpty
    private String nombreColeccion;

    @NotEmpty @NotBlank private String titulo;

    @ManyToOne(fetch = FetchType.LAZY)
    private Coleccion coleccion;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Planeacion> planeaciones = new ArrayList<>();

    public void addPlaneacion(Planeacion planeacion){
        this.planeaciones.add(planeacion);
        planeacion.setLibro(this);
    }

    void removePlaneacion(Planeacion planeacion){
        planeacion.setLibro(null);
        this.planeaciones.remove(planeacion);
    }

    void removePlaneaciones(){
        Iterator<Planeacion> iterator = this.planeaciones.iterator();

        while(iterator.hasNext()){
            Planeacion planeacion = iterator.next();

            planeacion.setLibro(null);
            iterator.remove();
        }
    }
}
