package dev.mouhieddine.springpetclinic.repositories;

import dev.mouhieddine.springpetclinic.model.Visit;
import org.springframework.data.repository.CrudRepository;

/**
 * @author : Mouhieddine.dev
 * @created : 11/30/2020, Monday
 **/
public interface VisitRepository extends CrudRepository<Visit, Long> {
}
