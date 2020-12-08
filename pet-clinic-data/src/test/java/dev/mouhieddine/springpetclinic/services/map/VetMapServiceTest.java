package dev.mouhieddine.springpetclinic.services.map;

import dev.mouhieddine.springpetclinic.model.Specialty;
import dev.mouhieddine.springpetclinic.model.Vet;
import dev.mouhieddine.springpetclinic.services.SpecialtyService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * @author : Mouhieddine.dev
 * @since : 12/7/2020, Monday
 **/
class VetMapServiceTest {

  private final Long VET_ID = 1L;
  private VetMapService vetMapService;
  private SpecialtyService specialtyService;
  private Set<Specialty> specialties;

  @BeforeEach
  void setUp() {
    // Loading specialties
    specialtyService = new SpecialtyMapService();
    specialtyService.save(Specialty.builder().id(1L).build());
    this.specialties = specialtyService.findAll();
    // Loading Vets
    vetMapService = new VetMapService(specialtyService);
    vetMapService.save(Vet.builder().id(VET_ID).specialties(specialties).build());
  }

  @Test
  void findAll() {
    Set<Vet> vets = vetMapService.findAll();
    assertEquals(1, vets.size());
  }

  @Test
  void findById() {
    Vet foundVet = vetMapService.findById(VET_ID);
    assertEquals(VET_ID, foundVet.getId());
  }

  @Test
  void saveExistingId() {
    Vet savedVet = vetMapService.save(Vet.builder().id(VET_ID).specialties(specialties).build());
    assertEquals(VET_ID, savedVet.getId());
  }

  @Test
  void saveNoId() {
    Vet savedVet = vetMapService.save(Vet.builder().specialties(specialties).build());
    assertNotNull(savedVet);
    assertNotNull(savedVet.getId());
  }

  @Test
  void delete() {
    vetMapService.delete(vetMapService.findById(VET_ID));
    assertEquals(0, vetMapService.findAll().size());
  }

  @Test
  void deleteById() {
    vetMapService.deleteById(VET_ID);
    assertEquals(0, vetMapService.findAll().size());
  }
}