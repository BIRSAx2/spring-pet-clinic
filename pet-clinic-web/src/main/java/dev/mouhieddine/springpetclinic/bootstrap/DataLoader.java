package dev.mouhieddine.springpetclinic.bootstrap;

import dev.mouhieddine.springpetclinic.model.*;
import dev.mouhieddine.springpetclinic.services.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataLoader implements CommandLineRunner {

  private final OwnerService ownerService;
  private final VetService vetService;
  private final PetTypeService petTypeService;
  private final SpecialtyService specialtyService;
  private final VisitService visitService;
  private final PetService petService;

  public DataLoader(OwnerService ownerService, VetService vetService, PetTypeService petTypeService, SpecialtyService specialtyService, VisitService visitService, PetService petService) {
    this.ownerService = ownerService;
    this.vetService = vetService;
    this.petTypeService = petTypeService;
    this.specialtyService = specialtyService;
    this.visitService = visitService;
    this.petService = petService;
  }

  @Override
  public void run(String... args) throws Exception {

    int count = petTypeService.findAll().size();
    if (count == 0) loadData();

  }

  private void loadData() {
    System.out.println("Loading Pet Types...");

    PetType cat = new PetType();
    cat.setName("Cat");
    PetType savedCatPetType = petTypeService.save(cat);

    PetType dog = new PetType();
    dog.setName("Dog");
    PetType savedDogPetType = petTypeService.save(dog);

    System.out.println("Loading specialties...");

    Specialty radiology = new Specialty();
    radiology.setDescription("Radiology");
    Specialty savedRadiology = specialtyService.save(radiology);


    Specialty surgery = new Specialty();
    surgery.setDescription("Surgery");
    Specialty savedSurgery = specialtyService.save(surgery);

    Specialty dentistry = new Specialty();
    dentistry.setDescription("Dentistry");
    Specialty savedDentistry = specialtyService.save(dentistry);

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
    vet1.getSpecialties().add(savedSurgery);

    Vet vet2 = new Vet();
    vet2.setFirstName("Jennifer");
    vet2.setLastName("Aniston");
    vet2.getSpecialties().add(savedRadiology);
    vetService.save(vet2);

    System.out.println("Loading pets...");
    Pet pet1 = new Pet();
    pet1.setPetType(cat);
    pet1.setBirthDate(LocalDate.now());
    pet1.setName("Tom");
    pet1.setOwner(owner1);
    petService.save(pet1);
    owner1.getPets().add(pet1);


    Pet pet2 = new Pet();
    pet2.setPetType(dog);
    pet2.setBirthDate(LocalDate.now());
    pet2.setName("Spike");
    pet2.setOwner(owner2);
    petService.save(pet2);
    owner2.getPets().add(pet2);

    System.out.println("Loading visits...");
    Visit visit1 = new Visit();
    visit1.setDate(LocalDate.now());
    visit1.setDescription("Tom Visit n. 1");
    visit1.setPet(pet1);
//    pet1.getVisits().add(visit1);
    visitService.save(visit1);

    Visit visit2 = new Visit();
    visit2.setDate(LocalDate.now());
    visit2.setDescription("Spike Visit n. 1");
    visit2.setPet(pet2);
//    pet1.getVisits().add(visit1);
    visitService.save(visit2);


  }
}
