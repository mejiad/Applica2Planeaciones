package com.evoltech.register.controller;

import com.evoltech.register.model.jpa.Documento;
import com.evoltech.register.model.jpa.Escuela;
import com.evoltech.register.model.jpa.Libro;
import com.evoltech.register.model.jpa.Planeacion;
import com.evoltech.register.repository.DocumentoRepository;
import com.evoltech.register.repository.EscuelaRepository;
import com.evoltech.register.repository.LibroRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class EscuelaController {

    Logger log = LoggerFactory.getLogger(EscuelaController.class);

    @Autowired
    EscuelaRepository escuelaRepository;

    @Autowired
    LibroRepository libroRepository;

    @Autowired
    DocumentoRepository documentoRepository;

    @RequestMapping(value = "/home", method= RequestMethod.GET)
    public String home(Model model){
        List<Escuela> escuelas = escuelaRepository.findAll();
        model.addAttribute("escuelas", escuelas);
        return "HomePage";
    }

    @RequestMapping(value = "/colecciones/{coleccion}", method= RequestMethod.GET)
    public String colecciones(@PathVariable String coleccion, Model model){
        log.warn(" Coleccion: " + coleccion);
        if (coleccion == null || coleccion.length() == 0) {
            coleccion = "El ABC";
        }
        // coleccion = "El ABC";
        List<Libro> libros = libroRepository.todosOrdenados(coleccion);

        ArrayList<ArrayList<Libro>> niveles = new ArrayList<>();
        String nivelStr = null;
        ArrayList<Libro> librosArr = new ArrayList<>();
        for (Libro l: libros) {
            if (nivelStr == null){
                nivelStr = l.getNivel();
                librosArr = new ArrayList<>();
            }
            if (nivelStr.equals(l.getNivel())){
                librosArr.add(l);
                log.warn("Libro: " + l.getTitulo() + "  " +  l.getNombreColeccion() + " " +  l.getNivel());
            } else {
                niveles.add(librosArr);
                librosArr = new ArrayList<>();
                nivelStr = l.getNivel();
                librosArr.add(l);
            }
        }
        log.warn("Antes de Niveles: " );
        if(librosArr.size() > 0){
            niveles.add(librosArr);
        }
        // log.warn("Niveles: " + niveles.toString());

        model.addAttribute("coleccion", coleccion);
        model.addAttribute("niveles", niveles);
        return "Colecciones";
    }

    @RequestMapping(value = "/planeacion/{id}", method= RequestMethod.GET)
    public String planeaciones(@PathVariable Long id, Model model){
        log.warn("Id del libro a buscar (debe ser 16 para test): " + id);
        // Long idLong = Long.getLong(id);
        Libro libro = libroRepository.getOne(id);
        log.warn("Libro: " + libro.getTitulo());
        log.warn("Libro.getPlaneaciones().size(): " + libro.getPlaneaciones().size());

        List<Planeacion> planeaciones = libro.getPlaneaciones();

        model.addAttribute("planeaciones", planeaciones);

        return "Planeaciones";
    }


    @RequestMapping(value = "/documento/{id}", method= RequestMethod.GET)
    public String documento(@PathVariable String id, Model model){

        long idLong = 0L;
        try {
            idLong = Long.parseLong(id);
        } catch (Exception e){
            model.addAttribute("request","documento/{id}");
            model.addAttribute("error","documento id invalido");
            return "error";
        }
        if (idLong > 0) {
            Documento documento = documentoRepository.getOne(idLong);
            model.addAttribute("documento", documento);
            if (documento.getMimeType().equals("imagen")){
                return "Carrousel";
            } else {
                return "Documento";
            }
        } else {
            return "error";
        }
    }

    /*
    @RequestMapping(value = "/loginForm", method= RequestMethod.GET)
    public String loginForm(Model model){
        List<Escuela> escuelas = escuelaRepository.findAll();
        model.addAttribute("escuelas", escuelas);
        return "Login";
    }

    @RequestMapping(value = "/loginPost", method= RequestMethod.GET)
    public String loginPost(Model model){
        List<Escuela> escuelas = escuelaRepository.findAll();
        model.addAttribute("escuelas", escuelas);
        return "Grupos";
    }
     */

    @RequestMapping(value = "/escuelas", method= RequestMethod.GET)
    public String escuelas(Model model){
        List<Escuela> escuelas = escuelaRepository.findAll();
        model.addAttribute("escuelas", escuelas);
        return "EscuelaList";
    }

    @RequestMapping(value = "/grupos", method= RequestMethod.GET)
    public String grupos(Model model){
        List<Escuela> escuelas = escuelaRepository.findAll();
        model.addAttribute("escuelas", escuelas);
        return "Grupos";
    }

    @RequestMapping(value = "/menu", method= RequestMethod.GET)
    public String menu(Model model){
        List<Escuela> escuelas = escuelaRepository.findAll();
        model.addAttribute("escuelas", escuelas);
        return "Menu";
    }

    @RequestMapping(value = "/col", method= RequestMethod.GET)
    public String colecciones(Model model){
        List<Libro> libros = libroRepository.todosOrdenados("El ABC");

        ArrayList<ArrayList<Libro>> niveles = new ArrayList<>();
        String nivelStr = null;
        ArrayList<Libro> librosArr = new ArrayList<>();
        for (Libro l: libros) {
            if (nivelStr == null){
                nivelStr = l.getNivel();
                librosArr = new ArrayList<>();
            }
            if (nivelStr.equals(l.getNivel())){
                librosArr.add(l);
                log.warn("Libro: " + l.getTitulo() + "  " +  l.getNombreColeccion() + " " +  l.getNivel());
            } else {
                niveles.add(librosArr);
                librosArr = new ArrayList<>();
                nivelStr = l.getNivel();
                librosArr.add(l);
            }
        }
        niveles.add(librosArr);
        log.warn("Niveles: " + niveles.toString());

        model.addAttribute("niveles", niveles);
        return "Colecciones";
    }

}
