package fr.dawan.magasin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/presentation")
public class PresentationController {

    @GetMapping("/path/{prenom}/{nom}")
    public String presentationPath(@PathVariable String prenom, @PathVariable String nom, Model model) {
        model.addAttribute("prenom", prenom);
        model.addAttribute("nom", nom);
        return "presentation";
    }

    @GetMapping("/param")
    public String presentationParamGet(@RequestParam String prenom, @RequestParam String nom, Model model) {
        model.addAttribute("prenom", prenom);
        model.addAttribute("nom", nom);
        return "presentation";
    }

    @PostMapping("/param")
    public String presentationParamPost(@RequestParam String prenom, @RequestParam String nom, Model model) {
        model.addAttribute("prenom", prenom);
        model.addAttribute("nom", nom);
        return "presentation";
    }

}
