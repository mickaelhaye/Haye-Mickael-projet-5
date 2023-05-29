package com.safetynet.safetynetalerts.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.safetynet.safetynetalerts.model.PersonModel;

@Repository
public interface PersonModelRepository extends CrudRepository<PersonModel, Long> {

}
