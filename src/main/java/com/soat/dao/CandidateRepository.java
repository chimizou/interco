package com.soat.dao;

import com.soat.entities.Candidate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import java.util.List;

@RepositoryRestResource
public interface CandidateRepository extends JpaRepository<Candidate, Long> {

    //A part les méthodes par defaut fournis pas @RepositoryRestResource, on pourra définir nos propres méthodes.
    @RestResource(path = "/byFirstName")
    List<Candidate> findByFirstNameContains(@Param("firstName") String name);

    //Exemple de requête : candidates/search/byFirstNamePage?nom=h&page=0&size=5
    @RestResource(path = "/byFirstNamePage")
    Page<Candidate> findByFirstNameContains(@Param("firstName") String name, Pageable pageable);

    Candidate findByEmailIgnoreCase(String email);
}
