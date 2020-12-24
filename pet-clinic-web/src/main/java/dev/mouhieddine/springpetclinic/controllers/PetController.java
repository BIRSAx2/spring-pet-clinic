package dev.mouhieddine.springpetclinic.controllers;

import dev.mouhieddine.springpetclinic.model.Owner;
import dev.mouhieddine.springpetclinic.model.Pet;
import dev.mouhieddine.springpetclinic.model.PetType;
import dev.mouhieddine.springpetclinic.services.OwnerService;
import dev.mouhieddine.springpetclinic.services.PetService;
import dev.mouhieddine.springpetclinic.services.PetTypeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Set;

/**
 * @author : Mouhieddine.dev
 * @since : 12/23/2020, Wednesday
 **/
@Controller
@RequestMapping("/owners/{ownerId}")
@Slf4j
public class PetController {
  private final static String VIEW_CREATE_OR_UPDATE_PET_FORM = "pets/createOrUpdatePetForm";

  private final PetService petService;
  private final OwnerService ownerService;
  private final PetTypeService petTypeService;

  public PetController(PetService petService, OwnerService ownerService, PetTypeService petTypeService) {
    this.petService = petService;
    this.ownerService = ownerService;
    this.petTypeService = petTypeService;
  }

  @ModelAttribute("types")
  public Set<PetType> populatePetTypes() {
    return petTypeService.findAll();
  }

  @ModelAttribute("owner")
  public Owner findOwner(@PathVariable("ownerId") Long id) {
    return ownerService.findById(id);
  }

  @InitBinder("owner")
  public void initOwnerBinder(WebDataBinder dataBinder) {
    dataBinder.setDisallowedFields("id");
  }

  @GetMapping("/pets/new")
  public String initCreationForm(Owner owner, Model model) {
    Pet pet = new Pet();
    owner.addPet(pet);
    model.addAttribute("pet", pet);
    return VIEW_CREATE_OR_UPDATE_PET_FORM;
  }

  @PostMapping("/pets/new")
  public String processCreationForm(Owner owner, @Valid Pet pet, BindingResult result, Model model) {
    if (StringUtils.hasLength(pet.getName()) && pet.isNew() && owner.getPetByName(pet.getName(), true) != null)
      result.rejectValue("name", "duplicate", "already exists");
    owner.addPet(pet);
    if (result.hasErrors()) {
      model.addAttribute("pet", pet);
      return VIEW_CREATE_OR_UPDATE_PET_FORM;
    }
    petService.save(pet);
    return "redirect:/owners/" + owner.getId();
  }

  @GetMapping("/pets/{petId}/edit")
  public String initUpdateForm(@PathVariable("petId") Long id, Model model) {
    Pet pet = petService.findById(id);
    if (pet == null) throw new RuntimeException("Pet not found");
    model.addAttribute("pet", pet);
    return VIEW_CREATE_OR_UPDATE_PET_FORM;
  }

  @PostMapping("/pets/{petId}/edit")
  public String processUpdateForm(Owner owner, @Valid Pet pet, BindingResult result, Model model) {
    if (result.hasErrors()) {
      pet.setOwner(owner);
      model.addAttribute("pet", pet);
      return VIEW_CREATE_OR_UPDATE_PET_FORM;
    }
    owner.addPet(pet);
    petService.save(pet);
    model.addAttribute("pet", pet);
    return "redirect:/owners/" + owner.getId();
  }
}
