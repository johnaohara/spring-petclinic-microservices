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
package io.quarkus.samples.petclinic.visits.web;

import java.util.List;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import io.quarkus.samples.petclinic.visits.model.Visit;
import org.eclipse.microprofile.metrics.annotation.Timed;
import org.jboss.logging.Logger;

/**
 * @author John O'Hara
 */
@Timed(name = "petclinic.visit")
@ApplicationScoped
@Consumes("application/json")
@Produces("application/json")
public class VisitResource {

    @Inject
    Logger log;

    @POST
    @Path("owners/*/pets/{petId}/visits")
    @Transactional
    public Visit create(
        @Valid Visit visit,
        @PathParam("petId") int petId) {

        visit.setPetId(petId);
        log.infof("Saving visit {}", visit);
        visit.persist();
        return visit;
    }

    @GET
    @Path("owners/*/pets/{petId}/visits")
    public List<Visit> visits(@PathParam("petId") int petId) {
        return Visit.findById(petId);
    }

    @GET
    @Path("pets/visits")
    public Visits visitsMultiGet(@QueryParam("petId") List<Integer> petIds) {
        final List<Visit> byPetIdIn = Visit.findByPetIdIn(petIds);
        return new Visits(byPetIdIn);
    }

    static class Visits {
        private final List<Visit> items;

        Visits(List<Visit> items) {
            this.items = items;
        }
    }
}
