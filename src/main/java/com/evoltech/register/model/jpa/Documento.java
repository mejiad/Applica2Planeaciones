package com.evoltech.register.model.jpa;

import com.evoltech.register.model.base.BaseJpaEntity;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Entity
@Data
public class Documento extends BaseJpaEntity<Long> implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String descripcion;
    private String uri;
    private String mimeType;
    private String content;

    @ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE})
    private Set<Planeacion> planeaciones = new HashSet<Planeacion>();

}
