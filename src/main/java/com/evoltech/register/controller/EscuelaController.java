package com.evoltech.register.controller;

import com.evoltech.register.model.jpa.Escuela;
import com.evoltech.register.model.jpa.Libro;
import com.evoltech.register.repository.EscuelaRepository;
import com.evoltech.register.repository.LibroRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.List;

@Controller
public class EscuelaController {

    Logger log = LoggerFactory.getLogger(EscuelaController.class);

    @Autowired
    EscuelaRepository escuelaRepository;

    @Autowired
    LibroRepository libroRepository;

    @RequestMapping(value = "/home", method= RequestMethod.GET)
    public String home(Model model){
        List<Escuela> escuelas = escuelaRepository.findAll();
        model.addAttribute("escuelas", escuelas);
        return "HomePage";
    }

    @RequestMapping(value = "/colecciones", method= RequestMethod.GET)
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

    @RequestMapping(value = "/libroDocs/{id}", method= RequestMethod.GET)
    public String libroDocs(Model model){
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

        return "LibroDocumentos";
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


}
