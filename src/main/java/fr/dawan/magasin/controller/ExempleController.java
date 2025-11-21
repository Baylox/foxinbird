package fr.dawan.magasin.controller;


import fr.dawan.magasin.entities.Personne;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Controller
@RequestMapping("/exemple")
public class ExempleController {

    @GetMapping
    public String index() {
        return "exemple-index";
    }

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
    public String testPathMap(@PathVariable Map<String, String> params, Model model) {
        model.addAttribute(
                "msg",
                "paramètres de chemin id=" + params.get("id") + " nom=" + params.get("nom")
        );
        return "exemple";
    }

    // Paramètre de requête
    @GetMapping("/testparam")
    public String testRequestParam(@RequestParam String id, Model model) {
        model.addAttribute("msg", "Le paramètre de requête id=" + id);
        return "exemple";
    }

    @GetMapping("/testparamulti")
    public String testRequestParamMulti(@RequestParam String id, @RequestParam String nom, Model model) {
        model.addAttribute("msg", "Le paramètres de requête id=" + id + "  nom=" + nom);
        return "exemple";
    }

    @GetMapping("/testparammap")
    public String testRequestParamMap(@RequestParam Map<String,String> param, Model model) {
        model.addAttribute("msg", "Le paramètres de requête id=" + param.get("id") + "  nom=" + param.get("nom"));
        return "exemple";
    }

    // L’attribut required
    @GetMapping({ "/testpathop", "/testpathop/{id}" })
    public String testPathOptionel(@PathVariable(required = false) String id, Model model) {
        if (id == null) {
            model.addAttribute("msg", "Paramètre absent");
        } else {
            model.addAttribute("msg", "paramètre de chemin id=" + id);
        }
        return "exemple";
    }

    @GetMapping({ "/testpathop2", "/testpathop2/{id}" })
    public String testPathOptionel2(@PathVariable(required = false) Optional<String> id, Model model) {
        if (id.isEmpty()) {
            model.addAttribute("msg", "Paramètre absent");
        } else {
            model.addAttribute("msg", "paramètre de chemin id=" + id.get());
        }
        return "exemple";
    }

    @GetMapping("/testparamOp")
    public String testRequestParamOp(@RequestParam(required=false) String id, Model model) {
        model.addAttribute("msg", "Le paramètre de requête id=" + (id != null ? id : "absent"));
        return "exemple";
    }

    // default
    @GetMapping("/testparamdefault")
    public String testRequestParamDefault(@RequestParam(defaultValue = "42") String id, Model model) {
        model.addAttribute("msg", "Le paramètre de requête id=" + id);
        return "exemple";
    }

    // @RequestHeader
    @GetMapping("/testheader")
    public String testHeader(@RequestHeader("user-agent") String userAgent, Model model) {
        model.addAttribute("msg", userAgent);
        return "exemple";
    }

    @GetMapping("/testallheader")
    public String testallHeader(@RequestHeader HttpHeaders headers, Model model) {
        String res = "";
        var entry = headers.entrySet();

        for (var e : entry) {
            String entete = e.getKey() + " : ";
            for (String v : e.getValue()) {
                entete += v + " ";
            }
            res += entete + " ; ";
        }

        model.addAttribute("msg", res);
        return "exemple";
    }

    // Conversion
    @GetMapping("/testpathconv/{id}")
    public String testPathConv(@PathVariable int id, Model model) {
        model.addAttribute("msg", "id=" + id);
        return "exemple";
    }

    @GetMapping("/testparamconv")
    public String testParamConv(
            @DateTimeFormat(pattern = "dd-MM-yyyy")
            @RequestParam LocalDate date,
            Model model
    ) {
        model.addAttribute("msg", "date=" + date.toString());
        return "exemple";
    }

    public String testBindPath(@PathVariable Personne p, Model model) {
        model.addAttribute("msg", "Personne=" + p.toString());
        return "exemple";
    }

    // Thymeleaf
    @GetMapping("/testmodel")
    public String testModel(Model model) {
        model.addAttribute("val", 10);

        Personne p1=new Personne("Alan","Smithee");
        model.addAttribute("personne", p1);

        double tab[]= {4.0,5.7,1.23,7.89,3.0};
        model.addAttribute("t",tab);

        Map<String,Integer> m=new HashMap<>();
        m.put("john", 34);
        m.put("jane", 31);
        m.put("Alan", 50);
        model.addAttribute("ages",m);
        return "exemplethymeleaf";
    }

    @GetMapping("/testthymeleaf/{val}")
    public String testThymeleaf(@PathVariable int val, Model model) {
        model.addAttribute("val", val);

        Personne p1=new Personne("Alan","Smithee");
        model.addAttribute("personne", p1);

        double tab[]= {4.0,5.7,1.23,7.89,3.0};
        model.addAttribute("t",tab);

        Map<String,Integer> m=new HashMap<>();
        m.put("john", 34);
        m.put("jane", 31);
        m.put("Alan", 50);
        model.addAttribute("ages",m);
        return "exemplethymeleaf";
    }

    // Redirect et Forward
    @GetMapping("/testredirect") // Réponse 302
    public String testRedirect() {
        return "redirect:/exemple";
    }

    @GetMapping("/testforward") // Réponse coté serveur
    public String testForward() {
        return "forward:/exemple";
    }

    // @ModelAttribute sur une méthode
    // Avant chaque méthode de ce controller on va ajouter au model l'attribut "dateJour"

    @ModelAttribute("dateJour")
    public LocalDateTime testModelAttribute() {
        return LocalDateTime.now();
    }

    @ModelAttribute
    public void testModelAttribute2(Model model) {
        model.addAttribute("personne1", new Personne("Alan","Smith"));
    }

    @GetMapping("/liens")
    public String testLiens() {
        return "exemple-liens";
    }

    // @ModelAttribute sur un paramètre de méthode
    @GetMapping("/testmodelattrparam")
    public String testModelAttributeParam(@ModelAttribute("personne1") Personne p, Model model) {
        model.addAttribute("msg", "Personne=" + p.toString());
        return "exemple";
    }

    @GetMapping("/testflash")
    public String testFlashAttribute(RedirectAttributes rAtt) {
        rAtt.addFlashAttribute("msg", "Vous venez d'être redirigé avec un message flash !");
        return "redirect:/exemple/cibleflash";
    }

    @GetMapping("/cibleflash")
    public String cibleFlashAttribute(@ModelAttribute("msg") String msg, Model model) {
        model.addAttribute("msg", "Message flash reçu : " + msg);
        return "exemple";
    }

    // Exception
    @GetMapping("/sqlexception")
    public void genSQLException() throws SQLException {
        throw new SQLException("Exception générée volontairement");
    }

    @GetMapping("/ioexception")
    public void genIOException() throws IOException {
        throw new IOException("Exception générée volontairement");
    }
}
