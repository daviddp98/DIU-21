package com.profile.examplemicroservices.model.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder

public class PersonDTO {
    private String name;
    private String surname;
    private String email;
    private Long phone;
}