package com.profile.tutorialesv2.controller.impl;

import com.profile.tutorialesv2.controller.TutorialApi;
import com.profile.tutorialesv2.model.TutorialVO;
import com.profile.tutorialesv2.model.dto.TutorialDTO;
import com.profile.tutorialesv2.repository.TutorialRepository;
import com.profile.tutorialesv2.service.impl.TutorialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class TutorialController implements TutorialApi {

    @Autowired
    private TutorialService tutorialService;

    @Autowired
    private TutorialRepository tutorialRepository;

    @Override
    public ResponseEntity<List<TutorialDTO>> findAllTutorials() {
        return ResponseEntity.ok(tutorialService.findAll());
    }

    @Override
    public ResponseEntity<List<TutorialDTO>> findByTitleContaining(String titulo) {
        return ResponseEntity.ok(tutorialService.findByTitleContaining(titulo));
    }

    @Override
    public ResponseEntity<TutorialDTO> getTutorialByID(String id) {
        return ResponseEntity.ok(tutorialService.getTutorialByID(id));
    }

    @Override
    public ResponseEntity<List<TutorialDTO>> findByPublished() {
        return ResponseEntity.ok(tutorialService.findByPublished());
    }


    @Override
    public ResponseEntity<TutorialDTO> createTutorial(TutorialDTO tutorialDTO) {
        return new ResponseEntity<>(tutorialService.create(tutorialDTO), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<TutorialDTO> updateTutorial(TutorialDTO tutorialDTO) {
        return new ResponseEntity<>(tutorialService.update(tutorialDTO), HttpStatus.CREATED);
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