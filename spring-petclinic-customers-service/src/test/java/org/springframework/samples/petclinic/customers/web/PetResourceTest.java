package io.quarkus.samples.petclinic.customers.web;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import io.quarkus.beans.factory.annotation.Autowired;
import io.quarkus.boot.test.autoconfigure.web.servlet.WebMvcTest;
import io.quarkus.boot.test.mock.mockito.MockBean;
import io.quarkus.http.MediaType;
import io.quarkus.samples.petclinic.customers.model.Owner;
import io.quarkus.samples.petclinic.customers.model.OwnerRepository;
import io.quarkus.samples.petclinic.customers.model.Pet;
import io.quarkus.samples.petclinic.customers.model.PetRepository;
import io.quarkus.samples.petclinic.customers.model.PetType;
import io.quarkus.test.context.ActiveProfiles;
import io.quarkus.test.context.junit.jupiter.QuarkusExtension;
import io.quarkus.test.web.servlet.MockMvc;


import static org.mockito.BDDMockito.given;
import static io.quarkus.test.web.servlet.request.MockMvcRequestBuilders.get;
import static io.quarkus.test.web.servlet.result.MockMvcResultMatchers.content;
import static io.quarkus.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static io.quarkus.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author Maciej Szarlinski
 */
@ExtendWith(QuarkusExtension.class)
@WebMvcTest(PetResource.class)
@ActiveProfiles("test")
class PetResourceTest {

    @Autowired
    MockMvc mvc;

    @MockBean
    PetRepository petRepository;

    @MockBean
    OwnerRepository ownerRepository;

    @Test
    void shouldGetAPetInJSonFormat() throws Exception {

        Pet pet = setupPet();

        given(petRepository.findById(2)).willReturn(Optional.of(pet));


        mvc.perform(get("/owners/2/pets/2").accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(content().contentType("application/json;charset=UTF-8"))
            .andExpect(jsonPath("$.id").value(2))
            .andExpect(jsonPath("$.name").value("Basil"))
            .andExpect(jsonPath("$.type.id").value(6));
    }

    private Pet setupPet() {
        Owner owner = new Owner();
        owner.setFirstName("George");
        owner.setLastName("Bush");

        Pet pet = new Pet();

        pet.setName("Basil");
        pet.setId(2);

        PetType petType = new PetType();
        petType.setId(6);
        pet.setType(petType);

        owner.addPet(pet);
        return pet;
    }
}
