package dev.mouhieddine.springpetclinic.model;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor

@Entity
@Table(name = "owners")
public class Owner extends Person {

  @Column(name = "address")
  private String address;

  @Column(name = "city")
  private String City;

  @Column(name = "telephone")
  private String telephone;

  @OneToMany(cascade = CascadeType.ALL, mappedBy = "owner")
  private Set<Pet> pets = new HashSet<>();

  @Builder
  public Owner(String firstName, String lastName, String address, String city, String telephone, Set<Pet> pets) {
    super(firstName, lastName);
    this.address = address;
    City = city;
    this.telephone = telephone;
    this.pets = pets;
  }

  public Owner(String address, String city, String telephone, Set<Pet> pets) {
    this.address = address;
    City = city;
    this.telephone = telephone;
    this.pets = pets;
  }
}
