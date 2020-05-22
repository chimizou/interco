package com.soat.service;

import com.soat.dao.CandidateRepository;
import com.soat.entities.Candidate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service("candidateService")
@Transactional
public class CandidateServiceImpl implements ICandidateService {

    @Autowired
    private CandidateRepository candidateRepository;

    @Override
    public Candidate saveCandidate(Candidate candidate) {
        return candidateRepository.save(candidate);
    }

    @Override
    public Page<Candidate> getPaginatedCandidates(int page, int size) {
        return candidateRepository.findAll(PageRequest.of(page, size, Sort.by("firstName").descending().and(Sort.by("lastName").descending())));
    }

    @Override
    public Candidate findCandidateByEmail(String email) {
        return candidateRepository.findByEmailIgnoreCase(email);
    }

    @Override
    public Page<Candidate> findByFirstNameContains(String name, Pageable pageable) {
        return candidateRepository.findByFirstNameContainsIgnoreCase(name, pageable);
    }

    @Override
    public Candidate findCandidateById(Long candidateId) {
        return candidateRepository.getOne(candidateId);
    }

    @Override
    public Optional<Candidate> findByCandidateById(Long candidateId) {
        return candidateRepository.findById(candidateId);
    }

    @Override
    public void deleteCandidateById(Long idCandidate) {
        candidateRepository.deleteById(idCandidate);
    }
}
