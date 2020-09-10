package com.evoltech.register.model.jpa;

import com.evoltech.register.model.base.BaseJpaEntity;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Entity
@Data
public class Maestra extends BaseJpaEntity<Long> implements Serializable {

    public Maestra(String email, String nombre){
        this.email= email;
        this.nombre= nombre;
    }

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty @NotBlank @Column(unique = true) String email;
    @NotEmpty @NotBlank String nombre;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "escuela_id")
    private Escuela escuela;

}