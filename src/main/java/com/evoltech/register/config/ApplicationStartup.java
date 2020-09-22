package com.evoltech.register.config;

import com.evoltech.register.model.jpa.*;
import com.evoltech.register.repository.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.*;


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

    @Autowired
    DocumentoRepository documentoRepository;

    @Override
    public void onApplicationEvent(final ApplicationReadyEvent event){
        log.warn("<<<<<<<<<<<<<<<<<<<<<<<<    on Application ready   >>>>>>>>>>>>>>>>>>>>>>>>>>");
        log.warn("<<<<<<<<<<<<<<<<<<<<<<<<    Init database     >>>>>>>>>>>>>>>>>>>>>>>>>>");
        log.warn("<<<<<<<<<<<<<<<<<<<<<<<<    ESCUELAS     >>>>>>>>>>>>>>>>>>>>>>>>>>");

       createMaestras();
       createLibro(10, "El ABC", "Nivel 1");
        createLibro(10, "El ABC", "Nivel 2");
        createLibro(10, "El ABC", "Nivel 3");

        createLibro(10, "El 123", "Nivel 1");
        createLibro(10, "El 123", "Nivel 2");
        createLibro(10, "El 123", "Nivel 3");

    }



    private String[] lastName = {"García", "González", "Rodríguez", "Fernández", "López",
            "Martinez", "Sánchez", "Pérez", "Gómez", "Jiménez",
            "Ruiz", "Hernández", "Díaz", "Moreno", "Romero"};

    private String[] names = {"Juan", "Luis", "José", "Maria", "Valeria",
            "Elba", "Gretel", "Ilsa", "Olivia", "Lara",
            "Pablo", "Diego", "Gabriel", "Angel", "Julián"};

    private int MAX_MAESTRAS = 15;

    private void createMaestras() {
        Maestra[] maestras = new Maestra[MAX_MAESTRAS];
        Random rand = new Random();
        for(int i = 0; i < MAX_MAESTRAS; i++) {
            int idxName = rand.nextInt(15);
            int idxLastname = rand.nextInt(15);
            String email = names[idxName] + "." + lastName[idxLastname] + i + "@gmail.com";
            String name = names[idxName] + " " + lastName[idxLastname];
            maestras[i] = new Maestra(email, name);
            maestraRepository.save(maestras[i]);
        }
    }

    private void createLibro(int numLibros, String coleccion, String nivel) {
        int i;
        Libro libro;
        for(i = 0; i < numLibros; i++){
            String titulo = "Libro " + i + " " + coleccion + " " + nivel;
            libro = new Libro(titulo, nivel, "Libro de Trabajo", coleccion);
            addPlaneacion(libro);
            libroRepository.save(libro);
        }
        /*
        i = i + 1;
        for(; i < numLibros; i++){
            String titulo = "Libro " + i + " " + coleccion + " " + nivel;
            libro = new Libro(titulo, nivel, "Libro de Tareas", coleccion);
            libroRepository.save(libro);
            // addPlaneacion(libro);
        }
        */
    }

    private int MAX_PLANEACIONES = 10;
    private void addPlaneacion(Libro libro){
        for (int i = 0; i < MAX_PLANEACIONES; i++){
            String unidad = "Unidad " + i;
            String nombre = "Planeación " + unidad;
            String descripcion = "Planeación para el uso de los contenidos de la unidad";
            LocalDateTime date = LocalDateTime.now();
            Planeacion planeacion = new Planeacion(nombre , descripcion, unidad, date);
            // planeacionRepository.save(planeacion);
            libro.addPlaneacion(planeacion);
            log.warn("id del libro: " + libro.getId());
            addDocumentos(planeacion);
        }
    }

    private int MAX_DOCUMENTOS = 6;
    String[] docsNombres = {"Actividades", "Guía Pedagógica", "Tareas", "Ejercicios"};

    String[] docsDescr = {"Actividades dentro del salon de clases", "Guía Pedagógica para la maestra", "Tareas y ejercicios para hacer en casa", "Ejercicios dentro del salón de clase"};

    String[] mimeType = {"pdf", "audio", "video", "imagen"};

    String[] pdfLinks = {
            "http://localhost:8080/pdf/PREE-1-MIALBUM-BAJA.pdf",
            "http://localhost:8080/pdf/PREE-2-MIALBUM-BAJA.pdf",
            "http://localhost:8080/pdf/PREE-3-MIALBUM-BAJA.pdf"
    };

    String[] audioLinks = {
            "http://localhost:8080/audios/audio00.mp3",
            "http://localhost:8080/audios/audio01.mp3",
            "http://localhost:8080/audios/audio02.mp3"
    };

   String[] videoLinks = { "https://www.youtube.com/embed/9WNCpGxZ0Gg",
            "https://www.youtube.com/embed/AgoJOI0A3e8",
           "https://www.youtube.com/embed/iaaZKuHXOVU",
           "https://www.youtube.com/embed/VODa1HYGHn4",
           "https://www.youtube.com/embed/2vF3H6mx3eM"
   };

    String[] imagenLinks = {
            "https://www.youtube.com/embed/9WNCpGxZ0Gg",
            "https://www.youtube.com/embed/9WNCpGxZ0Gg",
            "https://www.youtube.com/embed/9WNCpGxZ0Gg",
            "https://www.youtube.com/embed/9WNCpGxZ0Gg"
    };
    Map<String, String[]> media = Map.of(
            "pdf", pdfLinks,
            "audio", audioLinks,
            "video", videoLinks,
            "imagen", imagenLinks
    );

    private void addDocumentos(Planeacion planeacion){
        Random rand = new Random();
        int numDocs = rand.nextInt(MAX_DOCUMENTOS) + 1;
        for(int i = 0; i < numDocs; i++){
            int idxNombre = rand.nextInt(docsNombres.length);
           // public Documento(String nombre, String descripcion, String uri, String mimeType, String fechaStr){
            String mime = getMimetype();
            String link = getLink(mime);
            String nombre = docsNombres[idxNombre];
            String descr = docsDescr[idxNombre];

           Documento doc = new Documento(nombre, descr,  link,  mime, "20-09-2020");

           // documentoRepository.save(doc);
           planeacion.addDocumento(doc);
           // planeacionRepository.save(planeacion);
        }
    }

    private String getMimetype() {
        Random rand = new Random();

        Set<String> keys = media.keySet();
        int len = media.keySet().size();
        int selected = rand.nextInt(len);
        String arr[] = new String[len];

        int i = 0;
        for (String x : keys){
            arr[i++] = x;
        }
        return arr[selected];
    }

    private String getLink(String mime){
        Random rand = new Random();
        String arr[] = media.get(mime);
        int len = arr.length;
        int selected = rand.nextInt(len);
        return arr[selected];
    }

}
