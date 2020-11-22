package dev.mouhieddine.springpetclinic.services;

import dev.mouhieddine.springpetclinic.model.Pet;

import java.util.Set;

public interface PetService {

    Pet findById(Long id);

    Pet save(Pet pet);

    Set<Pet> findAll();
}
