package dev.mouhieddine.springpetclinic.controllers;

import dev.mouhieddine.springpetclinic.model.Vet;
import dev.mouhieddine.springpetclinic.services.VetService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Set;

@Controller
@Slf4j
public class VetController {
  private final VetService vetService;

  public VetController(VetService vetService) {
    this.vetService = vetService;
  }

  @InitBinder
  public void setAllowedFields(WebDataBinder dataBinder) {
    dataBinder.setDisallowedFields("id"); // don't allow web forms to address the id property
  }

  @GetMapping({"/vets", "/vets.html", "/vets/index", "/vets/index.html"})
  public String listVets(Model model) {
    model.addAttribute("vets", vetService.findAll());
    return "vets/index";
  }

  @GetMapping("/api/vets")
  public @ResponseBody
  Set<Vet> getVetsJson() {
    return vetService.findAll();
  }
}
