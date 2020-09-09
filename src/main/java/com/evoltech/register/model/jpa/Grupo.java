package com.evoltech.register.model.jpa;

import com.evoltech.register.model.base.BaseJpaEntity;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Entity
@Data
public class Grupo extends BaseJpaEntity<Long> implements Serializable {

    public Grupo(String nombre){
        this.nombre = nombre;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "escuela_id")
    private Escuela escuela;
}
