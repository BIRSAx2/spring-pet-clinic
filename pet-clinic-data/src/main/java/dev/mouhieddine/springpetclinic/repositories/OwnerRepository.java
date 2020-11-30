package dev.mouhieddine.springpetclinic.repositories;

import dev.mouhieddine.springpetclinic.model.Owner;
import org.springframework.data.repository.CrudRepository;

/**
 * @author : Mouhieddine.dev
 * @created : 11/30/2020, Monday
 **/
public interface OwnerRepository extends CrudRepository<Owner, Long> {
  Owner findByLastName(String lastName);
}
