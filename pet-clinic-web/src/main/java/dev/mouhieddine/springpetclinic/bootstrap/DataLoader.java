package dev.mouhieddine.springpetclinic.bootstrap;

import dev.mouhieddine.springpetclinic.model.Owner;
import dev.mouhieddine.springpetclinic.model.Pet;
import dev.mouhieddine.springpetclinic.model.PetType;
import dev.mouhieddine.springpetclinic.model.Vet;
import dev.mouhieddine.springpetclinic.services.OwnerService;
import dev.mouhieddine.springpetclinic.services.PetTypeService;
import dev.mouhieddine.springpetclinic.services.VetService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataLoader implements CommandLineRunner {

  private final OwnerService ownerService;
  private final VetService vetService;
  private final PetTypeService petTypeService;


  public DataLoader(OwnerService ownerService, VetService vetService, PetTypeService petTypeService) {
    this.ownerService = ownerService;
    this.vetService = vetService;
    this.petTypeService = petTypeService;
  }

  @Override
  public void run(String... args) throws Exception {

    System.out.println("Loading Pet Types...");

    PetType cat = new PetType();
    cat.setName("Cat");
    PetType savedCatPetType = petTypeService.save(cat);

    PetType dog = new PetType();
    dog.setName("Dog");
    PetType savedDogPetType = petTypeService.save(dog);

    System.out.println("Loading owners...");

    Owner owner1 = new Owner();
    owner1.setFirstName("Mouhieddine");
    owner1.setLastName("Sabir");
    owner1.setAddress("Via Borgo Rossi");
    owner1.setCity("Padua");
    owner1.setTelephone("1234567890");

    ownerService.save(owner1);

    Owner owner2 = new Owner();
    owner2.setFirstName("Benicio");
    owner2.setLastName("Del Toro");
    owner2.setAddress("I don't know");
    owner2.setCity("Los Angeles");
    owner2.setTelephone("0987654321");

    ownerService.save(owner2);

    System.out.println("Loading vets...");

    Vet vet1 = new Vet();
    vet1.setFirstName("Brad");
    vet1.setLastName("Pitt");
    vetService.save(vet1);

    Vet vet2 = new Vet();
    vet2.setFirstName("Jennifer");
    vet2.setLastName("Aniston");
    vetService.save(vet2);

    System.out.println("Loading pets...");
    Pet pet1 = new Pet();
    pet1.setPetType(cat);
    pet1.setBirthDate(LocalDate.now());
    pet1.setName("Tom");
    pet1.setOwner(owner1);
    owner1.getPets().add(pet1);

    Pet pet2 = new Pet();
    pet2.setPetType(dog);
    pet2.setBirthDate(LocalDate.now());
    pet2.setName("Spike");
    pet2.setOwner(owner2);
    owner2.getPets().add(pet2);

  }
}
