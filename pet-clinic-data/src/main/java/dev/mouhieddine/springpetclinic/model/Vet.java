package dev.mouhieddine.springpetclinic.model;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor

@Entity
@Table(name = "vets")
public class Vet extends Person {

  @Column(name = "specialties")
  @ManyToMany(fetch = FetchType.EAGER) // loads everything as once no lazy loading
  @JoinTable(name = "vet_specialties",
          joinColumns = @JoinColumn(name = "vet_id"),
          inverseJoinColumns = @JoinColumn(name = "speciality_id")
  )

  private Set<Specialty> specialties = new HashSet<>();

  @Builder
  public Vet(Long id,String firstName, String lastName, Set<Specialty> specialties) {
    super(id,firstName, lastName);
    this.specialties = specialties;
  }
}
