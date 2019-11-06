package io.quarkus.samples.petclinic.customers.web;

import io.quarkus.samples.petclinic.customers.model.Owner;
import io.quarkus.samples.petclinic.customers.model.Pet;
import io.quarkus.samples.petclinic.customers.model.PetRepository;
import io.quarkus.samples.petclinic.customers.model.PetType;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;
import javax.ws.rs.core.MediaType;
import java.util.Optional;

import static io.restassured.RestAssured.given;
import static org.hamcrest.core.Is.is;

/**
 * @author Maciej Szarlinski
 */
//@ExtendWith(QuarkusExtension.class)
//@WebMvcTest(PetResource.class)
//@ActiveProfiles("test")

//TODO:: Impl. testing
@QuarkusTest
class PetResourceTest {

//    @Autowired
//    MockMvc mvc;
//
//    @MockBean
    @Inject
    PetRepository petRepository;
//
//    @MockBean
//    OwnerRepository ownerRepository;
//
    @Test
    void shouldGetAPetInJSonFormat() throws Exception {

        given()
            .when().get("/owners/2/pets/2")
            .then()
            .statusCode(200)
            .contentType(MediaType.APPLICATION_JSON);
//            .body(is("hello"));

//        Pet pet = setupPet();
//
//        given(petRepository.findById(2)).willReturn(Optional.of(pet));


//        mvc.perform(get("/owners/2/pets/2").accept(MediaType.APPLICATION_JSON))
//            .andExpect(status().isOk())
//            .andExpect(content().contentType("application/json;charset=UTF-8"))
//            .andExpect(jsonPath("$.id").value(2))
//            .andExpect(jsonPath("$.name").value("Basil"))
//            .andExpect(jsonPath("$.type.id").value(6));
    }

//    private Pet setupPet() {
//        Owner owner = new Owner();
//        owner.setFirstName("George");
//        owner.setLastName("Bush");
//
//        Pet pet = new Pet();
//
//        pet.setName("Basil");
//        pet.setId(2);
//
//        PetType petType = new PetType();
//        petType.setId(6);
//        pet.setType(petType);
//
//        owner.addPet(pet);
//        return pet;
//    }
}
