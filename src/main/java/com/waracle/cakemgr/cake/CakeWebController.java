package com.waracle.cakemgr.cake;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

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
}
