package com.profile.tutorialesv2.controller;

import com.profile.tutorialesv2.controller.constant.EndPointUris;
import com.profile.tutorialesv2.model.dto.TutorialDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping(EndPointUris.API + EndPointUris.V1 + EndPointUris.TUTORIALES)
public interface TutorialApi {

    @GetMapping("/findAllTutorials")
    ResponseEntity<List<TutorialDTO>> findAllTutorials();

    @GetMapping("/findByTitleContaining/{titulo}")
    ResponseEntity<List<TutorialDTO>> findByTitleContaining(@PathVariable String titulo);

    @GetMapping("/getTutorialByID/{id}")
    ResponseEntity<TutorialDTO> getTutorialByID(@PathVariable String id);

    @GetMapping("/findByPublished")
    ResponseEntity<List<TutorialDTO>> findByPublished();

    @PostMapping("/createTutorial")
    ResponseEntity<TutorialDTO> createTutorial(@RequestBody TutorialDTO tutorialDTO);

    @PutMapping("/updateTutorial")
    ResponseEntity<TutorialDTO> updateTutorial(@RequestBody TutorialDTO tutorialDTO);

    @DeleteMapping("/deleteTutorial/{id}")
    ResponseEntity<Boolean> deleteTutorial(@PathVariable("id") String id);

    @DeleteMapping("/deleteAllTutorials")
    ResponseEntity<Boolean> deleteAllTutorials();
}