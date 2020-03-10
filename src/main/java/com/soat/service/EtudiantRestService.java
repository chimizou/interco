package com.soat.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.soat.dao.EtudiantRepository;
import com.soat.entities.Etudiant;

@RestController
public class EtudiantRestService {
	
	@Autowired
	private EtudiantRepository etudiantRepository;
	
	@RequestMapping(value="/saveEtudiant", method = RequestMethod.GET)
	public Etudiant saveEtudiant(Etudiant e) {
		return etudiantRepository.save(e);
	}
	
	@RequestMapping(value = "/etudiants")
	public Page<Etudiant> listEtudiants(int page, int size) {
		return etudiantRepository.findAll(PageRequest.of(page, size, Sort.by("nom").descending().and(Sort.by("prenom").descending())));
	}

}
