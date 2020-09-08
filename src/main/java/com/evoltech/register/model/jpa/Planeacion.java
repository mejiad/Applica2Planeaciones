package com.evoltech.register.model.jpa;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Entity
@Data
public class Planeacion implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // @org.hibernate.annotations.Type(type = "pg-uuid")
    private UUID guid;

    private LocalDateTime created;
    private LocalDateTime modified;

    private String nombre;

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

    @PrePersist
    void onCreate() {
        this.setCreated(LocalDateTime.now());
        this.setModified(LocalDateTime.now());
        this.setGuid(UUID.randomUUID());
    }

    @PreUpdate
    void onUpdate() {
        this.setModified(LocalDateTime.now());
    }
}
