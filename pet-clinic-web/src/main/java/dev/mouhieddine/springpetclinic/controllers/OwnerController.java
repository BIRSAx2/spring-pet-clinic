package dev.mouhieddine.springpetclinic.controllers;

import dev.mouhieddine.springpetclinic.model.Owner;
import dev.mouhieddine.springpetclinic.services.OwnerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Set;

@RequestMapping({"/owners", "/owners.html"})
@Controller
@Slf4j
public class OwnerController {

  private final OwnerService ownerService;

  public OwnerController(OwnerService ownerService) {
    this.ownerService = ownerService;
  }

  @InitBinder
  public void setAllowedFields(WebDataBinder dataBinder) {
    dataBinder.setDisallowedFields("id"); // don't allow web forms to address the id property
  }

  @GetMapping("/{ownerId}")
  public ModelAndView showOwner(@PathVariable("ownerId") Long ownerId) {
    ModelAndView modelAndView = new ModelAndView("owners/ownerDetails");
    modelAndView.addObject(ownerService.findById(ownerId));
    return modelAndView;
  }

  @RequestMapping("/find")
  public String findOwners(Model model) {
    model.addAttribute("owner", Owner.builder().build());
    return "owners/findOwners";
  }

  @GetMapping
  public String processFindForm(Owner owner, BindingResult result, Model model) {
    // allow parameterless GET request for /owners to return all records
    if (owner.getLastName() == null) owner.setLastName(""); // empty string signifies broadest possible search
    // find owners by last name
    Set<Owner> results = ownerService.findAllByLastNameLike("%" + owner.getLastName() + "%");

    if (results.isEmpty()) {
      // no owners found
      result.rejectValue("lastName", "notFound", "not found");
      return "owners/findOwners";
    }
    if (results.size() == 1) {
      // 1 owner found
      owner = results.stream().findFirst().get();
      return "redirect:/owners/" + owner.getId();
    }
    // multiple owners found
    model.addAttribute("selections", results);
    return "owners/ownersList";

  }

}
