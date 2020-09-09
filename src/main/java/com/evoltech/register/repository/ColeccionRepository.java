package com.evoltech.register.repository;

import com.evoltech.register.model.jpa.Coleccion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ColeccionRepository extends JpaRepository<Coleccion, Long> {
    List<Coleccion> findByNombre(String nombre);
    List<Coleccion> findByNivel(String nivel);
}
