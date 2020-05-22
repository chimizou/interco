package com.soat.service;

import com.soat.entities.Candidate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface ICandidateService {

    Candidate saveCandidate(Candidate candidate);

    Page<Candidate> getPaginatedCandidates(int page, int size); // Not used

    Candidate findCandidateByEmail(String email);

    Page<Candidate> findByFirstNameContains(String name, Pageable pageable);

    Candidate findCandidateById(Long candidateId);

    Optional<Candidate> findByCandidateById(Long candidateId);

    void deleteCandidateById(Long idCandidate);
}
