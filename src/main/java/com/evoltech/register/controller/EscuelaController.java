package com.evoltech.register.controller;

import com.evoltech.register.model.jpa.Escuela;
import com.evoltech.register.repository.EscuelaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class EscuelaController {

    Logger log = LoggerFactory.getLogger(EscuelaController.class);

    @Autowired
    EscuelaRepository escuelaRepository;

    @RequestMapping(value = "/home", method= RequestMethod.GET)
    public String home(Model model){
        List<Escuela> escuelas = escuelaRepository.findAll();
        model.addAttribute("escuelas", escuelas);
        return "HomePage";
    }

    @RequestMapping(value = "/login", method= RequestMethod.GET)
    public String login(Model model){
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
