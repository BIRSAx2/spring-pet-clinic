package dev.mouhieddine.springpetclinic.services.map;

import dev.mouhieddine.springpetclinic.model.Owner;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author : Mouhieddine.dev
 * @since : 12/7/2020, Monday
 **/
class OwnerMapServiceTest {
  private OwnerMapService ownerMapService;
  private final Long OWNER_ID = 1L;
  private final String LAST_NAME = "Sabir";

  @BeforeEach
  void setUp() {
    ownerMapService = new OwnerMapService(new PetTypeMapService(), new PetMapService());
    ownerMapService.save(Owner.builder().id(OWNER_ID).lastName("Sabir").build());
  }

  @Test
  void findAll() {
    Set<Owner> ownerSet = ownerMapService.findAll();
    assertEquals(1, ownerSet.size());
  }

  @Test
  void findById() {
    assertEquals(OWNER_ID, ownerMapService.findById(OWNER_ID).getId());
  }

  @Test
  void saveExistingId() {
    Owner savedOwner = ownerMapService.save(Owner.builder().id(OWNER_ID).build());
    assertEquals(OWNER_ID, savedOwner.getId());
  }

  @Test
  void saveNoId() {
    Owner savedOwner = ownerMapService.save(Owner.builder().build());
    assertNotNull(savedOwner);
    assertNotNull(savedOwner.getId());
  }

  @Test
  void delete() {
    ownerMapService.delete(ownerMapService.findById(OWNER_ID));
    assertEquals(0,ownerMapService.findAll().size());
  }

  @Test
  void deleteById() {
    ownerMapService.deleteById(OWNER_ID);
    assertEquals(0,ownerMapService.findAll().size());
  }

  @Test
  void findByLastName() {
    Owner owner = ownerMapService.findByLastName(LAST_NAME);
    assertNotNull(owner);
    assertEquals(OWNER_ID,owner.getId());
  }

  @Test
  void findByLastNameNotFound() {
    Owner owner = ownerMapService.findByLastName("a");
    assertNull(owner);
  }
}