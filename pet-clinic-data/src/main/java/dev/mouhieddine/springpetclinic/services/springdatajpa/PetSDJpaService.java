package dev.mouhieddine.springpetclinic.services.springdatajpa;

import dev.mouhieddine.springpetclinic.model.Pet;
import dev.mouhieddine.springpetclinic.repositories.PetRepository;
import dev.mouhieddine.springpetclinic.services.PetService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

/**
 * @author : Mouhieddine.dev
 * @created : 11/30/2020, Monday
 **/
@Service
@Profile("springdatajpa")
public class PetSDJpaService implements PetService {
  private final PetRepository petRepository;

  public PetSDJpaService(PetRepository petRepository) {
    this.petRepository = petRepository;
  }

  @Override
  public Set<Pet> findAll() {
    Set<Pet> Pets = new HashSet<>();
    petRepository.findAll().forEach(Pets::add);
    return Pets;
  }

  @Override
  public Pet findById(Long id) {
    return petRepository.findById(id).orElse(null);
  }

  @Override
  public Pet save(Pet Pet) {
    return petRepository.save(Pet);
  }

  @Override
  public void delete(Pet Pet) {
    petRepository.delete(Pet);
  }

  @Override
  public void deleteById(Long id) {
    petRepository.deleteById(id);
  }
}
