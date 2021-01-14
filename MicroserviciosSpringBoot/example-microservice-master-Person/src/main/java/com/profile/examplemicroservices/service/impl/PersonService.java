package com.profile.examplemicroservices.service.impl;

import com.profile.examplemicroservices.model.dto.PersonDTO;

import java.util.List;

public interface PersonService {

    List <PersonDTO> getAll();

    PersonDTO create(final PersonDTO city );

    PersonDTO update(final PersonDTO city );

    boolean delete( final String id );


}
