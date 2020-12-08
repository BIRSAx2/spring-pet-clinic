package dev.mouhieddine.springpetclinic.services.springdatajpa;

import dev.mouhieddine.springpetclinic.model.Pet;
import dev.mouhieddine.springpetclinic.repositories.PetRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * @author : Mouhieddine.dev
 * @since : 12/8/2020, Tuesday
 **/

@ExtendWith(MockitoExtension.class)
class PetSDJpaServiceTest {
  final String PET_NAME = "Tom";
  final Long PET_ID = 1L;
  @Mock
  PetRepository petRepository;
  @InjectMocks
  PetSDJpaService service;
  private Pet returnPet;

  @BeforeEach
  void setUp() {
    returnPet = Pet.builder().id(PET_ID).build();
  }

  @Test
  void findAll() {
    Set<Pet> petSet = new HashSet<>();
    petSet.add(returnPet);
    petSet.add(Pet.builder().id(2L).build());

    when(petRepository.findAll()).thenReturn(petSet);

    Set<Pet> returnSet = service.findAll();

    assertNotNull(returnSet);
    assertEquals(petSet.size(), returnSet.size());
  }

  @Test
  void findById() {
    when(petRepository.findById(anyLong())).thenReturn(Optional.of(returnPet));

    Pet pet = service.findById(PET_ID);

    assertNotNull(pet);
    assertEquals(returnPet.getId(), pet.getId());
  }

  @Test
  void save() {
    when(petRepository.save(any())).thenReturn(returnPet);
    Pet savedPet = service.save(returnPet);
    assertEquals(returnPet.getId(), savedPet.getId());
  }

  @Test
  void delete() {
    service.delete(returnPet);
    verify(petRepository).delete(any());
  }

  @Test
  void deleteById() {
    service.deleteById(PET_ID);
    verify(petRepository).deleteById(any());
  }
}