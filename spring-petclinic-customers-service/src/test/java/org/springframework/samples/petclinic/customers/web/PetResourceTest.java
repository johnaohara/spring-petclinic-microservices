package org.springframework.samples.petclinic.customers.web;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import javax.ws.rs.core.MediaType;

import static io.restassured.RestAssured.given;

/**
 * @author John O'Hara
 */
@QuarkusTest
class PetResourceTest {

    @Test
    void shouldGetAPetInJSonFormat() throws Exception {

        given()
            .when().get("/owners/2/pets/2")
            .then()
            .statusCode(200)
            .contentType(MediaType.APPLICATION_JSON);

    }

}
