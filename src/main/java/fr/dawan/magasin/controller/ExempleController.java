package fr.dawan.magasin.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/exemple")
public class ExempleController {

    @RequestMapping(value = {"", "/"})
    public String exemple() {
        return "exemple";
    }

    @RequestMapping(value = {"/testmodel", "/model"})
    public String testModel(Model model) {
        String message = "Test Model";
        model.addAttribute("msg", message);
        return "exemple";
    }

    @RequestMapping("/testmodelandview")
    public ModelAndView testModelAndView() {
        ModelAndView mdv = new ModelAndView("exemple");
        mdv.addObject("msg", "TestModelAndView");
        return mdv;
    }

}
