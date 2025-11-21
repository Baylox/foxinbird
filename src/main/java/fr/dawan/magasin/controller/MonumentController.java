package fr.dawan.magasin.controller;


import fr.dawan.magasin.entities.Monument;
import fr.dawan.magasin.repositories.MonumentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import jakarta.validation.Valid;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@AllArgsConstructor


@Controller
@RequestMapping("/monument")
public class MonumentController {

    // Autowired
    private MonumentRepository monumentRepository;

    @GetMapping("/monuments")
    public String getAll(Model model) {
        List<Monument> monuments = monumentRepository.findAll();
        model.addAttribute("monuments", monuments);
        return "monument";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("monument", new Monument());
        return "ajoutMonument";
    }

    @PostMapping("/add")
    public String addMonument(@Valid @ModelAttribute Monument monument,
                              BindingResult result,
                              RedirectAttributes rAtt) {
        if (result.hasErrors()) {
            return "ajoutMonument";
        }
        try {
            monumentRepository.saveAndFlush(monument);
            rAtt.addFlashAttribute("msg", "Monument ajouté avec succès");
            rAtt.addFlashAttribute("msgType", "success");
        } catch (Exception e) {
            rAtt.addFlashAttribute("msg", "Erreur lors de l'ajout du monument");
            rAtt.addFlashAttribute("msgType", "danger");
        }
        return "redirect:/monument/monuments";
    }

}
