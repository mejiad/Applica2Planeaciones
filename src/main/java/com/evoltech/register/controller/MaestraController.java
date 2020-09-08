package com.evoltech.register.controller;

import com.evoltech.register.model.jpa.Escuela;
import com.evoltech.register.model.jpa.Maestra;
import com.evoltech.register.repository.MaestraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class MaestraController {

    @Autowired
    MaestraRepository maestraRepository;

    @RequestMapping(value = "/maestras", method= RequestMethod.GET)
    public String maestras(Model model){
        List<Maestra> maestras = maestraRepository.findAll();
        model.addAttribute("maestras", maestras);
        return "HomePage";
    }
}
