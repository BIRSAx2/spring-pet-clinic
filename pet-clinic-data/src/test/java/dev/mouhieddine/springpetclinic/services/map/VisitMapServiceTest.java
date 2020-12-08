package dev.mouhieddine.springpetclinic.services.map;

import dev.mouhieddine.springpetclinic.model.Owner;
import dev.mouhieddine.springpetclinic.model.Pet;
import dev.mouhieddine.springpetclinic.model.Specialty;
import dev.mouhieddine.springpetclinic.model.Visit;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author : Mouhieddine.dev
 * @since : 12/8/2020, Tuesday
 **/
class VisitMapServiceTest {

  private final Long VISIT_ID = 1L;
  private VisitMapService visitMapService;
  private Pet pet;
  private Owner owner;

  @BeforeEach
  void setUp() {
    // Loading an Owner and a Pet
    owner = Owner.builder().id(1L).build();
    pet = Pet.builder().id(1L).owner(owner).build();

    visitMapService = new VisitMapService();
    visitMapService.save(Visit.builder().id(VISIT_ID).pet(pet).build());
  }

  @Test
  void findAll() {
    Set<Visit> visits = visitMapService.findAll();
    assertEquals(1, visits.size());
  }

  @Test
  void findById() {
    Visit foundVisit = visitMapService.findById(VISIT_ID);
    assertEquals(VISIT_ID, foundVisit.getId());
  }

  @Test
  void saveExistingId() {
    Visit savedVisit = visitMapService.save(Visit.builder().id(VISIT_ID).pet(pet).build());
    assertEquals(VISIT_ID, savedVisit.getId());
  }

  @Test
  void saveNoId() {
    Visit savedVisit = visitMapService.save(Visit.builder().pet(pet).build());
    assertNotNull(savedVisit);
    assertNotNull(savedVisit.getId());
  }

  @Test
  void delete() {
    visitMapService.delete(visitMapService.findById(VISIT_ID));
    assertEquals(0, visitMapService.findAll().size());
  }

  @Test
  void deleteById() {
    visitMapService.deleteById(VISIT_ID);
    assertEquals(0, visitMapService.findAll().size());
  }
}