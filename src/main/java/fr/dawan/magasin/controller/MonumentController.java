package fr.dawan.magasin.controller;


import fr.dawan.magasin.entities.Monument;
import fr.dawan.magasin.repositories.MonumentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@AllArgsConstructor


@Controller
public class MonumentController {

    // Autowired
    private MonumentRepository monumentRepository;

    @GetMapping("/monuments")
    public String getAll(Model model) {
        List<Monument> monuments = monumentRepository.findAll();
        model.addAttribute("monuments", monuments);
        return "monument";
    }

}
