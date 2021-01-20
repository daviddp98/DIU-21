package com.example.tutoriales.controller;

import com.example.tutoriales.controller.constant.EndPointUris;
import com.example.tutoriales.model.dto.TutorialDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping(EndPointUris.API + EndPointUris.V1 + EndPointUris.TUTORIALS)
public interface TutorialApi {

    @GetMapping
    ResponseEntity<List<TutorialDTO>> getAllTutorials();

    @GetMapping
    ResponseEntity<List<TutorialDTO>> findByTitleContaining(@PathVariable String titulo);

    @GetMapping(EndPointUris.ID)
    ResponseEntity<List<TutorialDTO>> getTutorialByID(@PathVariable String id);

    @GetMapping
    ResponseEntity<List<TutorialDTO>> findByPublished();

    @PostMapping
    ResponseEntity<TutorialDTO> create(@RequestBody TutorialDTO tutorialDTO);

    @PutMapping(EndPointUris.ID)
    ResponseEntity<TutorialDTO> updateTutorial(@PathVariable String id, @RequestBody TutorialDTO tutorialDTO);

    @DeleteMapping(EndPointUris.ID)
    ResponseEntity<Boolean> deleteTutorial(@PathVariable String id);

    @DeleteMapping
    ResponseEntity<Boolean> deleteAllTutorials();
}