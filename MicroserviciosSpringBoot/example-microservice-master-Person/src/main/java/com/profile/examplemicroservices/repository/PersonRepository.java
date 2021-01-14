package com.profile.examplemicroservices.repository;

import com.profile.examplemicroservices.model.PersonVO;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends MongoRepository<PersonVO, String > {
}
