package com.evoltech.register.repository;

import com.evoltech.register.model.jpa.Grupo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GrupoRepository extends JpaRepository<Grupo, Long> {

    List<Grupo> findByNombre(String nombre);

}