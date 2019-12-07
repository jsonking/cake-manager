package com.waracle.cakemgr.cake;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class CakeWebController {

    private CakeRepository repository;

    CakeWebController(CakeRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/")
    public String homePage(Model model) {
        model.addAttribute("cakes", repository.findAll());
        return "index";
    }

    @RequestMapping(value = "/addCake", method = RequestMethod.GET)
    public String addCake(Model model) {
        model.addAttribute("cake", new Cake());
        return "add-cake";
    }

    @RequestMapping(value = "/saveCake", method = RequestMethod.POST)
    public String saveCake(@ModelAttribute Cake cake, BindingResult errors, Model model) {
        repository.save(cake);
        return homePage(model);
    }
}
