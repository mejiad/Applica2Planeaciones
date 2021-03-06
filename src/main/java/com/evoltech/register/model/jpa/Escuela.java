package com.evoltech.register.model.jpa;

import com.evoltech.register.model.base.BaseJpaEntity;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Entity
@Data
public class Escuela extends BaseJpaEntity<Long> implements Serializable {

    public Escuela(String nombre){
        this.nombre = nombre;
    }

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;
    @NotEmpty @NotBlank @Column(unique = true) String nombre;

    // tiene varias maestras
    @OneToMany(cascade = CascadeType.ALL)
    private List<Maestra> maestras = new ArrayList<>();

    // tiene una o varias licencias
    // @OneToMany(cascade = CascadeType.ALL, mappedBy= "escuela", orphanRemoval = false)
    @OneToMany(cascade = CascadeType.ALL)
    private List<Licencia> licencias = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL)
    private List<Grupo> grupos = new ArrayList<>();

    public void addMaestra(Maestra maestra){
        this.maestras.add(maestra);
        maestra.setEscuela(this);
    }

    void removeMaestra(Maestra maestra){
        maestra.setEscuela(null);
        this.maestras.remove(maestra);
    }

    void removeMaestras() {
        Iterator<Maestra> iterator = this.maestras.iterator();

        while( iterator.hasNext()){
            Maestra maestra = iterator.next();

            maestra.setEscuela(null);
            iterator.remove();
        }
    }

    public void addLicencia(Licencia licencia){
        this.licencias.add(licencia);
        licencia.setEscuela(this);
    }

    void removeLicencia(Licencia licencia){
        licencia.setEscuela(null);
        this.licencias.remove(licencia);
    }

    void removeLicencias() {
        Iterator<Licencia> iterator = this.licencias.iterator();

        while(iterator.hasNext()){
            Licencia licencia = iterator.next();

            licencia.setEscuela(null);
            iterator.remove();
        }
    }

    public void addGrupo(Grupo grupo){
        this.grupos.add(grupo);
        grupo.setEscuela(this);
    }

    void removeGrupo(Grupo grupo){
        grupo.setEscuela(null);
        this.grupos.remove(grupo);
    }

    void removeGrupos(){
        Iterator<Grupo> iterator = this.grupos.iterator();

        while(iterator.hasNext()){
            Grupo grupo = iterator.next();

            grupo.setEscuela(null);
            iterator.remove();
        }
    }

}
