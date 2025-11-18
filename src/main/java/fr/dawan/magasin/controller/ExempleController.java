package fr.dawan.magasin.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@Controller
@RequestMapping("/exemple")
public class ExempleController {

    @GetMapping(value = "/testparamsvalue", params = "id=42")
    public String testParamsValue(Model model) {
        model.addAttribute("msg", "Test attribut params");
        return "exemple";
    }

    @GetMapping("/testpath/{id}")
// public String testPath(@PathVariable("id") String pId, Model model) {
//    model.addAttribute("msg","paramètre de chemin id=" + pId);
//    return "exemple";
// }
    public String testPath(@PathVariable String id, Model model) {
        model.addAttribute("msg", "paramètre de chemin id=" + id);
        return "exemple";
    }

    @GetMapping("/testpathmulti/{id}/{nom}")
    public String testPathMulti(@PathVariable String id, @PathVariable String nom, Model model) {
        model.addAttribute("msg", "paramètres de chemin id=" + id + " nom=" + nom);
        return "exemple";
    }

    @GetMapping("/testpathmap/{id}/{nom}")
    public String testPathMulti(@PathVariable Map<String, String> params, Model model) {
        model.addAttribute(
                "msg",
                "paramètres de chemin id=" + params.get("id") + " nom=" + params.get("nom")
        );
        return "exemple";
    }

    @GetMapping("/testpath1/{id:[0-9]+}")
    public String testPathAmbigue1(@PathVariable String id, Model model) {
        model.addAttribute("msg", "paramètre de chemin id=" + id);
        return "exemple";
    }

    @GetMapping("/testpath1/{nom:[a-zA-Z]+}")
    public String testPathAmbigue2(@PathVariable String nom, Model model) {
        model.addAttribute("msg", "paramètre de chemin nom=" + nom);
        return "exemple";
    }

}
