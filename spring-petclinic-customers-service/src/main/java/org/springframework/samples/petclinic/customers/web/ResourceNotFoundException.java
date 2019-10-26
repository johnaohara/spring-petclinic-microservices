package io.quarkus.samples.petclinic.customers.web;

import io.quarkus.http.HttpStatus;
import io.quarkus.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(String message) {
        super(message);
    }

}
