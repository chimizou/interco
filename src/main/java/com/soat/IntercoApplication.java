package com.soat;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import com.soat.dao.RecruiterRepository;
import com.soat.entities.Recruiter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;

import com.soat.dao.CandidateRepository;
import com.soat.entities.Candidate;

@SpringBootApplication
public class IntercoApplication implements CommandLineRunner{
	
	@Autowired
	private RepositoryRestConfiguration restConfiguration; 
	
	@Autowired
	private CandidateRepository candidateRepository;

	@Autowired
	private RecruiterRepository recruiterRepository;

	public static void main(String[] args) throws ParseException{
		SpringApplication.run(IntercoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		//Permet d'exposer les id, Optionnel car Spring Data Rest utilise dejà les liens
		restConfiguration.exposeIdsFor(Candidate.class);
		restConfiguration.exposeIdsFor(Recruiter.class);

		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		
		candidateRepository.save(new Candidate(null, "Anaïs", "Remy", df.parse("1990-12-03"), null));
		candidateRepository.save(new Candidate(null, "Anis", "Ben Yahia", df.parse("1990-12-03"), null));
		candidateRepository.save(new Candidate(null, "Anguela", "Merkel", df.parse("1990-12-03"), null));
		candidateRepository.save(new Candidate(null, "Monica", "Dufond", df.parse("1990-02-12"),null));
		candidateRepository.save(new Candidate(null, "Marion", "Cotillard", df.parse("1990-02-12"),null));
		candidateRepository.save(new Candidate(null, "Pascal", "Leroy", df.parse("1992-12-03"),null));
		candidateRepository.save(new Candidate(null, "Pascal", "Murot", df.parse("1992-06-09"),null));

		recruiterRepository.save(new Recruiter(null,"Soat", "SOAT, ce sont 360 consultants IT et coachs agiles qui développent un cercle vertueux entre connaissances et réalisations techniques pour transformer positivement vos organisations.", "java, .net, python", "20 rue des frigos 75013 Paris", "https://become.soat.fr/"));
		recruiterRepository.save(new Recruiter(null,"Talan", "Talan, ce sont 360 consultants IT et coachs agiles qui développent un cercle vertueux entre connaissances et réalisations techniques pour transformer positivement vos organisations.", "java, .net, python", "20 rue des frigos 75013 Paris", "https://become.talan.fr/"));
		recruiterRepository.save(new Recruiter(null,"BlaBlaCar", "BlaBlaCar, ce sont 360 consultants IT et coachs agiles qui développent un cercle vertueux entre connaissances et réalisations techniques pour transformer positivement vos organisations.", "java, .net, python", "20 rue des frigos 75013 Paris", "https://become.bbc.fr/"));

		List<Candidate> candidates = candidateRepository.findAll();
		List<Recruiter> recruiters = recruiterRepository.findAll();

		candidates.forEach(e -> System.out.println("Candidate " + e.getIdCandidate() + ": " + e.getFirstName()));
		recruiters.forEach(e -> System.out.println("Recruiter " + e.getIdRecruiter() + ": " + e.getName()));
	}

}
