package dev.mouhieddine.springpetclinic.services.map;

import dev.mouhieddine.springpetclinic.model.Owner;
import dev.mouhieddine.springpetclinic.services.OwnerService;
import dev.mouhieddine.springpetclinic.services.PetService;
import dev.mouhieddine.springpetclinic.services.PetTypeService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@Profile({"default", "map-based-services"})
public class OwnerMapService extends AbstractMapService<Owner, Long> implements OwnerService {

  private final PetTypeService petTypeService;
  private final PetService petService;

  public OwnerMapService(PetTypeService petTypeService, PetService petService) {
    this.petTypeService = petTypeService;
    this.petService = petService;
  }

  @Override
  public Set<Owner> findAll() {
    return super.findAll();
  }

  @Override
  public Owner findById(Long id) {
    return super.findById(id);
  }

  @Override
  public Owner save(Owner object) {
    if (object == null) return null;

    if (object.getPets() != null) {
      object.getPets().forEach(pet -> {
        if (pet.getPetType() == null) throw new RuntimeException("Pet Type Required!");
        // here we are getting the PetType that has been assigned and saving it so we can get the Id assigned to the map object
        if (pet.getPetType().getId() == null) pet.setPetType(petTypeService.save(pet.getPetType()));
        if (pet.getId() == null) pet.setId(petService.save(pet).getId()); // setting the petId to the saved pet id

      });
    }
    return super.save(object);
  }

  @Override
  public void delete(Owner object) {
    super.delete(object);
  }

  @Override
  public void deleteById(Long id) {
    super.deleteById(id);
  }

  @Override
  public Owner findByLastName(String lastName) {
    return this.findAll()
            .stream()
            .filter(owner -> owner.getLastName().equalsIgnoreCase(lastName))
            .findFirst()
            .orElse(null);
  }

  @Override
  public Set<Owner> findAllByLastNameLike(String lastname) {
    // todo implement this
    return null;
  }
}
