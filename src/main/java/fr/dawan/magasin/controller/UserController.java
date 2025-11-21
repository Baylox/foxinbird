package fr.dawan.magasin.controller;

import fr.dawan.magasin.entities.User;
import fr.dawan.magasin.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@AllArgsConstructor
@Controller
@RequestMapping("/admin/users")
public class UserController {

    private UserRepository userRepository;

    @GetMapping
    public String displayUser(Model model) {
        List<User> users = userRepository.findAll();
        model.addAttribute("users", users);
        return "user";
    }

    @GetMapping("/delete")
    public String deleteUser(@RequestParam Long id, RedirectAttributes rAtt) {
        try {
            userRepository.deleteById(id);
            rAtt.addFlashAttribute("msg", "Utilisateur supprimé avec succès");
            rAtt.addFlashAttribute("msgType", "success");
        } catch (Exception e) {
            rAtt.addFlashAttribute("msg", "Erreur lors de la suppression de l'utilisateur");
            rAtt.addFlashAttribute("msgType", "danger");
        }
        return "redirect:/admin/users";
    }

    @GetMapping("/export")
    public void exportUserCsv(HttpServletResponse response) {
        try {
            response.setContentType("text/csv");
            response.setHeader("Content-Disposition", "attachment; filename=users.csv");

            List<User> users = userRepository.findAll();
            PrintWriter writer = response.getWriter(); // Récupération du flux de sortie

            // En-tête CSV
            writer.println("ID,Prénom,Nom,Date de naissance,Email,Actif");

            // Données
            for (User user : users) {
                writer.println(String.format("%d,%s,%s,%s,%s,%s",
                        user.getId(),
                        user.getPrenom(),
                        user.getNom(),
                        user.getDateNaissance(),
                        user.getEmail(),
                        user.isEnable() ? "Oui" : "Non"
                ));
            }

            writer.flush();
        } catch (IOException e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }
}
