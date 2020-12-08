package dev.mouhieddine.springpetclinic.services.map;

import dev.mouhieddine.springpetclinic.model.Pet;
import dev.mouhieddine.springpetclinic.model.PetType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author : Mouhieddine.dev
 * @since : 12/7/2020, Monday
 **/
class PetTypeMapServiceTest {

  private final Long PET_TYPE_ID = 1L;
  private PetTypeMapService petTypeMapService;
  @BeforeEach
  void setUp() {
    petTypeMapService = new PetTypeMapService();
    petTypeMapService.save(PetType.builder().id(PET_TYPE_ID).build());
  }

  @Test
  void findAll() {
    Set<PetType> pets = petTypeMapService.findAll();
    assertEquals(1,pets.size());
  }

  @Test
  void findById() {
    PetType foundPetType = petTypeMapService.findById(PET_TYPE_ID);
    assertEquals(PET_TYPE_ID,foundPetType.getId());
  }

  @Test
  void saveExistingId() {
    PetType savedPetType = petTypeMapService.save(PetType.builder().id(PET_TYPE_ID).build());
    assertEquals(PET_TYPE_ID,savedPetType.getId());
  }
  @Test
  void saveNoId() {
    PetType savedPetType = petTypeMapService.save(PetType.builder().build());
    assertNotNull(savedPetType);
    assertNotNull(savedPetType.getId());
  }

  @Test
  void delete() {
    petTypeMapService.delete(petTypeMapService.findById(PET_TYPE_ID));
    assertEquals(0, petTypeMapService.findAll().size());
  }

  @Test
  void deleteById() {
    petTypeMapService.deleteById(PET_TYPE_ID);
    assertEquals(0, petTypeMapService.findAll().size());
  }
}
