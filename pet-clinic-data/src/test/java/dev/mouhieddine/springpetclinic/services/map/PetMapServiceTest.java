package dev.mouhieddine.springpetclinic.services.map;

import dev.mouhieddine.springpetclinic.model.Owner;
import dev.mouhieddine.springpetclinic.model.Pet;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author : Mouhieddine.dev
 * @since : 12/7/2020, Monday
 **/
class PetMapServiceTest {

  private final Long PET_ID = 1L;
  private PetMapService petMapService;
  @BeforeEach
  void setUp() {
    petMapService = new PetMapService();
    petMapService.save(Pet.builder().id(PET_ID).build());
  }

  @Test
  void findAll() {
    Set<Pet> pets = petMapService.findAll();
    assertEquals(1,pets.size());
  }

  @Test
  void findById() {
    Pet foundPet = petMapService.findById(PET_ID);
    assertEquals(PET_ID,foundPet.getId());
  }

  @Test
  void saveExistingId() {
    Pet savedPet = petMapService.save(Pet.builder().id(PET_ID).build());
    assertEquals(PET_ID,savedPet.getId());
  }
  @Test
  void saveNoId() {
    Pet savedPet = petMapService.save(Pet.builder().build());
    assertNotNull(savedPet);
    assertNotNull(savedPet.getId());
  }

  @Test
  void delete() {
    petMapService.delete(petMapService.findById(PET_ID));
    assertEquals(0,petMapService.findAll().size());
  }

  @Test
  void deleteById() {
    petMapService.deleteById(PET_ID);
    assertEquals(0,petMapService.findAll().size());
  }
}