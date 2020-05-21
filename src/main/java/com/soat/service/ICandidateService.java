package com.soat.service;

import com.soat.entities.Candidate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ICandidateService {

    Candidate saveCandidate(Candidate candidate);

    Page<Candidate> getPaginatedCandidates(int page, int size); // Not used

    Candidate findCandidateByEmail(String email);

    Page<Candidate> findByFirstNameContains(String name, Pageable pageable);

    Candidate findCandidateById(Long candidateId);
}
