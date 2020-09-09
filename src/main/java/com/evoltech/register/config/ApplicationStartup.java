package com.evoltech.register.config;

import com.evoltech.register.model.jpa.*;
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
        log.warn("<<<<<<<<<<<<<<<<<<<<<<<<    ESCUELAS     >>>>>>>>>>>>>>>>>>>>>>>>>>");

        Escuela escuela01 = new Escuela("Escuela 01");
        Escuela escuela02 = new Escuela("Escuela 02");
        Escuela escuela03 = new Escuela("Escuela 03");
        escuelaRepository.save(escuela01);
        escuelaRepository.save(escuela02);
        escuelaRepository.save(escuela03);

        log.warn("+<<<<<<<<<<<<<<<<<<<<<<<<[ escuela01.isNew: " + escuela01.isNew() + " ]>>>>>>>>>>>>>>>>>>>>>>>>>>");

        log.warn("+<<<<<<<<<<<<<<<<<<<<<<<<[    MAESTRAS     ]>>>>>>>>>>>>>>>>>>>>>>>>>>+");
        Maestra maestra01 = new Maestra("email 01", "nombre 01");
        escuela01.addMaestra(maestra01);
        maestraRepository.save(maestra01);
        // escuela01.addMaestra(maestra01);

        Maestra maestra02 = new Maestra("email 02", "nombre 02");
        escuela01.addMaestra(maestra02);
        maestraRepository.save(maestra02);
        // escuela01.addMaestra(maestra02);

        Maestra maestra03 = new Maestra("email 03", "nombre 03");
        maestraRepository.save(maestra03);
        // escuela02.addMaestra(maestra03);

        Maestra maestra04 = new Maestra("email 04", "nombre 04");
        maestraRepository.save(maestra04);
        // escuela01.addMaestra(maestra04);

        // escuelaRepository.save(escuela01);
        // escuelaRepository.save(escuela02);

        maestraRepository.save(new Maestra("email 05", "nombre 05"));
        maestraRepository.save(new Maestra("email 06", "nombre 06"));
        maestraRepository.save(new Maestra("email 07", "nombre 07"));
        maestraRepository.save(new Maestra("email 08", "nombre 08"));
        maestraRepository.save(new Maestra("email 09", "nombre 09"));

        log.warn("+<<<<<<<<<<<<<<<<<<<<<<<<[    COLECCIONES    ]>>>>>>>>>>>>>>>>>>>>>>>>>>+");

        Coleccion coleccionNivel01 = new Coleccion("coleccion 00", "Nivel 01");
        coleccionRepository.save(coleccionNivel01);
        coleccionRepository.save(new Coleccion("coleccion 01", "Nivel 01"));
        coleccionRepository.save(new Coleccion("coleccion 02", "Nivel 01"));
        coleccionRepository.save(new Coleccion("coleccion 03", "Nivel 01"));

        Coleccion coleccionNivel02 = new Coleccion("coleccion 00", "Nivel 02");
        coleccionRepository.save(coleccionNivel02);
        coleccionRepository.save(new Coleccion("coleccion 01", "Nivel 02"));
        coleccionRepository.save(new Coleccion("coleccion 02", "Nivel 02"));
        coleccionRepository.save(new Coleccion("coleccion 03", "Nivel 02"));

        Coleccion coleccionNivel03 = new Coleccion("coleccion 00", "Nivel 03");
        coleccionRepository.save(coleccionNivel03);
        coleccionRepository.save(new Coleccion("coleccion 01", "Nivel 03"));
        coleccionRepository.save(new Coleccion("coleccion 02", "Nivel 03"));
        coleccionRepository.save(new Coleccion("coleccion 03", "Nivel 03"));

        log.warn("+<<<<<<<<<<<<<<<<<<<<<<<<[    LIBROS    ]>>>>>>>>>>>>>>>>>>>>>>>>>>+");
        Libro libro01 = new Libro("Titulo 01");
        Libro libro02 = new Libro("Titulo 02");
        Libro libro03 = new Libro("Titulo 03");
        libroRepository.save(libro01);
        libroRepository.save(libro02);
        libroRepository.save(libro03);
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

        log.warn("+<<<<<<<<<<<<<<<<<<<<<<<<[    LICENCIAS    ]>>>>>>>>>>>>>>>>>>>>>>>>>>+");

        Licencia licencia01 = new Licencia("Licencia 01");
        Licencia licencia02 = new Licencia("Licencia 02");
        Licencia licencia03 = new Licencia("Licencia 03");
        licenciaRepository.save(new Licencia("Licencia 04"));
        licenciaRepository.save(new Licencia("Licencia 05"));
        licenciaRepository.save(new Licencia("Licencia 06"));
        licenciaRepository.save(new Licencia("Licencia 07"));
        licenciaRepository.save(new Licencia("Licencia 08"));


        /*
        escuela01.addLicencia(licencia01);
        escuela02.addLicencia(licencia03);
        escuela01.addLicencia(licencia02);
         */

        licenciaRepository.save(licencia01);
        licenciaRepository.save(licencia02);
        licenciaRepository.save(licencia03);
        escuelaRepository.save(escuela01);
        escuelaRepository.save(escuela02);

        log.warn("+<<<<<<<<<<<<<<<<<<<<<<<<[    PLANEACIONES    ]>>>>>>>>>>>>>>>>>>>>>>>>>>+");

        Planeacion planeacion01 = new Planeacion("Planeacion 01");
        Planeacion planeacion02 = new Planeacion("Planeacion 02");
        Planeacion planeacion03 = new Planeacion("Planeacion 03");
        Planeacion planeacion04 = new Planeacion("Planeacion 04");

        planeacionRepository.save(planeacion01);
        planeacionRepository.save(planeacion02);
        planeacionRepository.save(planeacion03);
        planeacionRepository.save(planeacion04);

        libro01.addPlaneacion(planeacion01);
        libro02.addPlaneacion(planeacion02);
        libro03.addPlaneacion(planeacion03);
        libro01.addPlaneacion(planeacion04);
        libroRepository.save(libro01);
        libroRepository.save(libro02);
        libroRepository.save(libro03);

        planeacionRepository.save(new Planeacion("Planeacion 05"));
        planeacionRepository.save(new Planeacion("Planeacion 06"));
        planeacionRepository.save(new Planeacion("Planeacion 07"));

    }
}
