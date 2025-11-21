package fr.dawan.magasin.controller;

import fr.dawan.magasin.entities.User;
import fr.dawan.magasin.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@AllArgsConstructor
@Controller
@RequestMapping("/users")
public class UserController {

    private UserRepository userRepository;

    @GetMapping
    public String getAll(Model model) {
        List<User> users = userRepository.findAll();
        model.addAttribute("users", users);
        return "user";
    }

}
