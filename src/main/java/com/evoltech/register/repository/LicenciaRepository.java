package com.evoltech.register.repository;

import com.evoltech.register.model.jpa.Coleccion;
import com.evoltech.register.model.jpa.Escuela;
import com.evoltech.register.model.jpa.Licencia;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LicenciaRepository extends JpaRepository<Licencia, Long> {
    List<Licencia> findByNombre(String nombre);
}
