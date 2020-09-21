package com.evoltech.register.model.jpa;

import com.evoltech.register.model.base.BaseJpaEntity;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Entity
@Data
public class Planeacion extends BaseJpaEntity<Long> implements Serializable {

    public Planeacion(String nombre, String fechaStr){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        this.nombre = nombre;
        this.descripcion = "Descripción";
        this.unidad = "Unidad Test";
        this.fecha= LocalDate.parse(fechaStr, formatter);
    }

    public Planeacion(String nombre, String descripcion, String unidad, LocalDateTime fecha){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.unidad = unidad;
        this.fecha= LocalDate.parse("21-10-2020", formatter);
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    private String descripcion;

    @NotEmpty
    private String unidad;

    @NotEmpty @NotBlank private String nombre;
    @NonNull private LocalDate fecha;

    @ManyToOne(fetch = FetchType.LAZY)
    private Libro libro;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = false)
    private List<Documento> documentos = new ArrayList<>();

    public void addDocumento(Documento documento){
        this.documentos.add(documento);
        documento.setPlaneacion(this);
    }

    public void removeDocumento(Documento documento){
        documento.setPlaneacion(null);
        this.documentos.remove(documento);
    }

    public void removeDocumentos() {
        Iterator<Documento> iterator = this.documentos.iterator();

        while(iterator.hasNext()){
            Documento documento = iterator.next();

            documento.setPlaneacion(null);
            iterator.remove();
        }
    }

}
