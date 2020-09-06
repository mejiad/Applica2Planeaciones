package com.evoltech.register.repository;

import com.evoltech.register.model.jpa.Escuela;
import com.evoltech.register.repository.base.BaseRepository;
import com.evoltech.register.repository.base.BaseRepositoryImpl;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EscuelaRepository extends JpaRepository<Escuela, Long> {

    List<Escuela> findByNombre(String nombre);

}
