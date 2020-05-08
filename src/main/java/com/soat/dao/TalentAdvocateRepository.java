package com.soat.dao;

import com.soat.entities.TalentAdvocate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin("*")
@RepositoryRestResource
public interface TalentAdvocateRepository extends JpaRepository<TalentAdvocate, Long> {

}
