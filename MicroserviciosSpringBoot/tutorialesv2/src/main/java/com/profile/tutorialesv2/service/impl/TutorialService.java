package com.profile.tutorialesv2.service.impl;

import com.profile.tutorialesv2.model.dto.TutorialDTO;

import java.util.List;

public interface TutorialService {

    List<TutorialDTO> findAll();

    TutorialDTO create(TutorialDTO tutorial);

    TutorialDTO update(TutorialDTO tutorial);

    boolean delete(String id);

    List<TutorialDTO> findByTitleContaining(String titulo);

    TutorialDTO getTutorialByID(String id);

    List<TutorialDTO> findByPublished();

    boolean deleteAll();
}