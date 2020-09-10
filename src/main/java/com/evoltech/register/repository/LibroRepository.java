package com.evoltech.register.repository;

import com.evoltech.register.model.jpa.Escuela;
import com.evoltech.register.model.jpa.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LibroRepository extends JpaRepository<Libro, Long> {
    List<Libro> findByTitulo(String titulo);

    List<Libro> todosOrdenados(String nombreColeccion);
}
