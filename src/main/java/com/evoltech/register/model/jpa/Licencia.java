package com.evoltech.register.model.jpa;

import com.evoltech.register.model.base.BaseJpaEntity;
import com.evoltech.register.util.LicenciaEstado;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.time.LocalDateTime;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Entity
@Data
public class Licencia extends BaseJpaEntity<Long> implements Serializable {

    public Licencia(String nombre){
        this.nombre = nombre;
    }

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty @NotBlank private String nombre;
    private LicenciaEstado estatus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "escuela_id")
    private Escuela escuela;

    @ManyToOne(fetch = FetchType.LAZY)
    private Coleccion coleccion;

    private LocalDateTime startDate;
    private LocalDateTime finishDate;

}
