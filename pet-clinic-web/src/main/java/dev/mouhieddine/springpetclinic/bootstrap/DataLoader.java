package dev.mouhieddine.springpetclinic.bootstrap;

import dev.mouhieddine.springpetclinic.model.Owner;
import dev.mouhieddine.springpetclinic.model.Vet;
import dev.mouhieddine.springpetclinic.services.OwnerService;
import dev.mouhieddine.springpetclinic.services.VetService;
import dev.mouhieddine.springpetclinic.services.map.OwnerServiceMap;
import dev.mouhieddine.springpetclinic.services.map.VetServiceMap;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;


    public DataLoader(OwnerService ownerService, VetService vetService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
    }

    @Override
    public void run(String... args) throws Exception {

        System.out.println("Loading owners...");

        Owner owner1 = new Owner();
        owner1.setId(1L);
        owner1.setFirstName("Mouhieddine");
        owner1.setLastName("Sabir");

        ownerService.save(owner1);

        Owner owner3 = new Owner();
        owner3.setId(2L);
        owner3.setFirstName("Benicio");
        owner3.setLastName("Del Toro");

        ownerService.save(owner3);

        System.out.println("Loading vets ...");

        Vet vet1 = new Vet();
        vet1.setId(1L);
        vet1.setFirstName("Brad");
        vet1.setLastName("Pitt");
        vetService.save(vet1);

        Vet vet2 = new Vet();
        vet2.setId(2L);
        vet2.setFirstName("Jennifer");
        vet2.setLastName("Aniston");
        vetService.save(vet2);


    }
}
