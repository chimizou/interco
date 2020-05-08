package com.soat;

import com.soat.dao.CandidateRepository;
import com.soat.dao.RecruiterRepository;
import com.soat.dao.TalentAdvocateRepository;
import com.soat.entities.Candidate;
import com.soat.entities.Recruiter;
import com.soat.entities.TalentAdvocate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

@SpringBootApplication
public class IntercoApplication implements CommandLineRunner {

    @Autowired
    private RepositoryRestConfiguration restConfiguration;

    @Autowired
    private CandidateRepository candidateRepository;

    @Autowired
    private TalentAdvocateRepository talentAdvocateRepository;

    @Autowired
    private RecruiterRepository recruiterRepository;

    public static void main(String[] args) {
        SpringApplication.run(IntercoApplication.class, args);
    }

    @Override
    public void run(String... args) {
        //Permet d'exposer les id, Optionnel car Spring Data Rest utilise dejà les liens
        restConfiguration.exposeIdsFor(Candidate.class);
        restConfiguration.exposeIdsFor(Recruiter.class);

        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");

//        Candidate candidate1 = new Candidate(null, "Anaïs", "Remy", "email@email.com", df.parse("1990-12-03"), null);
//        Candidate candidate2 = new Candidate(null, "Anis", "Ben Yahia", "email@email.com", df.parse("1990-12-03"), null);
//        Candidate candidate3 = new Candidate(null, "Anguela", "Merkel", "email@email.com", df.parse("1990-12-03"), null);
//        Candidate candidate4 = new Candidate(null, "Monica", "Dufond", "email@email.com", df.parse("1990-02-12"), null);
//        Candidate candidate5 = new Candidate(null, "Marion", "Cotillard", "email@email.com", df.parse("1990-02-12"), null);
//        Candidate candidate6 = new Candidate(null, "Pascal", "Leroy", "email@email.com", df.parse("1992-12-03"), null);
//        Candidate candidate7 = new Candidate(null, "Pascal", "Murot", "email@email.com", df.parse("1992-06-09"), null);

        Candidate candidate1 = new Candidate();
        candidate1.setFirstName("Hamza");
        candidate1.setLastName("Ben");
        Candidate candidate2 = new Candidate();
        candidate2.setFirstName("Aline");
        candidate2.setLastName("Christopher");
        Candidate candidate3 = new Candidate();
        candidate3.setFirstName("Marcos");
        candidate3.setLastName("Dessaillit");

        TalentAdvocate talentAdvocate1 = new TalentAdvocate();
        talentAdvocate1.setFirstName("Christine");
        talentAdvocateRepository.save(talentAdvocate1);
        TalentAdvocate talentAdvocate2 = new TalentAdvocate();
        talentAdvocate2.setFirstName("Francois");
        talentAdvocateRepository.save(talentAdvocate2);

        candidate1.setTalentAdvocate(talentAdvocate1);
        candidate2.setTalentAdvocate(talentAdvocate2);
        candidate3.setTalentAdvocate(talentAdvocate1);

        candidateRepository.save(candidate1);
        candidateRepository.save(candidate2);
        candidateRepository.save(candidate3);

        recruiterRepository.save(new Recruiter(null, "Soat", "SOAT, ce sont 360 consultants IT et coachs agiles qui développent un cercle vertueux entre connaissances et réalisations techniques pour transformer positivement vos organisations.", "java, .net, python", "20 rue des frigos 75013 Paris", "https://become.soat.fr/"));
        recruiterRepository.save(new Recruiter(null, "Talan", "Talan, ce sont 360 consultants IT et coachs agiles qui développent un cercle vertueux entre connaissances et réalisations techniques pour transformer positivement vos organisations.", "java, .net, python", "20 rue des frigos 75013 Paris", "https://become.talan.fr/"));
        recruiterRepository.save(new Recruiter(null, "BlaBlaCar", "BlaBlaCar, ce sont 360 consultants IT et coachs agiles qui développent un cercle vertueux entre connaissances et réalisations techniques pour transformer positivement vos organisations.", "java, .net, python", "20 rue des frigos 75013 Paris", "https://become.bbc.fr/"));

        List<Candidate> candidates = candidateRepository.findAll();
        List<TalentAdvocate> talentAdvocate = talentAdvocateRepository.findAll();
        List<Recruiter> recruiters = recruiterRepository.findAll();

        candidates.forEach(e -> System.out.println("Candidate " + e.getIdCandidate() + ": " + e.getFirstName()));
        talentAdvocate.forEach(e -> System.out.println("Talent Advocate " + e.getIdTalentAdvocate() + ": " + e.getFirstName()));
        recruiters.forEach(e -> System.out.println("Recruiter " + e.getIdRecruiter() + ": " + e.getName()));
    }

}
