package com.profile.examplemicroservices.service;

import com.profile.examplemicroservices.model.PersonVO;
import com.profile.examplemicroservices.model.dto.PersonDTO;
import com.profile.examplemicroservices.repository.PersonRepository;
import com.profile.examplemicroservices.service.converter.PersonConverterToDTO;
import com.profile.examplemicroservices.service.converter.PersonConverterToVO;
import com.profile.examplemicroservices.service.impl.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PersonServiceImpl implements PersonService {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private PersonConverterToDTO personConverterToDTO;

    @Autowired
    private PersonConverterToVO personConverterToVO;


    @Override
    public List <PersonDTO> getAll() {
        return personRepository.findAll()
                .stream()
                .map( personConverterToDTO::convert )
                .collect( Collectors.toList());
    }

    @Override
    public PersonDTO create(PersonDTO city ) {
        PersonVO personVO = personConverterToVO.convert( city );
        return personConverterToDTO.convert( personRepository.insert(personVO) );
    }

    @Override
    public PersonDTO update(PersonDTO city ) {
        PersonVO personVO = personConverterToVO.convert( city );
        return personConverterToDTO.convert( personRepository.save(personVO) );
    }

    @Override
    public boolean delete( String id ) {
        try{
            personRepository.deleteById( id );
            return Boolean.TRUE;
        } catch (Exception e){
            return Boolean.FALSE;
        }
    }
}
