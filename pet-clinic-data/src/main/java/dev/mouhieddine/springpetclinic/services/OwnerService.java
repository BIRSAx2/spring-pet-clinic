package dev.mouhieddine.springpetclinic.services;

import dev.mouhieddine.springpetclinic.model.Owner;

import java.util.Set;

public interface OwnerService extends CrudService<Owner, Long> {

  Owner findByLastName(String lastName);

  Set<Owner> findAllByLastNameLike(String lastname);

}
