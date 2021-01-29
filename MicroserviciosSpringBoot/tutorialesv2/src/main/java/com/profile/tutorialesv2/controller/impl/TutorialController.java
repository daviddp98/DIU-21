package com.profile.tutorialesv2.controller.impl;

import com.profile.tutorialesv2.controller.TutorialApi;
import com.profile.tutorialesv2.model.TutorialVO;
import com.profile.tutorialesv2.model.dto.TutorialDTO;
import com.profile.tutorialesv2.repository.TutorialRepository;
import com.profile.tutorialesv2.service.impl.Notification;
import com.profile.tutorialesv2.service.impl.TutorialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;

@CrossOrigin
@RestController
public class TutorialController implements TutorialApi {

    private final CopyOnWriteArrayList<SseEmitter> emitters = new CopyOnWriteArrayList<>();

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

    @EventListener
    public void onNotification(Notification notification) {
        List<SseEmitter> deadEmitters = new ArrayList<>();
        this.emitters.forEach(emitter -> {
            try {
                emitter.send(notification);

            } catch (Exception e) {
                deadEmitters.add(emitter);
            }
        });
        this.emitters.remove(deadEmitters);
    }

    public SseEmitter getNewNotification() {
        SseEmitter emitter = new SseEmitter();
        this.emitters.add(emitter);

        emitter.onCompletion(() -> this.emitters.remove(emitter));
        emitter.onTimeout(() -> {
            emitter.complete();
            this.emitters.remove(emitter);
        });
        return emitter;
    }
}