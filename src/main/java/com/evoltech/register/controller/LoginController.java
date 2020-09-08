package com.evoltech.register.controller;

import com.evoltech.register.model.jpa.User;
import com.evoltech.register.repository.MaestraRepository;
import com.evoltech.register.service.MaestraService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
public class LoginController {

    Logger log = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    MaestraService maestraService;

    @GetMapping("/login")
    public String blogDetail(@RequestParam("email") String email, @RequestParam("password") String password, Model model) {
        log.warn("valor de email: " + email + " password:" + password);
        model.addAttribute("maestraId", email);
        return "HomePage";
    }


    //  curl -d 'email=emailtest&password=1234' -H  "Content-Type: application/x-www-form-urlencoded; charset=utf-8" -X POST http://localhost:8080/register/authentication
    @RequestMapping(value="/authentication",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String authentication(User user) {

        log.warn("Dentro de save LoginController email: " + user.getEmail());
        log.warn("Dentro de save LoginController password: " + user.getPassword());

        return "HomePage";
    }


    //  curl -i  -d '{"email":"email", "password":"1234"}' -H  "Content-Type: application/json; charset=utf-8" -X POST http://localhost:8080/register/login3
    @RequestMapping(value = "/login3", method = { RequestMethod.POST,
    RequestMethod.PUT})
    public String login3(@RequestBody User user) {
        log.warn("Dentro de login email: " + user.getEmail());
        log.warn("Dentro de login password: " + user.getPassword());
        return "HomePage";
    }


    // curl -d 'email=emailtest&password=1234' -H  "Content-Type: application/x-www-form-urlencoded; charset=utf-8" -X POST http://localhost:8080/register/login2
    @RequestMapping(value = "/login2", method = { RequestMethod.POST,
            RequestMethod.PUT})
    public String login2(@RequestParam Map<String, String> body) {
        log.warn("Dentro de login email: " + body.keySet());
        log.warn("Dentro de login email2: " + body.get("email"));
        return "HomePage";
    }

    @RequestMapping(value = "/loginForm", method = { RequestMethod.GET})
    public String loginForm(){
        return "Login";
    }
}