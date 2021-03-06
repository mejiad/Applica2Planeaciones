package com.evoltech.register.model.jpa;

import com.evoltech.register.model.base.BaseJpaEntity;
import com.vladmihalcea.hibernate.type.array.StringArrayType;
import lombok.*;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;

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
@TypeDefs({
    @TypeDef(
        name = "string-array",
        typeClass = StringArrayType.class
    )
})
@Data
public class Documento extends BaseJpaEntity<Long> implements Serializable {

    public Documento(String nombre, String descripcion, String uri, String mimeType, String fechaStr, String[] archivos){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.uri = uri;
        this.mimeType = mimeType;
        this.fecha= LocalDate.parse(fechaStr, formatter);
        this.archivos = archivos;
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

    @Type(type = "string-array")
    @Column(
            name = "archivos",
            columnDefinition = "text[]"
    )
    private String[] archivos;

    /*
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "libro_id")
    private Libro libro;
     */

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "planeacion_id")
    private Planeacion planeacion;

}
