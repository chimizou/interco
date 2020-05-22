package com.soat.controller;

import com.soat.dto.CandidateDTO;
import com.soat.entities.Candidate;
import com.soat.service.CandidateServiceImpl;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/candidate")
public class CandidateController {

    public static final Logger LOGGER = LoggerFactory.getLogger(CandidateController.class);

    @Autowired
    private CandidateServiceImpl candidateService;

    @GetMapping(value = "/listCandidates")
    public Page<Candidate> getPaginatedCandidates(int page, int size) {
        return candidateService.getPaginatedCandidates(page, size);
    }

    @GetMapping(value = "/{idCandidate}")
    public ResponseEntity<CandidateDTO> findCandidateById(@PathVariable("idCandidate") final Long candidateId) {
        Candidate candidate = candidateService.findCandidateById(candidateId);
        if (candidate == null) {
            String errorMessage = "The candidate is not found in the database";
            LOGGER.info(errorMessage);
            return new ResponseEntity<CandidateDTO>(HttpStatus.NO_CONTENT);
        }
        CandidateDTO candidateDTO = mapCandidateToCandidateDTO(candidate);
        return new ResponseEntity<CandidateDTO>(candidateDTO, HttpStatus.OK);
    }


    @PutMapping(value = "/updateCandidate/{idCandidate}")
    ResponseEntity<CandidateDTO> updateCandidate(@RequestBody CandidateDTO candidateDTORequest, @PathVariable Long idCandidate) {
        Candidate newCandidate = mapCandidateDTOToCandidate(candidateDTORequest);
        Candidate candidateToUpdate = candidateService.findByCandidateById(idCandidate)
                .map(candidate -> {
                    candidate.setFirstName(newCandidate.getFirstName());
                    candidate.setLastName(newCandidate.getLastName());
                    candidate.setBirthDate(newCandidate.getBirthDate());
                    candidate.setEmail(newCandidate.getEmail());
                    candidate.setTalentAdvocate(newCandidate.getTalentAdvocate());
                    candidate.setCreationDate(LocalDate.now());
                    return candidateService.saveCandidate(candidate);
                })
                .orElseGet(() -> {
                    newCandidate.setIdCandidate(idCandidate);
                    return candidateService.saveCandidate(newCandidate);
                });
        CandidateDTO candidateDTO = mapCandidateToCandidateDTO(candidateToUpdate);
        return new ResponseEntity<CandidateDTO>(candidateDTO, HttpStatus.OK);

    }

    @GetMapping(path = "/byFirstNamePage")
    public ResponseEntity<Page<CandidateDTO>> findByFirstNameContains(@RequestParam("firstName") String name, Pageable pageable) {
        Page<Candidate> candidates = candidateService.findByFirstNameContains(name, pageable);
        if (!CollectionUtils.isEmpty(candidates.getContent())) {
            Page<CandidateDTO> pageCandidateDTO = candidates.map(candidate -> mapCandidateToCandidateDTO(candidate));
            return new ResponseEntity<Page<CandidateDTO>>(pageCandidateDTO, HttpStatus.OK);
        }
        return new ResponseEntity<Page<CandidateDTO>>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/addCandidate")
    public ResponseEntity<CandidateDTO> createNewCandidate(@RequestBody CandidateDTO candidateDTORequest) {
        Candidate existingCandidate = candidateService.findCandidateByEmail(candidateDTORequest.getEmail());
        if (existingCandidate != null) {
            return new ResponseEntity<CandidateDTO>(HttpStatus.CONFLICT);
        }
        Candidate candidateRequest = mapCandidateDTOToCandidate(candidateDTORequest);
        Candidate candidate = candidateService.saveCandidate(candidateRequest);
        if (candidate != null && candidate.getIdCandidate() != null) {
            CandidateDTO candidateDTO = mapCandidateToCandidateDTO(candidate);
            return new ResponseEntity<CandidateDTO>(candidateDTO, HttpStatus.CREATED);
        }
        return new ResponseEntity<CandidateDTO>(HttpStatus.NOT_MODIFIED);

    }

    @DeleteMapping("/deleteCandidate/{idCandidate}")
    void deleteEmployee(@PathVariable Long idCandidate) {
        candidateService.deleteCandidateById(idCandidate);
    }

    private Candidate mapCandidateDTOToCandidate(CandidateDTO candidateDTO) {
        ModelMapper mapper = new ModelMapper();
        Candidate candidate = mapper.map(candidateDTO, Candidate.class);
        candidate.setCreationDate(LocalDate.now());
        return candidate;
    }

    private CandidateDTO mapCandidateToCandidateDTO(Candidate candidate) {
        ModelMapper mapper = new ModelMapper();
        CandidateDTO candidateDTO = mapper.map(candidate, CandidateDTO.class);
        return candidateDTO;
    }

}
