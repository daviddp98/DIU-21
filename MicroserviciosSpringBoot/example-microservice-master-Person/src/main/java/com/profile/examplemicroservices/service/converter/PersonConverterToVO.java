package com.profile.examplemicroservices.service.converter;

import com.profile.examplemicroservices.model.PersonVO;
import com.profile.examplemicroservices.model.dto.PersonDTO;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class PersonConverterToVO implements Converter<PersonDTO, PersonVO> {

    @Override
    public PersonVO convert(PersonDTO personDTO) {
        return PersonVO.builder()
                .name( personDTO.getName() )
                .surname(personDTO.getSurname())
                .email(personDTO.getEmail())
                .phone(personDTO.getPhone())
                .build();
    }
}
