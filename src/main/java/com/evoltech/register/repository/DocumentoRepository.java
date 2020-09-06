package com.evoltech.register.repository;

import com.evoltech.register.model.jpa.Documento;
import com.evoltech.register.model.jpa.Maestra;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DocumentoRepository extends JpaRepository<Documento, Long> {
    List<Documento> findByNombre(String nombre);
}
