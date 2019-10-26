/*
 * Copyright 2002-2017 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.quarkus.samples.petclinic.vets.web;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import io.quarkus.beans.factory.annotation.Autowired;
import io.quarkus.boot.test.autoconfigure.web.servlet.WebMvcTest;
import io.quarkus.boot.test.mock.mockito.MockBean;
import io.quarkus.http.MediaType;
import io.quarkus.samples.petclinic.vets.model.Vet;
import io.quarkus.samples.petclinic.vets.model.VetRepository;
import io.quarkus.test.context.ActiveProfiles;
import io.quarkus.test.context.junit.jupiter.QuarkusExtension;
import io.quarkus.test.web.servlet.MockMvc;

import static java.util.Arrays.asList;
import static org.mockito.BDDMockito.given;
import static io.quarkus.test.web.servlet.request.MockMvcRequestBuilders.get;
import static io.quarkus.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static io.quarkus.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author Maciej Szarlinski
 */
@ExtendWith(QuarkusExtension.class)
@WebMvcTest(VetResource.class)
@ActiveProfiles("test")
class VetResourceTest {

    @Autowired
    MockMvc mvc;

    @MockBean
    VetRepository vetRepository;

    @Test
    void shouldGetAListOfVets() throws Exception {

        Vet vet = new Vet();
        vet.setId(1);

        given(vetRepository.findAll()).willReturn(asList(vet));

        mvc.perform(get("/vets").accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$[0].id").value(1));
    }
}
