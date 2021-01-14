package com.example.demo.model;

import lombok.AllArgsConstructor; //crea el constructor con argumentos
import lombok.Getter; //genera los getters por defecto
import lombok.NoArgsConstructor; //crea el constructor por defecto
import lombok.Setter;
import org.springframework.data.annotation.Id; //identificador del dato, coherente con el de mongodb
import org.springframework.data.mongodb.core.mapping.Document;
//elemento de la base datos

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Document
public class City {
    @Id
    private String id;
    private String code;
    private String name;
}