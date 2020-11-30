package dev.mouhieddine.springpetclinic.model;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder

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
}
