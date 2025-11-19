package fr.dawan.magasin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/presentation")
public class PresentationController {

    @GetMapping
    public String presentation(Model model) {
        model.addAttribute("prenom", "");
        model.addAttribute("nom", "");
        return "presentation";
    }

    // /presentation/path/john/doe
    @GetMapping("/path/{prenom}/{nom}")
    public String presentationPath(@PathVariable String prenom,@PathVariable String nom, Model model) {
        model.addAttribute("prenom", prenom);
        model.addAttribute("nom", nom);
        return "presentation";
    }

    // /presentation/param?prenom=john&nom=doe
    @RequestMapping(value="/param", method= { RequestMethod.GET,RequestMethod.POST})
    public String presentationParam(@RequestParam String prenom, @RequestParam String nom ,Model model) {
        model.addAttribute("prenom", prenom);
        model.addAttribute("nom", nom);
        return "presentation";
    }
}
