package com.evoltech.register.model.jpa;

import com.evoltech.register.model.base.BaseJpaEntity;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Entity
@Data
public class Documento extends BaseJpaEntity<Long> implements Serializable {

    public Documento(String nombre, String descripcion, String uri, String mimeType, String fechaStr){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.uri = uri;
        this.mimeType = mimeType;
        this.fecha= LocalDate.parse(fechaStr, formatter);
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull @NotEmpty private String nombre;
    @NonNull @NotEmpty private String descripcion;
    @NonNull @NotEmpty private String uri;
    @NonNull @NotEmpty private String mimeType;
    @NonNull private LocalDate fecha;
    private String content;

    @OneToMany
    private ArrayList<Archivo> archivos = new ArrayList<Archivo>();

    /*
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "libro_id")
    private Libro libro;
     */

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "planeacion_id")
    private Planeacion planeacion;

}
