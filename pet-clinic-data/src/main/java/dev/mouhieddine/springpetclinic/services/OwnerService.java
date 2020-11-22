package dev.mouhieddine.springpetclinic.services;

import dev.mouhieddine.springpetclinic.model.Owner;

public interface OwnerService extends CrudService<Owner, Long> {

    Owner findByLastName(String lastName);

}
