package com.profile.examplemicroservices.service.converter;

import com.profile.examplemicroservices.model.PersonVO;
import com.profile.examplemicroservices.model.dto.PersonDTO;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class PersonConverterToDTO implements Converter<PersonVO, PersonDTO> {

    @Override
    public PersonDTO convert(PersonVO personVO) {
        return PersonDTO.builder()
                .name( personVO.getName() )
                .surname(personVO.getSurname())
                .email(personVO.getEmail())
                .phone(personVO.getPhone())
                .build();
    }
}
