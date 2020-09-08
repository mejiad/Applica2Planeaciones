package com.evoltech.register.config;

import com.evoltech.register.model.jpa.Coleccion;
import com.evoltech.register.model.jpa.Escuela;
import com.evoltech.register.model.jpa.Libro;
import com.evoltech.register.model.jpa.Maestra;
import com.evoltech.register.repository.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class ApplicationStartup implements ApplicationListener<ApplicationReadyEvent> {

    Logger log = LoggerFactory.getLogger(ApplicationStartup.class);

    @Autowired
    EscuelaRepository escuelaRepository;

    @Autowired
    MaestraRepository maestraRepository;

    @Autowired
    ColeccionRepository coleccionRepository;

    @Autowired
    LibroRepository libroRepository;

    @Autowired
    LicenciaRepository licenciaRepository;

    @Autowired
    PlaneacionRepository planeacionRepository;

    @Override
    public void onApplicationEvent(final ApplicationReadyEvent event){
        log.warn("<<<<<<<<<<<<<<<<<<<<<<<<    on Application ready   >>>>>>>>>>>>>>>>>>>>>>>>>>");
        log.warn("<<<<<<<<<<<<<<<<<<<<<<<<    Init database     >>>>>>>>>>>>>>>>>>>>>>>>>>");
        escuelaRepository.save(new Escuela("Escuela Creada 1"));
        escuelaRepository.save(new Escuela("Escuela Creada 2"));
        escuelaRepository.save(new Escuela("Escuela Creada 3"));
        escuelaRepository.save(new Escuela("Escuela Creada 4"));

        maestraRepository.save(new Maestra("email 01", "nombre 01"));
        maestraRepository.save(new Maestra("email 02", "nombre 02"));
        maestraRepository.save(new Maestra("email 03", "nombre 03"));
        maestraRepository.save(new Maestra("email 04", "nombre 04"));
        maestraRepository.save(new Maestra("email 05", "nombre 05"));

        coleccionRepository.save(new Coleccion("coleccion 01", "Nivel 01"));
        coleccionRepository.save(new Coleccion("coleccion 02", "Nivel 01"));
        coleccionRepository.save(new Coleccion("coleccion 03", "Nivel 01"));

        coleccionRepository.save(new Coleccion("coleccion 01", "Nivel 02"));
        coleccionRepository.save(new Coleccion("coleccion 02", "Nivel 02"));
        coleccionRepository.save(new Coleccion("coleccion 03", "Nivel 02"));

        coleccionRepository.save(new Coleccion("coleccion 01", "Nivel 03"));
        coleccionRepository.save(new Coleccion("coleccion 02", "Nivel 03"));
        coleccionRepository.save(new Coleccion("coleccion 03", "Nivel 03"));

        libroRepository.save(new Libro("Titulo 01"));
        libroRepository.save(new Libro("Titulo 02"));
        libroRepository.save(new Libro("Titulo 03"));
        libroRepository.save(new Libro("Titulo 04"));
        libroRepository.save(new Libro("Titulo 05"));
        libroRepository.save(new Libro("Titulo 06"));
        libroRepository.save(new Libro("Titulo 07"));
        libroRepository.save(new Libro("Titulo 08"));
        libroRepository.save(new Libro("Titulo 09"));
        libroRepository.save(new Libro("Titulo 10"));
        libroRepository.save(new Libro("Titulo 11"));
        libroRepository.save(new Libro("Titulo 12"));
        libroRepository.save(new Libro("Titulo 13"));
        libroRepository.save(new Libro("Titulo 15"));
        libroRepository.save(new Libro("Titulo 16"));
        libroRepository.save(new Libro("Titulo 17"));
        libroRepository.save(new Libro("Titulo 18"));
        libroRepository.save(new Libro("Titulo 19"));


        return;
    }
}
