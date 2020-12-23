package dev.mouhieddine.springpetclinic.controllers;

import dev.mouhieddine.springpetclinic.model.Owner;
import dev.mouhieddine.springpetclinic.services.OwnerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.Set;

@RequestMapping({"/owners", "/owners.html"})
@Controller
@Slf4j
public class OwnerController {

  private final static String VIEW_CREATE_OR_UPDATE_OWNER_FORM = "owners/createOrUpdateOwnerForm";
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

  @GetMapping("/find")
  public String initFindForm(Model model) {
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

  @GetMapping("/{id}/edit")
  public String initUpdateForm(@PathVariable Long id, Model model) {
    Owner owner = ownerService.findById(id);
    if (owner == null) throw new RuntimeException("Owner not found");
    model.addAttribute("owner", owner);
    return VIEW_CREATE_OR_UPDATE_OWNER_FORM;
  }

  @PostMapping("/{id}/edit")
  public String processUpdateForm(@PathVariable Long id, @Valid Owner owner, BindingResult result) {
    if (result.hasErrors()) return VIEW_CREATE_OR_UPDATE_OWNER_FORM;
    owner.setId(id);
    ownerService.save(owner);
    return "redirect:/owners/" + owner.getId();
  }

  @GetMapping("/new")
  public String initCreationForm(Model model) {
    model.addAttribute("owner", new Owner());
    return VIEW_CREATE_OR_UPDATE_OWNER_FORM;
  }

  @PostMapping("/new")
  public String processCreationForm(@Valid Owner owner, BindingResult result) {
    if (result.hasErrors()) return VIEW_CREATE_OR_UPDATE_OWNER_FORM;
    Owner saved = ownerService.save(owner);
    return "redirect:/owners/" + saved.getId();
  }
}
