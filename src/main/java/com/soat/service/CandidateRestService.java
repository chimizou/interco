package com.soat.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.soat.dao.CandidateRepository;
import com.soat.entities.Candidate;

/**
 * @RestController est commenter car on voudrais utilisé la méthode à travers l'annotation @RepositoryRestResource 
 * dans l'interface CandidateRepository qui elle permet d'avoir un enwemble prêt à utiliser de méthode.
 * 
 * Elle reste valable si on veut avoir des serices avec des traitements assez complexes
 *
 */

@RestController 
public class CandidateRestService {
	
	@Autowired
	private CandidateRepository candidateRepository;
	
	// Les résultat de se service n'auront pas le même format de json que les service proposé par spring data rest, qui eux, vont avoir
	// un json sous le format HATEOAS. (avec des lien en plus)  
	@GetMapping(value = "/listCandidates")
	public Page<Candidate> listCandidates(int page, int size) {
		return candidateRepository.findAll(PageRequest.of(page, size, Sort.by("nom").descending().and(Sort.by("prenom").descending())));
	}

}
