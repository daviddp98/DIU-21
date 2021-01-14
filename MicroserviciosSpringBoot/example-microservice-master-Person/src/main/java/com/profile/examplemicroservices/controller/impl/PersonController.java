package com.profile.examplemicroservices.controller.impl;

import com.profile.examplemicroservices.controller.PersonApi;
import com.profile.examplemicroservices.model.dto.PersonDTO;
import com.profile.examplemicroservices.service.impl.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PersonController implements PersonApi {

    @Autowired
    private PersonService personService;

    @Override
    public ResponseEntity < List <PersonDTO> > getAll() {
        return ResponseEntity.ok( personService.getAll() );
    }

    @Override
    public ResponseEntity <PersonDTO> create(PersonDTO personDTO) {
        return new ResponseEntity <>( personService.create(personDTO), HttpStatus.CREATED );
    }

    @Override
    public ResponseEntity <PersonDTO> update(PersonDTO personDTO) {
        return new ResponseEntity <>( personService.update(personDTO), HttpStatus.CREATED );
    }

    @Override
    public ResponseEntity < Boolean > delete( String id ) {
        return personService.delete( id )
                ? ResponseEntity.ok( true )
                : new ResponseEntity <>( false, HttpStatus.NOT_FOUND );
    }
}
