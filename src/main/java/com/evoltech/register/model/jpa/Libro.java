package com.evoltech.register.model.jpa;

import com.evoltech.register.model.base.BaseJpaEntity;
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
public class Libro extends BaseJpaEntity<Long> implements Serializable {

    public Libro(String titulo){
        this.titulo = titulo;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty @NotBlank private String titulo;

    @ManyToOne(fetch = FetchType.LAZY)
    private Coleccion coleccion;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = false)
    private List<Planeacion> planeaciones = new ArrayList<Planeacion>();

    public void addPlaneacion(Planeacion planeacion){
        this.planeaciones.add(planeacion);
        planeacion.setLibro(this);
    }

    public void removePlaneacion(Planeacion planeacion){
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

}
