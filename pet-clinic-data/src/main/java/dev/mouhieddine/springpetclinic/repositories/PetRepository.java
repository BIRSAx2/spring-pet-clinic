package dev.mouhieddine.springpetclinic.repositories;

import dev.mouhieddine.springpetclinic.model.Pet;
import org.springframework.data.repository.CrudRepository;

/**
 * @author : Mouhieddine.dev
 * @created : 11/30/2020, Monday
 **/
public interface PetRepository extends CrudRepository<Pet, Long> {
}
