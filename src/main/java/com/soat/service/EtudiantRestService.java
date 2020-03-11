package com.soat.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.soat.dao.EtudiantRepository;
import com.soat.entities.Etudiant;

/**
 * @RestController est commenter car on voudrais utilisé la méthode à travers l'annotation @RepositoryRestResource 
 * dans l'interface EtudiantRepository qui elle permet d'avoir un enwemble prêt à utiliser de méthode.
 * 
 * Elle reste valable si on veut avoir des serices avec des traitements assez complexes
 *
 */

@RestController 
public class EtudiantRestService {
	
	@Autowired
	private EtudiantRepository etudiantRepository;
	
	// Les résultat de se service n'auront pas le même format de json que les service proposé par spring data rest, qui eux, vont avoir
	// un json sous le format HATEOAS. (avec des lien en plus)  
	@GetMapping(value = "/listEtudiants")
	public Page<Etudiant> listEtudiants(int page, int size) {
		return etudiantRepository.findAll(PageRequest.of(page, size, Sort.by("nom").descending().and(Sort.by("prenom").descending())));
	}

}
