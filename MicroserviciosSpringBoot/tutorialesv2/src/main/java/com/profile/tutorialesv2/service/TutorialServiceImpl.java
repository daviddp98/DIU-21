package com.profile.tutorialesv2.service;

import com.profile.tutorialesv2.model.TutorialVO;
import com.profile.tutorialesv2.model.dto.TutorialDTO;
import com.profile.tutorialesv2.repository.TutorialRepository;
import com.profile.tutorialesv2.service.converter.TutorialConverterToDTO;
import com.profile.tutorialesv2.service.converter.TutorialConverterToVO;
import com.profile.tutorialesv2.service.impl.Notification;
import com.profile.tutorialesv2.service.impl.TutorialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TutorialServiceImpl implements TutorialService {

    @Autowired
    private TutorialRepository tutorialRepository;

    @Autowired
    private TutorialConverterToDTO tutorialConverterToDTO;

    @Autowired
    private TutorialConverterToVO tutorialConverterToVO;

    public final ApplicationEventPublisher eventPublisher;

    public TutorialServiceImpl(ApplicationEventPublisher eventPublisher) {
        this.eventPublisher = eventPublisher;
    }

    @Override
    public List<TutorialDTO> findAll() {
        return tutorialRepository.findAll()
                .stream()
                .map(tutorialConverterToDTO::convert)
                .collect(Collectors.toList());
    }

    @Override
    public TutorialDTO create(TutorialDTO tutorial) {
        TutorialVO tutorialVO = tutorialConverterToVO.convert(tutorial);

        try {
            publishJobNotifications();
            return tutorialConverterToDTO.convert(tutorialRepository.insert(tutorialVO));
        } catch (Exception e) {
            return tutorialConverterToDTO.convert(tutorialRepository.insert(tutorialVO));
        }
    }

    @Override
    public TutorialDTO update(TutorialDTO tutorial) {
        TutorialVO tutorialVO = tutorialConverterToVO.convert(tutorial);
        return tutorialConverterToDTO.convert(tutorialRepository.save(tutorialVO));
    }

    @Override
    public boolean delete(String id) {
        try {
            tutorialRepository.deleteById(id);
            return Boolean.TRUE;
        } catch (Exception e) {
            return Boolean.FALSE;
        }
    }

    @Override
    public List<TutorialDTO> findByTitleContaining(String titulo) {
        return tutorialRepository.findAll()
                .stream()
                .map(tutorialConverterToDTO::convert)
                .filter(tutorialDTO -> tutorialDTO.getTitulo().contains(titulo))
                .collect(Collectors.toList());
    }

    @Override
    public TutorialDTO getTutorialByID(String id) {
        return tutorialConverterToDTO.convert(tutorialRepository.findById(id).get());
    }

    @Override
    public List<TutorialDTO> findByPublished() {
        return tutorialRepository.findAll()
                .stream()
                .map(tutorialConverterToDTO::convert)
                .filter(tutorialDTO -> tutorialDTO.isPublicado())
                .collect(Collectors.toList());
    }

    @Override
    public boolean deleteAll() {
        try {
            tutorialRepository.deleteAll();
            return Boolean.TRUE;
        } catch (Exception e) {
            return Boolean.FALSE;
        }
    }

    public void publishJobNotifications() throws InterruptedException {
        Integer jobId = Notification.getNextJobId();
        Notification nStarted = new Notification("Job No. " + jobId + " started.", new Date());

        this.eventPublisher.publishEvent(nStarted);

        Thread.sleep(2000);
        Notification nFinished = new Notification("Job No. " + jobId + " finished.", new Date());
        this.eventPublisher.publishEvent(nFinished);
    }
}