package fr.dawan.magasin.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HelloWorldController {

    @Value("${message: Message par défaut}")
    public String msg;

    @RequestMapping("/hello")
    public String hello(Model model) {
        model.addAttribute("msgvue", msg);
        return "hello";
    }
}
