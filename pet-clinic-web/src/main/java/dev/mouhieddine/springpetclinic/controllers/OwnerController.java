package dev.mouhieddine.springpetclinic.controllers;

import dev.mouhieddine.springpetclinic.services.OwnerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping({"/owners","/owners.html"})
@Controller
public class OwnerController {

    private final OwnerService ownerService;

    public OwnerController(OwnerService ownerService) {
        this.ownerService = ownerService;
    }

    @RequestMapping({"/",""})
    public String listOwners(Model model){
        model.addAttribute("owners",ownerService.findAll());
        return "owners/index";
    }
    @RequestMapping({"/find","/find.html","/find/"})
    public String findOwners(Model model){
        return "not-implemented";
    }

}
