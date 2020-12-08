package dev.mouhieddine.springpetclinic.services.springdatajpa;

import dev.mouhieddine.springpetclinic.model.Owner;
import dev.mouhieddine.springpetclinic.repositories.OwnerRepository;
import dev.mouhieddine.springpetclinic.repositories.PetRepository;
import dev.mouhieddine.springpetclinic.repositories.PetTypeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

/**
 * @author : Mouhieddine.dev
 * @since : 12/8/2020, Tuesday
 **/

@ExtendWith(MockitoExtension.class) // sets up a junit 5 for Mockito
class OwnerSDJpaServiceTest {
  final String LAST_NAME = "Sabir";
  final Long OWNER_ID = 1L;
  private Owner returnOwner;

  @Mock
  OwnerRepository ownerRepository;
  @InjectMocks
  OwnerSDJpaService service;


  @BeforeEach
  void setUp() {
    returnOwner = Owner.builder().id(OWNER_ID).lastName(LAST_NAME).build();
  }

  @Test
  void findByLastName() {
    when(ownerRepository.findByLastName(any())).thenReturn(returnOwner);
    Owner owner = service.findByLastName(LAST_NAME);
    assertEquals(LAST_NAME, owner.getLastName());

    verify(ownerRepository).findByLastName(any());
  }

  @Test
  void findAll() {
    Set<Owner> returnSet = new HashSet<>();
    returnSet.add(Owner.builder().id(1L).build());
    returnSet.add(Owner.builder().id(2L).build());

    when(ownerRepository.findAll()).thenReturn(returnSet);

    Set<Owner> owners = service.findAll();
    assertNotNull(owners);
    assertEquals(returnSet.size(), owners.size());

  }

  @Test
  void findById() {
    when(ownerRepository.findById(anyLong())).thenReturn(Optional.of(returnOwner));
    Owner owner = service.findById(OWNER_ID);

    assertNotNull(owner);
    assertEquals(returnOwner.getLastName(), owner.getLastName());
  }

  @Test
  void findByIdNotFound() {
    when(ownerRepository.findById(anyLong())).thenReturn(Optional.empty());
    Owner owner = service.findById(OWNER_ID);
    assertNull(null);
  }

  @Test
  void save() {
    when(ownerRepository.save(any())).thenReturn(returnOwner);
    Owner savedOwner = service.save(returnOwner);
    assertNotNull(savedOwner);
    assertEquals(returnOwner.getId(), savedOwner.getId());
  }

  @Test
  void delete() {
    service.delete(returnOwner);
    verify(ownerRepository,times(1)).delete(any());
  }

  @Test
  void deleteById() {
    service.deleteById(OWNER_ID);
    verify(ownerRepository,times(1)).deleteById(anyLong());
  }
}