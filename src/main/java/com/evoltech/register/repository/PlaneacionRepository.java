package com.evoltech.register.repository;

import com.evoltech.register.model.jpa.Maestra;
import com.evoltech.register.model.jpa.Planeacion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PlaneacionRepository extends JpaRepository<Planeacion, Long> {
    List<Planeacion> findByNombre(String nombre);
}
