package com.profile.tutorialesv2.repository;

import com.profile.tutorialesv2.model.TutorialVO;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TutorialRepository extends MongoRepository<TutorialVO, String> {
    List<TutorialVO> findByTitulo(String titulo);

    List<TutorialVO> findByPublicado(boolean publicado);
}