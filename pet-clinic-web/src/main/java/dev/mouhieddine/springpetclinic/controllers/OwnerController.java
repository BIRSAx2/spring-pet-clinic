package dev.mouhieddine.springpetclinic.controllers;

import dev.mouhieddine.springpetclinic.services.OwnerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@RequestMapping({"/owners", "/owners.html"})
@Controller
@Slf4j
public class OwnerController {

  private final OwnerService ownerService;

  public OwnerController(OwnerService ownerService) {
    this.ownerService = ownerService;
  }

  @GetMapping({"/", ""})
  public String listOwners(Model model) {
    model.addAttribute("owners", ownerService.findAll());
    return "owners/index";
  }

  @GetMapping({"/find", "/find.html", "/find/"})
  public String findOwner(Model model) {
    return "not-implemented";
  }

  @GetMapping("/{ownerId}")
  public ModelAndView showOwner(@PathVariable("ownerId") Long ownerId) {
    ModelAndView modelAndView = new ModelAndView("owners/ownerDetails");
    modelAndView.addObject(ownerService.findById(ownerId));
    return modelAndView;
  }
}
