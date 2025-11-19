package fr.dawan.magasin.controller;


import fr.dawan.magasin.entities.Personne;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import java.time.LocalDate;
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

    @GetMapping("/testredirect") // Réponse 302
    public String testRedirect() {
        return "redirect:/exemple";
    }

    @GetMapping("/testforward") // Réponse 302
    public String testForward() {
        return "forward:/exemple";
    }
}
