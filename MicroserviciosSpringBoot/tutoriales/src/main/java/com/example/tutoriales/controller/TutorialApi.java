package com.example.tutoriales.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping(EndPointUris.API + EndPointUris.V1 + EndPointUris.CITIES)
public interface TutorialApi {

    @GetMapping
    ResponseEntity<List<TutorialDTO>> getAll();

    @PostMapping
    ResponseEntity<TutorialDTO> create(@RequestBody final TutorialDTO tutorialDTO);

    @PutMapping
    ResponseEntity<TutorialDTO> update(@RequestBody final TutorialDTO tutorialDTO);

    @DeleteMapping(EndPointUris.ID)
    ResponseEntity<Boolean> delete(@PathVariable final String id);
}