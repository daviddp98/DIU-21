package com.example.tutoriales.repository;

import com.example.tutoriales.model.TutorialVO;
import com.example.tutoriales.model.dto.TutorialDTO;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;

@Repository
public interface TutorialRepository extends MongoRepository<TutorialVO, String> {
   // List<TutorialVO> findByTitleContaining(String titulo);
    //List<TutorialVO> findByPublished(boolean publicado);

}