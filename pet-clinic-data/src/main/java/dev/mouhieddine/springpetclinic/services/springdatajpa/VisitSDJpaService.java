package dev.mouhieddine.springpetclinic.services.springdatajpa;

import dev.mouhieddine.springpetclinic.model.Visit;
import dev.mouhieddine.springpetclinic.repositories.VisitRepository;
import dev.mouhieddine.springpetclinic.services.VisitService;

import java.util.HashSet;
import java.util.Set;

/**
 * @author : Mouhieddine.dev
 * @created : 11/30/2020, Monday
 **/
public class VisitSDJpaService implements VisitService {

  private final VisitRepository visitRepository;

  public VisitSDJpaService(VisitRepository visitRepository) {
    this.visitRepository = visitRepository;
  }

  @Override
  public Set<Visit> findAll() {
    Set<Visit> visits = new HashSet<>();
    visitRepository.findAll().forEach(visits::add);
    return visits;
  }

  @Override
  public Visit findById(Long id) {
    return visitRepository.findById(id).orElse(null);
  }

  @Override
  public Visit save(Visit visit) {
    return visitRepository.save(visit);
  }

  @Override
  public void delete(Visit visit) {
    visitRepository.delete(visit);
  }

  @Override
  public void deleteById(Long id) {
    visitRepository.deleteById(id);
  }
}
