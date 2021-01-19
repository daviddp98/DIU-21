package com.example.tutoriales.controller;

import com.example.tutoriales.controller.constant.EndPointUris;
import com.example.tutoriales.model.TutorialVO;
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
import java.util.Optional;

@RequestMapping(EndPointUris.API + EndPointUris.V1 + EndPointUris.TUTORIALS)
public interface TutorialApi {

    @GetMapping
    ResponseEntity<List<TutorialDTO>> getAllTutorials();

    @GetMapping
    ResponseEntity<List<TutorialDTO>> findByTitleContaining(@PathVariable final String titulo);

    @GetMapping
    ResponseEntity<Optional<TutorialVO>> getTutorialByID(@PathVariable final String id);

    @GetMapping
    ResponseEntity<List<TutorialVO>> findByPublished();

    @PostMapping
    ResponseEntity<TutorialDTO> create(@RequestBody final TutorialDTO tutorialDTO);

    @PutMapping(EndPointUris.ID)
    ResponseEntity<TutorialDTO> updateTutorial(@PathVariable final String id, @RequestBody final TutorialDTO tutorialDTO);

    @DeleteMapping(EndPointUris.ID)
    ResponseEntity<Boolean> deleteTutorial(@PathVariable final String id);

    @DeleteMapping
    ResponseEntity<Boolean> deleteAllTutorials();
}