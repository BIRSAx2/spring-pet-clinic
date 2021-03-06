package dev.mouhieddine.springpetclinic.services.map;

import dev.mouhieddine.springpetclinic.model.Visit;
import dev.mouhieddine.springpetclinic.services.VisitService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * @author : Mouhieddine.dev
 * @created : 11/30/2020, Monday
 **/
@Service
@Profile({"default","map-based-services"})
public class VisitMapService extends AbstractMapService<Visit, Long> implements VisitService {


  @Override
  public Set<Visit> findAll() {
    return super.findAll();
  }

  @Override
  public Visit findById(Long id) {
    return super.findById(id);
  }

  @Override
  public Visit save(Visit visit) {
    if (visit.getPet() == null || visit.getPet().getOwner() == null || visit.getPet().getId() == null)
      throw new RuntimeException("Invalid Visit object: Visit object must have a valid property pet.");
    return super.save(visit);
  }

  @Override
  public void delete(Visit visit) {
    super.delete(visit);
  }

  @Override
  public void deleteById(Long id) {
    super.deleteById(id);
  }

}
