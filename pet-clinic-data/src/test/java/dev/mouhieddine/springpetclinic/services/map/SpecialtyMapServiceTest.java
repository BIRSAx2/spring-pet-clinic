package dev.mouhieddine.springpetclinic.services.map;

import dev.mouhieddine.springpetclinic.model.Specialty;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * @author : Mouhieddine.dev
 * @since : 12/7/2020, Monday
 **/
class SpecialtyMapServiceTest {

  private final Long SPECIALTY_ID = 1L;
  private SpecialtyMapService specialtyMapService;

  @BeforeEach
  void setUp() {
    specialtyMapService = new SpecialtyMapService();
    specialtyMapService.save(Specialty.builder().id(SPECIALTY_ID).build());
  }

  @Test
  void findAll() {
    Set<Specialty> pets = specialtyMapService.findAll();
    assertEquals(1, pets.size());
  }

  @Test
  void findById() {
    Specialty foundSpecialty = specialtyMapService.findById(SPECIALTY_ID);
    assertEquals(SPECIALTY_ID, foundSpecialty.getId());
  }

  @Test
  void saveExistingId() {
    Specialty savedSpecialty = specialtyMapService.save(Specialty.builder().id(SPECIALTY_ID).build());
    assertEquals(SPECIALTY_ID, savedSpecialty.getId());
  }

  @Test
  void saveNoId() {
    Specialty savedSpecialty = specialtyMapService.save(Specialty.builder().build());
    assertNotNull(savedSpecialty);
    assertNotNull(savedSpecialty.getId());
  }

  @Test
  void delete() {
    specialtyMapService.delete(specialtyMapService.findById(SPECIALTY_ID));
    assertEquals(0, specialtyMapService.findAll().size());
  }

  @Test
  void deleteById() {
    specialtyMapService.deleteById(SPECIALTY_ID);
    assertEquals(0, specialtyMapService.findAll().size());
  }

}