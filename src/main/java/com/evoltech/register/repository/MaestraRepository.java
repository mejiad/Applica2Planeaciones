package com.evoltech.register.repository;

import com.evoltech.register.model.jpa.Escuela;
import com.evoltech.register.model.jpa.Maestra;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MaestraRepository extends JpaRepository<Maestra, Long> {

    List<Maestra> findByNombre(String nombre);

    List<Maestra> findByEmail(String email);

}