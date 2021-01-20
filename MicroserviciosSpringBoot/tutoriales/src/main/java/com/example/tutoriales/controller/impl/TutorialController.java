package com.example.tutoriales.controller.impl;

import com.example.tutoriales.controller.TutorialApi;
import com.example.tutoriales.model.TutorialVO;
import com.example.tutoriales.model.dto.TutorialDTO;
import com.example.tutoriales.service.impl.TutorialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TutorialController implements TutorialApi {

    @Autowired
    private TutorialService tutorialService;

    @Override
    public ResponseEntity<List<TutorialDTO>> getAllTutorials() {
        return ResponseEntity.ok(tutorialService.findAll());
    }

    @Override
    public ResponseEntity<List<TutorialDTO>> findByTitleContaining(String titulo) {
        return ResponseEntity.ok(tutorialService.findByTitleContaining(titulo));
    }

    @Override
    public ResponseEntity<List<TutorialDTO>> getTutorialByID(String id) {
        return ResponseEntity.ok(tutorialService.getTutorialByID(id));
    }

    @Override
    public ResponseEntity<List<TutorialDTO>> findByPublished() {
        return ResponseEntity.ok(tutorialService.findByPublished(true));
    }

    @Override
    public ResponseEntity<TutorialDTO> create(TutorialDTO tutorialDTO) {
        return new ResponseEntity<>(tutorialService.create(tutorialDTO), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<TutorialDTO> updateTutorial(String id, TutorialDTO tutorialDTO) {
        return new ResponseEntity<>(tutorialService.update(id, tutorialDTO), HttpStatus.CREATED);
    }


    @Override
    public ResponseEntity<Boolean> deleteTutorial(String id) {
        return tutorialService.delete(id)
                ? ResponseEntity.ok(true)
                : new ResponseEntity<>(false, HttpStatus.NOT_FOUND);
    }

    @Override
    public ResponseEntity<Boolean> deleteAllTutorials() {
        return ResponseEntity.ok(tutorialService.deleteAll());
    }
}