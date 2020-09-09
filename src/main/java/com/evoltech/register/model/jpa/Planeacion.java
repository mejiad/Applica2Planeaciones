package com.evoltech.register.model.jpa;

import com.evoltech.register.model.base.BaseJpaEntity;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Entity
@Data
public class Planeacion extends BaseJpaEntity<Long> implements Serializable {

    public Planeacion(String nombre){
        this.nombre = nombre;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty @NotBlank private String nombre;

    @ManyToMany(mappedBy = "planeaciones")
    private Set<Documento> documentos = new HashSet<Documento>();

    public void addDocumento(Documento documento){
        this.documentos.add(documento);
        documento.getPlaneaciones().add(this);
    }

    public void removeDocumento(Documento documento){
        this.documentos.remove(documento);
        documento.getPlaneaciones().remove(this);
    }

    public void removeDocumentos() {
        Iterator<Documento> iterator = this.documentos.iterator();

        while(iterator.hasNext()){
            Documento documento = iterator.next();

            documento.getPlaneaciones().remove(this);
            iterator.remove();
        }
    }

    @ManyToOne(fetch = FetchType.LAZY)
    private Libro libro;

}
