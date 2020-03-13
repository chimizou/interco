package com.soat;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;

import com.soat.dao.CandidatRepository;
import com.soat.entities.Candidat;

@SpringBootApplication
public class IntercoApplication implements CommandLineRunner{
	
	@Autowired
	private RepositoryRestConfiguration restConfiguration; 
	
	@Autowired
	private CandidatRepository candidatRepository;

	public static void main(String[] args) throws ParseException{
		SpringApplication.run(IntercoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		//Permet d'exposer les id, Optionnel car Spring Data Rest utilise dejà les liens
		restConfiguration.exposeIdsFor(Candidat.class);
		
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		
		candidatRepository.save(new Candidat("Anaïs", "Remy", df.parse("1990-12-03")));
		candidatRepository.save(new Candidat("Mickaël", "Dufond", df.parse("1990-02-12")));
		candidatRepository.save(new Candidat("Pascal", "Leroy", df.parse("1992-12-03")));
		candidatRepository.save(new Candidat("Pascal", "Murot", df.parse("1992-06-09")));
		
		List<Candidat> candidats = candidatRepository.findAll();
		
		candidats.forEach(e -> System.out.println(e.getNom()));
		
	}

}
