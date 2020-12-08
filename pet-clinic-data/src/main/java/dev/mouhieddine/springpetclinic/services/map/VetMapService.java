package dev.mouhieddine.springpetclinic.services.map;

import dev.mouhieddine.springpetclinic.model.Vet;
import dev.mouhieddine.springpetclinic.services.SpecialtyService;
import dev.mouhieddine.springpetclinic.services.VetService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@Profile({"default","map-based-services"})
public class VetMapService extends AbstractMapService<Vet, Long> implements VetService {

  private SpecialtyService specialtyService;

  public VetMapService(SpecialtyService specialtyService) {
    this.specialtyService = specialtyService;
  }

  @Override
  public Set<Vet> findAll() {
    return super.findAll();
  }

  @Override
  public Vet findById(Long id) {
    return super.findById(id);
  }

  @Override
  public Vet save(Vet object) {
    if (object == null) return null;
    if(object.getSpecialties() == null) throw new RuntimeException("Vet object must have at least one specialty");
    if (object.getSpecialties().size() > 0) {
      object.getSpecialties().forEach(specialty -> {
        if (specialty.getId() == null)
          specialty.setId(specialtyService.save(specialty).getId()); // setting the specialty id to the saved specialty id
      });
    }
    return super.save(object);
  }

  @Override
  public void delete(Vet object) {
    super.delete(object);
  }

  @Override
  public void deleteById(Long id) {
    super.deleteById(id);
  }
}
