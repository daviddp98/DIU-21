package com.example.tutoriales.service.impl;

import com.example.tutoriales.model.dto.TutorialDTO;

import java.util.List;

public interface TutorialService {

    List<TutorialDTO> findAll();

    TutorialDTO create(final TutorialDTO tutorial);

    TutorialDTO update(final String id, final TutorialDTO tutorial);

    boolean delete(final String id);

    List<TutorialDTO> findByTitleContaining(String titulo);

    List<TutorialDTO> getTutorialByID(String id);

    List<TutorialDTO> findByPublished(boolean publicado);

    boolean deleteAll();
}