package dev.mouhieddine.springpetclinic.services.springdatajpa;

import dev.mouhieddine.springpetclinic.model.Specialty;
import dev.mouhieddine.springpetclinic.repositories.SpecialtyRepository;
import dev.mouhieddine.springpetclinic.services.SpecialtyService;
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
public class SpecialtySDJpaService implements SpecialtyService {
  private final SpecialtyRepository specialtyRepository;

  public SpecialtySDJpaService(SpecialtyRepository specialtyRepository) {
    this.specialtyRepository = specialtyRepository;
  }

  @Override
  public Set<Specialty> findAll() {
    Set<Specialty> specialties = new HashSet<>();
    specialtyRepository.findAll().forEach(specialties::add);
    return specialties;
  }

  @Override
  public Specialty findById(Long id) {
    return specialtyRepository.findById(id).orElse(null);
  }

  @Override
  public Specialty save(Specialty owner) {
    return specialtyRepository.save(owner);
  }

  @Override
  public void delete(Specialty owner) {
    specialtyRepository.delete(owner);
  }

  @Override
  public void deleteById(Long id) {
    specialtyRepository.deleteById(id);
  }
}
