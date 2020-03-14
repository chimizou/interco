package com.soat.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.soat.entities.Candidate;

// Tomcat l'orsqu'il reçoit une requête à partir d'un autre domain, donc on lui donne l'acces.
@CrossOrigin("*")
@RepositoryRestResource
public interface CandidateRepository extends JpaRepository<Candidate, Long> {

	//A part les méthodes par defaut fournis pas @RepositoryRestResource, on pourra définir nos propres méthodes.
	@RestResource(path = "/byFirstName")
	public List<Candidate>  findByFirstNameContains(@Param("firstName") String name);
	
	//Exemple de requête : candidates/search/byFirstNamePage?nom=h&page=0&size=5
	@RestResource(path = "/byFirstNamePage")
	public Page<Candidate>  findByFirstNameContains(@Param("firstName") String name, Pageable pageable);
	
}
