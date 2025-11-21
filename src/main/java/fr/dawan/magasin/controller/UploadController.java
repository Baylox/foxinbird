package fr.dawan.magasin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
@RequestMapping("/upload")
public class UploadController {

    private final String UPLOAD_DIR = "uploads/";

    @GetMapping
    public String showUploadForm() {
        return "upload";
    }

    @PostMapping
    public String uploadFile(@RequestParam("file") MultipartFile file,
                             RedirectAttributes rAtt) {
        if (file.isEmpty()) {
            rAtt.addFlashAttribute("msg", "Veuillez sélectionner un fichier");
            rAtt.addFlashAttribute("msgType", "warning");
            return "redirect:/upload";
        }

        try {
            Path uploadPath = Paths.get(UPLOAD_DIR);
            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }

            String fileName = file.getOriginalFilename();
            Path filePath = uploadPath.resolve(fileName);
            Files.write(filePath, file.getBytes());

            rAtt.addFlashAttribute("msg", "Fichier '" + fileName + "' uploadé avec succès");
            rAtt.addFlashAttribute("msgType", "success");
        } catch (IOException e) {
            rAtt.addFlashAttribute("msg", "Erreur lors de l'upload: " + e.getMessage());
            rAtt.addFlashAttribute("msgType", "danger");
        }

        return "redirect:/upload";
    }
}
