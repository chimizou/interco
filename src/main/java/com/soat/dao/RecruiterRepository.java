package com.soat.dao;

import com.soat.entities.Recruiter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface RecruiterRepository extends JpaRepository<Recruiter, Long> {

}
