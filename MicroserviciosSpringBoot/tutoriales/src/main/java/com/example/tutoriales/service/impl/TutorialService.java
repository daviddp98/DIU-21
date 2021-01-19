package com.example.tutoriales.service.impl;

import com.example.tutoriales.model.TutorialVO;
import com.example.tutoriales.model.dto.TutorialDTO;

import java.util.List;
import java.util.Optional;

public interface TutorialService {

    List<TutorialDTO> findAll();

    TutorialDTO create(final TutorialDTO tutorial);

    TutorialDTO update(final String id, final TutorialDTO tutorial);

    boolean delete(final String id);

    List<TutorialDTO> findByTitleContaining(String titulo);

    Optional<TutorialVO> getTutorialByID(String id);

    List<TutorialVO> findByPublished(boolean publicado);

    boolean deleteAll();
}