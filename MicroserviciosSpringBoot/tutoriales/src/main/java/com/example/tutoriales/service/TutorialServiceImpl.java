package com.example.tutoriales.service;

import com.example.tutoriales.model.TutorialVO;
import com.example.tutoriales.model.dto.TutorialDTO;
import com.example.tutoriales.repository.TutorialRepository;
import com.example.tutoriales.service.converter.TutorialConverterToDTO;
import com.example.tutoriales.service.converter.TutorialConverterToVO;
import com.example.tutoriales.service.impl.TutorialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        return tutorialConverterToDTO.convert(tutorialRepository.insert(tutorialVO));
    }

    @Override
    public TutorialDTO update(String id, TutorialDTO tutorial) {
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
        return tutorialRepository.findByTitleContaining(titulo)
                .stream()
                .map(tutorialConverterToDTO::convert)
                .collect(Collectors.toList());
    }

    @Override
    public List<TutorialDTO> getTutorialByID(String id) {
        return tutorialRepository.findById(id)
                .stream()
                .map(tutorialConverterToDTO::convert)
                .collect(Collectors.toList());
    }

    @Override
    public List<TutorialDTO> findByPublished(boolean publicado) {
        return tutorialRepository.findByPublished(true)
                .stream()
                .map(tutorialConverterToDTO::convert)
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
}